package com.flextronics.cn.activity.color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.ImageViewBtnGroup;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.LinearLayoutCheckBox;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.Constants;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ColorMemoryTrainingChooseParamsActivity extends BaseActivity {
	
	/**
	 * 点状
	 */
	private final static String POINT = "POINT";
	/**
	 * 直线
	 */
	private final static String LINE = "LINE";
	/**
	 * 曲线
	 */
	private final static String CURVE = "CURVE";
	/**
	 * 形状
	 */
	private final static String SHAPE = "SHAPE";
	/**
	 * 图样
	 */
	private final static String TU_YANG = "TU_YANG";
	/**
	 * 自选形状
	 */
	private final static String ZI_XUAN_XING_ZHUANG = "ZI_XUAN_XING_ZHUANG";
	/**
	 * 随机形状
	 */
	private final static String SUI_JI_XING_ZHUANG = "SUI_JI_XING_ZHUANG";
	
	/**
	 * 设定位元
	 */
	private final static String SETTING_BIT = "SETTING_BIT";
	/**
	 * 自选位元
	 */
	private final static String SELF_CHOOSE_BIT = "SELF_CHOOSE_BIT";
	/**
	 * 连续位元
	 */
	private final static String CONTINUED_BIT = "CONTINUED_BIT";
	
	/**
	 * 显示后保留
	 */
	private final static String SHOW_HOLD = "SHOW_HOLD";
	/**
	 * 显示后消失
	 */
	private final static String SHOW_DISAPPEAR = "SHOW_DISAPPEAR";
	/**
	 * 同步显示同时消失
	 */
	private final static String SHOW_DISAPPEAR_TOGETHER = "SHOW_DISAPPEAR_TOGETHER";
	
	//9种具体的形状
	private final static String XING_ZHUANG_CIRCLE = "XING_ZHUANG_CIRCLE";
	private final static String XING_ZHUANG_TRIGON = "XING_ZHUANG_TRIGON";
	private final static String XING_ZHUANG_SQUARE = "XING_ZHUANG_SQUARE";
	private final static String XING_ZHUANG_HEXAGON = "XING_ZHUANG_HEXAGON";
	private final static String XING_ZHUANG_RECTANGLE = "XING_ZHUANG_RECTANGLE";
	private final static String XING_ZHUANG_SECTOR = "XING_ZHUANG_SECTOR";
	private final static String XING_ZHUANG_PARALLELOGRAM = "XING_ZHUANG_PARALLELOGRAM";
	private final static String XING_ZHUANG_DIAMOND = "XING_ZHUANG_DIAMOND";
	private final static String XING_ZHUANG_PENTAGON = "XING_ZHUANG_PENTAGON";
	//11种具体的图样
	private final static String TU_YANG_LINE = "TU_YANG_LINE";
	private final static String TU_YANG_CURVE = "TU_YANG_CURVE";
	private final static String TU_YANG_CIRCLE = "TU_YANG_CIRCLE";
	private final static String TU_YANG_TRIGON = "TU_YANG_TRIGON";
	private final static String TU_YANG_SQUARE = "TU_YANG_SQUARE";
	private final static String TU_YANG_HEXAGON = "TU_YANG_HEXAGON";
	private final static String TU_YANG_RECTANGLE = "TU_YANG_RECTANGLE";
	private final static String TU_YANG_SECTOR = "TU_YANG_SECTOR";
	private final static String TU_YANG_PARALLELOGRAM = "TU_YANG_PARALLELOGRAM";
	private final static String TU_YANG_DIAMOND = "TU_YANG_DIAMOND";
	private final static String TU_YANG_PENTAGON = "TU_YANG_PENTAGON";	
	
	/**
	 * 显示形体图像按钮组
	 */
	private ImageViewBtnGroup displayBodyImageViewBtnGroup;
	/**
	 * 显示位元图像按钮组
	 */
	private ImageViewBtnGroup bitTypeImageViewBtnGroup;
	/**
	 * 显示方式图像按钮组
	 */
	private ImageViewBtnGroup displayModeImageViewBtnGroup;
	/**
	 * 问题数图像按钮组
	 */
	private ImageViewBtnGroup questionCountImageViewBtnGroup;
	
	/**
	 * 自选图形/随机图像选择按钮组
	 */
	private LinearLayoutBtnGroup chooseShapeLinearLayoutBtnGroup;
	private LinearLayoutBtnGroup chooseTuYangLinearLayoutBtnGroup;
	/**
	 * 显示形体参数面板
	 */
	private ParamPanel displayBodyParamPanel;
	/**
	 * 显示位元参数面板
	 */
	private ParamPanel bitTypeParamPanel;
	/**
	 * 显示方式参数面板
	 */
	private ParamPanel displayModeParamPanel;
	/**
	 * 问题数参数面板
	 */
	private ParamPanel questionCountParamPanel;
	/**
	 * 点击形状按钮后弹出的子面板
	 */
	private ParamPanel shapesParamPanel;
	/**
	 * 点击自选形状弹出的子面板
	 */
	private ParamPanel selfChooseShapesParamPanel;
	/**
	 * 点击图样按钮后弹出的子面板
	 */
	private ParamPanel tuYangParamPanel;
	private ParamPanel[] paramPanels;
	
	private LinearLayout xingZhuangLinearLayout;
	private LinearLayoutCheckBox orderByIndexCheckBox;
	
	private Map<String, String> uiTextMap;
	private Map<String, Integer> uiParamValueMap;
	
	/**
	 * 用户自选的位元数
	 */
	private int bitSelfChosen = 3;
	/**
	 * 开始位元
	 */
	private int startBit = 3;
	private int canSupportedMaxBit = 36;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.color_memory_training_choose_parameter中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.color_memory_training_choose_parameter, null), getBaseLayoutParams());
		setContentView(layout);
		
		setupHashMaps();
		setupViews();
		setupListeners();
		
		setupTexts();
	}
	
	private void setupViews(){
		
		//设置训练标题
		setTrainingTitle(getString(R.string.color_memory_training));
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像 
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		//显示后退按钮
		setBackButtonEnable(true);
		//显示主页按钮
		setHomeButtonEnable(true);

		displayBodyParamPanel = (ParamPanel)findViewById(R.id.ParamPanel01);
		bitTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel02);
		displayModeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel03);
		questionCountParamPanel = (ParamPanel)findViewById(R.id.ParamPanel04);
		shapesParamPanel = (ParamPanel)findViewById(R.id.ParamPanel05);
		selfChooseShapesParamPanel = (ParamPanel)findViewById(R.id.ParamPanelShapeContent);
		tuYangParamPanel = (ParamPanel)findViewById(R.id.ParamPanel06);
		
		displayBodyImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup01);
		bitTypeImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup02);
		displayModeImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup03);
		questionCountImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup04);
		
		chooseShapeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup_Shape);
		chooseTuYangLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup05);
		xingZhuangLinearLayout = (LinearLayout)findViewById(R.id.LinearLayoutXingZhuang);
		
		orderByIndexCheckBox = (LinearLayoutCheckBox)findViewById(R.id.LinearLayoutCheckBox10);
		
		paramPanels = new ParamPanel[4];
		paramPanels[0] = displayBodyParamPanel;
		paramPanels[1] = bitTypeParamPanel;
		paramPanels[2] = displayModeParamPanel;
		paramPanels[3] = questionCountParamPanel;
		
		shapesParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		selfChooseShapesParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		tuYangParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		
		enableStartBtn();
	}
	
	private void setupListeners(){
		
		/*for (int i = 0; i < paramPanels.length; i++) {
			final ParamPanel paramPanel = paramPanels[i];
			paramPanel.setOnToggleListener(new ParamPanel.OnToggleListener() {				
				@Override
				public void OnToggle(boolean open) {
					// TODO Auto-generated method stub
					for (int j = 0; j < paramPanels.length; j++) {							
						if(paramPanels[j] != paramPanel){
							if(open){
								paramPanels[j].lock();
							}else {
								paramPanels[j].unlock();
							}
						}
					}
				}
			});			
		}*/
		
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
					startActivity(new Intent(ColorMemoryTrainingChooseParamsActivity.this, ColorTrainingChooseActivity.class));
					finish();
				}
				return false;
			}
		});
		
		//选中具体显示形体后将结果显示在右上角
		displayBodyImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupDisplayBodyResultText();
				if(displayBodyImageViewBtnGroup.getValue() != null){
					if(displayBodyImageViewBtnGroup.getValue().trim().equals(SHAPE)){
						if(chooseShapeLinearLayoutBtnGroup.getValue().equals(ZI_XUAN_XING_ZHUANG)){
							selfChooseShapesParamPanel.open();
						}
						shapesParamPanel.toggle(ParamPanel.CONTENT);
						tuYangParamPanel.close();
						canSupportedMaxBit = 36;
					}else if(displayBodyImageViewBtnGroup.getValue().trim().equals(TU_YANG)){
						tuYangParamPanel.toggle(ParamPanel.CONTENT);
						shapesParamPanel.close();
						canSupportedMaxBit = getCanSupportedMaxBit(chooseTuYangLinearLayoutBtnGroup.getValue());
						if(startBit > canSupportedMaxBit){
							startBit = canSupportedMaxBit;
						}
						if(bitSelfChosen > canSupportedMaxBit){
							bitSelfChosen = canSupportedMaxBit;
						}						
					}else {						
						shapesParamPanel.close();
						tuYangParamPanel.close();
						canSupportedMaxBit = 36;
					}
					setupBitTypeResultText();
				}
				enableStartBtn();
			}
		});
		
		//选中具体的显示位元后的处理动作
		bitTypeImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				//当显示位元是“连续位元”时，题目数是不能选择的				
				if(position == 2){//连续位元，选择题目的参数面板不需要显示出来
					if(questionCountParamPanel.getVisibility() != View.GONE){
						questionCountParamPanel.setVisibility(View.GONE);
					}
					showChooseBitDialog(3, canSupportedMaxBit, 130, -50, CONTINUED_BIT);
				}else{//非连续位元，选择题目的参数面板需要显示出来
					if(questionCountParamPanel.getVisibility() != View.VISIBLE){
						questionCountParamPanel.setVisibility(View.VISIBLE);
					}
					if (position == 1) {
						showChooseBitDialog(3, canSupportedMaxBit, -110, -50, SELF_CHOOSE_BIT);
					}
				}
				setupBitTypeResultText();
				enableStartBtn();
			}
		});
		
		//选中具体的显示模式后将结果显示在右上角
		displayModeImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupDisplayModeResultText();
				enableStartBtn();
			}
		});
		
		//选中具体的题目数后将结果显示在右上角
		questionCountImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupQuestionCountResultText();
				enableStartBtn();
			}
		});
				
		chooseShapeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				if(position == 0){
					selfChooseShapesParamPanel.open();
				}else{
					selfChooseShapesParamPanel.close();					
				}
				setupDisplayBodyResultText();
				enableStartBtn();
			}
		});
		
		for (int i = 0; i < xingZhuangLinearLayout.getChildCount(); i++) {
			if (xingZhuangLinearLayout.getChildAt(i) instanceof LinearLayoutCheckBox) {
				LinearLayoutCheckBox layoutCheckBox = (LinearLayoutCheckBox)xingZhuangLinearLayout.getChildAt(i);
				layoutCheckBox.setOnToggleListener(new LinearLayoutCheckBox.OnToggleListener() {
					
					@Override
					public void OnToggle(boolean isChecked) {
						// TODO Auto-generated method stub
						setupDisplayBodyResultText();
						enableStartBtn();
					}
				});
			}
		}
		
		chooseTuYangLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupDisplayBodyResultText();
				//如果之前选择的开始位元或者自选位元超过该图样支持的最大位元，
				//则将其改为该图样能支持的最大位元值
				canSupportedMaxBit = getCanSupportedMaxBit(chooseTuYangLinearLayoutBtnGroup.getValue());
				if(startBit > canSupportedMaxBit){
					startBit = canSupportedMaxBit;
				}
				if(bitSelfChosen > canSupportedMaxBit){
					bitSelfChosen = canSupportedMaxBit;
				}
				setupBitTypeResultText();
				enableStartBtn();
			}
		});
		
		orderByIndexCheckBox.setOnToggleListener(new LinearLayoutCheckBox.OnToggleListener() {
			
			@Override
			public void OnToggle(boolean isChecked) {
				// TODO Auto-generated method stub
				setupDisplayBodyResultText();
				enableStartBtn();
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					String displayBody = "";
					if(displayBodyImageViewBtnGroup.getValue().equals(SHAPE)){
						if(chooseShapeLinearLayoutBtnGroup.getValue().equals(ZI_XUAN_XING_ZHUANG)){
							for (int i = 0; i < xingZhuangLinearLayout.getChildCount(); i++) {
								if (xingZhuangLinearLayout.getChildAt(i) instanceof LinearLayoutCheckBox) {
									LinearLayoutCheckBox layoutCheckBox = (LinearLayoutCheckBox)xingZhuangLinearLayout.getChildAt(i);
									if(layoutCheckBox.isChecked()){
										displayBody += uiParamValueMap.get(layoutCheckBox.getValue())+",";
									}
								}
							}
							displayBody = displayBody.endsWith(",")?
									displayBody.substring(0, displayBody.length()-1):displayBody;
						}else if(chooseShapeLinearLayoutBtnGroup.getValue().equals(SUI_JI_XING_ZHUANG)){
							displayBody = Constants.Shapes.CIRCLE+","+Constants.Shapes.DIAMOND+","+Constants.Shapes.HEXAGON+","+
							Constants.Shapes.PARALLELOGRAM+","+Constants.Shapes.PENTAGON+","+Constants.Shapes.RECTANGLE+","+
							Constants.Shapes.SECTOR+","+Constants.Shapes.SQUARE+","+Constants.Shapes.TRIGON;
						}
					}else if (displayBodyImageViewBtnGroup.getValue().equals(TU_YANG)) {
						displayBody = uiParamValueMap.get(chooseTuYangLinearLayoutBtnGroup.getValue())+"";
					}else {
						displayBody = uiParamValueMap.get(displayBodyImageViewBtnGroup.getValue())+"";
					}
					
					Intent intent = new Intent(ColorMemoryTrainingChooseParamsActivity.this, ColorMemoryTrainingActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString(Constants.ColorMemoryTrainingUIParameter.DISPLAY_BODY, displayBody);
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.BIT_TYPE, 
							bitTypeImageViewBtnGroup.getValue().equals(SELF_CHOOSE_BIT)?
									bitSelfChosen:uiParamValueMap.get(bitTypeImageViewBtnGroup.getValue()));
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.DISPLAY_MODE, 
							uiParamValueMap.get(displayModeImageViewBtnGroup.getValue()));
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.QUESTION_COUNT,
							Integer.valueOf(questionCountImageViewBtnGroup.getValue()));					
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.DISPLAY_ORDER, 
							orderByIndexCheckBox.isChecked()?Constants.DisplayOrder.ORDER_BY_INDEX:
								Constants.DisplayOrder.NOT_ORDER_BY_INDEX);
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.START_BIT, startBit);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
	}
	
	/**
	 * 显示“显示形体”参数值
	 */
	private void setupDisplayBodyResultText(){
		String value = displayBodyImageViewBtnGroup.getValue();
		if(value != null){
			value = value.trim();
			//如果是形状
			if(value.equals(SHAPE)){
				String text = "";
				text += uiTextMap.get(SHAPE) + getString(R.string.space_mark_2) + 
					uiTextMap.get(chooseShapeLinearLayoutBtnGroup.getValue());
				if(chooseShapeLinearLayoutBtnGroup.getValue().equals(ZI_XUAN_XING_ZHUANG)){
					boolean flag = true;
					for (int i = 0; i < xingZhuangLinearLayout.getChildCount(); i++) {
						if (xingZhuangLinearLayout.getChildAt(i) instanceof LinearLayoutCheckBox) {
							LinearLayoutCheckBox layoutCheckBox = (LinearLayoutCheckBox)xingZhuangLinearLayout.getChildAt(i);
							if(layoutCheckBox.isChecked()){
								if(flag){
									text += getString(R.string.space_mark_2);
								}
								flag = false;
								text += uiTextMap.get(layoutCheckBox.getValue()) + getString(R.string.space_mark_3);								
							}
						}
					}
					if (text.endsWith(getString(R.string.space_mark_3))) {
						text = text.substring(0, text.length()-1);
					}
				}
				displayBodyParamPanel.setResultText(text);
			}else if (value.equals(TU_YANG)) {
				
				//如果是图样
				String text = "";
				text += uiTextMap.get(TU_YANG) + getString(R.string.space_mark_2) + 
					uiTextMap.get(chooseTuYangLinearLayoutBtnGroup.getValue()) + getString(R.string.space_mark_2);				
				if(orderByIndexCheckBox.isChecked()){
					text += getString(R.string.is_order_by_index);
				}else {
					text += getString(R.string.is_not_order_by_index);
				}
				displayBodyParamPanel.setResultText(text);
			}else {
				displayBodyParamPanel.setResultText(uiTextMap.get(value));
			}
		}		
	}
	
	/**
	 * 显示“位元类型”参数值
	 */
	private void setupBitTypeResultText(){
		String value = bitTypeImageViewBtnGroup.getValue();
		if(value != null){
			value = value.trim();
			if(value.equals(SETTING_BIT)){
				bitTypeParamPanel.setResultText(uiTextMap.get(SETTING_BIT));				
			}else if(value.equals(SELF_CHOOSE_BIT)){
				//形如： 自选位元数: 8
				bitTypeParamPanel.setResultText(uiTextMap.get(SELF_CHOOSE_BIT) + 
						getString(R.string.space_mark_1) + bitSelfChosen);
			}else if(value.equals(CONTINUED_BIT)){
				//形如： 连续位元数|开始位元：2
				bitTypeParamPanel.setResultText(uiTextMap.get(CONTINUED_BIT) + 
						getString(R.string.space_mark_2) + getString(R.string.start_bit) + 
						getString(R.string.space_mark_1) + startBit);
			}
		}
	}
	
	/**
	 * 显示“显示方式”参数值
	 */
	private void setupDisplayModeResultText(){
		displayModeParamPanel.setResultText(uiTextMap.get(displayModeImageViewBtnGroup.getValue()));
	}
		
	/**
	 * 显示“题目数”参数值
	 */
	private void setupQuestionCountResultText(){
		questionCountParamPanel.setResultText(questionCountImageViewBtnGroup.getValue() + 
				getString(R.string.question_count_unit));
	}
	
	/**
	 * 显示各参数面板的参数值
	 */
	private void setupTexts(){
		setupDisplayBodyResultText();
		setupBitTypeResultText();
		setupDisplayModeResultText();
		setupQuestionCountResultText();
	}
	
	private void showChooseBitDialog(int from, int to, int x, int y, final String type){
		final List<TextView> textViews = new ArrayList<TextView>();
		final Dialog dialog = new Dialog(ColorMemoryTrainingChooseParamsActivity.this, R.style.cs_choose_bit_dialog);
		
		HorizontalScrollView horizontalScrollView = new HorizontalScrollView(ColorMemoryTrainingChooseParamsActivity.this);
		LinearLayout layout = new LinearLayout(getApplicationContext());
		horizontalScrollView.addView(layout);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				52, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
				20, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams2.gravity = Gravity.CENTER;
		layout.addView(new TextView(ColorMemoryTrainingChooseParamsActivity.this), layoutParams2);
		
		for(int i=from; i<=to; i++){
			TextView textView = new TextView(ColorMemoryTrainingChooseParamsActivity.this);
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
		
		layout.addView(new TextView(ColorMemoryTrainingChooseParamsActivity.this), layoutParams2);
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
					setupBitTypeResultText();
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
	
	/**
	 * 各种图样支持的最大位元
	 * @param tuYang
	 * @return
	 */
	private int getCanSupportedMaxBit(String tuYang){
		
		int maxBit = 3;
		if(tuYang.equals(TU_YANG_LINE)){
			maxBit = 24;
		}else if(tuYang.equals(TU_YANG_CURVE)){
			maxBit = 24;
		}else if(tuYang.equals(TU_YANG_CIRCLE)){
			maxBit = 16;
		}else if(tuYang.equals(TU_YANG_TRIGON)){
			maxBit = 28;
		}else if(tuYang.equals(TU_YANG_SQUARE)){
			maxBit = 30;
		}else if(tuYang.equals(TU_YANG_HEXAGON)){
			maxBit = 32;	
		}else if(tuYang.equals(TU_YANG_RECTANGLE)){
			maxBit = 30;	
		}else if(tuYang.equals(TU_YANG_SECTOR)){
			maxBit = 30;	
		}else if(tuYang.equals(TU_YANG_PARALLELOGRAM)){
			maxBit = 30;
		}else if(tuYang.equals(TU_YANG_DIAMOND)){
			maxBit = 32;
		}else if(tuYang.equals(TU_YANG_PENTAGON)){
			maxBit = 30;
		}
		return maxBit;
	}
	
	/**
	 * 设置hash map的值
	 */
	private void setupHashMaps(){
		
		uiTextMap = new HashMap<String, String>();
		uiTextMap.put(POINT, getString(R.string.point));
		uiTextMap.put(LINE, getString(R.string.line));
		uiTextMap.put(CURVE, getString(R.string.curve));
		uiTextMap.put(SHAPE, getString(R.string.shape));
		uiTextMap.put(TU_YANG, getString(R.string.tu_yang));
		
		uiTextMap.put(SHOW_HOLD, getString(R.string.show_hold));
		uiTextMap.put(SHOW_DISAPPEAR, getString(R.string.show_disappear));
		uiTextMap.put(SHOW_DISAPPEAR_TOGETHER, getString(R.string.show_disappear_together));      

		uiTextMap.put(SETTING_BIT, getString(R.string.setting_bit));
		uiTextMap.put(SELF_CHOOSE_BIT, getString(R.string.self_choose_bit));
		uiTextMap.put(CONTINUED_BIT, getString(R.string.continued_bit));
		
		uiTextMap.put(XING_ZHUANG_CIRCLE, getString(R.string.circle));
		uiTextMap.put(XING_ZHUANG_TRIGON, getString(R.string.trigon));
		uiTextMap.put(XING_ZHUANG_SQUARE, getString(R.string.square));
		uiTextMap.put(XING_ZHUANG_HEXAGON, getString(R.string.hexagon));
		uiTextMap.put(XING_ZHUANG_RECTANGLE, getString(R.string.rectangle));
		uiTextMap.put(XING_ZHUANG_SECTOR, getString(R.string.sector));
		uiTextMap.put(XING_ZHUANG_PARALLELOGRAM, getString(R.string.parallelogram));
		uiTextMap.put(XING_ZHUANG_DIAMOND, getString(R.string.diamond));
		uiTextMap.put(XING_ZHUANG_PENTAGON, getString(R.string.pentagon));
		
		uiTextMap.put(TU_YANG_LINE, getString(R.string.line));		
		uiTextMap.put(TU_YANG_CURVE, getString(R.string.curve));		
		uiTextMap.put(TU_YANG_CIRCLE, getString(R.string.circle));
		uiTextMap.put(TU_YANG_TRIGON, getString(R.string.trigon));
		uiTextMap.put(TU_YANG_SQUARE, getString(R.string.square));
		uiTextMap.put(TU_YANG_HEXAGON, getString(R.string.hexagon));
		uiTextMap.put(TU_YANG_RECTANGLE, getString(R.string.rectangle));
		uiTextMap.put(TU_YANG_SECTOR, getString(R.string.sector));
		uiTextMap.put(TU_YANG_PARALLELOGRAM, getString(R.string.parallelogram));
		uiTextMap.put(TU_YANG_DIAMOND, getString(R.string.diamond));
		uiTextMap.put(TU_YANG_PENTAGON, getString(R.string.pentagon));
		
		uiTextMap.put(ZI_XUAN_XING_ZHUANG, getString(R.string.self_choose));
		uiTextMap.put(SUI_JI_XING_ZHUANG, getString(R.string.redom));
		
		uiParamValueMap = new HashMap<String, Integer>();
		uiParamValueMap.put(POINT, Constants.DisplayBody.POINT);
		uiParamValueMap.put(LINE, Constants.DisplayBody.LINE);
		uiParamValueMap.put(CURVE, Constants.DisplayBody.CURVE);
		uiParamValueMap.put(SHAPE, Constants.DisplayBody.SHAPES);
		
		uiParamValueMap.put(XING_ZHUANG_CIRCLE, Constants.Shapes.CIRCLE);
		uiParamValueMap.put(XING_ZHUANG_TRIGON, Constants.Shapes.TRIGON);
		uiParamValueMap.put(XING_ZHUANG_SQUARE, Constants.Shapes.SQUARE);
		uiParamValueMap.put(XING_ZHUANG_HEXAGON, Constants.Shapes.HEXAGON);		
		uiParamValueMap.put(XING_ZHUANG_RECTANGLE, Constants.Shapes.RECTANGLE);
		uiParamValueMap.put(XING_ZHUANG_SECTOR, Constants.Shapes.SECTOR);
		uiParamValueMap.put(XING_ZHUANG_PARALLELOGRAM, Constants.Shapes.PARALLELOGRAM);
		uiParamValueMap.put(XING_ZHUANG_DIAMOND, Constants.Shapes.DIAMOND);
		uiParamValueMap.put(XING_ZHUANG_PENTAGON, Constants.Shapes.PENTAGON);
		
		uiParamValueMap.put(TU_YANG_LINE, Constants.DisplayBody.TuYang.LINE);
		uiParamValueMap.put(TU_YANG_CURVE, Constants.DisplayBody.TuYang.CURVE);
		uiParamValueMap.put(TU_YANG_CIRCLE, Constants.DisplayBody.TuYang.CIRCLE);
		uiParamValueMap.put(TU_YANG_TRIGON, Constants.DisplayBody.TuYang.TRIGON);
		uiParamValueMap.put(TU_YANG_SQUARE, Constants.DisplayBody.TuYang.SQUARE);
		uiParamValueMap.put(TU_YANG_HEXAGON, Constants.DisplayBody.TuYang.HEXAGON);		
		uiParamValueMap.put(TU_YANG_RECTANGLE, Constants.DisplayBody.TuYang.RECTANGLE);
		uiParamValueMap.put(TU_YANG_SECTOR, Constants.DisplayBody.TuYang.SECTOR);
		uiParamValueMap.put(TU_YANG_PARALLELOGRAM, Constants.DisplayBody.TuYang.PARALLELOGRAM);
		uiParamValueMap.put(TU_YANG_DIAMOND, Constants.DisplayBody.TuYang.DIAMOND);
		uiParamValueMap.put(TU_YANG_PENTAGON, Constants.DisplayBody.TuYang.PENTAGON);
		
		uiParamValueMap.put(SHOW_HOLD, Constants.DisplayMode.SHOW_HOLD);
		uiParamValueMap.put(SHOW_DISAPPEAR, Constants.DisplayMode.SHOW_DISAPPEAR);
		uiParamValueMap.put(SHOW_DISAPPEAR_TOGETHER, Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER);
		
		uiParamValueMap.put(SETTING_BIT, Constants.BitType.GIVEN_BIT);
		uiParamValueMap.put(CONTINUED_BIT, Constants.BitType.CONTINUED_BIT);
	}
	
	private void enableStartBtn() {
		if (displayBodyImageViewBtnGroup.getValue() == null) {
			setOkButtonEnable(false);
			return;
		}else if (displayBodyImageViewBtnGroup.getValue().trim().equals(SHAPE)) {
			if(chooseShapeLinearLayoutBtnGroup.getValue().equals(ZI_XUAN_XING_ZHUANG)){
				boolean flag = false;
				for (int i = 0; i < xingZhuangLinearLayout.getChildCount(); i++) {
					if (xingZhuangLinearLayout.getChildAt(i) instanceof LinearLayoutCheckBox) {
						LinearLayoutCheckBox layoutCheckBox = (LinearLayoutCheckBox)xingZhuangLinearLayout.getChildAt(i);
						if(layoutCheckBox.isChecked()){
							flag = true;
							break;
						}
					}
				}
				setOkButtonEnable(flag);
				return;
			}
		}
		
		if (bitTypeImageViewBtnGroup.getValue() == null) {
			setOkButtonEnable(false);
			return;			
		}else if (bitTypeImageViewBtnGroup.getValue().equals(CONTINUED_BIT)) {
			if (startBit == 0) {
				setOkButtonEnable(false);
				return;				
			}
		}else if (bitTypeImageViewBtnGroup.getValue().equals(SELF_CHOOSE_BIT)) {
			if (bitSelfChosen == 0) {
				setOkButtonEnable(false);
				return;				
			}
		}else {
			if (questionCountImageViewBtnGroup.getValue() == null) {
				setOkButtonEnable(false);
				return;
			}
		}
		
		if (displayModeImageViewBtnGroup.getValue() == null) {
			setOkButtonEnable(false);
			return;							
		}
		
		setOkButtonEnable(true);
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