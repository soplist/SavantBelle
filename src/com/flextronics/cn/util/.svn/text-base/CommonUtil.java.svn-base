package com.flextronics.cn.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.util.Log;

public final class CommonUtil {
	
	private final static String TAG = "CommonUtil";

	
	/**生成0-某个基数之间的随机整数(最大小于基数)
	 * 
	 * @param number 基数
	 * @return 0-基数 之间的随机整数
	 */
	public static int radomFromZero(int number){
		return (int)(Math.random()*number);
	}
	
	
	/**生成1-某个基数之间的随机整数(最大取到基数)
	 * 
	 * @param number 基数
	 * @return 1-基数 之间的随机整数
	 */
	public static int radomFromOne(int number){
		return (int)(Math.random()*number)+1;
	}
	
	
	/**
	 * 取得系统样本群
	 * @return
	 */	
	public static String[] getSamples(){
		return new String[]{String.valueOf(Constants.Sample.WHITE), String.valueOf(Constants.Sample.COLORS),
			String.valueOf(Constants.Sample.NUMBERS), String.valueOf(Constants.Sample.ROME_NUMBERS),
			String.valueOf(Constants.Sample.ENGLISH_LETTERS), String.valueOf(Constants.Sample.SHAPES),
			String.valueOf(Constants.Sample.COMMON_MARKS), String.valueOf(Constants.Sample.MUSIC_MARKS),
			String.valueOf(Constants.Sample.FOREIGH_MUSIC), String.valueOf(Constants.Sample.FOLK_MUSIC),
			String.valueOf(Constants.Sample.PERCUSSION_INSTRUMENT)};
	}
	
	
	/**
	 * 取得系统白光样本
	 * @return
	 */	
	public static String[] getWhiteLight(){
		return Constants.WhiteLight.LIGHTS;
	}
	
	
	/**
	 * 取得系统颜色样本
	 * @return
	 */
	public static String[] getColors(){
		return Constants.Colors.COLORS;
	}
	
	
	/**
	 * 取得系统数字样本
	 * @return
	 */
	public static String[] getNumbers(){
		return Constants.Numbers.NUMBERS;
	}
	
	
	/**
	 * 取得系统罗马数字样本
	 * @return
	 */
	public static String[] getRomeNumbers( ){
		return Constants.RomeNumbers.NUMBERS;
	}
	
	
	/**
	 * 取得系统英文字母样本
	 * @return
	 */
	public static String[] getEnglishLetters( ){
		return Constants.EnglishLetters.LETTERS;
	}

	
	/**
	 * 取得系统图形样本
	 * @return
	 */
	public static String[] getShapes( ){
		return Constants.Shapes.SHAPES;
	}

	
	/**
	 * 取得系统一般记号样本
	 * @return
	 */
	public static String[] getCommonMarks( ){
		return Constants.CommonMarks.MARKS;
	}

	
	/**
	 * 取得系统音乐符号样本
	 * @return
	 */
	public static String[] getMusicMarks( ){
		return Constants.MusicMarks.MARKS;
	}

	
	/**
	 * 取得系统西洋乐样本
	 * @return
	 */
	public static String[] getForeignMusic( ){
		return Constants.ForeignMusic.INSTRUMENTS;
	}

	
	/**
	 * 取得系统民族乐样本
	 * @return
	 */	
	public static String[] getFolkMusic( ){
		return Constants.FolkMusic.INSTRUMENTS;
	}

	
	/**
	 * 取得系统打击乐样本
	 * @return
	 */	
	public static String[] getPercussionInstrument( ){
		return Constants.PercussionInstrument.INSTRUMENTS;
	}
	
	
	/**根据样本群的编码，取得该样本群中的所有元素的编码
	 * 
	 * @param sampleCode	样本群编码
	 * @return	该样本群中所有元素的编码
	 */
	public static String[] getSampleElementsInSample(int sampleCode){
		
		String[] elements;

		switch (sampleCode) {
		case Constants.Sample.WHITE:
			elements = getWhiteLight();
			break;
		case Constants.Sample.COLORS:
			elements = getColors();
			break;
		case Constants.Sample.NUMBERS:
			elements = getNumbers();
			break;
		case Constants.Sample.ROME_NUMBERS:
			elements = getRomeNumbers();
			break;
		case Constants.Sample.ENGLISH_LETTERS:
			elements = getEnglishLetters();
			break;
		case Constants.Sample.SHAPES:
			elements = getShapes();
			break;
		case Constants.Sample.COMMON_MARKS:
			elements = getCommonMarks();
			break;
		case Constants.Sample.MUSIC_MARKS:
			elements = getMusicMarks();
			break;
		case Constants.Sample.FOREIGH_MUSIC:
			elements = getForeignMusic();
			break;
		case Constants.Sample.FOLK_MUSIC:
			elements = getFolkMusic();
			break;
		case Constants.Sample.PERCUSSION_INSTRUMENT:
			elements = getPercussionInstrument();
			break;
		default:
			elements = null;
			break;
		}
		return elements;
	}
	
	
	/**根据乐器编号和音阶确定音乐文件的路径
	 * 
	 * @param instrument 乐器(各种乐器的编码)
	 * @param scale 音阶
	 * @return
	 */
	public static String getMusicFilePathByInstrumentScale(Context context, int instrument, int scale){
		
		String[] musicFilePaths = null;
		String musicFilePath = null;
		switch (instrument) {		//根据乐器区别出各乐器的音阶样本
		case Constants.ForeignMusic.PIANO:		//钢琴
			musicFilePaths = context.getResources().getStringArray(R.array.PianoScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];
			break;
		case Constants.ForeignMusic.TRUMENT:		//小号
			musicFilePaths = context.getResources().getStringArray(R.array.TrumpetScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.VIOLIN:		//小提琴
			musicFilePaths = context.getResources().getStringArray(R.array.ViolinScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.SHU_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.HarpScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.CHANG_HAO:		
			musicFilePaths = context.getResources().getStringArray(R.array.TromboneScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.ZHONG_TI_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.ViolaScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.JIAO_TANG_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.ChurchOrganScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.FA_GUO_HAO:	
			musicFilePaths = context.getResources().getStringArray(R.array.FrenchHornScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.DA_TI_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.CelloScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.MU_JI_TA:	
			musicFilePaths = context.getResources().getStringArray(R.array.AcousticGuitarScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.CHANG_DI:		
			musicFilePaths = context.getResources().getStringArray(R.array.FluteScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.SHOU_FENG_QIN:		
			musicFilePaths = context.getResources().getStringArray(R.array.AccordianScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.DIAN_JI_TA:	
			musicFilePaths = context.getResources().getStringArray(R.array.ElectricGuitarScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.SA_KE_SI:		
			musicFilePaths = context.getResources().getStringArray(R.array.SaxScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.ForeignMusic.TIE_QIN:		
			musicFilePaths = context.getResources().getStringArray(R.array.GlockScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;

		
		case Constants.FolkMusic.PI_PA:	
			musicFilePaths = context.getResources().getStringArray(R.array.PiPaScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.ER_HU:	
			musicFilePaths = context.getResources().getStringArray(R.array.ErHuScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.SUO_NA:	
			musicFilePaths = context.getResources().getStringArray(R.array.SuoNaScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.GU_ZHENG:	
			musicFilePaths = context.getResources().getStringArray(R.array.GuZhengScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.MA_TOU_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.MaTouQinScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.ER_AI_LAN_FENG_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.IrishWhistleScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.YANG_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.DulcimerScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.XI_TA_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.SitarScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.CHI_BA:	
			musicFilePaths = context.getResources().getStringArray(R.array.ShakuhachiScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.LIU_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.LiuQinScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.BAN_JIU_QIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.BanjoScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.DI:	
			musicFilePaths = context.getResources().getStringArray(R.array.DiZiScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.RUAN:	
			musicFilePaths = context.getResources().getStringArray(R.array.RuanScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.SAN_WEI_XIAN:	
			musicFilePaths = context.getResources().getStringArray(R.array.ShamisenScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.FolkMusic.SHENG:	
			musicFilePaths = context.getResources().getStringArray(R.array.ShengScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		
			
		case Constants.PercussionInstrument.DRUM:	
			musicFilePaths = context.getResources().getStringArray(R.array.KickScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.TEMPLE_BLOCK:	
			musicFilePaths = context.getResources().getStringArray(R.array.WoodblockScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.TRIANGLE:	
			musicFilePaths = context.getResources().getStringArray(R.array.TriangleScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.XIAO_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.SnareScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.LIN_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.TambourineScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.LUO:	
			musicFilePaths = context.getResources().getStringArray(R.array.XiaoLuoScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.TAI_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.TaikoDrumsScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.BANG_GE_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.BongoScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.BO:	
			musicFilePaths = context.getResources().getStringArray(R.array.CymbalsScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.DING_YIN_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.TimpaniScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.YAN_BA_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.TimbalesScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.SHA_LIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.CabasaScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.ZHONG_GUO_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.GuoScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.KANG_JIA_GU:	
			musicFilePaths = context.getResources().getStringArray(R.array.CongaScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
		case Constants.PercussionInstrument.NIU_LIN:	
			musicFilePaths = context.getResources().getStringArray(R.array.CowBellScaleFilePath);
			musicFilePath = musicFilePaths[scale-1];			
			break;
			
		default:
			break;
		}
		
		return musicFilePath;
	}
	
	public static String getScale(Context context, int musicInstrument, int scale) {
		String[] musicFilePaths = null;
		String musicFilePath = null;
		switch (musicInstrument) {
		case HearingConstants.MusicalInstruments.ForeignMusicScale.ACCORDIAN:
			musicFilePaths = context.getResources().getStringArray(R.array.AccordianScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.ACOUSTIC_GUITAR:
			musicFilePaths = context.getResources().getStringArray(R.array.AcousticGuitarScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.BASSOON:
			musicFilePaths = context.getResources().getStringArray(R.array.BassoonScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.CELLO:
			musicFilePaths = context.getResources().getStringArray(R.array.CelloScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.CHURCH_ORGAN:
			musicFilePaths = context.getResources().getStringArray(R.array.ChurchOrganScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.CLARINET:
			musicFilePaths = context.getResources().getStringArray(R.array.ClarinetScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.ELECTRIC_BASS:
			musicFilePaths = context.getResources().getStringArray(R.array.ElectricBassScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.ELECTRIC_GUITAR:
			musicFilePaths = context.getResources().getStringArray(R.array.ElectricGuitarScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.ELECTRIC_PIANO:
			musicFilePaths = context.getResources().getStringArray(R.array.ElectricPianoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.FLUTE:
			musicFilePaths = context.getResources().getStringArray(R.array.FluteScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.FRENCH_HORN:
			musicFilePaths = context.getResources().getStringArray(R.array.FrenchHornScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.GLOCK:
			musicFilePaths = context.getResources().getStringArray(R.array.GlockScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.HARP:
			musicFilePaths = context.getResources().getStringArray(R.array.HarpScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.HARPSICHORD:
			musicFilePaths = context.getResources().getStringArray(R.array.HarpsichordScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.OBOE:
			musicFilePaths = context.getResources().getStringArray(R.array.OboeBassScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;

		case HearingConstants.MusicalInstruments.ForeignMusicScale.PIANO:
			musicFilePaths = context.getResources().getStringArray(R.array.PianoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.PICCOLO:
			musicFilePaths = context.getResources().getStringArray(R.array.PiccoloScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.SAX:
			musicFilePaths = context.getResources().getStringArray(R.array.SaxScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.TROMBONE:
			musicFilePaths = context.getResources().getStringArray(R.array.TromboneScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.TRUMPET:
			musicFilePaths = context.getResources().getStringArray(R.array.TrumpetScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.TUBA:
			musicFilePaths = context.getResources().getStringArray(R.array.TubaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.TUBULAR_BELLS:
			musicFilePaths = context.getResources().getStringArray(R.array.TubularBellsScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.VIOLA:
			musicFilePaths = context.getResources().getStringArray(R.array.ViolaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.VIOLIN:
			musicFilePaths = context.getResources().getStringArray(R.array.ViolinScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.ForeignMusicScale.XYLOPHONE:
			musicFilePaths = context.getResources().getStringArray(R.array.XylophoneScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.BANJO:
			musicFilePaths = context.getResources().getStringArray(R.array.BanjoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.DI_ZI:
			musicFilePaths = context.getResources().getStringArray(R.array.DiZiScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.DULCIMER:
			musicFilePaths = context.getResources().getStringArray(R.array.DulcimerScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.ER_HU:
			musicFilePaths = context.getResources().getStringArray(R.array.ErHuScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.GU_ZHENG:
			musicFilePaths = context.getResources().getStringArray(R.array.GuZhengScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.IRISH_WHISTLE:
			musicFilePaths = context.getResources().getStringArray(R.array.IrishWhistleScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.LIU_QIN:
			musicFilePaths = context.getResources().getStringArray(R.array.LiuQinScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.MA_TOU_QIN:
			musicFilePaths = context.getResources().getStringArray(R.array.MaTouQinScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.MANDOLIN:
			musicFilePaths = context.getResources().getStringArray(R.array.MandolinScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.PI_PA:
			musicFilePaths = context.getResources().getStringArray(R.array.PiPaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.RUAN:
			musicFilePaths = context.getResources().getStringArray(R.array.RuanScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.SHAKUHACHI:
			musicFilePaths = context.getResources().getStringArray(R.array.ShakuhachiScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.SHAMISEN:
			musicFilePaths = context.getResources().getStringArray(R.array.ShamisenScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.SHENG:
			musicFilePaths = context.getResources().getStringArray(R.array.ShengScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.SITAR:
			musicFilePaths = context.getResources().getStringArray(R.array.SitarScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.SUO_NA:
			musicFilePaths = context.getResources().getStringArray(R.array.SuoNaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.UILLEANN_BAGPIPE:
			musicFilePaths = context.getResources().getStringArray(R.array.UilleannBagpipeScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.XIAO:
			musicFilePaths = context.getResources().getStringArray(R.array.XiaoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.FolkMusicScale.ZHONG:
			musicFilePaths = context.getResources().getStringArray(R.array.ZhongScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.BAN:
			musicFilePaths = context.getResources().getStringArray(R.array.BanScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.BONGO:
			musicFilePaths = context.getResources().getStringArray(R.array.BongoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.CABASA:
			musicFilePaths = context.getResources().getStringArray(R.array.CabasaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.CONGA:
			musicFilePaths = context.getResources().getStringArray(R.array.CongaScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.COW_BELL:
			musicFilePaths = context.getResources().getStringArray(R.array.CowBellScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.CYMBALS:
			musicFilePaths = context.getResources().getStringArray(R.array.CymbalsScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.DA_LUO:
			musicFilePaths = context.getResources().getStringArray(R.array.DaLuoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.GUO:
			musicFilePaths = context.getResources().getStringArray(R.array.GuoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.KICK:
			musicFilePaths = context.getResources().getStringArray(R.array.KickScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.SNARE:
			musicFilePaths = context.getResources().getStringArray(R.array.SnareScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.TAIKO_DRUMS:
			musicFilePaths = context.getResources().getStringArray(R.array.TaikoDrumsScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.TAMBOURINE:
			musicFilePaths = context.getResources().getStringArray(R.array.TambourineScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.TIMBALES:
			musicFilePaths = context.getResources().getStringArray(R.array.TimbalesScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.TIMPANI:
			musicFilePaths = context.getResources().getStringArray(R.array.TimpaniScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.TRIANGLE:
			musicFilePaths = context.getResources().getStringArray(R.array.TriangleScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.WOODBLOCK:
			musicFilePaths = context.getResources().getStringArray(R.array.WoodblockScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		case HearingConstants.MusicalInstruments.PercussionScale.XIAO_LUO:
			musicFilePaths = context.getResources().getStringArray(R.array.XiaoLuoScaleFilePath);
			musicFilePath = musicFilePaths[scale - 200];
			break;
		default:
			break;
		}

		return musicFilePath;
	}
	
	public static String getPercussionRhythm(Context context,int musicInstrument, int scale){
		String[] musicFilePaths = null;
		String musicFilePath = null;
		switch (musicInstrument) {
		case HearingConstants.MusicalInstruments.PercussionRhythm.CYMBALS:
			musicFilePaths = context.getResources().getStringArray(R.array.CymbalsRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.GLOCK:
			musicFilePaths = context.getResources().getStringArray(R.array.GlockRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.GUO:
			musicFilePaths = context.getResources().getStringArray(R.array.GuoRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.KICK:
			musicFilePaths = context.getResources().getStringArray(R.array.KickRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.PIANO:
			musicFilePaths = context.getResources().getStringArray(R.array.PianoRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.SNARE:
			musicFilePaths = context.getResources().getStringArray(R.array.SnareRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.TRIANGLE:
			musicFilePaths = context.getResources().getStringArray(R.array.TriangleRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.TRUMPET:
			musicFilePaths = context.getResources().getStringArray(R.array.TrumpetRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		case HearingConstants.MusicalInstruments.PercussionRhythm.WOODBLOCK:
			musicFilePaths = context.getResources().getStringArray(R.array.WoodblockRhythmFilePath);
			musicFilePath = musicFilePaths[scale - 300];
			break;
		default:
			break;
		}
		return musicFilePath;
	}
	
	
	/**根据左右手的各手指取得键盘的key值
	 * 
	 * @param hand	左右手
	 * @param key	手指头
	 * @return
	 */
	public static int getKeyCode(int hand, int key){
		
		int keyCode = 0;
		
		if (hand == Constants.LEFT_HAND) {//如果是左手
			switch (key) {
			case Constants.FORE_FINGER://食指
				keyCode = Constants.KeyStoke.FORE_FINGER_LEFT;
				break;
			case Constants.MIDDLE_FINGER://中指
				keyCode = Constants.KeyStoke.MIDDLE_FINGER_LEFT;
				break;
			case Constants.THIRD_FINGER://无名指
				keyCode = Constants.KeyStoke.THIRD_FINGER_LEFT;
				break;

			default:
				break;
			}
		}else if (hand == Constants.RIGHT_HAND) {//如果是右手
			switch (key) {
			case Constants.FORE_FINGER://食指
				keyCode = Constants.KeyStoke.FORE_FINGER_RIGHT;
				break;
			case Constants.MIDDLE_FINGER://中指
				keyCode = Constants.KeyStoke.MIDDLE_FINGER_RIGHT;
				break;
			case Constants.THIRD_FINGER://无名指
				keyCode = Constants.KeyStoke.THIRD_FINGER_RIGHT;
				break;

			default:
				break;
			}
			
		}
		
		return keyCode;
	}
	
	
	/**
	 * 根据样本元素编码获取其所在样本群的编码
	 * @param code
	 * @return
	 */
	public static int getSampleCodeBySampleElementCode(String code){
		try {
			int _code = Integer.valueOf(code);
			return getSampleCodeBySampleElementCode(_code);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	/**
	 * 根据样本元素编码获取其所在样本群的编码
	 * @param code
	 * @return
	 */
	public static int getSampleCodeBySampleElementCode(int code){
		
		int sampleId;
		
		if (code>=Constants.Colors.RED && code<=Constants.Colors.COFFEE) {
			
			sampleId = Constants.Sample.COLORS;
		}else if (code>=Constants.Numbers.ZERO && code<=Constants.Numbers.NINE) {
			
			sampleId = Constants.Sample.NUMBERS;
		}else if (code>=Constants.RomeNumbers.ROMR_ONE && code<=Constants.RomeNumbers.ROMR_TEN) {
			
			sampleId = Constants.Sample.ROME_NUMBERS;
		}else if (code>=Constants.EnglishLetters.A && code<=Constants.EnglishLetters.Z) {
			
			sampleId = Constants.Sample.ENGLISH_LETTERS;
		}else if (code>=Constants.Shapes.CIRCLE && code<=Constants.Shapes.PENTAGON) {
			
			sampleId = Constants.Sample.SHAPES;
		}else if (code>=Constants.CommonMarks.COMMON_MARK_01 && code<=Constants.CommonMarks.COMMON_MARK_20) {
			
			sampleId = Constants.Sample.COMMON_MARKS;
		}else if (code>=Constants.MusicMarks.MUSIC_MARK_01 && code<=Constants.MusicMarks.MUSIC_MARK_20) {
			
			sampleId = Constants.Sample.MUSIC_MARKS;
		}else if (code>=Constants.ForeignMusic.PIANO && code<=Constants.ForeignMusic.TIE_QIN) {
			
			sampleId = Constants.Sample.FOREIGH_MUSIC;
		}else if (code>=Constants.FolkMusic.PI_PA && code<=Constants.FolkMusic.SHENG) {
			
			sampleId = Constants.Sample.FOLK_MUSIC;
		}else if (code>=Constants.PercussionInstrument.DRUM && code<=Constants.PercussionInstrument.NIU_LIN) {
			
			sampleId = Constants.Sample.PERCUSSION_INSTRUMENT;
		}else if (code == Constants.WhiteLight.WHITELIGHT) {
			
			sampleId = Constants.Sample.WHITE;
		}else {
			return -1;
		}
		
		return sampleId;
	}
	
	/**
	 * 在制定路径下创建音频文件
	 * @param stream
	 * @return
	 */
	public static String createMediaPlayerDataSourcePath(InputStream stream) {
		String tempPath = null;
		try {
			File tmpDirectory = new File("/data/data/com.flextronics.cn.activity/tmp_music/");
			Runtime runtime = Runtime.getRuntime();
			if (!tmpDirectory.exists()) {
				tmpDirectory.mkdirs();				
				runtime.exec("chmod 777 " + tmpDirectory.getAbsolutePath());
			}
			
			File temp = File.createTempFile("mediaplayertmp", ".mp3", tmpDirectory);
			runtime.exec("chmod 777 " + temp.getAbsolutePath());
			tempPath = temp.getAbsolutePath();
			
			Log.d(TAG, "createMediaPlayerDataSourcePath -- tempPath： " + tempPath);
			
			FileOutputStream out = new FileOutputStream(temp);
			//用BufferdOutputStream速度快
			BufferedOutputStream bis = new BufferedOutputStream(out);
			byte buf[] = new byte[128];
			do {
				int numread = stream.read(buf);
				if (numread <= 0)
					break;
				bis.write(buf, 0, numread);
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tempPath;
	}
	
	/**
	 * 在制定路径下创建音频文件
	 * @param stream
	 * @return
	 */
	public static File createMusicFile(String filePath, InputStream stream) {
		
		Runtime runtime = Runtime.getRuntime();
		File file = new File(filePath);
		
		try {
			if (file.exists()) {
				file.delete();
			}
			
			File file2 = file.getParentFile();
			if (!file2.exists()) {
				file2.mkdirs();
			}
			
			Log.d(TAG, "file2.getAbsolutePath():" + file2.getAbsolutePath());
			while (!(file2.getAbsolutePath()+"/").equals(Constants.MUSIC_DIRECTORY_PATH)) {
				runtime.exec("chmod 777 " + file2.getAbsolutePath());
				file2 = file2.getParentFile();
			}
			
			file.createNewFile();
			runtime.exec("chmod 777 " + file.getAbsolutePath());
			
			Log.d(TAG, "createMusicFile： " + file.getAbsolutePath());
			
			FileOutputStream out = new FileOutputStream(file);
			int bufferSize = 1024;
			//用BufferdOutputStream速度快
			BufferedOutputStream bis = new BufferedOutputStream(out, bufferSize);
			byte buf[] = new byte[bufferSize];
			while (stream.read(buf, 0, bufferSize) != -1) {
				bis.write(buf, 0, bufferSize);
			}
			bis.flush();
			bis.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	public static boolean deleteFile(String path) {
		
		Log.d(TAG, "deleteFile -- path： " + path);
		
		if (path == null) {
			return false;
		}
		
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}else {
			return false;
		}
	}
	
		
	/**图样所支持的最大位元数
	 * 
	 * @param tuYangCode
	 * @return
	 */
	public static int getCanSupportedMaxBitByTuYang(int tuYangCode){
		int bit = 0;
		
		switch (tuYangCode) {
		case Constants.DisplayBody.TuYang.LINE:
			bit = 24;
			break;
		case Constants.DisplayBody.TuYang.CURVE:
			bit = 24;
			break;
		case Constants.DisplayBody.TuYang.CIRCLE:
			bit = 16;
			break;
		case Constants.DisplayBody.TuYang.TRIGON:
			bit = 28;
			break;
		case Constants.DisplayBody.TuYang.SQUARE:
			bit = 30;
			break;
		case Constants.DisplayBody.TuYang.HEXAGON:
			bit = 32;
			break;
		case Constants.DisplayBody.TuYang.RECTANGLE:
			bit = 30;
			break;
		case Constants.DisplayBody.TuYang.SECTOR:
			bit = 30;
			break;
		case Constants.DisplayBody.TuYang.PARALLELOGRAM:
			bit = 30;
			break;
		case Constants.DisplayBody.TuYang.DIAMOND:
			bit = 32;
			break;
		case Constants.DisplayBody.TuYang.PENTAGON:
			bit = 30;
			break;

		default:
			break;
		}
		
		return bit;
	}
	
	
	/**根据题目的位元数确定同步显示同步消失的显示时间
	 * 
	 * @param bit 题目的位元数，不小于3(正常情况下应该是3-36之间的值)
	 * @return
	 */
	public static long getDisplayTimeByBit(int bit){
		if (bit < Constants.MIN_BIT) {
			return 0;
		}
		
		long time = 1500;
		time += 500*(bit - Constants.MIN_BIT);
		
		return time;
	}

	
	/**
	 * 根据样本元素编码获取对应的图片资源在R中的值
	 * @param elementCode
	 * @return
	 */
	public static int getImageResBySampleElement(String elementCode){
		try {
			int _code = Integer.valueOf(elementCode);			
			return getImageResBySampleElement(_code);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	/**
	 * 根据样本元素编码获取其对应的图片资源在R中的值
	 * @param elementCode
	 * @return
	 */
	public static int getImageResBySampleElement(int elementCode){
				
		int[] imageResources;
		String[] sampleElements;
		
		if (elementCode>=Constants.Colors.RED && elementCode<=Constants.Colors.COFFEE) {
			
			imageResources = Constants.Colors.IMAGE_RESOURCES;
			sampleElements = Constants.Colors.COLORS;
		}else if (elementCode>=Constants.Numbers.ZERO && elementCode<=Constants.Numbers.NINE) {
			
			imageResources = Constants.Numbers.IMAGE_RESOURCES;
			sampleElements = Constants.Numbers.NUMBERS;
		}else if (elementCode>=Constants.RomeNumbers.ROMR_ONE && elementCode<=Constants.RomeNumbers.ROMR_TEN) {
			
			imageResources = Constants.RomeNumbers.IMAGE_RESOURCES;
			sampleElements = Constants.RomeNumbers.NUMBERS;
		}else if (elementCode>=Constants.EnglishLetters.A && elementCode<=Constants.EnglishLetters.Z) {
			
			imageResources = Constants.EnglishLetters.IMAGE_RESOURCES;
			sampleElements = Constants.EnglishLetters.LETTERS;
		}else if (elementCode>=Constants.Shapes.CIRCLE && elementCode<=Constants.Shapes.PENTAGON) {
			
			imageResources = Constants.Shapes.IMAGE_RESOURCES;
			sampleElements = Constants.Shapes.SHAPES;
		}else if (elementCode>=Constants.CommonMarks.COMMON_MARK_01 && elementCode<=Constants.CommonMarks.COMMON_MARK_20) {
			
			imageResources = Constants.CommonMarks.IMAGE_RESOURCES;
			sampleElements = Constants.CommonMarks.MARKS;
		}else if (elementCode>=Constants.MusicMarks.MUSIC_MARK_01 && elementCode<=Constants.MusicMarks.MUSIC_MARK_20) {
			
			imageResources = Constants.MusicMarks.IMAGE_RESOURCES;
			sampleElements = Constants.MusicMarks.MARKS;
		}else if (elementCode>=Constants.ForeignMusic.PIANO && elementCode<=Constants.ForeignMusic.TIE_QIN) {
			
			imageResources = Constants.ForeignMusic.IMAGE_RESOURCES;
			sampleElements = Constants.ForeignMusic.INSTRUMENTS;
		}else if (elementCode>=Constants.FolkMusic.PI_PA && elementCode<=Constants.FolkMusic.SHENG) {
			
			imageResources = Constants.FolkMusic.IMAGE_RESOURCES;
			sampleElements = Constants.FolkMusic.INSTRUMENTS;
		}else if (elementCode>=Constants.PercussionInstrument.DRUM && elementCode<=Constants.PercussionInstrument.NIU_LIN) {
			
			imageResources = Constants.PercussionInstrument.IMAGE_RESOURCES;
			sampleElements = Constants.PercussionInstrument.INSTRUMENTS;
		}else if (elementCode == Constants.WhiteLight.WHITELIGHT) {
			
			imageResources = Constants.WhiteLight.IMAGE_RESOURCES;
			sampleElements = Constants.WhiteLight.LIGHTS;
		}else {
			return -1;
		}
		
		String codeStr = String.valueOf(elementCode);
		int index = ArrayOperations.indexInElement(sampleElements, codeStr);
		if (index < 0) {
			return -1;
		}else {
			return imageResources[index];
		}		
	}
	
	
	/**根据样本元素编码取得对应的按键图片资源编号
	 * 
	 * @param elementCode	样本元素编码
	 * @return
	 */
	public static int getBtnImageResBySampleElement(String elementCode){
		try {
			int _elementCode = Integer.valueOf(elementCode);
			return getBtnImageResBySampleElement(_elementCode);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	/**根据样本元素编码取得对应的按键图片资源编号
	 * 
	 * @param elementCode	样本元素编码
	 * @return
	 */
	public static int getBtnImageResBySampleElement(int elementCode){
		
		Log.d(TAG, "getBtnImageRes4SampleElement(" + elementCode + ")");
		
		//该样本元素对应的样本群编号
		int sampleCode = (elementCode/100) * 100;
		Log.d(TAG, "sampleCode: " + sampleCode);
		int resId = -1;
		switch (sampleCode) {
			case Constants.Sample.WHITE:
				resId = Constants.WhiteLight.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.WHITE - 1];
				break;
			case Constants.Sample.COLORS:
				resId = Constants.Colors.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.COLORS - 1];
				break;
			case Constants.Sample.NUMBERS:
				resId = Constants.Numbers.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.NUMBERS - 1];
				break;
			case Constants.Sample.ROME_NUMBERS:
				resId = Constants.RomeNumbers.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.ROME_NUMBERS - 1];
				break;
			case Constants.Sample.ENGLISH_LETTERS:
				resId = Constants.EnglishLetters.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.ENGLISH_LETTERS - 1];
				break;
			case Constants.Sample.SHAPES:
				resId = Constants.Shapes.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.SHAPES - 1];
				break;
			case Constants.Sample.COMMON_MARKS:
				resId = Constants.CommonMarks.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.COMMON_MARKS - 1];
				break;
			case Constants.Sample.MUSIC_MARKS:
				//resId = Constants.MusicMarks.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.MUSIC_MARKS - 1];
				break;
			case Constants.Sample.FOREIGH_MUSIC:
				//resId = Constants.ForeignMusic.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.FOREIGH_MUSIC - 1];
				break;
			case Constants.Sample.FOLK_MUSIC:
				//resId = Constants.FolkMusic.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.FOLK_MUSIC - 1];
				break;
			case Constants.Sample.PERCUSSION_INSTRUMENT:
				//resId = Constants.PercussionInstrument.BTN_IMAGE_RESOURCES[elementCode - Constants.Sample.PERCUSSION_INSTRUMENT - 1];
				break;
		}	
		Log.d(TAG, "resId: " + resId);
		return resId;
	}
	
	
	/**取得样本群中所有元素图片资源
	 * 
	 * @param sampleCode 样本群编码
	 * @return
	 */
	public static int[] getSampleElementImageResBySampleCode(String sampleCode) {
		try {
			int _sampleCode = Integer.valueOf(sampleCode);
			return getSampleElementImageResBySampleCode(_sampleCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**取得样本群中所有元素图片资源
	 * 
	 * @param sampleCode 样本群编码
	 * @return
	 */
	public static int[] getSampleElementImageResBySampleCode(int sampleCode) {
		int[] images = null;

		if (sampleCode == Constants.Sample.COLORS) {

			images = Constants.Colors.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.NUMBERS) {

			images = Constants.Numbers.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.ROME_NUMBERS) {

			images = Constants.RomeNumbers.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.ENGLISH_LETTERS) {

			images = Constants.EnglishLetters.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.SHAPES) {

			images = Constants.Shapes.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.COMMON_MARKS) {

			images = Constants.CommonMarks.IMAGE_RESOURCES;
		} else if (sampleCode == Constants.Sample.MUSIC_MARKS) {
			images = Constants.MusicMarks.IMAGE_RESOURCES;
		} else {
			Log.e(TAG, "sample code wrong!code=" + sampleCode);
		}/**
		
		需要添加其他乐器的图片
		**/

		return images;
	}
		
	
	/**取得样本群中所有元素按钮图片资源(未点击时)
	 * 
	 * @param sampleCode 样本群编码
	 * @return
	 */
	public static int[] getSampleElementBtnImageResBySampleCode(String sampleCode){
		try {
			int _sampleCode = Integer.valueOf(sampleCode);
			return getSampleElementBtnImageResBySampleCode(_sampleCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	/**取得样本群中所有元素按钮图片资源(未点击时)
	 * 
	 * @param sampleCode 样本群编码
	 * @return
	 */
	public static int[] getSampleElementBtnImageResBySampleCode(int sampleCode){
		
		int[] btnImages = null;

		if (sampleCode == Constants.Sample.COLORS) {
			
			btnImages = Constants.Colors.BTN_IMAGE_RESOURCES;
		}else if (sampleCode == Constants.Sample.NUMBERS) {
			
			btnImages = Constants.Numbers.BTN_IMAGE_RESOURCES;
		}else if (sampleCode == Constants.Sample.ROME_NUMBERS) {
			
			btnImages = Constants.RomeNumbers.BTN_IMAGE_RESOURCES;
		}else if (sampleCode == Constants.Sample.ENGLISH_LETTERS) {
			
			btnImages = Constants.EnglishLetters.BTN_IMAGE_RESOURCES;
		}else if (sampleCode == Constants.Sample.SHAPES) {
			
			btnImages = Constants.Shapes.BTN_IMAGE_RESOURCES;
		}else if (sampleCode == Constants.Sample.COMMON_MARKS) {
			
			btnImages = Constants.CommonMarks.BTN_IMAGE_RESOURCES;
		}else {

			btnImages = Constants.RomeNumbers.BTN_IMAGE_RESOURCES;	
		}/**
		
		需要添加其他乐器的图片
		**/
		
		return btnImages;
	}
	
}
