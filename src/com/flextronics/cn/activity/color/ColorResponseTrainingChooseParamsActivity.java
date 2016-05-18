package com.flextronics.cn.activity.color;

import java.util.HashMap;
import java.util.Map;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.ImageViewBtnGroup;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ColorResponseTrainingChooseParamsActivity extends BaseActivity {
	
	/**
	 * 设定单一颜色
	 */
	private final static String SETTING_SINGLE_COLOR = "SETTING_SINGLE_COLOR";
	/**
	 * 自选单一颜色
	 */
	private final static String SELF_CHOOSE_SINGLE_COLOR = "SELF_CHOOSE_SINGLE_COLOR";
	/**
	 * 随机单一颜色
	 */
	private final static String REDOM_SINGLE_COLOR = "REDOM_SINGLE_COLOR";
	
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
	 * 自选单一形状
	 */
	private final static String ZI_XUAN_XING_ZHUANG = "ZI_XUAN_XING_ZHUANG";
	/**
	 * 随机单一形状
	 */
	private final static String SUI_JI_XING_ZHUANG = "SUI_JI_XING_ZHUANG";
		
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
	
	//9种颜色
	private final static String COLOR_1 = "COLOR_1";
	private final static String COLOR_2 = "COLOR_2";
	private final static String COLOR_3 = "COLOR_3";
	private final static String COLOR_4 = "COLOR_4";
	private final static String COLOR_5 = "COLOR_5";
	private final static String COLOR_6 = "COLOR_6";
	private final static String COLOR_7 = "COLOR_7";
	private final static String COLOR_8 = "COLOR_8";
	private final static String COLOR_9 = "COLOR_9";
	

	/**
	 * 颜色参数面板
	 */
	private ParamPanel colorParamPanel;
	/**
	 * 点击“自选单一颜色”后弹出的选择颜色的参数面板
	 */
	private ParamPanel selfChooseColorParamPanel;
	/**
	 * 显示形体参数面板
	 */
	private ParamPanel displayBodyParamPanel;
	/**
	 * 点击形状按钮后弹出的子面板
	 */
	private ParamPanel shapesParamPanel;
	/**
	 * 点击自选形状弹出的子面板
	 */
	private ParamPanel selfChooseShapesParamPanel;
	/**
	 * 问题数参数面板
	 */
	private ParamPanel questionCountParamPanel;
	
	/**
	 * 颜色图像按钮组
	 */
	private ImageViewBtnGroup colorImageViewBtnGroup;
	/**
	 * 自选单一颜色图像按钮组
	 */
	private ImageViewBtnGroup selfChooseColorImageViewBtnGroup;	
	/**
	 * 显示形体图像按钮组
	 */
	private ImageViewBtnGroup displayBodyImageViewBtnGroup;
	/**
	 * 问题数图像按钮组
	 */
	private ImageViewBtnGroup questionCountImageViewBtnGroup;
	
	/**
	 * 自选单一形状/随机单一形状选择按钮组
	 */
	private LinearLayoutBtnGroup chooseShapeLinearLayoutBtnGroup;
	/**
	 * 选择单一形状的按钮组
	 */
	private LinearLayoutBtnGroup shapesLinearLayoutBtnGroup;
	
	private Map<String, String> uiTextMap;
	private Map<String, Integer> uiParamValueMap;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		//将R.layout.color_memory_training_choose_parameter中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.color_response_training_choose_parameter, null), getBaseLayoutParams());
		setContentView(layout);
		
		setupHashMaps();
		setupViews();
		setupListeners();
		
		setupTexts();
	}
	
	private void setupViews(){
		//设置训练标题
		setTrainingTitle(getString(R.string.color_response_training));
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
		
		colorParamPanel = (ParamPanel)findViewById(R.id.ParamPanel01);
		selfChooseColorParamPanel = (ParamPanel)findViewById(R.id.ParamPanel06);
		displayBodyParamPanel = (ParamPanel)findViewById(R.id.ParamPanel02);
		shapesParamPanel = (ParamPanel)findViewById(R.id.ParamPanel03);
		selfChooseShapesParamPanel = (ParamPanel)findViewById(R.id.ParamPanelShapeContent);
		questionCountParamPanel = (ParamPanel)findViewById(R.id.ParamPanel04);
		
		colorImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup01);
		selfChooseColorImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup06);
		displayBodyImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup02);
		questionCountImageViewBtnGroup = (ImageViewBtnGroup)findViewById(R.id.ImageViewBtnGroup04);
		
		chooseShapeLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup_Shape);
		shapesLinearLayoutBtnGroup = (LinearLayoutBtnGroup)findViewById(R.id.LinearLayoutBtnGroup05);
				
		selfChooseColorParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		shapesParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		selfChooseShapesParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
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
					startActivity(new Intent(ColorResponseTrainingChooseParamsActivity.this, ColorTrainingChooseActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					String sampleElementStr = "";
					String displayBodyStr = "";
					if(colorImageViewBtnGroup.getValue().trim().equals(SETTING_SINGLE_COLOR)){
						sampleElementStr = Constants.Colors.RED + "";
					}else if(colorImageViewBtnGroup.getValue().trim().equals(SELF_CHOOSE_SINGLE_COLOR)){
						sampleElementStr = uiParamValueMap.get(
								selfChooseColorImageViewBtnGroup.getValue().trim())+"";
					}else if(colorImageViewBtnGroup.getValue().trim().equals(REDOM_SINGLE_COLOR)){
						sampleElementStr = Constants.Colors.RED+","+Constants.Colors.ORANGE+","+Constants.Colors.YELLOW
							+","+Constants.Colors.GREEN+","+Constants.Colors.BLUE+","+Constants.Colors.INDIGO
							+","+Constants.Colors.PURPLE+","+Constants.Colors.BLACK+","+Constants.Colors.COFFEE;
					}
						
					if(displayBodyImageViewBtnGroup.getValue().trim().equals(SHAPE)){
						if(chooseShapeLinearLayoutBtnGroup.getValue().trim().equals(ZI_XUAN_XING_ZHUANG)){
							displayBodyStr = uiParamValueMap.get(
									shapesLinearLayoutBtnGroup.getValue().trim())+"";
						}else {
							displayBodyStr = Constants.Shapes.DIAMOND+","+Constants.Shapes.CIRCLE+","+Constants.Shapes.TRIGON+","+
								Constants.Shapes.SQUARE+","+Constants.Shapes.HEXAGON+","+Constants.Shapes.RECTANGLE+","+
								Constants.Shapes.SECTOR+","+Constants.Shapes.PARALLELOGRAM+","+Constants.Shapes.PENTAGON;
						}
					}else {
						displayBodyStr = uiParamValueMap.get(
								displayBodyImageViewBtnGroup.getValue().trim())+"";
					}					
					
					Intent intent = new Intent(ColorResponseTrainingChooseParamsActivity.this, ColorResponseTrainingActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString(Constants.ColorResponseTrainingUIParameter.SAMPLE_SET, Constants.Sample.COLORS+"");
					bundle.putString(Constants.ColorResponseTrainingUIParameter.SAMPLE_ELEMENTS, sampleElementStr);
					bundle.putString(Constants.ColorResponseTrainingUIParameter.DISPLAY_BODY, displayBodyStr);					
					bundle.putInt(Constants.ColorMemoryTrainingUIParameter.QUESTION_COUNT,
							Integer.valueOf(questionCountImageViewBtnGroup.getValue()));
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
		
		//选中颜色类型后将结果显示在右上角
		colorImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupColorResultText();
				if(colorImageViewBtnGroup.getValue() != null){
					if(colorImageViewBtnGroup.getValue().trim().equals(SELF_CHOOSE_SINGLE_COLOR)){
						selfChooseColorParamPanel.toggle(ParamPanel.CONTENT);
					}else {						
						selfChooseColorParamPanel.close();
					}
				}
			}
		});
		
		//选中单一颜色后将结果显示在右上角
		selfChooseColorImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupColorResultText();
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
							if(selfChooseShapesParamPanel.isClose()){
								selfChooseShapesParamPanel.open();								
							}
						}
						shapesParamPanel.toggle(ParamPanel.CONTENT);
					}else {						
						shapesParamPanel.close();
					}
				}
			}
		});
		
		//选择“自选单一形状，随机形状”后将结果显示在右上角
		chooseShapeLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				if(position == 0){
					selfChooseShapesParamPanel.open();
				}else{
					selfChooseShapesParamPanel.close();					
				}
				setupDisplayBodyResultText();
			}
		});
		
		//选择单一形状后将结果显示在右上角
		shapesLinearLayoutBtnGroup.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				setupDisplayBodyResultText();
			}
		});
		
		//选中具体的题目数后将结果显示在右上角
		questionCountImageViewBtnGroup.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				setupQuestionCountResultText();
			}
		});
	}
	
	/**
	 * 显示“颜色”参数值
	 */
	private void setupColorResultText(){
		String value = colorImageViewBtnGroup.getValue();
		if(value != null){
			value = value.trim();
			//如果是自选单一颜色
			if(value.equals(SELF_CHOOSE_SINGLE_COLOR)){
				String text = "";
				text += uiTextMap.get(SELF_CHOOSE_SINGLE_COLOR) + getString(R.string.space_mark_2) + 
					uiTextMap.get(selfChooseColorImageViewBtnGroup.getValue());
				colorParamPanel.setResultText(text);
			}else {
				colorParamPanel.setResultText(uiTextMap.get(value));
			}
		}		
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
					text += getString(R.string.space_mark_2) + 
						uiTextMap.get(shapesLinearLayoutBtnGroup.getValue());
				}
				displayBodyParamPanel.setResultText(text);
			}else {
				displayBodyParamPanel.setResultText(uiTextMap.get(value));
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
		setupColorResultText();
		setupDisplayBodyResultText();
		setupQuestionCountResultText();
	}
		
	
	/**
	 * 设置hash map的值
	 */
	private void setupHashMaps(){
		
		uiTextMap = new HashMap<String, String>();
		
		uiTextMap.put(SETTING_SINGLE_COLOR, getString(R.string.setting_single_color));
		uiTextMap.put(SELF_CHOOSE_SINGLE_COLOR, getString(R.string.self_choose_single_color));
		uiTextMap.put(REDOM_SINGLE_COLOR, getString(R.string.redom_single_color));
		
		uiTextMap.put(COLOR_1, getString(R.string.red));
		uiTextMap.put(COLOR_2, getString(R.string.orange));
		uiTextMap.put(COLOR_3, getString(R.string.yellow));
		uiTextMap.put(COLOR_4, getString(R.string.green));
		uiTextMap.put(COLOR_5, getString(R.string.blue));
		uiTextMap.put(COLOR_6, getString(R.string.indigo));
		uiTextMap.put(COLOR_7, getString(R.string.purple));
		uiTextMap.put(COLOR_8, getString(R.string.black));
		uiTextMap.put(COLOR_9, getString(R.string.coffee));		
		
		uiTextMap.put(POINT, getString(R.string.point));
		uiTextMap.put(LINE, getString(R.string.line));
		uiTextMap.put(CURVE, getString(R.string.curve));
		uiTextMap.put(SHAPE, getString(R.string.shape));
		
		uiTextMap.put(XING_ZHUANG_CIRCLE, getString(R.string.circle));
		uiTextMap.put(XING_ZHUANG_TRIGON, getString(R.string.trigon));
		uiTextMap.put(XING_ZHUANG_SQUARE, getString(R.string.square));
		uiTextMap.put(XING_ZHUANG_HEXAGON, getString(R.string.hexagon));
		uiTextMap.put(XING_ZHUANG_RECTANGLE, getString(R.string.rectangle));
		uiTextMap.put(XING_ZHUANG_SECTOR, getString(R.string.sector));
		uiTextMap.put(XING_ZHUANG_PARALLELOGRAM, getString(R.string.parallelogram));
		uiTextMap.put(XING_ZHUANG_DIAMOND, getString(R.string.diamond));
		uiTextMap.put(XING_ZHUANG_PENTAGON, getString(R.string.pentagon));
		
		uiTextMap.put(ZI_XUAN_XING_ZHUANG, getString(R.string.self_choose_single));
		uiTextMap.put(SUI_JI_XING_ZHUANG, getString(R.string.redom_single));
		
		uiParamValueMap = new HashMap<String, Integer>();
		uiParamValueMap.put(POINT, Constants.DisplayBody.POINT);
		uiParamValueMap.put(LINE, Constants.DisplayBody.LINE);
		uiParamValueMap.put(CURVE, Constants.DisplayBody.CURVE);
		uiParamValueMap.put(SHAPE, Constants.DisplayBody.SHAPES);
		
		uiParamValueMap.put(COLOR_1, Constants.Colors.RED);
		uiParamValueMap.put(COLOR_2, Constants.Colors.ORANGE);
		uiParamValueMap.put(COLOR_3, Constants.Colors.YELLOW);
		uiParamValueMap.put(COLOR_4, Constants.Colors.GREEN);
		uiParamValueMap.put(COLOR_5, Constants.Colors.BLUE);
		uiParamValueMap.put(COLOR_6, Constants.Colors.INDIGO);
		uiParamValueMap.put(COLOR_7, Constants.Colors.PURPLE);
		uiParamValueMap.put(COLOR_8, Constants.Colors.BLACK);
		uiParamValueMap.put(COLOR_9, Constants.Colors.COFFEE);		
		
		uiParamValueMap.put(XING_ZHUANG_CIRCLE, Constants.Shapes.CIRCLE);
		uiParamValueMap.put(XING_ZHUANG_TRIGON, Constants.Shapes.TRIGON);
		uiParamValueMap.put(XING_ZHUANG_SQUARE, Constants.Shapes.SQUARE);
		uiParamValueMap.put(XING_ZHUANG_HEXAGON, Constants.Shapes.HEXAGON);		
		uiParamValueMap.put(XING_ZHUANG_RECTANGLE, Constants.Shapes.RECTANGLE);
		uiParamValueMap.put(XING_ZHUANG_SECTOR, Constants.Shapes.SECTOR);
		uiParamValueMap.put(XING_ZHUANG_PARALLELOGRAM, Constants.Shapes.PARALLELOGRAM);
		uiParamValueMap.put(XING_ZHUANG_DIAMOND, Constants.Shapes.DIAMOND);
		uiParamValueMap.put(XING_ZHUANG_PENTAGON, Constants.Shapes.PENTAGON);
	}
}