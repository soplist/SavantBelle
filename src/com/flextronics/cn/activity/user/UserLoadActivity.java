package com.flextronics.cn.activity.user;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.adapter.LinearLayoutAdapter4UserLoadActivity;
import com.flextronics.cn.dao.UserDao; 
import com.flextronics.cn.util.Constants;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * this activity is used for user to load SavantBelle System
 * @author ZhangGuoYin
 *
 */
public class UserLoadActivity extends BaseActivity {

	private final static String TAG = "UserLoadActivity";
	
	private Gallery usersGallery;
	
	private int positionChecked4UserIcon = -1;
	private Cursor cursor;
	private ScaleAnimation  scaleAnimation4ItemClicked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.user_load, null), getBaseLayoutParams());
		setContentView(layout);

		scaleAnimation4ItemClicked = new ScaleAnimation(1f, 1.15f, 1f, 1.15f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation4ItemClicked.setInterpolator(new AccelerateInterpolator());
		scaleAnimation4ItemClicked.setFillAfter(true);
		scaleAnimation4ItemClicked.setDuration(200);
		
		//set which widgets should be show
		setupViews();
		
		//set the adapter for gallery
		setupAdapter4Gallery();
		
		//set the listeners
		setupListeners();
	}
	
	private void setupViews(){
		setTrainingTitle(getString(R.string.userLoad));
		setUserNameEnabled(false);
		setUserIconEnable(false);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		
		usersGallery = (Gallery)findViewById(R.id.Gallery01);
	}
	
	private void setupAdapter4Gallery(){
		
		//get valid user in system
		cursor = new UserDao(this).getAllUsers();
		//create adapter for gallery
		LinearLayoutAdapter4UserLoadActivity adapter = 
			new LinearLayoutAdapter4UserLoadActivity(this, cursor);
		
		adapter.setOnViewCreatedListener(new LinearLayoutAdapter4UserLoadActivity.OnViewCreatedListener(){

			@Override
			public void onViewCreated(View view, int position) {
				
				if (position == positionChecked4UserIcon) {					
					view.findViewById(R.id.ImageView_userIcon).setAnimation(scaleAnimation4ItemClicked);
				}
			}			
		});
		
		usersGallery.setAdapter(adapter);
	}
	
	
	private void setupListeners(){

		usersGallery.setOnItemClickListener(new Gallery.OnItemClickListener(){
			public void onItemClick(AdapterView<?> paramAdapterView, View view,
					int realPosition, long arg3) {
				
				Log.d(TAG, "onItemClick() -- realPosition: " + realPosition);

				positionChecked4UserIcon = realPosition;
				if (positionChecked4UserIcon != -1) {
					setOkButtonEnable(true);
				}
				for (int i = 0; i < paramAdapterView.getChildCount(); i++) {
					
					if (view == paramAdapterView.getChildAt(i)) {
						paramAdapterView.getChildAt(i).findViewById(R.id.ImageView_userIcon)
						.setAnimation(scaleAnimation4ItemClicked);
					}else {
						paramAdapterView.getChildAt(i).findViewById(R.id.ImageView_userIcon)
						.setAnimation(null);
					}
				}
			}			
		});
		
		setOnHomeButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), UserManagementActivity.class));
					finish();
				}
				return false;
			}
		});
		
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), UserManagementActivity.class));
					finish();				
				}
				return false;
			}
		});
		
		setOnOkButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					if (positionChecked4UserIcon != -1) {

			            LayoutInflater factory = LayoutInflater.from(UserLoadActivity.this);
			            final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
			            final EditText passswdEditText = (EditText)textEntryView.findViewById(R.id.password_edit);
			            passswdEditText.requestFocus();
			            
			            cursor.moveToPosition(positionChecked4UserIcon);		            
			            ((EditText)textEntryView.findViewById(R.id.username_edit)).setText(
			            		cursor.getString(cursor.getColumnIndex(UserDao.User_NAME)));
			            
						new AlertDialog.Builder(UserLoadActivity.this)
							.setTitle(R.string.userLoad)
							.setView(textEntryView)
							.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int whichButton) {
			                    	if (!passswdEditText.getText().toString().trim().equals(
			                    			cursor.getString(cursor.getColumnIndex(UserDao.PASSWORD)))) {
			                    		//if password is error, pop up a alert dialog
			                    		new AlertDialog.Builder(UserLoadActivity.this)
			                    			.setTitle(R.string.tips)
			                    			.setMessage(R.string.passwordError)
			                    			.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener(){
												@Override
												public void onClick(
														DialogInterface paramDialogInterface,
														int paramInt) {	
												}
			                    				
			                    			}).show();									
									}else {
										//if password is right, we will go to next activity
										SharedPreferences sharedPreferences = getSharedPreferences(
												Constants.USER_INFO, MODE_PRIVATE);
										sharedPreferences
											.edit()
											.putString(Constants.USER_NAME, cursor.getString(cursor.getColumnIndex(UserDao.User_NAME)))
											.putInt(Constants.ICON, cursor.getInt(cursor.getColumnIndex(UserDao.ICON)))
											.putInt(Constants.BACKGROUND, cursor.getInt(cursor.getColumnIndex(UserDao.BACKGROUND)))
											.commit();
										startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
										finish();
									}		                       
			                    }
			                })
			                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int whichButton) {
			
			                        /* User clicked cancel so do some stuff */
			                    }
			                })
							.show();					
					}				
				}
				return false;
			}
		});		
	}	
}