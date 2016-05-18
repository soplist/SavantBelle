package com.flextronics.cn.activity;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;
import com.flextronics.cn.activity.color.ColorMemoryTrainingActivity;
import com.flextronics.cn.activity.color.ColorResponseTrainingActivity;
import com.flextronics.cn.activity.hearing.memory.answer.MusicalInstrumentsMemoryAnswerActivity;
import com.flextronics.cn.activity.hearing.memory.answer.RhythmMemoryAnswerActivity;
import com.flextronics.cn.activity.hearing.memory.answer.ScaleMemoryAnswerActivity;
import com.flextronics.cn.activity.hearing.response.answer.MusicalInstrumentsResponseAnswerActivity;
import com.flextronics.cn.activity.hearing.response.answer.RhythmResponseAnswerActivity;
import com.flextronics.cn.activity.hearing.response.answer.ScaleResponseAnswerActivity;
import com.flextronics.cn.activity.hearingtouch.HtmtSampleActivity;
import com.flextronics.cn.activity.hearingtouch.HtrtSampleActivity;
import com.flextronics.cn.activity.spatial.SpatialMemoryTrainingActivity;
import com.flextronics.cn.activity.symboltraining.SymbolMemeryTrainingActivity;
import com.flextronics.cn.activity.symboltraining.SymbolResponseTrainingActivity;
import com.flextronics.cn.activity.visiontouch.VtmtKeyStokeOperationActivity;
import com.flextronics.cn.activity.visiontouch.VtmtTouchScreenSampleElementOrderActivity;
import com.flextronics.cn.activity.visiontouch.VtmtTouchScreenSampleLocationOrderActivity;
import com.flextronics.cn.activity.visiontouch.VtrtKeyStokeOperationActivity;
import com.flextronics.cn.activity.visiontouch.VtrtTouchScreenOperationActivity;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingReport;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingReport;
import com.flextronics.cn.model.hearing.memory.MemoryReport;
import com.flextronics.cn.model.hearing.response.ResponseReport;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtReport;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolMemeryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolResponseTrainingReport;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtReport;
import com.flextronics.cn.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**显示测试结果的activity
 * 
 * @author ZhangGuoYin
 *
 */
public class TestReportActivity extends BaseActivity {
	
	private final static String TAG = "TestReportActivity";
	private TextView textView1;
	/**
	 * 再来一次的按钮
	 */
	private Button button_again;
	private Bundle bundle;
	private GraphicalView mView;
	private AbstractChart mChart;

	private ImageView leftBottomImageView;
	private TextView leftBottomTextView1;
	private TextView leftBottomTextView2;
	private TextView leftBottomTextView3;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);		

		RelativeLayout baseLayout = getBaseRelativeLayout();		
		String str = "";		
		bundle = this.getIntent().getExtras();
		
		if (bundle.getString(Constants.CLASS_NAME).equals(VtrtTouchScreenOperationActivity.class.getName()) || 
				bundle.getString(Constants.CLASS_NAME).equals(VtrtKeyStokeOperationActivity.class.getName())) {
			//如果是从A-视觉触觉反应训练跳转至此
			VtrtReport testReport = (VtrtReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleLocationOrderActivity.class.getName()) || 
				bundle.getString(Constants.CLASS_NAME).equals(VtmtKeyStokeOperationActivity.class.getName()) ||
				bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleElementOrderActivity.class.getName())) {
			//如果是从A-视觉触觉记忆训练跳转至此
			VtmtReport testReport = (VtmtReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(HtrtSampleActivity.class.getName())) {
			//如果是从A-听觉触觉反应训练跳转至此
			HtrtReport testReport = 
				(HtrtReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(HtmtSampleActivity.class.getName())) {
			//如果是从A-听觉触觉记忆训练跳转至此
			HtmtReport testReport = (HtmtReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsResponseAnswerActivity.class.getName())) {
			ResponseReport testReport = (ResponseReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ScaleResponseAnswerActivity.class.getName())) {
			ResponseReport testReport = (ResponseReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(RhythmResponseAnswerActivity.class.getName())) {
			ResponseReport testReport = (ResponseReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsMemoryAnswerActivity.class.getName())) {
			MemoryReport testReport = (MemoryReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ScaleMemoryAnswerActivity.class.getName())) {
			MemoryReport testReport = (MemoryReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(RhythmMemoryAnswerActivity.class.getName())) {
			MemoryReport testReport = (MemoryReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ColorResponseTrainingActivity.class.getName())) {
			//如果是从C-颜色反应训练跳转至此
			ColorResponseTrainingReport testReport = 
				(ColorResponseTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ColorMemoryTrainingActivity.class.getName())) {
			//如果是从C-颜色记忆训练跳转至此
			ColorMemoryTrainingReport testReport = 
				(ColorMemoryTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SpatialMemoryTrainingActivity.class.getName())) {
			
			//如果是从E-空间位置记忆训练跳转至此
			SpatialMemoryTrainingReport testReport = 
				(SpatialMemoryTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SymbolMemeryTrainingActivity.class.getName())) {
			
			//D-符号位置记忆训练
			SymbolMemeryTrainingReport testReport = 
				(SymbolMemeryTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			Log.d(TAG,"mark mChart 1");
			this.mChart = ((AbstractChart)testReport.getChart());
			Log.d(TAG,"mark mChart 2");
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SymbolResponseTrainingActivity.class.getName())) {
			
			//D-符号位置反应训练
			SymbolResponseTrainingReport testReport = 
				(SymbolResponseTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
			this.mChart = ((AbstractChart)testReport.getChart());
			str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
			"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
			"  "+this.getString(R.string.score)+testReport.getScores()+
			"  ";
		}
			
		//将test_report描绘成布局
		RelativeLayout layout = (RelativeLayout)getBaseLayoutInflater().inflate(R.layout.test_report, null);
		//柱状图		
		this.mView = new GraphicalView(this, this.mChart);
		mView.setLayoutParams(new LinearLayout.LayoutParams(210, 280));
		LinearLayout chartLinearLayout = (LinearLayout)layout.findViewById(R.id.LinearLayout_Chart);
		chartLinearLayout.addView(mView);
		baseLayout.addView(layout, getBaseLayoutParams());		
		//设置为当前activity的content view
		setContentView(baseLayout);
		
		textView1 = (TextView)this.findViewById(R.id.TextView_TestResult);		
		button_again = (Button)findViewById(R.id.ButtonDoAgain);
		textView1.setText(str);

		button_again.setOnClickListener(new Button.OnClickListener(){

			@SuppressWarnings("unchecked")
			public void onClick(View arg0) {
				
				try {
					Class class1 = Class.forName(bundle.getString(Constants.CLASS_NAME));
					bundle.remove(Constants.CLASS_NAME);
					Intent intent = new Intent(TestReportActivity.this, class1);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();	
				} catch (ClassNotFoundException e) {
					Log.e(TAG, "ClassNotFoundException");
					e.printStackTrace();
				}
			}			
		});
		
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
		
		//左下角图片
		leftBottomImageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		//左下角文字说明部分的上半部分
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		//左下角文字说明部分的中间部分
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		//左下角文字说明部分的下半部分
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		
		leftBottomImageView.setImageResource(R.drawable.menu1);
		
		if (bundle.getString(Constants.CLASS_NAME).equals(VtrtTouchScreenOperationActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.vision_touch_response_training_touch_screen);			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(VtrtKeyStokeOperationActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.vision_touch_response_training_key);		
		}else if (bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleLocationOrderActivity.class.getName())||
				bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleElementOrderActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.vision_touch_memory_training_touch_screen);		
		}else if (bundle.getString(Constants.CLASS_NAME).equals(VtmtKeyStokeOperationActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.vision_touch_memory_training_key);		
		}else if (bundle.getString(Constants.CLASS_NAME).equals(HtrtSampleActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.hearing_touch_response_training_key);		
		}else if (bundle.getString(Constants.CLASS_NAME).equals(HtmtSampleActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_A);
			leftBottomTextView3.setText(R.string.hearing_touch_memory_training_key);		
		}else if (bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsResponseAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_response_training_musical_instruments);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ScaleResponseAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_response_training_scale);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(RhythmResponseAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_response_training_rhythm);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsMemoryAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_memory_training_musical_instruments);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ScaleMemoryAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_memory_training_scale);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(RhythmMemoryAnswerActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_B);
			leftBottomTextView3.setText(R.string.hearing_system_memory_training_rhythm);		
			leftBottomImageView.setImageResource(R.drawable.menu2);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ColorResponseTrainingActivity.class.getName())) {
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_C);
			leftBottomTextView2.setText(R.string.leftBottomLine4Color);
			leftBottomTextView3.setText(R.string.color_response_training);		
			leftBottomImageView.setImageResource(R.drawable.menu3);
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ColorMemoryTrainingActivity.class.getName())) {
			
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_C);
			leftBottomTextView2.setText(R.string.leftBottomLine4Color);
			leftBottomTextView3.setText(R.string.color_memory_training);		
			leftBottomImageView.setImageResource(R.drawable.menu3);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SpatialMemoryTrainingActivity.class.getName())) {	
			
			//如果是从E-空间位置记忆训练跳转至此
			//测试模块名
			leftBottomTextView1.setText(R.string.Menu_E);
			//分割线
			leftBottomTextView2.setText(R.string.left_bottom_line_spatial);
			//空间位置|记忆训练
			leftBottomTextView3.setText(R.string.spatial_memory_training);		
			//空间位置记忆训练图片
			leftBottomImageView.setImageResource(R.drawable.menu5);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SymbolMemeryTrainingActivity.class.getName())) {
			
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_D);
			leftBottomTextView2.setText(R.string.leftBottomLine4Color);
			leftBottomTextView3.setText(R.string.symbol_memory_training);		
			leftBottomImageView.setImageResource(R.drawable.menu4);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(SymbolResponseTrainingActivity.class.getName())) {
			
			setTrainingTitle(null);
			leftBottomTextView1.setText(R.string.Menu_D);
			leftBottomTextView2.setText(R.string.leftBottomLine4Color);
			leftBottomTextView3.setText(R.string.symbol_response_training);		
			leftBottomImageView.setImageResource(R.drawable.menu4);
			
		}
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
					if (bundle.getString(Constants.CLASS_NAME).equals(VtrtTouchScreenOperationActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(VtrtKeyStokeOperationActivity.class.getName()) || 
							bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleLocationOrderActivity.class.getName()) || 
							bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleElementOrderActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(VtmtKeyStokeOperationActivity.class.getName()) || 
							bundle.getString(Constants.CLASS_NAME).equals(ColorMemoryTrainingActivity.class.getName()) || 
							bundle.getString(Constants.CLASS_NAME).equals(ColorResponseTrainingActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(SpatialMemoryTrainingActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(HtrtSampleActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsResponseAnswerActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(ScaleResponseAnswerActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(RhythmResponseAnswerActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsMemoryAnswerActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(ScaleMemoryAnswerActivity.class.getName()) ||
							bundle.getString(Constants.CLASS_NAME).equals(RhythmMemoryAnswerActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(SymbolMemeryTrainingActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(SymbolResponseTrainingActivity.class.getName())) {
						try {
							startActivity(new Intent(getApplicationContext(), 
									Class.forName(bundle.getString(Constants.PREVIOUS_ACTIVITY))));
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					finish();				
				}
				return false;
			}
		});		
	}
}
