package com.flextronics.cn.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.flextronics.cn.activity.user.UserManagementActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity is just show loading animation, 
 * we can add some service which should start after 
 * SavantBelle loads completed in this activity.  
 * 
 * @author ZhangGuoYin
 *
 */
public class LoadingActivity extends Activity {

	private ImageView loadingImageView;
	private TextView loadingTextView;
	private String loadingStr;
	private int count;
	
	//this handler will show "loading..."
	Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			count ++;
			loadingStr += ".";
			loadingTextView.setText(loadingStr);
			if (count >= 4) {
				count = 0;
				loadingStr = getApplicationContext().getString(R.string.loading);
			}
			handler.sendMessageDelayed(Message.obtain(), 500);
		}
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		setupViews();
		loadingStr = this.getString(R.string.loading);
		setupAnimations();
	}
	
	private void setupViews(){

		loadingImageView = (ImageView)findViewById(R.id.ImageView01);
		loadingTextView = (TextView)findViewById(R.id.TextView01);
	}
	
	/**
	 * setup the loading animation, the animation is translate animation, create by R.anim.loading;
	 * it makes the loading image moves from left to right.
	 * when it starts, it schedules a task 
	 */
	private void setupAnimations(){	
		
		final Animation animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		animation.setFillAfter(true);
		animation.setAnimationListener(new Animation.AnimationListener(){
			
			public void onAnimationEnd(Animation paramAnimation) {
				// TODO Auto-generated method stub				
			}
			
			public void onAnimationRepeat(Animation paramAnimation) {
				// TODO Auto-generated method stub				
			}

			
			public void onAnimationStart(Animation paramAnimation) {
				// TODO Auto-generated method stub
				TimerTask task = new TimerTask(){

					
					public void run() {
						//when the loading animation completes 90%, 
						//we will start the user management activity
						Intent intent = new Intent(LoadingActivity.this, UserManagementActivity.class);
						startActivity(intent);
						finish();
					}
				};
				Timer timer = new Timer();
				timer.schedule(task, (long)(0.9*animation.getDuration()));
			}			
		});
		loadingImageView.startAnimation(animation);

		handler.sendMessage(Message.obtain());
	}

	
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		((BitmapDrawable)loadingImageView.getDrawable()).getBitmap().recycle();
	}	
}