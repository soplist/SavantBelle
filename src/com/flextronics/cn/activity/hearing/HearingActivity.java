package com.flextronics.cn.activity.hearing;

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
import com.flextronics.cn.activity.hearing.identification.MusicalInstrumentsIdentificationActivity;
import com.flextronics.cn.activity.hearing.identification.RhythmIdentificationActivity;
import com.flextronics.cn.activity.hearing.identification.ScaleIdentificationActivity;
import com.flextronics.cn.activity.hearing.memory.MusicalInstrumentsMemoryActivity;
import com.flextronics.cn.activity.hearing.memory.RhythmMemoryActivity;
import com.flextronics.cn.activity.hearing.memory.ScaleMemoryActivity;
import com.flextronics.cn.activity.hearing.response.MusicalInstrumentsResponseActivity;
import com.flextronics.cn.activity.hearing.response.RhythmReponseActivity;
import com.flextronics.cn.activity.hearing.response.ScaleResponseActivity;
import com.flextronics.cn.util.HearingConstants;

public class HearingActivity extends BaseActivity {	
	private RelativeLayout baseLayout;

	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;

	private RelativeLayout leftBottomLayout;
	
	private RelativeLayout framework;

	private LinearLayout trainning;
	
	private RelativeLayout trainningPanel;

	private LinearLayout smaple;
	
	private RelativeLayout smaplePanel;

	private ImageView leftBottomImageView;

	private TextView leftBottomTextView1;

	private TextView leftBottomTextView2;
	
	private boolean trainningPanelState;
	
	private boolean elementPanelState;
	
	private String trainingType=HearingConstants.Training.IDENTIFICATION_TRAINING;
	
	private String trainingText;
	
	private String smapleType=HearingConstants.Smaple.MUSICAL_INSTRUMENTS;
	
	private String smapleText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		trainingText=getString(R.string.hearing_system_identification_training);
		smapleText=getString(R.string.hearing_system_musical_instruments);
		
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
	
	public void setupView() {

		setTrainingTitle(getString(R.string.menuOptionsChoose));

		setUserNameEnabled(true);

		setUserIconEnable(true);

		setCancelButtonEnable(false);

		setOkButtonEnable(true);

		leftBottomImageView = (ImageView) findViewById(R.id.ImageView_left_bottom_1);

		leftBottomTextView1 = (TextView) findViewById(R.id.TextView_left_bottom_1);

		leftBottomTextView2 = (TextView) findViewById(R.id.TextView_left_bottom_2);

		leftBottomImageView.setImageResource(R.drawable.menu2);

		leftBottomTextView1.setText(R.string.Menu_B);

		leftBottomTextView2.setText(null);
		
		choosedFramework();

		choosedTraining();
		
		choosedTrainningPanel();
		
		choosedElement();
		
		choosedElementPanel();
		
		setButtonOnTouchUpBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_ONE);

		setButtonOnTouchUpBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
	}
	
	private void setupListener() {
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
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
					if (trainingType.equals(HearingConstants.Training.IDENTIFICATION_TRAINING)) {
						if (smapleType.equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
							startActivity(new Intent(getApplicationContext(), MusicalInstrumentsIdentificationActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.SCALE)) {
							startActivity(new Intent(getApplicationContext(), ScaleIdentificationActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.RHYTHM)) {
							startActivity(new Intent(getApplicationContext(), RhythmIdentificationActivity.class));
						}
					}else if (trainingType.equals(HearingConstants.Training.RESPONSE_TRAINING)) {
						if (smapleType.equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
							startActivity(new Intent(getApplicationContext(), MusicalInstrumentsResponseActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.SCALE)) {
							startActivity(new Intent(getApplicationContext(), ScaleResponseActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.RHYTHM)) {
							startActivity(new Intent(getApplicationContext(), RhythmReponseActivity.class));
						}
					}else if (trainingType.equals(HearingConstants.Training.MEMORY_TRAINING)) {
						if (smapleType.equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
							startActivity(new Intent(getApplicationContext(), MusicalInstrumentsMemoryActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.SCALE)) {
							startActivity(new Intent(getApplicationContext(), ScaleMemoryActivity.class));
						}else if (smapleType.equals(HearingConstants.Smaple.RHYTHM)) {
							startActivity(new Intent(getApplicationContext(), RhythmMemoryActivity.class));
						}
					}
					finish();
				}
				return false;
			}
		});
		setTrainingAndTrainningPanelListener();
		
		setSmapleAndSmaplePanelListener();
	}
	
	private void setTrainingAndTrainningPanelListener(){
		trainning.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!trainningPanelState) {
						trainningPanelState=true;
						trainningPanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						trainningPanelState=false;
						trainningPanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		trainningPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					trainingType=HearingConstants.Training.IDENTIFICATION_TRAINING;
					trainingText=((Button)v).getText().toString();
					setDeputyTitle(trainning, trainingText);
				}
				return false;
			}
		});
		
		trainningPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					trainingType=HearingConstants.Training.RESPONSE_TRAINING;
					trainingText=((Button)v).getText().toString();
					setDeputyTitle(trainning, trainingText);
				}
				return false;
			}
		});
		
		trainningPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(trainningPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					trainingType=HearingConstants.Training.MEMORY_TRAINING;
					trainingText=((Button)v).getText().toString();
					setDeputyTitle(trainning, trainingText);
				}
				return false;
			}
		});
	}
	
	private void setSmapleAndSmaplePanelListener(){
		smaple.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!elementPanelState) {
						elementPanelState=true;
						smaplePanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						elementPanelState=false;
						smaplePanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		smaplePanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_ONE);
					smapleType=HearingConstants.Smaple.MUSICAL_INSTRUMENTS;
					smapleText=((Button)v).getText().toString();
					setDeputyTitle(smaple, smapleText);
				}
				return false;
			}
		});
		
		smaplePanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_TWO);
					smapleType=HearingConstants.Smaple.SCALE;
					smapleText=((Button)v).getText().toString();
					setDeputyTitle(smaple, smapleText);
				}
				return false;
			}
		});
		
		smaplePanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(smaplePanel, HearingConstants.ViewId.VIEW_ID_THREE);
					smapleType=HearingConstants.Smaple.RHYTHM;
					smapleText=((Button)v).getText().toString();
					setDeputyTitle(smaple, smapleText);
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
	
	private void choosedTraining() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=10;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		trainning=new LinearLayout(this);
		trainning.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		trainning.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		trainning.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(this);
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_training));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(trainingText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(trainning,params);
		trainning.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	private void choosedTrainningPanel(){
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
		
		trainningPanel=new RelativeLayout(this);
		trainningPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		trainningPanel.setVisibility(RelativeLayout.GONE);
		trainningPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_identification_training));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_response_training));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_memory_training));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(trainningPanel,params);
		trainningPanel.addView(button, params2);
		trainningPanel.addView(button2, params3);
		trainningPanel.addView(button3, params4);
	}
	
	private void choosedElement() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		smaple=new LinearLayout(this);
		smaple.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		smaple.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		smaple.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(this);
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_smaple));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(smapleText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(smaple,params);
		smaple.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	private void choosedElementPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		smaplePanel=new RelativeLayout(this);
		smaplePanel.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		smaplePanel.setVisibility(RelativeLayout.GONE);
		smaplePanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_musical_instruments));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_scale));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_rhythm));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(smaplePanel,params);
		smaplePanel.addView(button, params2);
		smaplePanel.addView(button2, params3);
		smaplePanel.addView(button3, params4);
	}
}
