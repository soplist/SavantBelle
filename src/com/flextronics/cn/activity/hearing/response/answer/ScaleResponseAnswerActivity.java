package com.flextronics.cn.activity.hearing.response.answer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.activity.hearing.response.ScaleResponseActivity;
import com.flextronics.cn.adapter.ScaleAdapter;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.hearing.response.ResponseAnswer;
import com.flextronics.cn.model.hearing.response.ResponseParameter;
import com.flextronics.cn.model.hearing.response.ResponseQuestion;
import com.flextronics.cn.model.hearing.response.ResponseReport;
import com.flextronics.cn.model.hearing.response.ResponseResult;
import com.flextronics.cn.model.hearing.response.ResponseRule;
import com.flextronics.cn.service.hearing.IResponseService;
import com.flextronics.cn.service.hearing.impl.ResponseService;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.MusicPlayTools;
import com.flextronics.cn.util.MyCountDownTimer;

public class ScaleResponseAnswerActivity extends BaseActivity{
	/**
	 * 当前页面的LOG打印标签
	 */
	private static final String TAG = "ScaleResponseAnswerActivity";

	private final static int CREATE_QUESTION = 8001;

	private final static int START_ANSWER = 8002;

	private final static int STOP_ANSWER = 8003;

	private RelativeLayout baseLayout;

	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;	

	private GridView answer;

	private ScaleAdapter scaleAdapter;

	private ImageView startAnswerFlag;

	private TextView answerCount;

	private MediaPlayer mediaPlayer;

	private MediaPlayer mp=new MediaPlayer();

	private IResponseService service;

	private ResponseQuestion question;

	private ResponseReport testReport;

	private int count=1;
	
	private int lastNumber=0;

	private boolean startFlag;

	private boolean isServiceStoped;

	private boolean play=true;
	
	private boolean randomEnabled;

	private Bundle bundle;

	private String smapleType=HearingConstants.Smaple.SCALE;

	private String elementsType;
	
	private List<Integer> musicalInstrumentsSet=new ArrayList<Integer>();
	
	private List<Integer> scaleSet=new ArrayList<Integer>();
	
	QuestionAnswerHandler qaHandler = new QuestionAnswerHandler();
	class QuestionAnswerHandler extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CREATE_QUESTION:
				if (count>HearingConstants.ANSWER_COUNT) {
					sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
					return;
				}
				setDisplayAnswerCount();
				if (isServiceStoped) {
					return;
				}
				Log.i(TAG, "starting create question...");
				question = service.createQuestion();
				setDisplayAnswer(randomEnabled);
				MusicPlayTools musicPlayTools = new MusicPlayTools();
				musicPlayTools.
				prepareQuestionMusic(getApplicationContext(),mp, question, play, question.getContents()[0]);
				break;
			case START_ANSWER:
				Log.i(TAG, "starting answer question...");
				service.startAnswer();
				countDownTimer.starts();
				break;
			case STOP_ANSWER:
				Log.i(TAG, "stopping answer question...");
				isServiceStoped = true;
				countDownTimer.cancels();
				play = false;
				Log.i(TAG, "stopping answer question is..."+mp.isPlaying());
				if (mp.isPlaying()) {
					mp.stop();
					mp.reset();
				}
				service.stop();
				testReport = service.generateTestReport();
				showTestReport(testReport);
				break;
			default:
				break;
			}
		}
	}

	private MyCountDownTimer countDownTimer = new MyCountDownTimer(HearingConstants.RESPONSE_ANSWER_TIME, HearingConstants.RESPONSE_ANSWER_TIME) {
		public void onFinish() {
			Log.d(TAG, "time up");
			answerQuestionError(getString(R.string.time_out));
		}
		public void onTick(long millisUntilFinished) {
			
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseLayout=getBaseRelativeLayout();
		lineLayout=(RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		displayLayout=new RelativeLayout(this);
		displayLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ONE);
		baseLayout.addView(lineLayout, getBaseLayoutParams());
		baseLayout.addView(displayLayout, getBaseLayoutParams());
		setContentView(baseLayout);
		
		getUIParams();
		ChoosedSample choosedSample=new ChoosedSample();
		choosedSample.setSmapleElementChoosed(listToString(musicalInstrumentsSet));
		ChoosedSample choosedSample2=new ChoosedSample();
		choosedSample2.setSmapleElementChoosed(listToString(scaleSet));
		
		ResponseParameter parameter=new ResponseParameter();
		parameter.setSmapleType(smapleType);
		parameter.setElementType(elementsType);
		parameter.setMusicInstrumentsSet(choosedSample);
		parameter.setScaleSet(choosedSample2);
		parameter.setRandomEnabled(randomEnabled);
		parameter.setQuestionTotal(HearingConstants.ANSWER_COUNT);

		ResponseRule rule=new ResponseRule();
		rule.setScore(HearingConstants.RESPONSE_SCORE);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(HearingConstants.RESPONSE_PARAMETERS, parameter);
		parameters.put(HearingConstants.RESPONSE_RULE, rule);
		parameters.put(HearingConstants.CONTEXT, this);
		
		service=new ResponseService();
		service.init(parameters);
		
		TimerTask timerTask=new TimerTask() {
			public void run() {
				service.start();				
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		};
		Timer timer=new Timer();
		timer.schedule(timerTask,HearingConstants.RESPONSE_DAFAULT_WAIT_TIME);
		
		setupView();
		setupListener();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		/**
		 * 
		 * 
		 * when the hard ware is ready, we should add codes at here the codes
		 * are about answer question
		 * 
		 * 
		 * 
		 * 
		 */

		return super.onKeyDown(keyCode, event);
	}
	
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
		countDownTimer.pause();
	}
	
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
		play = false;
		countDownTimer.cancels();
	}

	protected void onDestroy() {
		Log.d(TAG, "onDestroy()");
		count = 0;
		mp.reset();
		super.onDestroy();
	}
	
	private void setupView() {
		if (elementsType.equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)) {
			setTrainingTitle(getString(R.string.hearing_system_response_training_scale_foreign_music));
		} else if (elementsType.equals(HearingConstants.Element.FOLK_MUSIC_SCALE)) {
			setTrainingTitle(getString(R.string.hearing_system_response_training_scale_folk_music));
		}
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		
		startAnswerFlag();
		
		answerCount();
		
		answer();
	}
	
	private void setupListener() {
		answer.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				onClick(listToInteger(scaleSet)[position]);
			}
		});
		
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplication(),ScaleResponseActivity.class));
					finish();
				}
				return false;
			}
		});
		
		mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(final MediaPlayer mp) {
				Log.d(TAG, "mp is playing");
				mp.start();
				
			}
		});

		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer arg0) {
				Log.d(TAG, "mp is completion");
				qaHandler.sendMessage(qaHandler.obtainMessage(START_ANSWER));
			}
		});

		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mp.reset();
				return false;
			}
		});
	}
	
	private void onClick(int musicalInstrument){
		if(startFlag){
			if (lastNumber!=question.getId()) {
				countDownTimer.cancels();
				
				if (mp.isPlaying()) {
					mp.stop();
					mp.reset();
				}
				
				ResponseAnswer answer = new ResponseAnswer();
				
				answer.setQuestionId(question.getId());
				
				answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
				
				answer.setAnswers(new String[] { String.valueOf(musicalInstrument) });
				
				ResponseResult result = service.answerQuestion(question, answer);
				if (!result.getValue()) {
					playErrorMusic();
				}else {
					qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
				}
				lastNumber++;
			}
		}else {
			answerQuestionError(getString(R.string.premature));
		}
	}
	
	private void answerQuestionError(String errInfo) {
		countDownTimer.cancels();
		if (question != null) {
			service.answerQuestion(question, null);
			Toast.makeText(this, errInfo,Toast.LENGTH_SHORT).show();
			playErrorMusic();
		}
		lastNumber++;
	}
	
	private void playErrorMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(this, R.raw.error);
		mediaPlayer.start();
		
		TimerTask timerTask=new TimerTask() {
			public void run() {
				qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
			}
		};
		Timer timer=new Timer();
		timer.schedule(timerTask, 2000);
	}
	
	private void showTestReport(final ResponseReport testReport){		
		new AlertDialog.Builder(ScaleResponseAnswerActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(ScaleResponseAnswerActivity.this, TestReportActivity.class);
				bundle.putSerializable(HearingConstants.TEST_REPORT, testReport);
				bundle.putString(HearingConstants.CLASS_NAME, ScaleResponseAnswerActivity.class.getName());
				bundle.putString(HearingConstants.PREVIOUS_ACTIVITY, ScaleResponseActivity.class.getName());
				intent.putExtras(bundle);
				startActivity(intent);	
				finish();				
			}
		})
		.show();
	}
	
	private void setDisplayAnswerCount(){
		answerCount.setText(String.valueOf(count));
		if (count>HearingConstants.ANSWER_COUNT) {
			return;
		}else {
			count++;
		}
		startFlag=true;
		startAnswerFlag.setImageDrawable(getResources().getDrawable(R.drawable.green));
	}
	
	private void setDisplayAnswer(boolean state){
		scaleAdapter=null;
		if (state) {
			scaleSet.clear();
			int[] temp=stringArrayToInteger(question.getDisplayAnswer());
			scaleSet=integerArrayToList(temp);
			scaleAdapter=
			new ScaleAdapter(getApplicationContext(), true, temp);
			answer.setAdapter(scaleAdapter);
		}else {
			scaleAdapter=
			new ScaleAdapter(getApplicationContext(),true, listToInteger(scaleSet));
			answer.setAdapter(scaleAdapter);
		}
	}
	
	private void getUIParams() {
		bundle = getIntent().getExtras();
		elementsType=bundle.
		getString(HearingConstants.ResponseUIParameters.ELEMENT_TYPE);
		randomEnabled=
		bundle.getBoolean(HearingConstants.ResponseUIParameters.RANDOM);
		musicalInstrumentsSet=bundle.getIntegerArrayList(
		HearingConstants.ResponseUIParameters.MUSICAL_INSTRUMENTS_ELEMENT_SET);
		if (!randomEnabled) {
			scaleSet=bundle.getIntegerArrayList(
			HearingConstants.ResponseUIParameters.SCALE_ELEMENT_SET);
		}
	}
	
	private int[] listToInteger(List<Integer> list){
		int[] temp=new int[list.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i]=list.get(i);
		}
		return temp;
	}
	
	private int[] stringArrayToInteger(String[] array){
		int[] temp=new int[array.length];
		for (int i = 0; i < array.length; i++) {
			temp[i]=Integer.parseInt(array[i]);
		}
		return temp;
	}
	
	private String[] listToString(List<Integer> list){
		String[] temp=new String[list.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i]=list.get(i).toString();
		}
		return temp;
	}
	
	private List<Integer> integerArrayToList(int[] params){
		List<Integer> temp=new ArrayList<Integer>();
		for (int i = 0; i < params.length; i++) {
			temp.add(params[i]);
		}
		return temp;
	}
		
	private void startAnswerFlag(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.leftMargin=70;
		params.topMargin=160;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		startAnswerFlag=new ImageView(this);
		startAnswerFlag.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		startAnswerFlag.setImageDrawable(getResources().getDrawable(R.drawable.red));
		displayLayout.addView(startAnswerFlag, params);
	}
	
	private void answerCount(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=5;
		params.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_ONE);
		params.addRule(RelativeLayout.ALIGN_LEFT, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params3.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout relativeLayout=new RelativeLayout(this);
		relativeLayout.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_answer_number));
		title.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		title.setTextColor(Color.WHITE);
		
		answerCount=new TextView(this);
		answerCount.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		answerCount.setText(String.valueOf(count));
		answerCount.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		answerCount.setTextColor(Color.WHITE);
		
		displayLayout.addView(relativeLayout, params);
		relativeLayout.addView(title, params2);
		relativeLayout.addView(answerCount, params3);
	}
	
	private void answer(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(880,460);
		params.topMargin=5;
		params.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_TWO);
		params.addRule(RelativeLayout.ALIGN_LEFT, HearingConstants.ViewId.VIEW_ID_TWO);
		
		answer=new GridView(this);
		answer.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		answer.setSelector(getResources().getDrawable(R.drawable.hs_background_01));
		answer.setNumColumns(HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
		answer.setGravity(Gravity.CENTER);
		displayLayout.addView(answer, params);
	}
}