package com.flextronics.cn.activity.user;

import com.flextronics.cn.activity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**The main activity used for user management
 * 
 * @author ZhangGuoYin
 *
 */
public class UserManagementActivity extends Activity {
	
	private final static String TAG = "UserManagementActivity";
	private Button loadButton;
	private Button newUserButton;
	private Button aboutButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_management);
		setupViews();
		setupListeners();
	}
	
	/**
	 * setup the views on this activity
	 */
	private void setupViews(){
		loadButton = (Button)findViewById(R.id.Button01);
		newUserButton = (Button)findViewById(R.id.Button02);
		aboutButton = (Button)findViewById(R.id.Button03);		
	}
	
	/**
	 * set listeners for each button
	 */
	private void setupListeners(){
		
		loadButton.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				Log.d(TAG, "clickd the loadButton");
				Intent intent = new Intent(UserManagementActivity.this, UserLoadActivity.class);
				startActivity(intent);
				finish();
			}			
		});
		
		newUserButton.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				Log.d(TAG, "clickd the newUserButton");
				Intent intent = new Intent(UserManagementActivity.this, NewUserActivity.class);
				startActivity(intent);
				finish();
			}			
		});
		
		aboutButton.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				Log.d(TAG, "clickd the aboutButton");
				new AlertDialog.Builder(UserManagementActivity.this)
					.setTitle(R.string.tips)
					.setMessage(R.string.is_sure_exit_system)
					.setCancelable(false)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(
								DialogInterface paramDialogInterface,
								int paramInt) {
							finish();
						}						
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(
								DialogInterface paramDialogInterface,
								int paramInt) {
							
						}							
					})
					.show();
			}			
		});
	}
}
