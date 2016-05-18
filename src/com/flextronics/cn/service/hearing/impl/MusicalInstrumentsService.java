package com.flextronics.cn.service.hearing.impl;


import java.io.File;
import java.io.InputStream;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.service.hearing.IMusicalInstrumentsService;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

public class MusicalInstrumentsService implements IMusicalInstrumentsService {
	//声明一个常量
	public static final String TAG="MusicalInstrumentsService";
	//声明一个Context对象
	private Context context;
	//声明一个MediaPlayer对象
	private MediaPlayer mediaPlayer=new MediaPlayer();
	//声明一个int类型变量
	private int errorCount;
	//声明一个String类型数组
	private String [] filePathArray;
	/**
	 * 初始化方法
	 * Context 当前的Activity
	 * @param context
	 */
	public void init(Context context) {
		this.context = context;
	}
	
	/**
	 * 辨识音乐 文件播放
	 * @param musicalInstrumentsName 乐器名称 
	 */
	public void Melody(int musicalInstrumentsName) {
		Log.d(TAG, "musicalInstrumentsName:"+musicalInstrumentsName);
		filePathArray=context.getResources().getStringArray(R.array.MelodyFilePath);
		playMusicService(filePathArray[musicalInstrumentsName-100]);
	}
	
	/**
	 * 音乐播放服务
	 * @param path 路径
	 */
	private void playMusicService(String path) {
		File file = new File(Constants.MUSIC_DIRECTORY_PATH + path);
		try {
			if (!file.exists()) {
				InputStream stream = context.getAssets().open(path);
				file = CommonUtil.createMusicFile(Constants.MUSIC_DIRECTORY_PATH + path, stream);
			}
			mediaPlayer.reset();
			mediaPlayer.setDataSource(file.getAbsolutePath());
			mediaPlayer.prepare();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mediaPlayer.reset();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			errorCount++;
			if (errorCount <= 25) {
				playMusicService(path);
			} else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playMusicService(path);
				}
			}
		}
	}
	/**
	 * 停止旋律的播放
	 */
	public void stop(){
		if(mediaPlayer.isPlaying())
		{
			mediaPlayer.pause();
			mediaPlayer.stop();
		}
			mediaPlayer.release();
	}
}
