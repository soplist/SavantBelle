package com.flextronics.cn.activity.visionhearingtouch;

import java.util.HashMap;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearingtouch.ChooseParams4HtmtActivity;
import com.flextronics.cn.activity.hearingtouch.ChooseParams4HtrtActivity;
import com.flextronics.cn.activity.visiontouch.VtmtKeyStokeChooseParametersActivity;
import com.flextronics.cn.activity.visiontouch.VtmtTouchScreenChooseParametersActivity;
import com.flextronics.cn.activity.visiontouch.VtrtKeyStokeChooseParametersActivity;
import com.flextronics.cn.activity.visiontouch.VtrtTouchScreenChooseParametersActivity;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.ParamPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <b>A-视觉听觉触觉综合训练</b><br>
 * 选择何种训练
 * @author ZhangGuoYin
 *
 */
public class VhtChooseTrainingActivity extends BaseActivity {
	
	private final static String VISION_TOUCH = "VISION_TOUCH";
	private final static String HEARING_TOUCH = "HEARING_TOUCH";
	private final static String VISION_HEARING_TOUCH = "VISION_HEARING_TOUCH";
	private final static String RESPONSE_TRAINING = "RESPONSE_TRAINING";
	private final static String MEMORY_TRAINING = "MEMORY_TRAINING";
	private final static String TOUCH_SCREEN = "TOUCH_SCREEN";
	private final static String KEYSTOKE = "KEYSTOKE";
	private final static String TOUCH_SCREEN_KEYSTOKE = "TOUCH_SCREEN_KEYSTOKE";
	
	
	/**
	 * 感官类型参数面板
	 */
	private ParamPanel senseTypeParamPanel;
	/**
	 * 训练类型参数面板
	 */
	private ParamPanel trainingTypeParamPanel;	
	/**
	 * 操作方式参数面板
	 */
	private ParamPanel operationModeParamPanel;
	
	/**
	 * 感官类型按钮组
	 */
	private LinearLayoutBtnGroup senseTypeLinearLayoutBtnGroup;
	/**
	 * 训练类型按钮组
	 */
	private LinearLayoutBtnGroup trainingTypeLinearLayoutBtnGroup;
	/**
	 * 操作方式按钮组
	 */
	private LinearLayoutBtnGroup operationModeLinearLayoutBtnGroup;
	
	private HashMap<String, String> textsHashMap;
	
	private ImageView leftBottomImageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.vht_choose_training, null), getBaseLayoutParams());
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
		setTrainingTitle(getString(R.string.menuOptionsChoose));
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(true);		

		leftBottomImageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		leftBottomImageView.setImageResource(R.drawable.menu1);
		leftBottomTextView1.setText(R.string.Menu_A);
		leftBottomTextView2.setText(null);
		
    	senseTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel01);
    	trainingTypeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel02);    	
    	operationModeParamPanel = (ParamPanel)findViewById(R.id.ParamPanel03);
    	
    	senseTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup01);
    	trainingTypeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup02);
    	operationModeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup03);
    	operationModeLinearLayoutBtnGroup.getLinearLayoutBtns()[2].setEnabled(false);
    }
    
    /**
     * 为各view设置监听器
     */
    private void setupListeners(){
    	
    	senseTypeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				
				if (position == 0) { //视觉+触觉 
					trainingTypeParamPanel.unlock();
					operationModeParamPanel.unlock();
					if(operationModeLinearLayoutBtnGroup.getValue().equals(TOUCH_SCREEN_KEYSTOKE)){
						operationModeLinearLayoutBtnGroup.select(0);
						setupOperationModeParamPanelTexts();
					}					
				}else if (position == 1) { //听觉+触觉
					
					trainingTypeParamPanel.unlock();
					operationModeParamPanel.lock();
					operationModeLinearLayoutBtnGroup.select(1);
					setupOperationModeParamPanelTexts();
				}else if (position == 2) {//视觉+听觉+触觉 

					trainingTypeParamPanel.lock();
					operationModeParamPanel.lock();
					trainingTypeLinearLayoutBtnGroup.select(0);
					operationModeLinearLayoutBtnGroup.select(2);
					setupTrainingTypeParamPanelTexts();
					setupOperationModeParamPanelTexts();
				}
				
				setupSenseTypeParamPanelTexts();
			}
		});    	    	
    	
    	trainingTypeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				
				setupTrainingTypeParamPanelTexts();
			}
		});
    	
    	operationModeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				
				setupOperationModeParamPanelTexts();
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
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					if (senseTypeLinearLayoutBtnGroup.getValue().equals(VISION_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equals(RESPONSE_TRAINING)	&& 
							operationModeLinearLayoutBtnGroup.getValue().equals(TOUCH_SCREEN)) {
						
						startActivity(new Intent(getApplicationContext(), VtrtTouchScreenChooseParametersActivity.class));
					}else if (senseTypeLinearLayoutBtnGroup.getValue().equals(VISION_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equals(RESPONSE_TRAINING) &&
							operationModeLinearLayoutBtnGroup.getValue().equals(KEYSTOKE)) {
						
						startActivity(new Intent(getApplicationContext(), VtrtKeyStokeChooseParametersActivity.class));
					}else if(senseTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(VISION_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(MEMORY_TRAINING) && 
							operationModeLinearLayoutBtnGroup.getValue().equals(TOUCH_SCREEN)){
						
						startActivity(new Intent(getApplicationContext(),VtmtTouchScreenChooseParametersActivity.class));
					}else if(senseTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(VISION_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(MEMORY_TRAINING) && 
							operationModeLinearLayoutBtnGroup.getValue().equals(KEYSTOKE)){
						
						startActivity(new Intent(getApplicationContext(),VtmtKeyStokeChooseParametersActivity.class));						
					}else if (senseTypeLinearLayoutBtnGroup.getValue().equals(HEARING_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equals(RESPONSE_TRAINING) && 
							operationModeLinearLayoutBtnGroup.getValue().equals(KEYSTOKE)) {
						
						startActivity(new Intent(getApplicationContext(), ChooseParams4HtrtActivity.class));
					}else if(senseTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(HEARING_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equalsIgnoreCase(MEMORY_TRAINING) && 
							operationModeLinearLayoutBtnGroup.getValue().equals(KEYSTOKE)){
						
						startActivity(new Intent(getApplicationContext(),ChooseParams4HtmtActivity.class));
					}else if (senseTypeLinearLayoutBtnGroup.getValue().equals(VISION_HEARING_TOUCH) && 
							trainingTypeLinearLayoutBtnGroup.getValue().equals(RESPONSE_TRAINING) && 
							operationModeLinearLayoutBtnGroup.getValue().equals(TOUCH_SCREEN_KEYSTOKE)) {
						
						startActivity(new Intent(getApplicationContext(), VhtIntegrationTrainingChooseParametersActivity1.class));
					}
					finish();
				}
				return false;
			}
		});		
    }
    
    
    /**
     * 为变量赋初始值
     */
    private void setupVariableValues(){
        
    	textsHashMap = new HashMap<String, String>();
    	textsHashMap.put(VISION_TOUCH, getString(R.string.vision_touch));
    	textsHashMap.put(HEARING_TOUCH, getString(R.string.hearing_touch));
    	textsHashMap.put(VISION_HEARING_TOUCH, getString(R.string.vision_hearing_touch));
    	
    	textsHashMap.put(RESPONSE_TRAINING, getString(R.string.response_training));
    	textsHashMap.put(MEMORY_TRAINING, getString(R.string.memory_training));
    	
    	textsHashMap.put(TOUCH_SCREEN, getString(R.string.touch_screen));    	
    	textsHashMap.put(KEYSTOKE, getString(R.string.keystoke));
    	textsHashMap.put(TOUCH_SCREEN_KEYSTOKE, getString(R.string.touch_screen_keystoke));
    }
    
    /**
     * 显示感官类型参数面板选择的值
     */
    private void setupSenseTypeParamPanelTexts(){
    	senseTypeParamPanel.setResultText(
    			textsHashMap.get(senseTypeLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示训练类型参数面板选择的值
     */
    private void setupTrainingTypeParamPanelTexts(){
    	trainingTypeParamPanel.setResultText(
    			textsHashMap.get(trainingTypeLinearLayoutBtnGroup.getValue()));
    }
    
    /**
     * 显示操作方式参数面板选择的值
     */
    private void setupOperationModeParamPanelTexts(){
    	operationModeParamPanel.setResultText(
    			textsHashMap.get(operationModeLinearLayoutBtnGroup.getValue()));
    }
        
    private void setupTexts(){
    	setupSenseTypeParamPanelTexts();
    	setupTrainingTypeParamPanelTexts();
    	setupOperationModeParamPanelTexts();
    }

}