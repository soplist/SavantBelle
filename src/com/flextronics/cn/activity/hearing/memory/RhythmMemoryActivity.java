package com.flextronics.cn.activity.hearing.memory;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.HearingActivity;
import com.flextronics.cn.activity.hearing.memory.answer.RhythmMemoryAnswerActivity;
import com.flextronics.cn.adapter.MusicalInstrumentsAdapter;
import com.flextronics.cn.util.HearingConstants;

public class RhythmMemoryActivity extends BaseActivity {
	private RelativeLayout baseLayout;

	private RelativeLayout lineLayout;

	private RelativeLayout displayLayout;

	private RelativeLayout leftBottomLayout;
	
	private RelativeLayout framework;

	private LinearLayout choosedMode;
	
	private RelativeLayout choosedModePanel;
	
	private LinearLayout musicalInstruments;
	
	private RelativeLayout musicalInstrumentsPanel;
	
	private LinearLayout element;
	
	private RelativeLayout elementPanel;
	
	private LinearLayout choosedBit;
	
	private RelativeLayout choosedBitPanel;

	private ImageView leftBottomImageView;

	private TextView leftBottomTextView1;

	private TextView leftBottomTextView3;
	
	private boolean choosedModePanelState;
	
	private boolean musicalInstrumentsPanelState;
	
	private boolean elementPanelState;
	
	private boolean choosedBitPanelState;
	
	private boolean randomMusicalInstruments;
	
	private String choosedModeType=HearingConstants.Mode.MEASURE_RESPONSE;
	
	private String elementType=HearingConstants.Element.ONE_BAR_THREE_BEAT;
	
	private String choosedModeText;
	
	private String elementText;
	
	private List<Integer> musicalInstrumentsSet=new ArrayList<Integer>();
	
	private String musicalInstrumentsText;
	
	private String musicalInstrumentsNameText;
	
	private String bitType=HearingConstants.BitType.SETTING_BIT;
	
	private String bitText;
	
	private int optionalBit=3;
	
	private int startBit=3;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSingleMusicalInstruments();
		
		choosedModeText=getString(R.string.hearing_system_response_measure);
		musicalInstrumentsText=getString(R.string.hearing_system_set_single_musical_instruments);
		musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
		elementText=getString(R.string.hearing_system_one_bar_three_beat);
		bitText=getString(R.string.hearing_system_set_musical_instruments_bit);
		
		baseLayout = getBaseRelativeLayout();
		lineLayout = (RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		
		displayLayout = new RelativeLayout(getApplicationContext());
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

		leftBottomTextView3.setText(R.string.hearing_system_memory_training_rhythm);
		
		choosedFramework();

		choosedElement();
		
		choosedElementPanel();
		
		choosedMusicalInstruments();
		
		choosedMusicalInstrumentsPanel();
		
		choosedScale();
		
		choosedScalePanel();
		
		choosedBit();
		
		choosedBitPanel();

		setButtonOnTouchUpBackground(choosedModePanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
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
					startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});

		setOnOkButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {					
					Intent intent=new Intent();
					intent.setClass(getApplicationContext(), RhythmMemoryAnswerActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString(HearingConstants.MemoryUIParameters.CHOOSED_MODE, choosedModeType);
					bundle.putString(HearingConstants.MemoryUIParameters.ELEMENT_TYPE, elementType);
					bundle.putIntegerArrayList(
					HearingConstants.MemoryUIParameters.MUSICAL_INSTRUMENTS_ELEMENT_SET,(ArrayList<Integer>)musicalInstrumentsSet);
					bundle.putBoolean(HearingConstants.MemoryUIParameters.RANDOM_MUSICAL_INSTRUMENTS, randomMusicalInstruments);
					bundle.putString(HearingConstants.MemoryUIParameters.BIT_TYPE, bitType);
					bundle.putInt(HearingConstants.MemoryUIParameters.START_BIT, startBit);
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
		
		setChoosedBitAndChoosedBitPanelListener();
	}
	
	private void setElementAndElementPanelListener(){
		choosedMode.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!choosedModePanelState) {
						choosedModePanelState=true;
						choosedModePanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						choosedModePanelState=false;
						choosedModePanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		choosedModePanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(choosedModePanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(choosedModePanel, HearingConstants.ViewId.VIEW_ID_ONE);
					choosedModeText=((Button)v).getText().toString();
					setDeputyTitle(choosedMode, choosedModeText);
					setElementPanelParams();
					choosedModeType=HearingConstants.Mode.MEASURE_RESPONSE;
				}
				return false;
			}
		});
		
		choosedModePanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(choosedModePanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(choosedModePanel, HearingConstants.ViewId.VIEW_ID_TWO);
					choosedModeText=((Button)v).getText().toString();
					setDeputyTitle(choosedMode, choosedModeText);
					setElementPanelParams();
					choosedModeType=HearingConstants.Mode.BEAT_RESPONSE;
				}
				return false;
			}
		});
	}
	
	private void setMusicalInstrumentsAndMusicalInstrumentsPanelListener(){
		musicalInstruments.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!musicalInstrumentsPanelState) {
						musicalInstrumentsPanelState=true;
						musicalInstrumentsPanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						musicalInstrumentsPanelState=false;
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
					randomMusicalInstruments=false;
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					setSingleMusicalInstruments();
					musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText+"|"+musicalInstrumentsNameText);
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
					randomMusicalInstruments=false;
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					setManyMusicalInstruments();
					musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText+"|"+musicalInstrumentsNameText);
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
					randomMusicalInstruments=false;
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					optionMusicalInstruments(HearingConstants.DEFAULT_ELEMENT_COUNT_LOW);
				}
				return false;
			}
		});
		
		musicalInstrumentsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FOUR).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
					randomMusicalInstruments=false;
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					optionMusicalInstruments(HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
				}
				return false;
			}
		});
		
		musicalInstrumentsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FIVE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_FIVE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_FIVE);
					randomMusicalInstruments=true;
					musicalInstrumentsText=((Button)v).getText().toString();
					clearData(musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText);
				}
				return false;
			}
		});
	}
	
	private void setScaleAndScalePanelListener(){
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
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
					elementType=HearingConstants.Element.ONE_BAR_THREE_BEAT;
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
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
					elementType=HearingConstants.Element.ONE_BAR_FOUR_BEAT;
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
					elementText=((Button)v).getText().toString();
					setDeputyTitle(element, elementText);
					elementType=HearingConstants.Element.ALL_BEAT;
				}
				return false;
			}
		});
	}
	
	private void setChoosedBitAndChoosedBitPanelListener(){
		choosedBit.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (!choosedBitPanelState) {
						choosedBitPanelState=true;
						choosedBitPanel.setVisibility(RelativeLayout.VISIBLE);
					}else{
						choosedBitPanelState=false;
						choosedBitPanel.setVisibility(RelativeLayout.GONE);
					}
				}
				return false;
			}
		});
		
		choosedBitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					bitText=((Button)v).getText().toString();
					bitType=HearingConstants.BitType.SETTING_BIT;
					setDeputyTitle(choosedBit, bitText);
				}
				return false;
			}
		});
		
		choosedBitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					bitText=((Button)v).getText().toString();
					showChooseBitDialog(3,getArrayLength(),HearingConstants.BitType.SELF_CHOOSE_BIT);
				}
				return false;
			}
		});
		
		choosedBitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					bitText=((Button)v).getText().toString();
					showChooseBitDialog(3,getArrayLength(),HearingConstants.BitType.CONTINUED_BIT);
					bitType=HearingConstants.BitType.CONTINUED_BIT;
				}
				return false;
			}
		});
	}
	
	private void setElementPanelParams(){
		randomMusicalInstruments=false;
		clearData(musicalInstrumentsSet);
		setSingleMusicalInstruments();
		setDeputyTitle(musicalInstruments, 
		getString(R.string.hearing_system_set_single_musical_instruments)+
		"¦"+nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet));
		
		musicalInstrumentsPanelState=false;
		setButtonOnTouchUpBackground(musicalInstrumentsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		musicalInstrumentsPanel.setVisibility(View.GONE);
		
		setDeputyTitle(element, 
		getString(R.string.hearing_system_one_bar_three_beat));
		
		elementPanelState=false;
		elementType=HearingConstants.Element.ONE_BAR_THREE_BEAT;
		setButtonOnTouchUpBackground(elementPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		elementPanel.setVisibility(View.GONE);
		
		setDeputyTitle(choosedBit, 
		getString(R.string.hearing_system_set_scale_bit));
		
		choosedBitPanelState=false;
		bitType=HearingConstants.BitType.SETTING_BIT;
		setButtonOnTouchUpBackground(choosedBitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		choosedBitPanel.setVisibility(View.GONE);
	}
		
	private void setSingleMusicalInstruments(){
		musicalInstrumentsSet.add(HearingConstants.MusicalInstruments.PercussionRhythm.KICK);
	}
	
	private void setManyMusicalInstruments(){
		musicalInstrumentsSet.add(HearingConstants.MusicalInstruments.PercussionRhythm.KICK);
		musicalInstrumentsSet.add(HearingConstants.MusicalInstruments.PercussionRhythm.WOODBLOCK);
		musicalInstrumentsSet.add(HearingConstants.MusicalInstruments.PercussionRhythm.TRIANGLE);
	}
	
	private void optionMusicalInstruments(final int count){
		int[] temp=HearingConstants.MusicalInstruments.PercussionRhythm.ARRAY;
		final int[] temp2=temp;
		RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
		final Dialog dialog = new Dialog(this, R.style.dialog);
		Gallery gallery = new Gallery(getApplicationContext());
		MusicalInstrumentsAdapter musicalInstrumentsAdapter=new MusicalInstrumentsAdapter(getApplicationContext(),false,temp2);
		gallery.setAdapter(musicalInstrumentsAdapter);
		gallery.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_managerment_gally_1));
		gallery.setSpacing(10);
		relativeLayout.addView(gallery,new RelativeLayout.LayoutParams(320, 120));
		dialog.setContentView(relativeLayout,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
		dialog.show();
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				checkList(temp2[position], musicalInstrumentsSet);
				if (musicalInstrumentsSet.size() == count) {
					musicalInstrumentsNameText=nameArray(HearingConstants.Smaple.MUSICAL_INSTRUMENTS, musicalInstrumentsSet);
					setDeputyTitle(musicalInstruments, musicalInstrumentsText+"¦"+musicalInstrumentsNameText);
					dialog.cancel();
				}
			}
		});
	}
			
	private void showChooseBitDialog(int from,int to,final String type){
		final List<TextView> textViews = new ArrayList<TextView>();
		final Dialog dialog = new Dialog(this, R.style.cs_choose_bit_dialog);
		
		HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getApplicationContext());
		LinearLayout layout = new LinearLayout(getApplicationContext());
		horizontalScrollView.addView(layout);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(52, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams2.gravity = Gravity.CENTER;
		layout.addView(new TextView(getApplicationContext()), layoutParams2);
		for(int i=from; i<=to; i++){
			TextView textView = new TextView(getApplicationContext());
			textView.setText(i+"");
			if(type.equals(HearingConstants.BitType.SELF_CHOOSE_BIT) && optionalBit > 0){
				if(i == optionalBit){
					textView.setTextSize(26);
					textView.setTextColor(Color.parseColor("#2d791f"));
					textView.setTypeface(null, Typeface.BOLD);
				}else {
					textView.setTextSize(23);
					textView.setTextColor(Color.parseColor("#515151"));	
					textView.setTypeface(null, Typeface.NORMAL);
				}
			}else if(type.equals(HearingConstants.BitType.CONTINUED_BIT) && startBit > 0){
				if(i == startBit){
					textView.setTextSize(26);
					textView.setTextColor(Color.parseColor("#2d791f"));
					textView.setTypeface(null, Typeface.BOLD);
				}else {
					textView.setTextSize(23);
					textView.setTextColor(Color.parseColor("#515151"));	
					textView.setTypeface(null, Typeface.NORMAL);
				}
			}else if(i == from){
				textView.setTextSize(26);
				textView.setTextColor(Color.parseColor("#2d791f"));
				textView.setTypeface(null, Typeface.BOLD);
			}else {
				textView.setTextSize(23);
				textView.setTextColor(Color.parseColor("#515151"));	
				textView.setTypeface(null, Typeface.NORMAL);
			}
			layout.addView(textView, layoutParams);
			textViews.add(textView);
		}
		layout.addView(new TextView(getApplicationContext()), layoutParams2);
		for (final TextView textView : textViews) {
			textView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if(type.equals(HearingConstants.BitType.SELF_CHOOSE_BIT)){
						optionalBit = Integer.valueOf(textView.getText().toString());
					}else if (type.equals(HearingConstants.BitType.CONTINUED_BIT)) {
						startBit = Integer.valueOf(textView.getText().toString());
					}
					for(TextView textView2 : textViews){
						if(textView == textView2){
							textView2.setTextSize(26);
							textView2.setTextColor(Color.parseColor("#2d791f"));
							textView2.setTypeface(null, Typeface.BOLD);
						}else {
							textView2.setTextSize(23);
							textView2.setTextColor(Color.parseColor("#515151"));	
							textView2.setTypeface(null, Typeface.NORMAL);
						}
					}
					dialog.dismiss();
					if (type==HearingConstants.BitType.SELF_CHOOSE_BIT) {
						setDeputyTitle(choosedBit, bitText+":"+optionalBit);
						bitType=optionalBit+"";
					}else if (type==HearingConstants.BitType.CONTINUED_BIT) {
						setDeputyTitle(choosedBit, bitText+":"+startBit);
					}
				}
			});
		}
		dialog.setContentView(horizontalScrollView, new HorizontalScrollView.LayoutParams(300, 55));
		dialog.show();
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
	
	private int getArrayLength(){
		int length=0;
		if (elementType.equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)) {
			length=HearingConstants.Rhythm.OneBarThreeBeat.ARRAY.length;
		}else if (elementType.equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)) {
			length=HearingConstants.Rhythm.OneBarFourBeat.ARRAY.length;
		}else if (elementType.equals(HearingConstants.Element.ALL_BEAT)) {
			length=HearingConstants.Rhythm.ARRAY.length;
		}
		return length;
	}
	
	private void choosedFramework(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,450);
		params.topMargin=160;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		
		FrameLayout.LayoutParams params2=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		
		ScrollView scrollView=new ScrollView(getApplicationContext());
		
		framework=new RelativeLayout(getApplicationContext());
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
		
		choosedMode=new LinearLayout(getApplicationContext());
		choosedMode.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		choosedMode.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		choosedMode.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(getApplicationContext());
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(getApplicationContext());
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_element));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(getApplicationContext());
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(choosedModeText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(choosedMode,params);
		choosedMode.addView(lock, params2);
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
		
		choosedModePanel=new RelativeLayout(getApplicationContext());
		choosedModePanel.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		choosedModePanel.setVisibility(RelativeLayout.GONE);
		choosedModePanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(getApplicationContext());
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_response_measure));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(getApplicationContext());
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_response_beat));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		framework.addView(choosedModePanel,params);
		choosedModePanel.addView(button, params2);
		choosedModePanel.addView(button2, params3);
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
		
		musicalInstruments=new LinearLayout(getApplicationContext());
		musicalInstruments.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		musicalInstruments.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		musicalInstruments.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(getApplicationContext());
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(getApplicationContext());
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_musical_instruments));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		
		TextView deputyTitle=new TextView(getApplicationContext());
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
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params5=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params5.setMargins(20, 15, 20, 15);
		params5.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_THREE);
		
		RelativeLayout.LayoutParams params6=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params6.setMargins(20, 15, 20, 15);
		params6.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_ONE);
		
		musicalInstrumentsPanel=new RelativeLayout(getApplicationContext());
		musicalInstrumentsPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		musicalInstrumentsPanel.setVisibility(RelativeLayout.GONE);
		musicalInstrumentsPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(getApplicationContext());
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_set_single_musical_instruments));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(getApplicationContext());
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_set_many_musical_instruments));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(getApplicationContext());
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_optional_single_musical_instruments));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		Button button4=new Button(getApplicationContext());
		button4.setId(HearingConstants.ViewId.VIEW_ID_FOUR);
		button4.setText(getString(R.string.hearing_system_optional_many_musical_instruments));
		button4.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button4.setGravity(Gravity.CENTER);
		button4.setClickable(true);
		
		Button button5=new Button(getApplicationContext());
		button5.setId(HearingConstants.ViewId.VIEW_ID_FIVE);
		button5.setText(getString(R.string.hearing_system_random_musical_instruments));
		button5.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button5.setGravity(Gravity.CENTER);
		button5.setClickable(true);
		
		framework.addView(musicalInstrumentsPanel,params);
		musicalInstrumentsPanel.addView(button, params2);
		musicalInstrumentsPanel.addView(button2, params3);
		musicalInstrumentsPanel.addView(button3, params4);
		musicalInstrumentsPanel.addView(button4, params5);
		musicalInstrumentsPanel.addView(button5, params6);
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
		
		element=new LinearLayout(getApplicationContext());
		element.setId(HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		element.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		element.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(getApplicationContext());
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(getApplicationContext());
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_scale));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(getApplicationContext());
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
	
	private void choosedScalePanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		elementPanel=new RelativeLayout(getApplicationContext());
		elementPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		elementPanel.setVisibility(RelativeLayout.GONE);
		elementPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(getApplicationContext());
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_one_bar_three_beat));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(getApplicationContext());
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_one_bar_four_beat));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(getApplicationContext());
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_all_beat));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(elementPanel,params);
		elementPanel.addView(button, params2);
		elementPanel.addView(button2, params3);
		elementPanel.addView(button3, params4);
	}
	
	private void choosedBit() {
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=20;
		
		LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=5;
		
		choosedBit=new LinearLayout(getApplicationContext());
		choosedBit.setId(HearingConstants.LayoutId.LAYOUT_ID_NINE);
		choosedBit.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		choosedBit.setGravity(Gravity.CENTER_VERTICAL);
		
		LinearLayout lock=new LinearLayout(getApplicationContext());
		lock.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		lock.setBackgroundDrawable(null);
		lock.setGravity(Gravity.CENTER_VERTICAL);
		lock.setClickable(true);
		
		TextView title=new TextView(getApplicationContext());
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText(getString(R.string.hearing_system_choosed_bit));
		title.setTextSize(HearingConstants.TEXT_SIZE_BIG);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView deputyTitle=new TextView(getApplicationContext());
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(bitText);
		deputyTitle.setTextSize(HearingConstants.TEXT_SIZE_MEDIUM);
		deputyTitle.setTextColor(getResources().getColor(R.color.hearing_system_green));
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		
		framework.addView(choosedBit,params);
		choosedBit.addView(lock, params2);
		lock.addView(title, params3);
		lock.addView(deputyTitle, params4);
	}
	
	private void choosedBitPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_NINE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_TEN);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		choosedBitPanel=new RelativeLayout(getApplicationContext());
		choosedBitPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_TEN);
		choosedBitPanel.setVisibility(RelativeLayout.GONE);
		choosedBitPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(getApplicationContext());
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText(getString(R.string.hearing_system_set_bit_measure));
		button.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(getApplicationContext());
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText(getString(R.string.hearing_system_optional_bit_measure));
		button2.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(getApplicationContext());
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText(getString(R.string.hearing_system_cotinued_bit_measure));
		button3.setTextSize(HearingConstants.TEXT_SIZE_SMALL);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		framework.addView(choosedBitPanel,params);
		choosedBitPanel.addView(button, params2);
		choosedBitPanel.addView(button2, params3);
		choosedBitPanel.addView(button3, params4);
	}
}