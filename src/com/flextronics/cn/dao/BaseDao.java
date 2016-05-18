package com.flextronics.cn.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDao extends SQLiteOpenHelper {
	
	private final static String TAG = "BaseDao";
	
	final static String DB_NAME = "learning_machine";
	final static int DB_VERSION = 3;	
	
	public BaseDao(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	
	public void onCreate(SQLiteDatabase db) {

		Log.d(TAG, "onCreate");
		
		String sql = "create table answer_question(_id integer primary key, " + 
			"question_id integer, start_time long, end_time long, start_time_text text, " + 
			"end_time_text text, answer_result boolean, testing_id long);";
		Log.d(TAG, sql);
		db.execSQL(sql);
		
		sql = "create table testing(_id integer primary key, testing_id long, user_id integer, " + 
			"start_time long, end_time long, start_time_text text, end_time_text text);";		
		Log.d(TAG, sql);		
		db.execSQL(sql);
		
		sql = "create table user(user_id integer primary key, user_name text, password text, " +
				"email text, icon integer, background integer);";		
		Log.d(TAG, sql);		
		db.execSQL(sql);
		
		sql = "create table position(tra_no integer primary key,mode integer,x float,y float);";
		Log.d(TAG, sql);
		db.execSQL(sql);
	}
	
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.d(TAG, "onUpgrade");
		
		String sql = "drop table if exists answer_question;";		
		Log.d(TAG, sql);		
		db.execSQL(sql);
		
		sql = "drop table if exists testing;";	
		Log.d(TAG, sql);		
		db.execSQL(sql);
		
		sql = "drop table if exists user;";	
		Log.d(TAG, sql);		
		db.execSQL(sql);
		
		sql = "drop table if exists position;";	
		Log.d(TAG, sql);		
		db.execSQL(sql);
	}
}
