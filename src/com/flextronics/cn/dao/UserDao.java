package com.flextronics.cn.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDao extends BaseDao{
	
	private final static String TAG = "UserDao";
	public final static String User = "user";
	public final static String User_ID = "user_id";
	public final static String User_NAME = "user_name";
	public final static String PASSWORD = "password";
	public final static String EMAIL = "email";
	public final static String ICON = "icon";
	public final static String BACKGROUND = "background";
	
	public UserDao(Context context) {
		super(context);
	}	

	public long insertUser(String userName, String password, 
			String email, Integer icon, Integer background){
		Log.d(TAG, "insertUser");
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		if (userName == null) {
			contentValues.putNull(User_NAME);
		}else {
			contentValues.put(User_NAME, userName);
		}
		
		if (password == null) {
			contentValues.putNull(PASSWORD);
		}else {
			contentValues.put(PASSWORD, password);
		}
		
		if (email == null) {
			contentValues.putNull(EMAIL);
		}else {
			contentValues.put(EMAIL, email);
		}
		
		if (icon == null) {
			contentValues.putNull(ICON);
		}else {
			contentValues.put(ICON, icon);
		}
		
		if (background == null) {
			contentValues.putNull(BACKGROUND);
		}else {
			contentValues.put(BACKGROUND, background);
		}
		
		long row = db.insert(User, null, contentValues);	
		Log.d(TAG, "row id:" + row);
		db.close();
		return row;
	}
	
	public int updateUser(Long userId, String userName, String password, 
			String email, Integer icon, Integer background){
		Log.d(TAG, "updateUser");
		
		if (userId == null) {
			return -1;
		}
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();		
		
		if (userName == null) {
			contentValues.putNull(User_NAME);
		}else {
			contentValues.put(User_NAME, userName);
		}
		
		if (password == null) {
			contentValues.putNull(PASSWORD);
		}else {
			contentValues.put(PASSWORD, password);
		}
		
		if (email == null) {
			contentValues.putNull(EMAIL);
		}else {
			contentValues.put(EMAIL, email);
		}
		
		if (icon == null) {
			contentValues.putNull(ICON);
		}else {
			contentValues.put(ICON, icon);
		}
		
		if (background == null) {
			contentValues.putNull(BACKGROUND);
		}else {
			contentValues.put(BACKGROUND, background);
		}
		
		String where = User_ID + "=?";
		String[] args = {userId+""};
		int row = db.update(User, contentValues, where, args);
		Log.d(TAG, "update row id: " + row);

		db.close();
		return row;
	}
	
	/*public Cursor getUser(int userId){
		Log.d(TAG, "getUser");
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = User_USER_ID + "=?";
		String[] args = {userId+""};
		
		Log.d(TAG, "where "+ where);
		
		return db.query(User, null, where, args, null, null, null);
	}	*/
	
	public Cursor getAllUsers(){
		Log.d(TAG, "getAllUsers()");
		SQLiteDatabase db = this.getReadableDatabase();
		String where = User_ID + ">?";
		String[] args = {"0"};
		
		Cursor cursor = db.query(true, User, null, where, args, null, null, null, null);
		//db.close();
		
		return cursor;
	}

	public Cursor getUserByUserName(String userName){
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = User_NAME + "=?";
		String[] args = {userName+""};
		
		Cursor cursor = db.query(true, User, null, where, args, null, null, null, null);
		db.close();
		
		return cursor;
	}

	public int getUserCountByUserName(String userName){
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = User_NAME + "=?";
		String[] args = {userName+""};
		
		Cursor cursor = db.query(User, null, where, args, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		
		return count;
	}
}
