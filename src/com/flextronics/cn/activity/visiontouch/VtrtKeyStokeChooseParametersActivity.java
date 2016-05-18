package com.flextronics.cn.activity.visiontouch;

import java.util.HashMap;
import java.util.List;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.visionhearingtouch.VhtChooseTrainingActivity;
import com.flextronics.cn.ui.LinearLayoutBtn;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.LinearLayoutCheckBox;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.LinearLayoutCheckBoxList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <b>A-视觉听觉触觉综合训练</b><br>
 * 视觉触觉反应训练按键操作选择参数
 * @author ZhangGuoYin
 *
 */
public class VtrtKeyStokeChooseParametersActivity extends BaseActivity {
	
	private final static String REFLECT = "REFLECT";
	private final static String FIXED_POINT = "FIXED_POINT";
	private final static String INSTEAD = "INSTEAD";
	
	private final static String SETTING_SINGLE_SAMPLE = "SETTING_SINGLE_SAMPLE";
	private final static String SELF_CHOOSE_SINGLE_SAMPLE = "SELF_CHOOSE_SINGLE_SAMPLE";
	private final static String SETTING_TWO_SAMPLE = "SETTING_TWO_SAMPLE";
	private final static String SELF_CHOOSE_TWO_SAMPLE = "SELF_CHOOSE_TWO_SAMPLE";
	
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
	
	private final static String ORIGINAL_KEY = "ORIGINAL_KEY";
	private final static String OPPOSITE_KEY = "OPPOSITE_KEY";
	
	private final static String LEFT_HAND = "LEFT_HAND";
	private final static String RIGHT_HAND = "RIGHT_HAND";
	
	private LayoutInflater inflater;
	/**
	 * 回应类型参数面板
	 */
	private ParamPanel answerTypeParamPanel;
	/**
	 * 受测样本参数面板
	 */
	private ParamPanel sampleParamPanel;
	/**
	 * 测试时间参数面板
	 */
	private ParamPanel testingTimeParamPanel;
	/**
	 * 反应类型参数面板
	 */
	private ParamPanel responseTypeParamPanel;
	/**
	 * 优势手参数面板
	 */
	private ParamPanel handParamPanel;
	
	/**
	 * 回应类型按钮组
	 */
	private LinearLayoutBtnGroup answerTypeLinearLayoutBtnGroup;
	/**
	 * 设定单一样本/自选单一样本...按钮组
	 */
	private LinearLayoutBtnGroup chooseSampleLinearLayoutBtnGroup;
	/**
	 * 设定单一样本/自选单一样本...按钮组中的所有按钮
	 */
	private LinearLayoutBtn[] chooseSampleLinearLayoutBtns;
	/**
	 * 样本群的按钮组
	 */
	private LinearLayoutBtnGroup sampleSetLinearLayoutBtnGroup;
	/**
	 * 带有分割线的样本元素列布局
	 */
	private LinearLayout _sampleElementLinearLayout;
	/**
	 * 样本元素
	 */
	private LinearLayout sampleElementsLinearLayout;
	/**
	 * 测试时间的按钮组
	 */
	private LinearLayoutBtnGroup testingTimeLinearLayoutBtnGroup;
	/**
	 * 反应类型的按钮组
	 */
	private LinearLayoutBtnGroup responseTypeLinearLayoutBtnGroup;
	/**
	 * 优势手的按钮组
	 */
	private LinearLayoutBtnGroup handLinearLayoutBtnGroup;
	private LinearLayoutCheckBoxList sampleElementsCheckBoxList;
	
	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView3;
	
	private HashMap<String, String> textsHashMap;
	private HashMap<String, Integer> valuesHashMap;;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_vision_touch_response_training_choose_parameter_keystoke中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_vision_touch_response_training_choose_parameter_keystoke, null), getBaseLayoutParams());
		setContentView(layout);
        
        setupVariableValues();
        setupViews();
        setupListeners();
        setupTexts();
        
    }
    
    /**
     * 设置各Views
     */
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
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		imageView.setImageResource(R.drawable.menu1);
		leftBottomTextView1.setText(R.string.Menu_A);
		leftBottomTextView3.setText(R.string.vision_touch_response_training_key);

    	answerTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanelAnswerType);
    	sampleParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSample);
    	testingTimeParamPanel = (ParamPanel)findViewById(R.id.ParamPanelTime);
    	responseTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanelResponseType);
    	handParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleHand);
    	
    	answerTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupAnswerType);
    	chooseSampleLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupChooseSample);
    	sampleSetLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupSampleSet);    	
    	testingTimeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupTime);
    	responseTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupResponseType);
    	handLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupHand);       
    	
    	_sampleElementLinearLayout = (LinearLayout)findViewById(R.id.LinearLayout01);
    	sampleElementsLinearLayout = (LinearLayout)findViewById(R.id.LinearLayout02);
    	chooseSampleLinearLayoutBtns = chooseSampleLinearLayoutBtnGroup.getLinearLayoutBtns();
		enableShowView(chooseSampleLinearLayoutBtns[2], View.INVISIBLE);
		enableShowView(chooseSampleLinearLayoutBtns[3], View.INVISIBLE);
		for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
			enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.INVISIBLE);							
		}
    }
    
    /**
     * 为各view设置监听器
     */
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
    	
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					String sampleElements = "";
					String answerType = answerTypeLinearLayoutBtnGroup.getValue();
					String chooseSampleMode = chooseSampleLinearLayoutBtnGroup.getValue();
					
					if(answerType.equals(REFLECT)){
						if(chooseSampleMode.equals(SETTING_SINGLE_SAMPLE)){
							sampleElements = Constants.WhiteLight.WHITELIGHT+"";
						}else if(chooseSampleMode.equals(SELF_CHOOSE_SINGLE_SAMPLE)){
							sampleElements = ArrayOperations.toStringWithCharacter(
									CommonUtil.getSampleElementsInSample(valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue())), ",");
						}else {
							return false;
						}
					}else {
						sampleElements = ArrayOperations.toStringWithCharacter(getSelectValues(sampleElementsCheckBoxList), ",");
					}
					
					Bundle bundle = new Bundle();
					bundle.putInt(Constants.VisioTouchResponseTrainingUIParameter.TIME, 
							Integer.valueOf(testingTimeLinearLayoutBtnGroup.getValue()));
					bundle.putInt(Constants.VisioTouchResponseTrainingUIParameter.RESPONSE_TYPE, 
							valuesHashMap.get(responseTypeLinearLayoutBtnGroup.getValue()));
					bundle.putInt(Constants.VisioTouchResponseTrainingUIParameter.ANSWER_TYPE, 
							valuesHashMap.get(answerTypeLinearLayoutBtnGroup.getValue()));
					bundle.putString(Constants.VisioTouchResponseTrainingUIParameter.SAMPLE_SET, 
							valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()).toString());
					bundle.putString(Constants.VisioTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS, 
							sampleElements);
					bundle.putInt(Constants.VisioTouchResponseTrainingUIParameter.HAND_TYPE, 
							valuesHashMap.get(handLinearLayoutBtnGroup.getValue()));
					Intent intent = new Intent(getApplicationContext(), VtrtKeyStokeOperationActivity.class);
					intent.putExtras(bundle);				
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
    	
    	answerTypeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				if (position == 0) {//反射回应
					enableShowView(chooseSampleLinearLayoutBtns[2], View.INVISIBLE);
					enableShowView(chooseSampleLinearLayoutBtns[3], View.INVISIBLE);
					enableShowView(_sampleElementLinearLayout, View.GONE);
					
					if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SETTING_SINGLE_SAMPLE)){
						if(!sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.VISIBLE);
							for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
								enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.GONE);							
							}
							sampleSetLinearLayoutBtnGroup.select(0);
						}
					}else if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_SINGLE_SAMPLE)){
						if(sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.GONE);
							for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
								enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.VISIBLE);							
							}
							selectLinearLayoutBtn(sampleSetLinearLayoutBtnGroup, 1, true);
						}
					}else if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SETTING_TWO_SAMPLE) || 
							chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_TWO_SAMPLE)){
						selectLinearLayoutBtn(chooseSampleLinearLayoutBtnGroup, 0, true);//选中"设定单一样本",其他项可选
						enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.VISIBLE);
						for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.GONE);							
						}
						sampleSetLinearLayoutBtnGroup.select(0);
					}
					
				}else {//定点回应或者替代回应
					enableShowView(chooseSampleLinearLayoutBtns[2], View.VISIBLE);
					enableShowView(chooseSampleLinearLayoutBtns[3], View.VISIBLE);
					enableShowView(_sampleElementLinearLayout, View.VISIBLE);
					
					if(sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
						for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.VISIBLE);							
						}
						selectLinearLayoutBtn(sampleSetLinearLayoutBtnGroup, 1, true);//选中"颜色",其他项可选
						enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.GONE);
					}
					showSampleElements(answerTypeLinearLayoutBtnGroup.getValue(), 
							chooseSampleLinearLayoutBtnGroup.getValue(), valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
					_sampleElementLinearLayout.invalidate();
				}

				setupAnswerTypeTexts();
				setupSampleTexts();
			}
		});
    	
    	chooseSampleLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				
				if(answerTypeLinearLayoutBtnGroup.getValue().equals(REFLECT)){//如果是反射回应
					if(position == 0){//设定单一样本
						if(!sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.VISIBLE);
							for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
								enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.GONE);							
							}
							sampleSetLinearLayoutBtnGroup.select(0);
						}
					}else if (position == 1) {//自选单一样本
						if(sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
							selectLinearLayoutBtn(sampleSetLinearLayoutBtnGroup, 1, true);//其他项可选							
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.GONE);
							for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
								enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.VISIBLE);							
							}
						}						
					}
				}else {//如果是定点回应或替代回应

					if(sampleSetLinearLayoutBtnGroup.getValue().equals(WHITE)){
						selectLinearLayoutBtn(sampleSetLinearLayoutBtnGroup, 1, true);//其他项可选						
						enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.GONE);
						for (int i = 1; i < sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns().length; i++) {
							enableShowView(sampleSetLinearLayoutBtnGroup.getLinearLayoutBtns()[i], View.VISIBLE);							
						}
					}
					
					showSampleElements(answerTypeLinearLayoutBtnGroup.getValue(), 
							chooseSampleLinearLayoutBtnGroup.getValue(), valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
				}
				
				setupSampleTexts();
				enableStartBtn();
			}
		});
    	
    	sampleSetLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				showSampleElements(answerTypeLinearLayoutBtnGroup.getValue(), 
						chooseSampleLinearLayoutBtnGroup.getValue(), valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
				
				setupSampleTexts();
			}
		});    	
    	
    	testingTimeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupTestingTimeTexts();
			}
		});    
    	
    	responseTypeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupResponseTypeTexts();	
			}
		});
    	
    	handLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupHandTexts();	
			}
		});
    }
        
    /**
     * 为变量赋初始值
     */
    private void setupVariableValues(){
    	inflater = LayoutInflater.from(this);  
    	
    	textsHashMap = new HashMap<String, String>();
    	textsHashMap.put(REFLECT, getString(R.string.reflect));
    	textsHashMap.put(FIXED_POINT, getString(R.string.fixed_point));
    	textsHashMap.put(INSTEAD, getString(R.string.instead));
    	
    	textsHashMap.put(SETTING_SINGLE_SAMPLE, getString(R.string.setting_single_sample));
    	textsHashMap.put(SELF_CHOOSE_SINGLE_SAMPLE, getString(R.string.self_choose_single_sample));
    	textsHashMap.put(SETTING_TWO_SAMPLE, getString(R.string.setting_two_sample));
    	textsHashMap.put(SELF_CHOOSE_TWO_SAMPLE, getString(R.string.self_choose_two_sample));
    	
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
    	
    	textsHashMap.put(ORIGINAL_KEY, getString(R.string.original_key));
    	textsHashMap.put(OPPOSITE_KEY, getString(R.string.opposite_key));    	
    	textsHashMap.put(LEFT_HAND, getString(R.string.left_hand));
    	textsHashMap.put(RIGHT_HAND, getString(R.string.right_hand));
    	
    	valuesHashMap = new HashMap<String, Integer>();
    	valuesHashMap.put(REFLECT, Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER);
    	valuesHashMap.put(FIXED_POINT, Constants.VisioTouchResponseTrainingUIParameter.FIXED_POINT_ANSWER);
    	valuesHashMap.put(INSTEAD, Constants.VisioTouchResponseTrainingUIParameter.INSTEAD_ANSWER);
    	
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
    	
    	valuesHashMap.put(ORIGINAL_KEY, Constants.VisioTouchResponseTrainingUIParameter.ORIGINAL_KEY_RESPONSE);
    	valuesHashMap.put(OPPOSITE_KEY, Constants.VisioTouchResponseTrainingUIParameter.OPPOSITE_KEY_RESPONSE);
    	valuesHashMap.put(LEFT_HAND, Constants.RIGHT_HAND);
    	valuesHashMap.put(RIGHT_HAND, Constants.LEFT_HAND);
    }
    
    private void setupTexts(){
    	setupAnswerTypeTexts();
    	setupSampleTexts();
    	setupTestingTimeTexts();
    	setupResponseTypeTexts();   	
    	setupHandTexts();
    }
    
    /**
     * 显示回应类型面板选择的值
     */
    private void setupAnswerTypeTexts(){
    	answerTypeParamPanel.setResultText(
    			textsHashMap.get(answerTypeLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示受测样本面板选择的值
     */
    private void setupSampleTexts(){
    	sampleParamPanel.setResultText(
    			textsHashMap.get(chooseSampleLinearLayoutBtnGroup.getValue()) + getString(R.string.space_mark_2) +
    			textsHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示测试时间面板选择的值
     */
    private void setupTestingTimeTexts(){
    	testingTimeParamPanel.setResultText(Integer.valueOf(testingTimeLinearLayoutBtnGroup.getValue())/1000
				+ getString(R.string.second));
    }
        
    /**
     * 显示反应类型面板选择的值
     */
    private void setupResponseTypeTexts(){
    	responseTypeParamPanel.setResultText(textsHashMap.get(responseTypeLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 设置优势手面板选择的值
     */
    private void setupHandTexts(){
    	handParamPanel.setResultText(textsHashMap.get(handLinearLayoutBtnGroup.getValue()));
    }
    
    /**选中LinearLayoutBtnGroup中的某个按钮，其他项设置为可选/不可选
     * 
     * @param btnGroup
     * @param indexs 必须>=0
     * @param enable false--不可选; true--可选
     */
    private void selectLinearLayoutBtn(LinearLayoutBtnGroup btnGroup, int indexs, boolean enable){
    	if(indexs > -1 && indexs < btnGroup.getLinearLayoutBtns().length){
        	btnGroup.select(indexs);    		
    	}
    	
    	enableLinearLayoutBtnGroup(btnGroup, enable);
    }
    		
	private void enableStartBtn(){
		if(answerTypeLinearLayoutBtnGroup.getValue().equals(FIXED_POINT) ||
				answerTypeLinearLayoutBtnGroup.getValue().equals(INSTEAD)){
			if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_SINGLE_SAMPLE)){
				if(sampleElementsCheckBoxList.getSelectItems().size() != 1){
					setOkButtonEnable(false);
					return;				
				}
			}else if(chooseSampleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_TWO_SAMPLE)){
				if(sampleElementsCheckBoxList.getSelectItems().size() != 2){
					setOkButtonEnable(false);
					return;				
				}
			}
			setOkButtonEnable(true);
		}
	}
		
	private String[] getSelectValues(LinearLayoutCheckBoxList checkBoxGroup){
		List<LinearLayoutCheckBox> selectedList = checkBoxGroup.getSelectItems();
		String[] values = new String[selectedList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = selectedList.get(i).getValue();
		}
		return values;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 禁用键盘上的HOME,后退按钮
		if (keyCode==KeyEvent.KEYCODE_BACK || keyCode==KeyEvent.KEYCODE_HOME) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void enableLinearLayoutBtnGroup(LinearLayoutBtnGroup btnGroup, boolean enable){
		for(LinearLayoutBtn btn : btnGroup.getLinearLayoutBtns()){
			if(btn.isEnabled() != enable){
				btn.setEnabled(enable);
			}
		}
	}
	
	private void enableShowView(View view, int show){
		if(view.getVisibility() != show){
			view.setVisibility(show);
		}
	}	
	
	/**
	 * 显示样本群元素
	 * @param answerType	回应类型
	 * @param chooseSampleMode	选择样本的模式：设定|自选--单一|多种 样本  
	 * @param sampleCode 样本群编码
	 */
	public void showSampleElements(String answerType, String chooseSampleMode, int sampleCode){
		if(answerType.equals(REFLECT)){//反射回应
			return;
		}
		//创建样本元素按钮
		createSampleElementBtns(sampleCode);
		
		if(chooseSampleMode.equals(SETTING_SINGLE_SAMPLE) || 
				chooseSampleMode.equals(SELF_CHOOSE_SINGLE_SAMPLE)){//设定或自选单一样本
			sampleElementsCheckBoxList.setSingleChoose(true);//单选模式
			int[] indexs = null;
			switch (sampleCode) {
			case Constants.Sample.COLORS:
				indexs = new int[]{0};
				break;
			case Constants.Sample.NUMBERS:
				indexs = new int[]{1};
				break;
			case Constants.Sample.ROME_NUMBERS:
				indexs = new int[]{0};
				break;
			case Constants.Sample.ENGLISH_LETTERS:
				indexs = new int[]{0};
				break;
			case Constants.Sample.SHAPES:
				indexs = new int[]{1};
				break;
			case Constants.Sample.COMMON_MARKS:
				indexs = new int[]{13};
				break;
			case Constants.Sample.MUSIC_MARKS:
				indexs = new int[]{19};
				break;
			case Constants.Sample.FOREIGH_MUSIC:
				indexs = new int[]{0};
				break;
			case Constants.Sample.FOLK_MUSIC:
				indexs = new int[]{0};
				break;
			case Constants.Sample.PERCUSSION_INSTRUMENT:
				indexs = new int[]{0};
				break;
			default:
				break;
			}
			
			if(chooseSampleMode.equals(SETTING_SINGLE_SAMPLE)){//设定单一样本
				sampleElementsCheckBoxList.checkLinearLayoutCheckBox(indexs, false);
			}else {
				sampleElementsCheckBoxList.checkLinearLayoutCheckBox(indexs, true);
			}
		}else {//设定或自选多种样本
			sampleElementsCheckBoxList.setSingleChoose(false);//多选模式
			int[] indexs = null;
			switch (sampleCode) {
			case Constants.Sample.COLORS:
				indexs = new int[]{0, 3};
				break;
			case Constants.Sample.NUMBERS:
				indexs = new int[]{1, 3};
				break;
			case Constants.Sample.ROME_NUMBERS:
				indexs = new int[]{0, 2};
				break;
			case Constants.Sample.ENGLISH_LETTERS:
				indexs = new int[]{0, 2};
				break;
			case Constants.Sample.SHAPES:
				indexs = new int[]{1, 4};
				break;
			case Constants.Sample.COMMON_MARKS:
				indexs = new int[]{13, 14};
				break;
			case Constants.Sample.MUSIC_MARKS:
				indexs = new int[]{19, 12};
				break;
			case Constants.Sample.FOREIGH_MUSIC:
				indexs = new int[]{0, 2};
				break;
			case Constants.Sample.FOLK_MUSIC:
				indexs = new int[]{0, 2};
				break;
			case Constants.Sample.PERCUSSION_INSTRUMENT:
				indexs = new int[]{0, 2};
				break;
			default:
				break;
			}
			
			if(chooseSampleMode.equals(SETTING_TWO_SAMPLE)){//设定多种样本
				sampleElementsCheckBoxList.checkLinearLayoutCheckBox(indexs, false);
			}else {
				sampleElementsCheckBoxList.checkLinearLayoutCheckBox(indexs, true);
			}			
		}
	}
	
	/**创建样本元素选择按钮
	 * 
	 * @param sampleCode 样本群编码
	 */
	private void createSampleElementBtns(int sampleCode){
		sampleElementsCheckBoxList = new LinearLayoutCheckBoxList(this);
		sampleElementsCheckBoxList.setOnSelectListener(new LinearLayoutCheckBoxList.OnSelectListener() {			
			@Override
			public void onSelect(int position) {
				enableStartBtn();
			}
		});

		//移除sampleElementsLinearLayout中所有元素
		sampleElementsLinearLayout.removeAllViews();
		
		LinearLayout layout = new LinearLayout(this);
		//根据样本群编码获得图片资源编号
		int[] reses = CommonUtil.getSampleElementBtnImageResBySampleCode(sampleCode);
		String[] values = CommonUtil.getSampleElementsInSample(sampleCode);
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			LinearLayoutCheckBox checkBox = (LinearLayoutCheckBox)inflater.inflate(R.layout.vht_vision_touch_response_training_checkbox, null);
			BitmapDrawable bitmapDrawable = new BitmapDrawable(
					Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), reses[i]), 39, 39, true));
			checkBox.setNormalDrawable(bitmapDrawable);
			checkBox.setPressedDrawable(new BitmapDrawable(
					Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), reses[i]), 50, 50, true)));
			checkBox.setValue(values[i]);
			checkBox.check();
						
			//每10个一行
			if(count >= 10){
				sampleElementsLinearLayout.addView(layout);
				layout = new LinearLayout(this);
				layout.addView(checkBox);
				count = 0;
			}else {
				layout.addView(checkBox);
			}
			checkBox.reset();
			checkBox.setHoldPressingBackgd(false);
			sampleElementsCheckBoxList.addView(checkBox);
			count++;		
		}
		
		sampleElementsLinearLayout.addView(layout);
	}
}