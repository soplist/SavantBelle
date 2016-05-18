package com.flextronics.cn.activity.visiontouch;

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
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtAnswer;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtParameter;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtQuestion;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtResult;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtRule;
import com.flextronics.cn.service.visiontouch.VtrtQuestionAnswerService;
import com.flextronics.cn.service.visiontouch.VtrtQuestionAnswerServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>A-视觉听觉触觉综合训练</b><br>
 * 视觉触觉反应训练--触摸屏操作
 * 
 * @author ZhangGuoYin
 *
 */
public class VtrtTouchScreenOperationActivity extends BaseActivity {

	private final static String TAG = "VtrtTouchScreenOperationActivity";

	/**
	 * 测试时间
	 */
	private int time;
	/**
	 * 反应类型
	 */
	private int responseType;
	/**
	 * 回应类型
	 */
	private int answerType;
	/**
	 * 样本群
	 */
	private String sampleSet;
	/**
	 * 样本元素
	 */
	private String sampleElements;
	/**
	 * 位元数
	 */
	private int bit;

	/**
	 * 触摸键
	 */
	private ImageView imageViewTouch1;
	private ImageView imageViewTouch2;
	private ImageView imageViewTouch3;
	private ImageView imageViewTouch4;
	private ImageView imageViewTouch5;
	private ImageView imageViewTouch6;
	private ImageView imageViewTouch7;
	/**
	 * 计时显示器
	 */
	private TimerView timerView;
	/**
	 * 答题状态灯
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
	
	private boolean isReplaced = false;
	private boolean isFlash = false;
	/**
	 * 服务停止标志位
	 */
	private boolean isServiceStoped;
	/**
	 * 题目
	 */
	private VtrtQuestion question;
	/**
	 * 服务
	 */
	private VtrtQuestionAnswerService service;
	/**
	 * 第一次
	 */
	private boolean firstTime = true;
	/**
	 * 答错题的错误音播放器
	 */
	private MediaPlayer mediaPlayer;
	
	private Bundle bundle ;
	private Bitmap toFlashBitmap;
	private Bitmap oldBitmap;
	
	/**
	 * 整场测试的倒计时计时器
	 */
	private MyCountDownTimer countDownTimer4StopAnswer;
	/**
	 * 闪动Image view的消息处理类
	 */
	private Handler flashImageViewHandler = new Handler();
	
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
			
			flashImageViewHandler.postDelayed(this, Constants.FLASH_TIME);
		}
	};
	
	/**
	 * 每小题倒计时器，时间为5000ms. 时间范围内若没有作答，即算超时，计错误一次
	 */
	private MyCountDownTimer countDownTimer = new MyCountDownTimer(
			Constants.COUNT_DOWN_TIME, Constants.COUNT_DOWN_TIME){
		public void onFinish() {
			if (isServiceStoped) {
				cancels();
				return;
			}
			Log.d(TAG, "time up");
			answerQuestionError(R.string.time_out);
		}
		
		public void onTick(long millisUntilFinished) {
		}
	};
	

	
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_vision_touch_response_training_touchscreen中描述的布局添加到父布局中
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_vision_touch_response_training_touchscreen, null), getBaseLayoutParams());
		//设置为将要显示的布局
		setContentView(layout);
		
		this.setupViews();
		this.setupListeners();
		
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足A模块的接口规范************/
		bundle = this.getIntent().getExtras();
		time = bundle.getInt(Constants.VisioTouchResponseTrainingUIParameter.TIME, 
				Constants.DEFAULT_RESPONSE_TRAINING_TIME);
		responseType = bundle.getInt(Constants.VisioTouchResponseTrainingUIParameter.RESPONSE_TYPE,
				Constants.VisioTouchResponseTrainingUIParameter.ORIGINAL_KEY_RESPONSE);
		answerType = bundle.getInt(Constants.VisioTouchResponseTrainingUIParameter.ANSWER_TYPE,
				Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER);
		sampleSet = bundle.getString(Constants.VisioTouchResponseTrainingUIParameter.SAMPLE_SET);
		sampleElements = bundle.getString(Constants.VisioTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "--------------receive parameters from UI--------------");
		Log.d(TAG, "time:           " + time);
		Log.d(TAG, "responseType:   " + responseType);
		Log.d(TAG, "answerType:     " + answerType);
		Log.d(TAG, "sampleSet:      " + sampleSet);
		Log.d(TAG, "sampleElements: " + sampleElements);
		
		//分离出样本群信息
		String[] samples = sampleSet.split(",");
		String sampleIdRedom = ArrayOperations.getRedomElementFromElements(samples);
		String[] sampleElementsRedom = sampleElements
			.split(";")[ArrayOperations.indexInElement(samples, sampleIdRedom)].split(",");
		
		//如果是反射回应, 位元数为1
		if (answerType == Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER) {
			bit = 1;
		}else if (answerType == Constants.VisioTouchResponseTrainingUIParameter.FIXED_POINT_ANSWER||
				answerType == Constants.VisioTouchResponseTrainingUIParameter.INSTEAD_ANSWER) {
			//如果是定点回应或者替代回应, 位元数为样本元素数目+1
			bit = sampleElementsRedom.length+1;
		}

		/********************为视觉触觉反应训练服务准备参数 ********************/
		//创建选择样本对象
		ChoosedSample choosedSample = new ChoosedSample();
		//样本群编码
		choosedSample.setSample(Integer.valueOf(sampleIdRedom));
		//样本群元素编码
		choosedSample.setSmapleElementChoosed(sampleElementsRedom);

		//创建参数对象
		VtrtParameter vtrtParameter = new VtrtParameter();
		//位元数
		vtrtParameter.setBit(bit);
		//操作方式, 此处为触摸屏
		vtrtParameter.setOptionsType(Constants.TOUCH);
		//回应类型
		vtrtParameter.setAnswerType(answerType);
		//反应类型
		vtrtParameter.setResponseType(responseType);
		//受测样本
		vtrtParameter.setChoosedSample(choosedSample);
		
		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(Constants.PARAMETER, vtrtParameter);
		
		//规则
		VtrtRule vtrtRule = new VtrtRule();
		vtrtRule.setScore(2);
		parameters.put(Constants.RULE, vtrtRule);
		
		//当前CONTEXT
		parameters.put(Constants.CONTEXT, VtrtTouchScreenOperationActivity.this);
		/****************************参数准备完毕********************************/
				
		//实例化服务
		service = new VtrtQuestionAnswerServiceImpl();
		
		try {
			//根据参数初始化服务
			service.init(parameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		} catch (ParameterIsInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		
		timerView.setEnd(time);
		timerView.init();
		
		//根据测试时间创建倒计时, 时间一到停止答题
		countDownTimer4StopAnswer = new MyCountDownTimer(time, time){			
			public void onFinish() {
				//停止答题
				stopAnswerAction();
				timerView.stop();
			}
			
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
				startAnswerAction();
				//开启测试倒计时
				countDownTimer4StopAnswer.starts();
			}
		});

		//答题错误提示音
		mediaPlayer = MediaPlayer.create(VtrtTouchScreenOperationActivity.this, R.raw.error);
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
		setTrainingTitle(getString(R.string.vision_touch_response_training_touch_screen));
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
		
		//7个触摸键
		imageViewTouch1 = (ImageView)findViewById(R.id.ImageView01);
		imageViewTouch2 = (ImageView)findViewById(R.id.ImageView02);
		imageViewTouch3 = (ImageView)findViewById(R.id.ImageView03);
		imageViewTouch4 = (ImageView)findViewById(R.id.ImageView04);
		imageViewTouch5 = (ImageView)findViewById(R.id.ImageView05);
		imageViewTouch6 = (ImageView)findViewById(R.id.ImageView06);
		imageViewTouch7 = (ImageView)findViewById(R.id.ImageView07);
		_7ImageViews = new ImageView[]{imageViewTouch1, imageViewTouch2, imageViewTouch3,
				imageViewTouch4, imageViewTouch5, imageViewTouch6, imageViewTouch7};
		
		//倒计时时钟
		timerView = (TimerView)findViewById(R.id.TimerView_VTRT_TouchScreen);
		//训练答题状态灯
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView_VTRT_TouchScreen7);
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
					
					new AlertDialog.Builder(VtrtTouchScreenOperationActivity.this)
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
							//设置闪动标志位为false
							isFlash = false;
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
					
					new AlertDialog.Builder(VtrtTouchScreenOperationActivity.this)
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
							//设置闪动标志位为false
							isFlash = false;
							startActivity(new Intent(getApplicationContext(), VtrtTouchScreenChooseParametersActivity.class));
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
						}									
					}).show();
				}
				return false;
			}
		});		
		
		//为7个按钮设置触摸监听器
		imageViewTouch1.setOnTouchListener(this.createOnTouchListener(imageViewTouch1,1));
		imageViewTouch2.setOnTouchListener(this.createOnTouchListener(imageViewTouch2,2));
		imageViewTouch3.setOnTouchListener(this.createOnTouchListener(imageViewTouch3,3));
		imageViewTouch4.setOnTouchListener(this.createOnTouchListener(imageViewTouch4,4));
		imageViewTouch5.setOnTouchListener(this.createOnTouchListener(imageViewTouch5,5));
		imageViewTouch6.setOnTouchListener(this.createOnTouchListener(imageViewTouch6,6));
		imageViewTouch7.setOnTouchListener(this.createOnTouchListener(imageViewTouch7,7));
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
		//确定将要闪动的图像
		toFlashImageView(question);
		//设置闪动状态位
		isFlash = true;
		//推送到队列中
		flashImageViewHandler.post(flash);
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

		isServiceStoped = true;
		countDownTimer.cancels();
		
		//移除flash
		flashImageViewHandler.removeCallbacks(flash);
		//设置闪动标志位为false
		isFlash = false;
		//图片还原
		toFlashImageView.setImageResource(oldImageRes);
		//服务停止
		service.stop();
		//生成报告并显示
		showTestReport(service.generateReport());
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
	 * @param _answerCode 按键代码
	 * @return
	 */
	private View.OnTouchListener createOnTouchListener(ImageView imageView, final int _answerCode){

		TouchScreenOnTouchListener touchListener = new TouchScreenOnTouchListener(){			
			
			public void doSthInAvailableArea() {
				if (question != null) {
					//如果状态为可答题
					if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
						//取消答题倒计时器
						countDownTimer.cancels();

						int answerCode = _answerCode;
						//如果是对应键反应,则answerCode取对应键值
						if (responseType == Constants.VisioTouchResponseTrainingUIParameter.
								OPPOSITE_KEY_RESPONSE) {
							answerCode = Constants.OPPOSITE_KEY.get(_answerCode);
						}
						
						//创建答题对象
						VtrtAnswer answer = new VtrtAnswer();
						answer.setQuestionId(question.getId());
						answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
						answer.setAnswers(new String[]{String.valueOf(answerCode)});

						//答题并返回结果
						VtrtResult testResult = service.answerQuestion(question, answer);
						if (testResult.getValue()) {//如果回答正确
							
							//闪动标志位设为false
							isFlash = false;
							//闪动图像还原
							toFlashImageView.setImageResource(oldImageRes);
							//移除flash runable
							flashImageViewHandler.removeCallbacks(flash);
							//创建题目
							createQuestionAction();
							//开始答题
							startAnswerAction();									
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
			
			
			public void doSthNotInAvailableArea() {
				if (question != null) {
					//如果状态为可答题状态
					if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
						answerQuestionError(R.string.press_out);
					}else {
						//过早反应
						answerQuestionError(R.string.premature);
					}
				}
			}			
		};
		
		return touchListener.createOnTouchListener(imageView, 34, String.valueOf(_answerCode));
	}


	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 禁用键盘上的HOME,后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");

		//如果用户的身体任何部分触碰到屏幕的非按键之部分，均算错
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "MotionEvent.ACTION_DOWN");
			if (question != null) {
				if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
					answerQuestionError(R.string.touched_screen_error);
				}else {
					answerQuestionError(R.string.premature);
				}			
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	/**
	 * 显示测试报告
	 * @param testReport
	 */
	private void showTestReport(final VtrtReport testReport){		

		new AlertDialog.Builder(VtrtTouchScreenOperationActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				
				Intent intent = new Intent(VtrtTouchScreenOperationActivity.this, TestReportActivity.class);
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, VtrtTouchScreenOperationActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, VtrtTouchScreenChooseParametersActivity.class.getName());
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		})
		.show();
	}

	
	protected void onPause() {
		super.onPause();		
		Log.d(TAG, "onPause");		
		
		countDownTimer.pause();
		countDownTimer4StopAnswer.pause();
		timerView.pause();
	}

	
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
	
	
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
		
		countDownTimer.cancels();
		countDownTimer4StopAnswer.cancels();
	//	timerView.stop();
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
			Toast.makeText(VtrtTouchScreenOperationActivity.this, errInfo, Toast.LENGTH_SHORT).show();
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
		mediaPlayer = MediaPlayer.create(VtrtTouchScreenOperationActivity.this, R.raw.error);
		mediaPlayer.start();
	}
}