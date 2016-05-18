package com.flextronics.cn.activity.color;

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
import com.flextronics.cn.exception.CanNotSupportSuchBitsException;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.OutOfMaxQuestionsException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingAnswer;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingParameter;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingQuestion;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingReport;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingResult;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingRule;
import com.flextronics.cn.service.color.ColorMemoryTrainingService;
import com.flextronics.cn.service.color.ColorMemoryTrainingServiceImpl;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>C-颜色辨识深度训练</b><br>
 * 颜色记忆训练
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorMemoryTrainingActivity extends BaseActivity {

	private final static String TAG = "ColorMemoryTrainingActivity";
	private final static String INDEX = "INDEX";
	private final static String SHOW = "SHOW";
	private final static int ACTION_1 = 9001; 
	private final static int ACTION_2 = 9002; 
	private final static int ACTION_3 = 9003; 
	private final static int SIMPLE = 10001; 
	private final static int TU_YANG = 10002; 
	//private final static int TU_AN = 10003; 
	
	private final static int CREATE_QUESTION = 8001;
	private final static int START_ANSWER = 8002;
	private final static int STOP_ANSWER = 8003;

	/**
	 * 位元类型
	 */
	private int bitType;
	/**
	 * 开始位元--针对连续位元而言
	 */
	private int startBit;
	/**
	 * 显示形体
	 */
	private String displayBody;
	private int[] displayBodys;
	/**
	 * 显示模式
	 */
	private int displayMode;
	/**
	 * 显示顺序--针对显示形体是图样的时候才有的
	 */
	private int displayOrder;
	/**
	 * 题目数量
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
	
	/**
	 * 答题状态标志灯
	 */
	private StartFlagImageView startFlagImageView;
	/**
	 * 秒表
	 */
	private TimerView timerView;
	
	/**
	 * 红色作答按钮
	 */
	private ImageView imageViewTouchRed;
	/**
	 * 橙色作答按钮
	 */
	private ImageView imageViewTouchOrange;
	/**
	 * 黄色作答按钮
	 */
	private ImageView imageViewTouchYellow;
	/**
	 * 绿色作答按钮
	 */
	private ImageView imageViewTouchGreen;
	/**
	 * 蓝色作答按钮
	 */
	private ImageView imageViewTouchBlue;
	/**
	 * 靛色作答按钮
	 */
	private ImageView imageViewTouchIndigo;
	/**
	 * 紫色作答按钮
	 */
	private ImageView imageViewTouchPurple;
	/**
	 * 黑色作答按钮
	 */
	private ImageView imageViewTouchBlack;
	/**
	 * 咖啡色作答按钮
	 */
	private ImageView imageViewTouchCoffee;

	/**
	 * 用于显示题目的布局
	 */
	private LinearLayout linearLayout;
	/**
	 * 临时布局
	 */
	private LinearLayout layoutTmp;
	private RelativeLayout relativeLayout103;
	private Bitmap bitmap;

	/**
	 * 颜色记忆训练服务
	 */
	private ColorMemoryTrainingService service;
	/**
	 * 颜色记忆训练问题
	 */
	private ColorMemoryTrainingQuestion question;
	
	/**
	 * 第一次resume标志
	 */
	private boolean firstTime = true;
	/**
	 * 服务停止标志
	 */
	private boolean isServiceStoped;

	private Bundle bundle ;
	/**
	 * 错误音播放器
	 */
	private MediaPlayer mediaPlayer;

	/**
	 * 已作答的答案
	 */
	private List<String> answerList;
	/**
	 * 九种颜色中文名
	 */
	private String[] colorNames;	
	/**
	 * 闪动图片的线程
	 */
	private FlashThread flashThread;
	
	Handler qaHandler = new QuestionAnswerHandler();
	
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
				
				//取得编号为R.id.LinearLayout01的布局，移除其中所有的视图并刷新
				final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);
				linearLayout.removeAllViews();
								
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
							ActivityUtil.finish(ColorMemoryTrainingActivity.this);
						} catch (OutOfMaxQuestionsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ActivityUtil.finish(ColorMemoryTrainingActivity.this);
						} catch (ParameterIsInvalidException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ActivityUtil.finish(ColorMemoryTrainingActivity.this);
						} catch (CanNotSupportSuchBitsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							reachesMaxBit4TuYang();
							return;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ActivityUtil.finish(ColorMemoryTrainingActivity.this);
						}
						
						//如果题目为空
						if (question == null) {
							return;
						}

						linearLayout.invalidate();
						
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
						//将题目显示在屏幕上
						showQuestion(question);
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
				showTestReport(service.generateReport());
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
				answerQuestionError(R.string.time_out);
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
		//将R.layout.color_memory_training中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.color_memory_training, null), getBaseLayoutParams());
		setContentView(layout);
		
		colorNames = getResources().getStringArray(R.array.color_names);
		answerList = new ArrayList<String>();
		
		//获得此activity中的各视图控件
		this.setupViews();
		//为各控件设置监听器
		this.setupListeners();
		
		
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足C模块的接口规范************/
		bundle = this.getIntent().getExtras();
		//位元类型
		bitType = bundle.getInt(Constants.ColorMemoryTrainingUIParameter.BIT_TYPE, 
				Constants.BitType.GIVEN_BIT);
		//开始位元,当位元类型为连续位元时有作用
		startBit = bundle.getInt(Constants.ColorMemoryTrainingUIParameter.START_BIT, 3);
		//显示形体
		displayBody = bundle.getString(Constants.ColorMemoryTrainingUIParameter.DISPLAY_BODY);
		//显示模式
		displayMode = bundle.getInt(Constants.ColorMemoryTrainingUIParameter.DISPLAY_MODE, 
				Constants.DisplayMode.SHOW_HOLD);
		//显示顺序,当显示形体为图样或图案时有效
		displayOrder = bundle.getInt(Constants.ColorMemoryTrainingUIParameter.DISPLAY_ORDER, 
				Constants.DisplayOrder.ORDER_BY_INDEX);
		//需测试的题目数，默认是15题
		questionCount = bundle.getInt(Constants.ColorMemoryTrainingUIParameter.QUESTION_COUNT, 15);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI----------");
		Log.d(TAG, "bitType:       " + bitType);
		Log.d(TAG, "startBit:      " + startBit);
		Log.d(TAG, "displayBody:   " + displayBody);
		Log.d(TAG, "displayMode:   " + displayMode);
		Log.d(TAG, "displayOrder:  " + displayOrder);
		Log.d(TAG, "questionCount: " + questionCount);
		
		
		if(displayBody == null){
			displayBodys = new int[]{Constants.DisplayBody.POINT};
		}else {
			String[] displayBodyStrs = displayBody.split(",");
			displayBodys = new int[displayBodyStrs.length];
			for (int i = 0; i < displayBodyStrs.length; i++) {
				displayBodys[i] = Integer.valueOf(displayBodyStrs[i]);
			}
		}
		
		//创建ChoosedSample对象并赋值
		ChoosedSample color = new ChoosedSample();
		color.setSample(Constants.Sample.COLORS);
		color.setSmapleElementChoosed(Constants.Colors.COLORS);
		
		/************为颜色记忆训练的service准备参数 **********/
		//创建ColorMemoryTrainingParameter对象
		ColorMemoryTrainingParameter parameter = new ColorMemoryTrainingParameter();
		parameter.setBitType(bitType);
		parameter.setStartBit(startBit);
		parameter.setQuestionCount(questionCount);
		parameter.setDisplayBodys(displayBodys);
		parameter.setDisplayMode(displayMode);
		parameter.setDisplayOrder(displayOrder);
		parameter.setColor(color);
		
		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		//添加ColorMemoryTrainingParameter
		parameters.put(Constants.PARAMETER, parameter);

		//创建颜色记忆训练规则，并作为参数
		ColorMemoryTrainingRule rule = new ColorMemoryTrainingRule();
		rule.setScore(2);
		parameters.put(Constants.RULE, rule);
		//将当前上下文作为参数
		parameters.put(Constants.CONTEXT, ColorMemoryTrainingActivity.this);
		/******************参数准备完毕, 以上参数均要满足接口要求，不可缺少**********************/

		//实例化"颜色记忆训练"服务
		service = new ColorMemoryTrainingServiceImpl();
		//根据参数初始化服务
		try {
			service.init(parameters);
		}catch (LackOfParametersException e) {
			e.printStackTrace();
			ActivityUtil.finish(ColorMemoryTrainingActivity.this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(ColorMemoryTrainingActivity.this);
		}
		
		//创建答错题目时的错误音播放器
		mediaPlayer = MediaPlayer.create(ColorMemoryTrainingActivity.this, R.raw.error);
		//如果播放器发生错误，将其释放
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.i(TAG, "PlayErrorMusic onError()");
				mediaPlayer.release();
				return false;
			}
		});

		timerView.init();
		timerView.start();
		
		//服务开始
		service.start();
		//创建题目
		qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
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
	 * 获取相关view
	 */
	private void setupViews(){
		
		//设置训练标题
		setTrainingTitle(getString(R.string.color_memory_training));
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
				
		//九个色块按钮以供回答使用
		imageViewTouchRed = (ImageView)findViewById(R.id.ImageView01);
		imageViewTouchOrange = (ImageView)findViewById(R.id.ImageView02);
		imageViewTouchYellow = (ImageView)findViewById(R.id.ImageView03);
		imageViewTouchGreen = (ImageView)findViewById(R.id.ImageView04);
		imageViewTouchBlue = (ImageView)findViewById(R.id.ImageView05);
		imageViewTouchIndigo = (ImageView)findViewById(R.id.ImageView06);
		imageViewTouchPurple = (ImageView)findViewById(R.id.ImageView07);
		imageViewTouchBlack = (ImageView)findViewById(R.id.ImageView08);
		imageViewTouchCoffee = (ImageView)findViewById(R.id.ImageView09);
		
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView01);
		relativeLayout103 = (RelativeLayout)findViewById(R.id.RelativeLayout103);
		timerView = (TimerView)findViewById(R.id.TimerView01);
	}
	
	/**
	 * 为各view设置监听器
	 */
	private void setupListeners(){
		
		imageViewTouchRed.setOnTouchListener(
				createOnTouchListener(imageViewTouchRed, Constants.Colors.BTN_IMAGE_RESOURCES[0], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[0],Constants.Colors.RED));
		imageViewTouchOrange.setOnTouchListener(
				createOnTouchListener(imageViewTouchOrange, Constants.Colors.BTN_IMAGE_RESOURCES[1], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[1],Constants.Colors.ORANGE));
		imageViewTouchYellow.setOnTouchListener(
				createOnTouchListener(imageViewTouchYellow, Constants.Colors.BTN_IMAGE_RESOURCES[2], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[2],Constants.Colors.YELLOW));
		imageViewTouchGreen.setOnTouchListener(
				createOnTouchListener(imageViewTouchGreen, Constants.Colors.BTN_IMAGE_RESOURCES[3], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[3],Constants.Colors.GREEN));
		imageViewTouchBlue.setOnTouchListener(
				createOnTouchListener(imageViewTouchBlue, Constants.Colors.BTN_IMAGE_RESOURCES[4], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[4],Constants.Colors.BLUE));
		imageViewTouchIndigo.setOnTouchListener(
				createOnTouchListener(imageViewTouchIndigo, Constants.Colors.BTN_IMAGE_RESOURCES[5], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[5],Constants.Colors.INDIGO));
		imageViewTouchPurple.setOnTouchListener(
				createOnTouchListener(imageViewTouchPurple, Constants.Colors.BTN_IMAGE_RESOURCES[6], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[6],Constants.Colors.PURPLE));
		imageViewTouchBlack.setOnTouchListener(
				createOnTouchListener(imageViewTouchBlack, Constants.Colors.BTN_IMAGE_RESOURCES[7], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[7],Constants.Colors.BLACK));
		imageViewTouchCoffee.setOnTouchListener(
				createOnTouchListener(imageViewTouchCoffee, Constants.Colors.BTN_IMAGE_RESOURCES[8], 
						Constants.Colors.BTN_IMAGE_RESOURCES_2[8],Constants.Colors.COFFEE));
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					countDownTimer.pause();
					timerView.pause();
					
					new AlertDialog.Builder(ColorMemoryTrainingActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.resume();
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
					countDownTimer.pause();
					timerView.pause();
					
					new AlertDialog.Builder(ColorMemoryTrainingActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							//startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
							countDownTimer.cancels();
							startActivity(new Intent(getApplicationContext(), ColorMemoryTrainingChooseParamsActivity.class));
							finish();
						}									
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.resume();
							timerView.resume();
						}									
					}).show();
				}
				return false;
			}
		});
	}

	
	/**创建图片的触摸时回调函数
	 * 
	 * @param imageView	
	 * @param btnResourceId   正常时的图片资源
	 * @param btnResourceId2	按下时的图片资源
	 * @param _answerCode	图片所代表的编码
	 * @return
	 */
	private ImageView.OnTouchListener createOnTouchListener(final ImageView imageView,
			final int btnResourceId, final int btnResourceId2, final int _answerCode){
		
		return new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (isServiceStoped) {
					return false;
				}
				
				if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
					if (question!=null && lastAnswerQuestionId != question.getId()) {
						//换掉图片
						imageView.setImageResource(btnResourceId2);

						//如果答题状态标志位TIME_UP状态,即可以答题
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
									
									String[] answers = new String[question.getAnswers().length];
									for (int i = 0; i < answerList.size(); i++) {
										answers[i] = answerList.get(i);
									}								
									answerList.clear();
									
									ColorMemoryTrainingAnswer answer = new ColorMemoryTrainingAnswer();
									answer.setQuestionId(question.getId());
									answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
									answer.setAnswers(answers);

									lastAnswerQuestionId = question.getId();
									//回答问题并得到结果
									ColorMemoryTrainingResult testResult = service.answerQuestion(question, answer);
									//如果回答正确,按照流程此处肯定是回答正确的
									if (testResult.getValue()) {
										//判读是否继续出题
										decideAction4AnswerRight();
									}
								}else {//等待用户按下一个按钮
									countDownTimer.starts();
								}
							}						
						}else {//答题状态标志位没有切换到TIME_UP,属于过早反应
							Log.d(TAG, "answer premature");
							if (flashThread != null) {
								flashThread.stopFalsh();
							}
							answerQuestionPremature();
							answerList.clear();
							decideAction4AnswerError();		
						}
					}
								
				
				}else if (event.getAction() == MotionEvent.ACTION_UP) {
					//按起时恢复图片
					imageView.setImageResource(btnResourceId);
				}
				return true;
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
			str += colorNames[Integer.valueOf(_answers[i])-Constants.Colors.RED] + " ";
		}
		
		Toast.makeText(ColorMemoryTrainingActivity.this, 
				preString+"\n" + getResources().getString(R.string.answers) + str, 
				Toast.LENGTH_LONG).show();
	}
	
	
	/**显示测试报告，实际上是跳转到专门显示测试报告的activity
	 * 
	 * @param testReport
	 */
	private void showTestReport(final ColorMemoryTrainingReport testReport){		

		new AlertDialog.Builder(ColorMemoryTrainingActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)	//此alertDialog不允许被取消
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				if (bitType == Constants.BitType.CONTINUED_BIT) {
					//如果是连续位元,跳转至TestReportWithTableActivity显示report
					intent.setClass(ColorMemoryTrainingActivity.this, TestReportWithTableActivity.class);
				}else {
					//如果不是连续位元,跳转至TestReportActivity显示report
					intent.setClass(ColorMemoryTrainingActivity.this, TestReportActivity.class);
				}
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, ColorMemoryTrainingActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, ColorMemoryTrainingChooseParamsActivity.class.getName());
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
	
	/**
	 * 回答问题错误
	 * @param errInfo
	 */
	private void answerQuestionError(int errInfo){		
		answerQuestionError(getString(errInfo));
	}
	
	/**回答过早,过早反应
	 * 
	 */
	private void answerQuestionPremature(){
		countDownTimer.cancels();
		if (question != null && lastAnswerQuestionId!=question.getId()) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			Toast.makeText(ColorMemoryTrainingActivity.this, R.string.premature, Toast.LENGTH_SHORT).show();
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
		mediaPlayer = MediaPlayer.create(ColorMemoryTrainingActivity.this, R.raw.error);
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
			if (question.getId() == questionCount) {
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
			if (question.getId() == questionCount) {
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
			if (totalErrorCount>=ColorMemoryTrainingRule.getCumulativeErrorCount(question.getContents().length) 
					|| perPeriodErrorCount>=3) {
				//测试结束
				qaHandler.sendMessage(qaHandler.obtainMessage(STOP_ANSWER));	
			}else {
				//重新出题
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		}
	}
	
	/**
	 * 检查是否已达到图样所支持的最大位元数
	 * @return
	 */
	private void reachesMaxBit4TuYang(){
		
		Log.i(TAG, "It's reached the max bit for TuYang, we will stop answer question...");

		//设置服务停止状态位
		isServiceStoped = true;
		//取消计时器
		countDownTimer.cancels();
		//服务停止
		service.stop();
	
		new AlertDialog.Builder(this)
			.setTitle(R.string.tips)
			.setMessage(R.string.pls_do_other_training)
			.setCancelable(false)	//此alertDialog不允许被取消
			.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent();
					if (bitType == Constants.BitType.CONTINUED_BIT) {
						//如果是连续位元,跳转至TestReportWithTableActivity显示report
						intent.setClass(ColorMemoryTrainingActivity.this, TestReportWithTableActivity.class);
					}else {
						//如果不是连续位元,跳转至TestReportActivity显示report
						intent.setClass(ColorMemoryTrainingActivity.this, TestReportActivity.class);
					}
					bundle.putSerializable(Constants.TEST_REPORT, service.generateReport());
					bundle.putString(Constants.CLASS_NAME, ColorMemoryTrainingActivity.class.getName());
					bundle.putString(Constants.PREVIOUS_ACTIVITY, ColorMemoryTrainingChooseParamsActivity.class.getName());
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			})
			.show();
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
	
	
	/**
	 * 将题目在屏幕上显示出来
	 */
	private void showQuestion(ColorMemoryTrainingQuestion question){
		//题目不能为空
		if (question == null) {
			return;
		}
		
		Log.d(TAG, "-------------question info-----------");
		Log.d(TAG, question.toString());
		Log.d(TAG, "-------------------------------------\n");
		
		//如果显示形体是点,线,曲线,或者形状
		if(displayBodys.length == 1){
			if(displayBodys[0] == Constants.DisplayBody.POINT || 
					displayBodys[0] == Constants.DisplayBody.LINE || 
					displayBodys[0] == Constants.DisplayBody.CURVE ||
					ArrayOperations.inElements(Constants.Shapes.SHAPES, String.valueOf(displayBodys[0]))){
				showSimpleDisplayBody(question);
			}else {
				showTuYangDisplayBody(question);
			}
		}else if(displayBodys.length > 1){
			showSimpleDisplayBody(question);
		}
		/**
		 * 此处还应该有显示形体为图案
		 */
	}
	
	/**消息处理
	 * 	
	 * @param imageViews
	 * @param linearLayout
	 * @return
	 */
	private Handler imageViewFlashHandler(
			final ImageView[] imageViews, final LinearLayout linearLayout){
		return new Handler(){

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case ACTION_1:
					Bundle bundle = msg.getData();
					int index = bundle.getInt(INDEX);
					boolean isShow = bundle.getBoolean(SHOW);
					Log.d(TAG, "index: " + index);
					Log.d(TAG, "isShow: " + isShow);
					imageViews[index].setVisibility(isShow?ImageView.VISIBLE:ImageView.INVISIBLE);
					break;
					
				case ACTION_2:
					linearLayout.setVisibility(LinearLayout.INVISIBLE);
					System.gc();
					break;
					
				case ACTION_3:
					bundle = msg.getData();
					index = bundle.getInt(INDEX);
					Log.d(TAG, "index: " + index);
					imageViews[index].setVisibility(ImageView.GONE);
					break;

				default:
					break;
				}
			}				
		};
	}
	
	
	/**显示那些显示形体是简单的题目
	 * 
	 * @param question
	 */
	private void showSimpleDisplayBody(ColorMemoryTrainingQuestion question){
		
		//取得编号为R.id.LinearLayout01的列布局
		linearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);
		//设置该布局为可见
		linearLayout.setVisibility(LinearLayout.VISIBLE);
		
		layoutTmp = new LinearLayout(this);
		//文件名
		String fileNameStr = "";
		int width = 0;
		int height = 0;
		int maxHeight = 0;
		int[] imageResourceIds = new int[question.getContents().length];
		Bitmap[] bitmaps = new Bitmap[question.getContents().length];
		
		//根据题目内容的数目创建相当数量的imageview数组
		ImageView[] imageViews = new ImageView[question.getContents().length];
		//拟将每题的内容转为相对应图片
		for (int i = 0; i < question.getContents().length; i++) {
			
			fileNameStr = getFileNamePre(Integer.valueOf(question.getDisplayBody()[i]));
			fileNameStr += Integer.valueOf(question.getContents()[i]) - Constants.Sample.COLORS;
			Log.d(TAG, "fileNameStr: " + fileNameStr);
			
			//根据文件名获得在R中的值
			int imageResourceId = getResources().getIdentifier(fileNameStr, "drawable", this.getPackageName());
			imageResourceIds[i] = imageResourceId;
			
			bitmap = BitmapFactory.decodeResource(getResources(), imageResourceId);
			bitmaps[i] = bitmap;
			width += bitmap.getWidth();
			if (maxHeight < bitmap.getHeight()) {
				maxHeight = bitmap.getHeight();
			}
			
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setImageResource(imageResourceId);
			
			//如果显示模式是显示后消失
			if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR) {
				imageView.setVisibility(ImageView.GONE);
			}else if (displayMode == Constants.DisplayMode.SHOW_HOLD) {//显示后保留
				imageView.setVisibility(ImageView.INVISIBLE);					
			}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {//同步显示同时消失
				imageView.setVisibility(ImageView.VISIBLE);
			}
			imageViews[i] = imageView;
			
			//当layoutTmp的宽度将大于relativeLayout103的宽度时重新建立一个新的临时布局
			if (width <= relativeLayout103.getWidth()) {
				layoutTmp.addView(imageView);	
			}else {
				linearLayout.addView(layoutTmp);
				height += maxHeight;
				maxHeight = 0;
				layoutTmp = new LinearLayout(this);
				layoutTmp.addView(imageView);
				width = bitmap.getWidth();
			}						
		}
		if (layoutTmp!=null && layoutTmp.getChildCount()>0) {
			linearLayout.addView(layoutTmp);
			height += maxHeight;
		}		
				
		
		if (height > relativeLayout103.getHeight()) {
			float scale = ((float)relativeLayout103.getHeight())/height;
			width = 0;
			layoutTmp = new LinearLayout(this);
			linearLayout.removeAllViews();
			
			//根据题目内容的数目创建相当数量的imageview数组
			imageViews = new ImageView[question.getContents().length];
			//将每题的内容转为相对应图片
			for (int i = 0; i < question.getContents().length; i++) {
				
				width += (int)(scale*bitmaps[i].getWidth());
				
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new LinearLayout.LayoutParams((int)(scale*bitmaps[i].getWidth()), (int)(scale*bitmaps[i].getHeight())));
				imageView.setImageResource(imageResourceIds[i]);
				
				//如果显示模式是显示后消失
				if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR) {
					imageView.setVisibility(ImageView.GONE);
				}else if (displayMode == Constants.DisplayMode.SHOW_HOLD) {//显示后保留
					imageView.setVisibility(ImageView.INVISIBLE);					
				}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {//同步显示同时消失
					imageView.setVisibility(ImageView.VISIBLE);
				}
				imageViews[i] = imageView;
				if (width <= relativeLayout103.getWidth()) {
					layoutTmp.addView(imageView);	
				}else {			
					linearLayout.addView(layoutTmp);
					layoutTmp = new LinearLayout(this);
					layoutTmp.addView(imageView);
					width = (int)(scale*bitmaps[i].getWidth());
				}			
			}
			
			if (layoutTmp!=null && layoutTmp.getChildCount()>0) {
				linearLayout.addView(layoutTmp);
			}
		}
		//更新布局
		linearLayout.invalidate();
		
		//创建handler以处理imageview
		final Handler handler = imageViewFlashHandler(imageViews, linearLayout);
		
		flashThread = new FlashThread(imageViews, handler, SIMPLE);
		flashThread.start();	
	}
	
	
	/**显示题目,这些题目的显示形体是图样
	 * 
	 * @param question
	 */
	private void showTuYangDisplayBody(ColorMemoryTrainingQuestion question){
		
		//取得编号为R.id.LinearLayout01的布局,并设为可见
		final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);
		linearLayout.setVisibility(LinearLayout.VISIBLE);
		
		//取得LayoutInflater
		LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
		//因为图样比较复杂,因此图样在color_tuyang_*_*.xml中的已做预先定义
		//此处需根据图样将该xml文件取出
		int layoutId = getResources().getIdentifier(
				"color_tuyang_" + (displayBodys[0]-Constants.DisplayBody.TuYang.LINE+1), "layout", this.getPackageName());
		
		//如果图样是圆形
		if (displayBodys[0] == Constants.DisplayBody.TuYang.CIRCLE) {
			//圆形图样指定位元的xml为color_tuyang_3_4
			if (bitType == Constants.BitType.GIVEN_BIT) {
				layoutId = getResources().getIdentifier(
						"color_tuyang_3_4", "layout", this.getPackageName());
			}else if (bitType == Constants.BitType.CONTINUED_BIT ||
					(bitType>=3 && bitType<=16)) {
				//圆形图样的连续位元或者自选位元
				layoutId = getResources().getIdentifier(
						"color_tuyang_3_" + question.getContents().length, "layout", this.getPackageName());					
			}else {
				Log.e(TAG, "the bitType is error");
				return;
			}
		}else if (displayBodys[0] == Constants.DisplayBody.TuYang.SECTOR) {
			//如果图样是扇形
			if (bitType == Constants.BitType.GIVEN_BIT) {
				layoutId = getResources().getIdentifier(
						"color_tuyang_8_1_4", "layout", this.getPackageName());
			}else if (bitType == Constants.BitType.CONTINUED_BIT || 
					(bitType>=3 && bitType<=30)) {
				//如果是连续位元或者自选位元
				layoutId = getResources().getIdentifier(
						getPreName4Sector(question.getContents().length), "layout", this.getPackageName());
			}else {
				Log.e(TAG, "the bitType is error");
				return;
			}				
		}
		
		//将该xml定义转为布局对象
		RelativeLayout relativeLayout = (RelativeLayout)layoutInflater.inflate(layoutId, null);
		//根据题目内容创建相应数目的imagviews
		final ImageView[] imageViews = new ImageView[question.getContents().length];
		String fileNameStr = "";
		String imageViewIdStr = "";
		int imageViewId = -1;
		
		//如果显示形体不为扇形
		if (displayBodys[0] != Constants.DisplayBody.TuYang.SECTOR) {
			for (int i = 0; i < question.getContents().length; i++) {
				//根据location确定imageview编号
				imageViewIdStr = "ImageView" + (question.getLocations()[i].length()<2?
						("0"+question.getLocations()[i]):question.getLocations()[i]);
				Log.d(TAG, "imageViewIdStr: " + imageViewIdStr);
				imageViewId = getResources().getIdentifier(imageViewIdStr, "id", this.getPackageName());
				ImageView imageView = (ImageView)relativeLayout.findViewById(imageViewId);
				
				//图片的名称大致为：cs_tuyang_item_图样序号_显示位置_颜色序号
				//根据显示形体和显示位置确定图片名前缀
				fileNameStr = getFileNamePre(displayBodys[0], Integer.valueOf(question.getLocations()[i]));
				//再根据颜色确定图片名后续
				fileNameStr += Integer.valueOf(question.getContents()[i]) - Constants.Sample.COLORS;
				Log.d(TAG, "fileNameStr: " + fileNameStr);
				int imageResourceId = getResources().getIdentifier(fileNameStr, "drawable", this.getPackageName());
				
				imageView.setImageResource(imageResourceId);
				if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR) {
					imageView.setVisibility(ImageView.INVISIBLE);
				}else if (displayMode == Constants.DisplayMode.SHOW_HOLD) {
					imageView.setVisibility(ImageView.INVISIBLE);					
				}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
					imageView.setVisibility(ImageView.VISIBLE);					
				}
				imageViews[i] = imageView;
			}				
		}else {//如果显示形体为扇形

			for (int i = 0; i < question.getContents().length; i++) {
				imageViewIdStr = "ImageView" + (question.getLocations()[i].length()<2?
						("0"+question.getLocations()[i]):question.getLocations()[i]);
				Log.d(TAG, "imageViewIdStr: " + imageViewIdStr);
				imageViewId = getResources().getIdentifier(imageViewIdStr, "id", this.getPackageName());
				ImageView imageView = (ImageView)relativeLayout.findViewById(imageViewId);
				
				int questionCount = question.getContents().length;
				if (questionCount>=3 && questionCount<=12) {
					fileNameStr = "cs_tuyang_item_8_1_";				
				}else if (questionCount>=13 && questionCount<=15) {
					fileNameStr = "cs_tuyang_item_8_2_";				
				}else if (questionCount>=16 && questionCount<=18) {
					fileNameStr = "cs_tuyang_item_8_3_";				
				}else if (questionCount>=19 && questionCount<=20) {
					fileNameStr = "cs_tuyang_item_8_4_";				
				}else if (questionCount>=21 && questionCount<=24) {
					fileNameStr = "cs_tuyang_item_8_5_";				
				}else if (questionCount>=25 && questionCount<=30) {
					fileNameStr = "cs_tuyang_item_8_6_";				
				}
				fileNameStr += question.getLocations()[i];
				
				Log.d(TAG, "fileNameStr: " + fileNameStr);
				int imageResourceId = getResources().getIdentifier(fileNameStr, "drawable", this.getPackageName());
								
				//扇形图样的item图片采用替换色素的方式改变颜色
				imageView.setImageBitmap(setColor(imageResourceId, Integer.valueOf(question.getContents()[i])));
				
				if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR) {
					imageView.setVisibility(ImageView.INVISIBLE);
				}else if (displayMode == Constants.DisplayMode.SHOW_HOLD) {
					imageView.setVisibility(ImageView.INVISIBLE);					
				}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
					imageView.setVisibility(ImageView.VISIBLE);					
				}
				imageViews[i] = imageView;
			}
		}
		
		//将产生的布局添加到linearLayout中并刷新
		linearLayout.addView(relativeLayout);
		linearLayout.invalidate();
		
		//因线程中不能操作widget,因此创建消息处理类以操作imageview
		final Handler handler = imageViewFlashHandler(imageViews, linearLayout);
		
		flashThread = new FlashThread(imageViews, handler, TU_YANG);
		//线程启动
		flashThread.start();
	}
	
	
	/**用线程确定闪动哪些图像，闪动图像
	 * 
	 * @author ZhangGuoYin
	 *
	 */
	class FlashThread extends Thread{
		private ImageView[] imageViews;
		private Handler handler;
		private int display;
		private boolean flag = true;
		
		public FlashThread(ImageView[] imageViews, Handler handler, int display) {
			super();
			this.imageViews = imageViews;
			this.handler = handler;
			this.display = display;
		}

		public void stopFalsh(){
			flag = false;
			interrupt();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Bundle bundle = new Bundle();
			Message msg;

			if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR) {//显示后消失
				for (int i = 0; i < imageViews.length; i++) {
					try {
						if (i == 0) {
							Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						}
						if (!flag) {
							return;
						}
						
						//先显示
						bundle.clear();
						msg = Message.obtain();
						msg.what = ACTION_1;
						bundle.putInt(INDEX, i);
						bundle.putBoolean(SHOW, true);
						msg.setData(bundle);
						handler.sendMessage(msg);
														
						Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						if (!flag) {
							return;
						}
						
						//再消失
						msg = Message.obtain();
						
						if(display == SIMPLE){
							msg.what = ACTION_3;
						}else if (display == TU_YANG) {
							msg.what = ACTION_1;
							bundle.putBoolean(SHOW, false);
						}else{
							
						}
						
						msg.setData(bundle);
						handler.sendMessage(msg);
						
						Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						if (!flag) {
							return;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(ACTION_2);
				
			}else if (displayMode == Constants.DisplayMode.SHOW_HOLD) {//显示后保留
				for (int i = 0; i < imageViews.length; i++) {
					try {
						if (i == 0) {
							Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						}
						if (!flag) {
							return;
						}
						
						bundle.clear();
						msg = Message.obtain();
						msg.what = ACTION_1;
						bundle.putInt(INDEX, i);
						bundle.putBoolean(SHOW, true);
						msg.setData(bundle);
						handler.sendMessage(msg);
						
						Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						if (!flag) {
							return;
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(ACTION_2);
				
			}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {//同步显示同时消失
				try {
					//显示时间跟位元存在对应关系
					sleep(CommonUtil.getDisplayTimeByBit(question.getContents().length));
					//sleep(10000);
					if (!flag) {
						return;
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(ACTION_2);
			}

			qaHandler.sendMessage(qaHandler.obtainMessage(START_ANSWER));
		}
	}
	
	
	/**针对简单的显示形体,取得相对应的图片名前缀
	 * 
	 * @param displayBody
	 * @return
	 */
	private String getFileNamePre(int displayBody){
		String pre = "";
		if (displayBody == Constants.DisplayBody.POINT) {
			pre = "cs_point_";
		}else if (displayBody == Constants.DisplayBody.LINE) {
			pre = "cs_line_";
		}else if (displayBody == Constants.DisplayBody.CURVE) {
			pre = "cs_curve_";
		}else if (displayBody>=Constants.Shapes.CIRCLE && displayBody<=Constants.Shapes.PENTAGON) {
			pre = "cs_shape_" + (displayBody-Constants.Sample.SHAPES) + "_";
		}
		return pre;
	}
	
	
	/**显示形体为图样的相对应图片前缀
	 * 
	 * @param displayBody
	 * @param location
	 * @return
	 */
	private String getFileNamePre(int displayBody, int location){
		String pre = "cs_tuyang_item_";
		pre += displayBody-Constants.DisplayBody.TuYang.LINE+1;
		
		if (displayBody == Constants.DisplayBody.TuYang.LINE || 
				displayBody == Constants.DisplayBody.TuYang.CURVE) {//显示形体为直线或者曲线
			//显示位置为1-6或者19-24
			if ((location>=1&&location<=6) || (location>=19&&location<=24)) {
				pre += "_1_";
			}else {//其余位置
				pre += "_2_";
			}
		}else if (displayBody == Constants.DisplayBody.TuYang.TRIGON) {//显示形体为三角形
			if ((location>=1&&location<=5) || (location>=11&&location<=14) ||
					(location>=19&&location<=23)) {//显示位置为1-5及11-14或者19-23
				pre += "_1_";
			}else {
				pre += "_2_";
			}
		}else if (displayBody == Constants.DisplayBody.TuYang.PARALLELOGRAM) {//显示形体为平行四边形
			if (location>=1&&location<=15) {//显示位置为1-15
				pre += "_1_";
			}else {
				pre += "_2_";
			}
		}else if (displayBody == Constants.DisplayBody.TuYang.RECTANGLE) {//显示形体为长方形
			if ((location>=1&&location<=5) || (location>=11&&location<=15) || 
					(location>=21&&location<=25)) {//显示位置为1-5,11-15,21-25
				pre += "_1_";
			}else {
				pre += "_2_";
			}			
		}else if (displayBody == Constants.DisplayBody.TuYang.PENTAGON) {//显示形体为五边形
			if (location%2!=0) {//显示位置为奇数位
				pre += "_1_";
			}else {
				pre += "_2_";
			}	
		}else {
			pre += "_";
		}
		return pre;
	}
	

	/**改变指定资源编号的图片的颜色
	 * 
	 * @param resId	图片在R中的资源编号
	 * @param ColorCode	颜色代码如Constants.Colors.RED,Constants.Colors.ORANGE等
	 * @return
	 */
	private Bitmap setColor(int resId, int ColorCode){
		Bitmap src = BitmapFactory.decodeResource(getResources(), resId);
		int color = 0;
		switch (ColorCode) {
		case Constants.Colors.RED:
			color = Color.parseColor("#b70f1b");
			break;
		case Constants.Colors.ORANGE:
			color = Color.parseColor("#ea7015");			
			break;
		case Constants.Colors.YELLOW:
			color = Color.parseColor("#ffdc00");			
			break;
		case Constants.Colors.GREEN:
			color = Color.parseColor("#61af0e");			
			break;
		case Constants.Colors.BLUE:
			color = Color.parseColor("#0eb8e0");			
			break;
		case Constants.Colors.INDIGO:
			color = Color.parseColor("#1f42a3");			
			break;
		case Constants.Colors.PURPLE:
			color = Color.parseColor("#5f0693");			
			break;
		case Constants.Colors.BLACK:
			color = Color.parseColor("#000000");			
			break;
		case Constants.Colors.COFFEE:
			color = Color.parseColor("#401c10");			
			break;

		default:
			break;
		}
		
		return getBitmap(src, color);
	}
    private Bitmap getBitmap(Bitmap src, int color){
    	
    	int[] pixels = new int[src.getWidth()*src.getHeight()];
    	src.getPixels(pixels, 0, src.getWidth(), 0, 0, src.getWidth(), src.getHeight());
    	
    	for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] != 0) {
				pixels[i] = color;
			}
		}
    	
    	Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
    	bitmap.setPixels(pixels, 0, src.getWidth(), 0, 0, src.getWidth(), src.getHeight());
    	
    	return bitmap;
    }
    
    //针对扇形图样的item的图片前缀
    private String getPreName4Sector(int questionCount){
    	String name = "";
		if (questionCount>=3 && questionCount<=12) {
			name = "color_tuyang_8_1_" + questionCount;				
		}else if (questionCount>=13 && questionCount<=15) {
			name = "color_tuyang_8_2";
		}else if (questionCount>=16 && questionCount<=18) {
			name = "color_tuyang_8_3";
		}else if (questionCount>=19 && questionCount<=20) {
			name = "color_tuyang_8_4";
		}else if (questionCount>=21 && questionCount<=24) {
			name = "color_tuyang_8_5";
		}else if (questionCount>=25 && questionCount<=30) {
			name = "color_tuyang_8_6";
		}
		
		return name;
    }
}