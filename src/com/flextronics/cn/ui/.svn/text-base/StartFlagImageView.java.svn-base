package com.flextronics.cn.ui;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.MyCountDownTimer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class StartFlagImageView extends ImageView{

	private final static String TAG = "StartFlagImageView";
	public final static int STATE_INIT = 10;
	public final static int STATE_START = 11;
	public final static int STATE_PAUSE = 12;
	public final static int STATE_RESUME = 13;
	public final static int STATE_STOP = 14;
	public final static int STATE_TIME_UP = 15;

	private MyCountDownTimer countDownTimer;
	private Drawable startImage;
	private Drawable endImage;
	private int time;
	private int state;
	
	public Drawable getStartImage() {
		return startImage;
	}
	public void setStartImage(Drawable startImage) {
		this.startImage = startImage;
	}
	public Drawable getEndImage() {
		return endImage;
	}
	public void setEndImage(Drawable endImage) {
		this.endImage = endImage;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	private OnTimeUpListener onTimeUpListener;
	private OnInitListener onInitListener;
	private OnStartListener onStartListener;
	private OnPauseListener onPauseListener;
	private OnResumeListener onResumeListener;
	private OnStopListener onStopListener;

	public void setOnTimeUpListener(OnTimeUpListener onTimeUpListener) {
		this.onTimeUpListener = onTimeUpListener;
	}
	public void setOnInitListener(OnInitListener onInitListener) {
		this.onInitListener = onInitListener;
	}
	public void setOnStartListener(OnStartListener onStartListener) {
		this.onStartListener = onStartListener;
	}
	public void setOnPauseListener(OnPauseListener onPauseListener) {
		this.onPauseListener = onPauseListener;
	}
	public void setOnResumeListener(OnResumeListener onResumeListener) {
		this.onResumeListener = onResumeListener;
	}
	public void setOnStopListener(OnStopListener onStopListener) {
		this.onStopListener = onStopListener;
	}

	public static abstract interface OnTimeUpListener {
		public abstract void onTimeUp();
	}
	public static abstract interface OnInitListener {
		public abstract void onInit();
	}
	public static abstract interface OnStartListener {
		public abstract void onStart();
	}
	public static abstract interface OnPauseListener {
		public abstract void onPause();
	}
	public static abstract interface OnResumeListener {
		public abstract void onResume();
	}
	public static abstract interface OnStopListener {
		public abstract void onStop();
	}		
	
	public StartFlagImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setupListeners();
	}

	public StartFlagImageView(Context context) {
		super(context);

		setupListeners();
	}

	public StartFlagImageView(Context context, AttributeSet set) {
		super(context, set);

		TypedArray typedArray = context.obtainStyledAttributes(set, R.styleable.StartFlagImageView);
		this.time = typedArray.getInteger(R.styleable.StartFlagImageView_time, 2000);
		this.startImage = typedArray.getDrawable(R.styleable.StartFlagImageView_startImage);
		this.endImage = typedArray.getDrawable(R.styleable.StartFlagImageView_endImage);
		
		typedArray.recycle();

		setupListeners();
	}

	/**
	 * 初始化
	 */
	public void init(){

		Log.d(TAG, "init()");		

		if (state==STATE_STOP || state==0 || state==STATE_TIME_UP) {//在stop、STATE_TIME_UP和默认状态下方可初始化
			StartFlagImageView.this.setImageDrawable(startImage);
			
			countDownTimer = new MyCountDownTimer(time, time){

				
				public void onFinish() {
					// TODO Auto-generated method stub
					StartFlagImageView.this.setImageDrawable(endImage);
					timeUp();
				}

				
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					
				}
			};

			state=STATE_INIT;
			Log.d(TAG, "state: STATE_INIT");
			
			onInitListener.onInit();
		}		
	}

	public void start(){

		Log.d(TAG, "start()");

		if (state==STATE_INIT) {
			countDownTimer.starts();
			state = STATE_START;
			Log.d(TAG, "state: STATE_START");
			
			onStartListener.onStart();
		}				
	}

	public void pause(){

		Log.d(TAG, "pause()");

		if (state==STATE_START || state==STATE_RESUME) {
			countDownTimer.pause();
			state = STATE_PAUSE;
			Log.d(TAG, "state: STATE_PAUSE");
			
			onPauseListener.onPause();
		}		
	}

	public void resume(){

		Log.d(TAG, "resume()");

		if (state==STATE_PAUSE) {
			countDownTimer.resume();

			state = STATE_RESUME;
			Log.d(TAG, "state: STATE_RESUME");
			
			onResumeListener.onResume();
		}

	}

	public void stop() {

		Log.d(TAG, "stop()");

		if (state!=STATE_STOP && state!=0) {
			countDownTimer.cancels();
			state = STATE_STOP;
			
			Log.d(TAG, "state: STATE_STOP");
			
			onStopListener.onStop();
		}		
	}

	private void timeUp(){
		Log.d(TAG, "timeUp()");
		state = STATE_TIME_UP;
		onTimeUpListener.onTimeUp();
	}
	
	private void setupListeners(){
		onTimeUpListener = new OnTimeUpListener(){

			public void onTimeUp() {
				// TODO Auto-generated method stub				
			}			
		};
		onInitListener = new OnInitListener(){

			public void onInit() {
				// TODO Auto-generated method stub				
			}			
		};
		onStartListener = new OnStartListener(){

			public void onStart() {
				// TODO Auto-generated method stub				
			}
		};
		onPauseListener = new OnPauseListener(){

			public void onPause() {
				// TODO Auto-generated method stub				
			}			
		};
		onResumeListener = new OnResumeListener(){

			public void onResume() {
				// TODO Auto-generated method stub				
			}			
		};
		onStopListener = new OnStopListener(){

			public void onStop() {
				// TODO Auto-generated method stub				
			}
		};
	}
}
