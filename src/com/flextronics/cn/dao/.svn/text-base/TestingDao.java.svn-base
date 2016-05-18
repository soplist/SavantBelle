package com.flextronics.cn.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestingDao extends BaseDao{
	
	private final static String TAG = "TestingDao";
	private final static String TESTING = "testing";
	private final static String _ID = "_id";
	private final static String TESTING_ID = "testing_id";
	private final static String TESTING_USER_ID = "user_id";
	private final static String TESTING_START_TIME = "start_time";
	private final static String TESTING_END_TIME = "end_time";
	private final static String TESTING_START_TIME_TEXT = "start_time_text";
	private final static String TESTING_END_TIME_TEXT = "end_time_text";
	private SimpleDateFormat sdf;
	
	public TestingDao(Context context) {
		super(context);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	}	

	public long insertTesting(Long testingId, Integer userId, Long startTime, Long endTime){
		Log.d(TAG, "insertTesting");
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		if (testingId == null) {
			contentValues.putNull(TESTING_ID);
		}else {
			contentValues.put(TESTING_ID, testingId);
		}
		
		if (userId == null) {
			contentValues.putNull(TESTING_USER_ID);
		}else {
			contentValues.put(TESTING_USER_ID, userId);
		}
		
		if (startTime == null) {
			contentValues.putNull(TESTING_START_TIME);
			contentValues.putNull(TESTING_START_TIME_TEXT);
		}else {
			contentValues.put(TESTING_START_TIME, startTime);
			contentValues.put(TESTING_START_TIME_TEXT, sdf.format(new Date(startTime)));
		}
		
		if (endTime == null) {
			contentValues.putNull(TESTING_END_TIME);
			contentValues.putNull(TESTING_END_TIME_TEXT);
		}else {
			contentValues.put(TESTING_END_TIME, endTime);
			contentValues.put(TESTING_END_TIME_TEXT, sdf.format(new Date(endTime)));
		}
		
		long row = db.insert(TESTING, null, contentValues);	
		Log.d(TAG, "row id:" + row);
		db.close();
		return row;		
	}
	
	public int updateTesting(Integer id, Long testingId, Integer userId, Long startTime, Long endTime){
		Log.d(TAG, "updateTesting");
		
		if (id == null) {
			return -1;
		}
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();		
		
		if (testingId == null) {
			contentValues.putNull(TESTING_ID);
		}else {
			contentValues.put(TESTING_ID, testingId);
		}
		
		if (userId == null) {
			contentValues.putNull(TESTING_USER_ID);
		}else {
			contentValues.put(TESTING_USER_ID, userId);
		}
		
		if (startTime == null) {
			contentValues.putNull(TESTING_START_TIME);
			contentValues.putNull(TESTING_START_TIME_TEXT);
		}else {
			contentValues.put(TESTING_START_TIME, startTime);
			contentValues.put(TESTING_START_TIME_TEXT, sdf.format(new Date(startTime)));
		}
		
		if (endTime == null) {
			contentValues.putNull(TESTING_END_TIME);
			contentValues.putNull(TESTING_END_TIME_TEXT);
		}else {
			contentValues.put(TESTING_END_TIME, endTime);
			contentValues.put(TESTING_END_TIME_TEXT, sdf.format(new Date(endTime)));
		}
		
		String where = _ID + "=?";
		String[] args = {id+""};
		int row = db.update(TESTING, contentValues, where, args);
		Log.d(TAG, "update row id: " + row);

		db.close();
		return row;
	}
	
	public Cursor getTesting(int userId){		
		Log.d(TAG, "getTesting");
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = TESTING_USER_ID + "=?";
		String[] args = {userId+""};
		
		Log.d(TAG, "where "+ where);
		
		return db.query(TESTING, null, where, args, null, null, null);
	}	
}
