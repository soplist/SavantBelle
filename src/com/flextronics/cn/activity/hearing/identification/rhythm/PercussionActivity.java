package com.flextronics.cn.activity.hearing.identification.rhythm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.identification.RhythmIdentificationActivity;
import com.flextronics.cn.adapter.MusicalInstrumentsAdapter;
import com.flextronics.cn.adapter.RhythmAdatper;
import com.flextronics.cn.service.hearing.IRhythmService;
import com.flextronics.cn.service.hearing.impl.RhythmService;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class PercussionActivity extends BaseActivity {
	private RelativeLayout baseLayout;
	
	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;

	private RelativeLayout topFrameLayout;

	private RelativeLayout leftFrameLayout;

	private RelativeLayout rightFrameLayout;

	private RelativeLayout centerFrameLayout;

	private RelativeLayout bottomFrameLayout;

	private GridView musicalInstrumentsListView;

	private GridView rhythmListView;

	private MusicalInstrumentsAdapter musicalInstrumentsAdapter;

	private RhythmAdatper rhythmAdapter;

	private ImageView detailView;

	private ImageView playView;

	private Button singleToneButton;

	private Button complexToneButton;

	private IRhythmService service;

	private int musicalInstrumentsType;

	private int rhythmType;

	private int white=Color.WHITE;

	private int black=Color.BLACK;

	private boolean singleToneFlag=true;

	private boolean complexToneFlag=false;

	private Map<Integer, List<Integer>> map=new HashMap<Integer, List<Integer>>();

	private List<Integer> musicalInstrumentsList=new ArrayList<Integer>();

	private List<Integer> rhythmList=new ArrayList<Integer>();

	private List<Map<Integer, List<Integer>>> list=new ArrayList<Map<Integer,List<Integer>>>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseLayout=getBaseRelativeLayout();
		lineLayout=(RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		displayLayout=new RelativeLayout(this);
		displayLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ONE);
		baseLayout.addView(lineLayout, getBaseLayoutParams());
		baseLayout.addView(displayLayout, getBaseLayoutParams());
		setContentView(baseLayout);

		service=new RhythmService();
		service.init(this);

		setupView();
		setupListener();
	}
	
	private void setupView(){
		setTrainingTitle(getString(R.string.hearing_system_identification_training_rhythm_percussion));
		
		setUserNameEnabled(true);
		
		setUserIconEnable(true);
		
		setCancelButtonEnable(false);

		setOkButtonEnable(false);

		drawTopFrameLayout();

		drawLeftFrameLayout();

		drawRightFrameLayout();

		drawCenterFrameLayout();

		drawBottomFrameLayout();

		drawPlayView();

		setPlayViewEnable(true);

		setBackgroundDrawable(singleToneButton);
	}
	
	private void setupListener(){
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(),RhythmIdentificationActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {					
					startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});
		musicalInstrumentsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				musicalInstrumentsType = MusicalInstruments.PercussionRhythm.ARRAY[position];
				map = new HashMap<Integer, List<Integer>>();
				rhythmList = new ArrayList<Integer>();
				musicalInstrumentsList.add(musicalInstrumentsType);
				map.put(musicalInstrumentsType, rhythmList);
				list.add(map);
				detailView.setImageDrawable(getResources().getDrawable(
				musicalInstrumentsAdapter.getMusicalInstrumentsDetailImage()[musicalInstrumentsType-100]));
				if (singleToneFlag) {
					for (int i = 0; i < parent.getChildCount(); i++) {
						if (parent.getChildAt(i)==view) {
							parent.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_background_01));
						}else {
							parent.getChildAt(i).setBackgroundDrawable(null);
						}
					}
				}else {
					for (int i = 0; i < parent.getChildCount(); i++) {
						parent.getChildAt(i).setBackgroundDrawable(null);
					}
				}
			}
		});
		
		rhythmListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				rhythmType = HearingConstants.Rhythm.ARRAY[position];
				rhythmList.add(rhythmType);
			}
		});
		
		singleToneButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					singleToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_down));
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					setBackgroundDrawable(singleToneButton);
					selectFunction(singleToneButton);
				}
				return false;
			}
		});
		
		complexToneButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					complexToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_down));
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					setBackgroundDrawable(complexToneButton);
					selectFunction(complexToneButton);
				}
				return false;
			}
		});
		
		playView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (singleToneFlag) {
						service.singleToneService(musicalInstrumentsType,rhythmType);
					} else if (complexToneFlag) {
						if (rhythmList.size()>0&&musicalInstrumentsList.size()>0) {
							detailView.setImageDrawable(null);
							service.complexToneService(musicalInstrumentsList,list);
						}
					}
					clear();
				}
				return false;
			}
		});
	}
	
	/**
	 * 绘制页面上方的框架布局
	 */
	private void drawTopFrameLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=160;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		topFrameLayout=new RelativeLayout(this);
		topFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_TWO);
		topFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_02));
		displayLayout.addView(topFrameLayout, params);
	}
	
	private void drawLeftFrameLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(280,285);
		params.topMargin=5;
		params.leftMargin=5;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.topMargin=5;
		params2.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_THREE);
		
		leftFrameLayout=new RelativeLayout(this);
		leftFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		detailView=new ImageView(this);
		
		leftFrameLayout.addView(detailView, params2);
		topFrameLayout.addView(leftFrameLayout,params);
	}
	
	private void drawRightFrameLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(605,285);
		params.topMargin=5;
		params.leftMargin=10;
		params.addRule(RelativeLayout.RIGHT_OF,HearingConstants.LayoutId.LAYOUT_ID_THREE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.topMargin=5;
		
		rightFrameLayout=new RelativeLayout(this);
		rightFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		musicalInstrumentsListView=new GridView(this);
		musicalInstrumentsAdapter=new MusicalInstrumentsAdapter(this,true,MusicalInstruments.PercussionRhythm.ARRAY);
		musicalInstrumentsListView.setSelector(getResources().getDrawable(R.drawable.hs_background_01));
		musicalInstrumentsListView.setAdapter(musicalInstrumentsAdapter);
		musicalInstrumentsListView.setNumColumns(5);
		musicalInstrumentsListView.setVerticalSpacing(10);
		musicalInstrumentsListView.setGravity(Gravity.CENTER);
		
		rightFrameLayout.addView(musicalInstrumentsListView, params2);
		topFrameLayout.addView(rightFrameLayout, params);
	}
	
	private void drawCenterFrameLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=10;
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(900,110);
		params2.addRule(RelativeLayout.CENTER_IN_PARENT,HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		
		centerFrameLayout=new RelativeLayout(this);
		centerFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		centerFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_03));
		rhythmListView=new GridView(this);
		
		rhythmListView.setSelector(getResources().getDrawable(R.drawable.hs_background_01));
		rhythmListView.setNumColumns(4);
		rhythmListView.setVerticalSpacing(10);
		rhythmListView.setGravity(Gravity.CENTER);
		rhythmAdapter=new RhythmAdatper(this,true,HearingConstants.Rhythm.ARRAY);
		rhythmListView.setAdapter(rhythmAdapter);
		centerFrameLayout.addView(rhythmListView, params2);
		displayLayout.addView(centerFrameLayout, params);
	}
	
	private void drawBottomFrameLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=10;
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.leftMargin=200;
		params2.addRule(RelativeLayout.CENTER_VERTICAL,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=30;
		params3.addRule(RelativeLayout.RIGHT_OF,HearingConstants.ViewId.VIEW_ID_ONE);
		params3.addRule(RelativeLayout.CENTER_VERTICAL,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		bottomFrameLayout=new RelativeLayout(this);
		bottomFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		bottomFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_04));
		
		singleToneButton=new Button(this);
		singleToneButton.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		singleToneButton.setText("单音");
		singleToneButton.setTextColor(black);
		singleToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
		
		complexToneButton=new Button(getApplication());
		complexToneButton.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		complexToneButton.setText("复合音");
		complexToneButton.setTextColor(black);
		complexToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
		
		bottomFrameLayout.addView(singleToneButton, params2);
		bottomFrameLayout.addView(complexToneButton, params3);
		displayLayout.addView(bottomFrameLayout, params);
	}
	private void drawPlayView(){
		//设定playView的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定playView距上边距的距离为10dpi
		params.topMargin=10;
		//设定playView的在父框架中的布局规则
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//设定playView的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//实例化playView对象
		playView=new ImageView(this);
		//给playView设定图片
		playView.setImageDrawable(getResources().getDrawable(R.drawable.hs_play_button));
		//给playView设定点击状态为可用
		playView.setClickable(true);
		//添加到displayLayout中显示
		displayLayout.addView(playView, params);
	}
	
	private void setPlayViewEnable(boolean flag){
		if(flag){
			playView.setEnabled(true);
		}else {
			playView.setEnabled(false);
		}
	}
	
	private void setBackgroundDrawable(Button button){
		Button [] functionArray={singleToneButton,complexToneButton};
		for (int i = 0; i < functionArray.length; i++) {
			if(button==functionArray[i]){
				functionArray[i].setTextColor(white);
				functionArray[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_up));
			}else {
				functionArray[i].setTextColor(black);
				functionArray[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
			}
		}
	}

	private void selectFunction(Button button){  
		if(button==singleToneButton){
			singleToneFlag=true;
			complexToneFlag=false;
		}else if(button==complexToneButton){
			singleToneFlag=false;
			complexToneFlag=true;
		}else {
			
		}
		clear();
	}
	
	private void clear(){
		musicalInstrumentsList.clear();
		rhythmList.clear();
		map.clear();
		list.clear();
	}
	
	protected void onStop() {
		service.stop();
		super.onStop();
	}
}