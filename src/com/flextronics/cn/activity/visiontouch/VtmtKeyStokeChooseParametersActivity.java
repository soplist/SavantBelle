package com.flextronics.cn.activity.visiontouch;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.flextronics.cn.activity.visionhearingtouch.VhtChooseTrainingActivity;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.HearingConstants;

public class VtmtKeyStokeChooseParametersActivity extends BaseActivity {
	//声明一个常量
	public static final String TAG="VtmtKeyStokeChooseParametersActivity";
	//声明一个常量
	public static final float TEXT_SIZE=16f;
	/**
	 * 自选位元
	 */
	private final static String SELF_CHOOSE_BIT = "SELF_CHOOSE_BIT";
	/**
	 * 连续位元
	 */
	private final static String CONTINUED_BIT = "CONTINUED_BIT";
	
	// 声明一个RelativeLayout对象
	private RelativeLayout baseLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout lineLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout displayLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout framework;
	// 声明一个LinearLayout对象
	private LinearLayout elements;
	// 声明一个LinearLayout对象
	private RelativeLayout elementsPanel;
	// 声明一个LinearLayout对象
	private LinearLayout bit;
	// 声明一个LinearLayout对象
	private RelativeLayout bitPanel;
	// 声明一个LinearLayout对象
	private LinearLayout count;
	// 声明一个LinearLayout对象
	private RelativeLayout countPanel;
	// 声明一个LinearLayout对象
	private LinearLayout hand;
	// 声明一个RelativeLayout对象
	private RelativeLayout handPanel;
	
	private String elementTextOne="设定单一样本:";
	private String elementTextTwo="白光";
	private List<String> elementTextList=new ArrayList<String>();
	private String bitTextOne="设定位元数";
	private int bitTextTwo=3;
	private int countText=15;
	private String handText="右手";
	
	private String elementsParameters=String.valueOf(Constants.Sample.WHITE);
	private List<String> elementsList=new ArrayList<String>();
	private int bitParameters=Constants.BitType.GIVEN_BIT;
	private int bitSelfChosen = 3;
	private int startBit = 3;
	private int canSupportedMaxBit = 36;
	private int countParameters=15;
	private int handParameters=Constants.LEFT_HAND;
	
	
	boolean[] bs={false,false,false,false,false};
	
	protected void onCreate(Bundle savedInstanceState) {
		//继承父类的onCreate方法
		super.onCreate(savedInstanceState);
		//把父类中已经定义好的值赋给baseLayout
		baseLayout=getBaseRelativeLayout();
		//获得View对象并赋给lineLayout对象
		lineLayout=(RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		//实例化displayLayout对象
		displayLayout=new RelativeLayout(this);
		//设置displayLayout对象的ID号
		displayLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//把lineLayout对象添加到baseLayout对象中
		baseLayout.addView(lineLayout, getBaseLayoutParams());
		//把displayLayout对象添加到baseLayout对象中
		baseLayout.addView(displayLayout, getBaseLayoutParams());
		//设置当前显示的视图为baseLayout对象
		setContentView(baseLayout);
		//调用设置视图的方法
		setupView();
		//调用设置监听器的方法
		setupListener();
	}
	
	private void setupView(){
		
		setTrainingTitle(getString(R.string.menuOptionsChoose));
		
		setUserNameEnabled(true);
		
		setUserIconEnable(true);
		
		setCancelButtonEnable(false);
		
		setOkButtonEnable(true);
		
		choosedFramework();
		
		choosedElements();
		
		choosedElementsPanel();
		
		choosedBit();
		
		choosedBitPanel();
		
		choosedCount();
		
		choosedCountPanel();
		
		choosedHand();
		
		choosedHandPanel();
		
		setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_ELEVEN,0);
		
		setElementPanelButtonEnabled(1);
		
		setButtonOnTouchUpBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(countPanel, HearingConstants.ViewId.VIEW_ID_ONE);
		
		setButtonOnTouchUpBackground(handPanel, HearingConstants.ViewId.VIEW_ID_TWO);
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_EIGHT).setVisibility(View.INVISIBLE);
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_NINE).setVisibility(View.INVISIBLE);
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TEN).setVisibility(View.INVISIBLE);
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ELEVEN).setVisibility(View.INVISIBLE);
	}
	
	private void setupListener(){
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
		
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					startActivity(new Intent(VtmtKeyStokeChooseParametersActivity.this,VhtChooseTrainingActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (bs[3]) {
						elementsParameters=listToString(elementsList);
					}
					Intent intent=new Intent();
					intent.setClass(VtmtKeyStokeChooseParametersActivity.this,VtmtKeyStokeOperationActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString(Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_SET,elementsParameters);
					bundle.putInt(Constants.VisioTouchMemoryTrainingUIParameter.BIT_TYPE,bitParameters);
					bundle.putInt(Constants.VisioTouchMemoryTrainingUIParameter.START_BIT,startBit);
					bundle.putInt(Constants.VisioTouchMemoryTrainingUIParameter.QUESTION_COUNT, countParameters);
					bundle.putInt(Constants.VisioTouchMemoryTrainingUIParameter.HAND_TYPE, handParameters);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
		setElementAndElementPanelListener();
		
		setBitAndBitPanelListener();
		
		setCountAndCountPanelListener();
		
		setHandAndHandPanelListener();
	}
	
	private void setElementAndElementPanelListener(){
		elements.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (elementsPanel.getVisibility()==LinearLayout.VISIBLE) {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
						elementsPanel.setVisibility(LinearLayout.GONE);
					}else {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_select));
						elementsPanel.setVisibility(LinearLayout.VISIBLE);
					}
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					setElementValue(Constants.Sample.WHITE,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					setElementValue(Constants.Sample.ENGLISH_LETTERS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					setElementValue(Constants.Sample.COLORS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FOUR).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
					setElementValue(Constants.Sample.NUMBERS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FIVE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_FIVE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_FIVE);
					setElementValue(Constants.Sample.ROME_NUMBERS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_SIX).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_SIX);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_SIX);
					setElementValue(Constants.Sample.SHAPES,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_SEVEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_SEVEN);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_SEVEN);
					setElementValue(Constants.Sample.COMMON_MARKS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_EIGHT).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_EIGHT);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_EIGHT);
					setElementValue(Constants.Sample.MUSIC_MARKS,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}				
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_NINE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_NINE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_NINE);
					setElementValue(Constants.Sample.FOREIGH_MUSIC,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}
				
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_TEN);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_TEN);
					setElementValue(Constants.Sample.FOLK_MUSIC,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+":"+elementTextTwo);
					setOkButtonEnable(true);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ELEVEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ELEVEN);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementTextTwo=((Button)v).getText().toString();
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ELEVEN);
					setElementValue(Constants.Sample.PERCUSSION_INSTRUMENT,elementTextTwo,elementsList,elementTextList);
					if (bs[3]) {
						elementTextTwo=listToString(elementTextList);
					}
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					setOkButtonEnable(true);
				}				
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.LayoutId.LAYOUT_ID_ELEVEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementsList.clear();
					elementTextList.clear();
					elementTextTwo="";
					elementsParameters=null;
					setOkButtonEnable(true);
					setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_ELEVEN,0);
					setElementPanelButtonEnabled(1);
					setButtonOnTouchUpBackground(elementsPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					elementTextOne=((TextView)v.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).getText().toString();
					elementTextTwo="白光";
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					elementsParameters=String.valueOf(Constants.Sample.WHITE);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.LayoutId.LAYOUT_ID_TWELVE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementsList.clear();
					elementTextList.clear();
					elementTextTwo="";
					elementsParameters=null;
					setOkButtonEnable(true);
					setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_TWELVE,1);
					setElementPanelButtonEnabled(1);
					((Button)elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
					((Button)elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
					((Button)elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
					((Button)elementsPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FOUR))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
					elementTextOne=((TextView)v.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).getText().toString();
					elementTextTwo="白光"+","+"英文字母"+","+"颜色"+","+"阿拉伯数字";
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
					elementsParameters=Constants.Sample.WHITE+","+Constants.Sample.ENGLISH_LETTERS+","+Constants.Sample.COLORS
					+","+Constants.Sample.NUMBERS;
				}
				return false;
			}
		});
		elementsPanel.findViewById(HearingConstants.LayoutId.LAYOUT_ID_THIRTEEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementsList.clear();
					elementTextList.clear();
					elementTextTwo="";
					elementsParameters=null;
					setOkButtonEnable(false);
					setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_THIRTEEN,2);
					setElementPanelButtonEnabled(0);
					setButtonOnTouchUpBackground(elementsPanel, 1);
					elementTextOne=((TextView)v.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).getText().toString();
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.LayoutId.LAYOUT_ID_FOURTEEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementsList.clear();
					elementTextList.clear();
					elementTextTwo="";
					elementsParameters=null;
					setOkButtonEnable(false);
					setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_FOURTEEN,3);
					setElementPanelButtonEnabled(0);
					setButtonOnTouchUpBackground(elementsPanel, 1);
					elementTextOne=((TextView)v.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).getText().toString();
					setDeputyTitle(elements, elementTextOne+getString(R.string.space_mark_2)+elementTextTwo);
				}
				return false;
			}
		});
		
		elementsPanel.findViewById(HearingConstants.LayoutId.LAYOUT_ID_FIFTEEN).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					elementsList.clear();
					elementTextList.clear();
					elementTextTwo="";
					elementsParameters=null;
					setOkButtonEnable(true);
					setCheckBoxState(elementsPanel, HearingConstants.LayoutId.LAYOUT_ID_FIFTEEN,4);
					setElementPanelButtonEnabled(1);
					setButtonOnTouchUpBackground(elementsPanel, 1);
					elementTextOne=((TextView)v.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).getText().toString();
					setDeputyTitle(elements, elementTextOne);
					elementsParameters=Constants.Sample.WHITE+","+Constants.Sample.ENGLISH_LETTERS+","+Constants.Sample.COLORS
					+","+Constants.Sample.NUMBERS+","+Constants.Sample.ROME_NUMBERS+","+Constants.Sample.SHAPES+","
					+Constants.Sample.COMMON_MARKS;
					////+","+Constants.Sample.MUSIC_MARKS+","+Constants.Sample.FOREIGH_MUSIC+","+Constants.Sample.FOLK_MUSIC
					////+","+Constants.Sample.PERCUSSION_INSTRUMENT;
				}
				return false;
			}
		});
	}
	
	private void setBitAndBitPanelListener(){
		bit.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (bitPanel.getVisibility()==LinearLayout.VISIBLE) {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
						bitPanel.setVisibility(LinearLayout.GONE);
					}else {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_select));
						bitPanel.setVisibility(LinearLayout.VISIBLE);
					}
				}
				return false;
			}
		});
		
		bitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					count.setEnabled(true);
					count.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
					setButtonOnTouchUpBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					setDeputyTitle(bit, bitTextOne);
					bitParameters=Constants.BitType.GIVEN_BIT;
				}
				return false;
			}
		});
		
		bitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					Log.d(TAG, "true");
					setButtonOnTouchDownBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					count.setEnabled(true);
					count.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
					setButtonOnTouchUpBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					bitTextOne=((Button)v).getText().toString();
					setDeputyTitle(bit, bitTextOne+":"+bitTextTwo);
					showChooseBitDialog(3, canSupportedMaxBit, -110, -50, SELF_CHOOSE_BIT);
					bitParameters=bitTextTwo;
				}
				return false;
			}
		});
		
		bitPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					Log.d(TAG, "true");
					setButtonOnTouchDownBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					count.setEnabled(false);
					count.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_lock));
					setButtonOnTouchUpBackground(bitPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					bitTextOne=((Button)v).getText().toString();
					setDeputyTitle(bit, bitTextOne+":"+bitTextTwo);
					showChooseBitDialog(3, canSupportedMaxBit, 130, -50, CONTINUED_BIT);
					bitParameters=Constants.BitType.CONTINUED_BIT;
					startBit=bitTextTwo;
				}
				return false;
			}
		});
	}
	
	private void setCountAndCountPanelListener(){
		count.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (countPanel.getVisibility()==LinearLayout.VISIBLE) {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
						countPanel.setVisibility(LinearLayout.GONE);
					}else {
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_select));
						countPanel.setVisibility(LinearLayout.VISIBLE);
					}
				}
				return false;
			}
		});
		
		countPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(countPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(countPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					setDeputyTitle(count, ((Button)v).getText().toString()+"题");
				}
				
				countParameters=15;
				return false;
			}
		});
		
		countPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(countPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(countPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					setDeputyTitle(count, ((Button)v).getText().toString()+"题");
				}
				
				countParameters=30;
				return false;
			}
		});
		
		countPanel.findViewById(HearingConstants.ViewId.VIEW_ID_THREE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(countPanel, HearingConstants.ViewId.VIEW_ID_THREE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(countPanel, HearingConstants.ViewId.VIEW_ID_THREE);
					setDeputyTitle(count, ((Button)v).getText().toString()+"题");
				}
				countParameters=45;
				return false;
			}
		});
		
		countPanel.findViewById(HearingConstants.ViewId.VIEW_ID_FOUR).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setButtonOnTouchDownBackground(countPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(countPanel, HearingConstants.ViewId.VIEW_ID_FOUR);
					setDeputyTitle(count, ((Button)v).getText().toString()+"题");
				}
				
				countParameters=60;
				return false;
			}
		});
	}
	
	private void setHandAndHandPanelListener(){
		hand.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					Log.d(TAG, "ACTION_UP");
					if (handPanel.getVisibility()==LinearLayout.VISIBLE) {
						Log.d(TAG, "GONE");
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
						handPanel.setVisibility(LinearLayout.GONE);
					}else {
						Log.d(TAG, "VISIBLE");
						v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_select));
						handPanel.setVisibility(LinearLayout.VISIBLE);
					}
				}
				return false;
			}
		});
		
		handPanel.findViewById(HearingConstants.ViewId.VIEW_ID_ONE).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					Log.d(TAG, "true");
					setButtonOnTouchDownBackground(handPanel, HearingConstants.ViewId.VIEW_ID_ONE);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(handPanel, HearingConstants.ViewId.VIEW_ID_ONE);
					setDeputyTitle(hand,((Button)v).getText().toString());
				}
				handParameters=Constants.RIGHT_HAND;
				return false;
			}
		});
		
		handPanel.findViewById(HearingConstants.ViewId.VIEW_ID_TWO).setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					Log.d(TAG, "true");
					setButtonOnTouchDownBackground(handPanel, HearingConstants.ViewId.VIEW_ID_TWO);
				}
				
				if (event.getAction()==MotionEvent.ACTION_UP) {
					setButtonOnTouchUpBackground(handPanel, HearingConstants.ViewId.VIEW_ID_TWO);
					setDeputyTitle(hand,((Button)v).getText().toString());
				}
				handParameters=Constants.LEFT_HAND;
				return false;
			}
		});
	}
	
	private void showChooseBitDialog(int from, int to, int x, int y, final String type){
		final List<TextView> textViews = new ArrayList<TextView>();
		final Dialog dialog = new Dialog(VtmtKeyStokeChooseParametersActivity.this, R.style.cs_choose_bit_dialog);
		
		HorizontalScrollView horizontalScrollView = new HorizontalScrollView(VtmtKeyStokeChooseParametersActivity.this);
		LinearLayout layout = new LinearLayout(getApplicationContext());
		horizontalScrollView.addView(layout);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				52, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
				20, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams2.gravity = Gravity.CENTER;
		layout.addView(new TextView(VtmtKeyStokeChooseParametersActivity.this), layoutParams2);
		
		for(int i=from; i<=to; i++){
			TextView textView = new TextView(VtmtKeyStokeChooseParametersActivity.this);
			textView.setText(i+"");
			
			if(type.equals(SELF_CHOOSE_BIT) && bitSelfChosen > 0){
				if(i == bitSelfChosen){
					textView.setTextSize(26);
					textView.setTextColor(Color.parseColor("#2d791f"));
					textView.setTypeface(null, Typeface.BOLD);
				}else {
					textView.setTextSize(23);
					textView.setTextColor(Color.parseColor("#515151"));	
					textView.setTypeface(null, Typeface.NORMAL);
				}
			}else if(type.equals(CONTINUED_BIT) && startBit > 0){
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
		
		layout.addView(new TextView(VtmtKeyStokeChooseParametersActivity.this), layoutParams2);
		for(final TextView textView1 : textViews){
			textView1.setOnClickListener(new TextView.OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(type.equals(SELF_CHOOSE_BIT)){
						bitSelfChosen = Integer.valueOf(textView1.getText().toString());
					}else if (type.equals(CONTINUED_BIT)) {
						startBit = Integer.valueOf(textView1.getText().toString());
					}
					
					for(TextView textView2 : textViews){
						if(textView1 == textView2){
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
					if (type==SELF_CHOOSE_BIT) {
						bitTextTwo=bitSelfChosen;
						setDeputyTitle(bit, bitTextOne+":"+bitTextTwo);
					}else if (type==CONTINUED_BIT) {
						bitTextTwo=startBit;
						setDeputyTitle(bit, bitTextOne+":"+bitTextTwo);
					}
				}				
			});
		}
		
		dialog.setContentView(horizontalScrollView, new HorizontalScrollView.LayoutParams(
				300, 55));
		//改变dialog显示的位置
//		Window window = dialog.getWindow();
//		WindowManager.LayoutParams wmlLayoutParams = window.getAttributes();
//		wmlLayoutParams.x = x;
//		wmlLayoutParams.y = y;
//		dialog.onWindowAttributesChanged(wmlLayoutParams);
		dialog.show();					
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
	
	private void choosedElements(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=10;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params2.leftMargin=20;
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		
		elements=new LinearLayout(this);
		elements.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		elements.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		elements.setGravity(Gravity.CENTER_VERTICAL);
		elements.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText("样本选择");
		title.setTextSize(24);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		title.setGravity(Gravity.CENTER_VERTICAL);
		
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(elementTextOne+elementTextTwo);
		deputyTitle.setTextSize(20);
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		deputyTitle.setTextColor(R.color.hearing_system_green);
		
		framework.addView(elements,params);
		elements.addView(title, params2);
		elements.addView(deputyTitle, params3);
	}
	
	private void choosedElementsPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_THREE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(80,80);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(80,80);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(80,80);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params5=new RelativeLayout.LayoutParams(80,80);
		params5.setMargins(20, 15, 20, 15);
		params5.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_THREE);
		
		RelativeLayout.LayoutParams params6=new RelativeLayout.LayoutParams(80,80);
		params6.setMargins(20, 15, 20, 15);
		params6.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_FOUR);
		
		RelativeLayout.LayoutParams params7=new RelativeLayout.LayoutParams(80,80);
		params7.setMargins(20, 15, 20, 15);
		params7.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_FIVE);
		
		RelativeLayout.LayoutParams params8=new RelativeLayout.LayoutParams(80,80);
		params8.setMargins(20, 15, 20, 15);
		params8.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		params8.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params9=new RelativeLayout.LayoutParams(80,80);
		params9.setMargins(20, 15, 20, 15);
		params9.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_TWO);
		params9.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_SEVEN);
		
		RelativeLayout.LayoutParams params10=new RelativeLayout.LayoutParams(80,80);
		params10.setMargins(20, 15, 20, 15);
		params10.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_THREE);
		params10.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_EIGHT);
		
		RelativeLayout.LayoutParams params11=new RelativeLayout.LayoutParams(80,80);
		params11.setMargins(20, 15, 20, 15);
		params11.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_FOUR);
		params11.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_NINE);
		
		RelativeLayout.LayoutParams params12=new RelativeLayout.LayoutParams(80,80);
		params12.setMargins(20, 15, 20, 15);
		params12.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_FIVE);
		params12.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TEN);
		
		RelativeLayout.LayoutParams params13=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params13.setMargins(20, 15, 20, 15);
		params13.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_SEVEN);
		
		RelativeLayout.LayoutParams params14=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params14.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_ELEVEN);
		
		RelativeLayout.LayoutParams params15=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params15.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params15.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params16=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params16.setMargins(20, 15, 20, 15);
		params16.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_NINE);
		params16.addRule(RelativeLayout.RIGHT_OF, HearingConstants.LayoutId.LAYOUT_ID_ELEVEN);
		
		RelativeLayout.LayoutParams params17=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params17.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_TWELVE);
		
		RelativeLayout.LayoutParams params18=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params18.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params18.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params19=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params19.setMargins(20, 15, 20, 15);
		params19.addRule(RelativeLayout.BELOW, HearingConstants.ViewId.VIEW_ID_ELEVEN);
		params19.addRule(RelativeLayout.RIGHT_OF, HearingConstants.LayoutId.LAYOUT_ID_TWELVE);
		
		RelativeLayout.LayoutParams params20=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params20.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_THIRTEEN);
		
		RelativeLayout.LayoutParams params21=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params21.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params21.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params22=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params22.setMargins(20, 15, 20, 15);
		params22.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_ELEVEN);
		params22.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		RelativeLayout.LayoutParams params23=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params23.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FOURTEEN);
		
		RelativeLayout.LayoutParams params24=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params24.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params24.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params25=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params25.setMargins(20, 15, 20, 15);
		params25.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_TWELVE);
		params25.addRule(RelativeLayout.RIGHT_OF, HearingConstants.LayoutId.LAYOUT_ID_FOURTEEN);
		
		RelativeLayout.LayoutParams params26=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params26.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_FIFTEEN);
		
		RelativeLayout.LayoutParams params27=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params27.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		params27.addRule(RelativeLayout.ALIGN_BOTTOM, HearingConstants.ViewId.VIEW_ID_ONE);
		
		elementsPanel=new RelativeLayout(this);
		elementsPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		elementsPanel.setVisibility(RelativeLayout.GONE);
		elementsPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText("白光");
		button.setTextSize(16);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		button.setClickable(true);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText("英文字母");
		button2.setTextSize(16);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		button2.setClickable(true);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText("颜色");
		button3.setTextSize(16);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		button3.setClickable(true);
		
		Button button4=new Button(this);
		button4.setId(HearingConstants.ViewId.VIEW_ID_FOUR);
		button4.setText("阿拉伯数字");
		button4.setTextSize(16);
		button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button4.setGravity(Gravity.CENTER);
		button4.setClickable(true);
		
		Button button5=new Button(this);
		button5.setId(HearingConstants.ViewId.VIEW_ID_FIVE);
		button5.setText("罗马数字");
		button5.setTextSize(16);
		button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button5.setGravity(Gravity.CENTER);
		button5.setClickable(true);
		
		Button button6=new Button(this);
		button6.setId(HearingConstants.ViewId.VIEW_ID_SIX);
		button6.setText("形状");
		button6.setTextSize(16);
		button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button6.setGravity(Gravity.CENTER);
		button6.setClickable(true);
		
		Button button7=new Button(this);
		button7.setId(HearingConstants.ViewId.VIEW_ID_SEVEN);
		button7.setText("一般记号");
		button7.setTextSize(16);
		button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button7.setGravity(Gravity.CENTER);
		button7.setClickable(true);
		
		Button button8=new Button(this);
		button8.setId(HearingConstants.ViewId.VIEW_ID_EIGHT);
		button8.setText("音乐记号");
		button8.setTextSize(16);
		button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button8.setGravity(Gravity.CENTER);
		button8.setClickable(true);
		
		Button button9=new Button(this);
		button9.setId(HearingConstants.ViewId.VIEW_ID_NINE);
		button9.setText("西洋乐");
		button9.setTextSize(16);
		button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button9.setGravity(Gravity.CENTER);
		button9.setClickable(true);
		
		Button button10=new Button(this);
		button10.setId(HearingConstants.ViewId.VIEW_ID_TEN);
		button10.setText("民族乐");
		button10.setTextSize(16);
		button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button10.setGravity(Gravity.CENTER);
		button10.setClickable(true);
		
		Button button11=new Button(this);
		button11.setId(HearingConstants.ViewId.VIEW_ID_ELEVEN);
		button11.setText("打击乐");
		button11.setTextSize(16);
		button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button11.setGravity(Gravity.CENTER);
		button11.setClickable(true);
		
		RelativeLayout relativeLayout=new RelativeLayout(this);
		relativeLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ELEVEN);
		relativeLayout.setClickable(true);
		
		ImageView imageView=new ImageView(this);
		imageView.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		imageView.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
				
		TextView textView=new TextView(this);
		textView.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		textView.setText("设定单一样本");
		textView.setTextSize(24);
		textView.setGravity(Gravity.CENTER);
		
		RelativeLayout relativeLayout2=new RelativeLayout(this);
		relativeLayout2.setId(HearingConstants.LayoutId.LAYOUT_ID_TWELVE);
		relativeLayout2.setClickable(true);
		
		ImageView imageView2=new ImageView(this);
		imageView2.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		imageView2.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
				
		TextView textView2=new TextView(this);
		textView2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		textView2.setText("设定多种样本");
		textView2.setTextSize(24);
		textView2.setGravity(Gravity.CENTER);
		
		RelativeLayout relativeLayout3=new RelativeLayout(this);
		relativeLayout3.setId(HearingConstants.LayoutId.LAYOUT_ID_THIRTEEN);
		relativeLayout3.setClickable(true);
		
		ImageView imageView3=new ImageView(this);
		imageView3.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		imageView3.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
				
		TextView textView3=new TextView(this);
		textView3.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		textView3.setText("自选单一样本");
		textView3.setTextSize(24);
		textView3.setGravity(Gravity.CENTER);
		
		RelativeLayout relativeLayout4=new RelativeLayout(this);
		relativeLayout4.setId(HearingConstants.LayoutId.LAYOUT_ID_FOURTEEN);
		relativeLayout4.setClickable(true);
		
		ImageView imageView4=new ImageView(this);
		imageView4.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		imageView4.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
				
		TextView textView4=new TextView(this);
		textView4.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		textView4.setText("自选多种样本");
		textView4.setTextSize(24);
		textView4.setGravity(Gravity.CENTER);
		
		RelativeLayout relativeLayout5=new RelativeLayout(this);
		relativeLayout5.setId(HearingConstants.LayoutId.LAYOUT_ID_FIFTEEN);
		relativeLayout5.setClickable(true);
		
		ImageView imageView5=new ImageView(this);
		imageView5.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		imageView5.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
				
		TextView textView5=new TextView(this);
		textView5.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		textView5.setText("随机样本");
		textView5.setTextSize(24);
		textView5.setGravity(Gravity.CENTER);
		
		framework.addView(elementsPanel,params);
		elementsPanel.addView(button,params2);
		elementsPanel.addView(button2,params3);
		elementsPanel.addView(button3,params4);
		elementsPanel.addView(button4,params5);
		elementsPanel.addView(button5,params6);
		elementsPanel.addView(button6,params7);
		elementsPanel.addView(button7,params8);
		elementsPanel.addView(button8,params9);
		elementsPanel.addView(button9,params10);
		elementsPanel.addView(button10,params11);
		elementsPanel.addView(button11,params12);
		elementsPanel.addView(relativeLayout,params13);
		relativeLayout.addView(imageView, params14);
		relativeLayout.addView(textView, params15);
		elementsPanel.addView(relativeLayout2,params16);
		relativeLayout2.addView(imageView2, params17);
		relativeLayout2.addView(textView2, params18);
		elementsPanel.addView(relativeLayout3,params19);
		relativeLayout3.addView(imageView3, params20);
		relativeLayout3.addView(textView3, params21);
		elementsPanel.addView(relativeLayout4,params22);
		relativeLayout4.addView(imageView4, params23);
		relativeLayout4.addView(textView4, params24);
		elementsPanel.addView(relativeLayout5,params25);
		relativeLayout5.addView(imageView5, params26);
		relativeLayout5.addView(textView5, params27);
	}
	
	private void choosedBit(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params2.leftMargin=20;
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		
		bit=new LinearLayout(this);
		bit.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		bit.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		bit.setGravity(Gravity.CENTER_VERTICAL);
		bit.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText("显示位元");
		title.setTextSize(24);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(bitTextOne);
		deputyTitle.setTextSize(20);
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		deputyTitle.setTextColor(R.color.hearing_system_green);
		
		framework.addView(bit,params);
		bit.addView(title, params2);
		bit.addView(deputyTitle, params3);
	}
	
	private void choosedBitPanel(){
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
		
		bitPanel=new RelativeLayout(this);
		bitPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		bitPanel.setVisibility(RelativeLayout.GONE);
		bitPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText("设定位元数");
		button.setTextSize(16);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText("自选位元数");
		button2.setTextSize(16);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText("连续位元数");
		button3.setTextSize(16);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		
		framework.addView(bitPanel,params);
		bitPanel.addView(button, params2);
		bitPanel.addView(button2, params3);
		bitPanel.addView(button3, params4);
	}
	
	private void choosedCount(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_SIX);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params2.leftMargin=20;
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		
		count=new LinearLayout(this);
		count.setId(HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		count.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		count.setGravity(Gravity.CENTER_VERTICAL);
		count.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText("题目数");
		title.setTextSize(24);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(String.valueOf(countText)+"题");
		deputyTitle.setTextSize(20);
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		deputyTitle.setTextColor(R.color.hearing_system_green);
		
		framework.addView(count,params);
		count.addView(title, params2);
		count.addView(deputyTitle, params3);
	}	
	
	private void choosedCountPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_SEVEN);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(80,80);
		params2.setMargins(20, 15, 20, 15);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(80,80);
		params3.setMargins(20, 15, 20, 15);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(80,80);
		params4.setMargins(20, 15, 20, 15);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params5=new RelativeLayout.LayoutParams(80,80);
		params5.setMargins(20, 15, 20, 15);
		params5.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_THREE);
		
		countPanel=new RelativeLayout(this);
		countPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		countPanel.setVisibility(RelativeLayout.GONE);
		countPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText("15");
		button.setTextSize(16);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText("30");
		button2.setTextSize(16);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		
		Button button3=new Button(this);
		button3.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		button3.setText("45");
		button3.setTextSize(16);
		button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button3.setGravity(Gravity.CENTER);
		
		Button button4=new Button(this);
		button4.setId(HearingConstants.ViewId.VIEW_ID_FOUR);
		button4.setText("60");
		button4.setTextSize(16);
		button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button4.setGravity(Gravity.CENTER);
		
		framework.addView(countPanel,params);
		countPanel.addView(button, params2);
		countPanel.addView(button2, params3);
		countPanel.addView(button3, params4);
		countPanel.addView(button4, params5);
	}
	
	private void choosedHand(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=50;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_EIGHT);
		
		LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(220,LinearLayout.LayoutParams.WRAP_CONTENT);
		params2.leftMargin=20;
		
		LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(585,LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.leftMargin=5;
		
		hand=new LinearLayout(this);
		hand.setId(HearingConstants.LayoutId.LAYOUT_ID_NINE);
		hand.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_params_btn_normal));
		hand.setGravity(Gravity.CENTER_VERTICAL);
		hand.setClickable(true);
		
		TextView title=new TextView(this);
		title.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		title.setText("优势手");
		title.setTextSize(24);
		title.setTextColor(getResources().getColor(R.color.hearing_system_gray));
		title.setTypeface(null,Typeface.BOLD);
		
		
		TextView deputyTitle=new TextView(this);
		deputyTitle.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		deputyTitle.setText(handText);
		deputyTitle.setTextSize(20);
		deputyTitle.setTypeface(null,Typeface.BOLD);
		deputyTitle.setGravity(Gravity.CENTER);
		deputyTitle.setTextColor(R.color.hearing_system_green);
		
		framework.addView(hand,params);
		hand.addView(title, params2);
		hand.addView(deputyTitle, params3);
	}
	
	private void choosedHandPanel(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(820,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin=-6;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_TWO);
		params.addRule(RelativeLayout.BELOW, HearingConstants.LayoutId.LAYOUT_ID_NINE);
		
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(80,80);
		params2.setMargins(20, 20, 20, 20);
		params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, HearingConstants.LayoutId.LAYOUT_ID_TEN);
		
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(80,80);
		params3.setMargins(20, 20, 20, 20);
		params3.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_ONE);
		
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(80,80);
		params4.setMargins(20, 20, 20, 20);
		params4.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_TWO);
		
		RelativeLayout.LayoutParams params5=new RelativeLayout.LayoutParams(80,80);
		params5.setMargins(20, 20, 20, 20);
		params5.addRule(RelativeLayout.RIGHT_OF, HearingConstants.ViewId.VIEW_ID_THREE);
		
		handPanel=new RelativeLayout(this);
		handPanel.setId(HearingConstants.LayoutId.LAYOUT_ID_TEN);
		handPanel.setVisibility(RelativeLayout.GONE);
		handPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.ui_param_panel_content_backgd));
		
		Button button=new Button(this);
		button.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		button.setText("左手");
		button.setTextSize(16);
		button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button.setGravity(Gravity.CENTER);
		
		Button button2=new Button(this);
		button2.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		button2.setText("右手");
		button2.setTextSize(16);
		button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
		button2.setGravity(Gravity.CENTER);
		
		framework.addView(handPanel,params);
		handPanel.addView(button, params2);
		handPanel.addView(button2, params3);
	}
	
	private void setElementPanelButtonEnabled(int id){
		int[] temp=HearingConstants.ViewId.VIEW_ARRAY;
		switch (id) {
		case 0:
			for (int i = 0; i < temp.length; i++) {
				if (elementsPanel.findViewById(temp[i])!=null) {
					((Button)(elementsPanel.findViewById(temp[i]))).setEnabled(true);
				}
			}
			break;
		case 1:
			for (int i = 0; i < temp.length; i++) {
				if (elementsPanel.findViewById(temp[i])!=null) {
					((Button)(elementsPanel.findViewById(temp[i]))).setEnabled(false);
				}
			}
			break;
		default:
			for (int i = 0; i < temp.length; i++) {
				if (id==temp[i]) {
					if (elementsPanel.findViewById(temp[i])!=null) {
						((Button)(elementsPanel.findViewById(temp[i]))).setEnabled(true);
					}
				}else {
					if (elementsPanel.findViewById(temp[i])!=null) {
						((Button)(elementsPanel.findViewById(temp[i]))).setEnabled(false);
					}
				}
			}
			break;
		}
	}
	
	private void setCheckBoxState(View view,int id, int id2){
		int[] temp=HearingConstants.LayoutId.LAYOUT_ARRAY;
		for (int i = 10; i < temp.length; i++) {
			if (view!=null&&id!=0) {
				if (id==temp[i]) {
					if (view.findViewById(temp[i])!=null) {
						((ImageView)view.findViewById(temp[i]).findViewById(HearingConstants.ViewId.VIEW_ID_ONE))
						.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_2));
					}
				}else {
					if (view.findViewById(temp[i])!=null) {
						((ImageView)view.findViewById(temp[i]).findViewById(HearingConstants.ViewId.VIEW_ID_ONE))
						.setImageDrawable(getResources().getDrawable(R.drawable.cs_choose_params_checkbox_1));
					}
				}
			}
		}
		
		for (int i = 0; i < bs.length; i++) {
			if (i==id2) {
				bs[i]=true;
			}else {
				bs[i]=false;
			}
		}
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
						if (bs[3]) {
							continue;
						}
						((Button)(view.findViewById(temp[i])))
						.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
					}
				}
			}
		}
	}
	
	private void setButtonOnTouchUpBackground(View view,int id){
		int[] temp=HearingConstants.ViewId.VIEW_ARRAY;
		switch (id) {
		case 0:
			for (int i = 0; i < temp.length; i++) {
				if (view.findViewById(temp[i])!=null) {
					((Button)(view.findViewById(temp[i])))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
				}
			}
			break;
		case 1:
			for (int i = 0; i < temp.length; i++) {
				if (view.findViewById(temp[i])!=null) {
					((Button)(view.findViewById(temp[i])))
					.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
				}
			}
			break;
		default:
			for (int i = 0; i < temp.length; i++) {
				if (view!=null) {
					if (id==temp[i]) {
						if (view.findViewById(temp[i])!=null) {
							((Button)(view.findViewById(temp[i])))
							.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_3));
						}
					}else {
						if (view.findViewById(temp[i])!=null) {
							if (bs[3]) {
								continue;
							}
							((Button)(view.findViewById(temp[i])))
							.setBackgroundDrawable(getResources().getDrawable(R.drawable.cs_choose_params_btn1_1));
						}
					}
				}
			}
			break;
		}
	}
	
	private void setDeputyTitle(View view,String text){
		((TextView)view.findViewById(HearingConstants.ViewId.VIEW_ID_TWO)).setText(text);
	}
	
	private void setElementValue(int params,String text,List<String> list,List<String> list2){
		if (bs[3]) {
			checkList(params,text,list,list2);
		}else {
			elementsParameters=String.valueOf(params);
		}
	}

	private String listToString(List<String> list){
		String tempString=list.toString();
		String[] tempStrings=tempString.substring(1, tempString.length()-1).split(",");
		StringBuffer tempBuffer=new StringBuffer();
		for (int i = 0; i < tempStrings.length; i++) {
			tempBuffer.append(tempStrings[i].trim()+",");
		}
		return tempBuffer.toString();
	}
	
	private void checkList(int params,String text,List<String> list,List<String> list2){
		if (list.size()<=0&&list2.size()<=0) {
			list.add(String.valueOf(params));
			list2.add(text);
		}else {
			if(list.contains(String.valueOf(params))&&list2.contains(text)){
				Toast.makeText(getApplicationContext(), "你已经选择过这个乐器，请选择其他乐器", Toast.LENGTH_SHORT).show();
			}else {
				list.add(String.valueOf(params));
				list2.add(text);
			}
		}
	}

}
	