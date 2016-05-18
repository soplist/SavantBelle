package com.flextronics.cn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flextronics.cn.activity.color.ColorTrainingChooseActivity;
import com.flextronics.cn.activity.coordination.CoordinationActivity;
import com.flextronics.cn.activity.hearing.HearingActivity;
import com.flextronics.cn.activity.rockertraining.ModeSelectActivity;
import com.flextronics.cn.activity.rockertraining.RockerTestActivity;
import com.flextronics.cn.activity.spatial.ChooseParams4SpatialMemoryTrainingActivity;
import com.flextronics.cn.activity.stabilitytraining.TrajectorySelectMenuActivity;
import com.flextronics.cn.activity.visionhearingtouch.VhtChooseTrainingActivity;

/**this activity is the main menu of SavaneBelle;
 * it's used for user to choose training
 * @author ZhangGuoYin
 *
 */
public class MainMenuActivity extends BaseActivity {
	
	private final static String TAG = "UserLoadActivity";
	
	private ImageView menuImageView1;
	private ImageView menuImageView2;
	private ImageView menuImageView3;
	private ImageView menuImageView4;
	private ImageView menuImageView5;
	private ImageView menuImageView6;
	private ImageView menuImageView7;
	private ImageView menuImageView8;
	private ImageView[] menuImageViews;
	
	private TextView menuCommentsTextView;
	
	private String[] menuOptions;
	private int positionChoosed = -1;
	private Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.main_menu, null), getBaseLayoutParams());
		setContentView(layout);
		
		menuOptions = new String[]{
				getString(R.string.Menu_A),		getString(R.string.Menu_B),
				getString(R.string.Menu_C),		getString(R.string.Menu_D),
				getString(R.string.Menu_E),		getString(R.string.Menu_F),
				getString(R.string.Menu_G),		getString(R.string.Menu_H)
		};
		animation = AnimationUtils.loadAnimation(MainMenuActivity.this, R.anim.menu_item_clicked);
		
		//set which widgets should be show
		setupViews();

		//set the listeners
		setupListeners();
	}
	
	private void setupViews(){
		setTrainingTitle(getString(R.string.menuOptionsChoose));
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(false);
		
		menuImageView1 = (ImageView)findViewById(R.id.ImageView_MainMenu_1);
		menuImageView2 = (ImageView)findViewById(R.id.ImageView_MainMenu_2);
		menuImageView3 = (ImageView)findViewById(R.id.ImageView_MainMenu_3);
		menuImageView4 = (ImageView)findViewById(R.id.ImageView_MainMenu_4);
		menuImageView5 = (ImageView)findViewById(R.id.ImageView_MainMenu_5);
		menuImageView6 = (ImageView)findViewById(R.id.ImageView_MainMenu_6);
		menuImageView7 = (ImageView)findViewById(R.id.ImageView_MainMenu_7);
		menuImageView8 = (ImageView)findViewById(R.id.ImageView_MainMenu_8);
		menuImageViews = new ImageView[]{
				menuImageView1,		menuImageView2,		menuImageView3,
				menuImageView4,		menuImageView5,		menuImageView6,
				menuImageView7,		menuImageView8
		};
		menuCommentsTextView = (TextView)findViewById(R.id.TextView_MenuComments);
	}
	
	private void setupListeners(){

		for (int i = 0; i < menuImageViews.length; i++) {
			menuImageViews[i].setOnClickListener(
					createOnMenuImageViewClickListener(menuImageViews[i]));
		}
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					switch (positionChoosed) {
					case 0:
						Log.d(TAG, "we will go to vision-hearing-touch system...");
						startActivity(new Intent(getApplicationContext(), VhtChooseTrainingActivity.class));
						finish();
						break;
					case 1:
						Log.d(TAG, "we will go to module b hearing system...");
						startActivity(new Intent(getApplicationContext(), HearingActivity.class));
						finish();
						break;
					case 2:
						Log.d(TAG, "we will go to module C color system...");
						startActivity(new Intent(getApplicationContext(), ColorTrainingChooseActivity.class));
						finish();
						break;
					case 3:
						Log.d(TAG, "we will go to module D color system...");
						startActivity(new Intent(getApplicationContext(), com.flextronics.cn.activity.symboltraining.Main.class));
						finish();
						break;
					case 4:
						Log.d(TAG, "we will go to module E sptail system...");
						startActivity(new Intent(getApplicationContext(), ChooseParams4SpatialMemoryTrainingActivity.class));
						finish();
						break;
					case 5:
						Log.d(TAG, "we will go to module F sptail system...");
						startActivity(new Intent(getApplicationContext(),CoordinationActivity.class));
						finish();
						break;
					case 6:
						Log.d(TAG, "we will go to finger-stability-training system...");
						startActivity(new Intent(getApplicationContext(), TrajectorySelectMenuActivity.class));
						finish();
						break;
					case 7:
						Log.d(TAG, "we will go to rocker-stability-training system...");
						startActivity(new Intent(getApplicationContext(), ModeSelectActivity.class));
						finish();
						break;

					default:
						break;
					}
				}
				return false;
			}
		});		
	}
	
	/**
	 * Create the on click listener for menu options
	 * when click one menu, it will be larger than others
	 *  and there will be some comments about this menu
	 * @param imageView
	 * @return
	 */
	private ImageView.OnClickListener createOnMenuImageViewClickListener(final ImageView imageView){
		return new ImageView.OnClickListener(){
			
			@Override
			public void onClick(View paramView) {
				Log.d(TAG, "createOnMenuImageViewClickListener()");
				
				setOkButtonEnable(true);
				for (int i = 0; i < menuImageViews.length; i++) {
					if (menuImageViews[i] == imageView) {
						positionChoosed = i;
						animation.setFillAfter(true);
						menuImageViews[i].startAnimation(animation);
						
						menuCommentsTextView.setText(menuOptions[i]);
					}else {
						menuImageViews[i].setAnimation(null);
					}
				}
			}			
		};
	}	
}