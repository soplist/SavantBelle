package com.flextronics.cn.activity.user;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * this activity means you create new user successful
 * @author ZhangGuoYin
 *
 */
public class NewUserSuccessActivity extends BaseActivity {

	private TextView newUserNameTextView;
	private ImageView newUserIconImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.new_user_success, null), getBaseLayoutParams());
		setContentView(layout);
		
		//set which widgets should be show
		setupViews();
		
		//set the listeners
		setupListeners();
	}
	
	
	private void setupViews(){
		setTrainingTitle(getString(R.string.createNewUser));
		setBackButtonEnable(true);
		setUserName(null);
		setUserIconEnable(false);
		setCancelButtonEnable(false);
		
		newUserNameTextView = (TextView)findViewById(R.id.newUserName);
		newUserNameTextView.setText(getIntent().getExtras().getString("userName"));
		newUserIconImageView = (ImageView)findViewById(R.id.newUserIcon);
		newUserIconImageView.setImageResource(getIntent().getExtras().getInt("userIcon"));
	}
	
	
	private void setupListeners(){
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), UserManagementActivity.class));
					finish();				
				}
				return false;
			}
		});
		
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), NewUserActivity.class));
					finish();				
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), UserManagementActivity.class));
					finish();
				}
				return false;
			}
		});
	}	
}