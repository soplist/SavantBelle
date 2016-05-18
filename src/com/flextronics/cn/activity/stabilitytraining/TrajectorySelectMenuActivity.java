package com.flextronics.cn.activity.stabilitytraining;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.DrawView;
import com.flextronics.cn.util.Mathematics;
import com.flextronics.cn.util.Point;

public class TrajectorySelectMenuActivity  extends BaseActivity {
	
	public static final String TAG="TrajectorySelectMenuActivity";
	//设置100种轨迹可供选择
	private static final int totalTraNum = 100;
	//当前页
	private int currentPage = 1;
	//每页可供选择轨迹数
	private int traPerPageNum = 8;
	//计算共有多少页
	private int totalPage = Mathematics.ceilAfterDivide(totalTraNum,traPerPageNum);
	//已选择轨迹
	private int choosedTra = 0;
	
	private TableLayout tableLayout;
	private ImageView imageView;
	//下一页按钮
	private Button button01;
	//上一页按钮
	private Button button02;
	private Bundle bundle;

	private ArrayList<ImageView> imageViews;
	private TextView commentsTextView;
	private String[] options;
	private Animation animation;
	
	private void setUpPageImageView(int page){
		imageViews = new ArrayList<ImageView>();
		//imageView计数
		int count = 0;
		//装载ImageView,个数为,若当前页不为最后一页,装载本页的规定数量个ImageView,若为最后一页装载取余后余下的ImageView.
		if(page!=totalPage){
			for(int i = (page-1)*traPerPageNum+1;i<=(page-1)*traPerPageNum+traPerPageNum;i++){
				 Log.d(TAG, "show trajectory no:"+i);
				 Log.d(TAG, "show total page:"+totalPage);
				 imageView = new ImageView(this);
				 imageViews.add(imageView);
				 count++;
			}
		}else{//page==totalPage
			for(int i = (page-1)*traPerPageNum+1;i<=(page-1)*traPerPageNum+totalTraNum%traPerPageNum;i++){
				 Log.d(TAG, "show trajectory no:"+i);
				 Log.d(TAG, "show total page:"+totalPage);
				 imageView = new ImageView(this);
				 
				 imageViews.add(imageView);
				 count++;
			}
		}
		Log.d(TAG, count+" imageView has bean generate.");
	}
	private void setupImage(){
		 int no = 1;
		 
		 for (int i = 0; i < imageViews.size(); i++) {
			int imageId = this.getResources().getIdentifier("trajectory_icon_"+no,"drawable", getPackageName());
			imageViews.get(i).setImageResource(imageId);
		 }
		 //no++;
	}
	private void setupTable(){
		tableLayout = (TableLayout) findViewById(R.id.g_trajectory_select_tablelayout);
		//本页ImageView数
		int imageViewCurrentPage = imageViews.size();
		//生成2行4列表格
		for(int row = 0;row < 2;row++){
			TableRow tr = new TableRow(this);
			
			for(int col=0;col<4;col++){
				//装载ImageView计数
				int i = row*4+col+1;
				Log.d(TAG, "create image at the row:"+row+" col:"+col+".the imageViews array no is "+i);
				
				if(i <= imageViewCurrentPage){
				    ImageView iv = imageViews.get(i-1);
				    tr.addView(iv);
				}
				
				Log.d(TAG,"ImageView "+i+" finished loading");
            }
			tableLayout.addView(tr, new TableLayout.LayoutParams());
		}
	}
	private void setupOptionString(){
		options = new String[]{
				getString(R.string.trajectory_1),getString(R.string.trajectory_2),
				getString(R.string.trajectory_3),getString(R.string.trajectory_4),
				getString(R.string.trajectory_5),getString(R.string.trajectory_6),
				getString(R.string.trajectory_7),getString(R.string.trajectory_8),
				getString(R.string.trajectory_9),getString(R.string.trajectory_10),
				getString(R.string.trajectory_11),getString(R.string.trajectory_12)
		};
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.trajectory_select_menu, null), getBaseLayoutParams());
		setContentView(layout);
		
		Log.d(TAG,"totalTraNum/traPerPageNum="+totalTraNum/traPerPageNum);
		animation = AnimationUtils.loadAnimation(TrajectorySelectMenuActivity.this, R.anim.menu_item_clicked);
		
		bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        if(null!=bundle)
        	currentPage = bundle.getInt("currentPage");
        Log.d(TAG, "current page is "+currentPage);

		setupOptionString();
		setupViews();
		setUpPageImageView(currentPage);
		setupImage();
		setupTable();
		setupListeners();
	}

	private void setupViews(){
		setTrainingTitle(getString(R.string.menuTraChoose));
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(true);
		
		commentsTextView = (TextView)findViewById(R.id.TextView_TrajectorySelectMenuComments);
		button01 = (Button) findViewById(R.id.g_next_page_button);
		button02 = (Button) findViewById(R.id.g_previou_page_button);
		if(currentPage == 1)
			button02.setEnabled(false);
		if(currentPage == totalPage)
			button01.setEnabled(false);
	}
	
	
	
	private void setupListeners(){

		for (int i = 0; i < imageViews.size(); i++) {
			imageViews.get(i).setOnClickListener(
					createOnMenuImageViewClickListener(imageViews.get(i)));
		}
		
		button01.setOnClickListener(createOnNextPageButtonClickListener(button01));
		button02.setOnClickListener(createOnPreviouPageButtonClickListener(button02));
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
            @Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent(getApplicationContext(), FingerStabilityTrainingActivity.class);
					intent.putExtra("TraNo",choosedTra);
					startActivity(intent);
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
    }
	private Button.OnClickListener createOnNextPageButtonClickListener(final Button button){
		return new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "createOnNextPageButtonClickListener()");
				   Intent intent = new Intent(getApplicationContext(),TrajectorySelectMenuActivity.class);
			       Bundle bundle = new Bundle();
			       bundle.putInt("currentPage",++currentPage);
			       intent.putExtras(bundle);
			       startActivity(intent);         
            }
		};
	}
	
	private Button.OnClickListener createOnPreviouPageButtonClickListener(final Button button){
		return new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "createOnPreviouPageButtonClickListener()");
				   Intent intent = new Intent(getApplicationContext(),TrajectorySelectMenuActivity.class);
			       Bundle bundle = new Bundle();
			       bundle.putInt("currentPage",--currentPage);
			       intent.putExtras(bundle);
			       startActivity(intent);         
            }
		};
	}
	
	private ImageView.OnClickListener createOnMenuImageViewClickListener(final ImageView imageView){
		return new ImageView.OnClickListener(){
			
			@Override
			public void onClick(View paramView) {
				Log.d(TAG, "createOnMenuImageViewClickListener()");
				
				setOkButtonEnable(true);
				for (int i = 0; i < imageViews.size(); i++) {
					if (imageViews.get(i) == imageView) {
						choosedTra = (currentPage-1)*traPerPageNum+i+1;
						animation.setFillAfter(true);
						imageViews.get(i).startAnimation(animation);
						
						commentsTextView.setText(options[i]);
					}else {
						imageViews.get(i).setAnimation(null);
					}
				}
			}			
		};
	}	
	@Override
	protected void onPause() {
		super.onPause();
		DrawView.clearInstance();
	};
}
