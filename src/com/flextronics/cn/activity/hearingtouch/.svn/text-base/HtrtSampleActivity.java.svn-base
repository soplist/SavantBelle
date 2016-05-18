package com.flextronics.cn.activity.hearingtouch;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtAnswer;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtParameter;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtQuestion;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtResult;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtRule;
import com.flextronics.cn.service.hearingtouch.HtrtQuestionAnswerService;
import com.flextronics.cn.service.hearingtouch.HtrtQuestionAnswerServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.MusicPlayTools;
import com.flextronics.cn.util.MyCountDownTimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>A-视觉听觉触觉综合训练</b><br>
 * 听觉触觉反应训练--按键操作
 * 
 * @author ZhangGuoYin
 *
 */
public class HtrtSampleActivity extends BaseActivity {

	private final static String TAG = "HtrtSampleActivity";

	/**
	 * 计时显示器
	 */
	private TimerView timerView;
	/**
	 * 答题状态灯
	 */
	private StartFlagImageView startFlagImageView;

	/**
	 * 题目
	 */
	private HtrtQuestion question;
	/**
	 * 服务
	 */
	private HtrtQuestionAnswerService service;
	/**
	 * 播放题目音的播放器
	 */
	private MediaPlayer mp = new MediaPlayer();
	/**
	 * 播放答题错误音的播放器
	 */
	private MediaPlayer mediaPlayer;

	/**
	 * 测试时间
	 */
	private int time;
	/**
	 * 测试类型：乐器测试或者音阶测试
	 */
	private int testType;
	/**
	 * 手类型：左手或右手
	 */
	private int handType;
	/**
	 * 乐器样本
	 */
	private int sampleSetMusic;
	/**
	 * 乐器样本元素
	 */
	private String sampleElementsMusic;
	/**
	 * 音阶样本元素
	 */
	private String sampleElementsScale;
	/**
	 * 食指对应的乐器
	 */
	private int musicForeFinger;
	/**
	 * 中指对应的乐器
	 */
	private int musicMiddleFinger;
	/**
	 * 无名指对应的乐器
	 */
	private int musicThirdFinger;

	/**
	 * 第一次
	 */
	private boolean firstTime = true;
	/**
	 * 歌曲第一次播放标志位
	 */
	private boolean firstTimePlay = false;
	/**
	 * 可以播放标志位
	 */
	private boolean canPlay = true;
	/**
	 * 需要重复标志位
	 */
	private boolean needRepeat = false;
	/**
	 * 服务停止标志位
	 */
	private boolean isServiceStoped;
	private MyCountDownTimer repeatPlayTimer;

	private Bundle bundle ;

	/**
	 * 整场测试的倒计时计时器
	 */
	private MyCountDownTimer countDownTimer4StopAnswer;

	/**
	 * 每小题倒计时器，时间为5000ms. 时间范围内若没有作答，即算超时，计错误一次
	 */
	private MyCountDownTimer countDownTimer = new MyCountDownTimer(
			Constants.COUNT_DOWN_TIME, Constants.COUNT_DOWN_TIME){

		@Override
		public void onFinish() {
			if (isServiceStoped) {
				cancels();
				return;
			}
			Log.d(TAG, "time up");
			answerQuestionError(R.string.time_out);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_hearing_touch_reponse_training_keystoke中描述的布局添加到父布局中
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_hearing_touch_response_training_keystoke, null), getBaseLayoutParams());
		//设置为将要显示的布局
		setContentView(layout);
		
		this.setupViews();
		this.setupListeners();

		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足接口规范************/
		bundle = this.getIntent().getExtras();		

		time = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.TIME, 
				Constants.DEFAULT_RESPONSE_TRAINING_TIME);
		testType = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.TEST_TYPE);
		handType = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.HAND_TYPE);
		sampleSetMusic = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_SET_MUSIC);
		sampleElementsMusic = bundle.getString(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS_MUSIC);
		sampleElementsScale = bundle.getString(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS_SCALE);
		musicForeFinger = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_FORE_FINGER);
		musicMiddleFinger = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_MIDDLE_FINGER);
		musicThirdFinger = bundle.getInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_THIRD_FINGER);
		
		//对于乐器测试，如果是打击乐，则音阶只为1
		if(testType==Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT && 
				sampleSetMusic==Constants.Sample.PERCUSSION_INSTRUMENT){
			sampleElementsScale = "1";
		}

		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------------receive parameters from UI----------------");
		Log.d(TAG, "time:                " + time);
		Log.d(TAG, "testType:            " + testType);
		Log.d(TAG, "handType:            " + handType);
		Log.d(TAG, "sampleSetMusic:      " + sampleSetMusic);
		Log.d(TAG, "sampleElementsMusic: " + sampleElementsMusic);
		Log.d(TAG, "sampleElementsScale: " + sampleElementsScale);
		Log.d(TAG, "musicForeFinger:     " + musicForeFinger);
		Log.d(TAG, "musicMiddleFinger:   " + musicMiddleFinger);
		Log.d(TAG, "musicThirdFinger:    " + musicThirdFinger);

		//分离出样本信息
		String[] sampleElementsMusicArray = sampleElementsMusic.split(",");
		String[] sampleElementsScaleArray = sampleElementsScale.split(",");

		/********************为听觉触觉反应训练服务准备参数 ********************/
		//创建乐器样本对象
		ChoosedSample music = new ChoosedSample();
		//music.setSample(Constants.Sample.FOREIGH_MUSIC);
		music.setSmapleElementChoosed(sampleElementsMusicArray);

		//创建音阶样本对象
		ChoosedSample scale = new ChoosedSample();
		scale.setSmapleElementChoosed(sampleElementsScaleArray);
		
		//乐器跟按键的对应关系
		Map<Integer, Integer> musicInstrumentKeypadMap = new HashMap<Integer, Integer>();
		musicInstrumentKeypadMap.put(musicForeFinger, Constants.FORE_FINGER);
		musicInstrumentKeypadMap.put(musicMiddleFinger, Constants.MIDDLE_FINGER);
		musicInstrumentKeypadMap.put(musicThirdFinger, Constants.THIRD_FINGER);
		//高中音与按键的对应关系
		Map<Integer, Integer> scaleKeypadMap = new HashMap<Integer, Integer>();
		scaleKeypadMap.put(Constants.ScaleLevel.SCALE_LOW, Constants.THIRD_FINGER);
		scaleKeypadMap.put(Constants.ScaleLevel.SCALE_MIDDLE, Constants.MIDDLE_FINGER);
		scaleKeypadMap.put(Constants.ScaleLevel.SCALE_HIGH, Constants.FORE_FINGER);

		//创建参数对象
		HtrtParameter htrtParameter = new HtrtParameter();		
		//htrtParameter.setBit(4);
		htrtParameter.setHandType(handType);
		htrtParameter.setMusicType(sampleSetMusic);
		htrtParameter.setTestType(testType);
		htrtParameter.setMusic(music);
		htrtParameter.setScale(scale);
		htrtParameter.setMusicInstrumentKeypadMap(musicInstrumentKeypadMap);
		htrtParameter.setScaleKeypadMap(scaleKeypadMap);

		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(Constants.PARAMETER, htrtParameter);
		//规则
		HtrtRule htrtRule = new HtrtRule();
		htrtRule.setScore(2);
		parameters.put(Constants.RULE, htrtRule);
		//当前CONTEXT
		parameters.put(Constants.CONTEXT, HtrtSampleActivity.this);
		/****************************参数准备完毕********************************/

		//实例化听觉触觉问答服务对象
		service = new HtrtQuestionAnswerServiceImpl();
		//根据参数初始化服务
		try {
			service.init(parameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		
		timerView.setEnd(time);
		timerView.init();
		
		//根据测试时间创建倒计时, 时间一到停止答题
		countDownTimer4StopAnswer = new MyCountDownTimer(time, time){

			@Override
			public void onFinish() {
				//停止答题
				stopAnswerAction();
				timerView.stop();
			}

			@Override
			public void onTick(long millisUntilFinished) {;
			}
		};
		
		//初始化答题状态灯
		startFlagImageView.init();
		startFlagImageView.setOnTimeUpListener(new StartFlagImageView.OnTimeUpListener(){

			public void onTimeUp() {

				timerView.start();
				service.start();
				//创建题目
				createQuestionAction();
				countDownTimer4StopAnswer.starts();
			}
		});
		
		//答题错误提示音
		mediaPlayer = MediaPlayer.create(HtrtSampleActivity.this, R.raw.error);
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.d(TAG, "PlayErrorMusic onError()");
				mediaPlayer.release();
				return false;
			}
		});

		//答题状态灯开启
		startFlagImageView.start();
	}
	
	
	/**
	 * 设置相关控件
	 */
	private void setupViews(){

		//设置标题
		setTrainingTitle(getString(R.string.hearing_touch_response_training_key));
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		//显示后退按钮
		setBackButtonEnable(true);
		//显示主页按钮
		setHomeButtonEnable(true);
		
		//倒计时时钟
		timerView = (TimerView)findViewById(R.id.TimerView01);
		//训练答题状态灯
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView01);
	}

	
	/**
	 * 为相关控件设置监听器
	 */
	private void setupListeners(){
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					//答题计时器暂停
					countDownTimer.pause();
					countDownTimer4StopAnswer.pause();
					//显示时钟暂停
					timerView.pause();
					needRepeat = false;
					mp.stop();
					
					new AlertDialog.Builder(HtrtSampleActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							countDownTimer4StopAnswer.cancels();
							isServiceStoped = true;
							startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//答题计时器恢复
							countDownTimer.resume();
							countDownTimer4StopAnswer.resume();
							//显示时钟恢复
							timerView.resume();
							
							needRepeat = true;
							try {
								mp.prepare();
							} catch (IllegalStateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}									
					}).show();
				}
				return false;
			}
		});
		
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){
	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					//答题计时器暂停
					countDownTimer.pause();
					countDownTimer4StopAnswer.pause();
					//显示时钟暂停
					timerView.pause();
					needRepeat = false;
					mp.stop();
					
					new AlertDialog.Builder(HtrtSampleActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							countDownTimer4StopAnswer.cancels();
							isServiceStoped = true;
							if (testType == Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT){
								startActivity(new Intent(getApplicationContext(), ChooseParams4HtrtMusicActivity.class));
							}else if (testType == Constants.HearingTouchResponseTrainingUIParameter.SCALE) {
								startActivity(new Intent(getApplicationContext(), ChooseParams4HtrtScaleActivity.class));
							}
							
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//答题计时器恢复
							countDownTimer.resume();
							countDownTimer4StopAnswer.resume();
							//显示时钟恢复
							timerView.resume();
							
							needRepeat = true;
							try {
								mp.prepare();
							} catch (IllegalStateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}									
					}).show();
				}
				return false;
			}
		});
		
		//播放器准备完毕后，1s后开始播放
		mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

			public void onPrepared(final MediaPlayer mp) {
				
				repeatPlayTimer = new MyCountDownTimer(1000, 1000){

					@Override
					public void onFinish() {
						mp.start();
					}

					@Override
					public void onTick(long millisUntilFinished) {
					}
					
				};
				repeatPlayTimer.starts();
			}			
		});
		
		//播放器播放完毕后，如果需要重复，就重复播放
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

			public void onCompletion(MediaPlayer arg0) {
					
				//如果此歌曲是第一遍播放,则播放完毕后启动答题
				if (firstTimePlay) {
					//开始答题
					startAnswerAction();
				}
				firstTimePlay = false;
				
				//如果此歌曲需要重复,则重复准备播放
				if (needRepeat) {
					//创建音乐播放工具
					MusicPlayTools musicPlayTools = new MusicPlayTools();
					musicPlayTools.prepareQuestionMusic(HtrtSampleActivity.this, 
							mp, question, canPlay, question.getContents()[0]);
				}	
			}
		});

		//当播放器出现错误时进行reset
		mp.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				mp.reset();
				return false;
			}			
		});
	}


	/**
	 * 创建题目的相关动作
	 */
	private void createQuestionAction(){

		//如果服务已经停止
		if (isServiceStoped) {
			Log.i(TAG, "the service is stoped");
			return;
		}
		Log.i(TAG, "starting create question...");

		try {
			question = service.createQuestion();
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		} catch (ParameterIsInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		//设置播放的相关标志位
		needRepeat = true;
		firstTimePlay = true;
		
		MusicPlayTools musicPlayTools = new MusicPlayTools();
		musicPlayTools.prepareQuestionMusic(HtrtSampleActivity.this, 
				mp, question, canPlay, question.getContents()[0]);		
	}
	
	
	/**
	 * 开始答题的相关动作
	 */
	private void startAnswerAction(){
		
		Log.i(TAG, "starting answer question...");
		
		countDownTimer.cancels();
		service.startAnswer();
		countDownTimer.starts();		
	}
	
	
	/**
	 * 停止答题的相关动作
	 */
	private void stopAnswerAction(){
		
		Log.i(TAG, "stopping answer question...");

		//修改服务停止标志位
		isServiceStoped = true;
		countDownTimer.cancels();
		//修改播放器相关标志位
		canPlay = false;
		needRepeat = false;
		firstTimePlay = false;

		Log.d(TAG, "STOP_ANSWER HTRT, mp.isPlaying(): " + mp.isPlaying());
		//如果播放器正在播放，停止之
		if (mp.isPlaying()) {
			mp.stop();
			mp.reset();
		}
		//服务停止
		service.stop();
		//生成测试报告并显示
		showTestReport(service.generateReport());
	}
	
	
	/**键盘按下时的逻辑动作
	 * 
	 * @param answerCode  编码
	 * @return
	 */
	private void onKeyDownAction(final int answerCode){
		if (isServiceStoped) {
			return;
		}
		Log.d(TAG, "answerCode: " + answerCode);
		
		if (handType == Constants.RIGHT_HAND) {//如果是右手, _answerCode不能小于0
			if (answerCode < 0) {
				return;
			}
		}else if (handType == Constants.LEFT_HAND) {//如果是左手, _answerCode不能大于0
			if (answerCode > 0) {
				return;
			}
		}
		
		if (question != null) {
			//如果状态为可答题
			if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
				//取消答题倒计时器
				countDownTimer.cancels();
				
				//创建答题对象
				HtrtAnswer answer = new HtrtAnswer();
				answer.setQuestionId(question.getId());
				answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
				answer.setAnswers(new String[]{String.valueOf(answerCode)});

				//答题并返回结果
				HtrtResult testResult = service.answerQuestion(question, answer);
				if (testResult.getValue()) {//如果回答正确
					//修改播放器相关标志位, 如果在播放则停止
					needRepeat = false;
					if (mp.isPlaying()) {
						mp.stop();
					}
					//创建题目
					createQuestionAction();
				}else {//如果回答错误
					
					playErrorMusic();
				}
				//倒计时重新开始
				countDownTimer.starts();
			}else {
				//过早反应
				answerQuestionError(R.string.premature);
			}	
			
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown");
		
		// 禁用键盘上的HOME,后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_1://键盘上的1键代表左手无名指
			onKeyDownAction(Constants.KeyStoke.THIRD_FINGER_LEFT);
			break;
		case KeyEvent.KEYCODE_2://键盘上的2键代表左手中指
			onKeyDownAction(Constants.KeyStoke.MIDDLE_FINGER_LEFT);
			break;
		case KeyEvent.KEYCODE_3://键盘上的3键代表左手食指
			onKeyDownAction(Constants.KeyStoke.FORE_FINGER_LEFT);
			break;
		case KeyEvent.KEYCODE_4://键盘上的4键代表右手食指
			onKeyDownAction(Constants.KeyStoke.FORE_FINGER_RIGHT);			
			break;
		case KeyEvent.KEYCODE_5://键盘上的5键代表右手中指
			onKeyDownAction(Constants.KeyStoke.MIDDLE_FINGER_RIGHT);			
			break;
		case KeyEvent.KEYCODE_6://键盘上的6键代表右手无名指
			onKeyDownAction(Constants.KeyStoke.THIRD_FINGER_RIGHT);			
			break;

		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 显示测试报告
	 * @param testReport
	 */
	private void showTestReport(final HtrtReport testReport){
				
		if (mp.isPlaying()) {
			mp.stop();
		}

		new AlertDialog.Builder(HtrtSampleActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(HtrtSampleActivity.this, TestReportActivity.class);
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, HtrtSampleActivity.class.getName());				
				if (testType == Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT){
					bundle.putString(Constants.PREVIOUS_ACTIVITY, ChooseParams4HtrtMusicActivity.class.getName());
				}else if (testType == Constants.HearingTouchResponseTrainingUIParameter.SCALE) {
					bundle.putString(Constants.PREVIOUS_ACTIVITY, ChooseParams4HtrtScaleActivity.class.getName());
				}				
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		})
		.show();
	}


	@Override
	protected void onPause() {

		super.onPause();
		Log.d(TAG, "onPause");

		countDownTimer.pause();
		countDownTimer4StopAnswer.pause();
		timerView.pause();		
	}

	@Override
	protected void onResume() {

		super.onResume();
		Log.d(TAG, "onResume");
		
		if (!firstTime) {
			countDownTimer.resume();
			countDownTimer4StopAnswer.resume();
			timerView.resume();
		}else {
			firstTime = false;
		}
	}

	@Override
	protected void onStop() {

		super.onStop();
		Log.d(TAG, "onStop");

		//canPlay = false;
		countDownTimer.cancels();
		countDownTimer4StopAnswer.cancels();
		//	timerView.stop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy()");
		mp.reset();
		super.onDestroy();
	}
	
	/**
	 * 答错题目
	 * @param res
	 */
	private void answerQuestionError(int res){
		answerQuestionError(getString(res));
	}
	
	private void answerQuestionError(String errInfo){
		countDownTimer.cancels();
		if (question != null) {
			service.answerQuestion(question, null);
			Toast.makeText(HtrtSampleActivity.this, errInfo, Toast.LENGTH_SHORT).show();
			playErrorMusic();
		}
		if (!isServiceStoped) {
			countDownTimer.starts();
		}
	}

	private void playErrorMusic(){		
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(HtrtSampleActivity.this, R.raw.error);
		mediaPlayer.start();
	}	
}