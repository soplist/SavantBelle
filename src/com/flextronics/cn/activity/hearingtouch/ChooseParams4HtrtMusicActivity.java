package com.flextronics.cn.activity.hearingtouch;

import java.util.HashMap;
import java.util.List;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.LinearLayoutBtn;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.LinearLayoutCheckBox;
import com.flextronics.cn.ui.LinearLayoutCheckBoxGroup;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * <b>A-视觉听觉触觉综合训练</b><br>
 * 听觉触觉反应训练--选择参数操作--乐器部分
 * @author ZhangGuoYin
 *
 */
public class ChooseParams4HtrtMusicActivity extends BaseActivity {
	
	private final static String XI_YANG_YUE = "XI_YANG_YUE";
	private final static String MIN_ZU_YUE = "MIN_ZU_YUE";
	private final static String DA_JI_YUE = "DA_JI_YUE";
	
	private final static String SETTING = "SETTING";
	private final static String SELF_CHOOSE = "SELF_CHOOSE";
	
	private final static String SETTING_SINGLE_SCALE = "SETTING_SINGLE_SCALE";
	private final static String SELF_CHOOSE_SINGLE_SCALE = "SELF_CHOOSE_SINGLE_SCALE";
	private final static String SETTING_MULTI_SCALE = "SETTING_MULTI_SCALE";
	private final static String SELF_CHOOSE_MULTI_SCALE = "SELF_CHOOSE_MULTI_SCALE";
	private final static String REDOM_SCALE = "REDOM_SCALE";
	
	private final static String LEFT_HAND = "LEFT_HAND";
	private final static String RIGHT_HAND = "RIGHT_HAND";
	
	/**
	 * 乐器类型参数面板
	 */
	private ParamPanel sampleSetParamPanel;
	/**
	 * 乐器参数面板
	 */
	private ParamPanel sampleElementsParamPanel;
	/**
	 * 音阶参数面板
	 */
	private ParamPanel scaleParamPanel;
	/**
	 * 测试时间参数面板
	 */
	private ParamPanel testingTimeParamPanel;
	/**
	 * 优势手参数面板
	 */
	private ParamPanel handParamPanel;
	
	/**
	 * 乐器类型按钮组
	 */
	private LinearLayoutBtnGroup sampleSetLinearLayoutBtnGroup;
	/**
	 * 乐器的设定、自选按钮组
	 */
	private LinearLayoutBtnGroup settingOrSelfChooseLinearLayoutBtnGroup;
	/**
	 * 乐器数量下拉框
	 */
	private Spinner spinner;
	/**
	 * 乐器的无名指键选择区
	 */
	private LinearLayoutBtnGroup sampleElementsLinearLayoutBtnGroup01;
	/**
	 * 乐器的中指键选择区
	 */
	private LinearLayoutBtnGroup sampleElementsLinearLayoutBtnGroup02;
	/**
	 * 乐器的食指键选择区
	 */
	private LinearLayoutBtnGroup sampleElementsLinearLayoutBtnGroup03;
	/**
	 * 音阶选择按钮组
	 */
	private LinearLayoutCheckBoxGroup scaleLinearLayoutCheckBoxGroup;
	/**
	 * 所有的音阶的选择按钮
	 */
	private LinearLayoutCheckBox[] scaleLinearLayoutCheckBoxs;
	/**
	 * 音阶-设定单一音阶/自选单一音阶。。。的按钮组
	 */
	private LinearLayoutBtnGroup scaleLinearLayoutBtnGroup;
	/**
	 * 测试时间的按钮组
	 */
	private LinearLayoutBtnGroup testingTimeLinearLayoutBtnGroup;
	/**
	 * 优势手的按钮组
	 */
	private LinearLayoutBtnGroup handLinearLayoutBtnGroup;
	/**
	 * 中指键字样
	 */
	private TextView middleFingerTextView;
	
	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView3;
	private ArrayAdapter<String> adapter;
	
	private HashMap<String, String> textsHashMap;
	private HashMap<String, Integer> valuesHashMap;;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
      //从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.vht_hearing_touch_response_training_choose_parameter_music中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_hearing_touch_response_training_choose_parameter_music, null), getBaseLayoutParams());
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
		leftBottomTextView3.setText(R.string.hearing_touch_response_training_key);
		
    	sampleSetParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleSet);
    	sampleElementsParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleElement);
    	scaleParamPanel = (ParamPanel)findViewById(R.id.ParamPanelScale);
    	testingTimeParamPanel = (ParamPanel)findViewById(R.id.ParamPanelTime);
    	handParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleHand);
    	
    	sampleSetLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupSampleSet);
    	
    	spinner = (Spinner)findViewById(R.id.Spinner01);
    	settingOrSelfChooseLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupSetting);
    	sampleElementsLinearLayoutBtnGroup01 = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupMusic01);
    	sampleElementsLinearLayoutBtnGroup02 = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupMusic02);
    	sampleElementsLinearLayoutBtnGroup03 = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupMusic03);
    	spinner.setAdapter(adapter);
    	
    	scaleLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup01);
    	scaleLinearLayoutCheckBoxs = scaleLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs();
    	checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0},false);
    	scaleLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupScale);    	
    	
    	testingTimeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupTime);
    	handLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupHand);
    	middleFingerTextView = (TextView)findViewById(R.id.TextView03);
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
					startActivity(new Intent(getApplicationContext(), ChooseParams4HtrtActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					Bundle bundle = new Bundle();
					int musicSet = valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue());
					int testingTime = Integer.valueOf(testingTimeLinearLayoutBtnGroup.getValue());
					int hand = valuesHashMap.get(handLinearLayoutBtnGroup.getValue());
					String musicString = "";		
					String scaleString = "";
					
					if(Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())) == 2){
						
						musicString = sampleElementsLinearLayoutBtnGroup01.getValue() + "," + 
							sampleElementsLinearLayoutBtnGroup03.getValue();
					}else if(Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())) == 3){
						
						musicString = sampleElementsLinearLayoutBtnGroup01.getValue() + "," + 
							sampleElementsLinearLayoutBtnGroup02.getValue() + "," + 
							sampleElementsLinearLayoutBtnGroup03.getValue();

						bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_MIDDLE_FINGER, 
							Integer.valueOf(sampleElementsLinearLayoutBtnGroup02.getValue()));
					}
					
					if(sampleSetLinearLayoutBtnGroup.getValue().endsWith(DA_JI_YUE)){
						scaleString = "1";
					}else {
						if(scaleLinearLayoutBtnGroup.getValue().endsWith(REDOM_SCALE)){
							scaleString = "1,2,3,4,5,6,7,8";	
						}else {
							scaleString = ArrayOperations.toStringWithCharacter(getSelectValues(scaleLinearLayoutCheckBoxGroup), ",");
						}	
					}
										
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.TEST_TYPE, 
							Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT);					
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.HAND_TYPE, hand);					
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.TIME, testingTime);
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_SET_MUSIC, musicSet);					
					bundle.putString(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS_MUSIC, musicString);
					bundle.putString(Constants.HearingTouchResponseTrainingUIParameter.SAMPLE_ELEMENTS_SCALE, scaleString);					
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_FORE_FINGER, 
							Integer.valueOf(sampleElementsLinearLayoutBtnGroup03.getValue()));
					bundle.putInt(Constants.HearingTouchResponseTrainingUIParameter.MUSIC_THIRD_FINGER, 
							Integer.valueOf(sampleElementsLinearLayoutBtnGroup01.getValue()));
					
					Intent intent = new Intent(getApplicationContext(), HtrtSampleActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
    	sampleSetLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				switch (position) {
				case 0:
					scaleParamPanel.unlock();
					setupMusicTexts(getResources().getStringArray(R.array.foreigh_music_names),
							Constants.ForeignMusic.INSTRUMENTS);
					break;
				case 1:
					scaleParamPanel.unlock();
					setupMusicTexts(getResources().getStringArray(R.array.folk_music_names),
							Constants.FolkMusic.INSTRUMENTS);
					break;
				case 2:
					//禁用音阶选择面板
					scaleParamPanel.lock();					
					setupMusicTexts(getResources().getStringArray(R.array.percussion_instrument_names),
							Constants.PercussionInstrument.INSTRUMENTS);
					break;

				default:
					break;
				}
				
				setupSampleSetTexts();
			}
		});
    	
    	spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				setupMusicElementTexts();
				checkMusicElement(
						settingOrSelfChooseLinearLayoutBtnGroup.getValue().trim(), Integer.valueOf(adapter.getItem(position)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}    		
    	});
    	
    	settingOrSelfChooseLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupMusicElementTexts();
				switch (position) {
				case 0:
					checkMusicElement(SETTING, Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
					break;
				case 1:
					checkMusicElement(SELF_CHOOSE, Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
					break;

				default:
					break;
				}
			}
		});
    	
    	scaleLinearLayoutCheckBoxGroup.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupScaleTexts();
				enableStartBtn();
			}
		});
    	
    	scaleLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0},false);
					break;
				case 1:
					scaleLinearLayoutCheckBoxGroup.setSingleChoose(true);
					checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0},true);
					break;
				case 2:
					checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0, 4, 7},false);
					break;
				case 3:
					scaleLinearLayoutCheckBoxGroup.setSingleChoose(false);
					checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0, 4, 7},true);
					break;
				case 4:
					checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{-1},false);
					break;

				default:
					break;
				}

				setupScaleTexts();
			}
		});
    	
    	testingTimeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupTestingTimeTexts();
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
    
    /**
     * 为变量赋初始值
     */
    private void setupVariableValues(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{
    			"2", "3"});
        
    	textsHashMap = new HashMap<String, String>();    	
    	textsHashMap.put(XI_YANG_YUE, getString(R.string.foreign_music));
    	textsHashMap.put(MIN_ZU_YUE, getString(R.string.folk_music));
    	textsHashMap.put(DA_JI_YUE, getString(R.string.percussion_instrument));
    	
    	textsHashMap.put(SETTING, getString(R.string.setting));
    	textsHashMap.put(SELF_CHOOSE, getString(R.string.self_choose));
    	
    	textsHashMap.put(SETTING_SINGLE_SCALE, getString(R.string.setting_single_scale));
    	textsHashMap.put(SELF_CHOOSE_SINGLE_SCALE, getString(R.string.self_choose_single_scale));
    	textsHashMap.put(SETTING_MULTI_SCALE, getString(R.string.setting_multi_scale));
    	textsHashMap.put(SELF_CHOOSE_MULTI_SCALE, getString(R.string.self_choose_multi_scale));
    	textsHashMap.put(REDOM_SCALE, getString(R.string.redom_scale));
    	
    	textsHashMap.put(LEFT_HAND, getString(R.string.left_hand));
    	textsHashMap.put(RIGHT_HAND, getString(R.string.right_hand));
    	
    	valuesHashMap = new HashMap<String, Integer>();
    	valuesHashMap.put(XI_YANG_YUE, Constants.Sample.FOREIGH_MUSIC);
    	valuesHashMap.put(MIN_ZU_YUE, Constants.Sample.FOLK_MUSIC);
    	valuesHashMap.put(DA_JI_YUE, Constants.Sample.PERCUSSION_INSTRUMENT);

    	valuesHashMap.put(LEFT_HAND, Constants.RIGHT_HAND);
    	valuesHashMap.put(RIGHT_HAND, Constants.LEFT_HAND);
    }
    
    /**
     * 显示乐器类型面板选择的值
     */
    private void setupSampleSetTexts(){
    	sampleSetParamPanel.setResultText(
    			textsHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示乐器面板选择的值
     */
    private void setupMusicElementTexts(){
    	sampleElementsParamPanel.setResultText(textsHashMap.get(settingOrSelfChooseLinearLayoutBtnGroup.getValue()) + 
        		adapter.getItem(spinner.getSelectedItemPosition()) + getString(R.string.zhong) + getString(R.string.yue_qi));
    }
    
    /**
     * 显示音阶面板选择的值
     */
    private void setupScaleTexts(){
    	
    	String text = textsHashMap.get(scaleLinearLayoutBtnGroup.getValue());
    	List<LinearLayoutCheckBox> checkBoxList = scaleLinearLayoutCheckBoxGroup.getSelectItems();
    	if(checkBoxList!=null && checkBoxList.size()>0 && checkBoxList.get(0)!=null){
    		String[] values = new String[checkBoxList.size()];
    		for (int i = 0; i < values.length; i++) {
    			values[i] = checkBoxList.get(i).getValue();
			}
    		text += getString(R.string.space_mark_2);
    		text += ArrayOperations.toStringWithCharacter(values, ",");
    	}
    	scaleParamPanel.setResultText(text);
    }
    
    /**
     * 设置测试时间面板选择的值
     */
    private void setupTestingTimeTexts(){
    	testingTimeParamPanel.setResultText(Integer.valueOf(testingTimeLinearLayoutBtnGroup.getValue())/1000
				+ getString(R.string.second));
    }
        
    /**
     * 设置优势手面板选择的值
     */
    private void setupHandTexts(){
    	handParamPanel.setResultText(textsHashMap.get(handLinearLayoutBtnGroup.getValue()));
    }
    
    private void setupTexts(){
    	setupSampleSetTexts();
    	setupMusicElementTexts();
    	setupScaleTexts();
    	setupTestingTimeTexts();
    	setupHandTexts();
    	setupMusicTexts(getResources().getStringArray(R.array.foreigh_music_names), Constants.ForeignMusic.INSTRUMENTS);
    	checkMusicElement(settingOrSelfChooseLinearLayoutBtnGroup.getValue(), 
    			Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
    }
    	
	/**
	 * 设置食指键，中指键，无名指键 的乐器名及乐器编码
	 * @param names 乐器名数组，按从1-15升序
	 * @param values 乐器编码数组，按从1-15升序
	 */
	private void setupMusicTexts(String[] names, String[] values){
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		for (int i = 0; i < names.length; i++) {
			if((i+1)%3==1){
				sampleElementsLinearLayoutBtnGroup01.getLinearLayoutBtns()[index1].setText(names[i]);
				sampleElementsLinearLayoutBtnGroup01.getLinearLayoutBtns()[index1++].setValue(values[i]);
			}else if ((i+1)%3==2) {
				sampleElementsLinearLayoutBtnGroup02.getLinearLayoutBtns()[index2].setText(names[i]);
				sampleElementsLinearLayoutBtnGroup02.getLinearLayoutBtns()[index2++].setValue(values[i]);
			}else {
				sampleElementsLinearLayoutBtnGroup03.getLinearLayoutBtns()[index3].setText(names[i]);
				sampleElementsLinearLayoutBtnGroup03.getLinearLayoutBtns()[index3++].setValue(values[i]);
			}
		}
	}
	
	/**设定或者自选指定数目的乐器
	 * 
	 * @param action 设定/自选
	 * @param num 乐器数量
	 */
	private void checkMusicElement(String action, int num){
		if(action.equals(SETTING)){
			sampleElementsLinearLayoutBtnGroup01.select(0);
			sampleElementsLinearLayoutBtnGroup03.select(0);
			
			if(num == 2){
				enableShowView(sampleElementsLinearLayoutBtnGroup02, View.INVISIBLE);
				enableShowView(middleFingerTextView, View.INVISIBLE);
			}else if(num == 3){
				enableShowView(sampleElementsLinearLayoutBtnGroup02, View.VISIBLE);
				enableShowView(middleFingerTextView, View.VISIBLE);
				sampleElementsLinearLayoutBtnGroup02.select(0);
			}
			
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup01, false);
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup02, false);
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup03, false);
		}else if(action.equals(SELF_CHOOSE)){
			if(num == 2){
				enableShowView(sampleElementsLinearLayoutBtnGroup02, View.INVISIBLE);
				enableShowView(middleFingerTextView, View.INVISIBLE);
			}else if(num == 3){
				enableShowView(sampleElementsLinearLayoutBtnGroup02, View.VISIBLE);
				enableShowView(middleFingerTextView, View.VISIBLE);
			}
			
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup01, true);
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup02, true);
			enableLinearLayoutBtnGroup(sampleElementsLinearLayoutBtnGroup03, true);
		}
	}
		
	private void enableStartBtn(){
				
		if(scaleLinearLayoutBtnGroup.getValue().endsWith(SELF_CHOOSE_SINGLE_SCALE)){
			if(scaleLinearLayoutCheckBoxGroup.getSelectItems().size() != 1){
				setOkButtonEnable(false);
				return;				
			}
		}else if (scaleLinearLayoutBtnGroup.getValue().endsWith(SELF_CHOOSE_MULTI_SCALE)) {
			if(scaleLinearLayoutCheckBoxGroup.getSelectItems().size() < 1){
				setOkButtonEnable(false);
				return;				
			}			
		}
		
		setOkButtonEnable(true);
	}
	
	private String[] getSelectValues(LinearLayoutCheckBoxGroup checkBoxGroup){
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
}