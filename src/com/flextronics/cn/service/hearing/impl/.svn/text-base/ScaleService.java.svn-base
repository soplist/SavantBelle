package com.flextronics.cn.service.hearing.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.service.hearing.IScaleService;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class ScaleService implements IScaleService {
	//声明一个Context对象
	private Context context;
	//声明一个MediaPlayer对象
	private MediaPlayer mediaPlayer = new MediaPlayer();
	//声明一个int类型变量
	private int currentMusic;
	//声明一个int类型变量
	private int musicArrayLength;
	//声明一个int类型变量
	private int startIndex;
	//声明一个int类型变量
	private int lastIndex;
	//声明一个int类型变量
	private int errorCount;
	//声明一个String类型数组
	private String[] filePathArray;
	//声明一个int类型数组
	private int [] scaleArray;
	
	public void init(Context context) {
		this.context = context;
	}

	public void stop() {
		if(mediaPlayer.isPlaying())
		{
			mediaPlayer.pause();
			mediaPlayer.stop();
		}
			mediaPlayer.release();
	}
	
	public void octaveService(int type) {
		currentMusic = 0;
		musicArrayLength = 0;
		if (type == MusicalInstruments.ForeignMusicScale.CELLO) {
			filePathArray = context.getResources().getStringArray(
					R.array.CelloScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.VIOLA) {
			filePathArray = context.getResources().getStringArray(
					R.array.ViolaScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.VIOLIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ViolinScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.FLUTE) {
			filePathArray = context.getResources().getStringArray(
					R.array.FluteScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.PICCOLO) {
			filePathArray = context.getResources().getStringArray(
					R.array.PiccoloScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ChurchOrganScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.CLARINET) {
			filePathArray = context.getResources().getStringArray(
					R.array.ClarinetScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.AccordianScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.PIANO) {
			filePathArray = context.getResources().getStringArray(
					R.array.PianoScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {
			filePathArray = context.getResources().getStringArray(
					R.array.HarpsichordScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.HARP) {
			filePathArray = context.getResources().getStringArray(
					R.array.HarpScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricPianoScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.GLOCK) {
			filePathArray = context.getResources().getStringArray(
					R.array.GlockScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {
			filePathArray = context.getResources().getStringArray(
					R.array.XylophoneScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.AcousticGuitarScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricGuitarScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {
			filePathArray = context.getResources().getStringArray(
					R.array.TubularBellsScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.TROMBONE) {
			filePathArray = context.getResources().getStringArray(
					R.array.TromboneScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.BASSOON) {
			filePathArray = context.getResources().getStringArray(
					R.array.BassoonScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricBassScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.TRUMPET) {
			filePathArray = context.getResources().getStringArray(
					R.array.TrumpetScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.SAX) {
			filePathArray = context.getResources().getStringArray(
					R.array.SaxScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {
			filePathArray = context.getResources().getStringArray(
					R.array.FrenchHornScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.TUBA) {
			filePathArray = context.getResources().getStringArray(
					R.array.TubaScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.ForeignMusicScale.OBOE) {
			filePathArray = context.getResources().getStringArray(
					R.array.OboeBassScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {
			filePathArray = context.getResources().getStringArray(
					R.array.IrishWhistleScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.BANJO) {
			filePathArray = context.getResources().getStringArray(
					R.array.BanjoScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShakuhachiScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.DI_ZI) {
			filePathArray = context.getResources().getStringArray(
					R.array.DiZiScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.ER_HU) {
			filePathArray = context.getResources().getStringArray(
					R.array.ErHuScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.GU_ZHENG) {
			filePathArray = context.getResources().getStringArray(
					R.array.GuZhengScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.LIU_QIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.LiuQinScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.MaTouQinScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.MANDOLIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.MandolinScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.PI_PA) {
			filePathArray = context.getResources().getStringArray(
					R.array.PiPaScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.RUAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.RuanScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.SHAMISEN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShamisenScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.SHENG) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShengScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
			filePathArray = context.getResources().getStringArray(
					R.array.UilleannBagpipeScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.SUO_NA) {
			filePathArray = context.getResources().getStringArray(
					R.array.SuoNaScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.SITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.SitarScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.XIAO) {
			filePathArray = context.getResources().getStringArray(
					R.array.XiaoScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.DULCIMER) {
			filePathArray = context.getResources().getStringArray(
					R.array.DulcimerScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else if (type == MusicalInstruments.FolkMusicScale.ZHONG) {
			filePathArray = context.getResources().getStringArray(
					R.array.ZhongScaleFilePath);
			playMusicAESCService(filePathArray[currentMusic]);
		} else {

		}
	}
	
	public void monosyllabicService(int instrumentType, int scaleType) {
		musicArrayLength = 0;
		if (instrumentType == MusicalInstruments.ForeignMusicScale.CELLO) {
			filePathArray = context.getResources().getStringArray(
					R.array.CelloScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.VIOLA) {
			filePathArray = context.getResources().getStringArray(
					R.array.ViolaScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.VIOLIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ViolinScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.FLUTE) {
			filePathArray = context.getResources().getStringArray(
					R.array.FluteScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.PICCOLO) {
			filePathArray = context.getResources().getStringArray(
					R.array.PiccoloScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ChurchOrganScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.CLARINET) {
			filePathArray = context.getResources().getStringArray(
					R.array.ClarinetScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.AccordianScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.PIANO) {
			filePathArray = context.getResources().getStringArray(
					R.array.PianoScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {
			filePathArray = context.getResources().getStringArray(
					R.array.HarpsichordScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.HARP) {
			filePathArray = context.getResources().getStringArray(
					R.array.HarpScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricPianoScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.GLOCK) {
			filePathArray = context.getResources().getStringArray(
					R.array.GlockScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {
			filePathArray = context.getResources().getStringArray(
					R.array.XylophoneScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.AcousticGuitarScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricGuitarScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {
			filePathArray = context.getResources().getStringArray(
					R.array.TubularBellsScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.TROMBONE) {
			filePathArray = context.getResources().getStringArray(
					R.array.TromboneScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.BASSOON) {
			filePathArray = context.getResources().getStringArray(
					R.array.BassoonScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {
			filePathArray = context.getResources().getStringArray(
					R.array.ElectricBassScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.TRUMPET) {
			filePathArray = context.getResources().getStringArray(
					R.array.TrumpetScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.SAX) {
			filePathArray = context.getResources().getStringArray(
					R.array.SaxScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {
			filePathArray = context.getResources().getStringArray(
					R.array.FrenchHornScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.TUBA) {
			filePathArray = context.getResources().getStringArray(
					R.array.TubaScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.ForeignMusicScale.OBOE) {
			filePathArray = context.getResources().getStringArray(
					R.array.OboeBassScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {
			filePathArray = context.getResources().getStringArray(
					R.array.IrishWhistleScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.BANJO) {
			filePathArray = context.getResources().getStringArray(
					R.array.BanjoScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShakuhachiScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.DI_ZI) {
			filePathArray = context.getResources().getStringArray(
					R.array.DiZiScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.ER_HU) {
			filePathArray = context.getResources().getStringArray(
					R.array.ErHuScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.GU_ZHENG) {
			filePathArray = context.getResources().getStringArray(
					R.array.GuZhengScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.LIU_QIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.LiuQinScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.MaTouQinScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.MANDOLIN) {
			filePathArray = context.getResources().getStringArray(
					R.array.MandolinScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.PI_PA) {
			filePathArray = context.getResources().getStringArray(
					R.array.PiPaScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.RUAN) {
			filePathArray = context.getResources().getStringArray(
					R.array.RuanScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.SHAMISEN) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShamisenScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.SHENG) {
			filePathArray = context.getResources().getStringArray(
					R.array.ShengScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
			filePathArray = context.getResources().getStringArray(
					R.array.UilleannBagpipeScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.SUO_NA) {
			filePathArray = context.getResources().getStringArray(
					R.array.SuoNaScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.SITAR) {
			filePathArray = context.getResources().getStringArray(
					R.array.SitarScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.XIAO) {
			filePathArray = context.getResources().getStringArray(
					R.array.XiaoScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.DULCIMER) {
			filePathArray = context.getResources().getStringArray(
					R.array.DulcimerScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else if (instrumentType == MusicalInstruments.FolkMusicScale.ZHONG) {
			filePathArray = context.getResources().getStringArray(
					R.array.ZhongScaleFilePath);
			playMusicService(filePathArray[scaleType - 200]);
		} else {

		}
	}
	
	public void continuousToneService(int musicalInstrumentsType,
			List<Integer> scaleList) {
		if (scaleList.size()<=0) {
			return;
		}
		startIndex = 0;
		lastIndex = 0;
		currentMusic = 0;
		musicArrayLength = 0;
		if (scaleList.size() == 1) {
			Log.d("scaleList",""+scaleList.get(0));
			startIndex = scaleList.get(0) - 200;
			musicArrayLength = startIndex + 1;
			if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CELLO) {
				filePathArray = context.getResources().getStringArray(R.array.CelloScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLA) {
				filePathArray = context.getResources().getStringArray(R.array.ViolaScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLIN) {
				filePathArray = context.getResources().getStringArray(R.array.ViolinScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FLUTE) {
				filePathArray = context.getResources().getStringArray(R.array.FluteScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PICCOLO) {
				filePathArray = context.getResources().getStringArray(R.array.PiccoloScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {
				filePathArray = context.getResources().getStringArray(R.array.ChurchOrganScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CLARINET) {
				filePathArray = context.getResources().getStringArray(R.array.ClarinetScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {
				filePathArray = context.getResources().getStringArray(R.array.AccordianScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PIANO) {
				filePathArray = context.getResources().getStringArray(R.array.PianoScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {
				filePathArray = context.getResources().getStringArray(R.array.HarpsichordScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARP) {
				filePathArray = context.getResources().getStringArray(R.array.HarpScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {
				filePathArray = context.getResources().getStringArray(R.array.ElectricPianoScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.GLOCK) {
				filePathArray = context.getResources().getStringArray(R.array.GlockScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {
				filePathArray = context.getResources().getStringArray(R.array.XylophoneScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {
				filePathArray = context.getResources().getStringArray(R.array.AcousticGuitarScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {
				filePathArray = context.getResources().getStringArray(R.array.ElectricGuitarScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {
				filePathArray = context.getResources().getStringArray(R.array.TubularBellsScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TROMBONE) {
				filePathArray = context.getResources().getStringArray(R.array.TromboneScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.BASSOON) {
				filePathArray = context.getResources().getStringArray(R.array.BassoonScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {
				filePathArray = context.getResources().getStringArray(R.array.ElectricBassScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TRUMPET) {
				filePathArray = context.getResources().getStringArray(R.array.TrumpetScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.SAX) {
				filePathArray = context.getResources().getStringArray(R.array.SaxScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {
				filePathArray = context.getResources().getStringArray(R.array.FrenchHornScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBA) {
				filePathArray = context.getResources().getStringArray(R.array.TubaScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.OBOE) {
				filePathArray = context.getResources().getStringArray(R.array.OboeBassScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {
				filePathArray = context.getResources().getStringArray(R.array.IrishWhistleScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.BANJO) {
				filePathArray = context.getResources().getStringArray(R.array.BanjoScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {
				filePathArray = context.getResources().getStringArray(R.array.ShakuhachiScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DI_ZI) {
				filePathArray = context.getResources().getStringArray(R.array.DiZiScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ER_HU) {
				filePathArray = context.getResources().getStringArray(R.array.ErHuScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.GU_ZHENG) {
				filePathArray = context.getResources().getStringArray(R.array.GuZhengScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.LIU_QIN) {
				filePathArray = context.getResources().getStringArray(R.array.LiuQinScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
				filePathArray = context.getResources().getStringArray(R.array.MaTouQinScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MANDOLIN) {
				filePathArray = context.getResources().getStringArray(R.array.MandolinScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.PI_PA) {
				filePathArray = context.getResources().getStringArray(R.array.PiPaScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.RUAN) {
				filePathArray = context.getResources().getStringArray(R.array.RuanScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAMISEN) {
				filePathArray = context.getResources().getStringArray(R.array.ShamisenScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHENG) {
				filePathArray = context.getResources().getStringArray(R.array.ShengScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
				filePathArray = context.getResources().getStringArray(R.array.UilleannBagpipeScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SUO_NA) {
				filePathArray = context.getResources().getStringArray(R.array.SuoNaScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SITAR) {
				filePathArray = context.getResources().getStringArray(R.array.SitarScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.XIAO) {
				filePathArray = context.getResources().getStringArray(R.array.XiaoScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DULCIMER) {
				filePathArray = context.getResources().getStringArray(R.array.DulcimerScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ZHONG) {
				filePathArray = context.getResources().getStringArray(R.array.ZhongScaleFilePath);
				playMusicAESCService(filePathArray[currentMusic]);
			} else {

			}
		} else if (scaleList.size() == 2) {
			Log.d("scaleList",""+scaleList.get(0));
			startIndex = scaleList.get(0) - 200;
			lastIndex = scaleList.get(1) - 200;
			if (startIndex < lastIndex) {
				currentMusic = startIndex;
				musicArrayLength = lastIndex + 1;
				if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CELLO) {
					filePathArray = context.getResources().getStringArray(R.array.CelloScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLA) {
					filePathArray = context.getResources().getStringArray(R.array.ViolaScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLIN) {
					filePathArray = context.getResources().getStringArray(R.array.ViolinScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FLUTE) {
					filePathArray = context.getResources().getStringArray(R.array.FluteScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PICCOLO) {
					filePathArray = context.getResources().getStringArray(
							R.array.PiccoloScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {

					filePathArray = context.getResources().getStringArray(
							R.array.ChurchOrganScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CLARINET) {

					filePathArray = context.getResources().getStringArray(
							R.array.ClarinetScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {

					filePathArray = context.getResources().getStringArray(
							R.array.AccordianScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PIANO) {

					filePathArray = context.getResources().getStringArray(
							R.array.PianoScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {

					filePathArray = context.getResources().getStringArray(
							R.array.HarpsichordScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARP) {

					filePathArray = context.getResources().getStringArray(
							R.array.HarpScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricPianoScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.GLOCK) {

					filePathArray = context.getResources().getStringArray(
							R.array.GlockScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {
					filePathArray = context.getResources().getStringArray(
							R.array.XylophoneScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {

					filePathArray = context.getResources().getStringArray(
							R.array.AcousticGuitarScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricGuitarScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {

					filePathArray = context.getResources().getStringArray(
							R.array.TubularBellsScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TROMBONE) {

					filePathArray = context.getResources().getStringArray(
							R.array.TromboneScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.BASSOON) {

					filePathArray = context.getResources().getStringArray(
							R.array.BassoonScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricBassScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TRUMPET) {

					filePathArray = context.getResources().getStringArray(
							R.array.TrumpetScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.SAX) {

					filePathArray = context.getResources().getStringArray(
							R.array.SaxScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {

					filePathArray = context.getResources().getStringArray(
							R.array.FrenchHornScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBA) {

					filePathArray = context.getResources().getStringArray(
							R.array.TubaScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.OBOE) {

					filePathArray = context.getResources().getStringArray(
							R.array.OboeBassScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {

					filePathArray = context.getResources().getStringArray(
							R.array.IrishWhistleScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.BANJO) {

					filePathArray = context.getResources().getStringArray(
							R.array.BanjoScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {

					filePathArray = context.getResources().getStringArray(
							R.array.ShakuhachiScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DI_ZI) {

					filePathArray = context.getResources().getStringArray(
							R.array.DiZiScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ER_HU) {

					filePathArray = context.getResources().getStringArray(
							R.array.ErHuScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.GU_ZHENG) {

					filePathArray = context.getResources().getStringArray(
							R.array.GuZhengScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.LIU_QIN) {

					filePathArray = context.getResources().getStringArray(
							R.array.LiuQinScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
					musicArrayLength = startIndex;
					filePathArray = context.getResources().getStringArray(
							R.array.MaTouQinScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MANDOLIN) {

					filePathArray = context.getResources().getStringArray(
							R.array.MandolinScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.PI_PA) {

					filePathArray = context.getResources().getStringArray(
							R.array.PiPaScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.RUAN) {

					filePathArray = context.getResources().getStringArray(
							R.array.RuanScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAMISEN) {

					filePathArray = context.getResources().getStringArray(
							R.array.ShamisenScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHENG) {
					filePathArray = context.getResources().getStringArray(
							R.array.ShengScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
					filePathArray = context.getResources().getStringArray(
							R.array.UilleannBagpipeScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SUO_NA) {
					filePathArray = context.getResources().getStringArray(
							R.array.SuoNaScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SITAR) {
					filePathArray = context.getResources().getStringArray(
							R.array.SitarScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.XIAO) {
					filePathArray = context.getResources().getStringArray(
							R.array.XiaoScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DULCIMER) {
					filePathArray = context.getResources().getStringArray(
							R.array.DulcimerScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ZHONG) {
					filePathArray = context.getResources().getStringArray(
							R.array.ZhongScaleFilePath);
					playMusicAESCService(filePathArray[currentMusic]);
				} else {

				}
			} else if (startIndex > lastIndex) {
				musicArrayLength = startIndex + 1;
				currentMusic = startIndex;
				if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CELLO) {
					filePathArray = context.getResources().getStringArray(
							R.array.CelloScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLA) {
					filePathArray = context.getResources().getStringArray(
							R.array.ViolaScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLIN) {
					filePathArray = context.getResources().getStringArray(
							R.array.ViolinScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FLUTE) {
					filePathArray = context.getResources().getStringArray(
							R.array.FluteScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PICCOLO) {

					filePathArray = context.getResources().getStringArray(
							R.array.PiccoloScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {

					filePathArray = context.getResources().getStringArray(
							R.array.ChurchOrganScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CLARINET) {

					filePathArray = context.getResources().getStringArray(
							R.array.ClarinetScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {
					filePathArray = context.getResources().getStringArray(
							R.array.ViolaScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PIANO) {

					filePathArray = context.getResources().getStringArray(
							R.array.PianoScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {

					filePathArray = context.getResources().getStringArray(
							R.array.HarpsichordScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARP) {

					filePathArray = context.getResources().getStringArray(
							R.array.HarpScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricPianoScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.GLOCK) {

					filePathArray = context.getResources().getStringArray(
							R.array.GlockScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {

					filePathArray = context.getResources().getStringArray(
							R.array.XylophoneScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {

					filePathArray = context.getResources().getStringArray(
							R.array.AcousticGuitarScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricGuitarScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {

					filePathArray = context.getResources().getStringArray(
							R.array.TubularBellsScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TROMBONE) {

					filePathArray = context.getResources().getStringArray(
							R.array.TromboneScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.BASSOON) {

					filePathArray = context.getResources().getStringArray(
							R.array.BassoonScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {

					filePathArray = context.getResources().getStringArray(
							R.array.ElectricBassScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TRUMPET) {

					filePathArray = context.getResources().getStringArray(
							R.array.TrumpetScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.SAX) {
					filePathArray = context.getResources().getStringArray(R.array.SaxScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {
					filePathArray = context.getResources().getStringArray(R.array.FrenchHornScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBA) {

					filePathArray = context.getResources().getStringArray(
							R.array.TubaScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.OBOE) {

					filePathArray = context.getResources().getStringArray(
							R.array.OboeBassScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {

					filePathArray = context.getResources().getStringArray(
							R.array.IrishWhistleScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.BANJO) {

					filePathArray = context.getResources().getStringArray(
							R.array.BanjoScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {

					filePathArray = context.getResources().getStringArray(
							R.array.ShakuhachiScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DI_ZI) {

					filePathArray = context.getResources().getStringArray(
							R.array.DiZiScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ER_HU) {

					filePathArray = context.getResources().getStringArray(
							R.array.ErHuScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.GU_ZHENG) {

					filePathArray = context.getResources().getStringArray(
							R.array.GuZhengScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.LIU_QIN) {

					filePathArray = context.getResources().getStringArray(
							R.array.LiuQinScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
					filePathArray = context.getResources().getStringArray(
							R.array.MaTouQinScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MANDOLIN) {

					filePathArray = context.getResources().getStringArray(
							R.array.MandolinScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.PI_PA) {

					filePathArray = context.getResources().getStringArray(
							R.array.PiPaScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.RUAN) {

					filePathArray = context.getResources().getStringArray(
							R.array.RuanScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAMISEN) {

					filePathArray = context.getResources().getStringArray(
							R.array.ShamisenScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHENG) {
					filePathArray = context.getResources().getStringArray(
							R.array.ShengScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
					filePathArray = context.getResources().getStringArray(
							R.array.UilleannBagpipeScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SUO_NA) {
					filePathArray = context.getResources().getStringArray(
							R.array.SuoNaScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SITAR) {
					filePathArray = context.getResources().getStringArray(R.array.SitarScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.XIAO) {
					filePathArray = context.getResources().getStringArray(R.array.XiaoScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DULCIMER) {
					filePathArray = context.getResources().getStringArray(R.array.DulcimerScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ZHONG) {
					filePathArray = context.getResources().getStringArray(R.array.ZhongScaleFilePath);
					playMusicDESCService(filePathArray[currentMusic]);
				} else {

				}
			}else {
				
			}
		}else {
			
		}
	}
	
	public void complexToneService(int musicalInstrumentsType,
			List<Integer> scaleList) {
		if (scaleList.size()<=0) {
			return;
		}
		currentMusic = 0;
		if (scaleList.size() > 0) {
			scaleArray = new int[scaleList.size()];
			musicArrayLength = scaleArray.length;
			for (int i = 0; i < scaleList.size(); i++) {
				scaleArray[i] = (scaleList.get(i) - 200);
			}
			if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CELLO) {
				filePathArray = context.getResources().getStringArray(R.array.CelloScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLA) {
				filePathArray = context.getResources().getStringArray(R.array.ViolaScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.VIOLIN) {
				filePathArray = context.getResources().getStringArray(R.array.ViolinScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FLUTE) {
				filePathArray = context.getResources().getStringArray(
						R.array.FluteScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PICCOLO) {

				filePathArray = context.getResources().getStringArray(
						R.array.PiccoloScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN) {

				filePathArray = context.getResources().getStringArray(
						R.array.ChurchOrganScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.CLARINET) {
				filePathArray = context.getResources().getStringArray(
						R.array.ClarinetScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACCORDIAN) {
				filePathArray = context.getResources().getStringArray(R.array.AccordianScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.PIANO) {
				filePathArray = context.getResources().getStringArray(
						R.array.PianoScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARPSICHORD) {
				filePathArray = context.getResources().getStringArray(R.array.HarpsichordScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.HARP) {
				filePathArray = context.getResources().getStringArray(R.array.HarpScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO) {
				filePathArray = context.getResources().getStringArray(R.array.ElectricPianoScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.GLOCK) {
				filePathArray = context.getResources().getStringArray(R.array.GlockScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.XYLOPHONE) {
				filePathArray = context.getResources().getStringArray(R.array.XylophoneScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR) {
				filePathArray = context.getResources().getStringArray(R.array.AcousticGuitarScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR) {
				filePathArray = context.getResources().getStringArray(R.array.ElectricGuitarScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS) {
				filePathArray = context.getResources().getStringArray(R.array.TubularBellsScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TROMBONE) {
				filePathArray = context.getResources().getStringArray(R.array.TromboneScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.BASSOON) {
				filePathArray = context.getResources().getStringArray(
						R.array.BassoonScaleFilePath);
				playMusicListService(filePathArray[currentMusic]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS) {

				filePathArray = context.getResources().getStringArray(
						R.array.ElectricBassScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TRUMPET) {

				filePathArray = context.getResources().getStringArray(
						R.array.TrumpetScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.SAX) {
				filePathArray = context.getResources().getStringArray(
						R.array.SaxScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.FRENCH_HORN) {

				filePathArray = context.getResources().getStringArray(
						R.array.FrenchHornScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.TUBA) {

				filePathArray = context.getResources().getStringArray(
						R.array.TubaScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.ForeignMusicScale.OBOE) {
				filePathArray = context.getResources().getStringArray(
						R.array.OboeBassScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.IRISH_WHISTLE) {

				filePathArray = context.getResources().getStringArray(
						R.array.IrishWhistleScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.BANJO) {

				filePathArray = context.getResources().getStringArray(
						R.array.BanjoScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAKUHACHI) {

				filePathArray = context.getResources().getStringArray(
						R.array.ShakuhachiScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DI_ZI) {

				filePathArray = context.getResources().getStringArray(
						R.array.DiZiScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ER_HU) {

				filePathArray = context.getResources().getStringArray(
						R.array.ErHuScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.GU_ZHENG) {

				filePathArray = context.getResources().getStringArray(
						R.array.GuZhengScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.LIU_QIN) {

				filePathArray = context.getResources().getStringArray(
						R.array.LiuQinScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MA_TOU_QIN) {
				filePathArray = context.getResources().getStringArray(
						R.array.MaTouQinScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.MANDOLIN) {
				filePathArray = context.getResources().getStringArray(
						R.array.MandolinScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.PI_PA) {
				filePathArray = context.getResources().getStringArray(R.array.PiPaScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.RUAN) {
				filePathArray = context.getResources().getStringArray(R.array.RuanScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHAMISEN) {
				filePathArray = context.getResources().getStringArray(R.array.ShamisenScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SHENG) {
				filePathArray = context.getResources().getStringArray(R.array.ShengScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE) {
				filePathArray = context.getResources().getStringArray(R.array.UilleannBagpipeScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SUO_NA) {
				filePathArray = context.getResources().getStringArray(R.array.SuoNaScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.SITAR) {
				filePathArray = context.getResources().getStringArray(R.array.SitarScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.XIAO) {
				filePathArray = context.getResources().getStringArray(R.array.XiaoScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.DULCIMER) {
				filePathArray = context.getResources().getStringArray(R.array.DulcimerScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else if (musicalInstrumentsType == MusicalInstruments.FolkMusicScale.ZHONG) {
				filePathArray = context.getResources().getStringArray(R.array.ZhongScaleFilePath);
				playMusicListService(filePathArray[scaleArray[currentMusic]]);
			} else {

			}
		} else {

		}

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
	
	
	
	private void playMusicAESCService(String path) {
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
						playMusicAESCService(filePathArray[currentMusic]);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			errorCount++;
			if (errorCount <= 25) {
				playMusicAESCService(path);
			} else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playMusicAESCService(path);
				}
			}
		}
	}
	
	private void playMusicDESCService(String path) {
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
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					--currentMusic;
					if (musicArrayLength == 0) {
						musicArrayLength = filePathArray.length;
					} else {

					}
					if (currentMusic >= lastIndex
							&& currentMusic < musicArrayLength) {
						playMusicDESCService(filePathArray[currentMusic]);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			errorCount++;
			if (errorCount <= 25) {
				playMusicDESCService(path);
			} else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playMusicDESCService(path);
				}
			}
		}
	}
	
	private void playMusicListService(String path) {
		File file = new File(HearingConstants.MUSIC_DIRECTORY_PATH + path);
		try {
			if (!file.exists()) {
				InputStream stream = context.getAssets().open(path);
				file = CommonUtil.createMusicFile(HearingConstants.MUSIC_DIRECTORY_PATH + path, stream);
			}
			mediaPlayer.reset();
			mediaPlayer.setDataSource(file.getAbsolutePath());
			mediaPlayer.prepare();
			mediaPlayer.start();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
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
						playMusicListService(filePathArray[scaleArray[currentMusic]]);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			errorCount++;
			if (errorCount <= 25) {
				playMusicListService(path);
			} else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playMusicListService(path);
				}
			}
		}
	}
}
