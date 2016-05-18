package com.flextronics.cn.activity.visionhearingtouch;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.visionhearingtouch.VHTReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtReport;
import com.flextronics.cn.util.Constants;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**专用于视觉听觉触觉综合训练显示测试结果的activity
 * 
 * @author ZhangGuoYin
 *
 */
public class VhtIntegrationTrainingTestReportActivity extends BaseActivity {
	
	private final static String TAG = "VhtIntegrationTrainingTestReportActivity";
	private Button button_again;
	private Bundle bundle;
	private GraphicalView mView1;
	private GraphicalView mView2;
	private AbstractChart mChart1;
	private AbstractChart mChart2;

	private ImageView leftBottomImageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView2;
	private TextView leftBottomTextView3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		String vtrtResultStr = "";
		String htrtResultStr = "";
		
		bundle = this.getIntent().getExtras();
		VHTReport testReport = (VHTReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
		VtrtReport vtrtReport = testReport.getVtrtReport();
		HtrtReport htrtReport = testReport.getHtrtReport();		

		htrtResultStr = "  "+this.getString(R.string.rightCount)+htrtReport.getRightCount()+
		"\n  "+this.getString(R.string.wrongCount)+htrtReport.getErrorCount()+
		"\n  "+this.getString(R.string.score)+htrtReport.getScores();		
		vtrtResultStr = "  "+this.getString(R.string.rightCount)+vtrtReport.getRightCount()+
		"\n  "+this.getString(R.string.wrongCount)+vtrtReport.getErrorCount()+
		"\n  "+this.getString(R.string.score)+vtrtReport.getScores();
		
		//从BaseActivity中取得父布局
		RelativeLayout _layout = getBaseRelativeLayout();		
		RelativeLayout layout = (RelativeLayout)getBaseLayoutInflater().inflate(R.layout.test_report, null);
		
		LinearLayout motherlayout = new LinearLayout(this);
		motherlayout.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout vtrtlayout = new LinearLayout(this);
		vtrtlayout.setOrientation(LinearLayout.VERTICAL);
		TextView vtrtTitle = new TextView(this);
		vtrtTitle.setText(R.string.visionTouchResult);
		vtrtTitle.setTextColor(Color.WHITE);		
		TextView vtrtResult = new TextView(this);
		vtrtResult.setText(vtrtResultStr);
		vtrtResult.setTextColor(Color.WHITE);
		this.mChart1 = ((AbstractChart)vtrtReport.getChart());
		this.mView1 = new GraphicalView(this, this.mChart1);
		mView1.setLayoutParams(new LinearLayout.LayoutParams(210, 300));
		vtrtlayout.addView(vtrtTitle);
		vtrtlayout.addView(vtrtResult);
		vtrtlayout.addView(mView1);
		
		LinearLayout htrtlayout = new LinearLayout(this);
		htrtlayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(20, 0, 0, 0);
		htrtlayout.setLayoutParams(layoutParams);
		TextView htrtTitle = new TextView(this);
		htrtTitle.setText(R.string.hearingTouchResult);
		htrtTitle.setTextColor(Color.WHITE);
		TextView htrtResult = new TextView(this);
		htrtResult.setText(htrtResultStr);
		htrtResult.setTextColor(Color.WHITE);
		this.mChart2 = ((AbstractChart)htrtReport.getChart());
		this.mView2 = new GraphicalView(this, this.mChart2);
		mView2.setLayoutParams(new LinearLayout.LayoutParams(210, 300));
		htrtlayout.addView(htrtTitle);
		htrtlayout.addView(htrtResult);
		htrtlayout.addView(mView2);
		
		motherlayout.addView(vtrtlayout);
		motherlayout.addView(htrtlayout);
		
		LinearLayout chartLinearLayout = (LinearLayout)layout.findViewById(R.id.LinearLayout_Chart);
		chartLinearLayout.addView(motherlayout);
		
		_layout.addView(layout, getBaseLayoutParams());
		this.setContentView(_layout);
		
		setupViews();
		setupListeners();
	}
	
	private void setupViews(){
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像
		setUserIconEnable(true);
		//不显示取消按钮
		setCancelButtonEnable(false);
		//不显示确定按钮
		setOkButtonEnable(false);
		
		setTrainingTitle(null);

		button_again = (Button)findViewById(R.id.ButtonDoAgain);
		//左下角图片
		leftBottomImageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		//左下角文字说明部分的上半部分
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		//左下角文字说明部分的中间部分
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		//左下角文字说明部分的下半部分
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		
		leftBottomImageView.setImageResource(R.drawable.menu1);
		leftBottomTextView1.setText(R.string.Menu_A);
		leftBottomTextView3.setText(R.string.vision_hearing_touch_integration_training);
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
					startActivity(new Intent(getApplicationContext(), VhtIntegrationTrainingChooseParametersActivity1.class));
					finish();
				}
				return false;
			}
		});		

		button_again.setOnClickListener(new Button.OnClickListener(){

			@SuppressWarnings("unchecked")
			public void onClick(View arg0) {
				
				try {
					Class class1 = Class.forName(bundle.getString(Constants.CLASS_NAME));
					bundle.remove(Constants.CLASS_NAME);
					Intent intent = new Intent(VhtIntegrationTrainingTestReportActivity.this, class1);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();	
				} catch (ClassNotFoundException e) {
					Log.e(TAG, "ClassNotFoundException");
					e.printStackTrace();
				}
			}			
		});
	}
}
