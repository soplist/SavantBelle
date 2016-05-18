package com.flextronics.cn.activity.hearing.identification.scale;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.identification.ScaleIdentificationActivity;
import com.flextronics.cn.adapter.MusicalInstrumentsAdapter;
import com.flextronics.cn.adapter.ScaleAdapter;
import com.flextronics.cn.service.hearing.IScaleService;
import com.flextronics.cn.service.hearing.impl.ScaleService;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class ForeignMusicActivity extends BaseActivity{
	// 声明一个RelativeLayout对象
	private RelativeLayout baseLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout lineLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout displayLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout topFrameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout leftFrameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout rightFrameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout centerFrameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout bottomFrameLayout;
	// 声明一个GridView对象
	private GridView musicalInstrumentsListView;
	// 声明一个GridView对象
	private GridView scaleListView;
	//声明一个GridViewAdapter适配器
	private MusicalInstrumentsAdapter musicalInstrumentsAdapter;
	//声明一个GridViewAdapter适配器
	private ScaleAdapter scaleAdapter;
	//声明一个ImageView对象
	private ImageView detailView;
	//声明一个ImageView对象
	private ImageView playView;
	//声明一个Button对象
	private Button singleToneButton;
	//声明一个Button对象
	private Button continueToneButton;
	//声明一个Button对象
	private Button complexToneButton;
	//声明一个IMusicalInstrumentsService服务
	private IScaleService service;
	//声明一个int类型变量
	private int musicalInstrumentsType;
	//声明一个int类型变量
	private int scaleType;
	//声明一个int类型变量
	private int white=Color.WHITE;
	//声明一个int类型变量
	private int black=Color.BLACK;
	//声明一个boolean变量
	private boolean continueToneFlag;
	//声明一个boolean变量
	private boolean complexToneFlag;
	//声明一个List集合
	private List<Integer> scaleList = new ArrayList<Integer>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//把父类中已经定义好的值赋给baseLayout
		baseLayout=getBaseRelativeLayout();
		//获得View对象并赋给lineLayout对象
		lineLayout=(RelativeLayout) getBaseLayoutInflater().inflate(R.layout.included_line1, null);
		//实例化displayLayout对象
		displayLayout=new RelativeLayout(this);
		//设置displayLayout对象的Id属性
		displayLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//把lineLayout对象添加到baseLayout对象中
		baseLayout.addView(lineLayout, getBaseLayoutParams());
		//把displayLayout对象添加到baseLayout对象中
		baseLayout.addView(displayLayout, getBaseLayoutParams());
		//设置当前显示的视图为baseLayout对象
		setContentView(baseLayout);
		//实例化service对象
		service=new ScaleService();
		//调用service中的init方法
		service.init(this);
		//调用设置视图的方法
		setupView();
		//调用设置监听器的方法
		setupListener();
	}
	private void setupView(){
		//设置title的值
		setTrainingTitle(getString(R.string.hearing_system_identification_training_scale_foreign_music));
		//显示用户姓名
		setUserNameEnabled(true);
		//显示用户图片
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		//调用绘制TopFrameLayout方法
		drawTopFrameLayout();
		//调用绘制LeftFrameLayout方法
		drawLeftFrameLayout();
		//调用绘制RightFrameLayout方法
		drawRightFrameLayout();
		//调用绘制CenterFrameLayout方法
		drawCenterFrameLayout();
		//调用绘制BottomFrameLayout方法
		drawBottomFrameLayout();
		//调用绘制PlayButton方法
		drawPlayView();
		//不显示playView
		setPlayViewEnable(false);
		//默认选中singleToneButton
		setBackgroundDrawable(singleToneButton);
	}
	private void setupListener(){
		musicalInstrumentsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				musicalInstrumentsType=MusicalInstruments.ForeignMusicScale.ARRAY[position];
				detailView.setImageDrawable(getResources().getDrawable(musicalInstrumentsAdapter.getMusicalInstrumentsDetailImage()[position]));
				service.octaveService(musicalInstrumentsType);
				setPlayViewEnable(false);
				setBackgroundDrawable(singleToneButton);
				for (int i = 0; i < parent.getChildCount(); i++) {
					if (parent.getChildAt(i)==view) {
						parent.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_background_01));
					}else {
						parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
					}
				}
			}
		});
		scaleListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				scaleType=HearingConstants.Scale.ARRAY[position];
				scaleList.add(scaleType);
				service.monosyllabicService(musicalInstrumentsType, scaleType);
			}
		});
		singleToneButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					singleToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_down));
					setPlayViewEnable(false);
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					setBackgroundDrawable(singleToneButton);
				}
				return false;
			}
		});
		continueToneButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					continueToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_down));
					setPlayViewEnable(true);
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					setBackgroundDrawable(continueToneButton);
					selectFunction(continueToneButton);
				}
				return false;
			}
		});
		complexToneButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					complexToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_down));
					setPlayViewEnable(true);
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					setBackgroundDrawable(complexToneButton);
					selectFunction(complexToneButton);
				}
				return false;
			}
		});
		playView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					if (continueToneFlag) {
						service.continuousToneService(musicalInstrumentsType,scaleList);
					}else if (complexToneFlag) {
						service.complexToneService(musicalInstrumentsType,scaleList);
					}
					scaleList.clear();
				}
				return false;
			}
		});
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				Intent intent=new Intent();
				intent.setClass(getApplication(), ScaleIdentificationActivity.class);
				startActivity(intent);
				finish();
				return false;
			}
		});
	}
	
	/**
	 * 绘制页面上方的框架布局
	 */
	private void drawTopFrameLayout(){
		//设定Frame的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定Frame距上边距的距离为160dip
		params.topMargin=160;
		//设定Frame的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//实例化topFrameLayout对象
		topFrameLayout=new RelativeLayout(this);
		//给frameLayout对象设置唯一的ID号
		topFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_TWO);
		//给frameLayout设定背景图片
		topFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_02));
		//添加到displayLayout中显示
		displayLayout.addView(topFrameLayout, params);
	}
	
	private void drawLeftFrameLayout(){
		//设定Frame的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(280,285);
		//设定leftFrameLayout距上边距的距离为5dpi
		params.topMargin=5;
		//设定leftFrameLayout距左边距的距离为5dpi
		params.leftMargin=5;
		//设定leftFrameLayout的在父框架中的布局规则
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,HearingConstants.LayoutId.LAYOUT_ID_TWO);
		//设定imageView的大小
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定imageView距上边距的距离为5dip
		params2.topMargin=5;
		//设定imageView的在父框架中的布局规则
		params2.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_THREE);
		//实例化leftFrameLayout对象
		leftFrameLayout=new RelativeLayout(this);
		//给leftFrameLayout对象设置唯一的ID号
		leftFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_THREE);
		//实例化detailView对象
		detailView=new ImageView(this);
		//添加到leftFrameLayout中
		leftFrameLayout.addView(detailView, params2);
		//添加到frameLayout中
		topFrameLayout.addView(leftFrameLayout,params);
	}
	
	private void drawRightFrameLayout(){
		//设定rightFrameLayout的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(605,285);
		//设定rightFrameLayout距上边距的距离为5dip
		params.topMargin=5;
		//设定rightFrameLayout距左边距的距离为10dip
		params.leftMargin=10;
		//设定rightFrameLayout的在父框架中的布局规则
		params.addRule(RelativeLayout.RIGHT_OF,HearingConstants.LayoutId.LAYOUT_ID_THREE);
		//设定musicalInstrumentsListView的大小
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定imageView距上边距的距离为5dip
		params2.topMargin=5;
		//实例化rightFrameLayout对象
		rightFrameLayout=new RelativeLayout(this);
		//给leftFrameLayout对象设置唯一的ID号
		rightFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_FOUR);
		//实例化musicalInstrumentsListView对象
		musicalInstrumentsListView=new GridView(this);
		//设置选中的背景图片
		musicalInstrumentsListView.setSelector(getResources().getDrawable(R.drawable.hs_background_01));
		//在musicalInstrumentsListView中每行显示5个图标
		musicalInstrumentsListView.setNumColumns(5);
		//在musicalInstrumentsListView中每个图标上下的间距为10
		musicalInstrumentsListView.setVerticalSpacing(20);
		//在musicalInstrumentsListView中的图标都是剧中对齐
		musicalInstrumentsListView.setGravity(Gravity.CENTER);
		//实例化MusicalInstrumentsAdapter对象
		musicalInstrumentsAdapter=new MusicalInstrumentsAdapter(this,true,MusicalInstruments.ForeignMusicScale.ARRAY);
		//给musicalInstrumentsListView添加一个适配器
		musicalInstrumentsListView.setAdapter(musicalInstrumentsAdapter);
		//添加到rightFrameLayout中
		rightFrameLayout.addView(musicalInstrumentsListView, params2);
		//添加到frameLayout中
		topFrameLayout.addView(rightFrameLayout, params);
	}
	
	private void drawCenterFrameLayout(){
		//设定Frame的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定Frame距上边距的距离为10dpi
		params.topMargin=10;
		//设定Frame的在父框架中的布局规则
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_TWO);
		//设定Frame的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//设定scaleListView的大小
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(900,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定rightFrameLayout的在父框架中的布局规则
		params2.addRule(RelativeLayout.CENTER_IN_PARENT,HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		//实例化centerFrameLayout对象
		centerFrameLayout=new RelativeLayout(this);
		//给centerFrameLayout对象设置唯一的ID号
		centerFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		//给centerFrameLayout设定背景图片
		centerFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_03));
		//实例化scaleListView对象
		scaleListView=new GridView(this);
		
		scaleListView.setSelector(getResources().getDrawable(R.drawable.hs_background_00));
		//在scaleListView中每行显示5个图标
		scaleListView.setNumColumns(8);
		//在scaleListView中每个图标上下的间距为10
		scaleListView.setVerticalSpacing(10);
		//在scaleListView中的图标都是剧中对齐
		scaleListView.setGravity(Gravity.CENTER);
		//实例化scaleAdapter对象
		scaleAdapter=new ScaleAdapter(this,true,HearingConstants.Scale.ARRAY);
		//给musicalInstrumentsListView添加一个适配器
		scaleListView.setAdapter(scaleAdapter);
		//添加到rightFrameLayout中
		centerFrameLayout.addView(scaleListView, params2);
		//添加到displayLayout中显示
		displayLayout.addView(centerFrameLayout, params);
	}
	
	private void drawBottomFrameLayout(){
		//设定bottomFrameLayout的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定bottomFrameLayout距上边距的距离为10dpi
		params.topMargin=10;
		//设定bottomFrameLayout的在父框架中的布局规则
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_FIVE);
		//设定bottomFrameLayout的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//设定singleToneButton的大小
		RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params2.rightMargin=20;
		//设定singleToneButton的在父框架中的布局规则
		params2.addRule(RelativeLayout.LEFT_OF,HearingConstants.ViewId.VIEW_ID_TWO);
		//设定singleToneButton的在父框架中的布局规则
		params2.addRule(RelativeLayout.CENTER_IN_PARENT,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//设定continueToneButton的大小
		RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定continueToneButton的在父框架中的布局规则
		params3.addRule(RelativeLayout.CENTER_IN_PARENT,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//设定complexToneButton的大小
		RelativeLayout.LayoutParams params4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params4.leftMargin=20;
		//设定complexToneButton的在父框架中的布局规则
		params4.addRule(RelativeLayout.RIGHT_OF,HearingConstants.ViewId.VIEW_ID_TWO);
		//设定complexToneButton的在父框架中的布局规则
		params4.addRule(RelativeLayout.CENTER_IN_PARENT,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//实例化bottomFrameLayout对象
		bottomFrameLayout=new RelativeLayout(this);
		//给bottomFrameLayout对象设置唯一的ID号
		bottomFrameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//给bottomFrameLayout设定背景图片
		bottomFrameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_04));
		
		singleToneButton=new Button(this);
		singleToneButton.setId(HearingConstants.ViewId.VIEW_ID_ONE);
		singleToneButton.setText("单音");
		singleToneButton.setTextColor(black);
		singleToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
		
		
		continueToneButton=new Button(getApplication());
		continueToneButton.setId(HearingConstants.ViewId.VIEW_ID_TWO);
		continueToneButton.setText("连续音");
		continueToneButton.setTextColor(black);
		continueToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
		
		complexToneButton=new Button(getApplication());
		complexToneButton.setId(HearingConstants.ViewId.VIEW_ID_THREE);
		complexToneButton.setText("复合音");
		complexToneButton.setTextColor(black);
		complexToneButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
		
		bottomFrameLayout.addView(singleToneButton, params2);
		bottomFrameLayout.addView(continueToneButton, params3);
		bottomFrameLayout.addView(complexToneButton, params4);
		//添加到displayLayout中显示
		displayLayout.addView(bottomFrameLayout, params);
	}
	private void drawPlayView(){
		//设定playView的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定playView距上边距的距离为10dpi
		params.topMargin=10;
		//设定playView的在父框架中的布局规则
		params.addRule(RelativeLayout.BELOW,HearingConstants.LayoutId.LAYOUT_ID_SIX);
		//设定playView的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL,HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//实例化playView对象
		playView=new ImageView(this);
		//给playView设定图片
		playView.setImageDrawable(getResources().getDrawable(R.drawable.hs_play_button));
		//给playView设定点击状态为可用
		playView.setClickable(true);
		//添加到displayLayout中显示
		displayLayout.addView(playView, params);
	}
	
	private void setPlayViewEnable(boolean flag){
		if(flag){
			playView.setEnabled(true);
		}else {
			playView.setEnabled(false);
		}
	}
	
	private void setBackgroundDrawable(Button button){
		Button [] functionArray={singleToneButton,continueToneButton,complexToneButton};
		for (int i = 0; i < functionArray.length; i++) {
			if(button==functionArray[i]){
				functionArray[i].setTextColor(white);
				functionArray[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_up));
			}else {
				functionArray[i].setTextColor(black);
				functionArray[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_button_background_normal));
			}
		}
	}

	private void selectFunction(Button button){
		if(button==continueToneButton){
			continueToneFlag=true;
			complexToneFlag=false;
		}else if(button==complexToneButton){
			continueToneFlag=false;
			complexToneFlag=true;
		}else {
			
		}
		scaleList.clear();
	}
	protected void onStop() {
		service.stop();
		super.onStop();
	}
	
}