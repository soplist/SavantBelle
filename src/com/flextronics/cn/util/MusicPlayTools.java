package com.flextronics.cn.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.util.Log;

import com.flextronics.cn.activity.R;

/**
 * 音乐播放工具类
 * @author ZhangGuoYin
 *
 */
public class MusicPlayTools {
	private final static String TAG = "MusicPlayTools";
	private int errorCount;

	/**根据问题，为播放器准备歌曲
	 * 
	 * @param context
	 * @param mp
	 * @param question
	 * @param canPlay
	 * @param filePath
	 */
	public void prepareQuestionMusic(final Context context, MediaPlayer mp, 
			Object question, boolean canPlay, String filePath){
		//题目不能为空
		if (question == null) {
			return;
		}
		
		if (!canPlay) {
			return;
		}
		if (mp.isPlaying()) {
			mp.pause();
			mp.stop();
		}
		mp.reset();
		//创建文件对象
		File file = new File(Constants.MUSIC_DIRECTORY_PATH + filePath);
		
		try {
			//如果文件不存在
			if (!file.exists()) {
				//打开文件流写入文件
				Log.d(TAG, "music file is not exist, we will create it");
				InputStream inputStream = context.getAssets().open(filePath);
				file = CommonUtil.createMusicFile(Constants.MUSIC_DIRECTORY_PATH + filePath, inputStream);				
			}
			
			mp.setDataSource(file.getAbsolutePath());
			mp.prepare();
			//mp.prepare();
			//mp.start();
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "The media player has IllegalArgumentException.");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			Log.e(TAG, "The media player has IllegalStateException.");
			e.printStackTrace();
		} catch (IOException e) {
			//如果文件读写错误，将重试25次；如果还不行，则将先前的文件删除再重试25次；
			//还不行的话，则报内部错误
			Log.e(TAG, "The media player has IOException.");
			e.printStackTrace();
			errorCount ++;
			
			if (errorCount <= 25) {
				prepareQuestionMusic(context, mp, question, canPlay, filePath);
			}else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					prepareQuestionMusic(context, mp, question, canPlay, filePath);
				}
			}else {
				new AlertDialog.Builder(context)
				.setTitle(R.string.tips)
				.setMessage(R.string.internal_error)
				.setCancelable(false)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						((Activity) context).finish();
					}
				})
				.show();
				errorCount = 0;
			}			
		}
		errorCount = 0;
	}
}
