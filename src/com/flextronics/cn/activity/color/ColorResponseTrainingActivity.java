package com.flextronics.cn.activity.color;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingAnswer;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingParameter;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingQuestion;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingReport;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingResult;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingRule;
import com.flextronics.cn.service.color.ColorResponseTrainingService;
import com.flextronics.cn.service.color.ColorResponseTrainingServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.Constants;
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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

/**<b>C-颜色辨识深度训练</b><br>
 * 反应训练 
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorResponseTrainingActivity extends BaseActivity {

	private final static String TAG = "ColorResponseTrainingActivity";

	/**
	 * 用于接收参数
	 */
	private Bundle bundle ;
	/**
	 * 用于接收样本群
	 */
	private String sampleSet;
	/**
	 * 用于接收样本元素
	 */
	private String sampleElements;
	/**
	 * 用于接收显示形体
	 */
	private String displayBody;
	/**
	 * 用于接收题目总数
	 */
	private int questionCount;
	
	/**
	 * 上次回答的题目编号
	 */
	private long lastAnswerQuestionId = -1;
	
	/**
	 * 答题标志灯
	 */
	private StartFlagImageView startFlagImageView;
	/**
	 * 秒表
	 */
	private TimerView timerView;
	/**
	 * 用于显示题目
	 */
	private ImageView flashImageView;

	/**
	 * 错误提示音播放器
	 */
	private MediaPlayer mediaPlayer;
	
	/**
	 * 问答服务
	 */
	private ColorResponseTrainingService service;
	/**
	 * 题目
	 */
	private ColorResponseTrainingQuestion question;
	
	/**
	 * activity第一次onResume标志位
	 */
	private boolean firstTime = true;
	/**
	 * "颜色反应训练" 服务停止标志位
	 */
	private boolean isServiceStoped;	

	private String[] colors;
		
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.color_response_training中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.color_response_training, null), getBaseLayoutParams());
		setContentView(layout);
		
		//获得此activity中的各视图控件
		this.setupViews();
		//为各控件设置监听器
		this.setupListeners();
				
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足C模块的接口规范***********/
		bundle = this.getIntent().getExtras();
		//样本群，此处是颜色群编码
		sampleSet = bundle.getString(Constants.ColorResponseTrainingUIParameter.SAMPLE_SET);
		//对应的样本群中的元素，此处是各颜色编码组成的字符串
		sampleElements = bundle.getString(Constants.ColorResponseTrainingUIParameter.SAMPLE_ELEMENTS);
		//显示形体
		displayBody = bundle.getString(Constants.ColorResponseTrainingUIParameter.DISPLAY_BODY);
		//需测试的题目数，默认是15题
		questionCount = bundle.getInt(Constants.ColorResponseTrainingUIParameter.QUESTION_COUNT, 15);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI----------");
		Log.d(TAG, "sampleSet: " + sampleSet);
		Log.d(TAG, "sampleElements: " + sampleElements);
		Log.d(TAG, "displayBody: " + displayBody);
		Log.d(TAG, "questionCount: " + questionCount);
		
		//创建ChoosedSample对象并赋值
		ChoosedSample color = new ChoosedSample();
		color.setSample(Integer.valueOf(sampleSet));
		color.setSmapleElementChoosed(sampleElements.split(","));
		
		//将显示形体由字符串转为数组
		String[] displayBodyStrs = displayBody.split(",");
		int[] displayBody = new int[displayBodyStrs.length];
		for (int i = 0; i < displayBodyStrs.length; i++) {
			displayBody[i] = Integer.valueOf(displayBodyStrs[i]);
		}
		
		/************为颜色反应训练的service准备参数 **********/
		//创建ColorResponseTrainingParameter对象
		ColorResponseTrainingParameter parameter = new ColorResponseTrainingParameter();
		parameter.setQuestionCount(questionCount);
		parameter.setColor(color);
		parameter.setDisplayBody(displayBody);

		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		//添加ColorResponseTrainingParameter
		parameters.put(Constants.PARAMETER, parameter);
		
		//创建颜色反应训练规则，并作为参数
		ColorResponseTrainingRule rule = new ColorResponseTrainingRule();
		rule.setScore(2);
		parameters.put(Constants.RULE, rule);
		//将当前上下文作为参数
		parameters.put(Constants.CONTEXT, getApplicationContext());
		/******************参数准备完毕, 以上参数均为必须，不可缺少**********************/

		//实例化"颜色反应训练"服务
		service = new ColorResponseTrainingServiceImpl();
		//根据参数初始化服务
		try {
			service.init(parameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}		
		
		//initialize the timer view
		//timerView.setEnd(time);
		timerView.init();
		
		//初始化答题标志灯
		startFlagImageView.init();
		//标志灯变绿的时候的回调函数
		startFlagImageView.setOnTimeUpListener(new StartFlagImageView.OnTimeUpListener(){

			public void onTimeUp() {

				timerView.start();
				//"颜色反应训练"服务 启动
				service.start();
				//创建题目
				createQuestionAction();
			}
		});

		//创建回答错误播放错误提示音的MediaPlayer
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.error);
		//如果播放器发生错误，将其释放
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.e(TAG, "PlayErrorMusic onError()");
				mediaPlayer.release();
				return false;
			}				
		});
		
		//亮起标志灯（一般为红）,一段时间后将变绿
		//红色阶段是不能答题的，答题即为过早反应，需计错误一次；绿色方可答题
		startFlagImageView.start();
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
		
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 禁用键盘上的HOME,后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 创建题目的相关操作
	 */
	private void createQuestionAction(){
		//如果服务已经停止
		if (isServiceStoped) {
			Log.i(TAG, "service is stoped");
			return;
		}
		Log.d(TAG, "starting create question...");
		
		//取得编号为R.id.LinearLayout01的布局，移除其中所有的视图并刷新
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);
		linearLayout.removeAllViews();
		linearLayout.invalidate();
		
		//生成题目并显示
		question = service.createQuestion();
		if (question == null) {
			return;
		}
		showQuestion(question);
	}
		
	/**
	 * 停止问答的相关操作
	 */
	private void stopAnswerAction(){
		Log.i(TAG, "stopping answer question...");

		//设置服务停止状态位
		isServiceStoped = true;
		//取消计时器
		countDownTimer.cancels();
		//服务停止
		service.stop();
		timerView.stop();
		//生成报告并显示
		showTestReport(service.generateReport());		
	}
		
	/**
	 * 获取相关view
	 */
	private void setupViews(){
		//设置训练标题
		setTrainingTitle(getString(R.string.color_response_training));
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
		
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView01);
		timerView = (TimerView)findViewById(R.id.TimerView01);
		
		flashImageView = (ImageView)findViewById(R.id.ImageView01);
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
					
					new AlertDialog.Builder(ColorResponseTrainingActivity.this)
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
					
					new AlertDialog.Builder(ColorResponseTrainingActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							startActivity(new Intent(getApplicationContext(), ColorResponseTrainingChooseParamsActivity.class));
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
		

	/**显示测试报告，实际上是跳转到专门显示测试报告的activity
	 * 
	 * @param testReport
	 */
	private void showTestReport(final ColorResponseTrainingReport testReport){		

		new AlertDialog.Builder(ColorResponseTrainingActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)	//此alertDialog不允许被取消
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(ColorResponseTrainingActivity.this, TestReportActivity.class);
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, ColorResponseTrainingActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, ColorResponseTrainingChooseParamsActivity.class.getName());
				intent.putExtras(bundle);
				startActivity(intent);
				
				finish();
			}
		})
		.show();
	}	
	
	/**答题错误
	 * 
	 * @param errInfo	错误消息
	 */
	private void answerQuestionError(String errInfo){
		countDownTimer.cancels();
		if (question != null) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			Toast.makeText(getApplicationContext(), errInfo, Toast.LENGTH_SHORT).show();
			playErrorMusic();

			//检查所答题目是否已是最后一题
			if (question.getId() >= questionCount) {
				//停止回答
				stopAnswerAction();
			}else {
				//创建题目
				createQuestionAction();
			}
		}else {
			if (!isServiceStoped) {
				countDownTimer.starts();				
			}
		}
	}
	
	/**答题错误
	 * 
	 * @param resId	错误消息
	 */
	private void answerQuestionError(int resId){
		answerQuestionError(getString(resId));
	}
	
	
	private void playErrorMusic(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.error);
		mediaPlayer.start();
	}
	
	
	/**
	 *显示答题按钮
	 */
	private void showOperationButtons(){
		//取得编号为R.id.LinearLayout01的布局，答题按钮将作为该布局的子元素
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);	
		
		//答题按钮每次都需要随机排列，第一次的时候将样本元素进行随机排列，
		//此后都将前一次的排列再次进行随机排列
		if (colors == null) {
			colors = ArrayOperations.redomElements(Constants.Colors.COLORS);
		}else {
			colors = ArrayOperations.redomElements(colors);
		}
		int index;
		
		//创建列布局，设置为横向
		LinearLayout layout2 = new LinearLayout(this);
		layout2.setOrientation(LinearLayout.HORIZONTAL);
		//创建各颜色按钮
		for (int i = 0; i < colors.length; i++) {
			index = ArrayOperations.indexInElement(Constants.Colors.COLORS, colors[i]);
			createAndAddImageViews(layout2, Constants.Colors.BTN_IMAGE_RESOURCES[index], 
					Constants.Colors.BTN_IMAGE_RESOURCES_2[index], colors[i]);
		}
		
		//将前述列布局加入到编号为R.id.LinearLayout01的布局中并刷新
		linearLayout.addView(layout2);
		linearLayout.invalidate();
	}
	
	
	/**创建颜色按钮并添加到指定布局中
	 * 
	 * @param layout	用于添加颜色按钮的布局
	 * @param imageResource1	颜色按钮初始状态下的图片
	 * @param imageResource2	颜色按钮按下时的图片
	 * @param code	按钮的编码
	 */
	private void createAndAddImageViews(ViewGroup layout, int imageResource1, int imageResource2, String code){

		final ImageView imageView = new ImageView(this);
		imageView.setImageResource(imageResource1);
		//四周留8个像素的空间
		LayoutParams layoutParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(8, 8, 8, 8);
		imageView.setLayoutParams(layoutParams);
		//为颜色按钮设置触摸响应
		imageView.setOnTouchListener(createOnTouchListener(imageView, imageResource1,
				imageResource2, code));
		//添加到指定布局中
		layout.addView(imageView);
	}
	
	/**创建图片按钮的OnTouchListener
	 * 
	 * @param imageView	
	 * @param btnResourceId	     颜色按钮初始状态下的图片
	 * @param btnResourceId2	颜色按钮按下时的图片
	 * @param answerCode	按钮的编码
	 * @return
	 */
	private ImageView.OnTouchListener createOnTouchListener(final ImageView imageView, 
			final int btnResourceId, final int btnResourceId2, final String answerCode){
		
		return new ImageView.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "event.getAction():" + event.getAction());
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					if (question!=null && lastAnswerQuestionId!=question.getId()) {
						imageView.setImageResource(btnResourceId2);
						
						//取消答题计时器
						countDownTimer.cancels();

						//创建答案
						ColorResponseTrainingAnswer answer = new ColorResponseTrainingAnswer();
						answer.setAnswer(answerCode);
						answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
						answer.setQuestionId(question.getId());

						lastAnswerQuestionId = question.getId();
						//回答问题，并获得答题结果
						ColorResponseTrainingResult testResult = service.answerQuestion(question, answer);
						//答题错误播放错误音
						if (!testResult.getValue()) {
							playErrorMusic();
						}
						
						//检查所答题目是否已是最后一题
						if (question.getId() >= questionCount) {
							//所答题目已是最后一题，停止回答
							stopAnswerAction();
						}else {
							//创建题目
							createQuestionAction();
						}
					}					
				}/*else if (event.getAction() == MotionEvent.ACTION_UP) {
					//imageView.setImageResource(btnResourceId);
				}*/
				return true;
			}			
		};
	}
	
	/**将颜色反应训练的题目在屏幕上进行显示
	 * 
	 * @param question
	 */
	private void showQuestion(ColorResponseTrainingQuestion question){
		
		//题目不能为空
		if (question == null) {
			return;
		}

		Log.d(TAG, "-------------question info-----------");
		Log.d(TAG, "question.getId():          " + question.getId());
		Log.d(TAG, "question.getContent():     " + question.getContent());
		Log.d(TAG, "question.getAnswer():      " + question.getAnswer());
		Log.d(TAG, "question.getDisplayBody(): " + question.getDisplayBody());
		Log.d(TAG, "-------------------------------------\n");
		
		//该道题的显示形体
		String displayBody = question.getDisplayBody();
		//题目的最终显示将以图片的形式展现在屏幕上，因此针对每道题目
		//都会找到相对应的图片加以显示，图片在引入到项目时的命名就按照一定的规则进行命名的，
		//因此此处需要按照规则拼凑出图片的文件名（不含扩展名）
		//命名规则如下：
		//	点状：cs_point_*;直线：cs_line_*;曲线：cs_curve_*;图形：cs_shape_*_*
		String fileNameStr = "";
		if (displayBody.equals(Constants.DisplayBody.POINT+"")) {
			fileNameStr = "cs_point_";
		}else if (displayBody.equals(Constants.DisplayBody.LINE+"")) {
			fileNameStr = "cs_line_";
		}else if (displayBody.equals(Constants.DisplayBody.CURVE+"")) {
			fileNameStr = "cs_curve_";
		}else if (Integer.valueOf(displayBody)>=Constants.Shapes.CIRCLE && 
				Integer.valueOf(displayBody)<=Constants.Shapes.PENTAGON) {
			fileNameStr = "cs_shape_" + (Integer.valueOf(displayBody)-Constants.Sample.SHAPES) +"_";
		}
		fileNameStr += Integer.valueOf(question.getContent()) - Constants.Sample.COLORS;
		Log.d(TAG, "fileNameStr: " + fileNameStr);
		
		//根据图片文件名，在/res/drawable目录下寻找该图片，并取得该图片的标识符，相当于android.R.cs_point_1
		int imageResourceId = getResources().getIdentifier(fileNameStr, "drawable", this.getPackageName());
		Log.d(TAG, "imageResourceId:" + imageResourceId);
		//正常情况下图片如果完全按照以上规则命名，一定能够取得标识符
		//如果命名不规范，图片就找不到
		if (imageResourceId == 0) {
			Log.e(TAG, "can not find any image resources!");
			return;
		}
		//将该图片设置为flashImageView中的内容
		flashImageView.setImageResource(imageResourceId);
				
		//图片将持续显示700毫秒，700毫秒后，图片显示消失，并出现回答按钮以供答题
		MyCountDownTimer countDownTimer2 = new MyCountDownTimer(
				Constants.DEFAULT_SHOW_TIME, Constants.DEFAULT_SHOW_TIME) {
			
			@Override
			public void onTick(long paramLong) {
			}
			
			@Override
			public void onFinish() {
				//图像消失
				flashImageView.setImageBitmap(null);
				//取消原先的答题计时器
				countDownTimer.cancels();
				//开始答题
				service.startAnswer();
				//显示答题按钮以供答题者答题
				showOperationButtons();
				//开启新的答题计时器，5秒钟之内不作答即超时需计错误一次
				countDownTimer.starts();
			}
		};
		countDownTimer2.starts();
	}	
}