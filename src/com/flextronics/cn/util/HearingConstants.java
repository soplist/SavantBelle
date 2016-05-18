package com.flextronics.cn.util;


public class HearingConstants {
	/**
	 * 音乐文件读取路劲
	 */
	public final static String MUSIC_DIRECTORY_PATH = "/data/data/com.flextronics.cn.activity/";	
	/**
	 * 显示文字的大小--小
	 */
	public static final float TEXT_SIZE_SMALL = 18f;
	/**
	 * 显示文字的大小--中
	 */
	public static final float TEXT_SIZE_MEDIUM = 20f;
	/**
	 * 显示文字的大小--大
	 */
	public static final float TEXT_SIZE_BIG = 24f;
	/**
	 * 默认随机值--1
	 */
	public static final int DEFAULT_ELEMENT_COUNT_LOW=1;
	/**
	 * 默认随机值--3
	 */
	public static final int DEFAULT_ELEMENT_COUNT_MEDIUM=3;
	/**
	 * 默认随机值--4
	 */
	public static final int DEFAULT_ELEMENT_COUNT_HIGH=4;
	/**
	 * 题目数量--15
	 */
	public static final int ANSWER_COUNT = 15;
	
	public static final int DEFAULT_DISPLAY_ANSWER=6;
	
	public static final String PREVIOUS_ACTIVITY="PREVIOUS_ACTIVITY";
	
	public static final String CONTEXT="CONTEXT";
	
	public static final String RESPONSE_PARAMETERS="RESPONSE_PARAMETERS";
	
	public static final String RESPONSE_RULE="RESPONSE_RULE";
	
	public static final int RESPONSE_DAFAULT_WAIT_TIME=2000; 
	
	public static final long RESPONSE_ANSWER_TIME=5000; 
	
	public static final int RESPONSE_DAFAULT_QUESTION_TOTAL=15; 
	
	public static final int RESPONSE_SCORE=2;
	
	public static final int MEMORY_DAFAULT_TIME=2000; 
	
	public static final int MEMORY_ANSWER_TIME=5000;
	
	public static final int MEMORY_SCORE=2;
	
	public static final String MEMORY_PARAMETERS="MEMORY_PARAMETERS";
	
	public static final String MEMORY_RULE="MEMORY_RULE";
	
	public final static String TEST_REPORT="TEST_REPORT";
	
	public final static String CLASS_NAME="CLASS_NAME";
	
	
	/**
	 * 训练类型
	 * IDENTIFICATION_TRAINING 辨识训练 
	 * RESPONSE_TRAINING 反应训练 
	 * MEMORY_TRAINING 记忆训练
	 * @author daicun
	 */
	public static final class Training {
		public static final String IDENTIFICATION_TRAINING = "IDENTIFICATION_TRAINING";
		public static final String RESPONSE_TRAINING = "RESPONSE_TRAINING";
		public static final String MEMORY_TRAINING = "MEMORY_TRAINING";
	}

	/**
	 * 样本
	 * MUSICAL_INSTRUMENTS 乐器 
	 * SCALE 音阶 
	 * RHYTHM 节奏
	 * @author daicun
	 */
	public static final class Smaple {
		public static final String MUSICAL_INSTRUMENTS = "MUSICAL_INSTRUMENTS";
		public static final String SCALE = "SCALE";
		public static final String RHYTHM = "RHYTHM";
	}
	
	

	/**
	 * 元素 
	 * FOREIGN_MUSIC_SCALE 西洋乐器音阶
	 * FOLK_MUSIC_SCALE 民族乐器音阶
	 * PERCUSSION_SCALE 打击乐器音阶
	 * PERCUSSION_RHYTHM 打击乐器节拍
	 * @author daicun
	 */
	public static final class Element {
		public static final String FOREIGN_MUSIC_SCALE = "FOREIGN_MUSIC_SCALE";
		public static final String FOLK_MUSIC_SCALE = "FOLK_MUSIC_SCALE";
		public static final String PERCUSSION_SCALE = "PERCUSSION_SCALE";
		public static final String PERCUSSION_RHYTHM = "PERCUSSION_RHYTHM";
		public static final String ONE_BAR_THREE_BEAT="ONE_BAR_THREE_BEAT";
		public static final String ONE_BAR_FOUR_BEAT="ONE_BAR_FOUR_BEAT";
		public static final String ALL_BEAT="ALL_BEAT";
	}
	
	
	/**
	 * 反应训练--页面参数传递
	 * @author daicun
	 */
	public static class ResponseUIParameters{
		public static final String ELEMENT_TYPE="ELEMENT_TYPE";
		public static final String MUSICAL_INSTRUMENTS_ELEMENT_SET="MUSICAL_INSTRUMENTS_ELEMENT_SET";
		public static final String SCALE_ELEMENT_SET="SCALE_ELEMENT_SET";
		public static final String RANDOM="RANDOM";
	}
	
	/**
	 * 记忆训练--页面参数传递
	 * @author daicun
	 */
	public static class MemoryUIParameters{
		public static final String CHOOSED_MODE="CHOOSED_MODE";
		public static final String ELEMENT_TYPE="ELEMENT_TYPE";
		public static final String MUSICAL_INSTRUMENTS_ELEMENT_SET="MUSICAL_INSTRUMENTS_ELEMENT_SET";
		public static final String SCALE_ELEMENT_SET="SCALE_ELEMENT_SET";
		public static final String RANDOM_MUSICAL_INSTRUMENTS="RANDOM_MUSICAL_INSTRUMENTS";
		public static final String RANDOM_SCALE="RANDOM_SCALE";
		public static final String BIT_TYPE="BIT_TYPE";
		public static final String START_BIT="START_BIT";
	}
	
	/**
	 * 位元类型
	 * @author Administrator
	 */
	public static final class BitType{
		public static final String SETTING_BIT="SETTING_BIT";
		public static final String SELF_CHOOSE_BIT = "SELF_CHOOSE_BIT";
		public static final String CONTINUED_BIT = "CONTINUED_BIT";
	}
	
	/**
	 * 模式选择
	 * @author Administrator
	 */
	public static final class Mode{
		public static final String BEAT_RESPONSE="BEAT_RESPONSE";
		public static final String MEASURE_RESPONSE = "MEASURE_RESPONSE";
	}
	
	public static final class Beat{
		public static final int ONE_BEAT=300;
		public static final int HALF_BEAT=301;	
	}
	/**
	 * 乐器
	 */
	public static class MusicalInstruments{
		/**
		 * 西洋乐器--音阶
		 * @author daicun
		 */
		public static final class ForeignMusicScale {
			public static final int ACCORDIAN = 100;
			public static final int ACOUSTIC_GUITAR = 101;
			public static final int BASSOON = 102;
			public static final int CELLO = 103;
			public static final int CHURCH_ORGAN = 104;
			public static final int CLARINET = 105;
			public static final int ELECTRIC_BASS = 106;
			public static final int ELECTRIC_GUITAR = 107;
			public static final int ELECTRIC_PIANO = 108;
			public static final int FLUTE = 109;
			public static final int FRENCH_HORN = 110;
			public static final int GLOCK = 111;
			public static final int HARP = 112;
			public static final int HARPSICHORD = 113;
			public static final int OBOE = 114;
			public static final int PIANO = 115;
			public static final int PICCOLO = 116;
			public static final int SAX = 117;
			public static final int TROMBONE = 118;
			public static final int TRUMPET = 119;
			public static final int TUBA = 120;
			public static final int TUBULAR_BELLS = 121;
			public static final int VIOLA = 122;
			public static final int VIOLIN = 123;
			public static final int XYLOPHONE = 124;
			public static final int[] ARRAY = {ACCORDIAN,ACOUSTIC_GUITAR,BASSOON,CELLO, CHURCH_ORGAN,CLARINET,ELECTRIC_BASS,
			ELECTRIC_GUITAR,ELECTRIC_PIANO,FLUTE,FRENCH_HORN,GLOCK,HARP,HARPSICHORD,OBOE,PIANO,PICCOLO,SAX,TROMBONE,TRUMPET,
			TUBA,TUBULAR_BELLS,VIOLA,VIOLIN,XYLOPHONE};
		}

		/**
		 * 民族乐器--音阶
		 * @author daicun
		 */
		public static final class FolkMusicScale {
			public static final int BANJO = 125;
			public static final int DI_ZI = 126;
			public static final int DULCIMER = 127;
			public static final int ER_HU = 128;
			public static final int GU_ZHENG = 129;
			public static final int IRISH_WHISTLE = 130;
			public static final int LIU_QIN = 131;
			public static final int MA_TOU_QIN = 132;
			public static final int MANDOLIN = 133;
			public static final int PI_PA = 134;
			public static final int RUAN = 135;
			public static final int SHAKUHACHI = 136;
			public static final int SHAMISEN = 137;
			public static final int SHENG = 138;
			public static final int SITAR = 139;
			public static final int SUO_NA = 140;
			public static final int UILLEANN_BAGPIPE = 141;
			public static final int XIAO = 142;
			public static final int ZHONG = 143;
			public static final int[] ARRAY = {BANJO,DI_ZI,DULCIMER,ER_HU,GU_ZHENG,IRISH_WHISTLE,LIU_QIN,MA_TOU_QIN,
											   MANDOLIN, PI_PA,RUAN, SHAKUHACHI, SHAMISEN, SHENG, SITAR, SUO_NA,UILLEANN_BAGPIPE, XIAO, ZHONG};
		}

		/**
		 * 打击乐器--音阶
		 * @author daicun
		 */
		public static final class PercussionScale{
			public static final int BAN = 144;
			public static final int BONGO = 145;
			public static final int CABASA = 146;
			public static final int CONGA = 147;
			public static final int COW_BELL = 148;
			public static final int CYMBALS = 149;
			public static final int DA_LUO = 150;
			public static final int GUO = 151;
			public static final int KICK = 152;
			public static final int SNARE = 153;
			public static final int TAIKO_DRUMS = 154;
			public static final int TAMBOURINE = 155;
			public static final int TIMBALES = 156;
			public static final int TIMPANI = 157;
			public static final int TRIANGLE = 158;
			public static final int WOODBLOCK = 159;
			public static final int XIAO_LUO = 160;
			public static final int[] ARRAY = {BAN,BONGO,CABASA,CONGA,COW_BELL,CYMBALS,DA_LUO,GUO,KICK,SNARE,
											   TAIKO_DRUMS,TAMBOURINE,TIMBALES,TIMPANI,TRIANGLE,WOODBLOCK,XIAO_LUO};
		}

		/**
		 * 打击乐器--节拍
		 * @author daicun
		 */
		public static final class PercussionRhythm{
			public static final int CYMBALS=149;
			public static final int GLOCK=111;
			public static final int GUO=151;
			public static final int KICK=152;
			public static final int PIANO=115;
			public static final int SNARE=153;
			public static final int TRIANGLE=158;
			public static final int TRUMPET=119;
			public static final int WOODBLOCK=159;
			public static final int[] ARRAY={CYMBALS,GLOCK,GUO,KICK,PIANO,SNARE,TRIANGLE,TRUMPET,WOODBLOCK};
		}
		
		public static final int[] ARRAY = {ForeignMusicScale.ACCORDIAN,ForeignMusicScale.ACOUSTIC_GUITAR,ForeignMusicScale.BASSOON,
		ForeignMusicScale.CELLO, ForeignMusicScale.CHURCH_ORGAN,ForeignMusicScale.CLARINET,ForeignMusicScale.ELECTRIC_BASS,
		ForeignMusicScale.ELECTRIC_GUITAR,ForeignMusicScale.ELECTRIC_PIANO,ForeignMusicScale.FLUTE,ForeignMusicScale.FRENCH_HORN,
		ForeignMusicScale.GLOCK,ForeignMusicScale.HARP,ForeignMusicScale.HARPSICHORD,ForeignMusicScale.OBOE,ForeignMusicScale.PIANO,
		ForeignMusicScale.PICCOLO,ForeignMusicScale.SAX,ForeignMusicScale.TROMBONE,ForeignMusicScale.TRUMPET,ForeignMusicScale.TUBA,
		ForeignMusicScale.TUBULAR_BELLS,ForeignMusicScale.VIOLA,ForeignMusicScale.VIOLIN,ForeignMusicScale.XYLOPHONE,FolkMusicScale.BANJO,
		FolkMusicScale.DI_ZI,FolkMusicScale.DULCIMER,FolkMusicScale.ER_HU,FolkMusicScale.GU_ZHENG,FolkMusicScale.IRISH_WHISTLE,
		FolkMusicScale.LIU_QIN,FolkMusicScale.MA_TOU_QIN,FolkMusicScale.MANDOLIN, FolkMusicScale.PI_PA,FolkMusicScale.RUAN, 
		FolkMusicScale.SHAKUHACHI, FolkMusicScale.SHAMISEN, FolkMusicScale.SHENG, FolkMusicScale.SITAR, FolkMusicScale.SUO_NA,
		FolkMusicScale.UILLEANN_BAGPIPE, FolkMusicScale.XIAO, FolkMusicScale.ZHONG,PercussionScale.BAN,PercussionScale.BONGO,
		PercussionScale.CABASA,PercussionScale.CONGA,PercussionScale.COW_BELL,PercussionScale.CYMBALS,PercussionScale.DA_LUO,
		PercussionScale.GUO,PercussionScale.KICK,PercussionScale.SNARE,PercussionScale.TAIKO_DRUMS,PercussionScale.TAMBOURINE,
		PercussionScale.TIMBALES,PercussionScale.TIMPANI,PercussionScale.TRIANGLE,PercussionScale.WOODBLOCK,PercussionScale.XIAO_LUO};
	}
	
	/**
	 * 音阶
	 * @author daicun
	 */
	public static final class Scale {
		public static final int DO = 200;
		public static final int RE = 201;
		public static final int MI = 202;
		public static final int FA = 203;
		public static final int SOL = 204;
		public static final int LA = 205;
		public static final int SI = 206;
		public static final int HIGH_DO = 207;
		public static final int[] ARRAY ={DO,RE,MI,FA,SOL,LA,SI,HIGH_DO };
	}

	/**
	 * 节奏
	 * @author daicun
	 */
	public static final class Rhythm {
		public static final class OneBarThreeBeat{
			public static final int T3_ONE = 300;
			public static final int T3_TWO = 301;
			public static final int T3_THREE = 302;
			public static final int T3_FOUR = 303;
			public static final int T3_FIVE = 304;
			public static final int T3_SIX = 305;
			public static final int T3_SEVEN = 306;
			public static final int T3_EIGHT = 307;
			public static final int[] ARRAY = {T3_ONE,T3_TWO,T3_THREE,T3_FOUR,T3_FIVE,T3_SIX,T3_SEVEN,T3_EIGHT};
		}
		
		public static final class OneBarFourBeat{
			public static final int T4_ONE = 308;
			public static final int T4_TWO = 309;
			public static final int T4_THREE = 310;
			public static final int T4_FOUR = 311;
			public static final int T4_FIVE = 312;
			public static final int T4_SIX = 313;
			public static final int T4_SEVEN = 314;
			public static final int T4_EIGHT = 315;
			public static final int T4_NINE = 316;
			public static final int T4_TEN = 317;
			public static final int T4_ELEVEN = 318;
			public static final int T4_TWELVE = 319;
			public static final int T4_THIRTEEN = 320;
			public static final int T4_FOURTEEN = 321;
			public static final int T4_FIFTEEN = 322;
			public static final int T4_SIXTEEN = 323;
			public static final int[] ARRAY = {T4_ONE,T4_TWO,T4_THREE,T4_FOUR,T4_FIVE,T4_SIX,T4_SEVEN,T4_EIGHT,
				                               T4_NINE,T4_TEN,T4_ELEVEN,T4_TWELVE,T4_THIRTEEN,T4_FOURTEEN,T4_FIFTEEN,T4_SIXTEEN};
		}
		
		public static final int[] ARRAY = {OneBarThreeBeat.T3_ONE,OneBarThreeBeat.T3_TWO,OneBarThreeBeat.T3_THREE,OneBarThreeBeat.T3_FOUR,
			OneBarThreeBeat.T3_FIVE,OneBarThreeBeat.T3_SIX,OneBarThreeBeat.T3_SEVEN,OneBarThreeBeat.T3_EIGHT,OneBarFourBeat.T4_ONE,
			OneBarFourBeat.T4_TWO,OneBarFourBeat.T4_THREE,OneBarFourBeat.T4_FOUR,OneBarFourBeat.T4_FIVE,OneBarFourBeat.T4_SIX,
			OneBarFourBeat.T4_SEVEN,OneBarFourBeat.T4_EIGHT,OneBarFourBeat.T4_NINE,OneBarFourBeat.T4_TEN,OneBarFourBeat.T4_ELEVEN,
			OneBarFourBeat.T4_TWELVE,OneBarFourBeat.T4_THIRTEEN,OneBarFourBeat.T4_FOURTEEN,OneBarFourBeat.T4_FIFTEEN,
			OneBarFourBeat.T4_SIXTEEN};
	}
	
	/**
	 * 布局编号
	 * @author daicun
	 */
	public static class LayoutId{
		public static final int LAYOUT_ID_ONE=10;
		public static final int LAYOUT_ID_TWO=11;
		public static final int LAYOUT_ID_THREE=12;
		public static final int LAYOUT_ID_FOUR=13;
		public static final int LAYOUT_ID_FIVE=14;
		public static final int LAYOUT_ID_SIX=15;
		public static final int LAYOUT_ID_SEVEN=16;
		public static final int LAYOUT_ID_EIGHT=17;
		public static final int LAYOUT_ID_NINE=18;
		public static final int LAYOUT_ID_TEN=19;
		public static final int LAYOUT_ID_ELEVEN=20;
		public static final int LAYOUT_ID_TWELVE=21;
		public static final int LAYOUT_ID_THIRTEEN=22;
		public static final int LAYOUT_ID_FOURTEEN=23;
		public static final int LAYOUT_ID_FIFTEEN=24;
		public static final int LAYOUT_ID_SIXTEEN=25;
		public static final int LAYOUT_ID_SEVENTEEN=26;
		public static final int LAYOUT_ID_EIGHTEEN=27;
		public static final int LAYOUT_ID_NINETEEN=28;
		public static final int LAYOUT_ID_TWENTY=29;
		public static final int[] LAYOUT_ARRAY={LAYOUT_ID_ONE,LAYOUT_ID_TWO,LAYOUT_ID_THREE,LAYOUT_ID_FOUR,LAYOUT_ID_FIVE,LAYOUT_ID_SIX,
			LAYOUT_ID_SEVEN,LAYOUT_ID_EIGHT,LAYOUT_ID_NINE,LAYOUT_ID_TEN,LAYOUT_ID_ELEVEN,LAYOUT_ID_TWELVE,LAYOUT_ID_THIRTEEN,
			LAYOUT_ID_FOURTEEN,LAYOUT_ID_FIFTEEN,LAYOUT_ID_SIXTEEN,LAYOUT_ID_SEVENTEEN,LAYOUT_ID_EIGHTEEN,LAYOUT_ID_NINETEEN,
			LAYOUT_ID_TWENTY};
	}
	
	/**
	 * 试图编号
	 * @author daicun
	 */
	public static class ViewId{
		public static final int VIEW_ID_ONE=30;
		public static final int VIEW_ID_TWO=31;
		public static final int VIEW_ID_THREE=32;
		public static final int VIEW_ID_FOUR=33;
		public static final int VIEW_ID_FIVE=34;
		public static final int VIEW_ID_SIX=35;
		public static final int VIEW_ID_SEVEN=36;
		public static final int VIEW_ID_EIGHT=37;
		public static final int VIEW_ID_NINE=38;
		public static final int VIEW_ID_TEN=39;
		public static final int VIEW_ID_ELEVEN=40;
		public static final int VIEW_ID_TWELVE=41;
		public static final int VIEW_ID_THIRTEEN=42;
		public static final int VIEW_ID_FOURTEEN=43;
		public static final int VIEW_ID_FIFTEEN=44;
		public static final int VIEW_ID_SIXTEEN=45;
		public static final int VIEW_ID_SEVENTEEN=46;
		public static final int VIEW_ID_EIGHTEEN=47;
		public static final int VIEW_ID_NINETEEN=48;
		public static final int VIEW_ID_TWENTY=49;
		public static final int[] VIEW_ARRAY={VIEW_ID_ONE,VIEW_ID_TWO,VIEW_ID_THREE,VIEW_ID_FOUR,VIEW_ID_FIVE,VIEW_ID_SIX,
			VIEW_ID_SEVEN,VIEW_ID_EIGHT,VIEW_ID_NINE,VIEW_ID_TEN,VIEW_ID_ELEVEN,VIEW_ID_TWELVE,VIEW_ID_THIRTEEN,VIEW_ID_FOURTEEN,
			VIEW_ID_FIFTEEN,VIEW_ID_SIXTEEN,VIEW_ID_SEVENTEEN,VIEW_ID_EIGHTEEN,VIEW_ID_NINETEEN,VIEW_ID_TWENTY};
	}
	
}
