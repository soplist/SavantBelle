package com.flextronics.cn.activity.stabilitytraining;

import org.achartengine.GraphicalView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.chart.FingerStabilityTrainingReportChart;
import com.flextronics.cn.ui.DrawView;

public class TrainingResultActivity extends BaseActivity{
	String TAG = "TrainingResultActivity";
	
    private RelativeLayout baseLayout;
	
	private RelativeLayout lineLayout;
	
	private RelativeLayout displayLayout;
	
	private DrawView drawView;
	
	private RelativeLayout.LayoutParams params;
	private RelativeLayout.LayoutParams param0;
	private RelativeLayout.LayoutParams param1;
	private RelativeLayout.LayoutParams param2;
	
	private Chronometer chronometer;
	
	private TextView textView;
	
	private FingerStabilityTrainingReportChart chart;
	private GraphicalView graphicalView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseLayout = getBaseRelativeLayout();
        lineLayout = (RelativeLayout) getBaseLayoutInflater().inflate(R.layout.stability_training_result, null);
        displayLayout = new RelativeLayout(this);
        
        baseLayout.addView(lineLayout, getBaseLayoutParams());
        baseLayout.addView(displayLayout, getBaseLayoutParams());
        
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        int traNo = bundle.getInt("TraNo");
        Log.d(TAG, "get trajectory no is "+traNo);
        
        drawView = DrawView.getDrawViewInstance(this);
        drawView.drawErrorPlace();
        drawView.setDrawPathSwitchOff();
        chronometer = drawView.getChronometer();
        chart = new FingerStabilityTrainingReportChart(this);
        graphicalView = chart.execute(this,drawView.pc.correctPoints.size(),drawView.pc.errorPoints.size());
        
        setContentView(baseLayout);
        setContentView(baseLayout);
        setupViews();
        setupListeners();
    }

	private void setupViews(){
		setTrainingTitle(getString(R.string.result_display));
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(true);
		
		textView = new TextView(this);
		textView.setText("画图终端次数:"+drawView.getIntermittent());
		textView.setTextColor(android.graphics.Color.WHITE);
		params=new RelativeLayout.LayoutParams(580,550);
		param0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		param1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		param2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		//距离左边距80dip
		params.leftMargin=100;
		param0.leftMargin=740;
		param1.leftMargin=740;
		param2.leftMargin=700;
		param2.height=200;
		//距离上边距170dip
		params.topMargin=150;
		param0.topMargin=560;
		param1.topMargin=520;
		param2.topMargin=290;
		param2.width=200;
		
		displayLayout.addView(drawView,params);
		displayLayout.addView(chronometer,param0);
		displayLayout.addView(textView,param1);
		displayLayout.addView(graphicalView, param2);
	}
	private void setupListeners(){
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
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		displayLayout.removeView(drawView);
		displayLayout.removeView(chronometer);
	}
	@Override
	public void onBackPressed() {
	super.onBackPressed();
	displayLayout.removeView(drawView);
	displayLayout.removeView(chronometer);
	return;
	}
	@Override
	public void onRestart(){
	super.onRestart();
	displayLayout.addView(drawView, params);
	displayLayout.addView(chronometer,param0);
	}
}
