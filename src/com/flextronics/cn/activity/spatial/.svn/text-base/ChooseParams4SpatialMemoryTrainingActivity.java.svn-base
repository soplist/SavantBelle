package com.flextronics.cn.activity.spatial;

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
import com.flextronics.cn.ui.LinearLayoutCheckBoxGroup;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.ArrayOperations;
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
import android.widget.Toast;

public class ChooseParams4SpatialMemoryTrainingActivity extends BaseActivity {
	
	private final static String ORDER_ANSWER = "ORDER_ANSWER";
	private final static String SPECIFY_ANSWER = "SPECIFY_ANSWER";
	private final static String ORIENTATION_ANSWER = "ORIENTATION_ANSWER";
	private final static String REGION_ANSWER = "REGION_ANSWER";	
	
	private final static String REGION_A = "REGION_A";
	private final static String REGION_B = "REGION_B";
	private final static String REGION_C = "REGION_C";
	private final static String REGION_D = "REGION_D";
	
	private final static String SETTING_SINGLE_SAMPLE = "SETTING_SINGLE_SAMPLE";
	private final static String SELF_CHOOSE_SINGLE_SAMPLE = "SELF_CHOOSE_SINGLE_SAMPLE";
	private final static String SETTING_MULTI_SAMPLE = "SETTING_MULTI_SAMPLE";
	private final static String SELF_CHOOSE_MULTI_SAMPLE = "SELF_CHOOSE_MULTI_SAMPLE";
	private final static String REDOM_SAMPLE = "REDOM_SAMPLE";
	
	private final static String WHITE = "WHITE";
	private final static String COLORS = "COLORS";
	private final static String NUMBERS = "NUMBERS";
	private final static String ROME_NUMBERS = "ROME_NUMBERS";
	private final static String ENGLISH_LETTERS = "ENGLISH_LETTERS";	
	private final static String SHAPES = "SHAPES";	
	private final static String COMMON_MARKS = "COMMON_MARKS";
	private final static String MUSIC_MARKS = "MUSIC_MARKS";
	private final static String FOREIGN_MUSIC = "FOREIGN_MUSIC";
	private final static String FOLK_MUSIC = "FOLK_MUSIC";
	private final static String PERCUSSION_INSTRUMENT = "PERCUSSION_INSTRUMENT";
	
	private final static String SHOW_HOLD = "SHOW_HOLD";
	private final static String SHOW_DISAPPEAR = "SHOW_DISAPPEAR";
	private final static String SHOW_DISAPPEAR_TOGETHER = "SHOW_DISAPPEAR_TOGETHER";
	private final static String SETTING_BIT = "SETTING_BIT";
	private final static String SELF_CHOOSE_BIT = "SELF_CHOOSE_BIT";
	private final static String CONTINUED_BIT = "CONTINUED_BIT";

	/**
	 * 回应类型参数面板
	 */
	private ParamPanel answerTypeParamPanel;
	/**
	 * 回应类型按钮组
	 */
	private LinearLayoutBtnGroup answerTypeLinearLayoutBtnGroup;
	/**
	 * 四个区域的子面板
	 */
	private ParamPanel regionParamPanel;
	/**
	 * 四个区域的按钮组
	 */
	private LinearLayoutCheckBoxGroup regionLinearLayoutCheckBoxGroup;
	/**
	 * 受测样本参数面板
	 */
	private ParamPanel sampleParamPanel;
	/**
	 * 设定单一样本/自选单一样本...按钮组
	 */
	private LinearLayoutBtnGroup chooseSampleLinearLayoutBtnGroup;
	/**
	 * 样本群选择按钮组
	 */
	private LinearLayoutCheckBoxGroup sampleLinearLayoutCheckBoxGroup;
	/**
	 * 显示方式参数面板
	 */
	private ParamPanel displayModeParamPanel;
	/**
	 * 显示位元参数面板
	 */
	private ParamPanel bitTypeParamPanel;
	/**
	 * 问题数参数面板
	 */
	private ParamPanel questionCountParamPanel;
	/**
	 * 显示方式图像按钮组
	 */
	private ImageViewBtnGroup displayModeImageViewBtnGroup;
	/**
	 * 显示位元图像按钮组
	 */
	private ImageViewBtnGroup bitTypeImageViewBtnGroup;
	/**
	 * 问题数图像按钮组
	 */
	private ImageViewBtnGroup questionCountImageViewBtnGroup;
	
	private Map<String, String> textsHashMap;
	private Map<String, Integer> valuesHashMap;
	
	/**
	 * 用户自选的位元数
	 */
	private int bitSelfChosen = 3;
	/**
	 * 开始位元
	 */
	private int startBit = 3;
	private int canSupportedMaxBit = 36;	
	
	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView2;
	private TextView leftBottomTextView3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.spatial_memory_training_choose_parameter中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.spatial_memory_training_choose_parameter, null), getBaseLayoutParams());
		setContentView(layout);
		
		setupHashMaps();
		setupViews();
		setupListeners();
		
		setupTexts();
	}
	
	private void setupViews(){
		//设置训练标题
		setTrainingTitle(getString(R.string.sampleChoose));
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像 
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//显示确定按钮
		setOkButtonEnable(true);
		//显示后退按钮
		setBackButtonEnable(true);
		//显示主页按钮
		setHomeButtonEnable(true);

		imageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		imageView.setImageResource(R.drawable.menu5);
		leftBottomTextView1.setText(R.string.Menu_E);
		leftBottomTextView2.setText(R.string.left_bottom_line_spatial);
		leftBottomTextView3.setText(R.string.spatial_memory_training);
		
		answerTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel01);
		answerTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup01);
		regionParamPanel = (ParamPanel)findViewById(R.id.ParamPanel02);
		regionLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup01);
		
    	sampleParamPanel = (ParamPanel)findViewById(R.id.ParamPanel03);
    	chooseSampleLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup02);
    	sampleLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup02);
		
		displayModeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel04);
		displayModeImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup01);
		
		bitTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel05);
		bitTypeImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup02);
		
		questionCountParamPanel = (ParamPanel)findViewById(R.id.ParamPanel06);
		questionCountImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup03);
		
		enableShowView(regionParamPanel.getBtnRelativeLayout(), View.GONE);
		setSettingSamples(ORDER_ANSWER, SETTING_SINGLE_SAMPLE);
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
		
		//选中具体的回应类型后将结果显示在右上角
		answerTypeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				if(position == 3){//按区域回应
					regionParamPanel.toggle(ParamPanel.CONTENT);
					if(bitTypeImageViewBtnGroup.getValue().equals(CONTINUED_BIT)){
						checkLinearLayoutCheckBoxs(regionLinearLayoutCheckBoxGroup
								.getLinearLayoutCheckBoxs(), new int[]{0, 1, 2, 3}, false);						
					}else {
						for(LinearLayoutCheckBox checkBox : regionLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs()){
							if(checkBox.isEnabled() != true){
								checkBox.setEnabled(true);
							}
						}
					}
				}else {//非区域回应
					regionParamPanel.close();
				}
				setSettingSamples(answerTypeLinearLayoutBtnGroup.getValue(), 
						chooseSampleLinearLayoutBtnGroup.getValue());
				setupAnswerTypeResultText();
				enableStartBtn();
			}
		});
		
		regionLinearLayoutCheckBoxGroup.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				setupAnswerTypeResultText();
				enableStartBtn();
			}
		});
		
		chooseSampleLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				setSettingSamples(answerTypeLinearLayoutBtnGroup.getValue(), 
						chooseSampleLinearLayoutBtnGroup.getValue());
				setupSampleTexts();
				enableStartBtn();
			}
		});
		
		sampleLinearLayoutCheckBoxGroup.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				enableStartBtn();
			}
		});
		
		//选中具体的显示模式后将结果显示在右上角
		displayModeImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				setupDisplayModeResultText();
			}
		});
		
		//选中具体的显示位元后的处理动作
		bitTypeImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				if(position == 2){//连续位元，选择题目的参数面板不需要显示出来
					enableShowView(questionCountParamPanel, View.GONE);
					showChooseBitDialog(3, canSupportedMaxBit, 130, -50, CONTINUED_BIT);
					//如果是区域回应,则将四个区域全部选中，并且不可点击
					if(answerTypeLinearLayoutBtnGroup.getValue().equals(REGION_ANSWER)){
						checkLinearLayoutCheckBoxs(regionLinearLayoutCheckBoxGroup
								.getLinearLayoutCheckBoxs(), new int[]{0, 1, 2, 3}, false);
					}
				}else{//非连续位元，选择题目的参数面板需要显示出来
					enableShowView(questionCountParamPanel, View.VISIBLE);
					if (position == 1) {
						showChooseBitDialog(3, canSupportedMaxBit, -110, -50, SELF_CHOOSE_BIT);
					}
					for(LinearLayoutCheckBox checkBox : regionLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs()){
						if(checkBox.isEnabled() != true){
							checkBox.setEnabled(true);
						}
					}
				}
				setupBitTypeResultText();
				setupAnswerTypeResultText();
				enableStartBtn();
			}
		});
		
		//选中具体的题目数后将结果显示在右上角
		questionCountImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				setupQuestionCountResultText();
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					Bundle bundle = new Bundle();
					int bitType;
					String sampleStr = "";
					if(bitTypeImageViewBtnGroup.getValue().equals(SELF_CHOOSE_BIT)){
						bitType = bitSelfChosen;
					}else {
						bitType = valuesHashMap.get(bitTypeImageViewBtnGroup.getValue());
					}
					
					if (answerTypeLinearLayoutBtnGroup.getValue().equals(REGION_ANSWER)) {
						//选择区域，当回应类型是按区域回应的时候，需要提供此值
						String[] _values = getSelectValues(regionLinearLayoutCheckBoxGroup);
						String[] values = new String[_values.length];
						for (int i = 0; i < values.length; i++) {
							values[i] = valuesHashMap.get(_values[i])+"";
						}
						
						bundle.putString(Constants.SpatialMemoryTrainingUIParameter.REGION, 
								ArrayOperations.toStringWithCharacter(values, ","));
					}else {
						//选择区域，当回应类型是按区域回应的时候，需要提供此值
						bundle.putString(Constants.SpatialMemoryTrainingUIParameter.REGION, null);
					}
					
					if (chooseSampleLinearLayoutBtnGroup.getValue().equals(REDOM_SAMPLE)) {
						
						sampleStr = Constants.Sample.WHITE+","+Constants.Sample.COLORS+","+Constants.Sample.NUMBERS+","+
						Constants.Sample.ROME_NUMBERS+","+Constants.Sample.ENGLISH_LETTERS+","+Constants.Sample.SHAPES+","+
						Constants.Sample.COMMON_MARKS+","+Constants.Sample.MUSIC_MARKS+","+
						Constants.Sample.FOREIGH_MUSIC+","+Constants.Sample.FOLK_MUSIC+","+Constants.Sample.PERCUSSION_INSTRUMENT;
					}else {
						String[] _values = getSelectValues(sampleLinearLayoutCheckBoxGroup);
						String[] values = new String[_values.length];
						for (int i = 0; i < values.length; i++) {
							values[i] = valuesHashMap.get(_values[i])+"";
						}
						sampleStr = ArrayOperations.toStringWithCharacter(values, ",");
					}

					//回应类型
					bundle.putInt(Constants.SpatialMemoryTrainingUIParameter.ANSWER_TYPE, 
							valuesHashMap.get(answerTypeLinearLayoutBtnGroup.getValue()));
					//显示模式
					bundle.putInt(Constants.SpatialMemoryTrainingUIParameter.DISPLAY_MODE, 
							valuesHashMap.get(displayModeImageViewBtnGroup.getValue()));		
					bundle.putInt(Constants.SpatialMemoryTrainingUIParameter.BIT_TYPE, bitType);
					//开始位元,当位元类型为连续位元时有作用
					bundle.putInt(Constants.SpatialMemoryTrainingUIParameter.START_BIT, startBit);
					//需测试的题目数，默认是15题
					bundle.putInt(Constants.SpatialMemoryTrainingUIParameter.QUESTION_COUNT, 
							Integer.valueOf(questionCountImageViewBtnGroup.getValue()));
					//选择的样本群
					bundle.putString(Constants.SpatialMemoryTrainingUIParameter.SAMPLE_SET, sampleStr);
					
					Intent intent = new Intent(getApplicationContext(), SpatialMemoryTrainingActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
	}
	
	/**
	 * 显示“回应类型”参数值
	 */
	private void setupAnswerTypeResultText(){
		String value = textsHashMap.get(answerTypeLinearLayoutBtnGroup.getValue());
		if(answerTypeLinearLayoutBtnGroup.getValue().equals(REGION_ANSWER)){
			if(regionLinearLayoutCheckBoxGroup.getSelectItems().size() > 0){
				String[] textStrings = new String[regionLinearLayoutCheckBoxGroup.getSelectItems().size()];
				for (int i = 0; i < textStrings.length; i++) {
					textStrings[i] = regionLinearLayoutCheckBoxGroup.getSelectItems().get(i).getText();
				}
				value = value + getString(R.string.space_mark_2) + ArrayOperations.toStringWithCharacter(textStrings, ",");
			}
		}
		answerTypeParamPanel.setResultText(value);
	}

	/**
     * 显示受测样本面板选择的值
     */
    private void setupSampleTexts(){
    	sampleParamPanel.setResultText(
    			textsHashMap.get(chooseSampleLinearLayoutBtnGroup.getValue()));
    }
    
	/**
	 * 显示“显示方式”参数值
	 */
	private void setupDisplayModeResultText(){
		displayModeParamPanel.setResultText(textsHashMap.get(displayModeImageViewBtnGroup.getValue()));
	}
	
	/**
	 * 显示“位元类型”参数值
	 */
	private void setupBitTypeResultText(){
		String value = bitTypeImageViewBtnGroup.getValue();
		if(value != null){
			value = value.trim();
			if(value.equals(SETTING_BIT)){
				bitTypeParamPanel.setResultText(textsHashMap.get(SETTING_BIT));				
			}else if(value.equals(SELF_CHOOSE_BIT)){
				//形如： 自选位元数: 8
				bitTypeParamPanel.setResultText(textsHashMap.get(SELF_CHOOSE_BIT) + 
						getString(R.string.space_mark_1) + bitSelfChosen);
			}else if(value.equals(CONTINUED_BIT)){
				//形如： 连续位元数|开始位元：2
				bitTypeParamPanel.setResultText(textsHashMap.get(CONTINUED_BIT) + 
						getString(R.string.space_mark_2) + getString(R.string.start_bit) + 
						getString(R.string.space_mark_1) + startBit);
			}
		}
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
		setupAnswerTypeResultText();
		setupSampleTexts();
		setupBitTypeResultText();
		setupDisplayModeResultText();
		setupQuestionCountResultText();
	}
	
	/**弹出选择位元数的dialog
	 * 
	 * @param from
	 * @param to
	 * @param x
	 * @param y
	 * @param type
	 */
	private void showChooseBitDialog(int from, int to, int x, int y, final String type){
		final List<TextView> textViews = new ArrayList<TextView>();
		final Dialog dialog = new Dialog(this, R.style.cs_choose_bit_dialog);
		
		HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
		LinearLayout layout = new LinearLayout(getApplicationContext());
		horizontalScrollView.addView(layout);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				52, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
				20, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layoutParams2.gravity = Gravity.CENTER;
		layout.addView(new TextView(this), layoutParams2);
		
		for(int i=from; i<=to; i++){
			TextView textView = new TextView(this);
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
		
		layout.addView(new TextView(ChooseParams4SpatialMemoryTrainingActivity.this), layoutParams2);
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
					setupBitTypeResultText();
					enableStartBtn();
					dialog.dismiss();
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
	 * 设置hash map的值
	 */
	private void setupHashMaps(){
		
		textsHashMap = new HashMap<String, String>();
		textsHashMap.put(ORDER_ANSWER, getString(R.string.order_answer));
		textsHashMap.put(SPECIFY_ANSWER, getString(R.string.specify_answer));
		textsHashMap.put(ORIENTATION_ANSWER, getString(R.string.orientation_answer));
		textsHashMap.put(REGION_ANSWER, getString(R.string.region_answer));
    	
    	textsHashMap.put(SETTING_SINGLE_SAMPLE, getString(R.string.setting_single_sample));
    	textsHashMap.put(SELF_CHOOSE_SINGLE_SAMPLE, getString(R.string.self_choose_single_sample));
    	textsHashMap.put(SETTING_MULTI_SAMPLE, getString(R.string.setting_multi_sample));
    	textsHashMap.put(SELF_CHOOSE_MULTI_SAMPLE, getString(R.string.self_choose_multi_sample));
    	textsHashMap.put(REDOM_SAMPLE, getString(R.string.redom_sample));
    	
    	textsHashMap.put(WHITE, getString(R.string.white));
    	textsHashMap.put(COLORS, getString(R.string.colors));
    	textsHashMap.put(NUMBERS, getString(R.string.numbers));
    	textsHashMap.put(ROME_NUMBERS, getString(R.string.rome_numbers));
    	textsHashMap.put(ENGLISH_LETTERS, getString(R.string.english_letters));
    	textsHashMap.put(SHAPES, getString(R.string.shapes));
    	textsHashMap.put(COMMON_MARKS, getString(R.string.common_marks));
    	textsHashMap.put(MUSIC_MARKS, getString(R.string.music_marks));
    	textsHashMap.put(FOREIGN_MUSIC, getString(R.string.foreign_music));
    	textsHashMap.put(FOLK_MUSIC, getString(R.string.folk_music));
    	textsHashMap.put(PERCUSSION_INSTRUMENT, getString(R.string.percussion_instrument));		
		
		textsHashMap.put(SHOW_HOLD, getString(R.string.show_hold));
		textsHashMap.put(SHOW_DISAPPEAR, getString(R.string.show_disappear));
		textsHashMap.put(SHOW_DISAPPEAR_TOGETHER, getString(R.string.show_disappear_together));      

		textsHashMap.put(SETTING_BIT, getString(R.string.setting_bit));
		textsHashMap.put(SELF_CHOOSE_BIT, getString(R.string.self_choose_bit));
		textsHashMap.put(CONTINUED_BIT, getString(R.string.continued_bit));
		
		valuesHashMap = new HashMap<String, Integer>();		
		valuesHashMap.put(ORDER_ANSWER, Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER);
		valuesHashMap.put(SPECIFY_ANSWER, Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER);
		valuesHashMap.put(ORIENTATION_ANSWER, Constants.SpatialMemoryTrainingUIParameter.ORIENTATION_ANSWER);
		valuesHashMap.put(REGION_ANSWER, Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER);
		
		valuesHashMap.put(REGION_A, Constants.SpatialMemoryTrainingUIParameter.A_REGION);
		valuesHashMap.put(REGION_B, Constants.SpatialMemoryTrainingUIParameter.B_REGION);
		valuesHashMap.put(REGION_C, Constants.SpatialMemoryTrainingUIParameter.C_REGION);
		valuesHashMap.put(REGION_D, Constants.SpatialMemoryTrainingUIParameter.D_REGION);
    	
    	valuesHashMap.put(WHITE, Constants.Sample.WHITE);
    	valuesHashMap.put(COLORS, Constants.Sample.COLORS);
    	valuesHashMap.put(NUMBERS, Constants.Sample.NUMBERS);
    	valuesHashMap.put(ROME_NUMBERS, Constants.Sample.ROME_NUMBERS);
    	valuesHashMap.put(ENGLISH_LETTERS, Constants.Sample.ENGLISH_LETTERS);
    	valuesHashMap.put(SHAPES, Constants.Sample.SHAPES);
    	valuesHashMap.put(COMMON_MARKS, Constants.Sample.COMMON_MARKS);
    	valuesHashMap.put(MUSIC_MARKS, Constants.Sample.MUSIC_MARKS);
    	valuesHashMap.put(FOREIGN_MUSIC, Constants.Sample.FOREIGH_MUSIC);
    	valuesHashMap.put(FOLK_MUSIC, Constants.Sample.FOLK_MUSIC);
    	valuesHashMap.put(PERCUSSION_INSTRUMENT, Constants.Sample.PERCUSSION_INSTRUMENT);
		
		valuesHashMap.put(SHOW_HOLD, Constants.DisplayMode.SHOW_HOLD);
		valuesHashMap.put(SHOW_DISAPPEAR, Constants.DisplayMode.SHOW_DISAPPEAR);
		valuesHashMap.put(SHOW_DISAPPEAR_TOGETHER, Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER);
		
		valuesHashMap.put(SETTING_BIT, Constants.BitType.GIVEN_BIT);
		valuesHashMap.put(CONTINUED_BIT, Constants.BitType.CONTINUED_BIT);
	}
	
	private void enableStartBtn() {
		
		if(answerTypeLinearLayoutBtnGroup.getValue().equals(REGION_ANSWER)){//区域回应
			if(bitTypeImageViewBtnGroup.getValue().equals(SELF_CHOOSE_BIT)){//自选位元				
				if(bitSelfChosen>0 && bitSelfChosen <=18){
					if(regionLinearLayoutCheckBoxGroup.getSelectItems().size() < 2){
						showToast(getString(R.string.pls_choose_region2, 2));
						setOkButtonEnable(false);
						return;
					}					
				}else if(bitSelfChosen>18 && bitSelfChosen <=27){//自选位元数
					if(regionLinearLayoutCheckBoxGroup.getSelectItems().size() < 3){				
						
						showToast(getString(R.string.pls_choose_region, bitSelfChosen, 3));
						setOkButtonEnable(false);
						return;
					}
				}else if (bitSelfChosen>27 && bitSelfChosen <=36) {
					if(regionLinearLayoutCheckBoxGroup.getSelectItems().size() < 4){
						
						showToast(getString(R.string.pls_choose_region, bitSelfChosen, 4));
						setOkButtonEnable(false);
						return;
					}
				}
			}else if (bitTypeImageViewBtnGroup.getValue().equals(SETTING_BIT)) {
				if(regionLinearLayoutCheckBoxGroup.getSelectItems().size() < 2){
					showToast(getString(R.string.pls_choose_region2, 2));
					setOkButtonEnable(false);
					return;
				}
			}
		}
		
		if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_SINGLE_SAMPLE)){
			if(sampleLinearLayoutCheckBoxGroup.getSelectItems().size() != 1){
				setOkButtonEnable(false);
				return;								
			}
		}else if (chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_MULTI_SAMPLE)) {
			if(sampleLinearLayoutCheckBoxGroup.getSelectItems().size() < 1){
				setOkButtonEnable(false);
				return;								
			}			
		}
		
		if (bitTypeImageViewBtnGroup.getValue().equals(CONTINUED_BIT)) {
			if (startBit == 0) {
				setOkButtonEnable(false);
				return;				
			}
		}else if (bitTypeImageViewBtnGroup.getValue().equals(SELF_CHOOSE_BIT)) {
			if (bitSelfChosen == 0) {
				setOkButtonEnable(false);
				return;				
			}
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
	
	private void setSettingSamples(String answerType, String settingCode) {
		int[] index = null;
		
		boolean enable = true;
		if (settingCode.equals(REDOM_SAMPLE)) {
			index = new int[]{-1};
			enable = false;
		}else if(answerType.equals(ORDER_ANSWER) || answerType.equals(ORIENTATION_ANSWER)||
				answerType.equals(REGION_ANSWER)){
			
			if (settingCode.equals(SETTING_SINGLE_SAMPLE)) {
				index = new int[]{0};
				enable = false;
			}else if (settingCode.equals(SELF_CHOOSE_SINGLE_SAMPLE)) {
				index = new int[]{1};
				enable = true;
				sampleLinearLayoutCheckBoxGroup.setSingleChoose(true);
			}else if (settingCode.equals(SETTING_MULTI_SAMPLE)) {
				index = new int[]{0, 1, 2, 4};
				enable = false;
			}else if (settingCode.equals(SELF_CHOOSE_MULTI_SAMPLE)) {
				index = new int[]{0, 1, 2, 4};
				enable = true;
				sampleLinearLayoutCheckBoxGroup.setSingleChoose(false);
			}
		}else if (answerType.equals(SPECIFY_ANSWER)) {
			
			if (settingCode.equals(SETTING_SINGLE_SAMPLE)) {
				index = new int[]{2};
				enable = false;
			}else if (settingCode.equals(SELF_CHOOSE_SINGLE_SAMPLE)) {
				index = new int[]{2};
				enable = true;
				sampleLinearLayoutCheckBoxGroup.setSingleChoose(true);
			}else if (settingCode.equals(SETTING_MULTI_SAMPLE)) {
				index = new int[]{1, 2, 4, 5};
				enable = false;
			}else if (settingCode.equals(SELF_CHOOSE_MULTI_SAMPLE)) {
				index = new int[]{1, 2, 4, 5};
				enable = true;
				sampleLinearLayoutCheckBoxGroup.setSingleChoose(false);
			}			
		}
		checkLinearLayoutCheckBoxs(sampleLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs(),
				index, enable);
	}
	
	private void enableShowView(View view, int show){
		if(view.getVisibility() != show){
			view.setVisibility(show);
		}
	}
	
	private void checkLinearLayoutCheckBoxs(LinearLayoutCheckBox[] checkBoxs, int[] indexs, boolean enable){
    	for(LinearLayoutCheckBox checkBox : checkBoxs ){
    		checkBox.reset();
    		checkBox.setEnabled(true); 
    	}
    	for (int i = 0; i < indexs.length; i++) {
			for (int j = 0; j < checkBoxs.length; j++) {
				if(indexs[i] == j){
					checkBoxs[j].check();
				}
			}
		}
    	if(!enable){
        	for(LinearLayoutCheckBox checkBox : checkBoxs ){
            	checkBox.setEnabled(false);
        	}
    	}
    }
	
	private void showToast(String string){
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
	
	private String[] getSelectValues(LinearLayoutCheckBoxGroup checkBoxGroup){
		List<LinearLayoutCheckBox> selectedList = checkBoxGroup.getSelectItems();
		String[] values = new String[selectedList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = selectedList.get(i).getValue();
		}
		return values;
	}
}