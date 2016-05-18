package com.flextronics.cn.activity.stabilitytraining;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.DrawView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FingerStabilityTrainingActivity extends BaseActivity {
	/** Called when the activity is first created. */
	public static final String TAG="FingerStabilityTrainingActivity";
	
	private RelativeLayout baseLayout;
	
	
	private RelativeLayout lineLayout;
	
	private RelativeLayout displayLayout;
	
	private DrawView drawView;
	
	private Button button01;
	
	private RelativeLayout.LayoutParams params;
	
	private RelativeLayout.LayoutParams param0;
	
	private Chronometer chronometer;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        baseLayout = getBaseRelativeLayout();
        lineLayout = (RelativeLayout) getBaseLayoutInflater().inflate(R.layout.stability_training_browser, null);
        displayLayout = new RelativeLayout(this);
        drawView = DrawView.getDrawViewInstance(this);
        
        baseLayout.addView(lineLayout, getBaseLayoutParams());
        baseLayout.addView(displayLayout, getBaseLayoutParams());
        
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        int traNo = bundle.getInt("TraNo");
        Log.d(TAG, "get trajectory no is "+traNo);
        
        button01 = (Button) lineLayout.findViewById(R.id.g_show_result_button);
        button01.setEnabled(false);
        
        chronometer = drawView.getChronometer();
        
        setContentView(baseLayout);
        setupView();
        setupListeners();
    }

	private void setupView(){
		setTrainingTitle(getString(R.string.finger_stability_training));
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(true);
		
		params=new RelativeLayout.LayoutParams(580,550);
		param0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		//距离左边距80dip
		params.leftMargin=100;
		param0.leftMargin=740;
		//距离上边距170dip
		params.topMargin=150;
		param0.topMargin=560;
		displayLayout.addView(drawView,params);
		displayLayout.addView(chronometer, param0);
	}
  
	private void setupListeners(){
		button01.setOnClickListener(createOnShowResultButtonClickListener(button01));
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){
            @Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent(getApplicationContext(), TrainingResultActivity.class);
					//intent.putExtra("TraNo",choosedTra);
					startActivity(intent);
				}
				return false;
			}
		});	
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), TrajectorySelectMenuActivity.class));
					finish();
				}
				return false;
			}
		});
	}
	
	private Button.OnClickListener createOnShowResultButtonClickListener(final Button button){
		return new Button.OnClickListener(){
			@Override
			public void onClick(View paramView) {
				   Log.d(TAG, "createOnShowResultButtonClickListener()");
				   Intent intent = new Intent(getApplicationContext(),TrainingResultActivity.class);
			       Bundle bundle = new Bundle();
			       //bundle.p
			       
			       intent.putExtras(bundle);
			       displayLayout.removeView(drawView);
			       displayLayout.removeView(chronometer);
			       startActivity(intent);         
            }
		};
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void onRestart(){
	    super.onRestart();
	    displayLayout.addView(drawView, params);
	    displayLayout.addView(chronometer, param0);
	}
}
