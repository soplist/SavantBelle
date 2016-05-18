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
import com.flextronics.cn.model.hearing.memory.MemoryAnswer;
import com.flextronics.cn.model.hearing.memory.MemoryParameter;
import com.flextronics.cn.model.hearing.memory.MemoryQuestion;
import com.flextronics.cn.model.hearing.memory.MemoryReport;
import com.flextronics.cn.model.hearing.memory.MemoryResult;
import com.flextronics.cn.model.hearing.memory.MemoryRule;
import com.flextronics.cn.service.hearing.IMemoryService;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.HearingConstants;
import com.flextronics.cn.util.HearingConstants.MusicalInstruments;
import com.flextronics.cn.util.HearingUtil;
import com.flextronics.cn.util.RandomNext;

public class MemoryService implements IMemoryService {
	private final static String TAG = "MemoryService";
	private Context context;
	private MemoryParameter memoryParameter = new MemoryParameter();
	private MemoryRule memoryRule = new MemoryRule();
	
	private static long questionId;
	private static int questionCount;
	private static int errorCount;
	private static int rightCount;
	private static int scores;
	
	private long _id;
	private long testingId;
	private long startAnswerTime;
	private long testingStartTime;
	private long testingStopTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");

	private RandomNext musicInstrumentRandom;
	private RandomNext scaleRandom;
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	private MemoryTrainingReportChart chartTools;
	
	private int startBit =0;
	private int optionalBit =0;

	public void init(Map<String, Object> parameters) {
		memoryParameter = (MemoryParameter) parameters.get(HearingConstants.MEMORY_PARAMETERS);
		memoryRule = (MemoryRule) parameters.get(HearingConstants.MEMORY_RULE);
		context = (Context) parameters.get(HearingConstants.CONTEXT);

		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;

		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		startAnswerTime = System.currentTimeMillis();
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);
		_id = testingDao.insertTesting(testingId, null, null, null);
		Log.d(TAG, "_id: " + _id);
		if (memoryParameter == null) {
			return;
		}
		Log.d(TAG, memoryParameter.toString());
		
		startBit=memoryParameter.getStartBit();
	}
	
	public void start() {
		testingStartTime = System.currentTimeMillis();
		testingDao.updateTesting(Integer.valueOf(_id + ""), testingId, null,testingStartTime, null);
	}

	public void startAnswer() {
		startAnswerTime = System.currentTimeMillis();
	}
	
	public MemoryResult answerQuestion(MemoryQuestion question,MemoryAnswer answer) {
		Log.d(TAG, "answerQuestion()");
		MemoryResult result = new MemoryResult();
		boolean value;
		if (question == null || question.getId() == 0) {
			//当题目为空时,不做任何处理
			return result;
		}else if (!(memoryParameter.getBitType().equals(HearingConstants.BitType.CONTINUED_BIT)) 
				&&question.getId()>memoryParameter.getQuestionCount()) {
			//如果非连续位元且题目总数大于参数指定题目数,则不做任何处理
			return result;
		}else if (answer==null || answer.getAnswers()==null) {
			//当答案为空时
			//计错误一次
			errorCount++;
			value = false;
		}else if (question.getId() != answer.getQuestionId()) {
			//如果回答的题目不是那一题
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
				scores += memoryRule.getScore();
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
	
	public MemoryQuestion createQuestion() {
		Log.d(TAG, "createQuestion()");
		if (memoryParameter == null) {
			return null;
		}
		Log.d(TAG, memoryParameter.toString());
		questionId++;
		
		String[] contentArray = null;
		String[] answerArray = null;
		String[] musicalInstrumentsChoosedRandom = musicalInstruments(optionalBit, questionId);
		String[] scaleChoosedRandom = null;
		String[] displaySet = null;
		String metronnome=null;
		
		if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)||
				memoryParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			scaleChoosedRandom=scaleStrings(optionalBit, questionId);
			if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)){
				displaySet=musicalInstrumentsChoosedRandom;
				contentArray=new String[musicalInstrumentsChoosedRandom.length];
				answerArray=new String[musicalInstrumentsChoosedRandom.length];
			}else {
				displaySet=scaleChoosedRandom;
				contentArray=new String[scaleChoosedRandom.length];
				answerArray=new String[scaleChoosedRandom.length];
			}
			musicInstrumentRandom=new RandomNext(musicalInstrumentsChoosedRandom);
			scaleRandom=new RandomNext(scaleChoosedRandom);
			if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
				for (int i = 0; i < musicalInstrumentsChoosedRandom.length; i++) {
					int musicalInstruments=Integer.parseInt(musicInstrumentRandom.next());
					int scale=Integer.parseInt(scaleRandom.next());
					contentArray[i]=CommonUtil.getScale(context, musicalInstruments, scale);
					answerArray[i] = String.valueOf(musicalInstruments);
				}
			}else {
				for (int i = 0; i < scaleChoosedRandom.length; i++) {
					int musicalInstruments=Integer.parseInt(musicInstrumentRandom.next());
					int scale=Integer.parseInt(scaleRandom.next());
					contentArray[i]=CommonUtil.getScale(context, musicalInstruments, scale);
					answerArray[i] = String.valueOf(scale);
				}
			}
		}else if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			scaleChoosedRandom=rhythmStrings(optionalBit, questionId);
			displaySet=scaleChoosedRandom;
			contentArray=new String[scaleChoosedRandom.length+1];
			answerArray=new String[scaleChoosedRandom.length];
			musicInstrumentRandom=new RandomNext(musicalInstrumentsChoosedRandom);
			scaleRandom=new RandomNext(scaleChoosedRandom);
			if(memoryParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)){
				metronnome=context.getResources().getStringArray(R.array.MetronomeRhythmFilePath)[1];
				contentArray[0]=metronnome;
			}else if(memoryParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)){
				metronnome=context.getResources().getStringArray(R.array.MetronomeRhythmFilePath)[2];
				contentArray[0]=metronnome;
			}else if(memoryParameter.getElementType().equals(HearingConstants.Element.ALL_BEAT)){
				metronnome=context.getResources().getStringArray(R.array.MetronomeRhythmFilePath)[0];
				contentArray[0]=metronnome;
			}
			for (int i = 0; i < scaleChoosedRandom.length; i++) {
				int musicalInstruments=Integer.parseInt(musicInstrumentRandom.next());
				int scale=Integer.parseInt(scaleRandom.next());
				contentArray[i+1] = CommonUtil.getPercussionRhythm(context,musicalInstruments,scale);
				answerArray[i] = String.valueOf(scale);
			}
		}
		MemoryQuestion question = new MemoryQuestion();
		question.setId(questionId);
		question.setContents(contentArray);
		if (memoryParameter.getChoosedMode()!=null&&
				memoryParameter.getChoosedMode().equals(HearingConstants.Mode.BEAT_RESPONSE)) {
			question.setAnswers(HearingUtil.getRhythmAnswerArray(answerArray));
		}else {
			question.setAnswers(answerArray);
		}
		question.setDisplayContents(displaySet);
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		questionCount++;
		return question;
	}
	
	public void stop() {
		Log.d(TAG, "stop()");
		testingStopTime = System.currentTimeMillis();
		testingDao.updateTesting(Integer.valueOf(_id + ""), testingId, null,testingStartTime, testingStopTime);
		testingDao.close();
	}
	
	public MemoryReport generateTestReport() {
		MemoryReport report = new MemoryReport();
		//此次测试编号
		report.setTestingId(testingId);
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
		if (!(memoryParameter.getBitType().equals(HearingConstants.BitType.CONTINUED_BIT))) {
			chartTools = new MemoryTrainingReportChart(context, testingId,answerQuestionDao);
			report.setChart(chartTools.generateBarChart(memoryParameter.getQuestionCount()));
			answerQuestionDao.close();
		}
		return report;
	}
	
	private String[] musicalInstruments(int optional,long count){
		String[] temp=null;
		if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)) {
			if (memoryParameter.isRandomMusicalInstruments()) {
				if (memoryParameter.getBitType().equals(HearingConstants.BitType.SETTING_BIT)) {
					temp=randomArray(HearingConstants.DEFAULT_ELEMENT_COUNT_HIGH);
				}else if (memoryParameter.getBitType().equals(HearingConstants.BitType.CONTINUED_BIT)) {
					if (count%5==1&&count!=1) {
						startBit++;
						if (startBit>getArrayLength()) {
							startBit=3;
						}
					}
					temp=randomArray(startBit);
				}else {
					optional=Integer.parseInt(memoryParameter.getBitType());
					temp=randomArray(optional);
				}
			}else {
				temp=memoryParameter.getMusicalInstruments().getSmapleElementChoosed();
			}
		}else {
			if (memoryParameter.isRandomMusicalInstruments()) {
				if (memoryParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)) {
					temp=ArrayOperations.toStringArray(ArrayOperations.getRedomElementFromElements(
							HearingConstants.MusicalInstruments.ForeignMusicScale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_LOW));
				}else if (memoryParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)) {
					temp=ArrayOperations.toStringArray(ArrayOperations.getRedomElementFromElements(
							HearingConstants.MusicalInstruments.FolkMusicScale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_LOW));
				}
			}else {
				temp=memoryParameter.getMusicalInstruments().getSmapleElementChoosed();
			}
		}
		return temp;
	}
	
	private String[] scaleStrings(int optional,long count){
		String[] temp=null;
		if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			if (memoryParameter.isRandomMusicalInstruments()) {
				if (memoryParameter.getBitType().equals(HearingConstants.BitType.SETTING_BIT)) {
					temp=randomArray(HearingConstants.DEFAULT_ELEMENT_COUNT_HIGH);
				}else if (memoryParameter.getBitType().equals(HearingConstants.BitType.CONTINUED_BIT)) {
					if (count%5==1&&count!=1) {
						startBit++;
						if (startBit>getArrayLength()) {
							startBit=3;
						}
					}
					temp=randomArray(startBit);
				}else {
					optional=Integer.parseInt(memoryParameter.getBitType());
					temp=randomArray(optional);
				}
			}else {
				temp=memoryParameter.getScale().getSmapleElementChoosed();
			}
		}else {
			if (memoryParameter.isRandomScale()) {
				temp=ArrayOperations.toStringArray(ArrayOperations.getRedomElementFromElements(
						HearingConstants.Scale.ARRAY, HearingConstants.DEFAULT_ELEMENT_COUNT_LOW));
			}else {
				temp=memoryParameter.getScale().getSmapleElementChoosed();
			}
		}
		return temp;
	}
	
	private String[] rhythmStrings(int optional,long count){
		String[] temp=null;
		if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			if (memoryParameter.getBitType().equals(HearingConstants.BitType.SETTING_BIT)) {
				temp=randomArray(HearingConstants.DEFAULT_ELEMENT_COUNT_MEDIUM);
			}else if (memoryParameter.getBitType().equals(HearingConstants.BitType.CONTINUED_BIT)) {
				if (count%5==1&&count!=1) {
					startBit++;
					if (startBit>getArrayLength()) {
						startBit=3;
					}
				}
				temp=randomArray(startBit);
			}else {
				optional=Integer.parseInt(memoryParameter.getBitType());
				temp=randomArray(optional);
			}
		}
		return temp;
	}
	
	private String[] randomArray(int bitCount){
		int[] elementArray=null;
		String[] temp=new String[bitCount];
		if(memoryParameter.getSmapleType().equals(HearingConstants.Smaple.MUSICAL_INSTRUMENTS)){
			if(memoryParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.ForeignMusicScale.ARRAY, bitCount);
			}else if(memoryParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.FolkMusicScale.ARRAY, bitCount);
			}else if(memoryParameter.getElementType().equals(HearingConstants.Element.PERCUSSION_SCALE)){
				elementArray=ArrayOperations.
				getRedomElementFromElements(MusicalInstruments.PercussionScale.ARRAY, bitCount);
			}
		}else if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			elementArray=ArrayOperations.
			getRedomElementFromElements(HearingConstants.Scale.ARRAY, bitCount);
		}else if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.RHYTHM)) {
			if (memoryParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_THREE_BEAT)) {
				elementArray=ArrayOperations.
				getRedomElementFromElements(HearingConstants.Rhythm.OneBarThreeBeat.ARRAY, bitCount);
			}else if (memoryParameter.getElementType().equals(HearingConstants.Element.ONE_BAR_FOUR_BEAT)) {
				elementArray=ArrayOperations.
				getRedomElementFromElements(HearingConstants.Rhythm.OneBarFourBeat.ARRAY, bitCount);
			}else {
				elementArray=ArrayOperations.
				getRedomElementFromElements(HearingConstants.Rhythm.ARRAY, bitCount);
			}
		}
		for (int i = 0; i < elementArray.length; i++) {
			temp[i]=String.valueOf(elementArray[i]);
		}
		return temp;
	}
	
	private int getArrayLength(){
		int length=0;
		if (memoryParameter.getElementType().equals(HearingConstants.Element.FOREIGN_MUSIC_SCALE)) {
			length=HearingConstants.MusicalInstruments.ForeignMusicScale.ARRAY.length;
		}else if (memoryParameter.getElementType().equals(HearingConstants.Element.FOLK_MUSIC_SCALE)) {
			length=HearingConstants.MusicalInstruments.FolkMusicScale.ARRAY.length;
		}else if (memoryParameter.getElementType().equals(HearingConstants.Element.PERCUSSION_SCALE)) {
			length=HearingConstants.MusicalInstruments.PercussionScale.ARRAY.length;
		}else if (memoryParameter.getSmapleType().equals(HearingConstants.Smaple.SCALE)) {
			length=HearingConstants.Scale.ARRAY.length;
		}
		return length;
	}

}