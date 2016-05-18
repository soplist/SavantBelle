package com.flextronics.cn.activity.hearingtouch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtAnswer;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtParameter;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtQuestion;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtReport;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtResult;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtRule;
import com.flextronics.cn.service.hearingtouch.HtmtQuestionAnswerService;
import com.flextronics.cn.service.hearingtouch.HtmtQuestionAnswerServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.ui.TimerView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.MyCountDownTimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**<b>A-视觉听觉触觉综合训练</b><br>
 * 听觉触觉记忆训练--按键操作
 * 
 * @author ZhangGuoYin
 *
 */
public class HtmtSampleActivity extends BaseActivity{
	
	private final static String TAG = "HtmtSampleActivity";
	private final static long PLAYER_WAITING_TIME = 1000;
	
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
	private HtmtQuestion question;
	
	/**
	 * 服务
	 */
	private HtmtQuestionAnswerService service;
	/**
	 * 播放题目音的播放器
	 */
	private MediaPlayer mp = new MediaPlayer();

	private List<String> answerList = new ArrayList<String>();
	/**
	 * 题目总共的片段
	 */
	private int playToalParts = 0;
	/**
	 * 当前播放的片段
	 */
	private int playParts = 0;
	private boolean isStoped;	
	private boolean needSchedule = true;
	private boolean firstTime = true;
	private boolean canPlay = true;
	private boolean isServiceStoped;
	private int errorCount;
	
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
	 * 题目数
	 */
	private int questionCount;
	
	private long lastAnswerQuestionId = -1;
	
	private Bundle bundle ;
	/**
	 * 播放答题错误音的播放器
	 */
	private MediaPlayer mediaPlayer;
	
	
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

			if (needSchedule) {
				answerQuestionError(getString(R.string.time_out));
				if (question != null) {
					//清空答案的list
					answerList.clear();
					stopOrContinue();
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
		//将vht_hearing_touch_memory_training_keystoke中描述的布局添加到父布局中
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_hearing_touch_memory_training_keystoke, null), getBaseLayoutParams());
		//设置为将要显示的布局
		setContentView(layout);
		
		this.setupViews();
		this.setupListeners();
				
		/*************从跳转到该activity的UI处获得各项参数，参数的格式必须满足接口规范************/
		bundle = this.getIntent().getExtras();		
		testType = bundle.getInt(Constants.HearingTouchMemoryTrainingUIParameter.TEST_TYPE);
		handType = bundle.getInt(Constants.HearingTouchMemoryTrainingUIParameter.HAND_TYPE);
		sampleSetMusic = bundle.getInt(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_SET_MUSIC);
		sampleElementsMusic = bundle.getString(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_ELEMENTS_MUSIC);
		sampleElementsScale = bundle.getString(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_ELEMENTS_SCALE);
		questionCount = bundle.getInt(Constants.HearingTouchMemoryTrainingUIParameter.QUESTION_COUNT, 15);
		
		/*****************将各参数打印出来****************/
		Log.d(TAG, "----------receive parameters from UI----------");
		Log.d(TAG, "testType:           " + testType);
		Log.d(TAG, "handType:           " + handType);
		Log.d(TAG, "sampleSetMusic:     " + sampleSetMusic);
		Log.d(TAG, "sampleElementsMusic:" + sampleElementsMusic);
		Log.d(TAG, "sampleElementsScale:" + sampleElementsScale);
		Log.d(TAG, "-------------------- end ---------------------");
		
		String[] sampleElementsMusicArray = sampleElementsMusic.split(",");
		String[] sampleElementsScaleArray = sampleElementsScale.split(",");
		
		int bit = 4;
		if (testType == Constants.HearingTouchMemoryTrainingUIParameter.MUSICAL_INSTRUMENT) {
			bit = sampleElementsMusicArray.length;
		}else if (testType == Constants.HearingTouchMemoryTrainingUIParameter.SCALE) {
			bit = sampleElementsScaleArray.length;
		}		
		
		/********************为听觉触觉反应训练服务准备参数 ********************/
		//创建乐器样本对象
		ChoosedSample music = new ChoosedSample();
		music.setSample(sampleSetMusic);
		music.setSmapleElementChoosed(sampleElementsMusicArray);
		
		//创建音阶样本对象
		ChoosedSample scale = new ChoosedSample();
		scale.setSmapleElementChoosed(sampleElementsScaleArray);		
		
		HtmtParameter htmtParameter = new HtmtParameter();		
		htmtParameter.setBit(bit);
		htmtParameter.setHandType(handType);
		htmtParameter.setMusicType(sampleSetMusic);
		htmtParameter.setTestType(testType);
		htmtParameter.setMusic(music);
		htmtParameter.setScale(scale);
		htmtParameter.setQuestionCount(questionCount);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(Constants.PARAMETER, htmtParameter);
		
		HtmtRule htmtRule = new HtmtRule();
		htmtRule.setScore(2);
		parameters.put(Constants.RULE, htmtRule);

		parameters.put(Constants.CONTEXT, HtmtSampleActivity.this);
		/****************************参数准备完毕********************************/
		
		//实例化听觉触觉问答服务对象
		service = new HtmtQuestionAnswerServiceImpl();
		//根据参数初始化服务
		try {
			service.init(parameters);
		} catch (LackOfParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(this);
		}
		
		mediaPlayer = MediaPlayer.create(HtmtSampleActivity.this, R.raw.error);
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
		createQuestionAction();
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
		//取消答题计时器
		countDownTimer.cancels();
		
		//将答题状态标志灯变为红色,状态设为初始状态
		startFlagImageView.setImageResource(R.drawable.red);
		startFlagImageView.setState(StartFlagImageView.STATE_INIT);		
		
		new CountDownTimer(Constants.WAITING_TIME, Constants.WAITING_TIME){

			@Override
			public void onFinish() {
				
				try {
					question = service.createQuestion();
				} catch (LackOfParametersException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ActivityUtil.finish(HtmtSampleActivity.this);
				}
				//如果题目为空
				if (question == null) {
					return;
				}				

				playParts = 0;
				playQuestionMusic(question.getContents()[0]);
			}

			@Override
			public void onTick(long paramLong) {
			}					
		}.start();
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
		mp.stop();
		needSchedule = false;
		isStoped = true;
		service.stop();
		//生成测试报告并显示
		showTestReport(service.generateTestReport());		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");

		return super.onTouchEvent(event);
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
		//	timerView.stop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy()");
		mp.release();
		super.onDestroy();
	}
	
	/**
	 * 设置相关控件
	 */
	private void setupViews(){

		//设置标题
		setTrainingTitle(getString(R.string.hearing_touch_memory_training_key));
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
					//显示时钟暂停
					timerView.pause();
					mp.stop();
					
					new AlertDialog.Builder(HtmtSampleActivity.this)
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
					//显示时钟暂停
					timerView.pause();
					mp.stop();
					
					new AlertDialog.Builder(HtmtSampleActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_doing)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							countDownTimer.cancels();
							isServiceStoped = true;
							startActivity(new Intent(getApplicationContext(), ChooseParams4HtmtActivity.class));							
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
		
		//当完成当前音乐片段的播放后，检查所有片段是否已经播放完毕
		//如果所有片段没有播放完毕，则继续播放下一片段;
		//如果所有片段均播放完毕，则将状态灯变绿，表示可以答题了，并发消息通知答题
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

			public void onCompletion(MediaPlayer arg0) {
					Log.d(TAG, "MediaPlayer.OnCompletionListener()");
					
					if (!isStoped) {
						playToalParts = question.getContents().length;
						playParts ++;
						if (playToalParts > 1 && playParts<playToalParts) {
							playQuestionMusic(question.getContents()[playParts]);
						}
						//如果所有片段播放完毕
						if ((playToalParts > 1 && playParts==playToalParts) || playToalParts==1) {
							//更改状态灯
							startFlagImageView.setState(StartFlagImageView.STATE_TIME_UP);
							startFlagImageView.setImageResource(R.drawable.green);
							//倒计时开始
							countDownTimer.starts();
							//开始答题
							startAnswerAction();
						}
					}else {
						needSchedule = false;
					}
			}					
		});
		
		//播放器准备完毕，1s后开始播放
		mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

			public void onPrepared(final MediaPlayer mp) {
				TimerTask task = new TimerTask(){

					@Override
					public void run() {
						mp.start();
					}					
				};
			
				Timer timer = new Timer();
				timer.schedule(task, PLAYER_WAITING_TIME);
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
		
		if (question!=null && question.getId()<=questionCount && lastAnswerQuestionId != question.getId()) {
			if (startFlagImageView.getState() == StartFlagImageView.STATE_TIME_UP) {
				//取消答题计时器
				countDownTimer.cancels();
				//将_answerCode添加到列表中
				answerList.add(String.valueOf(answerCode));
				
				if (!currentResult(answerList, question.getAnswers())) {
					//如果列表中值不跟标准答案相同,表明用户已经答错了,可以直接提示用户答题错误,进入下一题
					//无需等待用户把所有答案都按对为止							
					Log.d(TAG, "_answerCode: " + answerCode + " is wrong.");
					lastAnswerQuestionId = question.getId();
					service.answerQuestion(question, null);
					showErrorInfo("");
					playErrorMusic();
					
					answerList.clear();
					//判读是否继续出题
					stopOrContinue();
				}else {
					Log.d(TAG, "_answerCode: " + answerCode + " is right.");
					
					if (answerList.size()==question.getAnswers().length) {//用户已经按全所有按键
						
						String[] answers = new String[question.getContents().length];
						for (int i = 0; i < answerList.size(); i++) {
							answers[i] = answerList.get(i);
						}								
						answerList.clear();

						HtmtAnswer answer = new HtmtAnswer();
						answer.setQuestionId(question.getId());
						answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
						answer.setAnswers(answers);

						lastAnswerQuestionId = question.getId();
						//回答问题并得到结果
						HtmtResult testResult = service.answerQuestion(question, answer);
						//如果回答正确,按照流程此处肯定是回答正确的
						if (testResult.getValue()) {
							//判读是否继续出题
							stopOrContinue();
						}
					}else {
						countDownTimer.starts();
					}
				}						
			}else {//答题状态标志位没有切换到TIME_UP,属于过早反应
				Log.d(TAG, "answer premature");
				
				if (mp!=null && mp.isPlaying()) {
					mp.pause();
					mp.stop();
					mp.reset();
				}
				answerQuestionPremature();
				answerList.clear();
				stopOrContinue();				
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
	 * when answer error, make a toast which includes 
	 * the correct answer and show the toast
	 * @param preString
	 */
	private void showErrorInfo(String preString){
		
		String[] _answers = question.getAnswers();
		String str = "";
		for (int i = 0; i < _answers.length; i++) {			
			str += getFingerName(_answers[i]) + " ";
		}
		
		Toast.makeText(HtmtSampleActivity.this, 
				preString+" " + getResources().getString(R.string.answers) + str, 
				Toast.LENGTH_LONG).show();
	}	

	private String getFingerName(String code){
		String finger = "";
		if (code.equals(Constants.KeyStoke.FORE_FINGER_LEFT+"") ||
				code.equals(Constants.KeyStoke.FORE_FINGER_RIGHT+"")) {
			finger = getResources().getString(R.string.foreFinger);
		}else if (code.equals(Constants.KeyStoke.MIDDLE_FINGER_LEFT+"") ||
				code.equals(Constants.KeyStoke.MIDDLE_FINGER_RIGHT+"")) {
			finger = getResources().getString(R.string.middleFinger);
		}else if (code.equals(Constants.KeyStoke.THIRD_FINGER_LEFT+"") ||
				code.equals(Constants.KeyStoke.THIRD_FINGER_RIGHT+"")) {
			finger = getResources().getString(R.string.thirdFinger);
		}
		return finger;
	}
		
	/**
	 * 显示测试报告
	 * @param testReport
	 */
	private void showTestReport(final HtmtReport testReport){		

		new AlertDialog.Builder(HtmtSampleActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				
				Intent intent = new Intent(HtmtSampleActivity.this, TestReportActivity.class);
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, HtmtSampleActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, ChooseParams4HtmtActivity.class.getName());
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
		
		countDownTimer.cancels();
		if (question != null) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			showErrorInfo(errInfo);		
			playErrorMusic();
		}
		if (!isServiceStoped) {
			countDownTimer.starts();
		}
	}
	
	/**回答过早,过早反应
	 * 
	 */
	private void answerQuestionPremature(){		
		
		countDownTimer.cancels();
		if (question != null) {
			lastAnswerQuestionId = question.getId();
			service.answerQuestion(question, null);
			Toast.makeText(HtmtSampleActivity.this, R.string.premature, Toast.LENGTH_SHORT).show();
			playErrorMusic();
		}
		if (!isServiceStoped) {
			countDownTimer.starts();
		}
	}	
	
	/**
	 * 播放答题错误音
	 */
	private void playErrorMusic(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(HtmtSampleActivity.this, R.raw.error);
		mediaPlayer.start();
	}
		
	/**
	 * 回答问题后看是否应该继续出题
	 */
	private void stopOrContinue(){
		if (question.getId() == questionCount) {
			stopAnswerAction();
		}else {
			createQuestionAction();
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
	
	/**
	 * 播放音乐文件
	 * @param questionContent 音乐文件路径
	 */
	private void playQuestionMusic(String questionContent){
		
		if (question == null) {
			return;
		}
		
		if (!canPlay) {
			return;
		}
		if (mp.isPlaying()) {
			mp.pause();
			mp.stop();
		}
		
		File file = new File(Constants.MUSIC_DIRECTORY_PATH + questionContent);
		
		try {			
			if (!file.exists()) {
				Log.d(TAG, "music file is not exist, we will create it");
				InputStream inputStream = HtmtSampleActivity.this.getAssets().open(questionContent);
				file = CommonUtil.createMusicFile(Constants.MUSIC_DIRECTORY_PATH + questionContent, inputStream);				
			}			

			mp.reset();
			mp.setDataSource(file.getAbsolutePath());
			mp.prepare();
			//mp.prepare();
			//mp.start();
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "The media player has IllegalArgumentException.");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			Log.e(TAG, "The media player has IllegalStateException.");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "The media player has IOException.");
			e.printStackTrace();
			errorCount ++;
			
			if (errorCount <= 25) {
				playQuestionMusic(questionContent);
			}else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playQuestionMusic(questionContent);
				}
			}else {
				new AlertDialog.Builder(HtmtSampleActivity.this)
				.setTitle(R.string.tips)
				.setMessage(R.string.internal_error)
				.setCancelable(false)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						HtmtSampleActivity.this.finish();
					}
				})
				.show();
				errorCount = 0;
			}			
		}
		errorCount = 0;
	}
}