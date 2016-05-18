package com.flextronics.cn.service.hearingtouch;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.chart.ResponseTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtAnswer;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtParameter;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtQuestion;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtResult;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.RandomNext;

/**HtrtQuestionAnswerService的实现类
 * 
 * @author ZhangGuoYin
 *
 */
public class HtrtQuestionAnswerServiceImpl implements HtrtQuestionAnswerService {

	private final static String TAG = "HtrtQuestionAnswerServiceImpl";

	private long _id;
	/**
	 * 当前用户的ID
	 */
	private Integer userId;
	/**
	 * 测试ID
	 */
	private long testingId;

	/**
	 * 每道题的ID
	 */
	private static long questionId;
	/**
	 * 题目总数
	 */
	private static int questionCount;
	/**
	 * 答错题目总数
	 */
	private static int errorCount;
	/**
	 * 答错题目总数
	 */
	private static int rightCount;
	/**
	 * 总分数
	 */
	private static int scores;
	
	private Context context;
	/**
	 * 参数
	 */
	private HtrtParameter parameter;
	/**
	 * 规则
	 */	
	private HtrtRule rule;
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	private SimpleDateFormat sdf;
	/**
	 * 测试开始时间
	 */
	private long testingStartTime;
	/**
	 * 测试结束时间
	 */
	private long testingStopTime;
	/**
	 * 答题开始时间
	 */
	private long startAnswerTime;
	
	private RandomNext musicInstrumentRandom;
	private RandomNext scaleRandom;
	
	
	public HtrtQuestionAnswerServiceImpl() {
		super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		parameter = new HtrtParameter();	
		rule = new HtrtRule();
	}
	
	/**
	 * 初始化服务
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException {
		//参数不能为空
		if (parameters == null) {
			Log.e(TAG, "parameters is null, it's error!");
			throw new LackOfParametersException();
		}
		
		this.parameter = (HtrtParameter)parameters.get(
				Constants.PARAMETER);
		rule = (HtrtRule)parameters.get(Constants.RULE);
		context = (Context)parameters.get(Constants.CONTEXT);

		//参数不能为空
		if (parameter == null) {
			
			Log.e(TAG, "htrtParameter is null, it's error!");
			throw new LackOfParametersException();			
		}else if (rule == null) {
			
			Log.e(TAG, "htrtRule is null, it's error!");
			throw new LackOfParametersException();			
		}else if (context == null) {
			
			Log.e(TAG, "context is null, it's error!");
			throw new LackOfParametersException();			
		}
		
		//创建数据库操作对象
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
		
		//取得当前用户的userId
		/*userId = context.getSharedPreferences(
				Constants.USER_INFO, Context.MODE_PRIVATE).getInt(Constants.USER_NAME, -1);*/
		_id = testingDao.insertTesting(testingId, userId, null, null);
		Log.d(TAG, "_id: " + _id);
				
		//根据乐器元素数组创建musicInstrumentRandom
		musicInstrumentRandom = new RandomNext(
				parameter.getMusic().getSmapleElementChoosed());
		//根据音阶元素数组创建scaleRandom
		scaleRandom = new RandomNext(
				parameter.getScale().getSmapleElementChoosed());
	}
	
	/**
	 * 开始服务
	 */
	public void start() {
		//此次测试的开始时间
		testingStartTime = System.currentTimeMillis();
		//更新数据库中此次测试的开始时间
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, userId, testingStartTime, null);
	}
	
	/**
	 * 开始答题
	 */	
	public void startAnswer(){
		startAnswerTime = System.currentTimeMillis();
	}
	
	/**
	 * 回答题目
	 */
	public HtrtResult answerQuestion(HtrtQuestion question, HtrtAnswer answer) {

		HtrtResult result = new HtrtResult();
		boolean value;

		if (question == null) { //当题目为空时,不做任何处理
			return result;
		}else if (answer==null || answer.getAnswers()==null) {
			//计错误一次
			errorCount++;
			value = false;
			//保存此道题的答题记录
			answerQuestionDao.insertAnswerQuestion(
					question.getId(), startAnswerTime, System.currentTimeMillis(), value, testingId);
			//更新开始答题时间	
			startAnswerTime = System.currentTimeMillis();
			
			return result;
		}else if (question.getId() != answer.getQuestionId()) {
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
			
			if (count == question.getAnswers().length) {//如果回答正确
				value = true;
				//计正确一次
				rightCount++;
				//分数自增指定分数
				scores += rule.getScore();
			}else {//回答错误
				errorCount++;
				value = false;
			}
		}
		
		result.setValue(value);
		result.setQuestionId(question.getId());
		result.setContinuedTime(answer.getAnswerTime().getTime()-question.getCreateTime().getTime());
		
		answerQuestionDao.insertAnswerQuestion(question.getId(), startAnswerTime, System.currentTimeMillis(), value, testingId);		
		startAnswerTime = System.currentTimeMillis();
		
		return result;
	}
	
	/**
	 * 创建题目
	 * @throws LackOfParametersException 
	 * @throws ParameterIsInvalidException 
	 */
	public HtrtQuestion createQuestion() throws LackOfParametersException, ParameterIsInvalidException {
		
		Log.d(TAG, "createQuestion()");
		if (parameter == null) {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new LackOfParametersException();
		}
		
		//questionId自增		
		questionId ++;
		
		//取出下一个乐器
		Integer musicInstrumentRedomCode = Integer.valueOf(musicInstrumentRandom.next());
		//取出下一个音阶
		Integer scaleRedomCode = Integer.valueOf(scaleRandom.next());
		
		//根据乐器音阶获得音频文件路径
		String content = CommonUtil.getMusicFilePathByInstrumentScale(
				context, musicInstrumentRedomCode, scaleRedomCode);
		String answer = new String(); 
			
		if (parameter.getTestType() == 
			Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT) {//如果测试类型是乐器			
			
			Log.d(TAG, "htrtParameter.getTestType() == " +
					"Constants.HearingTouchResponseTrainingParameter.MUSICAL_INSTRUMENT");
			
			//取出乐器对应的按键
			Map<Integer, Integer> musicInstrumentKeypadMap = 
				parameter.getMusicInstrumentKeypadMap();
			int key = musicInstrumentKeypadMap.get(musicInstrumentRedomCode);
			Log.d(TAG, "key: " + key);
			//手+按键确定按键代码,作为答案
			answer = String.valueOf(CommonUtil.getKeyCode(parameter.getHandType(), key));
			
		}else if (parameter.getTestType() == 
			Constants.HearingTouchResponseTrainingUIParameter.SCALE) {//如果测试类型是音阶
			
			Log.d(TAG, "htrtParameter.getTestType() == Constants.HearingTouchResponseTrainingParameter.SCALE");
			
			//确定该音阶在所有音阶中的高低
			int scaleLevel = this.getScaleLevel(
					parameter.getScale().getSmapleElementChoosed(), scaleRedomCode.toString());
			//取出音阶对应的按键
			Map<Integer, Integer> scaleKeypadMap = 
				parameter.getScaleKeypadMap();
			int key = scaleKeypadMap.get(scaleLevel);
			Log.d(TAG, "key: " + key);
			//手+按键确定按键代码,作为答案
			answer = String.valueOf(CommonUtil.getKeyCode(parameter.getHandType(), key));
		}else {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new ParameterIsInvalidException();
		}
		
		HtrtQuestion question = new HtrtQuestion();
		question.setId(questionId);
		question.setContents(new String[]{content});
		question.setAnswers(new String[]{answer});
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		Log.d(TAG, question.toString());
		questionCount++;
		
		return question;
	}

	
	/**
	 * 停止服务
	 */
	public void stop() {
		Log.d(TAG, "stop()");
		
		testingStopTime = System.currentTimeMillis();
		//修改此次测试的结束时间
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, userId, testingStartTime, testingStopTime);
		testingDao.close();
	}

	
	/**生成测试报告
	 * 
	 * @return
	 */
	public HtrtReport generateReport() {
		
		HtrtReport report = new HtrtReport();
		//题目总数
		report.setQuestionCount(questionCount);
		//错误数
		report.setErrorCount(errorCount);
		//正确数
		report.setRightCount(rightCount);
		//总分数
		report.setScores(scores);
		if (errorCount+rightCount != 0) {
			report.setRightPercentage(rightCount/(errorCount+rightCount));
			report.setErrorPercentage(errorCount/(errorCount+rightCount));
		}else {
			report.setRightPercentage(0);
			report.setErrorPercentage(0);
		}
				
		report.setChart(new ResponseTrainingReportChart(context, testingId, answerQuestionDao)
					.generateBarChart(testingStartTime, testingStopTime));
		
		answerQuestionDao.close();
		return report;
	}	

	
	
	/**确定音阶在一组音阶中的高中低
	 * 
	 * @param scales 一组音阶
	 * @param scale	一个音阶
	 * @return
	 */
	private int getScaleLevel(String[] scales, String scale){
		
		if (scales==null || scale==null || ArrayOperations.inElements(scales, scale)!=true) {
			return -1;
		}else {
			
			for (int i = 0; i < scales.length-1; i++) {
				for (int j = i+1; j < scales.length; j++) {
					if (Integer.valueOf(scales[i])>Integer.valueOf(scales[j])) {
						String s = scales[i];
						scales[i] = scales[j];
						scales[j] = s;
					}
				}
			}
			
			int index = ArrayOperations.indexInElement(scales, scale);
			if (scales.length==2) {				
				if (index == 0) {
					return Constants.ScaleLevel.SCALE_LOW;
				}else if (index == 1) {
					return Constants.ScaleLevel.SCALE_HIGH;
				}
			}else if (scales.length==3) {
				
				if (index == 0) {
					return Constants.ScaleLevel.SCALE_LOW;
				}else if (index == 1) {
					return Constants.ScaleLevel.SCALE_MIDDLE;
				}else if (index == 2) {
					return Constants.ScaleLevel.SCALE_HIGH;
				}
			}
			return -1;
		}		
	}
}
