package com.flextronics.cn.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AnswerQuestionDao extends BaseDao{
	
	private final static String TAG = "AnswerQuestionDB";
	private final static String ANSWER_QUESTION = "answer_question";
	private final static String ANSWER_QUESTION_ID = "_id";
	public final static String ANSWER_QUESTION_QUESTION_ID = "question_id";
	private final static String ANSWER_QUESTION_START_TIME = "start_time";
	private final static String ANSWER_QUESTION_END_TIME = "end_time";
	private final static String ANSWER_QUESTION_START_TIME_TEXT = "start_time_text";
	private final static String ANSWER_QUESTION_END_TIME_TEXT = "end_time_text";
	public final static String ANSWER_QUESTION_ANSWER_RESULT = "answer_result";
	private final static String TESTING_ID = "testing_id";
	private SimpleDateFormat sdf;
	
	public AnswerQuestionDao(Context context) {
		super(context);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	}

	public long insertAnswerQuestion(Long questionId, Long startTime, Long endTime, Boolean result, Long testingId){
		Log.d(TAG, "insertAnswerQuestion");
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		if (questionId == null) {
			contentValues.putNull(ANSWER_QUESTION_QUESTION_ID);
		}else {
			contentValues.put(ANSWER_QUESTION_QUESTION_ID, questionId);
		}		
		
		if (startTime == null) {
			contentValues.putNull(ANSWER_QUESTION_START_TIME);
			contentValues.putNull(ANSWER_QUESTION_START_TIME_TEXT);
		}else {
			contentValues.put(ANSWER_QUESTION_START_TIME, startTime);
			contentValues.put(ANSWER_QUESTION_START_TIME_TEXT, sdf.format(new Date(startTime)));
		}
		
		if (endTime == null) {
			contentValues.putNull(ANSWER_QUESTION_END_TIME);
			contentValues.putNull(ANSWER_QUESTION_END_TIME_TEXT);
		}else {
			contentValues.put(ANSWER_QUESTION_END_TIME, endTime);
			contentValues.put(ANSWER_QUESTION_END_TIME_TEXT, sdf.format(new Date(endTime)));
		}
		
		if (result == null) {
			contentValues.putNull(ANSWER_QUESTION_ANSWER_RESULT);
		}else {			
			contentValues.put(ANSWER_QUESTION_ANSWER_RESULT, result);
		}
		
		if (testingId == null) {
			contentValues.putNull(TESTING_ID);
		}else {
			contentValues.put(TESTING_ID, testingId);
		}
				
		long row = db.insert(ANSWER_QUESTION, null, contentValues);	
		Log.d(TAG, "row id:" + row);

		db.close();
		return row;
	}
	
	public Cursor getAnswerQuestion(int id){
		
		Log.d(TAG, "getTestReport");
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = ANSWER_QUESTION_ID + "=?";
		String[] args = {id+""};
		
		Log.d(TAG, "where "+ where);
		
		return db.query(ANSWER_QUESTION, null, where, args, null, null, null);
	}
	
	public int getAnswerQuestionCount(Long testingId, Long startTime, Long endTime, Boolean value){
		
		Log.d(TAG, "getTestReport");
		
		if (testingId == null || startTime == null || endTime == null || value == null) {
			return -1;
		}else if (startTime >= endTime) {
			return -1;
		}		
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = TESTING_ID + "=? and " + ANSWER_QUESTION_ANSWER_RESULT + "=? and " + 
			ANSWER_QUESTION_END_TIME + ">=? and " + ANSWER_QUESTION_END_TIME + "<?";
		String[] args = {testingId+"", (value?1:0)+"", startTime+"", endTime+"", };
		
		Log.d(TAG, "where "+ where);
		
		Cursor cursor = db.query(ANSWER_QUESTION, null, where, args, null, null, null);
		int size = cursor.getCount();
		cursor.close();
		db.close();
		
		Log.d(TAG, "cursor.length: " + size);
		
		return size;
	}
	
	public int getAnswerQuestionCountInQuestions(Long testingId, Long startQuestionId, Long endQuestionId, Boolean value){
		
		Log.d(TAG, "getAnswerQuestionCountInQuestions");
		
		if (testingId == null || startQuestionId == null || endQuestionId == null || value == null) {
			return -1;
		}else if (startQuestionId >= endQuestionId) {
			return -1;
		}		
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = TESTING_ID + "=? and " + ANSWER_QUESTION_ANSWER_RESULT + "=? and " + 
			ANSWER_QUESTION_QUESTION_ID + ">=? and " + ANSWER_QUESTION_QUESTION_ID + "<=?";
		String[] args = {testingId+"", (value?1:0)+"", startQuestionId+"", endQuestionId+"", };
		
		Log.d(TAG, "where "+ where);
		
		Cursor cursor = db.query(ANSWER_QUESTION, null, where, args, null, null, null);
		int size = cursor.getCount();
		cursor.close();
		db.close();
		
		Log.d(TAG, "cursor.length: " + size);
		
		return size;
	}
	
	public Cursor getAnswerQuestionByTestingId(Long testingId){
		Log.d(TAG, "getAnswerQuestionByTestingId");
		
		if (testingId == null) {
			return null;
		}
		
		SQLiteDatabase db = this.getReadableDatabase();
		String where = TESTING_ID + "=?";
		String[] args = {testingId+""};
		
		Log.d(TAG, "where "+ where);
		
		Cursor cursor = db.query(ANSWER_QUESTION, null, where, args, null, null, null);
		Log.d(TAG, "cursor.length: " + cursor.getCount());
		db.close();
		//cursor.close();
		return cursor;
	}
}
