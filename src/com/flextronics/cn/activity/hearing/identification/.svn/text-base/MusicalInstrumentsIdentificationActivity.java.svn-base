package com.flextronics.cn.activity.hearing.identification;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.HearingActivity;
import com.flextronics.cn.activity.hearing.identification.musicalinstruments.FolkMusicActivity;
import com.flextronics.cn.activity.hearing.identification.musicalinstruments.ForeignMusicActivity;
import com.flextronics.cn.activity.hearing.identification.musicalinstruments.PercussionActivity;
import com.flextronics.cn.util.HearingConstants;


public class MusicalInstrumentsIdentificationActivity extends BaseActivity {	
	private RelativeLayout baseLayout;

	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;

	private RelativeLayout leftBottomLayout;
	
	private RelativeLayout framework;

	private LinearLayout element;
	
	private RelativeLayout elementPanel;

	private ImageView leftBottomImageView;

	private TextView leftBottomTextView1;

	private TextView leftBottomTextView3;
	
	private boolean elementPanelState;
	
	private String elementType=HearingConstants.Element.FOREIGN_MUSIC_SCALE;
	
	private String elementText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		elementText=getString(R.string.hearing_system_foreign_music);
		
		baseLayout = getBaseRelativeLayout();
		lineLayout = (RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		
		displayLayout = new RelativeLayout(this);
		displayLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		leftBottomLayout = (RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_left_bottom, null);
		
		baseLayout.addView(lineLayout, getBaseLayoutParams());
		baseLayout.addView(displayLayout, getBaseLayoutParams());
		baseLayout.addView(leftBottomLayout, getBaseLayoutParams());
		
		setContentView(baseLayout);
		
		setupView();
		setupListener();
	}
	/**
	 * 设置页面上要显示的内容
	 */
	public void setupView() {

		setTrainingTitle(getString(R.string.menuOptionsChoose));

		setUserNameEnabled(true);

		setUserIconEnable(true);

		setCancelButtonEnable(false);

		setOkButtonEnable(true);

		leftBottomImageView = (ImageView) findViewById(R.id.ImageView_left_bottom_1);

		leftBottomTextView1 = (TextView) findViewById(R.id.TextView_left_bottom_1);

		leftBottomTextView3 = (TextView) findViewById(R.id.TextView_left_bottom_3);

		leftBottomImageView.setImageResource(R.drawable.menu2);

		leftBottomTextView1.setText(R.string.Menu_B);

		leftBottomTextView3.setText(R.string.hearing_system_identification_training_musical_instruments);
		
		choosedFramework();

		choosedElement();
		
		choosedElementPanel();

		setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
	}
	
	/**
	 * 设置页面上显示的图标的点击事件
	 */
	private void setupListener() {
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), HearingActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {					
					startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});

		setOnOkButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (elementType.equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)) {
						startActivity(new Intent(getApplicationContext(), ForeignMusicActivity.class));
					}else if (elementType.equals(HearingConstants.Element.FOLK_MUSIC_SCALE)) {
						startActivity(new Intent(getApplicationContext(), FolkMusicActivity.class));
					}else if (elementType.equals(HearingConstants.Element.PERCUSSION_SCALE)) {
						startActivity(new Intent(getApplicationContext(), PercussionActivity.class));
					}
					finish();
				}
				return false;
			}
		});
		
		setElementAndElementPanelListener();
	}
	
	private void setElementAndElementPanelListener(){
		element.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!elementPanelState) {
						elementPanelState=true;
						elementPanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						elementPanelState=false;
						elementPanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		elementPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					elementType=HearingConstants.Element.FOREIGN_MUSIC_SCALE;
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
				}
				return false;
			}
		});
		
		elementPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					elementType=HearingConstants.Element.FOLK_MUSIC_SCALE;
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
				}
				return false;
			}
		});
		
		elementPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					elementType=HearingConstants.Element.PERCUSSION_SCALE;
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
				}
				return false;
			}
		});
	}
	
	private void setButtonOnTouchDownBackground(View view,int id){
		int[] temp=HearingConstants.ViewId.VIEW_ARRAY;
		for (int i = 0; i < temp.length; i++) {
			if (view!=null) {
				if (id==temp[i]) {
					if (view.findViewById(temp[i])!=null) {
						((Button)(view.findViewById(temp[i])))
						.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_2));
					}
				}else {
					if (view.findViewById(temp[i])!=null) {
						((Button)(view.findViewById(temp[i])))
						.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
					}
				}
			}
		}
	}
	
	private void setButtonOnTouchUpBackground(View view,int id){
		int[] temp=HearingConstants.ViewId.VIEW_ARRAY;
		for (int i = 0; i < temp.length; i++) {
			if (view!=null) {
				if (id==temp[i]) {
					if (view.findViewById(temp[i])!=null) {
						((Button)(view.findViewById(temp[i])))
						.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
					}
				}else {
					if (view.findViewById(temp[i])!=null) {
						((Button)(view.findViewById(temp[i])))
						.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
					}
				}
			}
		}
	}
	
	private void setDeputyTitle(View view,String text){
		((TextView)view.findViewById(HearingConstants.ViewId.VIEW_ID_ONE)
				.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).setText(text);
	}
	
	private void choosedFramework(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,450);
		
		params.topMargin=160;
		
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		framework=new RelativeLayout(this);
		
		framework.setId(HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		displayLayout.addView(framework, params);
	}
	
	/**
	 * 选择样本
	 */
	private void choosedElement() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=10;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		element=new LinearLayout(this);
		element.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		element.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		element.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(this);
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_element));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(elementText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(element,params);
		element.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	
	private void choosedElementPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_THREE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		elementPanel=new RelativeLayout(this);
		elementPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		elementPanel.setVisibility(RelativeLayout.GONE);
		elementPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_foreign_music));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_folk_music));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_percussion));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(elementPanel,params);
		elementPanel.addView(button, params2);
		elementPanel.addView(button2, params3);
		elementPanel.addView(button3, params4);
	}
}