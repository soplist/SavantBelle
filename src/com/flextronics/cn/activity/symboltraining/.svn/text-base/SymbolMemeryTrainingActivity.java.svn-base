package com.flextronics.cn.activity.symboltraining;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.TestReportActivity;
import com.flextronics.cn.activity.TestReportWithTableActivity;
import com.flextronics.cn.activity.color.ColorMemoryTrainingActivity;
import com.flextronics.cn.activity.color.ColorMemoryTrainingChooseParamsActivity;
import com.flextronics.cn.activity.symboltraining.Helper.OperationPanelListener;
import com.flextronics.cn.activity.symboltraining.ui.CommonDisplayPanel;
import com.flextronics.cn.activity.symboltraining.ui.DisplayPanel;
import com.flextronics.cn.activity.symboltraining.ui.PatternDisplayPanel;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingParameter;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolMemeryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolMemoryTrainingParameter;
import com.flextronics.cn.service.color.ColorMemoryTrainingService;
import com.flextronics.cn.service.color.ColorMemoryTrainingServiceImpl;
import com.flextronics.cn.service.symbol.SymbolMemeryTrainingService;
import com.flextronics.cn.service.symbol.SymbolMemeryTrainingServiceImpl;
import com.flextronics.cn.ui.StartFlagImageView;
import com.flextronics.cn.util.ActivityUtil;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**
 * 符号辨识记忆训练
 * 
 * @author Zhaohe.Guo
 * 
 *         2010-12-8 下午02:43:39
 */
public class SymbolMemeryTrainingActivity extends BaseActivity {
	
	private SymbolMemeryTrainingService service;
	
	private Bundle bundle;
	
	private int questionCount;
	
	private int questionIndex = 1;
	
	MediaPlayer player = null;
	
	private static final String TAG = SymbolMemeryTrainingActivity.class
			.getSimpleName();
	/**
	 * 题目显示的时间
	 */
	private static final long DURATION = 2 * 1000;
	/**
	 * 答题时的反应时间
	 */
	private static final long ANSWER_INTERVAL = 5 * 1000;
	/**
	 * 样本集
	 */
	private int sampleSet;

	/**
	 * 显示问题的面板
	 */
	DisplayPanel displayPanel = null;
	private Handler handler = null;
	/**
	 * 显示效果
	 */
	AnimationDelegate animDelegate = null;

	private Logic logic = null;

	private State state = State.DISPLAYING;

	private CountDownTimer countDownTimer = null;
	
	private StartFlagImageView startFlagImageView;

	enum State {
		DISPLAYING, // 显示题目阶段
		ANSWERING
		// 答题阶段
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.color_memory_training中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.symbol_memery_training, null), getBaseLayoutParams());
		setContentView(layout);
		
		player = createMediaPlayer();
		
		bundle = this.getIntent().getExtras();
		this.sampleSet = bundle
				.getInt(Constants.SymbolTraingParam.Key.SAMPLE_SET);
		int elementSize = CommonUtil
				.getSampleElementImageResBySampleCode(sampleSet).length;
		int displaySettingType = bundle
				.getInt(Constants.SymbolTraingParam.Key.DISPLAY_SETTING);
		if (displaySettingType == Constants.SymbolTraingParam.DISPLAY_SETTING_COMMON) {
			displayPanel = new CommonDisplayPanel(this, CommonUtil
					.getSampleElementImageResBySampleCode(sampleSet), false);
		} else {
			boolean random = false;
			if (displaySettingType == Constants.SymbolTraingParam.DISPLAY_SETTING_PATTERN_RANDOM) {
				random = true;
			}
			int pattern = bundle
					.getInt(Constants.SymbolTraingParam.Key.DISPLAY_PATTERN);
			displayPanel = new PatternDisplayPanel(this, CommonUtil
					.getSampleElementImageResBySampleCode(sampleSet), random,
					pattern);
		}

		int unitCountSetting = bundle
				.getInt(Constants.SymbolTraingParam.Key.UNIT_COUNT_SETTING);
		if (unitCountSetting == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_SEQENTIAL) {
			this.logic = new SequentialQuestionSizeLogic(elementSize);
		} else {
			questionCount = bundle
					.getInt(Constants.SymbolTraingParam.Key.QUESTION_COUNT);
			Log.d(TAG, "questionCount=" + questionCount);
			int unitCount = 4;
			if (unitCountSetting == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_MANUAL) {
				unitCount = bundle
						.getInt(Constants.SymbolTraingParam.Key.UNIT_COUNT);
			}
			if (unitCount > displayPanel.getMaxUnitCount()) {
				Log.w(TAG,
						"unitcount is too larger than what displaypanel can handle.unitcount="
								+ unitCount);
				unitCount = displayPanel.getMaxUnitCount();
			}
			this.logic = new FixedQuestionSizeLogic(unitCount, elementSize,
					questionCount);
		}
        
		LinearLayout linerLayout = new LinearLayout(this);
		linerLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		param.gravity = Gravity.CENTER;
		
		startFlagImageView = (StartFlagImageView)findViewById(R.id.StartFlagImageView17);
		//将答题状态标志灯变为红色,状态设为初始状态
		startFlagImageView.setImageResource(R.drawable.red);
		startFlagImageView.setState(StartFlagImageView.STATE_INIT);
		
		SymbolMemoryTrainingParameter parameter = new SymbolMemoryTrainingParameter();
		
		parameter.setQuestionCount(questionCount);
		
		//创建参数键值对
		Map<String, Object> parameters = new HashMap<String, Object>();
		//添加ColorMemoryTrainingParameter
		parameters.put(Constants.PARAMETER, parameter);
		parameters.put(Constants.CONTEXT, SymbolMemeryTrainingActivity.this);
		service = new SymbolMemeryTrainingServiceImpl();
		Log.d(TAG,"service create");
		//根据参数初始化服务
		try {
			service.init(parameters);
			Log.d(TAG, "service init");
		}catch (LackOfParametersException e) {
			e.printStackTrace();
			ActivityUtil.finish(SymbolMemeryTrainingActivity.this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActivityUtil.finish(SymbolMemeryTrainingActivity.this);
		}
		linerLayout.addView(displayPanel.getDisplayableView(), param);
		linerLayout.addView(Helper.getOperationPanel(sampleSet, this,
				getOperationPanelListener()), param);
		
		layout.addView(linerLayout,
				getCentralLayoutParams());
		this.setContentView(layout);
		countDownTimer = createCountDownTimer();

	    service.start();
		
		handler = new Handler();
		int animationSetting = bundle
				.getInt(Constants.SymbolTraingParam.Key.ANIMATION_SETTING);
		if (animationSetting == Constants.SymbolTraingParam.ANIMATION_SETTING_SAME_TIME_FADEIN_FADEOUT) {
			animDelegate = new SameTimeFadeInFadeOut(
					createAnimationEndListener(), handler);
		} else if (animationSetting == Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_FADEOUT) {
			animDelegate = new SingleFadeInFadeOut(
					createAnimationEndListener(), handler);
		} else if (animationSetting == Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_SAME_FADEOUT) {
			animDelegate = new SingleFadeInSameFadeOut(
					createAnimationEndListener(), handler);
		} else {
			Log
					.e(TAG, "wrong animation setting code! code="
							+ animationSetting);
		}

		// ======================
		this.setTrainingTitle("符号辨识|记忆训练");
		// 显示用户名
		setUserNameEnabled(true);
		// 显示用户头像
		setUserIconEnable(true);
		this.setOkButtonEnable(false);
		this.setOnBackButtonTouchListener(new GoToActivityListener(this,
				SymbolMemoryTrainingChooseParamsActivity.class));
		this.setOnCancelButtonTouchListener(new GoToActivityListener(this,
				SymbolMemoryTrainingChooseParamsActivity.class));
		this.setOnHomeButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class));
	}

	private CountDownTimer createCountDownTimer() {
		return new CountDownTimer(ANSWER_INTERVAL, ANSWER_INTERVAL) {
			@Override
			public void onFinish() {
				Log.d(TAG, "countDownTimer on finish...");
				Toast.makeText(SymbolMemeryTrainingActivity.this, "超时",
						Toast.LENGTH_SHORT).show();
				logic.timeout();
				
				service.answerQuestion(questionIndex,false);
				questionIndex++;
				
				Log.d(TAG,"c");
				nextQuestionOrOver();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// do nothing...
			}
		};
	}

	private OperationPanelListener getOperationPanelListener() {
		return new Helper.OperationPanelListener() {
			@Override
			public void onClick(View view, final int index) {
				// 显示面板上显示该回答
				if (SymbolMemeryTrainingActivity.this.state == State.DISPLAYING) {
					Log.d(TAG, "displaying not complete.Ignore this clicking");
					
					Toast.makeText(SymbolMemeryTrainingActivity.this,
							"回答错误", Toast.LENGTH_SHORT).show();
					service.answerQuestion(questionIndex,false);
					questionIndex++;
					displayQuestion(logic.nextQuestion());
					
					return;
				}
				Log.d(TAG, "change imageview's image to " + index);
				SymbolMemeryTrainingActivity.this.displayPanel
						.setImageResource(
								logic.getAnswerIndex(),
								CommonUtil
										.getSampleElementImageResBySampleCode(sampleSet)[index]);
				displayPanel.getDisplayableView().invalidate();
				int result = logic.onAnswer(index);
				countDownTimer.cancel();
				Log
						.d(TAG,
								"count down timer cancel(on operationPanelListener)");
				if (result == Logic.ANSWER_RESULT_CORRECT_CONTINUE) {
					// 回答正确,继续
					countDownTimer.start();
					Log
							.d(TAG,
									"count down timer start(on operationPanelListener)");
				} else {
					if (result == Logic.ANSWER_RESULT_CORRECT_FINISH) {
						// 回答结束
						service.answerQuestion(questionIndex,true);
						questionIndex++;
						Toast.makeText(SymbolMemeryTrainingActivity.this,
								"回答正确", Toast.LENGTH_SHORT).show();
					} else if (result == Logic.ANSWER_RESULT_WRONG) {
						// 回答错误
						service.answerQuestion(questionIndex,false);
						questionIndex++;
						Toast.makeText(SymbolMemeryTrainingActivity.this,
								"回答错误", Toast.LENGTH_SHORT).show();
					}
					nextQuestionOrOver();
				}
			}
		};
	}

	private void playErrorMusic() {
		if (player == null) {
			player = createMediaPlayer();
			player.start();
		} else {
			player.seekTo(0);
			player.start();
		}
	}

	private MediaPlayer createMediaPlayer() {
		MediaPlayer p = MediaPlayer.create(this, R.raw.error);
		p.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.d(TAG, "PlayErrorMusic onError()what=" + what + " extra="
						+ extra);
				mp.release();
				player = null;
				return false;
			}
		});
		return p;
	}
	
	private void nextQuestionOrOver() {
		if (logic.trainingOver()) {
			// TODO 训练结束,生成测试报告
			destroy();
			
			startFlagImageView.setImageResource(R.drawable.red);
			startFlagImageView.setState(StartFlagImageView.STATE_STOP);
			service.stop();
			/*new AlertDialog.Builder(this).setTitle("训练结束").setMessage("训练结束")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									showTestReport(service.generateReport());
									startActivity(new Intent(
											getApplicationContext(),
											SymbolMemoryTrainingChooseParamsActivity.class));
									finish();
								}
							}).show();*/
			showTestReport(service.generateReport(logic.getQuestionTotal(),logic.getWrongCount()));
		} else {
			// 下一题目
			this.displayQuestion(logic.nextQuestion());
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		displayQuestion(logic.nextQuestion());
	}

	private void displayQuestion(int[] question) {
		SymbolMemeryTrainingActivity.this.state = State.DISPLAYING;
		displayPanel.arrangeQuestion(question);
		animDelegate.display(displayPanel);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player != null) {
			player.release();
		}
		Log.d(TAG, "destroying...");
		destroy();
	}

	private void destroy() {
		countDownTimer.cancel();
		Log.d(TAG, "count down timer cancel(in destroy)");
		handler.removeCallbacksAndMessages(null);
	}

	private RelativeLayout.LayoutParams getCentralLayoutParams() {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		return params;
	}

	private AnimationDelegate.AnimationEndListener createAnimationEndListener() {
		return new AnimationDelegate.AnimationEndListener() {

			@Override
			public void onAnimationEnd() {
				SymbolMemeryTrainingActivity.this.displayPanel
						.clearAnimationAndImage();
				SymbolMemeryTrainingActivity.this.state = State.ANSWERING;
				startFlagImageView.setState(StartFlagImageView.STATE_START);
				//切换答题状态灯至绿色
				startFlagImageView.setImageResource(R.drawable.green);
				Toast.makeText(SymbolMemeryTrainingActivity.this, "开始回答",
						Toast.LENGTH_SHORT).show();
				countDownTimer.start();
				Log.d(TAG, "count down timer start(onAnimationEnd)");
			}
		};
	}

	/**
	 * 负责显示时的动画设定
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-8 下午03:38:38
	 */
	static abstract class AnimationDelegate {

		AnimationEndListener l;
		Handler handler;

		public AnimationDelegate(AnimationEndListener l, Handler handler) {
			this.l = l;
			this.handler = handler;
		}

		public abstract void display(DisplayPanel panel);

		interface AnimationEndListener {
			public void onAnimationEnd();
		}
	}

	/**
	 * 同时淡入淡出的效果
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-8 下午03:43:51
	 */
	private class SameTimeFadeInFadeOut extends AnimationDelegate {
		public SameTimeFadeInFadeOut(AnimationEndListener l, Handler handler) {
			super(l, handler);
		}

		@Override
		public void display(final DisplayPanel panel) {
			for (ImageView iv : panel.getAllElements()) {
				iv.setVisibility(View.INVISIBLE);
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					for (ImageView iv : panel.getAllElements()) {
						iv.setVisibility(View.VISIBLE);
					}
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							for (ImageView iv : panel.getAllElements()) {
								iv.setVisibility(View.INVISIBLE);
							}
							l.onAnimationEnd();
						}
					}, DURATION);
				}
			}, DURATION);
		}
	}

	/**
	 * 单个淡入淡出
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-8 下午04:35:27
	 */
	private class SingleFadeInFadeOut extends AnimationDelegate {
		// public static final long DISPLAY_INTERVAL = DURATION;// 每个元素显示时的时间间隔

		/**
		 * 是否是随机显示
		 */
		// boolean random = false;
		public SingleFadeInFadeOut(AnimationEndListener l, Handler handler) {
			super(l, handler);
		}

		@Override
		public void display(DisplayPanel panel) {
			final View[] views = panel.getAllElements();
			for (View view : views) {
				view.setVisibility(View.INVISIBLE);
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					views[0].setVisibility(View.VISIBLE);
				}
			}, DURATION);
			for (int i = 0; i < views.length - 1; i++) {
				final View view = views[i];
				final View nextView = views[i + 1];

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						view.setVisibility(View.INVISIBLE);
						nextView.setVisibility(View.VISIBLE);
					}
				}, DURATION * (i + 1 + 1));
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					views[views.length - 1].setVisibility(View.INVISIBLE);
					l.onAnimationEnd();
				}
			}, DURATION * (views.length + 1));
		}
	}

	/**
	 * 单个淡入,同时淡出的效果
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-9 上午11:34:32
	 */
	class SingleFadeInSameFadeOut extends AnimationDelegate {

		public SingleFadeInSameFadeOut(AnimationEndListener l, Handler handler) {
			super(l, handler);
		}

		@Override
		public void display(DisplayPanel panel) {
			final View[] views = panel.getAllElements();
			for (View view : views) {
				view.setVisibility(View.INVISIBLE);
			}
			for (int i = 0; i < views.length; i++) {
				final View view = views[i];
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						view.setVisibility(View.VISIBLE);
					}
				}, DURATION * (i + 1));
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					for (View view : views) {
						view.setVisibility(View.INVISIBLE);
					}
					l.onAnimationEnd();
				}
			}, DURATION * (views.length + 1));
		}
	}

	/**
	 * 训练游戏的逻辑
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2011-2-28 下午01:56:41
	 */
	interface Logic {
		public static final int ANSWER_RESULT_CORRECT_FINISH = -1;// 回答正确并且回答结束
		public static final int ANSWER_RESULT_CORRECT_CONTINUE = -2;// 回答错误
		public static final int ANSWER_RESULT_WRONG = -3;// 回答错误

		/**
		 * 返回下一道题目
		 * 
		 * @return
		 */
		public int[] nextQuestion();

		/**
		 * 返回当前答案在的问题中的索引
		 * 
		 * @return
		 */
		int getAnswerIndex();

		/**
		 * 用户开始答题
		 * 
		 * @param answer
		 *            用户按下的键的index
		 * @return
		 * @see {@link #ANSWER_RESULT_CORRECT_CONTINUE}
		 */
		int onAnswer(int answer);

		/**
		 * 返回训练是否结束
		 * 
		 * @return
		 */
		boolean trainingOver();

		/**
		 * 返回回答错误的题目的数目
		 * 
		 * @return
		 */
		public int getWrongCount();
		public int getQuestionTotal();

		public void timeout();

	}

	/**
	 * 问题个数固定时的游戏逻辑(位元数固定时问题个数就是固定的)
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2011-2-28 下午01:57:29
	 */
	private class FixedQuestionSizeLogic implements Logic {
		/**
		 * 当前的问题
		 */
		private int[] question = null;

		/**
		 * 问题的元数
		 */
		private int questionSize;

		protected int elementSize;

		protected int[] pendingQuestion;

		/**
		 * 回答错误的问题的个数
		 */
		private int wrongCount = 0;

		public int getQuestionTotal() {
			return questionTotal;
		}

		public void setQuestionTotal(int questionTotal) {
			this.questionTotal = questionTotal;
		}

		public void setWrongCount(int wrongCount) {
			this.wrongCount = wrongCount;
		}

		/**
		 * 问题的总个数
		 */
		private int questionTotal;
		private int questionCount = 0;

		int answerIndex = 0;

		/**
		 * 问题元数
		 * 
		 * @param questionSize
		 * @param elementSize
		 */
		FixedQuestionSizeLogic(int questionSize, int elementSize,
				int questionTotal) {
			this.questionSize = questionSize;
			this.elementSize = elementSize;
			this.questionTotal = questionTotal;
			initPendingQuestion(questionSize, elementSize);
		}

		public int[] nextQuestion() {
			startFlagImageView.setImageResource(R.drawable.red);
			startFlagImageView.setState(StartFlagImageView.STATE_STOP);
			questionCount++;
			answerIndex = 0;
			ArrayOperations.shuffle(pendingQuestion);
			question = new int[getQuestionSize()];
			System
					.arraycopy(pendingQuestion, 0, question, 0,
							getQuestionSize());
			Log.d(TAG, "question=" + Arrays.toString(question));
			return question;
		}

		/**
		 * 返回当前答案在的问题中的索引
		 * 
		 * @return
		 */
		public int getAnswerIndex() {
			return answerIndex;
		}

		public int onAnswer(int answer) {
			service.startAnswer();
			if (question[answerIndex] != answer) {
				// 回答错误,不需要再继续
				
				playErrorMusic();
				wrongCount++;
				return Logic.ANSWER_RESULT_WRONG;
			}
			// 回答正确
			if (answerIndex == question.length - 1) {
				// 回答问题结束
				return Logic.ANSWER_RESULT_CORRECT_FINISH;
			}
			answerIndex = (answerIndex + 1) % question.length;
			return Logic.ANSWER_RESULT_CORRECT_CONTINUE;
		}

		@Override
		public boolean trainingOver() {
			return questionCount >= questionTotal;
		}

		protected int getQuestionSize() {
			return questionSize;
		}

		public int getWrongCount() {
			return wrongCount;
		}

		@Override
		public void timeout() {
			wrongCount++;
		}

		protected void initPendingQuestion(int questionSize, int elementSize) {
			int size = questionSize < elementSize ? elementSize : questionSize;
			pendingQuestion = new int[size];
			for (int i = 0; i < pendingQuestion.length; i++) {
				pendingQuestion[i] = i % elementSize;
			}
		}

	}

	/**
	 * 问题个数不固定时的游戏逻辑(选择"连续位元数"时问题个数不是固定的)
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2011-2-28 下午01:58:38
	 */
	private class SequentialQuestionSizeLogic extends FixedQuestionSizeLogic {
		/**
		 * 当前阶段错误的题目数目
		 */
		private int currentStageWrongCount = 0;

		/**
		 * 当前阶段的题目的数目
		 */
		private int currentStageQuestionCount = 0;

		/**
		 * 当前阶段的题目的位元数
		 */
		private int sequentialQuestionSize = 3;

		SequentialQuestionSizeLogic(int elementSize) {
			super(0, elementSize, 0);
		}

		@Override
		public int[] nextQuestion() {
			currentStageQuestionCount++;
			if (currentStageQuestionCount > questionEveryStage()) {
				nextStage();
				currentStageQuestionCount = 1;
			}
			if (pendingQuestion.length < sequentialQuestionSize) {
				initPendingQuestion(sequentialQuestionSize, elementSize);
			}
			return super.nextQuestion();
		}

		@Override
		public int onAnswer(int answer) {
			int rst = super.onAnswer(answer);
			if (rst == Logic.ANSWER_RESULT_WRONG) {
				// 回答错误
				currentStageWrongCount++;
			}
			return rst;
		}

		/**
		 * 进入下一个阶段
		 */
		private void nextStage() {
			sequentialQuestionSize++;
			if (sequentialQuestionSize > displayPanel.getMaxUnitCount()) {
				sequentialQuestionSize = displayPanel.getMaxUnitCount();
			}
			currentStageWrongCount = 0;
		}

		@Override
		protected int getQuestionSize() {
			return sequentialQuestionSize;
		}

		@Override
		public boolean trainingOver() {
			if (currentStageWrongCount >= 3) {
				return true;
			}

			if (getWrongCount() >= getMaxWrongCount()) {
				return true;
			}
			return false;
		}

		/**
		 * 返回该阶段允许的最大的错误累计数，如果大于等于该数目则认为测试结束
		 * 
		 * @see C模块中的说明
		 * @return
		 */
		private int getMaxWrongCount() {
			if (sequentialQuestionSize <= 7) {
				return 7;
			}
			if (sequentialQuestionSize <= 10) {
				return 8;
			}
			return 9 + (sequentialQuestionSize - 11) / 2;
		}

		private int questionEveryStage() {
			return 5;
		}
	}

	/**显示测试报告，实际上是跳转到专门显示测试报告的activity
	 * 
	 * @param testReport
	 */
	private void showTestReport(final SymbolMemeryTrainingReport testReport){		

		new AlertDialog.Builder(SymbolMemeryTrainingActivity.this)
		.setTitle(R.string.tips)
		.setMessage(R.string.test_over)
		.setCancelable(false)	//此alertDialog不允许被取消
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				    Intent intent = new Intent();
					//如果不是连续位元,跳转至TestReportActivity显示report
					intent.setClass(SymbolMemeryTrainingActivity.this, TestReportActivity.class);
				if(null==bundle){
					Log.d(TAG,"null==bundle");
				}
				bundle.putSerializable(Constants.TEST_REPORT, testReport);
				bundle.putString(Constants.CLASS_NAME, SymbolMemeryTrainingActivity.class.getName());
				bundle.putString(Constants.PREVIOUS_ACTIVITY, SymbolMemoryTrainingChooseParamsActivity.class.getName());
				intent.putExtras(bundle);
				startActivity(intent);
				finish();				
			}
		})
		.show();
	}
	
}
