package com.flextronics.cn.activity.hearingtouch;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.visionhearingtouch.VhtChooseTrainingActivity;
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
 * <b>A-视觉听觉触觉综合训练</b><br>
 * 听觉触觉反应训练--选择测试类型
 * @author ZhangGuoYin
 *
 */
public class ChooseParams4HtrtActivity extends BaseActivity {
	
	private final static String MUSIC = "MUSIC";
	private final static String SCALE = "SCALE";
	
	/**
	 * 训练类型按钮组
	 */
	private LinearLayoutBtnGroup testingTypeLinearLayoutBtnGroup;
	/****左下角那一组图像及文字****/
	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.vht_hearing_touch_response_training_choose_parameter中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_hearing_touch_response_training_choose_parameter, null), getBaseLayoutParams());
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
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		imageView.setImageResource(R.drawable.menu1);
		leftBottomTextView1.setText(R.string.Menu_A);
		leftBottomTextView3.setText(R.string.hearing_touch_response_training_key);
		
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
					startActivity(new Intent(getApplicationContext(), VhtChooseTrainingActivity.class));
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
					if (testingTypeLinearLayoutBtnGroup.getValue().equals(MUSIC)) {
						intent.setClass(getApplicationContext(), ChooseParams4HtrtMusicActivity.class);					
					}else if (testingTypeLinearLayoutBtnGroup.getValue().equals(SCALE)) {
						intent.setClass(getApplicationContext(), ChooseParams4HtrtScaleActivity.class);
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
