package com.flextronics.cn.activity.rockertraining;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.R.id;
import com.flextronics.cn.activity.stabilitytraining.TrainingResultActivity;

public class ModeSelectActivity  extends BaseActivity{
	Button button01;
	Button button02;
	Button button03;
	private final static String TAG = "RockerTestActivity";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "RockerTestActivity on create");
		super.onCreate(savedInstanceState);
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.rocker_test_mode_select_training, null), getBaseLayoutParams());
		setContentView(layout);	
		button01 = (Button) findViewById(id.r_mode_select_1);
		button01.setText(R.string.h_mode_select_1);
		button02 = (Button) findViewById(id.r_mode_select_2);
		button02.setText(R.string.h_mode_select_2);
		button03 = (Button) findViewById(id.r_mode_select_3);
		button03.setText(R.string.h_mode_select_3);
		setupViews();
		setupListeners();
		
	}
	private void setupViews(){
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(true);
	}
	
	private void setupListeners(){
		setOnBackButtonTouchListener(new Button.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "RockerTestActivity");
				   Intent intent = new Intent(getApplicationContext(),RockerTestActivity.class);
			       startActivity(intent); 
			       finish();
            }
		});
        button02.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "SeparateControlActivity");
				   Intent intent = new Intent(getApplicationContext(),SeparateControlActivity.class);
			       startActivity(intent); 
			       finish();
            }
		});
        button03.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "CooperativeControlActivity");
				   Intent intent = new Intent(getApplicationContext(),CooperativeControlActivity.class);
			       startActivity(intent); 
			       finish();
            }
		});
	}
}
