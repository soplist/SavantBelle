package com.flextronics.cn.activity;

import com.flextronics.cn.activity.color.ColorMemoryTrainingActivity;
import com.flextronics.cn.activity.hearing.memory.answer.MusicalInstrumentsMemoryAnswerActivity;
import com.flextronics.cn.activity.hearing.memory.answer.ScaleMemoryAnswerActivity;
import com.flextronics.cn.activity.spatial.SpatialMemoryTrainingActivity;
import com.flextronics.cn.activity.visiontouch.VtmtKeyStokeOperationActivity;
import com.flextronics.cn.activity.visiontouch.VtmtTouchScreenSampleElementOrderActivity;
import com.flextronics.cn.activity.visiontouch.VtmtTouchScreenSampleLocationOrderActivity;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.model.BaseMemoryTrainingReport;
import com.flextronics.cn.util.Constants;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**<b>显示位元类型为连续位元的记忆训练所得测试报告的Activity</b><br>
 * 使用此activity需传入测试报告,测试报告需为BaseMemoryTrainingReport或其子类
 * 
 * @author ZhangGuoYin
 *
 */
public class TestReportWithTableActivity extends BaseActivity {

	private final static String TAG = "TestReportWithTableActivity";
	
	/**
	 * 以文字形式显示测试结果,分数,错误(正确)题数
	 */
	private TextView textView1;
	/**
	 * 再做一次
	 */
	private Button button_again;
	/**
	 * 以表格形式显示具体每道题的正确与否
	 */
	private GridView gridView;
	
	private Bundle bundle;	

	/**
	 * 左下角的图片
	 */
	private ImageView leftBottomImageView;
	/**
	 * 左下角的文字说明之上半部
	 */
	private TextView leftBottomTextView1;
	/**
	 * 左下角的文字说明之中间分割线
	 */
	private TextView leftBottomTextView2;
	/**
	 * 左下角的文字说明之下半部
	 */
	private TextView leftBottomTextView3;
	
	/**
	 * 创建数据库访问对象
	 */
	private AnswerQuestionDao answerQuestionDao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		//接收测试报告数据
		bundle = this.getIntent().getExtras();
		
		//从父类中获得基本布局
		RelativeLayout baseLayout = getBaseRelativeLayout();
		//将test_report_with_table.xml中的描述的布局添加到基本布局中		
		baseLayout.addView(
				(RelativeLayout)getBaseLayoutInflater().inflate(R.layout.test_report_with_table, null), 
				getBaseLayoutParams());
		//将基本布局设置为当前activity的显示内容
		setContentView(baseLayout);		
		
		setupViews();
		setupListeners();
		
		//强制转换为BaseMemoryTrainingReport
		BaseMemoryTrainingReport testReport = 
			(BaseMemoryTrainingReport)this.getIntent().getSerializableExtra(Constants.TEST_REPORT);
		
		//组织测试报告文字描述内容
		String str = "";
		str = this.getString(R.string.rightCount2)+testReport.getRightCount()+
		"  "+this.getString(R.string.wrongCount2)+testReport.getErrorCount()+
		"  "+this.getString(R.string.score)+testReport.getScores()+
		"  ";
		//将以上内容进行显示
		textView1.setText(str);
		
		//实例化数据库访问对象
		answerQuestionDao = new AnswerQuestionDao(this);
		//从数据库中取得数据并为grid_view构造适配器
		Cursor cursor = answerQuestionDao.getAnswerQuestionByTestingId(testReport.getTestingId());
		
		gridView.setAdapter(new MyAdapter(this, cursor));
	}
	
	
	/**
	 * 设置此activity中的views
	 */
	private void setupViews(){
		
		//显示用户名
		setUserNameEnabled(true);
		//显示用户头像
		setUserIconEnable(true);
		//不显示右下角取消按钮
		setCancelButtonEnable(false);
		//不显示右下角确定按钮
		setOkButtonEnable(false);	
		//不显示训练标题
		setTrainingTitle(null);	

		leftBottomImageView = (ImageView)findViewById(R.id.ImageView_left_bottom_1);
		leftBottomTextView1 = (TextView)findViewById(R.id.TextView_left_bottom_1);
		leftBottomTextView2 = (TextView)findViewById(R.id.TextView_left_bottom_2);
		leftBottomTextView3 = (TextView)findViewById(R.id.TextView_left_bottom_3);
		leftBottomImageView.setImageResource(R.drawable.menu1);
		
		if (bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleLocationOrderActivity.class.getName()) ||
				bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleElementOrderActivity.class.getName())) {
			
			//如果是从A-视觉触觉记忆训练触摸屏操作跳转至此
			leftBottomTextView1.setText(R.string.Menu_A);
			//分割线
			leftBottomTextView2.setText(R.string.left_bottom_line_vtmt_touch);
			//视觉触觉记忆训练触摸屏操作
			leftBottomTextView3.setText(R.string.vision_touch_memory_training_touch_screen);		
			//视觉听觉触觉综合训练图标
			leftBottomImageView.setImageResource(R.drawable.menu1);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(VtmtKeyStokeOperationActivity.class.getName())) {
			
			//如果是从A-视觉触觉记忆训练控制键操作跳转至此
			leftBottomTextView1.setText(R.string.Menu_A);
			//分割线
			leftBottomTextView2.setText(R.string.left_bottom_line_vtmt_touch);
			//视觉触觉记忆训练控制键操作
			leftBottomTextView3.setText(R.string.vision_touch_memory_training_key);		
			//视觉听觉触觉综合训练图标
			leftBottomImageView.setImageResource(R.drawable.menu1);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ColorMemoryTrainingActivity.class.getName())) {
			
			//如果是从C-颜色辨识记忆训练跳转至此
			leftBottomTextView1.setText(R.string.Menu_C);
			//分割线
			leftBottomTextView2.setText(R.string.leftBottomLine4Color);
			//颜色记忆训练
			leftBottomTextView3.setText(R.string.color_memory_training);		
			//颜色记忆训练图片
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
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(MusicalInstrumentsMemoryAnswerActivity.class.getName())) {
			
			leftBottomTextView1.setText(R.string.Menu_B);
			
			leftBottomTextView3.setText(R.string.hearing_system_memory_training_musical_instruments);		
			
			leftBottomImageView.setImageResource(R.drawable.menu2);
			
		}else if (bundle.getString(Constants.CLASS_NAME).equals(ScaleMemoryAnswerActivity.class.getName())) {
			
			leftBottomTextView1.setText(R.string.Menu_B);
			
			leftBottomTextView3.setText(R.string.hearing_system_memory_training_scale);		
			
			leftBottomImageView.setImageResource(R.drawable.menu2);
			
		}
		
		textView1 = (TextView)this.findViewById(R.id.TextView_TestResult);		
		button_again = (Button)findViewById(R.id.ButtonDoAgain);
		
		gridView = (GridView)findViewById(R.id.GridView01);
		//gridView.setNumColumns(5);
	}
	
	
	/**
	 * 为此activity的views设置监听程序
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
					if (bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleLocationOrderActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(VtmtTouchScreenSampleElementOrderActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(VtmtKeyStokeOperationActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(ColorMemoryTrainingActivity.class.getName())||
							bundle.getString(Constants.CLASS_NAME).equals(SpatialMemoryTrainingActivity.class.getName())) {
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

		button_again.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				
				try {
					@SuppressWarnings("rawtypes")
					Class class1 = Class.forName(bundle.getString(Constants.CLASS_NAME));
					bundle.remove(Constants.CLASS_NAME);
					Intent intent = new Intent(TestReportWithTableActivity.this, class1);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				} catch (ClassNotFoundException e) {
					Log.e(TAG, "ClassNotFoundException");
					e.printStackTrace();
				}finally{
					finish();	
				}
			}			
		});
	}

	class MyAdapter extends CursorAdapter{
		
		private Context mContext;

		public MyAdapter(Context context, Cursor c) {
			super(context, c);
			mContext = context;
		}

		@Override
		public void bindView(View paramView, Context paramContext,
				Cursor paramCursor) {
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {			
			
			Cursor cursor = getCursor();
			cursor.moveToPosition(position);
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			LinearLayout layout = (LinearLayout)
				layoutInflater.inflate(R.layout.test_report_table_item, null);
			TextView textView = (TextView)layout.findViewById(R.id.TextView01);
			textView.setText(cursor.getString(
					cursor.getColumnIndex(AnswerQuestionDao.ANSWER_QUESTION_QUESTION_ID)));
			ImageView imageView = (ImageView)layout.findViewById(R.id.ImageView01);
			String resultStr = cursor.getString(
					cursor.getColumnIndex(AnswerQuestionDao.ANSWER_QUESTION_ANSWER_RESULT));
			imageView.setImageResource(resultStr.equals("1")?R.drawable.is_right:R.drawable.is_wrong);
			return layout;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		answerQuestionDao.close();
		answerQuestionDao = null;
	}
}
