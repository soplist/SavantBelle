package com.flextronics.cn.activity.hearing.response;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.HearingActivity;
import com.flextronics.cn.activity.hearing.response.answer.RhythmResponseAnswerActivity;
import com.flextronics.cn.adapter.MusicalInstrumentsAdapter;
import com.flextronics.cn.adapter.RhythmAdatper;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class RhythmReponseActivity extends BaseActivity{
	private RelativeLayout baseLayout;

	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;

	private RelativeLayout leftBottomLayout;
	
	private RelativeLayout framework;

	private LinearLayout element;
	
	private RelativeLayout elementPanel;
	
	private LinearLayout musicalInstruments;
	
	private RelativeLayout musicalInstrumentsPanel;
	
	private LinearLayout scale;
	
	private RelativeLayout scalePanel;

	private ImageView leftBottomImageView;

	private TextView leftBottomTextView1;

	private TextView leftBottomTextView3;
	
	private boolean elementPanelState;
	
	private boolean musicalInstrumentsState;
	
	private boolean scaleState;
	
	private boolean randomEnabled;
	
	private String elementType=HearingConstants.Element.ONE_BAR_THREE_BEAT;
	
	private String elementText;
	
	private List<Integer> musicalInstrumentsSet=new ArrayList<Integer>();
	
	private String musicalInstrumentsText;
	
	private String musicalInstrumentsNameText;
	
	private List<Integer> scaleSet=new ArrayList<Integer>();
	
	private String scaleText;
	
	private String scaleNameText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSingleMusicalInstruments();
		setManyScale(elementType);
		
		elementText=getString(R.string.hearing_system_one_bar_three_beat);
		musicalInstrumentsText=getString(R.string.hearing_system_set_single_musical_instruments);
		musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
		scaleText=getString(R.string.hearing_system_set_many_measure);
		scaleNameText=nameArray(HearingConstants.Smaple.RHYTHM, scaleSet);
		
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
	private void setupView() {

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

		leftBottomTextView3.setText(R.string.hearing_system_response_training_rhythm);
		
		choosedFramework();

		choosedElement();
		
		choosedElementPanel();
		
		choosedMusicalInstruments();
		
		choosedMusicalInstrumentsPanel();
		
		choosedScale();
		
		choosedScalePanel();

		setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		
	}
	
	private void setupListener() {
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(),HearingActivity.class));
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
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Bundle bundle=new Bundle();
					bundle.
					putString(HearingConstants.ResponseUIParameters.ELEMENT_TYPE, elementType);
					bundle.putIntegerArrayList(
					HearingConstants.ResponseUIParameters.MUSICAL_INSTRUMENTS_ELEMENT_SET, (ArrayList<Integer>) musicalInstrumentsSet);
					bundle.putIntegerArrayList(
					HearingConstants.ResponseUIParameters.SCALE_ELEMENT_SET, (ArrayList<Integer>) scaleSet);
					bundle.
					putBoolean(HearingConstants.ResponseUIParameters.RANDOM, randomEnabled);
					Intent intent=new Intent();
					intent.setClass(getApplicationContext(), RhythmResponseAnswerActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
		setElementAndElementPanelListener();
		
		setMusicalInstrumentsAndMusicalInstrumentsPanelListener();
		
		setScaleAndScalePanelListener();
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
					elementType=HearingConstants.Element.ONE_BAR_THREE_BEAT;
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
					setElementPanelParams(musicalInstruments, musicalInstrumentsPanel, scale, scalePanel, elementType);
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
					elementType=HearingConstants.Element.ONE_BAR_FOUR_BEAT;
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
					setElementPanelParams(musicalInstruments, musicalInstrumentsPanel, scale, scalePanel, elementType);
				}
				return false;
			}
		});
	}
	
	private void setMusicalInstrumentsAndMusicalInstrumentsPanelListener(){
		musicalInstruments.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!musicalInstrumentsState) {
						musicalInstrumentsState=true;
						musicalInstrumentsPanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						musicalInstrumentsState=false;
						musicalInstrumentsPanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		musicalInstrumentsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					setSingleMusicalInstruments();
					musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText+"¦"+musicalInstrumentsNameText);
				}
				return false;
			}
		});
		
		musicalInstrumentsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					optionSingleMusicalInstruments();
				}
				return false;
			}
		});
		
		musicalInstrumentsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					randomMusicalInstruments();
					setDeputyTitle(musicalInstruments, musicalInstrumentsText);
				}
				return false;
			}
		});
	}
	
	private void setScaleAndScalePanelListener(){
		scale.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!scaleState) {
						scaleState=true;
						scalePanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						scaleState=false;
						scalePanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		scalePanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_ONE);
					scaleText=((Button)v).getText().toString();
					randomEnabled=false;
					clearData(scaleSet);
					setManyScale(elementType);
					scaleNameText=nameArray(HearingConstants.Smaple.RHYTHM, scaleSet);
					setDeputyTitle(scale, scaleText);
				}
				return false;
			}
		});
		
		scalePanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_TWO);
					scaleText=((Button)v).getText().toString();
					randomEnabled=false;
					clearData(scaleSet);
					optionManyScale(elementType);
				}
				return false;
			}
		});
		
		scalePanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(scalePanel, HearingConstants.ViewId.VIEW_ID_THREE);
					scaleText=((Button)v).getText().toString();
					randomEnabled=true;
					setDeputyTitle(scale, scaleText);
				}
				return false;
			}
		});
	}
	
	private void setElementPanelParams(View view,View view2,View view3,View view4,String string){		
		randomEnabled=false;
		clearData(musicalInstrumentsSet);
		setSingleMusicalInstruments();
		setDeputyTitle(view, 
		getString(R.string.hearing_system_set_many_musical_instruments)+
		"¦"+nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet));
		
		setButtonOnTouchUpBackground(view2, HearingConstants.ViewId.VIEW_ID_ONE);
		
		clearData(scaleSet);
		setManyScale(string);
		setDeputyTitle(view3, 
		getString(R.string.hearing_system_set_single_scale)+
		"¦"+nameArray(HearingConstants.Smaple.SCALE, scaleSet));
		
		scaleState=false;
		setButtonOnTouchUpBackground(view4, HearingConstants.ViewId.VIEW_ID_ONE);
		view4.setVisibility(View.GONE);
	}
	
	private void setSingleMusicalInstruments(){
		musicalInstrumentsSet.add(MusicalInstruments.PercussionRhythm.KICK);
	}
	
	private void optionSingleMusicalInstruments(){
		RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
		final Dialog dialog = new Dialog(this, R.style.dialog);
		Gallery gallery = new Gallery(getApplicationContext());
		MusicalInstrumentsAdapter musicalInstrumentsAdapter=
		new MusicalInstrumentsAdapter(this,false,MusicalInstruments.PercussionRhythm.ARRAY);
		gallery.setAdapter(musicalInstrumentsAdapter);
		gallery.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_managerment_gally_1));
		gallery.setSpacing(10);
		relativeLayout.addView(gallery,new RelativeLayout.LayoutParams(320, 120));
		dialog.setContentView(relativeLayout,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				musicalInstrumentsSet.add(MusicalInstruments.PercussionRhythm.ARRAY[position]);
				if (musicalInstrumentsSet.size() == HearingConstants.DEFAULT_ELEMENT_COUNT_LOW) {
					musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText+"¦"+musicalInstrumentsNameText);
					dialog.cancel();
				}
			}
		});
		dialog.show();
	}
	
	private void setManyScale(String params){
		if (params.equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)) {
			scaleSet.add(HearingConstants.Rhythm.OneBarThreeBeat.T3_TWO);
			scaleSet.add(HearingConstants.Rhythm.OneBarThreeBeat.T3_THREE);
			scaleSet.add(HearingConstants.Rhythm.OneBarThreeBeat.T3_FOUR);
		}else if (params.equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)) {
			scaleSet.add(HearingConstants.Rhythm.OneBarFourBeat.T4_SEVEN);
			scaleSet.add(HearingConstants.Rhythm.OneBarFourBeat.T4_EIGHT);
			scaleSet.add(HearingConstants.Rhythm.OneBarFourBeat.T4_TEN);
		}
	}
	
	private void optionManyScale(final String params){
		int[] temp=null;
		if (params.equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)) {
			temp=HearingConstants.Rhythm.OneBarThreeBeat.ARRAY;
		}else if (params.equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)) {
			temp=HearingConstants.Rhythm.OneBarFourBeat.ARRAY;
		}
		final int[] temp2=temp;
		RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
		final Dialog dialog = new Dialog(this, R.style.dialog);
		Gallery gallery = new Gallery(getApplicationContext());
		RhythmAdatper rhythmAdatper=new RhythmAdatper(getApplicationContext(), false, temp2);
		gallery.setAdapter(rhythmAdatper);
		gallery.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_managerment_gally_1));
		gallery.setSpacing(10);
		relativeLayout.addView(gallery,new RelativeLayout.LayoutParams(320, 120));
		dialog.setContentView(relativeLayout,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
		dialog.show();
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				checkList(temp2[position], scaleSet);
				if (scaleSet.size() == HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM) {
					scaleNameText=nameArray(HearingConstants.Smaple.RHYTHM, scaleSet);
					setDeputyTitle(scale, scaleText+"¦"+scaleNameText);
					dialog.cancel();
				}
			}
		});
	}
	
	private void randomMusicalInstruments(){
		musicalInstrumentsSet.add(ArrayOperations.getRedomElementFromElements(MusicalInstruments.PercussionRhythm.ARRAY));
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
	
	private void clearData(List<Integer> list){
		list.clear();
	}
	
	private void checkList(int params,List<Integer> list){
		if (list.size()<=0) {
			list.add(params);
		}else {
			if(list.contains(params)){
				Toast.makeText(getApplicationContext(), "你已经选择过这个选项，请选择其他选项", Toast.LENGTH_SHORT).show();
			}else {
				list.add(params);
			}
		}
	}
	
	private String nameArray(String type,List<Integer> list){
		String[] nameStrings=null;
		String str=null;
		int temp=0;
		if (type.equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
			temp=100;
		}else if (type.equals(HearingConstants.Smaple.SCALE)) {
			temp=200;
		}else if (type.equals(HearingConstants.Smaple.RHYTHM)) {
			temp=300;
		}
		if (type.equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
			nameStrings=getResources().getStringArray(R.array.MusicalInstrumentsNameArray);
		}else if (type.equals(HearingConstants.Smaple.SCALE)) {
			nameStrings=getResources().getStringArray(R.array.ScaleNameArray);
		}else if (type.equals(HearingConstants.Smaple.RHYTHM)) {
			nameStrings=getResources().getStringArray(R.array.RhythmNameArray);
		}
		str="";
		for (int i = 0; i < list.size(); i++) {
			if (list.size()==1) {
				str+=nameStrings[list.get(i)-temp];
			}else {
				if (i==(list.size()-1)) {
					str+=nameStrings[list.get(i)-temp];
				}else {
					str+=nameStrings[list.get(i)-temp]+",";
				}
			}
		}
		return str;
	}
	
	private void choosedFramework(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,450);
		params.topMargin=160;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		FrameLayout.LayoutParams params2=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		
		ScrollView scrollView=new ScrollView(this);
		
		framework=new RelativeLayout(this);
		framework.setId(HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		displayLayout.addView(scrollView, params);
		scrollView.addView(framework, params2);
	}
	
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
		button.setText(getString(R.string.hearing_system_one_bar_three_beat));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_one_bar_four_beat));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		framework.addView(elementPanel,params);
		elementPanel.addView(button, params2);
		elementPanel.addView(button2, params3);
	}
	
	private void choosedMusicalInstruments() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		musicalInstruments=new LinearLayout(this);
		musicalInstruments.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		musicalInstruments.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		musicalInstruments.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(this);
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_musical_instruments));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(musicalInstrumentsText+"¦"+musicalInstrumentsNameText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		
		framework.addView(musicalInstruments,params);
		musicalInstruments.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	private void choosedMusicalInstrumentsPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		musicalInstrumentsPanel=new RelativeLayout(this);
		musicalInstrumentsPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		musicalInstrumentsPanel.setVisibility(RelativeLayout.GONE);
		musicalInstrumentsPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_set_single_musical_instruments));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_optional_single_musical_instruments));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_random_musical_instruments));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(musicalInstrumentsPanel,params);
		musicalInstrumentsPanel.addView(button, params2);
		musicalInstrumentsPanel.addView(button2, params3);
		musicalInstrumentsPanel.addView(button3, params4);
	}
	
	private void choosedScale() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		scale=new LinearLayout(this);
		scale.setId(HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		scale.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		scale.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(this);
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_scale));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(scaleText+"¦"+scaleNameText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(scale,params);
		scale.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	private void choosedScalePanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		scalePanel=new RelativeLayout(this);
		scalePanel.setId(HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		scalePanel.setVisibility(RelativeLayout.GONE);
		scalePanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_set_many_measure));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_optional_many_measure));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_random_measure));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(scalePanel,params);
		scalePanel.addView(button, params2);
		scalePanel.addView(button2, params3);
		scalePanel.addView(button3, params4);
	}
}