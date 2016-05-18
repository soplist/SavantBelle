package com.flextronics.cn.service.hearing.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.service.hearing.IRhythmService;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class RhythmService implements IRhythmService {
	//声明一个Context对象
	private Context context;
	//声明一个MediaPlayer对象
	private MediaPlayer mediaPlayer = new MediaPlayer();
	//声明一个int类型变量
	private int currentMusic;
	//声明一个int类型变量
	private int musicArrayLength;
	//声明一个int类型变量
	private int errorCount;
	//声明一个String类型数组
	private String[] filePathArray;
	//声明一个int类型数组
	public void init(Context context) {
		this.context=context;
	}

	public void singleToneService(int musicalInstrumentType, int rhythmType) {
		filePathArray = null;
		currentMusic = 0;
		musicArrayLength = 0;
		if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.CYMBALS) {
			filePathArray = getString(R.array.CymbalsRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.GLOCK) {
			filePathArray = getString(R.array.GlockRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.GUO) {
			filePathArray = getString(R.array.GuoRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.KICK) {
			filePathArray = getString(R.array.KickRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.PIANO) {
			filePathArray = getString(R.array.PianoRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.SNARE) {
			filePathArray = getString(R.array.SnareRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.TRIANGLE) {
			filePathArray = getString(R.array.TriangleRhythmFilePath,
					rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.TRUMPET) {
			filePathArray = getString(R.array.TrumpetRhythmFilePath, rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else if (musicalInstrumentType == MusicalInstruments.PercussionRhythm.WOODBLOCK) {
			filePathArray = getString(R.array.WoodblockRhythmFilePath,
					rhythmType);
			playMusicService(filePathArray[currentMusic]);
		} else {

		}

	}
	
	public void complexToneService(List<Integer> musicalInstrumentsList,
			List<Map<Integer, List<Integer>>> rhythmList) {
		filePathArray = null;
		currentMusic = 0;
		musicArrayLength = 0;
		if(musicalInstrumentsList.size()>0){
			if (rhythmList.size() > 0) {
				filePathArray = getString(rhythmList);
				for (int i = 0; i < filePathArray.length; i++) {
					Log.d("filePathArray", "" + filePathArray[i]);
				}
				Log.d("filePathArray.length", filePathArray.length + "");
				playMusicService(filePathArray[currentMusic]);
			}
		}
	}
	
	private String[] getString(int instrumentArray, int rhythmType) {
		String[] metronomeRhythmFilePath = context.getResources()
				.getStringArray(R.array.MetronomeRhythmFilePath);
		String[] file = context.getResources().getStringArray(instrumentArray);
		String[] newString = new String[2];
		String metronome = null;
		String rhythm = null;
		if (rhythmType <= HearingConstants.Rhythm.OneBarThreeBeat.T3_EIGHT) {
			metronome = metronomeRhythmFilePath[1];
			rhythm = file[rhythmType - 300];
			newString[0] = metronome;
			newString[1] = rhythm;
			return newString;
		} else if (rhythmType > HearingConstants.Rhythm.OneBarThreeBeat.T3_EIGHT) {
			metronome = metronomeRhythmFilePath[2];
			rhythm = file[rhythmType - 300];
			newString[0] = metronome;
			newString[1] = rhythm;
			return newString;
		} else {
			return null;
		}
	}
	
	private String[] getString(List<Map<Integer, List<Integer>>> rhythmList) {
		String[] metronomeRhythmFilePath = context.getResources()
				.getStringArray(R.array.MetronomeRhythmFilePath);
		String metronome = metronomeRhythmFilePath[0];
		int[] rhythm = null;
		List<String> list = new ArrayList<String>();
		list.add(metronome);
		for (int i = 0; i < rhythmList.size(); i++) {
			if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.CYMBALS) {
				filePathArray = context.getResources().getStringArray(
						R.array.CymbalsRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.CYMBALS).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.CYMBALS).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.CYMBALS).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.GLOCK) {
				filePathArray = context.getResources().getStringArray(
						R.array.GlockRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.GLOCK).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.GLOCK).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.GLOCK).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.KICK) {
				filePathArray = context.getResources().getStringArray(
						R.array.KickRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.KICK).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.KICK).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.KICK).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.PIANO) {
				filePathArray = context.getResources().getStringArray(
						R.array.PianoRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.PIANO).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.PIANO).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.PIANO).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.SNARE) {
				filePathArray = context.getResources().getStringArray(
						R.array.SnareRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.SNARE).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.SNARE).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.SNARE).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.TRIANGLE) {
				filePathArray = context.getResources().getStringArray(
						R.array.TriangleRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.TRIANGLE).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.TRIANGLE).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.TRIANGLE).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.TRUMPET) {
				filePathArray = context.getResources().getStringArray(
						R.array.TrumpetRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.TRUMPET).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.TRUMPET).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.TRUMPET).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.WOODBLOCK) {
				filePathArray = context.getResources().getStringArray(
						R.array.WoodblockRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.WOODBLOCK).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.WOODBLOCK).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.WOODBLOCK).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			} else if (rhythmList.get(i).keySet().iterator().next() == MusicalInstruments.PercussionRhythm.GUO) {
				filePathArray = context.getResources().getStringArray(
						R.array.GuoRhythmFilePath);
				rhythm = new int[rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.GUO).size()];
				for (int j = 0; j < rhythmList.get(i).get(
						MusicalInstruments.PercussionRhythm.GUO).size(); j++) {
					rhythm[j] = (rhythmList.get(i).get(
							MusicalInstruments.PercussionRhythm.GUO).get(j) - 300);
				}
				for (int j = 0; j < rhythm.length; j++) {
					list.add(filePathArray[rhythm[j]]);
				}
			}else {

			}
		}
		String[] newString = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			newString[i] = list.get(i);
		}
		return newString;
	}
	
	public void stop() {
		if(mediaPlayer.isPlaying())
		{
			mediaPlayer.pause();
			mediaPlayer.stop();
		}
			mediaPlayer.release();
	}
	private void playMusicService(String path) {
		File file = new File(HearingConstants.MUSIC_DIRECTORY_PATH + path);
		try {
			if (!file.exists()) {
				InputStream stream = context.getAssets().open(path);
				file = CommonUtil.createMusicFile(HearingConstants.MUSIC_DIRECTORY_PATH + path, stream);
			}
			mediaPlayer.reset();
			mediaPlayer.setDataSource(file.getAbsolutePath());
			mediaPlayer.prepare();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
					mediaPlayer.getCurrentPosition();
					mediaPlayer.getDuration();
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					++currentMusic;
					if (musicArrayLength == 0) {
						musicArrayLength = filePathArray.length;
					} else {

					}
					if (currentMusic > 0 && currentMusic < musicArrayLength) {
						playMusicService(filePathArray[currentMusic]);
					}
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
}
