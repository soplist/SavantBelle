package com.flextronics.cn.activity.visionhearingtouch;

import java.util.HashMap;
import java.util.List;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <b>A-视觉听觉触觉综合训练</b><br>
 * 视觉听觉触觉整合训练---听觉触觉选择参数部分
 * @author ZhangGuoYin
 *
 */
public class VhtIntegrationTrainingChooseParametersActivity2 extends BaseActivity {
	
	private final static String XI_YANG_YUE = "XI_YANG_YUE";
	private final static String MIN_ZU_YUE = "MIN_ZU_YUE";
	private final static String DA_JI_YUE = "DA_JI_YUE";
	
	private final static String SETTING = "SETTING";
	private final static String SELF_CHOOSE = "SELF_CHOOSE";
	
	private final static String SETTING_THREE_SCALE = "SETTING_THREE_SCALE";
	private final static String SELF_CHOOSE_THREE_SCALE = "SELF_CHOOSE_THREE_SCALE";
	
	private final static String SETTING_SINGLE_MUSIC = "SETTING_SINGLE_MUSIC";
	private final static String SELF_CHOOSE_SINGLE_MUSIC = "SELF_CHOOSE_SINGLE_MUSIC";
	private final static String SETTING_MULTI_MUSIC = "SETTING_MULTI_MUSIC";
	private final static String SELF_CHOOSE_MULTI_MUSIC = "SELF_CHOOSE_MULTI_MUSIC";
	
	/**
	 * 乐器类型参数面板
	 */
	private ParamPanel sampleSetParamPanel;
	/**
	 * 音阶参数面板
	 */
	private ParamPanel scaleParamPanel;	
	/**
	 * 乐器参数面板
	 */
	private ParamPanel sampleElementsParamPanel;
	
	/**
	 * 乐器类型按钮组
	 */
	private LinearLayoutBtnGroup sampleSetLinearLayoutBtnGroup;
	/**
	 * 音阶的设定.自选按钮组
	 */
	private LinearLayoutBtnGroup settingScaleLinearLayoutBtnGroup;
	/**
	 * 音阶的选择区
	 */
	private LinearLayoutCheckBoxGroup scaleLinearLayoutCheckBoxGroup;
	/**
	 * 乐器选择按钮组
	 */
	private LinearLayoutCheckBoxGroup sampleElementLinearLayoutCheckBoxGroup;
	/**
	 * 所有的音阶的选择按钮
	 */
	private LinearLayoutCheckBox[] scaleLinearLayoutCheckBoxs;
	/**
	 * 所有的乐器的选择按钮
	 */
	private LinearLayoutCheckBox[] sampleElementLinearLayoutCheckBoxs;
	/**
	 * 乐器-设定单一乐器/自选单一乐器。。。的按钮组
	 */
	private LinearLayoutBtnGroup sampleElementLinearLayoutBtnGroup;

	private ImageView imageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView3;
	
	private HashMap<String, String> textsHashMap;
	private HashMap<String, Integer> valuesHashMap;;

	private Bundle bundle;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		bundle = this.getIntent().getExtras();
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将vht_integration_training_choose_parameters_2中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_integration_training_choose_parameters_2, null), getBaseLayoutParams());
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
		setTrainingTitle(getString(R.string.hearing_touch_sample_choose));
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
		leftBottomTextView3.setText(R.string.vision_hearing_touch_integration_training);
		
    	sampleSetParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleSet);
    	scaleParamPanel = (ParamPanel)findViewById(R.id.ParamPanelScale);    	
    	sampleElementsParamPanel = (ParamPanel)findViewById(R.id.ParamPanelMusic);
    	
    	sampleSetLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupSampleSet);    	
    	settingScaleLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupChooseScale);
    	scaleLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroupScale);
    	scaleLinearLayoutCheckBoxs = scaleLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs();
    	checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, new int[]{0, 4, 7}, false);			
			
    	sampleElementLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupMusic);    	
    	sampleElementLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup01);
    	sampleElementLinearLayoutCheckBoxs = sampleElementLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs();
    	checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0},false);
    }
    
    /**
     * 为各view设置监听器
     */
    private void setupListeners(){
		
    	setOnBackButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent(getApplicationContext(), 
							VhtIntegrationTrainingChooseParametersActivity1.class);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
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
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {

					if(sampleSetLinearLayoutBtnGroup.getValue().equals(DA_JI_YUE)){
						bundle.putInt(Constants.VhtIntegrationTrainingUIParameter.TEST_TYPE, 
								Constants.VhtIntegrationTrainingUIParameter.MUSICAL_INSTRUMENT);
						bundle.putString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_HEARING_SCALE, "1");
					}else {						
						bundle.putInt(Constants.VhtIntegrationTrainingUIParameter.TEST_TYPE, 
								Constants.VhtIntegrationTrainingUIParameter.SCALE);
						bundle.putString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_HEARING_SCALE, 
								ArrayOperations.toStringWithCharacter(getSelectValues(scaleLinearLayoutCheckBoxGroup), ","));
					}
										
					bundle.putString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_SET_HEARING_INSTRUMENT, 
							valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue())+"");				
					bundle.putString(Constants.VhtIntegrationTrainingUIParameter.SAMPLE_ELEMENTS_HEARING_INSTRUMENT, 
							ArrayOperations.toStringWithCharacter(getSelectValues(sampleElementLinearLayoutCheckBoxGroup), ","));
									
					Intent intent = new Intent(getApplicationContext(), VhtIntegrationOperationActivity.class);
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
				if(position==0 || position==1){					
					enableShowView(sampleElementLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.VISIBLE);
					enableShowView(sampleElementLinearLayoutBtnGroup.getLinearLayoutBtns()[1], View.VISIBLE);
					if(scaleParamPanel.isClose()){
						scaleParamPanel.unlock();
					}
					setupScaleTexts();
					if(position == 0){
						setupMusicTexts(getResources().getStringArray(R.array.foreigh_music_names),
								Constants.ForeignMusic.INSTRUMENTS);
					}else {
						setupMusicTexts(getResources().getStringArray(R.array.folk_music_names),
								Constants.FolkMusic.INSTRUMENTS);
					}
				}else if (position == 2) {			
					enableShowView(sampleElementLinearLayoutBtnGroup.getLinearLayoutBtns()[0], View.GONE);
					enableShowView(sampleElementLinearLayoutBtnGroup.getLinearLayoutBtns()[1], View.GONE);
					scaleParamPanel.setResultText(null);					
					scaleParamPanel.lock();	
					setupMusicTexts(getResources().getStringArray(R.array.percussion_instrument_names),
							Constants.PercussionInstrument.INSTRUMENTS);
					
					if(sampleElementLinearLayoutBtnGroup.getValue().equals(SETTING_SINGLE_MUSIC) ||
							sampleElementLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_SINGLE_MUSIC)){
						sampleElementLinearLayoutBtnGroup.select(2);
						checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0, 1, 2},false);
						enableStartBtn();
					}
				}
				
				setupSampleSetTexts();
			}
		});
    	
    	settingScaleLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					checkScale(SETTING, 3);
					break;
				case 1:
					checkScale(SELF_CHOOSE, 3);
					break;

				default:
					break;
				}
				
				setupScaleTexts();
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
    	
    	sampleElementLinearLayoutCheckBoxGroup.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				enableStartBtn();
			}
		});
    	
    	sampleElementLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0},false);
					break;
				case 1:
					sampleElementLinearLayoutCheckBoxGroup.setSingleChoose(true);
					checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0},true);
					break;
				case 2:
					checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0, 1, 2},false);
					break;
				case 3:
					sampleElementLinearLayoutCheckBoxGroup.setSingleChoose(false);
					checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0, 1, 2},true);
					break;

				default:
					break;
				}

				setupMusicElementsTexts();
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
        
    	textsHashMap = new HashMap<String, String>();
    	textsHashMap.put(XI_YANG_YUE, getString(R.string.foreign_music));
    	textsHashMap.put(MIN_ZU_YUE, getString(R.string.folk_music));
    	textsHashMap.put(DA_JI_YUE, getString(R.string.percussion_instrument));
    	
    	textsHashMap.put(SETTING_THREE_SCALE, getString(R.string.setting_three_scale));
    	textsHashMap.put(SELF_CHOOSE_THREE_SCALE, getString(R.string.self_choose_three_scale));
    	
    	textsHashMap.put(SETTING_SINGLE_MUSIC, getString(R.string.setting_single_music));
    	textsHashMap.put(SELF_CHOOSE_SINGLE_MUSIC, getString(R.string.self_choose_single_music));
    	textsHashMap.put(SETTING_MULTI_MUSIC, getString(R.string.setting_multi_music));
    	textsHashMap.put(SELF_CHOOSE_MULTI_MUSIC, getString(R.string.self_choose_multi_music));
    	
    	valuesHashMap = new HashMap<String, Integer>();
    	valuesHashMap.put(XI_YANG_YUE, Constants.Sample.FOREIGH_MUSIC);
    	valuesHashMap.put(MIN_ZU_YUE, Constants.Sample.FOLK_MUSIC);
    	valuesHashMap.put(DA_JI_YUE, Constants.Sample.PERCUSSION_INSTRUMENT);
    }
    
    /**
     * 显示乐器类型面板选择的值
     */
    private void setupSampleSetTexts(){
    	sampleSetParamPanel.setResultText(
    			textsHashMap.get(sampleSetLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示音阶面板选择的值
     */
    private void setupScaleTexts(){
    	
    	String text = textsHashMap.get(settingScaleLinearLayoutBtnGroup.getValue());
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
     * 显示乐器面板选择的值
     */
    private void setupMusicElementsTexts(){
    	
    	String text = textsHashMap.get(sampleElementLinearLayoutBtnGroup.getValue());
    	sampleElementsParamPanel.setResultText(text);
    }
    
    
    private void setupTexts(){
    	setupSampleSetTexts();
    	setupScaleTexts();
    	setupMusicElementsTexts();
    	setupMusicTexts(getResources().getStringArray(R.array.foreigh_music_names),
				Constants.ForeignMusic.INSTRUMENTS);
    }
    	
	/**
	 * 设置乐器按钮组的乐器名及乐器编码
	 * @param names 乐器名数组，按从1-15升序
	 * @param values 乐器编码数组，按从1-15升序
	 */
	private void setupMusicTexts(String[] names, String[] values){
		for (int i = 0; i < sampleElementLinearLayoutCheckBoxs.length; i++) {
			sampleElementLinearLayoutCheckBoxs[i].setText(names[i]);
			sampleElementLinearLayoutCheckBoxs[i].setValue(values[i]);
		}
	}
	
	/**设定|自选  +  2|3种音阶
	 * 
	 * @param action 设定/自选
	 * @param num 音阶数
	 */
	private void checkScale(String action, int num){
		int[] indexs = null;
		if(num == 2){
			indexs = new int[]{0, 7};
		}else if (num == 3) {
			indexs = new int[]{0, 4, 7};
		}
		
		if(action.equals(SETTING)){
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, indexs, false);
		}else if(action.equals(SELF_CHOOSE)){
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxs, indexs, true);
		}
	}
		
	private void enableStartBtn(){
		if (settingScaleLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_THREE_SCALE)) {
			if(scaleLinearLayoutCheckBoxGroup.getSelectItems().size() != 3){
				setOkButtonEnable(false);
				return;				
			}			
		}
		
		if(sampleElementLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_SINGLE_MUSIC)){
			if(sampleElementLinearLayoutCheckBoxGroup.getSelectItems().size() != 1){
				setOkButtonEnable(false);
				return;				
			}
		}else if (sampleElementLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE_MULTI_MUSIC)) {
			if(sampleElementLinearLayoutCheckBoxGroup.getSelectItems().size() < 1){
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
	
	private void enableShowView(View view, int show){
		if(view.getVisibility() != show){
			view.setVisibility(show);
		}
	}
}