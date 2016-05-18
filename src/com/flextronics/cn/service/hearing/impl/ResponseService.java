package com.flextronics.cn.service.hearing.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.model.hearing.response.ResponseAnswer;
import com.flextronics.cn.model.hearing.response.ResponseParameter;
import com.flextronics.cn.model.hearing.response.ResponseQuestion;
import com.flextronics.cn.model.hearing.response.ResponseReport;
import com.flextronics.cn.model.hearing.response.ResponseResult;
import com.flextronics.cn.model.hearing.response.ResponseRule;
import com.flextronics.cn.service.hearing.IResponseService;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;
import com.flextronics.cn.util.RandomNext;

public class ResponseService implements IResponseService {
	private final static String TAG = "ResponseService";

	private Context context;
	private ResponseParameter responseParameter=new ResponseParameter();
	private ResponseRule responseRule=new ResponseRule();
	private boolean randomEnabled;

	private static long questionId;
	private static int questionCount;
	private static int errorCount;
	private static int rightCount;
	private static int scores;

	private long _id;
	private long testingId;
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");

	private long testingStartTime;
	private long testingStopTime;
	private long startAnswerTime;

	private MemoryTrainingReportChart chartTools;

	private RandomNext musicInstrumentRandom;
	private RandomNext scaleRandom;
	
	private String[] randomElementArray;
	
	/**
	 * 初始化方法
	 * @param parameters
	 */
	public void init(Map<String, Object> parameters) {
		responseParameter = (ResponseParameter) parameters
		.get(HearingConstants.RESPONSE_PARAMETERS);
		responseRule = (ResponseRule) parameters.get(HearingConstants.RESPONSE_RULE);
		context = (Context) parameters.get(HearingConstants.CONTEXT);
		randomEnabled=responseParameter.isRandomEnabled();
		
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;

		startAnswerTime = System.currentTimeMillis();
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);

		_id = testingDao.insertTesting(testingId, null, null, null);
		Log.d(TAG, "_id: " + _id);

		if (responseParameter == null) {
			return;
		}
		Log.d(TAG, responseParameter.toString());
		
		musicInstrumentRandom = new RandomNext(responseParameter.getMusicInstrumentsSet().getSmapleElementChoosed());
		scaleRandom = new RandomNext(responseParameter.getScaleSet().getSmapleElementChoosed());
	}
	/**
	 * 开始方法
	 */
	public void start() {
		testingStartTime = System.currentTimeMillis();
		testingDao.updateTesting(Integer.valueOf(_id + ""), testingId, null,testingStartTime, null);
	}
	/**
	 * 开始答题
	 */
	public void startAnswer() {
		startAnswerTime = System.currentTimeMillis();
	}
	/**
	 * 统计结果
	 * @param question
	 * @param answer
	 * @return
	 */
	public ResponseResult answerQuestion(ResponseQuestion question,
			ResponseAnswer answer) {
		ResponseResult result = new ResponseResult();
		boolean value;

		if (question == null || question.getId() == 0) { //当题目为空时,不做任何处理
			return result;
		}else if (answer==null || answer.getAnswers()==null) {//当答案为空时
			//计错误一次
			errorCount++;
			value = false;
		}else if (question.getId() != answer.getQuestionId()) {//如果回答的题目不是那一题
			//计错误一次
			errorCount++;
			value = false;
		}else if (question.getAnswers().length != answer.getAnswers().length) {
			errorCount++;
			value = false;
		}else {
			int count = 0;
			for (int i = 0; i < question.getAnswers().length; i++) {
				if (question.getAnswers()[i].equals(answer.getAnswers()[i])) {
					count++;
				}
			}
			if (count == question.getAnswers().length) {//如果回答
				value = true;
				//计正确一次
				rightCount++;
				//分数自增指定分数
				scores += responseRule.getScore();
			}else {//回答错误
				errorCount++;
				value = false;
			}			
		}
		
		result.setValue(value);
		result.setQuestionId(question.getId());
		if (answer == null) {
			result.setContinuedTime(System.currentTimeMillis() - question.getCreateTime().getTime());
		}else {
			result.setContinuedTime(answer.getAnswerTime().getTime()-question.getCreateTime().getTime());
		}
		//向数据库中增加此道题的答题记录
		answerQuestionDao.insertAnswerQuestion(question.getId(), startAnswerTime, System.currentTimeMillis(), value, testingId);
		//更新startAnswerTime
		startAnswerTime = System.currentTimeMillis();
		return result;
	}
	/**
	 * 生成题目
	 */
	public ResponseQuestion createQuestion() {
		Log.d(TAG, "createQuestion()");
		String metronnome = null;
		
		if (responseParameter == null) {
			return null;
		}
		Log.d(TAG, responseParameter.toString());
		questionId++;
		
		if (randomEnabled) {
			if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
				musicInstrumentRandom=null;
				randomElementArray=randomArray();
				musicInstrumentRandom=new RandomNext(randomElementArray);
			} else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)||
					   responseParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
				scaleRandom=null;
				randomElementArray=randomArray();
				scaleRandom=new RandomNext(randomElementArray);
			}
		}
		
		Integer musicInstrumentRedomCode = Integer.valueOf(musicInstrumentRandom.next());
		Integer scaleRedomCode = Integer.valueOf(scaleRandom.next());
		
		String content=null;
		if(responseParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)){
			if(responseParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)){
				content=CommonUtil.getScale(context, musicInstrumentRedomCode, scaleRedomCode);
			}else if(responseParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)){
				content=CommonUtil.getScale(context, musicInstrumentRedomCode, scaleRedomCode);
			}else if(responseParameter.getElementType().equals(HearingConstants.Element.PERCUSSION_SCALE)){
				content=CommonUtil.getScale(context, musicInstrumentRedomCode, scaleRedomCode);
			}
		}else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			if(responseParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)){
				content=CommonUtil.getScale(context, musicInstrumentRedomCode, scaleRedomCode);
			}else if(responseParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)){
				content=CommonUtil.getScale(context, musicInstrumentRedomCode, scaleRedomCode);
			}
		}else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			if (scaleRedomCode <= HearingConstants.Rhythm.OneBarThreeBeat.T3_EIGHT) {
				metronnome = context.getResources().getStringArray(R.array.MetronomeRhythmFilePath)[1];
			} else {
				metronnome = context.getResources().getStringArray(R.array.MetronomeRhythmFilePath)[2];
			}
			content=CommonUtil.getPercussionRhythm(context, musicInstrumentRedomCode, scaleRedomCode);
		}
		Log.d(TAG+"content", ""+content);
		String answer = new String();
		if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
			answer = musicInstrumentRedomCode.toString();
		} else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			answer = scaleRedomCode.toString();
		} else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			answer = scaleRedomCode.toString();
		}
		
		ResponseQuestion question = new ResponseQuestion();
		question.setId(questionId);
		if (metronnome == null) {
			question.setContents(new String[] { content });
		} else {
			question.setContents(new String[] { metronnome, content });
		}
		question.setAnswers(new String[] { answer });
		question.setDisplayAnswer(randomElementArray);
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		questionCount++;
		return question;
	}
	/**
	 * 生成测试报告
	 */
	public ResponseReport generateTestReport() {

		ResponseReport report = new ResponseReport();
		report.setQuestionCount(questionCount);
		report.setErrorCount(errorCount);
		report.setRightCount(rightCount);
		report.setScores(scores);
		if (errorCount + rightCount != 0) {
			report.setRightPercentage(rightCount / (errorCount + rightCount));
			report.setErrorPercentage(errorCount / (errorCount + rightCount));
		} else {
			report.setRightPercentage(0);
			report.setErrorPercentage(0);
		}

		chartTools = new MemoryTrainingReportChart(context, testingId,answerQuestionDao);
		report.setChart(chartTools.generateBarChart(responseParameter.getQuestionTotal()));
		answerQuestionDao.close();
		return report;
	}
	/**
	 * 停止服务
	 */
	public void stop() {
		Log.d(TAG, "stop()");
		// musicInstrumentRandom = null;
		// scaleRandom = null;
		testingStopTime = System.currentTimeMillis();
		testingDao.updateTesting(Integer.valueOf(_id + ""), testingId, null,testingStartTime, testingStopTime);
		testingDao.close();
	}
	
	private String[] randomArray(){
		int[] elementArray=null;
		String[] temp=new String[HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM];
		if(responseParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)){
			if(responseParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.ForeignMusicScale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}else if(responseParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.FolkMusicScale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}else if(responseParameter.getElementType().equals(HearingConstants.Element.PERCUSSION_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.PercussionScale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}
		}else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			elementArray=ArrayOperations.
			getRedomElementFromElements(HearingConstants.Scale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
		}else if (responseParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			if (responseParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)) {
				elementArray=ArrayOperations.
				getRedomElementFromElements(HearingConstants.Rhythm.OneBarThreeBeat.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}else if (responseParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)) {
				elementArray=ArrayOperations.
				getRedomElementFromElements(HearingConstants.Rhythm.OneBarFourBeat.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}
		}
		
		for (int i = 0; i < elementArray.length; i++) {
			temp[i]=String.valueOf(elementArray[i]);
		}
		return temp;
	}
	
}
