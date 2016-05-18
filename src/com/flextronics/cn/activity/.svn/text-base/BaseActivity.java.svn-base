package com.flextronics.cn.activity;

import com.flextronics.cn.activity.user.UserManagementActivity;
import com.flextronics.cn.util.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This activity is base, it has background, top, ok and cancel button;
 * the activity which extends base activity must call super.OnCreate();
 * call setOkButtonEnable(true) can set the ok button is visible and others like this;
 * the base activity uses relative layout as its base layout, so you had better 
 * use relative layout too;
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseActivity extends Activity {
	
	private RelativeLayout baseRelativeLayout;
	private RelativeLayout background;
	private RelativeLayout top;
	private RelativeLayout bottom;
	
	private ImageView homeImageView;
	private ImageView backImageView;
	private TextView leftTagTextView;
	private TextView trainingTitleTextView;
	private TextView rightTagTextView;
	private TextView userNameTextView;
	private ImageView userIconImageView;
	private ImageView okImageView;
	private ImageView cancelImageView;
	private ImageView nextImageView;
	
	private Animation clickedAnimation;
	private int updateUserInfoOptionsIndex = -1;
	
	private RelativeLayout.LayoutParams layoutParams;
	private LayoutInflater layoutInflater;	
	
	public RelativeLayout.LayoutParams getBaseLayoutParams() {
		return layoutParams;
	}

	public LayoutInflater getBaseLayoutInflater() {
		return layoutInflater;
	}

	public RelativeLayout getBaseRelativeLayout() {
		return baseRelativeLayout;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		clickedAnimation = AnimationUtils.loadAnimation(this, R.anim.clicked_110);
		
		baseRelativeLayout = new RelativeLayout(this);
		layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);		
		layoutInflater = LayoutInflater.from(this);
		
		background = (RelativeLayout)layoutInflater.inflate(
				R.layout.included_background, null);
		baseRelativeLayout.addView(background, layoutParams);
		
		top = (RelativeLayout)layoutInflater.inflate(R.layout.included_top, null);
		baseRelativeLayout.addView(top, layoutParams);
		
		bottom = (RelativeLayout)layoutInflater.inflate(R.layout.included_ok_cancle_buttons, null);
		baseRelativeLayout.addView(bottom, layoutParams);
		
		setupViews0();
		setNextButtonEnable(false);
	}
	
	private void setupViews0(){

		backImageView = (ImageView)top.findViewById(R.id.ImageView_Back);
		homeImageView = (ImageView)top.findViewById(R.id.ImageView_Home);
		leftTagTextView = (TextView)top.findViewById(R.id.TextView_LeftTag);
		trainingTitleTextView = (TextView)top.findViewById(R.id.TextView1_TrainingTitle);
		rightTagTextView = (TextView)top.findViewById(R.id.TextView_RightTag);
		userNameTextView = (TextView)top.findViewById(R.id.TextView_UserName);
		userIconImageView = (ImageView)top.findViewById(R.id.ImageView_UserIcon);
		okImageView = (ImageView)bottom.findViewById(R.id.ImageView_Button_Ok);
		cancelImageView = (ImageView)bottom.findViewById(R.id.ImageView_Button_Cancel);
		nextImageView = (ImageView)bottom.findViewById(R.id.ImageView_Button_Next);
	}

	/**
	 * set the title for activity, it will show at left_top position
	 * @param trainingTitle
	 */
	public void setTrainingTitle(String trainingTitle){
		if (trainingTitle == null) {
			leftTagTextView.setVisibility(TextView.INVISIBLE);
			leftTagTextView.setEnabled(false);
			trainingTitleTextView.setEnabled(false);
			rightTagTextView.setVisibility(TextView.INVISIBLE);
			rightTagTextView.setEnabled(false);
		}else {
			leftTagTextView.setEnabled(true);
			trainingTitleTextView.setText(trainingTitle);
			rightTagTextView.setEnabled(true);
		}
	}
	
	/**
	 * set user name and show it;
	 * @param userName
	 */
	public void setUserName(String userName){
		if (userName == null) {
			userNameTextView.setEnabled(false);
		}else {
			userNameTextView.setEnabled(true);
			userNameTextView.setText(userName);
		}
	}
	
	public void setUserNameEnabled(boolean enabled){
		if (enabled) {
			String userName = getSharedPreferences(Constants.USER_INFO, MODE_PRIVATE)
				.getString(Constants.USER_NAME, null);
			if (userName!=null) {
				userNameTextView.setText(userName);
			}
		}
		userNameTextView.setEnabled(enabled);
	}
	
	public void setUserIcon(int iconNo){
		userIconImageView.setImageResource(iconNo);
	}
	
	public void setHomeButtonEnable(boolean enabled){
		homeImageView.setEnabled(enabled);
	}	

	public void setBackButtonEnable(boolean enabled){
		backImageView.setEnabled(enabled);		
	}
	
	public void setUserIconEnable(boolean enabled){
		if (enabled) {
			int icon = getSharedPreferences(
					Constants.USER_INFO, MODE_PRIVATE).getInt(Constants.ICON, -1);
			if (icon != -1) {
				userIconImageView.setImageResource(Constants.USER_ICON_IMAGE_RESOURCES[icon]);
				userIconImageView.setOnClickListener(new ImageView.OnClickListener(){

					public void onClick(View paramView) {

						userIconImageView.startAnimation(clickedAnimation);
						new AlertDialog.Builder(BaseActivity.this)
							.setTitle(getString(R.string.plsChoose) + " :")
							.setSingleChoiceItems(
								new String[]{getString(R.string.updateUserInfo), getString(R.string.exit)}, 
								0, new DialogInterface.OnClickListener(){

									@Override
									public void onClick(
											DialogInterface paramDialogInterface,
											int whichButton) {
										updateUserInfoOptionsIndex = whichButton;									
									}									
								})
							.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

								@Override
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
									if (updateUserInfoOptionsIndex>-1) {
										if (updateUserInfoOptionsIndex == 1) {//exit user load
											new AlertDialog.Builder(BaseActivity.this)
												.setTitle(R.string.tips)
												.setMessage(R.string.isSureExit)
												.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

													@Override
													public void onClick(
															DialogInterface paramDialogInterface,
															int paramInt) {
														getSharedPreferences(Constants.USER_INFO, MODE_PRIVATE).edit().clear().commit();
														startActivity(new Intent(getApplicationContext(), UserManagementActivity.class));
														finish();
													}													
												})
												.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

													@Override
													public void onClick(
															DialogInterface paramDialogInterface,
															int paramInt) {
														updateUserInfoOptionsIndex = -1;
													}													
												})
												.show();
										}else if (updateUserInfoOptionsIndex == 0) {//update user info
											
										}
									}
								}						
							})
							.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

								@Override
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
									// TODO Auto-generated method stub
									updateUserInfoOptionsIndex = -1;
								}							
							})
							.show();							
					}				
				});
			}
		}
		userIconImageView.setEnabled(enabled);
	}

	public void setOkButtonEnable(boolean enabled){
		okImageView.setEnabled(enabled);
		okImageView.setClickable(enabled);
	}
	
	public void setCancelButtonEnable(boolean enabled){
		cancelImageView.setEnabled(enabled);
	}	
	
	public void setNextButtonEnable(boolean enabled){
		nextImageView.setEnabled(enabled);
	}
	
	/**
	 * set on touch listener for back button
	 * @param onTouchListener
	 */
	public void setOnBackButtonTouchListener(ImageView.OnTouchListener onTouchListener){
		backImageView.setOnTouchListener(onTouchListener);		
	}
	
	/**
	 * set on touch listener for home button
	 * @param onTouchListener
	 */
	public void setOnHomeButtonTouchListener(ImageView.OnTouchListener onTouchListener){
		homeImageView.setOnTouchListener(onTouchListener);		
	}
	
	/**
	 * set on touch listener for ok button
	 * @param onTouchListener
	 */
	public void setOnOkButtonTouchListener(ImageView.OnTouchListener onTouchListener){
		okImageView.setOnTouchListener(onTouchListener);		
	}
	
	/**
	 * set on touch listener for cancel button
	 * @param onTouchListener
	 */
	public void setOnCancelButtonTouchListener(ImageView.OnTouchListener onTouchListener){
		cancelImageView.setOnTouchListener(onTouchListener);		
	}

	/**
	 * set on touch listener for next button
	 * @param onTouchListener
	 */
	public void setOnNextButtonTouchListener(ImageView.OnTouchListener onTouchListener){
		nextImageView.setOnTouchListener(onTouchListener);		
	}
	
	
	
	/*
	*//**
	 * set on click listener for back button
	 * @param onClickListener
	 *//*
	public void setOnBackButtonClick(ImageView.OnClickListener onClickListener){
		backImageView.setOnClickListener(onClickListener);
	}
	
	*//**
	 * set on click listener for ok button
	 * @param onClickListener
	 *//*
	public void setOnOkButtonClick(ImageView.OnClickListener onClickListener){
		okImageView.setOnClickListener(onClickListener);
	}
	
	*//**
	 * set on click listener for cancel button
	 * @param onClickListener
	 *//*
	public void setOnCancelButtonClick(ImageView.OnClickListener onClickListener){
		cancelImageView.setOnClickListener(onClickListener);
	}
	
	*//**
	 * set on click listener for home button
	 * @param onClickListener
	 *//*
	public void setOnHomeButtonClick(ImageView.OnClickListener onClickListener){
		homeImageView.setOnClickListener(onClickListener);		
	}	*/
}
