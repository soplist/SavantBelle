package com.flextronics.cn.activity.spatial;

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

import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingAnswer;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingParameter;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingQuestion;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingReport;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingResult;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingRule;
import com.flextronics.cn.service.spatial.SpatialMemoryTrainingService;
import com.flextronics.cn.service.spatial.SpatialMemoryTrainingServiceImpl;
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
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**<b>E-空间位置记忆训练</b><br>
 * 空间位置记忆训练
 * 
 * @author ZhangGuoYin
 *
 */
public class SpatialMemoryTrainingActivity extends BaseActivity {

	private final static String TAG = "SpatialMemoryTrainingActivity";
	private final static String INDEX = "INDEX";
	private final static String SHOW = "SHOW";
	private final static int ACTION_1 = 9001; 
	private final static int ACTION_2 = 9002; 
	private final static int ACTION_3 = 9003; 
	
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
	 * 显示模式
	 */
	private int displayMode;
	/**
	 * 样本群
	 */
	private String sampleSets;
	/**
	 * 题目数量
	 */
	private int questionCount;
	/**
	 * 回应类型
	 */
	private int answerType;
	/**
	 * 选定区域
	 */
	private String regions;
	
	/**
	 * 总共答错题目数量--针对连续位元而言
	 */
	private int totalErrorCount=0;
	/**
	 * 阶段内答错题目数量--针对连续位元而言
	 */
	private int perPeriodErrorCount=0;
	/**
	 * 答的上一题的编号
	 */
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
	 * 图片闪动所在的布局
	 */
	private RelativeLayout flashRelativeLayout;
	/**
	 * 答题按钮所在的布局
	 */
	private LinearLayout buttonsLayout;

	/**
	 * 空间位置记忆训练服务
	 */
	private SpatialMemoryTrainingService service;
	/**
	 * 空间位置记忆训练参数
	 */
	private SpatialMemoryTrainingParameter parameter;
	/**
	 * 空间位置记忆训练的问题
	 */
	private SpatialMemoryTrainingQuestion question;
	/**
	 * 空间位置记忆训练的测试报告
	 */
	private SpatialMemoryTrainingReport testReport;
	
	/**
	 * 第一次resume标志
	 */
	private boolean firstTime = true;
	/**
	 * 服务停止标志
	 */
	private boolean isServiceStoped;
	/**
	 * 样本是否为单一白光
	 */
	private boolean sampleIsOnlyWhiteLight;

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
	 * 闪动图片的线程
	 */
	private FlashThread flashThread;
	/**
	 * 存放36个Image view的数组
	 */
	private ImageView[] _36ImageViews;
	/**
	 * 显示错误答案的toast中最多显示的图片的数量
	 */
	private int maxImages;
	/**
	 * 答错题目时出现的toast中图片的LayoutParams
	 */
	private LinearLayout.LayoutParams toastImageViewLayoutParams;
	/**
	 * 答题按钮的背景图的LayoutParams
	 */
	private LinearLayout.LayoutParams buttonImageViewLayoutParams;
	/**
	 * 四个区域的名称
	 */
	private String[] regionNames;
	
	Handler qaHandler = new QuestionAnswerHandler();
	
	/**
	 * 显示下个位置--针对指定回应
	 */
	private Location location;
	/**
	 * 显示某个位置以提示用户回答--针对指定回应
	 */
	private FlashThread4SpecifyAnswer flashThread4Special;
	class QuestionAnswerHandler extends Handler{

		@Override
		public void handleMessage(Message msg) { //handle message 
			switch (msg.what) {
			case CREATE_QUESTION:
				
				//如果是指定回应, 停止闪烁
				if (answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER
						&& flashThread4Special!=null) {
					flashThread4Special.stopFalsh();
				}
				
				//如果服务已经停止
				if (isServiceStoped) {
					Log.i(TAG, "the service is stoped");
					return;
				}
				Log.i(TAG, "starting create question...");
				
				//将flashRelativeLayout设置为不可见
				flashRelativeLayout.setVisibility(View.INVISIBLE);
				if (!sampleIsOnlyWhiteLight) {
					//移除buttonsLayout中所有view,并设为不可见,最后刷新
					buttonsLayout.removeAllViews();
					buttonsLayout.setVisibility(View.INVISIBLE);
					buttonsLayout.invalidate();	
				}
				new CountDownTimer(Constants.MEMORY_TRAINING_WAITING_TIME, 
						Constants.MEMORY_TRAINING_WAITING_TIME){//此处是为了出题的时候有点间隔（暂定）

					@Override
					public void onFinish() {
						
						//创建题目
						try {
							question = service.createQuestion();
						} catch (LackOfParametersException e) {
							e.printStackTrace();
							ActivityUtil.finish(SpatialMemoryTrainingActivity.this);
						} catch (OutOfMaxQuestionsException e) {
							e.printStackTrace();
							reachesMax();
						} catch (ParameterIsInvalidException e) {
							e.printStackTrace();						
							ActivityUtil.finish(SpatialMemoryTrainingActivity.this);
						} catch (CanNotSupportSuchBitsException e) {
							e.printStackTrace();
							reachesMax();
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
				
				if (answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER) {
					Log.d(TAG, "The answer type is SPECIFY_ANSWER.");
					
					String[] redomedLocations = ArrayOperations.redomElements(question.getLocations());
					location = new Location(redomedLocations);
					flashThread4Special = new FlashThread4SpecifyAnswer(location.nextLocation());
					flashThread4Special.start();
				}
				
				//切换答题状态至STATE_TIME_UP
				startFlagImageView.setState(StartFlagImageView.STATE_TIME_UP);
				//切换答题状态灯至绿色
				startFlagImageView.setImageResource(R.drawable.green);
				//答题计时器开始
				countDownTimer.starts();
				
				break;
			case STOP_ANSWER:
				Log.i(TAG, "stopping answer question...");
				
				//如果是指定回应, 停止闪烁
				if (answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER
						&& flashThread4Special!=null) {
					flashThread4Special.stopFalsh();
				}
				
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
	
	class Location{
		private String[] locations;
		private int i = 0;

		public Location(String[] locations) {
			super();
			this.locations = locations;
		}
		
		/**下一个位置, 当位置为-1时表示已经取完了
		 * 
		 * @return
		 */
		private int nextLocation(){
			int location = -1;
			if (i < locations.length) {
				location = Integer.valueOf(locations[i]);
				i++;
			}
			return location;			
		}
	}
	
	/**
	 * 消息处理 -- 指定回应
	 */
	Handler specifyHandler = new Handler(){
		ImageView imageView;
		int id;
		String indexStr;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			indexStr = String.valueOf(msg.getData().getInt(INDEX));			
			indexStr = "ImageView" + (indexStr.length()<2?("0"+indexStr):indexStr);
			Log.d(TAG, "indexStr: " + indexStr);			

			id = getResources().getIdentifier(indexStr, "id", getPackageName());;
			imageView = (ImageView)flashRelativeLayout.findViewById(id);
			
			switch (msg.what) {
			case ACTION_1:				
				imageView.setImageResource(R.drawable.color_yellow);
				break;
			case ACTION_2:
				imageView.setImageResource(R.drawable.spatial_tuyang_item_0);
				break;

			default:
				break;
			}
		}		
	};
		
		
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
		
		int imageWidth = 35;
		maxImages = (int)(this.getResources().getDisplayMetrics().widthPixels*0.85)/imageWidth;
		toastImageViewLayoutParams = new LinearLayout.LayoutParams(imageWidth, imageWidth);
		buttonImageViewLayoutParams = new LinearLayout.LayoutParams(45, 45);
		regionNames = new String[]{"A", "B", "C", "D"};
		
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足E模块的接口规范************/
		bundle = this.getIntent().getExtras();
		//位元类型
		bitType = bundle.getInt(Constants.SpatialMemoryTrainingUIParameter.BIT_TYPE, 
				Constants.BitType.GIVEN_BIT);
		//开始位元,当位元类型为连续位元时有作用
		startBit = bundle.getInt(Constants.SpatialMemoryTrainingUIParameter.START_BIT, 3);
		//显示模式
		displayMode = bundle.getInt(Constants.SpatialMemoryTrainingUIParameter.DISPLAY_MODE, 
				Constants.DisplayMode.SHOW_HOLD);
		//需测试的题目数，默认是15题
		questionCount = bundle.getInt(Constants.SpatialMemoryTrainingUIParameter.QUESTION_COUNT, 15);
		//回应类型，默认是按顺序回应
		answerType = bundle.getInt(Constants.SpatialMemoryTrainingUIParameter.ANSWER_TYPE, 
				Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER);
		//选择区域，当回应类型是按区域回应的时候，需要提供此值
		regions = bundle.getString(Constants.SpatialMemoryTrainingUIParameter.REGION);
		//选择的样本群
		sampleSets = bundle.getString(Constants.SpatialMemoryTrainingUIParameter.SAMPLE_SET);
		//样本元素(预留信息，咱时不用)
		//sampleElements = bundle.getString(Constants.SpatialMemoryTrainingUIParameter.SAMPLE_ELEMENTS);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI----------");
		Log.d(TAG, "bitType:       " + bitType);
		Log.d(TAG, "startBit:      " + startBit);
		Log.d(TAG, "displayMode:   " + displayMode);
		Log.d(TAG, "questionCount: " + questionCount);
		Log.d(TAG, "answerType:    " + answerType);
		Log.d(TAG, "regions:       " + regions);
		Log.d(TAG, "sampleSets:    " + sampleSets);
		
		//创建ChoosedSample对象数组
		String[] samples = sampleSets.split(",");
		//String[] elementsInEachSample = sampleElements.split(";");
		ChoosedSample[] choosedSamples = new ChoosedSample[samples.length];
		for (int i = 0; i < samples.length; i++) {
			ChoosedSample choosedSample = new ChoosedSample();
			choosedSample.setSample(Integer.valueOf(samples[i]));
			//choosedSample.setSmapleElementChoosed(elementsInEachSample[i].split(","));
			choosedSamples[i] = choosedSample;
		}
				
		/************为空间位置记忆训练的service准备参数 **********/
		//创建SpatialMemoryTrainingParameter对象
		parameter = new SpatialMemoryTrainingParameter();
		parameter.setBitType(bitType);
		parameter.setStartBit(startBit);
		parameter.setChoosedSamples(choosedSamples);
		parameter.setAnswerType(answerType);
		if (answerType == Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER) {
			parameter.setRegions(regions.split(","));	
		}
		parameter.setDisplayMode(displayMode);
		parameter.setQuestionCount(questionCount);		
		
		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		//添加SpatialMemoryTrainingParameter
		parameters.put(Constants.PARAMETER, parameter);

		//创建空间位置记忆训练规则，并作为参数
		SpatialMemoryTrainingRule rule = new SpatialMemoryTrainingRule();
		rule.setScore(2);
		parameters.put(Constants.RULE, rule);
		//将当前context作为参数
		parameters.put(Constants.CONTEXT, SpatialMemoryTrainingActivity.this);
		/******************参数准备完毕, 以上参数均要满足接口要求**********************/

		//实例化"空间位置记忆训练"服务
		service = new SpatialMemoryTrainingServiceImpl();
		//根据参数初始化服务
		try {
			service.init(parameters);
		} catch (LackOfParametersException e) {
			e.printStackTrace();
			ActivityUtil.finish(SpatialMemoryTrainingActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
			ActivityUtil.finish(SpatialMemoryTrainingActivity.this);
		}
		
		//创建答错题目时的错误音播放器
		mediaPlayer = MediaPlayer.create(SpatialMemoryTrainingActivity.this, R.raw.error);
		//如果播放器发生错误，将其释放
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.d(TAG, "PlayErrorMusic onError()");
				mediaPlayer.release();
				return false;
			}				
		});

		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//如果样本群是单一的白光，则将R.layout.spatial_memory_training_1中定义的布局添加到父布局中，
		//否则将R.layout.spatial_memory_training_2中定义的布局添加到父布局中，并显示出来
		sampleIsOnlyWhiteLight = samples.length==1 && samples[0].equals(Constants.Sample.WHITE+"");
		if(sampleIsOnlyWhiteLight){
			layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
					R.layout.spatial_memory_training_1, null), getBaseLayoutParams());
		}else {
			layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
					R.layout.spatial_memory_training_2, null), getBaseLayoutParams());
		}
		
		//根据回应类型确定需要向LinearLayout01中添加显示的布局资源文件
		int layoutResId = -1;		
		if (answerType == Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER||
				answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER) {
			//如果回应类型是按顺序回应或者指定回应，则资源文件是R.layout.spatial_tuyang_1
			layoutResId = R.layout.spatial_tuyang_1;
		}else if (answerType == Constants.SpatialMemoryTrainingUIParameter.ORIENTATION_ANSWER) {
			//如果回应类型是按方位回应，则资源文件是R.layout.spatial_tuyang_2
			layoutResId = R.layout.spatial_tuyang_2;
		}else if (answerType == Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER) {
			//如果回应类型是按区域回应，则资源文件是R.layout.spatial_tuyang_3
			layoutResId = R.layout.spatial_tuyang_3;
		}
		//将资源文件定义的布局添加到R.id.LinearLayout01中
		((LinearLayout)layout.findViewById(R.id.LinearLayout01))
			.addView(getBaseLayoutInflater().inflate(layoutResId, null));
		setContentView(layout);
		
		//实例化答题列表
		answerList = new ArrayList<String>();
		
		//获得此activity中的各视图控件
		this.setupViews();
		//为各控件设置监听器
		this.setupListeners();
		
		//初始化timer view
		timerView.init();
		//启动timer view
		timerView.start();
		
		//问答服务开始
		service.start();
		//发送消息以创建题目
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
		setTrainingTitle(getString(R.string.spatial_memory_training));
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
		
		//答题状态灯
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView01);
		//计时器
		timerView = (TimerView)findViewById(R.id.TimerView01);
		//跳动显示的布局
		flashRelativeLayout = (RelativeLayout)findViewById(R.id.RelativeLayout_Flash);
		//flashRelativeLayout中的36个image view,这36个image view的ID是ImageView01-ImageView36
		_36ImageViews = new ImageView[flashRelativeLayout.getChildCount()];
		for (int i = 0; i < flashRelativeLayout.getChildCount(); i++) {
			_36ImageViews[i] = (ImageView)flashRelativeLayout.getChildAt(i);
		}
		//答题按钮
		if (!sampleIsOnlyWhiteLight) {
			buttonsLayout = (LinearLayout)findViewById(R.id.LinearLayoutButtons);			
		}
	}
	
	/**
	 * 为各view设置监听器
	 */
	private void setupListeners(){		
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					countDownTimer.pause();
					timerView.pause();
					
					new AlertDialog.Builder(SpatialMemoryTrainingActivity.this)
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
					
					new AlertDialog.Builder(SpatialMemoryTrainingActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							startActivity(new Intent(getApplicationContext(), ChooseParams4SpatialMemoryTrainingActivity.class));
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
		
		//如果样本为单一的白光,则答题的方式是点击闪动的图样上面的数字键
		if (sampleIsOnlyWhiteLight) {
			for (int i = 0; i < _36ImageViews.length; i++) {
				_36ImageViews[i].setOnTouchListener(createOnTouchListener(_36ImageViews[i], R.drawable.color_yellow, i+1));
			}
		}
	}

	
	/**创建触摸图片时的回调函数
	 * 
	 * @param imageView	
	 * @param btnResourceId2	按下时的图片资源
	 * @param _answerCode	图片所代表的编码
	 * @return
	 */
	private ImageView.OnTouchListener createOnTouchListener(final ImageView imageView,
			final int btnResourceId2, final int _answerCode){
		
		return new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (isServiceStoped) {
					return false;
				}
				
				Log.d(TAG, "answer code is " + _answerCode);
				
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
								showErrorInfo(R.string.answerIsError);
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
									
									SpatialMemoryTrainingAnswer answer = new SpatialMemoryTrainingAnswer();
									answer.setQuestionId(question.getId());
									answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
									answer.setAnswers(answers);

									lastAnswerQuestionId = question.getId();
									//回答问题并得到结果
									SpatialMemoryTrainingResult testResult = service.answerQuestion(question, answer);
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
					//按起时恢复图片至spatial_tuyang_item_0
					imageView.setImageResource(R.drawable.spatial_tuyang_item_0);
				}
				return true;
			}			
		};
	}
	
	
	/**创建点击按钮时回调函数
	 * 
	 * @param _answerCode 图片所代表的编码
	 * @return
	 */
	private Button.OnClickListener createBtnOnClickListener2(final int _answerCode){
		
		return new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (isServiceStoped) {
					return;
				}
				
				Log.d(TAG, "answer code is " + _answerCode);				
				
				if (question!=null && lastAnswerQuestionId != question.getId()) {
					
					//如果答题状态标志位TIME_UP状态,即可以答题
					if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
						//取消答题计时器
						countDownTimer.cancels();
						//将_answerCode添加到列表中
						answerList.add(String.valueOf(_answerCode));
						
						String currentAnswer = question.getContents()[
                          ArrayOperations.indexInElement(question.getLocations(), flashThread4Special.currentLocation()+"")];
						Log.d(TAG, "currentAnswer:"+currentAnswer) ;						
						
						if (!currentAnswer.equals(_answerCode+"")) {
							//如果指定位置答错，可以直接提示用户答题错误,进入下一题
							//无需等待用户把所有答案都按对为止
							Log.d(TAG, "_answerCode: " + _answerCode + " is wrong.");
							lastAnswerQuestionId = question.getId();
							service.answerQuestion(question, null);
							showErrorInfo(R.string.answerIsError);
							playErrorMusic();

							answerList.clear();
							//判读是否继续出题
							decideAction4AnswerError();
						}else {
							Log.d(TAG, "_answerCode: " + _answerCode + " is right.");
							
							if (answerList.size()==question.getAnswers().length) {//用户已经按全所有按键
								answerList.clear();
								
								SpatialMemoryTrainingAnswer answer = new SpatialMemoryTrainingAnswer();
								answer.setQuestionId(question.getId());
								answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
								answer.setAnswers(question.getAnswers());

								lastAnswerQuestionId = question.getId();
								//回答问题并得到结果
								SpatialMemoryTrainingResult testResult = service.answerQuestion(question, answer);
								//如果回答正确,按照流程此处肯定是回答正确的
								if (testResult.getValue()) {
									//判读是否继续出题
									decideAction4AnswerRight();
								}
							}else {//中指当前闪动，进入下一个闪动
								flashThread4Special.stopFalsh();
								flashThread4Special = new FlashThread4SpecifyAnswer(location.nextLocation());
								flashThread4Special.start();
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
			}			
		};
	}
	

	/**创建点击按钮时回调函数
	 * 
	 * @param _answerCode 图片所代表的编码
	 * @return
	 */
	private Button.OnClickListener createBtnOnClickListener(final int _answerCode){
		
		return new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				if (isServiceStoped) {
					return;
				}
				
				Log.d(TAG, "answer code is " + _answerCode);				
				
				if (question!=null && lastAnswerQuestionId != question.getId()) {
					
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
							showErrorInfo(R.string.answerIsError);
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
								
								SpatialMemoryTrainingAnswer answer = new SpatialMemoryTrainingAnswer();
								answer.setQuestionId(question.getId());
								answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
								answer.setAnswers(answers);

								lastAnswerQuestionId = question.getId();
								//回答问题并得到结果
								SpatialMemoryTrainingResult testResult = service.answerQuestion(question, answer);
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
			}			
		};
	}
	
	
	/**弹出一个toast,以告知用户错误信息
	 * 
	 * @param resId
	 */
	private void showErrorInfo(int resId){
		showErrorInfo(getString(resId));
	}
	
	/**弹出一个toast,以告知用户错误信息
	 * 
	 * @param preString
	 */
	private void showErrorInfo(String preString){

		preString += ", " + getString(R.string.answers);
		String[] _answers;
		if (answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER) {
			_answers = new String[]{question.getContents()[
			           ArrayOperations.indexInElement(question.getLocations(), flashThread4Special.currentLocation()+"")]};
		} else {
			_answers = question.getAnswers();
		}
		
		//如果样本是单一白光，则错误消息应该是位置;
		//否则应该是按钮图片
		if (sampleIsOnlyWhiteLight) {
			String str = "\n";
			if (answerType == Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER) {
				//最终显示的错误信息如：答案是：A1 B3 A4 D8
				for (int i = 0; i < _answers.length; i++) {
					int answerCode = Integer.valueOf(_answers[i]);
					str += regionNames[(answerCode-1)/9] + (answerCode%9==0?"9":(answerCode%9+"")) + " ";
				}
			}else if (answerType == Constants.SpatialMemoryTrainingUIParameter.ORIENTATION_ANSWER) {	
				//最终显示的错误信息如：答案是：1 3 5 6 7 9
				for (int i = 0; i < _answers.length; i++) {
					str += _answers[i] + " ";
				}
			}else if (answerType == Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER) {
				Toast.makeText(SpatialMemoryTrainingActivity.this, 
						preString.split(",")[0], Toast.LENGTH_SHORT).show();
				return;
			}
			
			Toast.makeText(SpatialMemoryTrainingActivity.this, preString + str, 
					Toast.LENGTH_SHORT).show();
		}else {
			//创建一个列布局
			LinearLayout baseLayout = new LinearLayout(this);
			//设置为纵向排列
			baseLayout.setOrientation(LinearLayout.VERTICAL);
			//创建一个文本视图
			TextView textView = new TextView(this);
			textView.setText(preString);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(18);
			textView.setGravity(Gravity.CENTER_HORIZONTAL);
			//将文本视图添加到布局中
			baseLayout.addView(textView);
					
			//新建一列布局,设置为横向
			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			
			for (int i = 0; i < _answers.length; i++) {			

				if (i%maxImages==0 && i>0) {
					baseLayout.addView(layout);
					layout = new LinearLayout(this);
					layout.setOrientation(LinearLayout.HORIZONTAL);
				}
				
				ImageView imageView = new ImageView(this);
				imageView.setLayoutParams(toastImageViewLayoutParams);
				imageView.setImageResource(CommonUtil.getBtnImageResBySampleElement(
						Integer.valueOf(_answers[i])));
				layout.addView(imageView);			
			}
			
			baseLayout.addView(layout);
			Toast toast = new Toast(getApplicationContext());
			toast.setView(baseLayout);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	
	/**显示测试报告，实际上是跳转到专门显示测试报告的activity
	 * 
	 * @param testReport
	 */
	private void showTestReport(final SpatialMemoryTrainingReport testReport){		

		new AlertDialog.Builder(SpatialMemoryTrainingActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)	//此alertDialog不允许被取消
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				
				Intent intent = new Intent();
				if (bitType == Constants.BitType.CONTINUED_BIT) {
					//如果是连续位元,跳转至TestReportWithTableActivity显示report
					intent.setClass(SpatialMemoryTrainingActivity.this, TestReportWithTableActivity.class);
				}else {
					//如果不是连续位元,跳转至TestReportActivity显示report
					intent.setClass(SpatialMemoryTrainingActivity.this, TestReportActivity.class);
				}
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, SpatialMemoryTrainingActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, ChooseParams4SpatialMemoryTrainingActivity.class.getName());
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
			Toast.makeText(SpatialMemoryTrainingActivity.this, R.string.premature, Toast.LENGTH_SHORT).show();
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
		mediaPlayer = MediaPlayer.create(SpatialMemoryTrainingActivity.this, R.raw.error);
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
			if (totalErrorCount>=SpatialMemoryTrainingRule.getCumulativeErrorCount(question.getContents().length) 
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
	 * 达到最大元或者最多题目数
	 * @return
	 */
	private void reachesMax(){
		
		Log.i(TAG, "It's reached the total number of question or the max bit, we will stop answer question...");

		//设置服务停止状态位
		isServiceStoped = true;
		//取消计时器
		countDownTimer.cancels();
		//服务停止
		service.stop();
		//生成测试报告并显示
		testReport = service.generateReport();
	
		new AlertDialog.Builder(this)
			.setTitle(R.string.tips)
			.setMessage(R.string.pls_do_other_training)
			.setCancelable(false)	//此alertDialog不允许被取消
			.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent();
					if (bitType == Constants.BitType.CONTINUED_BIT) {
						//如果是连续位元,跳转至TestReportWithTableActivity显示report
						intent.setClass(SpatialMemoryTrainingActivity.this, TestReportWithTableActivity.class);
					}else {
						//如果不是连续位元,跳转至TestReportActivity显示report
						intent.setClass(SpatialMemoryTrainingActivity.this, TestReportActivity.class);
					}
					bundle.putSerializable(Constants.TEST_REPORT, testReport);
					bundle.putString(Constants.CLASS_NAME, SpatialMemoryTrainingActivity.class.getName());
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
	private void showQuestion(SpatialMemoryTrainingQuestion question){
		/*//题目不能为空
		if (question == null) {
			return;
		}*/
		
		Log.d(TAG, "-------------question info-----------");
		Log.d(TAG, question.toString());
		Log.d(TAG, "-------------------------------------\n");
		
		showTuYang(question);
	}
		
	
	/**消息处理
	 * 	
	 * @param imageViews
	 * @param relativeLayout
	 * @return
	 */
	private Handler flashImageViewHandler(
			final ImageView[] imageViews, final RelativeLayout relativeLayout){
		return new Handler(){

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case ACTION_1:
					//根据INDEX, SHOW对应的值设置image view可见与否
					Bundle bundle = msg.getData();
					int index = bundle.getInt(INDEX);
					boolean isShow = bundle.getBoolean(SHOW);
					Log.d(TAG, "index: " + index);
					Log.d(TAG, "isShow: " + isShow);
					imageViews[index].setVisibility(isShow?ImageView.VISIBLE:ImageView.INVISIBLE);
					break;
					
				case ACTION_2:
					//将imageViews中所有元素的图片设置为spatial_tuyang_item_0
					for (int i = 0; i < imageViews.length; i++) {
						imageViews[i].setImageResource(R.drawable.spatial_tuyang_item_0);
					}
					System.gc();
					break;
					
				case ACTION_3:
					//如果buttonValues不为空(即样本非单一白光)
					if (question.getButtonValues() != null) {
						//设定image view 的宽高为45
						int imageWidth = 45;
						String[][] buttonValues = question.getButtonValues();
						for (int i = 0; i < buttonValues.length; i++) {
							if (buttonValues[i] != null) {
								//存放image view的列布局
								LinearLayout layout = new LinearLayout(getApplicationContext());
								int width = 0;
								
								for (int j = 0; j < buttonValues[i].length; j++) {
									Log.d(TAG, "ButtonValues[" + i +"][" + j + "]:" + buttonValues[i][j]);
									
									/*ImageView imageView = new ImageView(getApplicationContext());
									//宽高设定
									imageView.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageWidth));
									//设置图片
									int resId = CommonUtil.getBtnImageRes4SampleElement(
											Integer.valueOf(buttonValues[i][j]));
									imageView.setImageResource(resId);
									//width += ((BitmapDrawable)imageView.getDrawable()).getBitmap().getWidth();
									imageView.setOnTouchListener(createOnTouchListener(imageView, resId, R.drawable.btn_color_white, Integer.valueOf(buttonValues[i][j])));
									//累计宽度
									width += imageWidth;
									//如果宽度超过buttonsLayout的宽度，需要新建个layout
									if (width > buttonsLayout.getWidth()) {
										buttonsLayout.addView(layout);
										layout = new LinearLayout(getApplicationContext());
										width = imageWidth;
										layout.addView(imageView);
									}else {
										layout.addView(imageView);
									}*/
									
									Button button = new Button(getApplicationContext());
									//宽高设定
									button.setLayoutParams(buttonImageViewLayoutParams);
									//设置按钮的背景图片
									button.setBackgroundResource(CommonUtil.getBtnImageResBySampleElement(
											Integer.valueOf(buttonValues[i][j])));
									if (answerType == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER) {
										button.setOnClickListener(createBtnOnClickListener2(Integer.valueOf(buttonValues[i][j])));										
									}else {
										button.setOnClickListener(createBtnOnClickListener(Integer.valueOf(buttonValues[i][j])));										
									}
									//累计宽度
									width += imageWidth;
									//如果宽度超过buttonsLayout的宽度，需要新建个layout
									if (width > buttonsLayout.getWidth()) {
										buttonsLayout.addView(layout);
										layout = new LinearLayout(getApplicationContext());
										width = imageWidth;
										layout.addView(button);
									}else {
										layout.addView(button);
									}
								}
								buttonsLayout.addView(layout);
							}
						}
						
						//设置buttonsLayout为可见并刷新之
						buttonsLayout.setVisibility(View.VISIBLE);
						buttonsLayout.invalidate();
					}
					break;

				default:
					break;
				}
			}				
		};
	}	
	
	
	/**显示题目,这些题目的显示形体是图样
	 * 
	 * @param question
	 */
	private void showTuYang(SpatialMemoryTrainingQuestion question){
		
		//需要显示的图片名称
		String imageViewIdStr = "";
		//需要显示的image view的ID
		int imageViewId = -1;
		//根据题目的内容创建相对应数目的image view数组
		ImageView[] imageViews = new ImageView[question.getContents().length];
		//遍历题目
		for (int i = 0; i < question.getContents().length; i++) {
			//根据每个location确定image view的ID
			imageViewIdStr = "ImageView" + (question.getLocations()[i].length()<2?
					("0"+question.getLocations()[i]):question.getLocations()[i]);
			Log.d(TAG, "imageViewIdStr: " + imageViewIdStr);
			//获得该ID的image view在R中的值
			imageViewId = getResources().getIdentifier(imageViewIdStr, "id", this.getPackageName());
			//从flashRelativeLayout找到该image view
			ImageView imageView = (ImageView)flashRelativeLayout.findViewById(imageViewId);
			
			//根据题目内容，即样本元素编码，取得图片资源在R中的值
			int imageResourceId = CommonUtil.getImageResBySampleElement(
					Integer.valueOf(question.getContents()[i]));
			Log.d(TAG, "imageResourceId: " + imageResourceId);
			//将图片资源赋给image view
			imageView.setImageResource(imageResourceId);
			
			//如果显示模式是显示后消失或者显示后保留，则将image view设为不可见;
			//如果显示模式是同步显示同时消失，则将image view设为可见
			if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR ||
					displayMode == Constants.DisplayMode.SHOW_HOLD) {
				imageView.setVisibility(ImageView.INVISIBLE);
			} else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
				imageView.setVisibility(ImageView.VISIBLE);
			}
			//将image view赋给imageViews[i]
			imageViews[i] = imageView;
		}
		
		//对于36个image views，如果该image view不在imageViews[]中出现，即没有设置图片，
		//则将该image view 的图片设置为spatial_tuyang_item_0
		for (int i = 0; i < _36ImageViews.length; i++) {
			if (!inImageViews(imageViews, _36ImageViews[i])) {
				_36ImageViews[i].setImageResource(R.drawable.spatial_tuyang_item_0);
			}
		}
		//设置flashRelativeLayout为可见
		flashRelativeLayout.setVisibility(View.VISIBLE);
		
		//因线程中不能操作widget,因此创建消息处理类以操作image view
		final Handler handler = flashImageViewHandler(imageViews, flashRelativeLayout);
		
		flashThread = new FlashThread(imageViews, handler);
		//线程启动
		flashThread.start();
	}
	
	
	/**判断一个image view是否存在于一个ImageView[]中
	 * 
	 * @param imageViews
	 * @param imageView
	 * @return
	 */
	private boolean inImageViews(ImageView[] imageViews, ImageView imageView){
		if (imageViews==null || imageView==null) {
			return false;
		}
		for (int i = 0; i < imageViews.length; i++) {
			if (imageViews[i].getId() == imageView.getId()) {
				Log.d(TAG, "ImageView" + i + " is in imageviews.");
				return true;
			}
		}
		return false;
	}
	
	
	/**用线程确定闪动哪些图像，闪动图像
	 * 
	 * @author ZhangGuoYin
	 *
	 */
	class FlashThread extends Thread{
		/**
		 * 需要操作的image views
		 */
		private ImageView[] imageViews;
		/**
		 * 通过Handler操作image view
		 */
		private Handler handler;
		private boolean flag = true;
		
		public FlashThread(ImageView[] imageViews, Handler handler) {
			super();
			this.imageViews = imageViews;
			this.handler = handler;
		}

		public void stopFalsh(){
			flag = false;
			interrupt();
		}
		
		@Override
		public void run() {
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
						msg.what = ACTION_1;
						bundle.putBoolean(SHOW, false);
						msg.setData(bundle);
						handler.sendMessage(msg);
						
						Thread.sleep(Constants.DEFAULT_SHOW_TIME);
						if (!flag) {
							return;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
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
						e.printStackTrace();
					}
				}
				
			}else if (displayMode == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {//同步显示同时消失
				try {
					//显示时间跟位元存在对应关系
					sleep(CommonUtil.getDisplayTimeByBit(question.getContents().length));
					if (!flag) {
						return;
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			handler.sendEmptyMessage(ACTION_2);
			if (!sampleIsOnlyWhiteLight) {
				//发送消息显示答题按钮
				handler.sendEmptyMessage(ACTION_3);	
			}
			qaHandler.sendMessage(qaHandler.obtainMessage(START_ANSWER));
		}
	}	
	
	
	/**用于指定回应, 闪动指定图像以提示用户回答
	 * 
	 * @author ZhangGuoYin
	 *
	 */
	class FlashThread4SpecifyAnswer extends Thread{
		private boolean flag = true;
		private Message msg;
		private Bundle bundle;
		private int location;
		
		public FlashThread4SpecifyAnswer(int location) {
			super();
			this.location = location;
		}
		
		public int currentLocation(){
			return location;
		}
		
		public void stopFalsh(){
			flag = false;
			//发送消息将Image view的图像还原
			msg = Message.obtain();
			msg.what = ACTION_2;
			msg.setData(bundle);
			specifyHandler.sendMessage(msg);
			//中断线程
			interrupt();
		}
				
		@Override
		public void run() {
			super.run();
			bundle = new Bundle();
			try {
				while (flag) {
					if (location < 0) {
						stopFalsh();
					}
					
					sleep(Constants.FLASH_TIME);
					if (!flag) {
						break;
					}										
					bundle.clear();
					bundle.putInt(INDEX, location);
					msg = Message.obtain();
					msg.what = ACTION_1;
					msg.setData(bundle);					
					specifyHandler.sendMessage(msg);
					
					sleep(Constants.FLASH_TIME);
					if (!flag) {
						break;
					}
					msg = Message.obtain();
					msg.what = ACTION_2;
					msg.setData(bundle);
					specifyHandler.sendMessage(msg);
				}				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}