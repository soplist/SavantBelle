package com.flextronics.cn.activity.color;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 颜色辨识训练选择训练种类
 */
public class ColorTrainingChooseActivity extends BaseActivity {
	
	private final static String RESPONSE_TRAINING = "RESPONSE_TRAINING";
	private final static String MEMORY_TRAINING = "MEMORY_TRAINING";
	
	/**
	 * 训练类型按钮组
	 */
	private LinearLayoutBtnGroup testingTypeLinearLayoutBtnGroup;
	/****左下角那一组图像及文字****/
	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.color_training_choose中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.color_training_choose, null), getBaseLayoutParams());
		setContentView(layout);

		setupViews();
		setupListeners();
	}

	private void setupViews(){
		//设置训练标题
		setTrainingTitle(getString(R.string.menuOptionsChoose));
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像 
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		//显示next按钮
		setNextButtonEnable(true);
		//显示后退按钮
		setBackButtonEnable(true);
		//显示主页按钮
		setHomeButtonEnable(true);

		imageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		imageView.setImageResource(R.drawable.menu3);
		leftBottomTextView1.setText(R.string.Menu_C);
		leftBottomTextView2.setText(null);
		
		testingTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup01);
	}
	
	private void setupListeners(){
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {					
					startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){
	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnNextButtonTouchListener(new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {					
					Intent intent = new Intent();
					if (testingTypeLinearLayoutBtnGroup.getValue().equals(RESPONSE_TRAINING)) {
						intent.setClass(getApplicationContext(), ColorResponseTrainingChooseParamsActivity.class);					
					}else if (testingTypeLinearLayoutBtnGroup.getValue().equals(MEMORY_TRAINING)) {
						intent.setClass(getApplicationContext(), ColorMemoryTrainingChooseParamsActivity.class);
					}
					startActivity(intent);
					finish();
				}
				return false;
			}
		});		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 禁用键盘上的HOME,后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
