package com.flextronics.cn.activity.hearing.identification.musicalinstruments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.activity.hearing.identification.MusicalInstrumentsIdentificationActivity;
import com.flextronics.cn.adapter.MusicalInstrumentsAdapter;
import com.flextronics.cn.service.hearing.IMusicalInstrumentsService;
import com.flextronics.cn.service.hearing.impl.MusicalInstrumentsService;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;

public class ForeignMusicActivity extends BaseActivity {
	// 声明一个RelativeLayout对象
	private RelativeLayout baseLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout lineLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout displayLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout frameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout leftFrameLayout;
	// 声明一个RelativeLayout对象
	private RelativeLayout rightFrameLayout;
	// 声明一个GridView对象
	private GridView musicalInstrumentsListView;
	//声明一个GridViewAdapter适配器
	private MusicalInstrumentsAdapter adapter;
	//声明一个ImageView适配器
	private ImageView detailView;
	//声明一个IMusicalInstrumentsService服务
	private IMusicalInstrumentsService service;
	//声明一个int类型变量
	private int musicalInstrumentsType;
	
	protected void onCreate(Bundle savedInstanceState) {
		//继承父类的onCreate方法
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
		service=new MusicalInstrumentsService();
		//调用service中的init方法
		service.init(this);
		//调用设置视图的方法
		setupView();
		//调用设置监听器的方法
		setupListener();
	}
	/**
	 * 设置显示当前视图要显示的内容
	 */
	private void setupView(){
		//设置title的值
		setTrainingTitle(getString(R.string.hearing_system_identification_training_musical_instruments_foreign_music));
		//显示用户姓名
		setUserNameEnabled(true);
		//显示用户图片
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		//调用绘制Frame方法
		drawFrameLayout();
		//调用绘制LeftFrameLayout方法
		drawLeftFrameLayout();
		//调用绘制RightFrameLayout方法
		drawRightFrameLayout();
	}
	/**
	 * 设置当前页面中图标的点击事件
	 */
	private void setupListener(){
		//设置点击GridView中的事件
		musicalInstrumentsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				musicalInstrumentsType=MusicalInstruments.ForeignMusicScale.ARRAY[position];
				detailView.setImageDrawable(getResources().getDrawable(adapter.getMusicalInstrumentsDetailImage()[position]));
				service.Melody(musicalInstrumentsType);
				for (int i = 0; i < parent.getChildCount(); i++) {
					if (parent.getChildAt(i)==view) {
						parent.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_background_01));
					}else {
						parent.getChildAt(i).setBackgroundDrawable(null);
					}
				}
			}
		});
		//设置点击Back按钮时，返回到HearingActivity
		setOnBackButtonTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_UP){
					startActivity(new Intent(ForeignMusicActivity.this,MusicalInstrumentsIdentificationActivity.class));
					adapter.clear();
					finish();
				}
				return false;
			}
		});
	}
	
	/**
	 * 绘制框架
	 */
	private void drawFrameLayout(){
		//设定Frame的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//设定Frame距上边距的距离为160dip
		params.topMargin=160;
		//设定Frame的在父框架中的布局规则
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, HearingConstants.LayoutId.LAYOUT_ID_ONE);
		//实例化frameLayout对象
		frameLayout=new RelativeLayout(this);
		//给frameLayout对象设置唯一的ID号
		frameLayout.setId(HearingConstants.LayoutId.LAYOUT_ID_TWO);
		//给frameLayout设定背景图片
		frameLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hs_frame_01));
		//添加到displayLayout中显示
		displayLayout.addView(frameLayout, params);
	}
	/**
	 * 绘制LeftFrameLayout
	 */
	private void drawLeftFrameLayout(){
		//设定leftFrameLayout的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(280,590);
		//设定leftFrameLayout距上边距的距离为5dip
		params.topMargin=5;
		//设定leftFrameLayout距左边距的距离为5dip
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
		frameLayout.addView(leftFrameLayout,params);
	}
	/**
	 * 绘制RightFrameLayout
	 */
	private void drawRightFrameLayout(){
		//设定rightFrameLayout的大小
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(605,590);
		//设定rightFrameLayout距上边距的距离为5dip
		params.topMargin=5;
		//设定rightFrameLayout距左边距的距离为10dip
		params.leftMargin=10;
		//设定rightFrameLayout的在父框架中的布局规则
		params.addRule(RelativeLayout.RIGHT_OF,HearingConstants.LayoutId.LAYOUT_ID_THREE);
		//声明一个RelativeLayout.LayoutParams对象
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
		musicalInstrumentsListView.setSelector(getResources().getDrawable(R.drawable.hs_background_00));
		//在musicalInstrumentsListView中每行显示5个图标
		musicalInstrumentsListView.setNumColumns(5);
		//在musicalInstrumentsListView中每个图标上下的间距为10
		musicalInstrumentsListView.setVerticalSpacing(10);
		//在musicalInstrumentsListView中的图标都是剧中对齐
		musicalInstrumentsListView.setGravity(Gravity.CENTER);
		//实例化MusicalInstrumentsAdapter对象
		adapter=new MusicalInstrumentsAdapter(this,true,MusicalInstruments.ForeignMusicScale.ARRAY);
		//给musicalInstrumentsListView添加一个适配器
		musicalInstrumentsListView.setAdapter(adapter);
		//添加到rightFrameLayout中
		rightFrameLayout.addView(musicalInstrumentsListView, params2);
		//添加到frameLayout中
		frameLayout.addView(rightFrameLayout, params);
	}
	/**
	 * 当屏幕完全失去焦点的时候，执行当前Activity的onStop停止service
	 */
	protected void onStop() {
		service.stop();
		super.onStop();
	}
}
