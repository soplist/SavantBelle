package com.flextronics.cn.util;

import android.os.CountDownTimer;

public abstract class MyCountDownTimer extends CountDownTimer{


	private long currentTimeInMills;
	private long pauseTimeInMills;
	private long remainTimeInMills;
	private long millisInFuture;
	private long passTimeCount;
	
	private CountDownTimer _countDownTimer;


	public MyCountDownTimer(long arg0, long arg1) {
		super(arg0, arg1);
		millisInFuture = arg0;
	}

	public void pause(){
		cancel();
		if (_countDownTimer != null) {
			_countDownTimer.cancel();
		}
		pauseTimeInMills = System.currentTimeMillis();
	}

	public void resume(){
		long passTimeInMills = pauseTimeInMills - currentTimeInMills;
		passTimeCount += passTimeInMills;
		remainTimeInMills = millisInFuture - passTimeCount;

		_countDownTimer = new CountDownTimer(remainTimeInMills, remainTimeInMills){

			
			public void onFinish() {
				passTimeCount = 0;
				MyCountDownTimer.this.onFinish();
			}

			
			public void onTick(long arg0) {
				// TODO Auto-generated method stub				
			}
		};
		_countDownTimer.start();
		currentTimeInMills = System.currentTimeMillis();
	}

	public void starts(){
		currentTimeInMills = System.currentTimeMillis();
		start();
	}

	public void cancels(){
		cancel();		
		if (_countDownTimer != null) {
			_countDownTimer.cancel();
		}
	}
}
