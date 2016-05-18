package com.flextronics.cn.ui;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TimerView extends TextView{

	private final static String TAG = "TimerView";
	private final static int COUNT_IN_100_MILLS = 1;
	private final static int COUNT_IN_SEC = 2;
	private final static int STATE_INIT = 10;
	private final static int STATE_START = 11;
	private final static int STATE_PAUSE = 12;
	private final static int STATE_RESUME = 13;
	private final static int STATE_STOP = 14;

	private String mFormatText;	//显示格式
	private boolean mAsc = true;	//升序标志
	private long mStart;	//开始毫秒数
	private long mEnd;	//结束毫秒数

	private long count;
	private int hour;
	private int min;
	private int sec;
	private int partSec;	//十分之一秒
	private int countType;
	private long period;
	private Message msg;
	private Handler handler;	

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
	public long getStart() {
		return mStart;
	}
	public void setStart(long start) {
		mStart = start;
	}
	public long getEnd() {
		return mEnd;
	}
	public void setEnd(long end) {
		mEnd = end;
	}
	public String getFormatText() {
		return mFormatText;
	}
	public void setFormatText(String formatText) {
		mFormatText = formatText;
	}
	public boolean getAsc() {
		return mAsc;
	}
	public void setAsc(boolean asc) {
		mAsc = asc;
	}

	private String text;
	private Timer timer;
	private TimerTask timerTask;
	private long startTimeInMills;
	private long pauseTimeInMills;
	private int state;


	public TimerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setupListeners();
	}

	public TimerView(Context context) {
		super(context);

		setupListeners();
	}

	public TimerView(Context context, AttributeSet set) {
		super(context, set);

		TypedArray typedArray = context.obtainStyledAttributes(set, R.styleable.TimerView);
		this.mFormatText = typedArray.getString(R.styleable.TimerView_formatText);
		this.mStart = typedArray.getInteger(R.styleable.TimerView_start, 0);
		this.mAsc = typedArray.getBoolean(R.styleable.TimerView_asc, true);
		if (mAsc) {
			this.mEnd = typedArray.getInteger(R.styleable.TimerView_end, 3600*24*1000);
		}else {
			this.mEnd = typedArray.getInteger(R.styleable.TimerView_end, 0);
		}
		typedArray.recycle();

		if (mFormatText==null || mFormatText.equals("")) {//默认的显示格式为：hh:mm:ss.ff
			mFormatText = "hh:mm:ss.ff";
		}		

		Log.d(TAG, "mFormatText: " + mFormatText);
		Log.d(TAG, "mAsc: " + mAsc);
		Log.d(TAG, "mStart: " + mStart);
		Log.d(TAG, "mEnd: " + mEnd);

		setupListeners();

		showText(0, 0, 0, 0);
	}

	/**
	 * 初始化
	 */
	public void init(){

		Log.d(TAG, "init()");

		mFormatText = mFormatText.toLowerCase();
		if (mAsc) {
			if (this.mEnd < this.mStart) {
				this.mEnd = 3600*24*1000;
			}
		}else {
			if (this.mEnd > this.mStart) {
				this.mEnd = 0;
			}
		}
		final long oldCount;

		if (state==STATE_STOP || state==0) {//在stop和默认状态下方可初始化

			//如果显示的格式带十分之一秒的话,计算周期即为100ms;如果没有,则周期为1000ms.
			if (mFormatText.indexOf("ff") > -1) {
				countType = COUNT_IN_100_MILLS;
				period = 100;
				oldCount = mStart/100;
			}else {
				countType = COUNT_IN_SEC;
				period = 1000;
				oldCount = mStart/1000;
			}	
			count = oldCount;

			handler = new Handler(){

				
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 1:

						if (mAsc) {	//升序
							count ++;
						}else {	//降序
							count --;
						}

						int totalSec = 0;
						int yushu = 0;

						if (countType == COUNT_IN_100_MILLS) {
							totalSec = (int)(count / 10);
							yushu = (int)(count % 10);
						}else if (countType == COUNT_IN_SEC) {
							totalSec = (int)count;
						}

						if ((mAsc&&(totalSec >= mEnd/1000)) || ((!mAsc)&&(totalSec <= mEnd/1000))) {
							timeUp();
							yushu = 0;
							count = oldCount;
						}

						hour = totalSec/(60*60);
						min = (totalSec-hour*3600)/60;
						sec = totalSec - hour*3600 - min*60;

						partSec = yushu;

						showText(hour, min, sec, partSec);
						break;

					default:
						break;
					}
				}
			};

			state=STATE_INIT;
			Log.d(TAG, "state: STATE_INIT");
			
			onInitListener.onInit();
		}		
	}

	public void start(){

		Log.d(TAG, "start()");

		if (state==STATE_INIT || state==STATE_STOP) {
			startAction();
			state = STATE_START;
			Log.d(TAG, "state: STATE_START");
			
			onStartListener.onStart();
		}				
	}

	public void pause(){

		Log.d(TAG, "pause()");

		if (state==STATE_START || state==STATE_RESUME) {
			pauseTimeInMills = System.currentTimeMillis();

			timerTask.cancel();
			handler.removeMessages(msg.what);

			state = STATE_PAUSE;
			Log.d(TAG, "state: STATE_PAUSE");
			
			onPauseListener.onPause();
		}		
	}

	public void resume(){

		Log.d(TAG, "resume()");

		if (state==STATE_PAUSE) {
			int leavingTime = (int)(period - (pauseTimeInMills-startTimeInMills));
			if (leavingTime > 0) {
				timer.schedule(new TimerTask(){

					
					public void run() {
						timerTask.run();
						startAction();
					}
				}, leavingTime);
			}else {
				startAction();
			}

			state = STATE_RESUME;
			Log.d(TAG, "state: STATE_RESUME");
			
			onResumeListener.onResume();
		}

	}

	public void stop() {

		Log.d(TAG, "stop()");

		if (state!=STATE_STOP && state!=0) {
			stopAction();
			
			Log.d(TAG, "state: STATE_STOP");
			
			onStopListener.onStop();
		}		
	}

	private void timeUp(){
		Log.d(TAG, "timeUp()");

		if (state!=STATE_STOP && state!=0) {
			stopAction();
			
			Log.d(TAG, "state: STATE_STOP");
			
			onTimeUpListener.onTimeUp();
		}
	}

	private void startAction(){
		timerTask = new TimerTask(){

			
			public void run() {
				if (null == msg) {
					msg = new Message();
				}else {
					msg = Message.obtain();
				}
				msg.what = 1;
				handler.sendMessage(msg);
				startTimeInMills = System.currentTimeMillis();
			}
		};
		timer = new Timer(true);
		timer.schedule(timerTask, period, period);
	}

	private void stopAction(){
		Log.d(TAG, "stopAction()");
		
		timerTask.cancel();
		timerTask = null;
		timer.cancel();
		timer.purge();
		timer = null;

		if (null != msg) {
			handler.removeMessages(msg.what);
		}

		count = 0;
		hour = 0;
		min = 0;
		sec = 0;
		partSec = 0;
		showText(hour, min, sec, partSec);

		state = STATE_STOP;
		
	}

	private void showText(int hour, int min, int sec, int ff){
		DecimalFormat decimalFormat = new DecimalFormat("01");
		text = mFormatText.toLowerCase().replace("hh", decimalFormat.format(hour))
		.replace("mm", decimalFormat.format(min))
		.replace("ss", decimalFormat.format(sec))
		.replace("ff", String.valueOf(ff));

		TimerView.this.setText(text);
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
