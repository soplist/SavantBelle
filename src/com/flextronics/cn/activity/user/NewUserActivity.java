package com.flextronics.cn.activity.user;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.adapter.ImageAdapter4NewUser;
import com.flextronics.cn.dao.UserDao;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.InputCheck;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

/**
 * This activity is used for add user
 * @author ZhangGuoYin
 *
 */
public class NewUserActivity extends BaseActivity {
	
	private final static String TAG = "NewUserActivity";

	private EditText userNameEditText;
	private EditText passwdEditText;
	private EditText passwdEditText2;
	private EditText emailEditText;
	private Gallery chooseUserIconGallery;
	private Gallery chooseBackgroundGallery;
	
	private int positionChecked4UserIcon;
	private int positionChecked4Background;
	
	private UserDao userDao;
	private ScaleAnimation  scaleAnimation4ItemClicked;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.new_user, null), getBaseLayoutParams());
		setContentView(layout);		

		scaleAnimation4ItemClicked = new ScaleAnimation(1f, 1.15f, 1f, 1.15f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation4ItemClicked.setInterpolator(new AccelerateInterpolator());
		scaleAnimation4ItemClicked.setFillAfter(true);
		scaleAnimation4ItemClicked.setDuration(200);

		setupValue4Variables();
		setupViews();
		setupAdapter4Gallys();
		setupListeners();
	}
	
	private void setupValue4Variables(){
		positionChecked4UserIcon = -1;
		positionChecked4Background = -1;
		userDao = new UserDao(this);
	}
	
	private void setupViews(){

		setTrainingTitle(getString(R.string.createNewUser));
		setUserNameEnabled(false);
		setUserIconEnable(false);
		
		userNameEditText = (EditText)findViewById(R.id.EditText01);
		passwdEditText = (EditText)findViewById(R.id.EditText02);
		passwdEditText2 = (EditText)findViewById(R.id.EditText03);
		emailEditText = (EditText)findViewById(R.id.EditText04);
		
		chooseUserIconGallery = (Gallery)findViewById(R.id.Gallery01);
		chooseBackgroundGallery = (Gallery)findViewById(R.id.Gallery02);		
	}
	
	private void setupAdapter4Gallys(){
		ImageAdapter4NewUser userIconAdapter4NewUser = 
			new ImageAdapter4NewUser(this, Constants.USER_ICON_IMAGE_RESOURCES);
		userIconAdapter4NewUser.setOnViewCreatedListener(new ImageAdapter4NewUser.OnViewCreatedListener(){

			@Override
			public void onViewCreated(View view, int position) {
				
				if (position == positionChecked4UserIcon) {
					view.setAnimation(scaleAnimation4ItemClicked);
				}
			}			
		});		
		chooseUserIconGallery.setAdapter(userIconAdapter4NewUser);		
		

		ImageAdapter4NewUser backgroundAdapter4NewUser = 
			new ImageAdapter4NewUser(this, Constants.BACKGROUND_IMAGE_RESOURCES);
		backgroundAdapter4NewUser.setOnViewCreatedListener(new ImageAdapter4NewUser.OnViewCreatedListener(){

			@Override
			public void onViewCreated(View view, int position) {
				
				if (position == positionChecked4Background) {
					view.setAnimation(scaleAnimation4ItemClicked);
				}
			}			
		});
		chooseBackgroundGallery.setAdapter(backgroundAdapter4NewUser);
	}
	
	private void setupListeners(){
		
		chooseUserIconGallery.setOnItemClickListener(new Gallery.OnItemClickListener(){			
			public void onItemClick(AdapterView<?> paramAdapterView, View view,
					int realPosition, long arg3) {
				
				Log.d(TAG, "onItemClick() -- realPosition: " + realPosition);

				positionChecked4UserIcon = realPosition;
				for (int i = 0; i < paramAdapterView.getChildCount(); i++) {
					
					if (view == paramAdapterView.getChildAt(i)) {
						paramAdapterView.getChildAt(i).setAnimation(scaleAnimation4ItemClicked);
					}else {
						paramAdapterView.getChildAt(i).setAnimation(null);
					}
				}
			}			
		});
		
		chooseBackgroundGallery.setOnItemClickListener(new Gallery.OnItemClickListener(){			
			public void onItemClick(AdapterView<?> paramAdapterView, View view,
					int realPosition, long arg3) {
				
				Log.d(TAG, "onItemClick() -- realPosition: " + realPosition);

				positionChecked4Background = realPosition;
				for (int i = 0; i < paramAdapterView.getChildCount(); i++) {
					
					if (view == paramAdapterView.getChildAt(i)) {
						paramAdapterView.getChildAt(i).setAnimation(scaleAnimation4ItemClicked);
					}else {
						paramAdapterView.getChildAt(i).setAnimation(null);
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
					if (checkInput()) {
						long userId = userDao.insertUser(userNameEditText.getText().toString(), 
								passwdEditText.getText().toString(), emailEditText.getText().toString(), 
								positionChecked4UserIcon, positionChecked4Background);
						userDao.close();
						if (userId>0) {
							Bundle bundle = new Bundle();
							bundle.putString("userName", userNameEditText.getText().toString());
							bundle.putInt("userIcon", Constants.USER_ICON_IMAGE_RESOURCES_[positionChecked4UserIcon]);
							Intent intent = new Intent(getApplicationContext(), NewUserSuccessActivity.class);
							intent.putExtras(bundle);
							
							startActivity(intent);
						}else {
							Toast.makeText(NewUserActivity.this, R.string.cerateNewUserFailed, Toast.LENGTH_SHORT).show();
						}
					}
				}
				return false;
			}
		});
		
		setOnCancelButtonTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					userNameEditText.setText(null);
					passwdEditText.setText(null);
					passwdEditText2.setText(null);
					emailEditText.setText(null);
					
					if (chooseUserIconGallery.getChildAt(positionChecked4UserIcon)!=null) {
						chooseUserIconGallery.getChildAt(positionChecked4UserIcon).setAnimation(null);
					}
					positionChecked4UserIcon = -1;
					
					if (chooseBackgroundGallery.getChildAt(positionChecked4Background)!=null) {
						chooseBackgroundGallery.getChildAt(positionChecked4Background).setAnimation(null);
					}
					positionChecked4Background = -1;
				}
				return false;
			}
		});
	}
	
	/**
	 * check the user's input
	 * @return
	 */
	private boolean checkInput(){
		
		Log.d(TAG, "checkInput...");
		
		final boolean isUserNameValid;
		final boolean isPasswdValid;
		final boolean isEmailValid;
		boolean isUserIconValid;
		boolean isBackgroundValid;
		
		//user name is not null or empty
		if (userNameEditText.getText()==null || 
				userNameEditText.getText().toString().trim().length()<1) {
			isUserNameValid = false;
		}else {
			isUserNameValid = true;
		}
		
		//password is not null or empty, and the passwords which you input
		//must be consistent
		if (passwdEditText.getText()==null || passwdEditText2==null ||
				passwdEditText.getText().toString().trim().length()<1 ||
				passwdEditText2.getText().toString().trim().length()<1) {
			isPasswdValid = false;
		}else {
			isPasswdValid = InputCheck.isPasswdConsistent(passwdEditText.getText().toString().trim(), 
					passwdEditText2.getText().toString().trim());
		}		
		
		//the email is not null or empty and it must matche the format about email 
		if (emailEditText.getText()==null || 
				emailEditText.getText().toString().trim().length()<1) {
			isEmailValid = false;
		}else {
			isEmailValid = InputCheck.isEmail(emailEditText.getText().toString());
		}
		
		//it must choose one of the user icon
		if (positionChecked4UserIcon < 0 || 
				positionChecked4UserIcon>=Constants.USER_ICON_IMAGE_RESOURCES.length-1) {
			isUserIconValid = false;
		}else {
			isUserIconValid = true;			
		}
		
		//it must choose one of the background image
		if (positionChecked4Background < 0 || 
				positionChecked4Background>=Constants.BACKGROUND_IMAGE_RESOURCES.length-1) {
			isBackgroundValid = false;
		}else {
			isBackgroundValid = true;
		}

		Log.d(TAG, "-----the results-----");
		Log.d(TAG, "isUserNameValid: " + isUserNameValid);
		Log.d(TAG, "isPasswdValid: " + isPasswdValid);
		Log.d(TAG, "isEmailValid: " + isEmailValid);
		Log.d(TAG, "isUserIconValid: " + isUserIconValid);
		Log.d(TAG, "isBackgroundValid: " + isBackgroundValid);
		
		String errorInfo = "";
		if (!isUserNameValid) {
			errorInfo += "\t" + getString(R.string._userName) + "\n";
		}
		if (!isPasswdValid) {
			errorInfo += "\t" + getString(R.string._password) + "\n";
			errorInfo += "\t" + getString(R.string._password2) + "\n";
		}
		if (!isEmailValid) {
			errorInfo += "\t" + getString(R.string._email) + "\n";
		}
		if (!isUserIconValid) {
			errorInfo += "\t" + getString(R.string._userIcon) + "\n";
		}
		if (!isBackgroundValid) {
			errorInfo += "\t" + getString(R.string._background) + "\n";
		}
		
		if (!errorInfo.equals("")) {
			new AlertDialog.Builder(this)
				.setTitle(R.string.plsCheck)
				.setMessage(errorInfo)
				.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener(){
					
					public void onClick(DialogInterface dialog, int which) {
						if (!isUserNameValid) {
							userNameEditText.requestFocus();
						}else if (!isPasswdValid) {
							passwdEditText.requestFocus();
						}else if (!isEmailValid) {
							emailEditText.requestFocus();
						}
					}
				}).show();
			return false;
			
		}else {
			
			if (userDao.getUserCountByUserName(userNameEditText.getText().toString()) > 0) {

				new AlertDialog.Builder(this)
					.setTitle(R.string.tips)
					.setMessage(R.string.userNameIsExist)
					.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener(){

						
						public void onClick(DialogInterface dialog, int which) {
							userNameEditText.requestFocus();
						}
					}).show();
				return false;
			}
			return true;
		}
	}

	@Override
	protected void onDestroy() {

		if (userDao!=null) {
			userDao.close();
			userDao=null;
		}
		super.onDestroy();
	}
}