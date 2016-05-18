package com.flextronics.cn.activity.hearingtouch;

import java.util.HashMap;
import java.util.List;

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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
 * 听觉触觉记忆训练--选择参数操作--音阶部分
 * @author ZhangGuoYin
 *
 */
public class ChooseParams4HtmtScaleActivity extends BaseActivity {
	
	private final static String XI_YANG_YUE = "XI_YANG_YUE";
	private final static String MIN_ZU_YUE = "MIN_ZU_YUE";
	
	private final static String SETTING = "SETTING";
	private final static String SELF_CHOOSE = "SELF_CHOOSE";
	private final static String REDOM = "REDOM";
	
	private final static String SETTING_SINGLE_MUSIC = "SETTING_SINGLE_MUSIC";
	private final static String SELF_CHOOSE_SINGLE_MUSIC = "SELF_CHOOSE_SINGLE_MUSIC";
	private final static String SETTING_MULTI_MUSIC = "SETTING_MULTI_MUSIC";
	private final static String SELF_CHOOSE_MULTI_MUSIC = "SELF_CHOOSE_MULTI_MUSIC";
	private final static String REDOM_MUSIC = "REDOM_MUSIC";
	
	private final static String LEFT_HAND = "LEFT_HAND";
	private final static String RIGHT_HAND = "RIGHT_HAND";
	
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
	 * 题目数参数面板
	 */
	private ParamPanel questionCountParamPanel;
	/**
	 * 优势手参数面板
	 */
	private ParamPanel handParamPanel;
	
	/**
	 * 乐器类型按钮组
	 */
	private LinearLayoutBtnGroup sampleSetLinearLayoutBtnGroup;
	/**
	 * 音阶的设定.自选.随机按钮组
	 */
	private LinearLayoutBtnGroup isRedomSetLinearLayoutBtnGroup;
	/**
	 * 音结数下拉框
	 */
	private Spinner spinner;
	/**
	 * 音阶的无名指键选择区
	 */
	private LinearLayoutCheckBoxGroup scaleLinearLayoutCheckBoxGroup01;
	/**
	 * 音阶的中指键选择区
	 */
	private LinearLayoutCheckBoxGroup scaleLinearLayoutCheckBoxGroup02;
	/**
	 * 音阶的食指键选择区
	 */
	private LinearLayoutCheckBoxGroup scaleLinearLayoutCheckBoxGroup03;
	/**
	 * 乐器选择按钮组
	 */
	private LinearLayoutCheckBoxGroup sampleElementLinearLayoutCheckBoxGroup;
	/**
	 * 所有的乐器的选择按钮
	 */
	private LinearLayoutCheckBox[] sampleElementLinearLayoutCheckBoxs;
	/**
	 * 乐器-设定单一乐器/自选单一乐器。。。的按钮组
	 */
	private LinearLayoutBtnGroup sampleElementLinearLayoutBtnGroup;
	/**
	 * 题目数的按钮组
	 */
	private ImageViewBtnGroup questionCountImageViewBtnGroup;
	/**
	 * 优势手的按钮组
	 */
	private LinearLayoutBtnGroup handLinearLayoutBtnGroup;

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
		//将vht_hearing_touch_memory_training_choose_parameter_music中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_hearing_touch_memory_training_choose_parameter_scale, null), getBaseLayoutParams());
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
		setOkButtonEnable(false);
		//显示后退按钮
		setBackButtonEnable(true);
		//显示主页按钮
		setHomeButtonEnable(true);

		imageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		imageView.setImageResource(R.drawable.menu1);
		leftBottomTextView1.setText(R.string.Menu_A);
		leftBottomTextView3.setText(R.string.hearing_touch_memory_training_key);
		
    	sampleSetParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleSet);
    	scaleParamPanel = (ParamPanel)findViewById(R.id.ParamPanelScale);    	
    	sampleElementsParamPanel = (ParamPanel)findViewById(R.id.ParamPanelMusic);
    	questionCountParamPanel = (ParamPanel)findViewById(R.id.ParamPanel04);
    	handParamPanel = (ParamPanel)findViewById(R.id.ParamPanelSampleHand);
    	
    	sampleSetLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupSampleSet);
    	
    	spinner = (Spinner)findViewById(R.id.Spinner01);
    	isRedomSetLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupRedom);
    	scaleLinearLayoutCheckBoxGroup01 = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup02);
    	scaleLinearLayoutCheckBoxGroup02 = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup03);
    	scaleLinearLayoutCheckBoxGroup03 = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup04);
    	spinner.setAdapter(adapter);
    	
    	sampleElementLinearLayoutCheckBoxGroup = (LinearLayoutCheckBoxGroup)findViewById(R.id.LinearLayoutCheckBoxGroup01);
    	sampleElementLinearLayoutCheckBoxs = sampleElementLinearLayoutCheckBoxGroup.getLinearLayoutCheckBoxs();
    	checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{0},false);
    	sampleElementLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupMusic);    	
    	
    	questionCountImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup01);
    	handLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroupHand);    	
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
					startActivity(new Intent(getApplicationContext(), ChooseParams4HtmtActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					int musicSet = valuesHashMap.get(sampleSetLinearLayoutBtnGroup.getValue());
					int hand = valuesHashMap.get(handLinearLayoutBtnGroup.getValue());
					int questionCount = Integer.valueOf(questionCountImageViewBtnGroup.getValue());
					String musicString = "";		
					String scaleString = "";
					
					if(isRedomSetLinearLayoutBtnGroup.getValue().equals(REDOM)){
						String[] values = null;
						String[] _values0 = null;
						String[] _values = getValues(scaleLinearLayoutCheckBoxGroup01);
						_values0 = ArrayOperations.addElements(_values0, _values);
						values = ArrayOperations.addElements(values, ArrayOperations.redomElements(_values, 1));
						
						_values = getValues(scaleLinearLayoutCheckBoxGroup02);
						_values0 = ArrayOperations.addElements(_values0, _values);
						values = ArrayOperations.addElements(values, ArrayOperations.redomElements(_values, 1));
						
						_values = getValues(scaleLinearLayoutCheckBoxGroup03);
						_values0 = ArrayOperations.addElements(_values0, _values);
						values = ArrayOperations.addElements(values, ArrayOperations.redomElements(_values, 1));
						
						values = ArrayOperations.addElements(values, 
								ArrayOperations.redomElements(ArrayOperations.otherElements(_values0, values), 
										Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition()))-3));
						
						scaleString = ArrayOperations.toStringWithCharacter(values, ",");
					}else {
						String[] values = getSelectValues(scaleLinearLayoutCheckBoxGroup01);
						values = ArrayOperations.addElements(values, getSelectValues(scaleLinearLayoutCheckBoxGroup02));
						values = ArrayOperations.addElements(values, getSelectValues(scaleLinearLayoutCheckBoxGroup03));
						scaleString = ArrayOperations.toStringWithCharacter(values, ",");
					}					
					
					if(sampleElementLinearLayoutBtnGroup.getValue().endsWith(REDOM_MUSIC)){
						if(musicSet == Constants.Sample.FOREIGH_MUSIC){
							musicString = ArrayOperations.toStringWithCharacter(Constants.ForeignMusic.INSTRUMENTS, ",");
						}else if (musicSet == Constants.Sample.FOLK_MUSIC) {
							musicString = ArrayOperations.toStringWithCharacter(Constants.FolkMusic.INSTRUMENTS, ",");
						}
					}else {
						musicString = ArrayOperations.toStringWithCharacter(getSelectValues(sampleElementLinearLayoutCheckBoxGroup), ",");
					}	
					
					Log.d("123", "musicSet:" + musicSet);
					Log.d("123", "hand:" + hand);
					Log.d("123", "questionCount:" + questionCount);
					Log.d("123", "musicString:" + musicString);
					Log.d("123", "scaleString:" + scaleString);
					
					Intent intent = new Intent(getApplicationContext(), HtmtSampleActivity.class);
					Bundle bundle = new Bundle();
					
					bundle.putInt(Constants.HearingTouchMemoryTrainingUIParameter.TEST_TYPE, 
							Constants.HearingTouchMemoryTrainingUIParameter.SCALE);
					bundle.putInt(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_SET_MUSIC, musicSet);
					bundle.putString(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_ELEMENTS_MUSIC , musicString);
					bundle.putString(Constants.HearingTouchMemoryTrainingUIParameter.SAMPLE_ELEMENTS_SCALE, scaleString);
					bundle.putInt(Constants.HearingTouchMemoryTrainingUIParameter.QUESTION_COUNT, questionCount);
					bundle.putInt(Constants.HearingTouchMemoryTrainingUIParameter.HAND_TYPE, hand);
					
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
					setupMusicTexts(getResources().getStringArray(R.array.foreigh_music_names),
							Constants.ForeignMusic.INSTRUMENTS);
					break;
				case 1:
					setupMusicTexts(getResources().getStringArray(R.array.folk_music_names),
							Constants.FolkMusic.INSTRUMENTS);
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
				setupScaleTexts();
				checkScale(isRedomSetLinearLayoutBtnGroup.getValue().trim(), Integer.valueOf(adapter.getItem(position)));
				enableStartBtn();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}    		
    	});
    	
    	isRedomSetLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupScaleTexts();
				switch (position) {
				case 0:
					checkScale(SETTING, Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
					break;
				case 1:
					checkScale(SELF_CHOOSE, Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
					break;
				case 2:
					checkScale(REDOM, Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition())));
					break;

				default:
					break;
				}
				enableStartBtn();
			}
		});

    	scaleLinearLayoutCheckBoxGroup01.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				enableStartBtn();
			}
		});

    	scaleLinearLayoutCheckBoxGroup02.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				enableStartBtn();
			}
		});

    	scaleLinearLayoutCheckBoxGroup03.setOnSelectListener(new LinearLayoutCheckBoxGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
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
				case 4:
					checkLinearLayoutCheckBoxs(sampleElementLinearLayoutCheckBoxs, new int[]{-1},false);
					break;

				default:
					break;
				}

				setupMusicElementsTexts();
			}
		});
    	
    	questionCountImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupQuestionCountTexts();
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
    			"4", "5", "6", "7", "8"});
        
    	textsHashMap = new HashMap<String, String>();
    	textsHashMap.put(XI_YANG_YUE, getString(R.string.foreign_music));
    	textsHashMap.put(MIN_ZU_YUE, getString(R.string.folk_music));
    	
    	textsHashMap.put(SETTING, getString(R.string.setting));
    	textsHashMap.put(SELF_CHOOSE, getString(R.string.self_choose));
    	textsHashMap.put(REDOM, getString(R.string.redom));
    	
    	textsHashMap.put(SETTING_SINGLE_MUSIC, getString(R.string.setting_single_music));
    	textsHashMap.put(SELF_CHOOSE_SINGLE_MUSIC, getString(R.string.self_choose_single_music));
    	textsHashMap.put(SETTING_MULTI_MUSIC, getString(R.string.setting_multi_music));
    	textsHashMap.put(SELF_CHOOSE_MULTI_MUSIC, getString(R.string.self_choose_multi_music));
    	textsHashMap.put(REDOM_MUSIC, getString(R.string.redom_music));
    	
    	textsHashMap.put(LEFT_HAND, getString(R.string.left_hand));
    	textsHashMap.put(RIGHT_HAND, getString(R.string.right_hand));
    	
    	valuesHashMap = new HashMap<String, Integer>();
    	valuesHashMap.put(XI_YANG_YUE, Constants.Sample.FOREIGH_MUSIC);
    	valuesHashMap.put(MIN_ZU_YUE, Constants.Sample.FOLK_MUSIC);

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
     * 显示音阶面板选择的值
     */
    private void setupScaleTexts(){
    	scaleParamPanel.setResultText(textsHashMap.get(isRedomSetLinearLayoutBtnGroup.getValue()) + 
        		adapter.getItem(spinner.getSelectedItemPosition()) + getString(R.string.zhong) + getString(R.string.scale));
    }
    
    /**
     * 显示乐器面板选择的值
     */
    private void setupMusicElementsTexts(){
    	
    	String text = textsHashMap.get(sampleElementLinearLayoutBtnGroup.getValue());
    	sampleElementsParamPanel.setResultText(text);
    }
    
    /**
     * 设置题目数面板选择的值
     */
    private void setupQuestionCountTexts(){
    	questionCountParamPanel.setResultText(questionCountImageViewBtnGroup.getValue()
				+ getString(R.string.question_count_unit));
    }
        
    /**
     * 设置优势手面板选择的值
     */
    private void setupHandTexts(){
    	handParamPanel.setResultText(textsHashMap.get(handLinearLayoutBtnGroup.getValue()));
    }
    
    private void setupTexts(){
    	setupSampleSetTexts();
    	setupScaleTexts();
    	setupMusicElementsTexts();
    	setupQuestionCountTexts();
    	setupHandTexts();
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
	
	/**设定或者自选或者随机指定数目的音阶
	 * 
	 * @param action 设定/自选/随机
	 * @param num 音阶数
	 */
	private void checkScale(String action, int num){
		if(action.equals(REDOM)){
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup01.getLinearLayoutCheckBoxs(),
					new int[]{-1},false);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup02.getLinearLayoutCheckBoxs(),
					new int[]{-1},false);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup03.getLinearLayoutCheckBoxs(),
					new int[]{-1},false);
		}else if(action.equals(SETTING)){
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup01.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{1, 4, 7}, num), false);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup02.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{2, 5}, num), false);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup03.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{3, 6, 8}, num),  false);
		}else if(action.equals(SELF_CHOOSE)){
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup01.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{1, 4, 7}, num), true);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup02.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{2, 5}, num), true);
			checkLinearLayoutCheckBoxs(scaleLinearLayoutCheckBoxGroup03.getLinearLayoutCheckBoxs(),
					getIndex(new int[]{3, 6, 8}, num), true);
		}
	}
	
	/**
	 * 在指定数组中比指定数小的元素的索引组成的数组
	 * @param indexs 按升序排列的数组 非空
	 * @param num 大于0
	 * @return
	 */
	private int[] getIndex(int[] indexs, int num){
		int maxIndex = -1;
		for (int i = 0; i < indexs.length; i++) {
			if(indexs[i] <= num){
				maxIndex = i;
			}
		}
		if(maxIndex > -1){
			int[] _indexs = new int[maxIndex+1];
			for (int i = 0; i < _indexs.length; i++) {
				_indexs[i] = i;
			}
			return _indexs;
		}else {
			return new int[]{-1};
		}
	}	
	
	private void enableStartBtn(){

		if(!isRedomSetLinearLayoutBtnGroup.getValue().equals(REDOM)){
			if(scaleLinearLayoutCheckBoxGroup01.getSelectItems().size()<1){
				setOkButtonEnable(false);
				return;
			}
			if(scaleLinearLayoutCheckBoxGroup02.getSelectItems().size()<1){
				setOkButtonEnable(false);
				return;
			}
			if(scaleLinearLayoutCheckBoxGroup03.getSelectItems().size()<1){
				setOkButtonEnable(false);
				return;
			}			
		}
		
		if(isRedomSetLinearLayoutBtnGroup.getValue().equals(SELF_CHOOSE)){
			int musicCount = Integer.valueOf(adapter.getItem(spinner.getSelectedItemPosition()));
			if(scaleLinearLayoutCheckBoxGroup01.getSelectItems().size() + 
					scaleLinearLayoutCheckBoxGroup02.getSelectItems().size() + 
					scaleLinearLayoutCheckBoxGroup03.getSelectItems().size() != musicCount){
				setOkButtonEnable(false);
				return;				
			}
		}
		
		if(sampleElementLinearLayoutBtnGroup.getValue().endsWith(SELF_CHOOSE_SINGLE_MUSIC)){
			if(sampleElementLinearLayoutCheckBoxGroup.getSelectItems().size() != 1){
				setOkButtonEnable(false);
				return;				
			}
		}else if (sampleElementLinearLayoutBtnGroup.getValue().endsWith(SELF_CHOOSE_MULTI_MUSIC)) {
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
	
	private String[] getValues(LinearLayoutCheckBoxGroup checkBoxGroup){
		LinearLayoutCheckBox[] checkBoxs = checkBoxGroup.getLinearLayoutCheckBoxs();
		String[] values = new String[checkBoxs.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = checkBoxs[i].getValue();
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
}