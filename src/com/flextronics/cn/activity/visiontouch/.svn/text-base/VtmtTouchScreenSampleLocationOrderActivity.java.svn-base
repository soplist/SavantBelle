package com.flextronics.cn.activity.visiontouch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.activity.TestReportWithTableActivity;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtAnswer;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtParameter;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtQuestion;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtReport;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtResult;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtRule;
import com.flextronics.cn.service.visiontouch.VtmtQuestionAnswerService;
import com.flextronics.cn.service.visiontouch.VtmtQuestionAnswerServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.MyCountDownTimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>A-视觉 听觉 触觉综合训练</b><br>
 * 视觉触觉记忆训练触摸屏操作---样本位置顺序
 * 
 * @author ZhangGuoYin
 *
 */
public class VtmtTouchScreenSampleLocationOrderActivity extends BaseActivity {

	private final static String TAG = "VtmtTouchScreenSampleLocationOrderActivity";
	private final static int CREATE_QUESTION = 8001;
	private final static int START_ANSWER = 8002;
	private final static int STOP_ANSWER = 8003;
	private final static String CONTENT = "CONTENT";
	private final static String LOCATION = "LOCATION";
	private final static String[] OLD_CONTENTS = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private final static int[] OLD_RESOURCES = new int[]{R.drawable.touch1, R.drawable.touch2, R.drawable.touch3, R.drawable.touch4, 
			R.drawable.touch5, R.drawable.touch6, R.drawable.touch7, R.drawable.touch8, R.drawable.touch9};

	/**
	 * 位元类型
	 */
	private int bitType;
	/**
	 * 开始位元 ---- 相对位元类型取连续位元而言
	 */
	private int startBit;
	/**
	 * 样本元素出现顺序类型
	 */
	private int presentType;
	/**
	 * 样本群
	 */
	private String sampleSet;
	/**
	 * 题目数
	 */
	private int questionCount;
	
	/**
	 * 总共答错题目数量
	 */
	private int totalErrorCount=0;
	/**
	 * 阶段内答错题目数量
	 */
	private int perPeriodErrorCount=0;
	private long lastAnswerQuestionId = -1;
	
	private ImageView imageViewTouch1;
	private ImageView imageViewTouch2;
	private ImageView imageViewTouch3;
	private ImageView imageViewTouch4;
	private ImageView imageViewTouch5;
	private ImageView imageViewTouch6;
	private ImageView imageViewTouch7;
	private ImageView imageViewTouch8;
	private ImageView imageViewTouch9;
	
	/**
	 * 计时显示器
	 */
	private TimerView timerView;
	/**
	 * 答题状态灯
	 */
	private StartFlagImageView startFlagImageView;
	
	private VtmtQuestion question;
	private VtmtQuestionAnswerService service;
	/**
	 * 第一次resume标志
	 */
	private boolean firstTime = true;
	/**
	 * 服务停止标志
	 */
	private boolean isServiceStoped;

	private Bundle bundle ;
	private MediaPlayer mediaPlayer;

	private FlashThread flashThread;
	private ImageView[] imageViews;
	private List<String> answerList = new ArrayList<String>();

	private Handler qaHandler = new QuestionAnswerHandler();
	/**
	 * 处理带题目内容的消息，闪动图片
	 */
	private FlashHandler flashHandler = new FlashHandler();	
	
	
	/**
	 * 闪动图片
	 * @author ZhangGuoYin
	 *
	 */
	class FlashHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			
			ImageView imageViewFlash = imageViews[msg.getData().getInt(LOCATION)-1];
			
			int resourceId = 0;
			if (ArrayOperations.inElements(OLD_CONTENTS, msg.getData().getString(CONTENT))) {
				resourceId = OLD_RESOURCES[ArrayOperations.indexInElement(OLD_CONTENTS, msg.getData().getString(CONTENT))];
			}else {
				resourceId = CommonUtil.getImageResBySampleElement(
						Integer.valueOf(msg.getData().getString(CONTENT)));
			}
			
			imageViewFlash.setImageResource(resourceId);
		}
	}
	
	/**
	 * 闪动图片的进程
	 * @author ZhangGuoYin
	 *
	 */
	class FlashThread extends Thread{

		private boolean stopFlash;		
		public void setStopFlash(boolean stopFlash) {
			this.stopFlash = stopFlash;
		}

		public void run() {			
			//send message to show the question content
			for (int i = 0; i < question.getContents().length; i++) {

				try {
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString(CONTENT, question.getContents()[i]);
					bundle.putInt(LOCATION, Integer.valueOf(question.getLocations()[i]));
					msg.setData(bundle);

					sleep(Constants.DEFAULT_SHOW_TIME);
					if (stopFlash) {
						return;
					}
					flashHandler.sendMessage(msg);

					msg = new Message();
					bundle = new Bundle();
					bundle.putString(CONTENT, OLD_CONTENTS[Integer.valueOf(question.getLocations()[i])-1]);
					bundle.putInt(LOCATION, Integer.valueOf(question.getLocations()[i]));
					msg.setData(bundle);

					sleep(Constants.DEFAULT_SHOW_TIME);
					if (stopFlash) {
						return;
					}
					flashHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			qaHandler.sendMessage(qaHandler.obtainMessage(START_ANSWER));
		}
	}	
	
	class QuestionAnswerHandler extends Handler{

		@Override
		public void handleMessage(Message msg) { //handle message 
			switch (msg.what) {
			case CREATE_QUESTION:
				//如果服务已经停止
				if (isServiceStoped) {
					Log.i(TAG, "the service is stoped");
					return;
				}				
				Log.i(TAG, "starting create question...");
				
				if (flashThread != null) {
					flashThread.setStopFlash(true);
				}
				
				resetFlashImages();
				
				new CountDownTimer(Constants.MEMORY_TRAINING_WAITING_TIME, 
						Constants.MEMORY_TRAINING_WAITING_TIME){

					@Override
					public void onFinish() {
						
						//创建题目
						try {
							question = service.createQuestion();
						} catch (LackOfParametersException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ActivityUtil.finish(VtmtTouchScreenSampleLocationOrderActivity.this);
						} catch (ParameterIsInvalidException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ActivityUtil.finish(VtmtTouchScreenSampleLocationOrderActivity.this);
						}
						
						//如果题目为空
						if (question == null) {
							return;
						}
						
						//将答题状态标志灯变为红色,状态设为初始状态
						startFlagImageView.setImageResource(R.drawable.red);
						startFlagImageView.setState(StartFlagImageView.STATE_INIT);
						
						//如果是连续位元,每阶段完成5题,则将该阶段的错误次数清零
						if (bitType == Constants.BitType.CONTINUED_BIT) {
							if (question.getId()>1 && question.getId()%5 == 1) {
								perPeriodErrorCount=0;
							}
						}
						
						//取消答题计时器
						countDownTimer.cancels();
						
						flashThread = new FlashThread();
						flashThread.start();
					}

					@Override
					public void onTick(long paramLong) {
					}					
				}.start();				
				break;
				
			case START_ANSWER:
				Log.i(TAG, "starting answer question...");
				
				//将存放答案的list清空
				answerList.clear();
				//答题开始
				service.startAnswer();

				//切换答题状态至STATE_TIME_UP
				startFlagImageView.setState(StartFlagImageView.STATE_TIME_UP);
				//切换答题状态灯至绿色
				startFlagImageView.setImageResource(R.drawable.green);
				//答题计时器开始
				countDownTimer.starts();
				break;
			case STOP_ANSWER:
				Log.i(TAG, "stopping answer question...");
				
				//设置服务停止状态位
				isServiceStoped = true;			
				//取消计时器
				countDownTimer.cancels();
				//服务停止
				service.stop();
				timerView.stop();
				//生成测试报告并显示				
				showTestReport(service.generateTestReport());
				break;
			default:
				break;
			}
		}		
	} 

	/**
	 * 每小题的倒计时器,在指定时间内不作答计错一次
	 */
	private MyCountDownTimer countDownTimer = new MyCountDownTimer(
			Constants.COUNT_DOWN_TIME, Constants.COUNT_DOWN_TIME){

		@Override
		public void onFinish() {
			Log.d(TAG, "time up");
			
			if (!isServiceStoped) {
				answerQuestionError(getString(R.string.time_out));
				if (question != null) {
					//清空答案的list
					answerList.clear();
					decideAction4AnswerError();
				}
			}else {
				cancels();
			}
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	};	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_vision_touch_memory_training_sample_location_touchscreen中描述的布局添加到父布局中
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_vision_touch_memory_training_sample_location_touchscreen, null), getBaseLayoutParams());
		//设置为将要显示的布局
		setContentView(layout);
		
		this.setupViews();
		this.setupListeners();		
		
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足A模块的接口规范************/
		bundle = this.getIntent().getExtras();
		bitType = bundle.getInt(Constants.VisioTouchMemoryTrainingUIParameter.BIT_TYPE, Constants.BitType.GIVEN_BIT);
		startBit = bundle.getInt(Constants.VisioTouchMemoryTrainingUIParameter.START_BIT, 3);
		presentType = bundle.getInt(Constants.VisioTouchMemoryTrainingUIParameter.PRESENT_TYPE);
		sampleSet = bundle.getString(Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_SET);
		questionCount = bundle.getInt(Constants.VisioTouchMemoryTrainingUIParameter.QUESTION_COUNT, 15);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI----------");
		Log.d(TAG, "bitType:      " + bitType);
		Log.d(TAG, "startBit:     " + startBit);
		Log.d(TAG, "presentType:  " + presentType);
		Log.d(TAG, "sampleSet:    " + sampleSet);
		Log.d(TAG, "questionCount:" + questionCount);
		Log.d(TAG, "-------------------- end ---------------------");
		
		//分离出样本群信息
		String[] samples = sampleSet.split(",");
		ChoosedSample[] choosedSamples = new ChoosedSample[samples.length];
		for (int i = 0; i < samples.length; i++) {
			ChoosedSample choosedSample = new ChoosedSample();
			choosedSample.setSample(Integer.valueOf(samples[i]));
			choosedSamples[i] = choosedSample;
		}
		
		/********************为视觉触觉记忆训练服务准备参数 ********************/
		VtmtParameter vtmtParameter = new VtmtParameter();
		vtmtParameter.setBitType(bitType);
		vtmtParameter.setOptionsType(Constants.TOUCH);
		vtmtParameter.setPresentType(presentType);
		vtmtParameter.setStartBit(startBit);
		vtmtParameter.setQuestionCount(questionCount);
		vtmtParameter.setChoosedSamples(choosedSamples);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(Constants.PARAMETER, vtmtParameter);

		VtmtRule vtmtRule = new VtmtRule();
		vtmtRule.setScore(2);
		parameters.put(Constants.RULE, vtmtRule);
		
		parameters.put(Constants.CONTEXT, VtmtTouchScreenSampleLocationOrderActivity.this);
		/****************************参数准备完毕********************************/

		//实例化服务
		service = new VtmtQuestionAnswerServiceImpl();
		try {
			service.init(parameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}		
		
		mediaPlayer = MediaPlayer.create(VtmtTouchScreenSampleLocationOrderActivity.this, R.raw.error);
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.d(TAG, "PlayErrorMusic onError()");
				mediaPlayer.release();
				return false;
			}				
		});
		
		timerView.init();
		timerView.start();
		//服务开始
		service.start();
		qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown");
		// 禁用键盘上的后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}	
	
	@Override
	protected void onPause() {
		super.onPause();		
		Log.d(TAG, "onPause");		
		
		countDownTimer.pause();
		timerView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		
		if (!firstTime) {
			countDownTimer.resume();
			timerView.resume();
		}else {
			firstTime = false;
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
		
		countDownTimer.cancels();
	}
	
	/**
	 * 设置相关控件
	 */
	private void setupViews(){
		//设置标题
		setTrainingTitle(getString(R.string.vision_touch_memory_training_touch_screen));
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
		imageViewTouch1 = (ImageView)findViewById(R.id.ImageView9_01);
		imageViewTouch2 = (ImageView)findViewById(R.id.ImageView9_02);
		imageViewTouch3 = (ImageView)findViewById(R.id.ImageView9_03);
		imageViewTouch4 = (ImageView)findViewById(R.id.ImageView9_04);
		imageViewTouch5 = (ImageView)findViewById(R.id.ImageView9_05);
		imageViewTouch6 = (ImageView)findViewById(R.id.ImageView9_06);
		imageViewTouch7 = (ImageView)findViewById(R.id.ImageView9_07);
		imageViewTouch8 = (ImageView)findViewById(R.id.ImageView9_08);
		imageViewTouch9 = (ImageView)findViewById(R.id.ImageView9_09);
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView_VTMT_TouchScreen9);
		imageViews = new ImageView[]{imageViewTouch1, imageViewTouch2, imageViewTouch3, imageViewTouch4,
				imageViewTouch5, imageViewTouch6, imageViewTouch7, imageViewTouch8, imageViewTouch9};
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
					//显示时钟暂停
					timerView.pause();
					
					new AlertDialog.Builder(VtmtTouchScreenSampleLocationOrderActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
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
					//显示时钟暂停
					timerView.pause();
					
					new AlertDialog.Builder(VtmtTouchScreenSampleLocationOrderActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							isServiceStoped = true;
							startActivity(new Intent(getApplicationContext(), VtmtTouchScreenChooseParametersActivity.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//答题计时器恢复
							countDownTimer.resume();
							//显示时钟恢复
							timerView.resume();
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
		imageViewTouch8.setOnTouchListener(this.createOnTouchListener(imageViewTouch8,8));
		imageViewTouch9.setOnTouchListener(this.createOnTouchListener(imageViewTouch9,9));
	}

	/**创建图片的触摸时回调函数
	 * 
	 * @param imageView	
	 * @param _answerCode	图片所代表的编码
	 * @return
	 */
	private ImageView.OnTouchListener createOnTouchListener(ImageView imageView, final int _answerCode){

		return new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (isServiceStoped) {
					return false;
				}
				if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
					if (question!=null && lastAnswerQuestionId != question.getId()) {
						if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
							//取消答题计时器
							countDownTimer.cancels();
							//将_answerCode添加到列表中
							answerList.add(String.valueOf(_answerCode));
							
							if (!currentResult(answerList, question.getAnswers())) {
								//如果列表中值不跟标准答案相同,表明用户已经答错了,可以直接提示用户答题错误,进入下一题
								//无需等待用户把所有答案都按对为止							
								Log.d(TAG, "_answerCode: " + _answerCode + " is wrong.");
								lastAnswerQuestionId = question.getId();
								service.answerQuestion(question, null);
								showErrorInfo("");
								playErrorMusic();
								
								answerList.clear();
								//判读是否继续出题
								decideAction4AnswerError();
							}else {
								Log.d(TAG, "_answerCode: " + _answerCode + " is right.");
								
								if (answerList.size()==question.getAnswers().length) {//用户已经按全所有按键
									
									String[] answers = new String[question.getLocations().length];
									for (int i = 0; i < answerList.size(); i++) {
										answers[i] = answerList.get(i);
									}								
									answerList.clear();

									VtmtAnswer answer = new VtmtAnswer();
									answer.setQuestionId(question.getId());
									answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
									answer.setAnswers(answers);

									lastAnswerQuestionId = question.getId();
									//回答问题并得到结果
									VtmtResult testResult = service.answerQuestion(question, answer);
									//如果回答正确,按照流程此处肯定是回答正确的
									if (testResult.getValue()) {
										//判读是否继续出题
										decideAction4AnswerRight();
									}
								}else {
									countDownTimer.starts();
								}
							}						
						}else {//答题状态标志位没有切换到TIME_UP,属于过早反应
							Log.d(TAG, "answer premature");
							answerQuestionPremature();
							answerList.clear();
							decideAction4AnswerError();				
						}
					}
				}
				return false;
			}			
		};
	}

	/**弹出一个toast,以告知用户错误信息
	 * 
	 * @param preString
	 */
	private void showErrorInfo(String preString){
		
		String[] _answers = question.getAnswers();
		String str = "";
		for (int i = 0; i < _answers.length; i++) {			
			str += _answers[i] + " ";
		}
		
		Toast.makeText(VtmtTouchScreenSampleLocationOrderActivity.this, 
				preString+" " + getResources().getString(R.string.answers) + str, 
				Toast.LENGTH_LONG).show();
	}
	
	/**显示测试报告，实际上是跳转到专门显示测试报告的activity
	 * 
	 * @param testReport
	 */
	private void showTestReport(final VtmtReport testReport){		

		new AlertDialog.Builder(VtmtTouchScreenSampleLocationOrderActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)	//此alertDialog不允许被取消
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();

				if (bitType == Constants.BitType.CONTINUED_BIT) {
					intent.setClass(getApplicationContext(), TestReportWithTableActivity.class);
				}else {
					intent.setClass(getApplicationContext(), TestReportActivity.class);
				}

				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, VtmtTouchScreenSampleLocationOrderActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, VtmtTouchScreenChooseParametersActivity.class.getName());
				intent.putExtras(bundle);
				startActivity(intent);
				finish();				
			}
		})
		.show();
	}	

	/**
	 * 回答问题错误
	 * @param errInfo
	 */
	private void answerQuestionError(String errInfo){
		//取消计时器
		countDownTimer.cancels();
		if (question != null && lastAnswerQuestionId!=question.getId()) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			//显示错误信息
			showErrorInfo(errInfo);
			//播放错误音乐
			playErrorMusic();
		}
		//重新开启计时器
		countDownTimer.starts();
	}
	
	/**回答过早,过早反应
	 * 
	 */
	private void answerQuestionPremature(){
		countDownTimer.cancels();
		if (question != null && lastAnswerQuestionId!=question.getId()) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			Toast.makeText(VtmtTouchScreenSampleLocationOrderActivity.this, R.string.premature, Toast.LENGTH_SHORT).show();
			playErrorMusic();
		}
		countDownTimer.starts();		
	}

	/**
	 * 播放答题错误音
	 */
	private void playErrorMusic(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(VtmtTouchScreenSampleLocationOrderActivity.this, R.raw.error);
		mediaPlayer.start();
	}	
	
	/**
	 * 回答问题正确后决定是继续出题还是停止测试
	 */
	private void decideAction4AnswerRight(){
		
		//如果是指定位元或者自选位元
		if ( (bitType == Constants.BitType.GIVEN_BIT) ||
				(bitType>=3&&bitType<=36)) {
			//如果题目已出满则结束测试,否则重新出题
			if (question.getId() >= questionCount) {
				//测试结束
				qaHandler.sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
			}else {
				//重新出题
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		}else if (bitType == Constants.BitType.CONTINUED_BIT) {//如果是连续位元
			//重新出题
			qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
		}
	}
	
	/**
	 * 回答问题错误后决定是继续出题还是停止测试
	 */
	private void decideAction4AnswerError(){
				
		//如果是指定位元或者自选位元
		if ( (bitType == Constants.BitType.GIVEN_BIT) ||
				(bitType>=3&&bitType<=36)) {
			//如果题目已出满则结束测试,否则重新出题				
			if (question.getId() >= questionCount) {
				//测试结束
				qaHandler.sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
			}else {
				//重新出题
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		}else if (bitType == Constants.BitType.CONTINUED_BIT) {//如果是连续位元
			//总错误次数自增
			totalErrorCount++;
			//此阶段内错误次数自增
			perPeriodErrorCount++;
			//如果总错误次数达到该位元允许的最大错误次数或者此阶段内错误达3次,则结束此次测试
			//否则重新出题
			if (totalErrorCount>=VtmtRule.getCumulativeErrorCount(question.getContents().length) 
					|| perPeriodErrorCount>=3) {
				//测试结束
				qaHandler.sendMessage(qaHandler.obtainMessage(STOP_ANSWER));	
			}else {
				//重新出题
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		}
	}

	
	/**用户当前所选的答案跟标准答案是否匹配
	 * 
	 * @param codesList
	 * @param answers
	 * @return
	 */
	private boolean currentResult(List<String> codesList, String[] answers){
		if (answers==null || codesList==null) {
			return false;
		}
		if (codesList.size() > answers.length) {
			return false;
		}
		boolean value = true;
		for (int i = 0; i < codesList.size(); i++) {
			
			value = value&&(codesList.get(i).equals(answers[i]));
			if (!value) {
				break;
			}
		}
		return value;
	}
		
	private void resetFlashImages(){
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[i].setImageResource(OLD_RESOURCES[i]);
		}
	}
}