package com.flextronics.cn.activity.visionhearingtouch;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtAnswer;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtParameter;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtQuestion;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtResult;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtRule;
import com.flextronics.cn.model.visionhearingtouch.VHTReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtAnswer;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtParameter;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtQuestion;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtResult;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtRule;
import com.flextronics.cn.service.hearingtouch.HtrtQuestionAnswerService;
import com.flextronics.cn.service.hearingtouch.VHThtrtQuestionAnswerServiceImpl;
import com.flextronics.cn.service.visiontouch.VtrtQuestionAnswerService;
import com.flextronics.cn.service.visiontouch.VtrtQuestionAnswerServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.MusicPlayTools;
import com.flextronics.cn.util.MyCountDownTimer;
import com.flextronics.cn.util.TouchScreenOnTouchListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>A-视觉听觉触觉综合训练</b><br>
 * 视觉听觉触觉 整合训练
 * 
 * @author ZhangGuoYin
 *
 */
public class VhtIntegrationOperationActivity extends BaseActivity {

	private final static String TAG = "VhtIntegrationOperationActivity";
	
	/**
	 * 测试时间
	 */
	private int time;
	/**
	 * 反应类型：原始键反应、对应键反应
	 */
	private int responseType;
	/**
	 * 回应类型：反射回应、定点回应、替代回应
	 */
	private int answerType;
	/**
	 * 视觉触觉 样本群
	 */
	private String sampleSetVision;
	/**
	 * 视觉触觉 样本元素
	 */
	private String sampleElementsVision;
	/**
	 * 听觉触觉 乐器样本群
	 */
	private String sampleSetHearingInstrument;
	/**
	 * 听觉触觉 乐器样本元素
	 */
	private String sampleElementsHearingInstrument;
	/**
	 * 听觉触觉 音阶样本元素
	 */
	private String sampleElementsHearingScale;
	private int bit;
	/**
	 * 测试类型
	 */
	private int testType;
	/**
	 * 手类型
	 */
	//private int handType;
	//闪动图片的7个image view
	private ImageView imageViewTouch1;
	private ImageView imageViewTouch2;
	private ImageView imageViewTouch3;
	private ImageView imageViewTouch4;
	private ImageView imageViewTouch5;
	private ImageView imageViewTouch6;
	private ImageView imageViewTouch7;

	/**
	 * 倒计时显示器
	 */
	private TimerView timerView;
	/**
	 * 状态灯
	 */
	private StartFlagImageView startFlagImageView;
	private ImageView[] _7ImageViews;
	private int[] _7TouchImageRes = new int[]{R.drawable.touch1, R.drawable.touch2, 
			R.drawable.touch3, R.drawable.touch4, R.drawable.touch5, R.drawable.touch6, 
			R.drawable.touch7};
	
	/**
	 * 将要闪动图片的image view
	 */
	private ImageView toFlashImageView;
	/**
	 * 原始图片资源
	 */
	private int oldImageRes;
	/**
	 * 将要闪动的图片资源
	 */
	private int toFlashImageRes;

	private Bitmap toFlashBitmap;
	private Bitmap oldBitmap;
	
	/**
	 * 答题错误播放器
	 */
	private MediaPlayer mediaPlayer;
	/**
	 * 播放听觉触觉题目的播放器
	 */
	MediaPlayer mp = new MediaPlayer();

	private MyCountDownTimer countDownTimer4StopAnswer;
	private MyCountDownTimer repeatPlayTimer;
		
	private boolean isReplaced = false;
	private boolean isFlash = false;
	private boolean firstTime = true;
	private boolean firstTimePlay = false;
	private boolean needRepeat = false;
	private boolean canPlay = true;
	private boolean isVtrtServiceStoped;
	private boolean isHtrtServiceStoped;

	private VtrtQuestion vtrtQuestion;
	private VtrtReport vtrtTestReport;
	private VtrtQuestionAnswerService vtrtService;

	private HtrtQuestion htrtQuestion;
	private HtrtReport htrtTestReport;
	private HtrtQuestionAnswerService htrtService;
	private VHTReport testReport;
	
	private Bundle bundle;

	private Handler vtrtHandler = new Handler();
	private Handler showTestReportHandler = new Handler(){

		@Override
		public void handleMessage(final Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			new AlertDialog.Builder(VhtIntegrationOperationActivity.this)
			.setTitle(R.string.tips)
			.setMessage(R.string.test_over)
			.setCancelable(false)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {

					Intent intent = new Intent(VhtIntegrationOperationActivity.this, VhtIntegrationTrainingTestReportActivity.class);
					bundle.putSerializable(Constants.TEST_REPORT, testReport);
					bundle.putString(Constants.CLASS_NAME, VhtIntegrationOperationActivity.class.getName());
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			})
			.show();		
		}		
	};
	
	/**
	 * 闪动图像
	 */
	private Runnable flash = new Runnable(){

		public void run() {
			if (isFlash) {
				if (isReplaced) {
					if (oldBitmap!=null && !oldBitmap.isRecycled()) {
						oldBitmap.recycle();
						oldBitmap=null;
					}
					toFlashBitmap = BitmapFactory.decodeResource(getResources(), toFlashImageRes);
					toFlashImageView.setImageBitmap(toFlashBitmap);	
				}else {
					if (toFlashBitmap!=null && !toFlashBitmap.isRecycled()) {
						toFlashBitmap.recycle();
						toFlashBitmap=null;
					}
					oldBitmap = BitmapFactory.decodeResource(getResources(), oldImageRes);
					toFlashImageView.setImageBitmap(oldBitmap);
				}
				isReplaced = !isReplaced;
			}
			
			vtrtHandler.postDelayed(this, Constants.FLASH_TIME);
		}
	};
	
	/**
	 * 创建题目的相关动作
	 */
	private void createQuestionAction(){
		createVtrtQuestion();
		createHtrtQuestion();
		startAnswerVtrt();
		startAnswerHtrt();
	}	
	
	/**
	 * 停止答题的相关动作
	 */
	private void stopAnswerAction(){

		
		stopAnswerVtrt();
		stopAnswerHtrt();
						
		//等待视觉触觉+听觉触觉服务完全停止后，即可显示测试报告
		Thread showTestReportThread = new Thread(){

			@Override
			public void run() {						
				super.run();
				//waiting for both vtrtService and htrtService have stopped
				while ((!isVtrtServiceStoped) || (!isHtrtServiceStoped)) {
					try {
						sleep(500);
						Log.d(TAG, "current:-----------" + System.currentTimeMillis());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				testReport = new VHTReport();
				testReport.setHtrtReport(htrtTestReport);
				testReport.setVtrtReport(vtrtTestReport);				
				showTestReportHandler.sendEmptyMessage(0);
			}					
		};
		showTestReportThread.start();
	}
	
	
	/**************************************视觉 触觉 部分************************************/	
	/**
	 * 听觉触觉每小题倒计时器，时间为5000ms. 时间范围内若没有作答，即算超时，计错误一次
	 */
	private MyCountDownTimer vtrtCountDownTimer = new MyCountDownTimer(
			Constants.COUNT_DOWN_TIME, Constants.COUNT_DOWN_TIME){

		@Override
		public void onFinish() {
			if (isVtrtServiceStoped) {
				cancels();
				return;
			}
			Log.d(TAG, "time up");
			answerVtrtQuestionError(getString(R.string.vtrtTimeOut));
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	};
		
	/**
	 * 创建视觉触觉题目
	 */
	private void createVtrtQuestion(){

		if (isVtrtServiceStoped) {
			return;
		}
		Log.i(TAG, "starting create vtrt question...");
		
		try {
			vtrtQuestion = vtrtService.createQuestion();
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		} catch (ParameterIsInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		//根据题目确定闪动的图片
		toFlashImageView(vtrtQuestion);
		isFlash = true;
		vtrtHandler.post(flash);
	}	
	
	/**
	 * 开始回答视觉触觉题目
	 */
	private void startAnswerVtrt(){
		Log.i(TAG, "starting answer question...");
		
		vtrtCountDownTimer.cancels();
		vtrtService.startAnswer();
		vtrtCountDownTimer.starts();
	}
	
	/**
	 * 停止回答视觉触觉题目
	 */
	private void stopAnswerVtrt(){
		
		Log.i(TAG, "stopping answer vtrt question...");

		vtrtCountDownTimer.cancels();

		//移除flash
		vtrtHandler.removeCallbacks(flash);
		//设置闪动标志位为false
		isFlash = false;
		//图片还原
		toFlashImageView.setImageResource(oldImageRes);	
		//服务停止			
		vtrtService.stop();
		//生成报告
		vtrtTestReport = vtrtService.generateReport();

		isVtrtServiceStoped = true;
	}
	
	/**确定将要闪动的图片
	 * 
	 * @param question
	 */
	private void toFlashImageView(VtrtQuestion question){
		
		int locationCode = Integer.valueOf(question.getLocations()[0]);
		toFlashImageView = _7ImageViews[locationCode-1];
		oldImageRes = _7TouchImageRes[locationCode-1];
		int resourceId = CommonUtil.getImageResBySampleElement(
				question.getContents()[0]);
		
		if (resourceId == -1) {
			Log.e(TAG, "image resource not exist");
			toFlashImageRes = oldImageRes;
		}else {
			toFlashImageRes = resourceId;
		}
	}
	
	/**创建OnTouchListener
	 * 
	 * @param _answerCode the answer code for the keys on touch screen
	 * @return
	 */
	private View.OnTouchListener createOnTouchListener(ImageView imageView, final int _answerCode){

		TouchScreenOnTouchListener touchListener = new TouchScreenOnTouchListener(){
			
			@Override
			public void doSthInAvailableArea() {
				if (vtrtQuestion != null) {
					//如果状态为可答题
					if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
						//取消答题倒计时器
						vtrtCountDownTimer.cancels();

						int answerCode = _answerCode;
						//如果是对应键反应,则answerCode取对应键值
						if (responseType == Constants.VisioTouchResponseTrainingUIParameter.OPPOSITE_KEY_RESPONSE) {
							answerCode = Constants.OPPOSITE_KEY.get(_answerCode);
						}
						
						//创建答题对象
						VtrtAnswer answer = new VtrtAnswer();
						answer.setQuestionId(vtrtQuestion.getId());
						answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
						answer.setAnswers(new String[]{String.valueOf(answerCode)});

						//答题并返回结果
						VtrtResult testResult = vtrtService.answerQuestion(vtrtQuestion, answer);
						if (testResult.getValue()) {//如果回答正确
							
							//闪动标志位设为false
							isFlash = false;
							//闪动图像还原
							toFlashImageView.setImageResource(oldImageRes);
							//移除flash runable
							vtrtHandler.removeCallbacks(flash);
							//创建题目
							createVtrtQuestion();
							//开始答题
							startAnswerVtrt();									
						}else {//如果回答错误
							
							playVtrtErrorMusic();						
						}
						//倒计时重新开始
						vtrtCountDownTimer.starts();
					}else {
						answerVtrtQuestionError(getString(R.string.premature));
					}
				}				
			}
			
			@Override
			public void doSthNotInAvailableArea() {

				if (vtrtQuestion != null) {
					//如果状态为可答题状态
					if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
						answerVtrtQuestionError(VhtIntegrationOperationActivity.this.getString(R.string.press_out));
					}else {
						answerVtrtQuestionError(getString(R.string.premature));
					}					
				}
			}			
		};
		
		return touchListener.createOnTouchListener(imageView, 34, String.valueOf(_answerCode));
	}
	
	
	private void answerVtrtQuestionError(String errInfo){
		vtrtCountDownTimer.cancels();
		if (vtrtQuestion != null) {
			vtrtService.answerQuestion(vtrtQuestion, null);
			Toast.makeText(VhtIntegrationOperationActivity.this, errInfo, Toast.LENGTH_SHORT).show();
			playVtrtErrorMusic();
		}
		if (!isVtrtServiceStoped) {
			vtrtCountDownTimer.starts();
		}
	}
	
	private void playVtrtErrorMusic(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(VhtIntegrationOperationActivity.this, R.raw.error);
		mediaPlayer.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");

		//如果用户的身体任何部分触碰到屏幕的非按键之部分，均算错
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "MotionEvent.ACTION_DOWN");
			if (vtrtQuestion != null) {
				if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
					answerVtrtQuestionError(this.getString(R.string.touched_screen_error));
				}else {
					answerVtrtQuestionError(this.getString(R.string.premature));
				}
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	
	
	/***********************************听觉触觉************************************/	
	private MyCountDownTimer htrtCountDownTimer = new MyCountDownTimer(
			Constants.COUNT_DOWN_TIME, Constants.COUNT_DOWN_TIME){

		@Override
		public void onFinish() {
			if (isHtrtServiceStoped) {
				cancels();
				return;
			}
			
			Log.d(TAG, "time up");
			answerHtrtQuestionError(getString(R.string.htrtTimeOut));
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	};
	
	/**
	 * 创建听觉触觉题目
	 */
	private void createHtrtQuestion(){
		//如果服务已经停止
		if (isHtrtServiceStoped) {
			return;
		}
		Log.i(TAG, "starting create htrt question...");

		try {
			htrtQuestion = htrtService.createQuestion();
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
		musicPlayTools.prepareQuestionMusic(VhtIntegrationOperationActivity.this, 
				mp, htrtQuestion, canPlay, htrtQuestion.getContents()[0]);
	}
	
	/**
	 * 开始回答听觉触觉题目
	 */
	private void startAnswerHtrt(){
		Log.i(TAG, "starting answer question...");
		
		htrtCountDownTimer.cancels();
		htrtService.startAnswer();
		htrtCountDownTimer.starts();		
	}
	
	/**
	 * 停止回答听觉触觉题目
	 */
	private void stopAnswerHtrt(){
		Log.i(TAG, "stopping answer question...");
		
		htrtCountDownTimer.cancels();
		//修改播放器相关标志位
		canPlay = false;
		needRepeat = false;
		firstTimePlay = false;
		Log.d(TAG, "STOP_ANSWER HTRT, mp.isPlaying(): " + mp.isPlaying());
		if (mp.isPlaying()) {
			mp.stop();
			mp.reset();
		}
		
		htrtService.stop();
		htrtTestReport = htrtService.generateReport();
		isHtrtServiceStoped = true;		
	}
		
	/**键盘按下时的逻辑动作
	 * 
	 * @param answerCode  编码
	 * @return
	 */
	private void onKeyDownAction(final int answerCode){
		Log.d(TAG, "answerCode: " + answerCode);
		
		/*if (handType == Constants.RIGHT_HAND) {//如果是右手, _answerCode不能小于0
			if (answerCode < 0) {
				return;
			}
		}else if (handType == Constants.LEFT_HAND) {//如果是左手, _answerCode不能大于0
			if (answerCode > 0) {
				return;
			}
		}*/
		
		if (htrtQuestion != null) {
			//如果状态为可答题
			if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
				//取消答题倒计时器
				htrtCountDownTimer.cancels();
				
				//创建答题对象
				HtrtAnswer answer = new HtrtAnswer();
				answer.setQuestionId(htrtQuestion.getId());
				answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
				answer.setAnswers(new String[]{String.valueOf(answerCode)});

				//答题并返回结果
				HtrtResult testResult = htrtService.answerQuestion(htrtQuestion, answer);
				if (testResult.getValue()) {//如果回答正确
					//修改播放器相关标志位, 如果在播放则停止
					needRepeat = false;
					if (mp.isPlaying()) {
						mp.stop();
					}
					//创建题目
					createHtrtQuestion();
					startAnswerHtrt();
				}else {
					playHtrtErrorMusic();
				}
				
				htrtCountDownTimer.starts();
			}else {
				answerHtrtQuestionError(getString(R.string.premature));
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
			onKeyDownAction(Constants.THIRD_FINGER);
			break;
		case KeyEvent.KEYCODE_2://键盘上的2键代表左手中指
			onKeyDownAction(Constants.MIDDLE_FINGER);
			break;
		case KeyEvent.KEYCODE_3://键盘上的3键代表左手食指
			onKeyDownAction(Constants.FORE_FINGER);
			break;
		case KeyEvent.KEYCODE_4://键盘上的4键代表右手食指
			onKeyDownAction(Constants.FORE_FINGER);			
			break;
		case KeyEvent.KEYCODE_5://键盘上的5键代表右手中指
			onKeyDownAction(Constants.MIDDLE_FINGER);			
			break;
		case KeyEvent.KEYCODE_6://键盘上的6键代表右手无名指
			onKeyDownAction(Constants.THIRD_FINGER);			
			break;

		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	private void answerHtrtQuestionError(String errInfo){
		htrtCountDownTimer.cancels();
		if (htrtQuestion != null) {
			htrtService.answerQuestion(htrtQuestion, null);
			Toast.makeText(VhtIntegrationOperationActivity.this, errInfo, Toast.LENGTH_SHORT).show();
			playHtrtErrorMusic();			
		}
		if (!isHtrtServiceStoped) {
			htrtCountDownTimer.starts();			
		}
	}

	private void playHtrtErrorMusic(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(VhtIntegrationOperationActivity.this, R.raw.error);
		mediaPlayer.start();
	}
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_vision_touch_response_training_touchscreen中描述的布局添加到父布局中
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_vision_hearing_touch, null), getBaseLayoutParams());
		//设置为将要显示的布局
		setContentView(layout);
		
		this.setupViews();
		this.setupListeners();
		
		/****************接收来自UI的参数：视觉+触觉部分********/
		bundle = this.getIntent().getExtras();
		//测试时间
		time = bundle.getInt(Constants.VisioTouchResponseTrainingUIParameter.TIME, 30000);
		//反应类型
		responseType = bundle.getInt(Constants.VhtIntegrationTrainingUIParameter.RESPONSE_TYPE);
		//回应类型
		answerType = bundle.getInt(Constants.VhtIntegrationTrainingUIParameter.ANSWER_TYPE);
		//视觉触觉的样本群
		sampleSetVision = bundle.getString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_SET_VISION);
		//视觉触觉的样本元素
		sampleElementsVision = bundle.getString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_VISION);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI: Vision+Touch ----------");
		Log.d(TAG, "time: " + time);
		Log.d(TAG, "responseType: " + responseType);
		Log.d(TAG, "answerType: " + answerType);
		Log.d(TAG, "sampleSetVision: " + sampleSetVision);
		Log.d(TAG, "sampleElementsVision: " + sampleElementsVision);
		
		//分离出样本群信息
		String[] samples = sampleSetVision.split(",");
		String sampleIdRedom = ArrayOperations.getRedomElementFromElements(samples);
		String[] sampleElementsVisionRedom = sampleElementsVision
			.split(";")[ArrayOperations.indexInElement(samples, sampleIdRedom)].split(",");
		
		//如果是反射回应, 位元数为1
		if (answerType == Constants.VhtIntegrationTrainingUIParameter.REFLECT_ANSWER) {
			bit = 1;
		}else if (answerType == Constants.VhtIntegrationTrainingUIParameter.FIXED_POINT_ANSWER||
				answerType == Constants.VhtIntegrationTrainingUIParameter.INSTEAD_ANSWER) {
			//如果是定点回应或者替代回应, 位元数为样本元素数目+1
			bit = sampleElementsVisionRedom.length+1;
		}
		
		/****************接收来自UI的参数：听觉+触觉部分********/
		testType = bundle.getInt(Constants.VhtIntegrationTrainingUIParameter.TEST_TYPE);
		//handType = bundle.getInt(Constants.VhtIntegrationTrainingUIParameter.HAND_TYPE);
		sampleSetHearingInstrument = bundle.getString(
				Constants.VhtIntegrationTrainingUIParameter.SAMPLE_SET_HEARING_INSTRUMENT);
		sampleElementsHearingInstrument = bundle.getString(
				Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_HEARING_INSTRUMENT);
		sampleElementsHearingScale = bundle.getString(
				Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_HEARING_SCALE);
		
		Log.d(TAG, "----------receive parameters from UI: Hearing+Touch ----------");
		Log.d(TAG, "testType: " + testType);
		//Log.d(TAG, "handType: " + handType);
		Log.d(TAG, "sampleSetHearingInstrument: " + sampleSetHearingInstrument);
		Log.d(TAG, "sampleElementsHearingInstrument: " + sampleElementsHearingInstrument);
		Log.d(TAG, "sampleElementsHearingScale: " + sampleElementsHearingScale);
		
		String[] sampleElementsMusicArray = sampleElementsHearingInstrument.split(",");
		String[] sampleElementsScaleArray = sampleElementsHearingScale.split(",");
		
		
		/************为初始化视觉触觉服务准备参数**********/
		ChoosedSample choosedSample = new ChoosedSample();
		choosedSample.setSample(Integer.valueOf(sampleIdRedom));
		choosedSample.setSmapleElementChoosed(sampleElementsVisionRedom);
		
		VtrtParameter vtrtParameter = new VtrtParameter();
		vtrtParameter.setBit(bit);
		vtrtParameter.setOptionsType(Constants.TOUCH);
		vtrtParameter.setAnswerType(answerType);
		vtrtParameter.setChoosedSample(choosedSample);
		Map<String, Object> visionTouchParameters = new HashMap<String, Object>();
		visionTouchParameters.put(Constants.PARAMETER, vtrtParameter);
		
		VtrtRule vtrtRule = new VtrtRule();
		vtrtRule.setScore(2);
		visionTouchParameters.put(Constants.RULE, vtrtRule);
		visionTouchParameters.put(Constants.CONTEXT, VhtIntegrationOperationActivity.this);
		/***********************************************************************************/		
		
		/*******************为初始化听觉触觉准备参数******************/
		ChoosedSample music = new ChoosedSample();
		music.setSample(Integer.valueOf(sampleSetHearingInstrument));
		music.setSmapleElementChoosed(sampleElementsMusicArray);
		
		ChoosedSample scale = new ChoosedSample();
		scale.setSmapleElementChoosed(sampleElementsScaleArray);		
		
		//Map<Integer, Integer> musicInstrumentKeypadMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> scaleKeypadMap = new HashMap<Integer, Integer>();
		/*if (testType == Constants.VhtIntegrationTrainingUIParameter.MUSICAL_INSTRUMENT) {			
			for (int i = 0; i < sampleElementsMusicArray.length; i++) {
				int key = this.getFingerAreaByMusicInstrument(
						Integer.valueOf(sampleSetHearingInstrument), Integer.valueOf(sampleElementsMusicArray[i]));
				musicInstrumentKeypadMap.put(Integer.valueOf(sampleElementsMusicArray[i]), key);
			}
		}else */if (testType == Constants.VhtIntegrationTrainingUIParameter.SCALE) {
			scaleKeypadMap.put(
					Constants.ScaleLevel.SCALE_LOW, Constants.THIRD_FINGER);
			scaleKeypadMap.put(
					Constants.ScaleLevel.SCALE_MIDDLE, Constants.MIDDLE_FINGER);
			scaleKeypadMap.put(
					Constants.ScaleLevel.SCALE_HIGH, Constants.FORE_FINGER);
		}		
		
		HtrtParameter htrtParameter = new HtrtParameter();
		//htrtParameter.setBit(3);
		//htrtParameter.setHandType(handType);
		htrtParameter.setMusicType(Integer.valueOf(sampleSetHearingInstrument));
		htrtParameter.setTestType(testType);
		htrtParameter.setMusic(music);
		htrtParameter.setScale(scale);
		//htrtParameter.setMusicInstrumentKeypadMap(musicInstrumentKeypadMap);
		htrtParameter.setScaleKeypadMap(scaleKeypadMap);
		
		Map<String, Object> hearingTouchParameters = new HashMap<String, Object>();
		hearingTouchParameters.put(Constants.PARAMETER, htrtParameter);
		
		HtrtRule htrtRule = new HtrtRule();
		htrtRule.setScore(2);
		hearingTouchParameters.put(Constants.RULE, htrtRule);
		hearingTouchParameters.put(Constants.CONTEXT, VhtIntegrationOperationActivity.this);
		/***********************************************************************************/
		
		//initialize VTRT service
		vtrtService = new VtrtQuestionAnswerServiceImpl();
		try {
			vtrtService.init(visionTouchParameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		} catch (ParameterIsInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		//initialize HTRT service
		htrtService = new VHThtrtQuestionAnswerServiceImpl();
		try {
			htrtService.init(hearingTouchParameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		
		//initialize the timer view
		timerView.setEnd(time);
		timerView.init();

		//根据测试时间创建倒计时, 时间一到停止答题
		countDownTimer4StopAnswer = new MyCountDownTimer(time, time){

			@Override
			public void onFinish() {
				//when time up, send message to stop VTRT service and stop the timer view
				//motherHandler.sendMessage(motherHandler.obtainMessage(STOP_ANSWER));
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
				vtrtService.start();
				htrtService.start();
				//send message to begin creating question
				//motherHandler.sendMessage(motherHandler.obtainMessage(CREATE_QUESTION));
				createQuestionAction();
				countDownTimer4StopAnswer.starts();
			}
		});
		
		mediaPlayer = MediaPlayer.create(VhtIntegrationOperationActivity.this, R.raw.error);
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
	
	@Override
	protected void onStop() {
		
		vtrtCountDownTimer.cancels();
		if (repeatPlayTimer != null) {
			repeatPlayTimer.cancels();
		}
		htrtCountDownTimer.cancels();
		countDownTimer4StopAnswer.cancels();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		
		mp.release();
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		
		super.onPause();
		vtrtCountDownTimer.pause();
		if (repeatPlayTimer != null) {
			repeatPlayTimer.pause();
		}
		htrtCountDownTimer.pause();
		countDownTimer4StopAnswer.pause();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		
		super.onResume();
		
		if (!firstTime) {
			vtrtCountDownTimer.resume();
			htrtCountDownTimer.resume();
			if (repeatPlayTimer != null) {
				repeatPlayTimer.resume();
			}
			countDownTimer4StopAnswer.resume();
		}else {
			firstTime = false;
		}
	}
	
	
	private void setupViews(){

		//设置标题
		setTrainingTitle(getString(R.string.vision_hearing_touch_integration_training));
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

		imageViewTouch1 = (ImageView)findViewById(R.id.ImageView_VHT_01);
		imageViewTouch2 = (ImageView)findViewById(R.id.ImageView_VHT_02);
		imageViewTouch3 = (ImageView)findViewById(R.id.ImageView_VHT_03);
		imageViewTouch4 = (ImageView)findViewById(R.id.ImageView_VHT_04);
		imageViewTouch5 = (ImageView)findViewById(R.id.ImageView_VHT_05);
		imageViewTouch6 = (ImageView)findViewById(R.id.ImageView_VHT_06);
		imageViewTouch7 = (ImageView)findViewById(R.id.ImageView_VHT_07);
		_7ImageViews = new ImageView[]{imageViewTouch1, imageViewTouch2, imageViewTouch3,
				imageViewTouch4, imageViewTouch5, imageViewTouch6, imageViewTouch7};
		
		timerView = (TimerView)findViewById(R.id.TimerView_VHT);
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView_VHT);
	}
	
	
	private void setupListeners(){
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					//答题计时器暂停
					vtrtCountDownTimer.pause();
					htrtCountDownTimer.pause();
					countDownTimer4StopAnswer.pause();
					//显示时钟暂停
					timerView.pause();
					needRepeat = false;
					mp.stop();
					
					new AlertDialog.Builder(VhtIntegrationOperationActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							vtrtCountDownTimer.cancels();
							htrtCountDownTimer.cancels();
							countDownTimer4StopAnswer.cancels();
							isVtrtServiceStoped = true;
							isHtrtServiceStoped = true;
							startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//答题计时器恢复
							vtrtCountDownTimer.resume();
							htrtCountDownTimer.resume();
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
					vtrtCountDownTimer.pause();
					htrtCountDownTimer.pause();
					countDownTimer4StopAnswer.pause();
					//显示时钟暂停
					timerView.pause();
					needRepeat = false;
					mp.stop();
					
					new AlertDialog.Builder(VhtIntegrationOperationActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							vtrtCountDownTimer.cancels();
							htrtCountDownTimer.cancels();
							countDownTimer4StopAnswer.cancels();
							isVtrtServiceStoped = true;
							isHtrtServiceStoped = true;
							startActivity(new Intent(VhtIntegrationOperationActivity.this, VhtIntegrationTrainingChooseParametersActivity1.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//答题计时器恢复
							vtrtCountDownTimer.resume();
							htrtCountDownTimer.resume();
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
		
		imageViewTouch1.setOnTouchListener(this.createOnTouchListener(imageViewTouch1,1));
		imageViewTouch2.setOnTouchListener(this.createOnTouchListener(imageViewTouch2,2));
		imageViewTouch3.setOnTouchListener(this.createOnTouchListener(imageViewTouch3,3));
		imageViewTouch4.setOnTouchListener(this.createOnTouchListener(imageViewTouch4,4));
		imageViewTouch5.setOnTouchListener(this.createOnTouchListener(imageViewTouch5,5));
		imageViewTouch6.setOnTouchListener(this.createOnTouchListener(imageViewTouch6,6));
		imageViewTouch7.setOnTouchListener(this.createOnTouchListener(imageViewTouch7,7));
				
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

			public void onCompletion(MediaPlayer mp) {
				//如果此歌曲是第一遍播放,则播放完毕后启动答题
				if (firstTimePlay) {					
					//开始答题
					startAnswerHtrt();
				}				
				firstTimePlay = false;
				
				//如果此歌曲需要重复,则重复准备播放
				if (needRepeat) {
					MusicPlayTools musicPlayTools = new MusicPlayTools();
					musicPlayTools.prepareQuestionMusic(VhtIntegrationOperationActivity.this, 
							mp, htrtQuestion, canPlay, htrtQuestion.getContents()[0]);
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
}