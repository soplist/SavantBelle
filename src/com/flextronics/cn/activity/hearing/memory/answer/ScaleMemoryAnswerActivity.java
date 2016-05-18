package com.flextronics.cn.activity.hearing.memory.answer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
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
import com.flextronics.cn.activity.TestReportWithTableActivity;
import com.flextronics.cn.activity.hearing.memory.ScaleMemoryActivity;
import com.flextronics.cn.adapter.ScaleAdapter;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.hearing.memory.MemoryAnswer;
import com.flextronics.cn.model.hearing.memory.MemoryParameter;
import com.flextronics.cn.model.hearing.memory.MemoryQuestion;
import com.flextronics.cn.model.hearing.memory.MemoryReport;
import com.flextronics.cn.model.hearing.memory.MemoryRule;
import com.flextronics.cn.service.hearing.IMemoryService;
import com.flextronics.cn.service.hearing.impl.MemoryService;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingUtil;
import com.flextronics.cn.util.MyCountDownTimer;

public class ScaleMemoryAnswerActivity extends BaseActivity {
	private static final String TAG = "ScaleMemoryAnswerActivity";
	
	private final static int CREATE_QUESTION = 8001;
	
	private final static int CREATED = 8002;
	
	private final static int START_ANSWER = 8003;
	
	private final static int STOP_ANSWER = 8004;
	
	private RelativeLayout baseLayout;
	
	private RelativeLayout lineLayout;
	
	private RelativeLayout displayLayout;	
	
	private GridView answer;
	
	private ScaleAdapter scaleAdapter;
	
	private ImageView startAnswerFlag;
	
	private TextView answerCount;
	
	private TextView answerText;
	
	private MediaPlayer mediaPlayer;
	
	private MediaPlayer mp=new MediaPlayer();
	
	private MemoryQuestion question;
	
	private MemoryReport testReport;
	
	private IMemoryService service;
	
	private int scale;
	
	private int playParts;
	
	private int playToalParts;
	
	private int count=1;
	
	private int currentError=0;
	
	private int lastQuestionId=0;
	
	private int errorCount;
	
	private boolean startFlag;
	
	private boolean play=true;
	
	private boolean isServiceStoped;
	
	private String answerTextString;
	
	private List<String> keyList=new ArrayList<String>();
	
	private Bundle bundle;

	private String smapleType=HearingConstants.Smaple.SCALE;

	private String elementType;
	
	private String bitType;
	
	private List<Integer> musicalInstrumentsSet=new ArrayList<Integer>();
	
	private List<Integer> scaleSet=new ArrayList<Integer>();
	
	private boolean randomMusicalInstruments;
	
	private boolean randomScale;
	
	private int startBit;
	
	QuestionAnswerHandler qaHandler = new QuestionAnswerHandler();
	class QuestionAnswerHandler extends Handler {
		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case CREATE_QUESTION:
				if (bitType.equals(HearingConstants.BitType.CONTINUED_BIT)) {
					if (service.generateTestReport().getErrorCount()%7==0&&
							service.generateTestReport().getErrorCount()!=0) {
						sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
						return;
					}
				}else {
					if (count>HearingConstants.ANSWER_COUNT) {
						sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
						return;
					}
				}
				if (isServiceStoped) {
					return;
				}
				Log.i(TAG, "starting create question...");
				setDisplayAnswerCount(bitType);
				setStartFlagStated(false);
				setDisplayAnswerText(0);
				question = service.createQuestion();
				setDisplayAnswer();
				sendMessageDelayed(qaHandler.obtainMessage(CREATED), HearingConstants.MEMORY_DAFAULT_TIME);
				break;
			case CREATED:
				Log.i(TAG, "complete creating question...");
				playParts = 0;
				playQuestionMusic(question.getContents()[0]);
				break;
			case START_ANSWER:
				Log.i(TAG, "starting answer question...");
				service.startAnswer();
				setStartFlagStated(true);
				countDownTimer.starts();
				break;
			case STOP_ANSWER:
				Log.i(TAG, "stopping answer question...");
				isServiceStoped = true;
				countDownTimer.cancels();
				if (mp.isPlaying()) {
					mp.stop();
					mp.reset();
				}
				service.stop();
				testReport = service.generateTestReport();
				showTestReport(testReport);
			default:
				break;
			}
		}
	}

	private MyCountDownTimer countDownTimer = new MyCountDownTimer(HearingConstants.MEMORY_ANSWER_TIME, 
			HearingConstants.MEMORY_ANSWER_TIME) {
		public void onFinish() {
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
		choosedSample.setSmapleElementChoosed(HearingUtil.DataConversion.listToString(musicalInstrumentsSet));
		ChoosedSample choosedSample2=new ChoosedSample();
		choosedSample2.setSmapleElementChoosed(HearingUtil.DataConversion.listToString(scaleSet));
		
		MemoryParameter parameter = new MemoryParameter();
		parameter.setSmapleType(smapleType);
		parameter.setElementType(elementType);
		parameter.setBitType(bitType);
		parameter.setMusicalInstruments(choosedSample);
		parameter.setScale(choosedSample2);
		parameter.setRandomMusicalInstruments(randomMusicalInstruments);
		parameter.setRandomScale(randomScale);
		parameter.setStartBit(startBit);
		parameter.setQuestionCount(HearingConstants.ANSWER_COUNT);
		
		MemoryRule rule=new MemoryRule();
		rule.setScore(HearingConstants.MEMORY_SCORE);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(HearingConstants.MEMORY_PARAMETERS, parameter);
		parameters.put(HearingConstants.MEMORY_RULE, rule);
		parameters.put(HearingConstants.CONTEXT, this);
		
		service=new MemoryService();
		service.init(parameters);
		service.start();
		qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
		
		setupView();
		setupListener();
	}
	
	protected void onDestroy() {
		Log.d(TAG, "onDestroy........");
		mp.stop();
		mp.reset();
		mediaPlayer.reset();
		countDownTimer.cancels();
		super.onDestroy();
	}
	
	private void setupView() {
		if (elementType.equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)) {
			setTrainingTitle(getString(R.string.hearing_system_memory_training_scale_foreign_music));
		} else if (elementType.equals(HearingConstants.Element.FOLK_MUSIC_SCALE)) {
			setTrainingTitle(getString(R.string.hearing_system_memory_training_scale_folk_music));
		}
		setUserNameEnabled(true);
		
		setUserIconEnable(true);
		
		setCancelButtonEnable(false);
		
		setOkButtonEnable(false);
		
		startAnswerFlag();
		
		answerText();
		
		answerCount();
		
		answer();
	}
	
	private void setupListener() {
		answer.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				scale=scaleSet.get(position);
				onClick(scale);
			}
		});
		
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent(getApplication(),ScaleMemoryActivity.class);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
		mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(final MediaPlayer mp) {
						mp.start();
			}
		});
		
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer arg0) {
				Log.d(TAG, "MediaPlayer.OnCompletionListener()");
				playToalParts = question.getContents().length;
				playParts++;
				if (playToalParts > 1 && playParts < playToalParts) {
					playQuestionMusic(question.getContents()[playParts]);
				}
				if ((playToalParts > 1 && playParts == playToalParts)|| playToalParts == 1) {
					qaHandler.sendMessage(qaHandler.obtainMessage(START_ANSWER));
				}
			}
		});

		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mp.reset();
				return false;
			}
		});
		
		
		
	}
	
	private void onClick(int key){
		if (lastQuestionId!=question.getId()) {
			if(startFlag){
				keyList.add(String.valueOf(key));
				if (!currentResult(keyList, question.getAnswers())) {
					answerQuestionError(getString(R.string.hearing_system_answer_error));
				}else {
					countDownTimer.cancels();
					setDisplayAnswerText(key);
					if (keyList.size() == question.getContents().length) {
						countDownTimer.cancels();
						MemoryAnswer answer = new MemoryAnswer();
						answer.setQuestionId(question.getId());
						answer.setAnswerTime(new Timestamp(System.currentTimeMillis()));
						answer.setAnswers(question.getAnswers());
						service.answerQuestion(question, answer);
						lastQuestionId=(int)question.getId();
						keyList.clear();
						qaHandler.sendMessage(qaHandler.obtainMessage(CREATE_QUESTION));
						return;
					}
					countDownTimer.starts();
				}
			}else {
				answerQuestionError(getString(R.string.premature));
			}
		}
	}
	
	private boolean currentResult(List<String> codesList, String[] answers) {
		if (answers == null || codesList == null) {
			return false;
		}
		if (codesList.size() > answers.length) {
			return false;
		}
		
		for (int i = 0; i < codesList.size(); i++) {
			if (!(codesList.get(i)).equals(answers[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	private void showTestReport(final MemoryReport testReport) {
		new AlertDialog.Builder(ScaleMemoryAnswerActivity.this).setTitle(R.string.tips)
				.setMessage(R.string.test_over).setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (bitType.equals(HearingConstants.BitType.CONTINUED_BIT)) {
									//如果是连续位元,跳转至TestReportWithTableActivity显示report
									Intent intent = new Intent(ScaleMemoryAnswerActivity.this, TestReportWithTableActivity.class);
									bundle.putSerializable(Constants.TEST_REPORT, testReport);
									bundle.putString(Constants.CLASS_NAME, ScaleMemoryAnswerActivity.class.getName());
									intent.putExtras(bundle);
									startActivity(intent);
								}else {
									//如果不是连续位元,跳转至TestReportActivity显示report
									Intent intent = new Intent(ScaleMemoryAnswerActivity.this, TestReportActivity.class);
									bundle.putSerializable(Constants.TEST_REPORT, testReport);
									bundle.putString(Constants.CLASS_NAME, ScaleMemoryAnswerActivity.class.getName());
									bundle.putString(HearingConstants.PREVIOUS_ACTIVITY, ScaleMemoryActivity.class.getName());
									intent.putExtras(bundle);
									startActivity(intent);	
								}
								finish();		
							}
						}).show();
	}
	
	private void answerQuestionError(String errInfo) {
		countDownTimer.cancels();
		if (mp != null && mp.isPlaying()) {
			mp.pause();
			mp.stop();
			mp.reset();
		}
		if (question != null) {
			Toast.makeText(this, errInfo,Toast.LENGTH_SHORT).show();
			keyList.clear();
			service.answerQuestion(question, null);
			showRightAnswer();
			currentErrorCount();
			playErrorMusic();
			qaHandler.sendMessageDelayed(qaHandler.obtainMessage(CREATE_QUESTION), HearingConstants.MEMORY_DAFAULT_TIME);
		}
		lastQuestionId=(int)question.getId();
	}
	
	private void playErrorMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.reset();
		}
		mediaPlayer = MediaPlayer.create(this, R.raw.error);
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mediaPlayer.reset();
				return false;
			}
		});
		mediaPlayer.start();
	}
	
	private void getUIParams() {
		bundle = getIntent().getExtras();

		elementType=bundle.getString(HearingConstants.MemoryUIParameters.ELEMENT_TYPE);
		
		bitType=bundle.getString(HearingConstants.MemoryUIParameters.BIT_TYPE);
		
		musicalInstrumentsSet=bundle.getIntegerArrayList(HearingConstants.MemoryUIParameters.MUSICAL_INSTRUMENTS_ELEMENT_SET);
		
		scaleSet=bundle.getIntegerArrayList(HearingConstants.MemoryUIParameters.SCALE_ELEMENT_SET);
		
		randomMusicalInstruments=bundle.getBoolean(HearingConstants.MemoryUIParameters.RANDOM_MUSICAL_INSTRUMENTS);
		
		randomScale=bundle.getBoolean(HearingConstants.MemoryUIParameters.RANDOM_SCALE);
		
		startBit=bundle.getInt(HearingConstants.MemoryUIParameters.START_BIT);
	}
	
	private void currentErrorCount(){
		if (bitType.equals(HearingConstants.BitType.CONTINUED_BIT)) {
			currentError++;
			Log.d(TAG, "currentError:"+currentError);
			if ((currentError==3&&question.getId()%5==0)||currentError==3) {
				qaHandler.sendMessage(qaHandler.obtainMessage(STOP_ANSWER));
			}else if (currentError<3&&question.getId()%5==0) {
				currentError=0;
			}
		}
	}
	
	private void setDisplayAnswerCount(String continueBit){
		answerCount.setText(String.valueOf(count));
		if (count>HearingConstants.ANSWER_COUNT) {
			if (bitType.equals(HearingConstants.BitType.CONTINUED_BIT)) {
				count++;
			}else {
				return;
			}
		}else {
			count++;
		}
	}
	
	private void setDisplayAnswer(){
		scaleAdapter=null;
		if (question.getDisplayContents().length<HearingConstants.DEFAULT_DISPLAY_ANSWER) {
			answer.setNumColumns(question.getDisplayContents().length);
		}else {
			answer.setNumColumns(HearingConstants.DEFAULT_DISPLAY_ANSWER);
		}
		if (randomScale) {
			scaleSet.clear();
			int[] temp=HearingUtil.DataConversion.stringArrayToInteger(question.getDisplayContents());
			scaleSet=HearingUtil.DataConversion.integerArrayToList(temp);
			scaleAdapter=
			new ScaleAdapter(getApplicationContext(), true, temp);
			answer.setAdapter(scaleAdapter);
		}else {
			scaleAdapter=
			new ScaleAdapter(getApplicationContext(),true, HearingUtil.DataConversion.listToInteger(scaleSet));
			answer.setAdapter(scaleAdapter);
		}
	}
	
	private void setDisplayAnswerText(int answer){
		if (answer!=0) {
			String[] temp=getResources().getStringArray(R.array.ScaleNameArray);
			answerTextString+=temp[answer-200]+" ";
			answerText.setText(getString(R.string.hearing_system_response_answer)+answerTextString);
			answerText.setTextColor(Color.WHITE);
		}else {
			answerTextString="";
			answerText.setText(getString(R.string.hearing_system_response_answer));
			answerText.setTextColor(Color.WHITE);
		}
	}
	
	private void setStartFlagStated(boolean stated){
		if (stated) {
			startAnswerFlag.setImageDrawable(getResources().getDrawable(R.drawable.green));
			startFlag=true;
		}else {
			startAnswerFlag.setImageDrawable(getResources().getDrawable(R.drawable.red));
			startFlag=false;
		}
	}
		
	private void showRightAnswer(){
		String tempString=getString(R.string.hearing_system_answer_right);
		String[] tempStrings=getResources().getStringArray(R.array.ScaleNameArray);
		for (int i = 0; i < question.getAnswers().length; i++) {
			tempString+=tempStrings[Integer.parseInt(question.getAnswers()[i])-200]+" ";
		}
		answerText.setText(tempString);
		answerText.setTextColor(Color.RED);
	}
	
	private void playQuestionMusic(String questionContent) {
		if (question == null) {
			return;
		}
		if (!play) {
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
				InputStream inputStream = ScaleMemoryAnswerActivity.this.getAssets()
						.open(questionContent);
				file = CommonUtil.createMusicFile(Constants.MUSIC_DIRECTORY_PATH + questionContent, inputStream);
			}
			mp.reset();
			mp.setDataSource(file.getAbsolutePath());
			mp.prepare();
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "The media player has IllegalArgumentException.");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			Log.e(TAG, "The media player has IllegalStateException.");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "The media player has IOException.");
			e.printStackTrace();
			errorCount++;
			if (errorCount <= 25) {
				playQuestionMusic(questionContent);
			} else if (errorCount <= 50) {
				if (file.exists()) {
					file.delete();
					playQuestionMusic(questionContent);
				}
			} else {
				new AlertDialog.Builder(ScaleMemoryAnswerActivity.this).setTitle(R.string.tips).setMessage(R.string.internal_error)
						.setPositiveButton(R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										ScaleMemoryAnswerActivity.this.finish();
									}
								}).show();
				errorCount = 0;
			}
		}
		errorCount = 0;
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
	
	private void answerText(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=5;
		params.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_ONE);
		params.addRule(RelativeLayout.ALIGN_LEFT, HearingConstants.ViewId.VIEW_ID_ONE);
		
		answerText=new TextView(this);
		answerText.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		answerText.setText(getString(R.string.hearing_system_response_answer));
		answerText.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		answerText.setTextColor(Color.WHITE);
		displayLayout.addView(answerText, params);
	}
	
	private void answerCount(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=5;
		params.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_TWO);
		params.addRule(RelativeLayout.ALIGN_LEFT, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.ViewId.VIEW_ID_THREE);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params3.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout relativeLayout=new RelativeLayout(this);
		relativeLayout.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		
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
		params.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_THREE);
		params.addRule(RelativeLayout.ALIGN_LEFT, HearingConstants.ViewId.VIEW_ID_THREE);
		
		answer=new GridView(this);
		answer.setId(HearingConstants.ViewId.VIEW_ID_FOUR);
		answer.setSelector(getResources().getDrawable(R.drawable.hs_background_01));
		answer.setGravity(Gravity.CENTER);
		displayLayout.addView(answer, params);
	}
}