package com.flextronics.cn.util;

import java.util.HashMap;
import java.util.Map;
import com.flextronics.cn.activity.R;

/**
 * 常量类
 * 
 * @author all
 * 
 */
public final class Constants {

	/**
	 * 参数
	 */
	public final static String PARAMETER = "PARAMETER";
	/**
	 * 规则
	 */
	public final static String RULE = "RULE";
	/**
	 * 上下文
	 */
	public final static String CONTEXT = "CONTEXT";
	/**
	 * 最小位元数
	 */
	public final static int MIN_BIT = 3;
	/**
	 * 最大位元数
	 */
	public final static int MAX_BIT = 36;

	public final static String TEST_REPORT = "TEST_REPORT";
	public final static String CLASS_NAME = "CLASS_NAME";
	public final static String TESTING_ID = "TESTING_ID";
	public final static String PREVIOUS_ACTIVITY = "PREVIOUS_ACTIVITY";
	/**
	 * 音乐文件存放路径
	 */
	public final static String MUSIC_DIRECTORY_PATH = "/data/data/com.flextronics.cn.activity/";

	/**
	 * 左手
	 */
	public final static int LEFT_HAND = 10001;
	/**
	 * 右手
	 */
	public final static int RIGHT_HAND = 10002;
	/**
	 * 食指
	 */
	public final static int FORE_FINGER = 10003;
	/**
	 * 中指
	 */
	public final static int MIDDLE_FINGER = 10004;
	/**
	 * 无名指
	 */
	public final static int THIRD_FINGER = 10005;
	/**
	 * 触摸
	 */
	public final static int TOUCH = 10006;
	/**
	 * 按键
	 */
	public final static int KEY_STOKE = 10007;

	/**
	 * 闪烁时间
	 */
	public final static long FLASH_TIME = 500;
	/**
	 * 等待时间
	 */
	public final static long WAITING_TIME = 2000;
	/**
	 * 记忆训练出题时的等待时间
	 */
	public final static long MEMORY_TRAINING_WAITING_TIME = 1000;
	/**
	 * 默认反应训练题目时间
	 */
	public final static int DEFAULT_RESPONSE_TRAINING_TIME = 30000;
	/**
	 * 默认显示时间
	 */
	public final static long DEFAULT_SHOW_TIME = 1700;
	/**
	 * 倒计时时间
	 */
	public final static long COUNT_DOWN_TIME = 5000;

	/**
	 * 用户信息
	 */
	public final static String USER_INFO = "USER_INFO";
	/**
	 * 用户名
	 */
	public final static String USER_NAME = "USER_NAME";
	/**
	 * 邮箱
	 */
	public final static String EMAIL = "EMAIL";
	/**
	 * 用户头像
	 */
	public final static String ICON = "ICON";
	/**
	 * 背景
	 */
	public final static String BACKGROUND = "BACKGROUND";

	/**
	 * 用户头像资源(8种)
	 */
	public final static int[] USER_ICON_IMAGE_RESOURCES = new int[] {
			R.drawable.user_icon_1, R.drawable.user_icon_2,
			R.drawable.user_icon_3, R.drawable.user_icon_4,
			R.drawable.user_icon_5, R.drawable.user_icon_6,
			R.drawable.user_icon_7, R.drawable.user_icon_8, };

	/**
	 * 用户头像资源2(8种)
	 */
	public final static int[] USER_ICON_IMAGE_RESOURCES_ = new int[] {
			R.drawable.user_icon_1_, R.drawable.user_icon_2_,
			R.drawable.user_icon_3_, R.drawable.user_icon_4_,
			R.drawable.user_icon_5_, R.drawable.user_icon_6_,
			R.drawable.user_icon_7_, R.drawable.user_icon_8_, };

	/**
	 * 系统背景图的缩略图(6种)
	 */
	public final static int[] BACKGROUND_IMAGE_RESOURCES = new int[] {
			R.drawable.user_background_1, R.drawable.user_background_2,
			R.drawable.user_background_3, R.drawable.user_background_4,
			R.drawable.user_background_5, R.drawable.user_background_6 };

	/**
	 * 对应键
	 */
	public final static Map<Integer, Integer> OPPOSITE_KEY = new HashMap<Integer, Integer>();

	static {
		/**
		 * 1和4互为对应键, 2和5互为对应键, 3和6互为对应键, 7的对应键是7; 食指和无名指互为对应键, 中指的对应键是中指
		 */
		OPPOSITE_KEY.put(1, 4);
		OPPOSITE_KEY.put(2, 5);
		OPPOSITE_KEY.put(3, 6);
		OPPOSITE_KEY.put(4, 1);
		OPPOSITE_KEY.put(5, 2);
		OPPOSITE_KEY.put(6, 3);
		OPPOSITE_KEY.put(7, 7);

		OPPOSITE_KEY.put(KeyStoke.FORE_FINGER_LEFT, KeyStoke.THIRD_FINGER_LEFT);
		OPPOSITE_KEY.put(KeyStoke.MIDDLE_FINGER_LEFT,
				KeyStoke.MIDDLE_FINGER_LEFT);
		OPPOSITE_KEY.put(KeyStoke.THIRD_FINGER_LEFT, KeyStoke.FORE_FINGER_LEFT);
		OPPOSITE_KEY.put(KeyStoke.FORE_FINGER_RIGHT,
				KeyStoke.THIRD_FINGER_RIGHT);
		OPPOSITE_KEY.put(KeyStoke.MIDDLE_FINGER_RIGHT,
				KeyStoke.MIDDLE_FINGER_RIGHT);
		OPPOSITE_KEY.put(KeyStoke.THIRD_FINGER_RIGHT,
				KeyStoke.FORE_FINGER_RIGHT);
	}

	/**
	 * 键盘区按键编码
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public final class KeyStoke {
		/**
		 * 左手食指
		 */
		public final static int FORE_FINGER_LEFT = -40001;
		/**
		 * 左手中指
		 */
		public final static int MIDDLE_FINGER_LEFT = -40002;
		/**
		 * 左手无名指
		 */
		public final static int THIRD_FINGER_LEFT = -40003;
		/**
		 * 右手食指
		 */
		public final static int FORE_FINGER_RIGHT = 40001;
		/**
		 * 右手中指
		 */
		public final static int MIDDLE_FINGER_RIGHT = 40002;
		/**
		 * 右手无名指
		 */
		public final static int THIRD_FINGER_RIGHT = 40003;
	}

	/**
	 * 系统中固定的样本群 编码
	 */
	public final class Sample {
		/**
		 * 白光 样本群
		 */
		public final static int WHITE = 50000;
		/**
		 * 颜色 样本群
		 */
		public final static int COLORS = 60000;
		/**
		 * 阿拉伯数字 样本群
		 */
		public final static int NUMBERS = 70000;
		/**
		 * 罗马数字 样本群
		 */
		public final static int ROME_NUMBERS = 80000;
		/**
		 * 英文字母 样本群
		 */
		public final static int ENGLISH_LETTERS = 90000;
		/**
		 * 形状 样本群
		 */
		public final static int SHAPES = 100000;
		/**
		 * 一般符号 样本群
		 */
		public final static int COMMON_MARKS = 110000;
		/**
		 * 音乐符号 样本群
		 */
		public final static int MUSIC_MARKS = 120000;
		/**
		 * 西洋乐 样本群
		 */
		public final static int FOREIGH_MUSIC = 130000;
		/**
		 * 民族乐 样本群
		 */
		public final static int FOLK_MUSIC = 140000;
		/**
		 * 打击乐 样本群
		 */
		public final static int PERCUSSION_INSTRUMENT = 150000;
	}

	/**
	 * 颜色
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class Colors {

		/**
		 * 红色
		 */
		public final static int RED = 60001;
		/**
		 * 橙色
		 */
		public final static int ORANGE = 60002;
		/**
		 * 黄色
		 */
		public final static int YELLOW = 60003;
		/**
		 * 绿色
		 */
		public final static int GREEN = 60004;
		/**
		 * 蓝色
		 */
		public final static int BLUE = 60005;
		/**
		 * 靛色
		 */
		public final static int INDIGO = 60006;
		/**
		 * 紫色
		 */
		public final static int PURPLE = 60007;
		/**
		 * 黑色
		 */
		public final static int BLACK = 60008;
		/**
		 * 咖啡色
		 */
		public final static int COFFEE = 60009;
		/**
		 * 颜色样本群中所有元素
		 */
		public static final String[] COLORS = { "60001", "60002", "60003",
				"60004", "60005", "60006", "60007", "60008", "60009" };

		/**
		 * 颜色样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = {

		R.drawable.color_red, R.drawable.color_orange, R.drawable.color_yellow,
				R.drawable.color_green, R.drawable.color_blue,
				R.drawable.color_indigo, R.drawable.color_purple,
				R.drawable.color_black, R.drawable.color_coffee };
		/**
		 * 颜色样本群中所有元素对应的按钮图片资源(未点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				R.drawable.btn_color_red, R.drawable.btn_color_orange,
				R.drawable.btn_color_yellow, R.drawable.btn_color_green,
				R.drawable.btn_color_blue, R.drawable.btn_color_indigo,
				R.drawable.btn_color_purple, R.drawable.btn_color_black,
				R.drawable.btn_color_coffee };
		/**
		 * 颜色样本群中所有元素对应的按钮图片资源(点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				R.drawable.btn_color_white, R.drawable.btn_color_white,
				R.drawable.btn_color_white, R.drawable.btn_color_white,
				R.drawable.btn_color_white, R.drawable.btn_color_white,
				R.drawable.btn_color_white, R.drawable.btn_color_white,
				R.drawable.btn_color_white };
	}

	/**
	 * 阿拉伯数字
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class Numbers {

		public final static int ZERO = 70001;
		public final static int ONE = 70002;
		public final static int TWO = 70003;
		public final static int THREE = 70004;
		public final static int FOUR = 70005;
		public final static int FIVE = 70006;
		public final static int SIX = 70007;
		public final static int SEVEN = 70008;
		public final static int EIGHT = 70009;
		public final static int NINE = 70010;

		/**
		 * 阿拉伯数字样本群中所有元素对应的图片资源
		 */
		public static final String[] NUMBERS = { "70001", "70002", "70003",
				"70004", "70005", "70006", "70007", "70008", "70009", "70010" };
		/**
		 * 阿拉伯数字 样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.number_0,
				R.drawable.number_1, R.drawable.number_2, R.drawable.number_3,
				R.drawable.number_4, R.drawable.number_5, R.drawable.number_6,
				R.drawable.number_7, R.drawable.number_8, R.drawable.number_9 };
		/**
		 * 阿拉伯数字 样本群中所有元素对应的按钮图片资源(未点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				R.drawable.btn_number_0, R.drawable.btn_number_1,
				R.drawable.btn_number_2, R.drawable.btn_number_3,
				R.drawable.btn_number_4, R.drawable.btn_number_5,
				R.drawable.btn_number_6, R.drawable.btn_number_7,
				R.drawable.btn_number_8, R.drawable.btn_number_9 };

		/**
		 * 阿拉伯数字 样本群中所有元素对应的按钮图片资源(点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				R.drawable.btn_number_0_2, R.drawable.btn_number_1_2,
				R.drawable.btn_number_2_2, R.drawable.btn_number_3_2,
				R.drawable.btn_number_4_2, R.drawable.btn_number_5_2,
				R.drawable.btn_number_6_2, R.drawable.btn_number_7_2,
				R.drawable.btn_number_8_2, R.drawable.btn_number_9_2 };

		/**
		 * 阿拉伯数字所对应的字符串
		 */
		public static final String[] STRING_NUMBERS = { "0", "1", "2", "3",
				"4", "5", "6", "7", "8", "9" };

	}

	/**
	 * 罗马数字
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class RomeNumbers {

		public final static int ROMR_ONE = 80001;
		public final static int ROMR_TWO = 80002;
		public final static int ROMR_THREE = 80003;
		public final static int ROMR_FOUR = 80004;
		public final static int ROMR_FIVE = 80005;
		public final static int ROMR_SIX = 80006;
		public final static int ROMR_SEVEN = 80007;
		public final static int ROMR_EIGHT = 80008;
		public final static int ROMR_NINE = 80009;
		public final static int ROMR_TEN = 80010;

		public static final String[] NUMBERS = { "80001", "80002", "80003",
				"80004", "80005", "80006", "80007", "80008", "80009", "80010" };

		/**
		 * 罗马数字 样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.rome_number_1,
				R.drawable.rome_number_2, R.drawable.rome_number_3,
				R.drawable.rome_number_4, R.drawable.rome_number_5,
				R.drawable.rome_number_6, R.drawable.rome_number_7,
				R.drawable.rome_number_8, R.drawable.rome_number_9,
				R.drawable.rome_number_10 };
		/**
		 * 罗马数字 样本群中所有元素对应的按钮图片资源(未点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				R.drawable.btn_rome_number_01, R.drawable.btn_rome_number_02,
				R.drawable.btn_rome_number_03, R.drawable.btn_rome_number_04,
				R.drawable.btn_rome_number_05, R.drawable.btn_rome_number_06,
				R.drawable.btn_rome_number_07, R.drawable.btn_rome_number_08,
				R.drawable.btn_rome_number_09, R.drawable.btn_rome_number_10 };
		/**
		 * 罗马数字 样本群中所有元素对应的按钮图片资源(点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				R.drawable.btn_rome_number_01_2,
				R.drawable.btn_rome_number_02_2,
				R.drawable.btn_rome_number_03_2,
				R.drawable.btn_rome_number_04_2,
				R.drawable.btn_rome_number_05_2,
				R.drawable.btn_rome_number_06_2,
				R.drawable.btn_rome_number_07_2,
				R.drawable.btn_rome_number_08_2,
				R.drawable.btn_rome_number_09_2,
				R.drawable.btn_rome_number_10_2 };

		/**
		 * 罗马数字的字符串
		 */
		public static final String[] STRING_ROMA_NUMBER = { "Ⅰ", "Ⅱ", "Ⅲ", "Ⅳ",
				"Ⅴ", "Ⅵ", "Ⅶ", "Ⅷ", "Ⅸ", "Ⅹ", "Ⅺ", "Ⅻ" };
	}

	/**
	 * 英文字母
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class EnglishLetters {

		public final static int A = 90001;
		public final static int B = 90002;
		public final static int C = 90003;
		public final static int D = 90004;
		public final static int E = 90005;
		public final static int F = 90006;
		public final static int G = 90007;
		public final static int H = 90008;
		public final static int I = 90009;
		public final static int J = 90010;
		public final static int K = 90011;
		public final static int L = 90012;
		public final static int M = 90013;
		public final static int N = 90014;
		public final static int O = 90015;
		public final static int P = 90016;
		public final static int Q = 90017;
		public final static int R = 90018;
		public final static int S = 90029;
		public final static int T = 90020;
		public final static int U = 90021;
		public final static int V = 90022;
		public final static int W = 90023;
		public final static int X = 90024;
		public final static int Y = 90025;
		public final static int Z = 90026;

		public static final String[] LETTERS = { "90001", "90002", "90003",
				"90004", "90005", "90006", "90007", "90008", "90009", "90010",
				"90011", "90012", "90013", "90014", "90015", "90016", "90017",
				"90018", "90019", "90020", "90021", "90022", "90023", "90024",
				"90025", "90026" };

		/**
		 * 英文字母样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = {
				com.flextronics.cn.activity.R.drawable.english_letter_a,
				com.flextronics.cn.activity.R.drawable.english_letter_b,
				com.flextronics.cn.activity.R.drawable.english_letter_c,
				com.flextronics.cn.activity.R.drawable.english_letter_d,
				com.flextronics.cn.activity.R.drawable.english_letter_e,
				com.flextronics.cn.activity.R.drawable.english_letter_f,
				com.flextronics.cn.activity.R.drawable.english_letter_g,
				com.flextronics.cn.activity.R.drawable.english_letter_h,
				com.flextronics.cn.activity.R.drawable.english_letter_i,
				com.flextronics.cn.activity.R.drawable.english_letter_j,
				com.flextronics.cn.activity.R.drawable.english_letter_k,
				com.flextronics.cn.activity.R.drawable.english_letter_l,
				com.flextronics.cn.activity.R.drawable.english_letter_m,
				com.flextronics.cn.activity.R.drawable.english_letter_n,
				com.flextronics.cn.activity.R.drawable.english_letter_o,
				com.flextronics.cn.activity.R.drawable.english_letter_p,
				com.flextronics.cn.activity.R.drawable.english_letter_q,
				com.flextronics.cn.activity.R.drawable.english_letter_r,
				com.flextronics.cn.activity.R.drawable.english_letter_s,
				com.flextronics.cn.activity.R.drawable.english_letter_t,
				com.flextronics.cn.activity.R.drawable.english_letter_u,
				com.flextronics.cn.activity.R.drawable.english_letter_v,
				com.flextronics.cn.activity.R.drawable.english_letter_w,
				com.flextronics.cn.activity.R.drawable.english_letter_x,
				com.flextronics.cn.activity.R.drawable.english_letter_y,
				com.flextronics.cn.activity.R.drawable.english_letter_z };
		/**
		 * 英文字母 样本群中所有元素对应的按钮图片资源(未点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				com.flextronics.cn.activity.R.drawable.btn_english_letter_a,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_b,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_c,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_d,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_e,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_f,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_g,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_h,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_i,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_j,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_k,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_l,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_m,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_n,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_o,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_p,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_q,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_r,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_s,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_t,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_u,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_v,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_w,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_x,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_y,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_z };
		/**
		 * 英文字母 样本群中所有元素对应的按钮图片资源(点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				com.flextronics.cn.activity.R.drawable.btn_english_letter_a_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_b_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_c_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_d_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_e_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_f_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_g_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_h_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_i_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_j_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_k_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_l_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_m_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_n_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_o_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_p_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_q_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_r_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_s_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_t_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_u_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_v_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_w_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_x_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_y_2,
				com.flextronics.cn.activity.R.drawable.btn_english_letter_z_2 };

		public static final String[] STRING_LETTERS = { "A", "B", "C", "D",
				"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	}

	/**
	 * 图形
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class Shapes {

		/**
		 * 圆形编码
		 */
		public final static int CIRCLE = 100001;
		/**
		 * 三角形编码
		 */
		public final static int TRIGON = 100002;
		/**
		 * 正方形编码
		 */
		public final static int SQUARE = 100003;
		/**
		 * 六边形编码
		 */
		public final static int HEXAGON = 100004;
		/**
		 * 长方形编码
		 */
		public final static int RECTANGLE = 100005;
		/**
		 * 扇形编码
		 */
		public final static int SECTOR = 100006;
		/**
		 * 平行四边形编码
		 */
		public final static int PARALLELOGRAM = 100007;
		/**
		 * 菱形编码
		 */
		public final static int DIAMOND = 100008;
		/**
		 * 五边形编码
		 */
		public final static int PENTAGON = 100009;

		/**
		 * 图形样本中所有元素编码
		 */
		public static final String[] SHAPES = { "100001", "100002", "100003",
				"100004", "100005", "100006", "100007", "100008", "100009" };

		/**
		 * 图形样本所有元素对应的图片(圆形)
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.shape_1,
				R.drawable.shape_2, R.drawable.shape_3, R.drawable.shape_4,
				R.drawable.shape_5, R.drawable.shape_6, R.drawable.shape_7,
				R.drawable.shape_8, R.drawable.shape_9 };

		/**
		 * 图形样本所有元素的按钮图片(方形-没有按下时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				R.drawable.btn_shape_1, R.drawable.btn_shape_2,
				R.drawable.btn_shape_3, R.drawable.btn_shape_4,
				R.drawable.btn_shape_5, R.drawable.btn_shape_6,
				R.drawable.btn_shape_7, R.drawable.btn_shape_8,
				R.drawable.btn_shape_9 };

		/**
		 * 图形样本所有元素的按钮图片(方形-按下时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				R.drawable.btn_shape_1_2, R.drawable.btn_shape_2_2,
				R.drawable.btn_shape_3_2, R.drawable.btn_shape_4_2,
				R.drawable.btn_shape_5_2, R.drawable.btn_shape_6_2,
				R.drawable.btn_shape_7_2, R.drawable.btn_shape_8_2,
				R.drawable.btn_shape_9_2 };
	}

	/**
	 * 一般符号
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class CommonMarks {

		public final static int COMMON_MARK_01 = 110001;
		public final static int COMMON_MARK_02 = 110002;
		public final static int COMMON_MARK_03 = 110003;
		public final static int COMMON_MARK_04 = 110004;
		public final static int COMMON_MARK_05 = 110005;
		public final static int COMMON_MARK_06 = 110006;
		public final static int COMMON_MARK_07 = 110007;
		public final static int COMMON_MARK_08 = 110008;
		public final static int COMMON_MARK_09 = 110009;
		public final static int COMMON_MARK_10 = 110010;
		public final static int COMMON_MARK_11 = 110011;
		public final static int COMMON_MARK_12 = 110012;
		public final static int COMMON_MARK_13 = 110013;
		public final static int COMMON_MARK_14 = 110014;
		public final static int COMMON_MARK_15 = 110015;
		public final static int COMMON_MARK_16 = 110016;
		public final static int COMMON_MARK_17 = 110017;
		public final static int COMMON_MARK_18 = 110018;
		public final static int COMMON_MARK_19 = 110019;
		public final static int COMMON_MARK_20 = 110020;
		/**
		 * 一般符号样本中所有元素编码
		 */
		public static final String[] MARKS = { "110001", "110002", "110003",
				"110004", "110005", "110006", "110007", "110008", "110009",
				"110010", "110011", "110012", "110013", "110014", "110015",
				"110016", "110017", "110018", "110019", "110020" };

		/**
		 * 一般符号样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.common_mark_1,
				R.drawable.common_mark_2, R.drawable.common_mark_3,
				R.drawable.common_mark_4, R.drawable.common_mark_5,
				R.drawable.common_mark_6, R.drawable.common_mark_7,
				R.drawable.common_mark_8, R.drawable.common_mark_9,
				R.drawable.common_mark_10, R.drawable.common_mark_11,
				R.drawable.common_mark_12, R.drawable.common_mark_13,
				R.drawable.common_mark_14, R.drawable.common_mark_15,
				R.drawable.common_mark_16, R.drawable.common_mark_17,
				R.drawable.common_mark_18, R.drawable.common_mark_19,
				R.drawable.common_mark_20 };

		/**
		 * 一般符号 样本群中所有元素对应的按钮图片资源(未点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = {
				R.drawable.btn_common_mark_1, R.drawable.btn_common_mark_2,
				R.drawable.btn_common_mark_3, R.drawable.btn_common_mark_4,
				R.drawable.btn_common_mark_5, R.drawable.btn_common_mark_6,
				R.drawable.btn_common_mark_7, R.drawable.btn_common_mark_8,
				R.drawable.btn_common_mark_9, R.drawable.btn_common_mark_10,
				R.drawable.btn_common_mark_11, R.drawable.btn_common_mark_12,
				R.drawable.btn_common_mark_13, R.drawable.btn_common_mark_14,
				R.drawable.btn_common_mark_15, R.drawable.btn_common_mark_16,
				R.drawable.btn_common_mark_17, R.drawable.btn_common_mark_18,
				R.drawable.btn_common_mark_19, R.drawable.btn_common_mark_20 };

		/**
		 * 一般符号样本群中所有元素对应的按钮图片资源(点击时)
		 */
		public static final int[] BTN_IMAGE_RESOURCES_2 = {
				R.drawable.btn_common_mark_1_2, R.drawable.btn_common_mark_2_2,
				R.drawable.btn_common_mark_3_2, R.drawable.btn_common_mark_4_2,
				R.drawable.btn_common_mark_5_2, R.drawable.btn_common_mark_6_2,
				R.drawable.btn_common_mark_7_2, R.drawable.btn_common_mark_8_2,
				R.drawable.btn_common_mark_9_2,
				R.drawable.btn_common_mark_10_2,
				R.drawable.btn_common_mark_11_2,
				R.drawable.btn_common_mark_12_2,
				R.drawable.btn_common_mark_13_2,
				R.drawable.btn_common_mark_14_2,
				R.drawable.btn_common_mark_15_2,
				R.drawable.btn_common_mark_16_2,
				R.drawable.btn_common_mark_17_2,
				R.drawable.btn_common_mark_18_2,
				R.drawable.btn_common_mark_19_2,
				R.drawable.btn_common_mark_20_2 };
		
		public static final String[] STRING_COMMON_MARKS = {
			"?","!",":",",",";","+","-","=","~","...","｀","&","%","*","#","@","$","￥","￡","∈",
		};
	}

	/**
	 * 音乐符号
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class MusicMarks {

		public final static int MUSIC_MARK_01 = 120001;
		public final static int MUSIC_MARK_02 = 120002;
		public final static int MUSIC_MARK_03 = 120003;
		public final static int MUSIC_MARK_04 = 120004;
		public final static int MUSIC_MARK_05 = 120005;
		public final static int MUSIC_MARK_06 = 120006;
		public final static int MUSIC_MARK_07 = 120007;
		public final static int MUSIC_MARK_08 = 120008;
		public final static int MUSIC_MARK_09 = 120009;
		public final static int MUSIC_MARK_10 = 120010;
		public final static int MUSIC_MARK_11 = 120011;
		public final static int MUSIC_MARK_12 = 120012;
		public final static int MUSIC_MARK_13 = 120013;
		public final static int MUSIC_MARK_14 = 120014;
		public final static int MUSIC_MARK_15 = 120015;
		public final static int MUSIC_MARK_16 = 120016;
		public final static int MUSIC_MARK_17 = 120017;
		public final static int MUSIC_MARK_18 = 120018;
		public final static int MUSIC_MARK_19 = 120019;
		public final static int MUSIC_MARK_20 = 120020;

		/**
		 * 音乐符号样本中所有元素编码
		 */
		public static final String[] MARKS = { "120001", "120002", "120003",
				"120004", "120005", "120006", "120007", "120008", "120009",
				"120010", "120011", "120012", "120013", "120014", "120015",
				"120016", "120017", "120018", "120019", "120020" };
		/**
		 * 音乐符号样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.music_mark_1,
				R.drawable.music_mark_2, R.drawable.music_mark_3,
				R.drawable.music_mark_4, R.drawable.music_mark_5,
				R.drawable.music_mark_6, R.drawable.music_mark_7,
				R.drawable.music_mark_8, R.drawable.music_mark_9,
				R.drawable.music_mark_10, R.drawable.music_mark_11,
				R.drawable.music_mark_12, R.drawable.music_mark_13,
				R.drawable.music_mark_14, R.drawable.music_mark_15,
				R.drawable.music_mark_16, R.drawable.music_mark_17,
				R.drawable.music_mark_18, R.drawable.music_mark_19,
				R.drawable.music_mark_20 };
	}

	/**
	 * 西洋乐
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class ForeignMusic {

		public final static int PIANO = 130001;
		public final static int TRUMENT = 130002;
		public final static int VIOLIN = 130003;
		public final static int SHU_QIN = 130004;
		public final static int CHANG_HAO = 130005;
		public final static int ZHONG_TI_QIN = 130006;
		public final static int JIAO_TANG_QIN = 130007;
		public final static int FA_GUO_HAO = 130008;
		public final static int DA_TI_QIN = 130009;
		public final static int MU_JI_TA = 130010;
		public final static int CHANG_DI = 130011;
		public final static int SHOU_FENG_QIN = 130012;
		public final static int DIAN_JI_TA = 130013;
		public final static int SA_KE_SI = 130014;
		public final static int TIE_QIN = 130015;
		/**
		 * 西洋乐样本中所有元素编码
		 */
		public static final String[] INSTRUMENTS = { "130001", "130002",
				"130003", "130004", "130005", "130006", "130007", "130008",
				"130009", "130010", "130011", "130012", "130013", "130014",
				"130015" };
		/**
		 * 食指区包含的西洋乐
		 */
		public static final String[] INSTRUMENTS_FORE_FINGER_AREA = { "130003",
				"130006", "130009", "130012", "130015" };
		/**
		 * 中指区包含的西洋乐
		 */
		public static final String[] INSTRUMENTS_MIDDLE_FINGER_AREA = {
				"130002", "130005", "130008", "130011", "130014" };
		/**
		 * 无名指区包含的西洋乐
		 */
		public static final String[] INSTRUMENTS_THIRD_FINGER_AREA = {
				"130001", "130004", "130007", "130010", "130013" };

		/**
		 * 西洋乐样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = {
				R.drawable.foreign_music_1, R.drawable.foreign_music_2,
				R.drawable.foreign_music_3, R.drawable.foreign_music_4,
				R.drawable.foreign_music_5, R.drawable.foreign_music_6,
				R.drawable.foreign_music_7, R.drawable.foreign_music_8,
				R.drawable.foreign_music_9, R.drawable.foreign_music_10,
				R.drawable.foreign_music_11, R.drawable.foreign_music_12,
				R.drawable.foreign_music_13, R.drawable.foreign_music_14,
				R.drawable.foreign_music_15 };
	}

	/**
	 * 民族乐
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class FolkMusic {

		public final static int PI_PA = 140001;
		public final static int ER_HU = 140002;
		public final static int SUO_NA = 140003;
		public final static int GU_ZHENG = 140004;
		public final static int MA_TOU_QIN = 140005;
		public final static int ER_AI_LAN_FENG_QIN = 140006;
		public final static int YANG_QIN = 140007;
		public final static int XI_TA_QIN = 140008;
		public final static int CHI_BA = 140009;
		public final static int LIU_QIN = 140010;
		public final static int BAN_JIU_QIN = 140011;
		public final static int DI = 140012;
		public final static int RUAN = 140013;
		public final static int SAN_WEI_XIAN = 140014;
		public final static int SHENG = 140015;

		/**
		 * 民族乐样本中所有元素编码
		 */
		public static final String[] INSTRUMENTS = { "140001", "140002",
				"140003", "140004", "140005", "140006", "140007", "140008",
				"140009", "140010", "140011", "140012", "140013", "140014",
				"140015" };
		/**
		 * 食指区包含的民族乐
		 */
		public static final String[] INSTRUMENTS_FORE_FINGER_AREA = { "140003",
				"140006", "140009", "140012", "140015" };
		/**
		 * 中指区包含的民族乐
		 */
		public static final String[] INSTRUMENTS_MIDDLE_FINGER_AREA = {
				"140002", "140005", "140008", "140011", "140014" };
		/**
		 * 无名指区包含的民族乐
		 */
		public static final String[] INSTRUMENTS_THIRD_FINGER_AREA = {
				"140001", "140004", "140007", "140010", "140013" };
		/**
		 * 民族乐样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.folk_music_1,
				R.drawable.folk_music_2, R.drawable.folk_music_3,
				R.drawable.folk_music_4, R.drawable.folk_music_5,
				R.drawable.folk_music_6, R.drawable.folk_music_7,
				R.drawable.folk_music_8, R.drawable.folk_music_9,
				R.drawable.folk_music_10, R.drawable.folk_music_11,
				R.drawable.folk_music_12, R.drawable.folk_music_13,
				R.drawable.folk_music_14, R.drawable.folk_music_15 };
	}

	/**
	 * 打击乐
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class PercussionInstrument {

		public final static int DRUM = 150001;
		public final static int TEMPLE_BLOCK = 150002;
		public final static int TRIANGLE = 150003;
		public final static int XIAO_GU = 150004;
		public final static int LIN_GU = 150005;
		public final static int LUO = 150006;
		public final static int TAI_GU = 150007;
		public final static int BANG_GE_GU = 150008;
		public final static int BO = 150009;
		public final static int DING_YIN_GU = 150010;
		public final static int YAN_BA_GU = 150011;
		public final static int SHA_LIN = 150012;
		public final static int ZHONG_GUO_GU = 150013;
		public final static int KANG_JIA_GU = 150014;
		public final static int NIU_LIN = 150015;

		/**
		 * 打击乐样本中所有元素编码
		 */
		public static final String[] INSTRUMENTS = { "150001", "150002",
				"150003", "150004", "150005", "150006", "150007", "150008",
				"150009", "150010", "150011", "150012", "150013", "150014",
				"150015" };
		/**
		 * 食指区包含的打击乐
		 */
		public static final String[] INSTRUMENTS_FORE_FINGER_AREA = { "150003",
				"150006", "150009", "150012", "150015" };
		/**
		 * 中指区包含的打击乐
		 */
		public static final String[] INSTRUMENTS_MIDDLE_FINGER_AREA = {
				"150002", "150005", "150008", "150011", "150014" };
		/**
		 * 无名指区包含的打击乐
		 */
		public static final String[] INSTRUMENTS_THIRD_FINGER_AREA = {
				"150001", "150004", "150007", "150010", "150013" };
		/**
		 * 打击乐样本群中所有元素对应的图片资源
		 */
		public static final int[] IMAGE_RESOURCES = {
				R.drawable.percussion_instrument_1,
				R.drawable.percussion_instrument_2,
				R.drawable.percussion_instrument_3,
				R.drawable.percussion_instrument_4,
				R.drawable.percussion_instrument_5,
				R.drawable.percussion_instrument_6,
				R.drawable.percussion_instrument_7,
				R.drawable.percussion_instrument_8,
				R.drawable.percussion_instrument_9,
				R.drawable.percussion_instrument_10,
				R.drawable.percussion_instrument_11,
				R.drawable.percussion_instrument_12,
				R.drawable.percussion_instrument_13,
				R.drawable.percussion_instrument_14,
				R.drawable.percussion_instrument_15 };
	}

	/**
	 * 白光
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static class WhiteLight {
		/**
		 * 白光编码
		 */
		public final static int WHITELIGHT = 50001;
		/**
		 * 该样本群中所有元素
		 */
		public static final String[] LIGHTS = { "50001" };
		/**
		 * 样本群对应的图片(圆形)
		 */
		public static final int[] IMAGE_RESOURCES = { R.drawable.white_light };
		/**
		 * 样本群对应的按钮图片(方形)
		 */
		public static final int[] BTN_IMAGE_RESOURCES = { R.drawable.btn_color_white };
	}

	/**
	 * 视觉触觉反应训练，UI页面需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class VisioTouchResponseTrainingUIParameter {
		/**
		 * 时间,取值30s, 60s, 90s, 120s, 180s
		 */
		public final static String TIME = "TIME";
		/**
		 * 反应类型：原始键反应、对应键反应
		 */
		public final static String RESPONSE_TYPE = "RESPONSE_TYPE";
		/**
		 * 回应类型：反射回应、定点回应、替代回应
		 */
		public final static String ANSWER_TYPE = "ANSWER_TYPE";
		/**
		 * 原始键反应
		 */
		public final static int ORIGINAL_KEY_RESPONSE = 170001;
		/**
		 * 对应键反应
		 */
		public final static int OPPOSITE_KEY_RESPONSE = 170002;
		/**
		 * 反射回应
		 */
		public final static int REFLECT_ANSWER = 170003;
		/**
		 * 定点回应
		 */
		public final static int FIXED_POINT_ANSWER = 170004;
		/**
		 * 替代回应
		 */
		public final static int INSTEAD_ANSWER = 170005;
		/**
		 * 样本群
		 */
		public final static String SAMPLE_SET = "SAMPLE_SET";
		/**
		 * 样本元素
		 */
		public final static String SAMPLE_ELEMENTS = "SAMPLE_ELEMENTS";
		/**
		 * 手势类型
		 */
		public final static String HAND_TYPE = "HAND_TYPE";
	}

	/**
	 * 视觉触觉记忆训练，UI页面需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class VisioTouchMemoryTrainingUIParameter {
		/**
		 * 样本群
		 */
		public final static String SAMPLE_SET = "SAMPLE_SET";
		/**
		 * 样本元素
		 */
		public final static String SAMPLE_ELEMENTS = "SAMPLE_ELEMENTS";
		/**
		 * 手势类型
		 */
		public final static String HAND_TYPE = "HAND_TYPE";
		/**
		 * 位元类型
		 */
		public final static String BIT_TYPE = "BIT_TYPE";
		/**
		 * 开始位元
		 */
		public final static String START_BIT = "START_BIT";
		/**
		 * 样本元素出现顺序类型
		 */
		public final static String PRESENT_TYPE = "PRESENT_TYPE";
		/**
		 * 题目数
		 */
		public final static String QUESTION_COUNT = "QUESTION_COUNT";
		/**
		 * 样本元素出现的位置顺序
		 */
		public final static int SAMPLE_LOCATION = 180003;
		/**
		 * 样本元素出现顺序
		 */
		public final static int SAMPLE_ELEMENT = 180004;
	}

	/**
	 * 听觉触觉反应训练，UI页面需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class HearingTouchResponseTrainingUIParameter {

		/**
		 * 时间,取值30s, 60s, 90s, 120s, 180s
		 */
		public final static String TIME = "TIME";
		/**
		 * 乐器样本群编号：西洋乐、民族乐、打击乐
		 */
		public final static String SAMPLE_SET_MUSIC = "SAMPLE_SET_MUSIC";
		/**
		 * 乐器样本元素：钢琴、小提琴...
		 */
		public final static String SAMPLE_ELEMENTS_MUSIC = "SAMPLE_ELEMENTS_MUSIC";
		/**
		 * 音阶样本元素：1,2,3,4...
		 */
		public final static String SAMPLE_ELEMENTS_SCALE = "SAMPLE_ELEMENTS_SCALE";
		/**
		 * 手势类型
		 */
		public final static String HAND_TYPE = "HAND_TYPE";
		/**
		 * 测试类型：乐器测试、音阶测试
		 */
		public final static String TEST_TYPE = "TEST_TYPE";
		/**
		 * 食指对应的乐器，该乐器在SAMPLE_ELEMENTS_MUSIC必须存在
		 */
		public final static String MUSIC_FORE_FINGER = "MUSIC_FORE_FINGER";
		/**
		 * 中指对应的乐器，该乐器在SAMPLE_ELEMENTS_MUSIC必须存在
		 */
		public final static String MUSIC_MIDDLE_FINGER = "MUSIC_MIDDLE_FINGER";
		/**
		 * 无名指对应的乐器，该乐器在SAMPLE_ELEMENTS_MUSIC必须存在
		 */
		public final static String MUSIC_THIRD_FINGER = "MUSIC_THIRD_FINGER";
		/**
		 * 乐器
		 */
		public final static int MUSICAL_INSTRUMENT = 190001;
		/**
		 * 音阶
		 */
		public final static int SCALE = 190002;
	}

	/**
	 * 听觉触觉记忆训练，UI页面需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class HearingTouchMemoryTrainingUIParameter {
		/**
		 * 乐器样本群编号：西洋乐、民族乐、打击乐
		 */
		public final static String SAMPLE_SET_MUSIC = "SAMPLE_SET_MUSIC";
		/**
		 * 乐器样本元素：钢琴、小提琴...
		 */
		public final static String SAMPLE_ELEMENTS_MUSIC = "SAMPLE_ELEMENTS_MUSIC";
		/**
		 * 音阶样本元素：1,2,3,4...
		 */
		public final static String SAMPLE_ELEMENTS_SCALE = "SAMPLE_ELEMENTS_SCALE";
		/**
		 * 手势类型
		 */
		public final static String HAND_TYPE = "HAND_TYPE";
		/**
		 * 测试类型：乐器测试、音阶测试
		 */
		public final static String TEST_TYPE = "TEST_TYPE";
		/**
		 * 乐器
		 */
		public final static int MUSICAL_INSTRUMENT = HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT;
		/**
		 * 音阶
		 */
		public final static int SCALE = HearingTouchResponseTrainingUIParameter.SCALE;
		/**
		 * 题目数
		 */
		public final static String QUESTION_COUNT = "QUESTION_COUNT";
	}

	/**
	 * 视觉听觉触觉综合训练，UI页面需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public final class VhtIntegrationTrainingUIParameter {
		/**
		 * 时间,取值30s, 60s, 90s, 120s, 180s
		 */
		public final static String TIME = "TIME";
		/**
		 * 反应类型：原始键反应、对应键反应
		 */
		public final static String RESPONSE_TYPE = "RESPONSE_TYPE";
		/**
		 * 回应类型：反射回应、定点回应、替代回应
		 */
		public final static String ANSWER_TYPE = "ANSWER_TYPE";
		/**
		 * 原始键反应
		 */
		public final static int ORIGINAL_KEY_RESPONSE = VisioTouchResponseTrainingUIParameter.ORIGINAL_KEY_RESPONSE;
		/**
		 * 对应键反应
		 */
		public final static int OPPOSITE_KEY_RESPONSE = VisioTouchResponseTrainingUIParameter.OPPOSITE_KEY_RESPONSE;
		/**
		 * 反射回应
		 */
		public final static int REFLECT_ANSWER = VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER;
		/**
		 * 定点回应
		 */
		public final static int FIXED_POINT_ANSWER = VisioTouchResponseTrainingUIParameter.FIXED_POINT_ANSWER;
		/**
		 * 替代回应
		 */
		public final static int INSTEAD_ANSWER = VisioTouchResponseTrainingUIParameter.INSTEAD_ANSWER;
		/**
		 * 针对视觉触觉的样本群
		 */
		public final static String SAMPLE_SET_VISION = "SAMPLE_SET_VISION";
		/**
		 * 针对视觉触觉的样本元素
		 */
		public final static String SAMPLE_ELEMENTS_VISION = "SAMPLE_ELEMENTS_VISION";
		/**
		 * 针对听觉触觉的的乐器样本群
		 */
		public final static String SAMPLE_SET_HEARING_INSTRUMENT = "SAMPLE_SET_HEARING_INSTRUMENT";
		/**
		 * 针对听觉触觉的的乐器样本元素
		 */
		public final static String SAMPLE_ELEMENTS_HEARING_INSTRUMENT = "SAMPLE_ELEMENTS_HEARING_INSTRUMENT";
		/**
		 * 针对听觉触觉的音阶样本元素
		 */
		public final static String SAMPLE_ELEMENTS_HEARING_SCALE = "SAMPLE_ELEMENTS_HEARING_SCALE";
		/**
		 * 手势类型
		 */
		public final static String HAND_TYPE = "HAND_TYPE";
		/**
		 * 测试类型：乐器测试、音阶测试
		 */
		public final static String TEST_TYPE = "TEST_TYPE";
		/**
		 * 乐器
		 */
		public final static int MUSICAL_INSTRUMENT = HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT;
		/**
		 * 音阶
		 */
		public final static int SCALE = HearingTouchResponseTrainingUIParameter.SCALE;
	}

	/**
	 * 音阶等级
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public final class ScaleLevel {
		/**
		 * 低音
		 */
		public final static int SCALE_LOW = 200001;
		/**
		 * 中音
		 */
		public final static int SCALE_MIDDLE = 200002;
		/**
		 * 高音
		 */
		public final static int SCALE_HIGH = 200003;
	}

	/**
	 * 显示形体
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public final static class DisplayBody {
		/**
		 * 点状
		 */
		public static final int POINT = 220001;
		/**
		 * 直线
		 */
		public static final int LINE = 220002;
		/**
		 * 曲线
		 */
		public static final int CURVE = 220003;
		/**
		 * 形状
		 */
		public static final int SHAPES = 220004;

		/**
		 * 图样
		 * 
		 * @author ZhangGuoYin
		 * 
		 */
		public class TuYang {
			/**
			 * 直线图样
			 */
			public static final int LINE = 22000501;
			/**
			 * 曲线图样
			 */
			public static final int CURVE = 22000502;
			/**
			 * 圆形图样
			 */
			public final static int CIRCLE = 22000503;
			/**
			 * 三角形图样
			 */
			public final static int TRIGON = 22000504;
			/**
			 * 正方形图样
			 */
			public final static int SQUARE = 22000505;
			/**
			 * 六边形图样
			 */
			public final static int HEXAGON = 22000506;
			/**
			 * 长方形图样
			 */
			public final static int RECTANGLE = 22000507;
			/**
			 * 扇形图样
			 */
			public final static int SECTOR = 22000508;
			/**
			 * 平行四边形图样
			 */
			public final static int PARALLELOGRAM = 22000509;
			/**
			 * 菱形图样
			 */
			public final static int DIAMOND = 22000510;
			/**
			 * 五边形图样
			 */
			public final static int PENTAGON = 22000511;
		}

		public class TuAn {

		}
	}

	/**
	 * 位元类型
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static final class BitType {
		/**
		 * 给定位元
		 */
		public final static int GIVEN_BIT = -1;
		/**
		 * 连续位元
		 */
		public final static int CONTINUED_BIT = -2;
	}

	/**
	 * 显示模式
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class DisplayMode {
		/**
		 * 显示后保留
		 */
		public static final int SHOW_HOLD = 230001;
		/**
		 * 显示后消失
		 */
		public static final int SHOW_DISAPPEAR = 230002;
		/**
		 * 同时显示同步消失
		 */
		public static final int SHOW_DISAPPEAR_TOGETHER = 230003;
	}

	/**
	 * 显示顺序
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class DisplayOrder {
		/**
		 * 依序号
		 */
		public static final int ORDER_BY_INDEX = 240001;
		/**
		 * 不依序号
		 */
		public static final int NOT_ORDER_BY_INDEX = 240002;
	}

	/**
	 * 颜色反应训练需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class ColorResponseTrainingUIParameter {
		/**
		 * 样本群
		 */
		public final static String SAMPLE_SET = "SAMPLE_SET";
		/**
		 * 样本元素
		 */
		public final static String SAMPLE_ELEMENTS = "SAMPLE_ELEMENTS";
		/**
		 * 显示形体
		 */
		public final static String DISPLAY_BODY = "DISPLAY_BODY";
		/**
		 * 题目数
		 */
		public final static String QUESTION_COUNT = "QUESTION_COUNT";
	}

	/**
	 * 颜色记忆训练需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public class ColorMemoryTrainingUIParameter {

		/**
		 * 样本群
		 */
		public final static String SAMPLE_SET = "SAMPLE_SET";
		/**
		 * 样本元素
		 */
		public final static String SAMPLE_ELEMENTS = "SAMPLE_ELEMENTS";
		/**
		 * 位元类型
		 */
		public final static String BIT_TYPE = "BIT_TYPE";
		/**
		 * 开始位元
		 */
		public final static String START_BIT = "START_BIT";
		/**
		 * 显示形体
		 */
		public final static String DISPLAY_BODY = "DISPLAY_BODY";
		/**
		 * 题目数
		 */
		public final static String QUESTION_COUNT = "QUESTION_COUNT";
		/**
		 * 显示方式
		 */
		public final static String DISPLAY_MODE = "DISPLAY_MODE";
		/**
		 * 显示顺序
		 */
		public final static String DISPLAY_ORDER = "DISPLAY_ORDER";
	}

	/**
	 * 符号反应训练需要传递的参数
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-6 下午07:17:24
	 */
	public static final class SymbolTraingParam {

		public static class Key {
			/**
			 * 题目个数(不能为空)
			 */
			public static final String QUESTION_COUNT = "QUESTION_COUNT";
			/**
			 * 选定的样本群(不能为空)
			 */
			public static final String SAMPLE_SET = "SAMPLE_SET";
			/**
			 * 选定的样本元元素
			 * 
			 * @see #SAMPLE_ELEMENT_RANDDOM
			 * @see #SAMPLE_ELEMENT_SETTING
			 */
			public static final String SAMPLE_ELEMENT = "SAMPLE_ELEMENT";

			/**
			 * 位元数设定类型
			 * 
			 * @see #UNIT_COUNT_SETTING_DEFAULT
			 * @see #UNIT_COUNT_SETTING_SEQENTIAL
			 * @see #UNIT_COUNT_SETTING_MANUAL
			 */
			public static final String UNIT_COUNT_SETTING = "UNIT_COUNT_SETTING";

			/**
			 * 如果位元数设定类型为SymbolTraingParam.UNIT_COUNT_SETTING_MANUAL，
			 * 则该项指定位元的具体位数
			 */
			public static final String UNIT_COUNT = "UNIT_COUNT";

			/**
			 * 显示方式
			 * 
			 * @see #DISPLAY_SETTING_COMMON
			 * @see #DISPLAY_SETTING_PATTERN_SEQUENTIAL
			 * @see #DISPLAY_SETTING_PATTERN_RANDOM
			 */
			public static final String DISPLAY_SETTING = "DISPLAY_SETTING";
			/**
			 * 使用图样显示时指定的图样
			 * 
			 * @see #DISPLAY_PATTERN_CIRCULAR
			 */
			public static final String DISPLAY_PATTERN = "DISPLAY_PATTERN";

			/**
			 * 题目出现的方式
			 * 
			 * @see #ANIMATION_SETTING_SAME_TIME_FADEIN_FADEOUT
			 * @see #ANIMATION_SETTING_SINGLE_FADEIN_FADEOUT
			 * @see #ANIMATION_SETTING_SINGLE_FADEIN_SAME_FADEOUT
			 */
			public static final String ANIMATION_SETTING = "ANIMATION_SETTING";
		}

		// ====================================================
		public static final int SAMPLE_ELEMENT_RANDOM = -1;// 随机样本元素
		public static final int SAMPLE_ELEMENT_SETTING = -2;// 设定样本元素

		// ===============================================
		public static final int DISPLAY_SETTING_COMMON = 1;// 一般显示
		public static final int DISPLAY_SETTING_PATTERN_SEQUENTIAL = 2;// 按照图样显示(按顺序)
		public static final int DISPLAY_SETTING_PATTERN_RANDOM = 3;// 按照图样显示（不按顺序）
		// ================================================
		public static final int DISPLAY_PATTERN_CIRCULAR = 1;// 圆形图样
		public static final int DISPLAY_PATTERN_TRIANGLE = 2;// 三角形图样
		public static final int DISPLAY_PATTERN_SQUARE = 3;// 正方形
		public static final int DISPLAY_PATTERN_HEXAGON = 4;// 六边形
		public static final int DISPLAY_PATTERN_RECTANGLE = 5;// 长方形图样
		public static final int DISPLAY_PATTERN_SECTOR = 6;// 扇形
		public static final int DISPLAY_PATTERN_PARALLELOGRAM = 7;// 平行四边形
		public static final int DISPLAY_PATTERN_DIAMOND = 8;// 菱形
		public static final int DISPLAY_PATTERN_PENTAGON = 9;// 菱形

		// ==================================================
		public static final int UNIT_COUNT_SETTING_DEFAULT = -2;// 默认位元数
		public static final int UNIT_COUNT_SETTING_SEQENTIAL = -1;// 连续位元数
		public static final int UNIT_COUNT_SETTING_MANUAL = 0;// 自己设定位元数

		// ====================================================
		public static final int ANIMATION_SETTING_SAME_TIME_FADEIN_FADEOUT = 1;// 同时显示同时消失
		public static final int ANIMATION_SETTING_SINGLE_FADEIN_FADEOUT = 2;// 单个显示/消失
		public static final int ANIMATION_SETTING_SINGLE_FADEIN_SAME_FADEOUT = 3;// 单个显示，同时消失

	}

	/**
	 * 空间位置记忆训练需要传递的参数
	 * 
	 * @author ZhangGuoYin
	 * 
	 */
	public static final class SpatialMemoryTrainingUIParameter {

		/**
		 * 样本群
		 */
		public final static String SAMPLE_SET = "SAMPLE_SET";
		/**
		 * 样本元素
		 */
		public final static String SAMPLE_ELEMENTS = "SAMPLE_ELEMENTS";
		/**
		 * 位元类型
		 */
		public final static String BIT_TYPE = "BIT_TYPE";
		/**
		 * 开始位元
		 */
		public final static String START_BIT = "START_BIT";
		/**
		 * 题目数
		 */
		public final static String QUESTION_COUNT = "QUESTION_COUNT";
		/**
		 * 显示方式
		 */
		public final static String DISPLAY_MODE = "DISPLAY_MODE";
		/**
		 * 回应类型
		 */
		public final static String ANSWER_TYPE = "ANSWER_TYPE";
		/**
		 * 顺序回应
		 */
		public final static int ORDER_ANSWER = 260011;
		/**
		 * 指定回应
		 */
		public final static int SPECIFY_ANSWER = 260012;
		/**
		 * 方位回应
		 */
		public final static int ORIENTATION_ANSWER = 260013;
		/**
		 * 区域回应
		 */
		public final static int REGION_ANSWER = 260014;
		/**
		 * 区域
		 */
		public final static String REGION = "REGION";
		/**
		 * 区域A
		 */
		public final static int A_REGION = 1;
		/**
		 * 区域B
		 */
		public final static int B_REGION = 2;
		/**
		 * 区域C
		 */
		public final static int C_REGION = 3;
		/**
		 * 区域D
		 */
		public final static int D_REGION = 4;
	}
}
