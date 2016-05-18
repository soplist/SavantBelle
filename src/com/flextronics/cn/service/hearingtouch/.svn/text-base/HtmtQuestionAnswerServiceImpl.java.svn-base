package com.flextronics.cn.service.hearingtouch;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtAnswer;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtParameter;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtQuestion;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtReport;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtResult;
import com.flextronics.cn.model.hearingtouch.memorytraining.HtmtRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.RandomNext;

/**HtmtQuestionAnswerService的实现类
 * 
 * @author ZhangGuoYin
 *
 */
public class HtmtQuestionAnswerServiceImpl implements HtmtQuestionAnswerService {

	private final static String TAG = "HtmtQuestionAnswerServiceImpl";

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
	private SimpleDateFormat sdf;
	private HtmtParameter parameter;	
	private HtmtRule rule;
	
	private RandomNext musicInstrumentRandom;
	private RandomNext scaleRandom;
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	
	
	public HtmtQuestionAnswerServiceImpl() {
		super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		parameter = new HtmtParameter();	
		rule = new HtmtRule();
		
	}

	/**
	 * 初始化服务
	 * @throws LackOfParametersException 
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException {
		//参数不能为空
		if (parameters == null) {
			Log.e(TAG, "parameters is null, it's error!");
			throw new LackOfParametersException();
		}
		
		parameter = (HtmtParameter)parameters.get(
				Constants.PARAMETER);
		rule = (HtmtRule)parameters.get(Constants.RULE);
		context = (Context)parameters.get(Constants.CONTEXT);
		
		//参数不能为空
		if (parameter == null) {
			
			Log.e(TAG, "htmtParameter is null, it's error!");
			throw new LackOfParametersException();			
		}else if (rule == null) {
			
			Log.e(TAG, "htmtRule is null, it's error!");
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
		_id = testingDao.insertTesting(testingId, null, null, null);
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
	public HtmtResult answerQuestion(HtmtQuestion question, HtmtAnswer answer) {

		Log.d(TAG, "answerQuestion()");
		HtmtResult result = new HtmtResult();
		boolean value;

		if (question==null) { //当题目为空时,不做任何处理
			return result;
		}else if (question.getId() == 0 || question.getId()>parameter.getQuestionCount()) {
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
	 * 生成题目
	 * @throws LackOfParametersException 
	 */
	public HtmtQuestion createQuestion() throws LackOfParametersException {
		Log.d(TAG, "createQuestion()");
		if (parameter == null) {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new LackOfParametersException();
		}
		
		//questionId自增		
		questionId ++;
		
		String[] contentArray = null;
		String[] answerArray = null;
					
		//如果测试类型是乐器反应
		if (parameter.getTestType() == 
			Constants.HearingTouchResponseTrainingUIParameter.MUSICAL_INSTRUMENT) {
			
			Log.d(TAG, "htmtParameter.getTestType() == Constants.HearingTouchMemoryTrainingParameter.MUSICAL_INSTRUMENT");
			
			Integer scaleRedomCode = Integer.valueOf(scaleRandom.next());
			//将乐器做随机操作
			String[] musicInstrumentChoosedRedom = ArrayOperations.redomElements(
					parameter.getMusic().getSmapleElementChoosed());
			
			int musicCode = parameter.getMusic().getSample();
			contentArray = new String[musicInstrumentChoosedRedom.length];
			answerArray = new String[musicInstrumentChoosedRedom.length];
			for (int i = 0; i < musicInstrumentChoosedRedom.length; i++) {
				
				//get the music file path by music instrument and scale
				contentArray[i] = CommonUtil.getMusicFilePathByInstrumentScale(context, 
						Integer.valueOf(musicInstrumentChoosedRedom[i]), scaleRedomCode);
				Log.d(TAG, "contentArray[i]: " + contentArray[i]);
				
				//make sure the finger by music
				int key = -1;
				switch (musicCode) {
				case Constants.Sample.FOREIGH_MUSIC:
					
					if (ArrayOperations.inElements(Constants.ForeignMusic.INSTRUMENTS_FORE_FINGER_AREA, 
							musicInstrumentChoosedRedom[i])) {
						key = Constants.FORE_FINGER;
					}else if (ArrayOperations.inElements(Constants.ForeignMusic.INSTRUMENTS_MIDDLE_FINGER_AREA, 
							musicInstrumentChoosedRedom[i])) {
						key = Constants.MIDDLE_FINGER;
					}else if (ArrayOperations.inElements(Constants.ForeignMusic.INSTRUMENTS_THIRD_FINGER_AREA,
							musicInstrumentChoosedRedom[i])) {
						key = Constants.THIRD_FINGER;
					}
					break;
				case Constants.Sample.FOLK_MUSIC:

					if (ArrayOperations.inElements(Constants.FolkMusic.INSTRUMENTS_FORE_FINGER_AREA, 
							 musicInstrumentChoosedRedom[i])) {
						key = Constants.FORE_FINGER;
					}else if (ArrayOperations.inElements(Constants.FolkMusic.INSTRUMENTS_MIDDLE_FINGER_AREA, 
							musicInstrumentChoosedRedom[i])) {
						key = Constants.MIDDLE_FINGER;
					}else if (ArrayOperations.inElements(Constants.FolkMusic.INSTRUMENTS_THIRD_FINGER_AREA,
							musicInstrumentChoosedRedom[i])) {
						key = Constants.THIRD_FINGER;
					}					
					break;
				case Constants.Sample.PERCUSSION_INSTRUMENT:

					if (ArrayOperations.inElements(Constants.PercussionInstrument.INSTRUMENTS_FORE_FINGER_AREA, 
							 musicInstrumentChoosedRedom[i])) {
						key = Constants.FORE_FINGER;
					}else if (ArrayOperations.inElements(Constants.PercussionInstrument.INSTRUMENTS_MIDDLE_FINGER_AREA, 
							musicInstrumentChoosedRedom[i])) {
						key = Constants.MIDDLE_FINGER;
					}else if (ArrayOperations.inElements(Constants.PercussionInstrument.INSTRUMENTS_THIRD_FINGER_AREA, 
							musicInstrumentChoosedRedom[i])) {
						key = Constants.THIRD_FINGER;
					}
					break;

				default:
					break;
				}
				
				Log.d(TAG, "key: " + key);
				answerArray[i] = String.valueOf(CommonUtil.getKeyCode(parameter.getHandType(), key));
				Log.d(TAG, "answerArray[i]: " + answerArray[i]);
			}
			
		}else if (parameter.getTestType() == 
			Constants.HearingTouchResponseTrainingUIParameter.SCALE) {
			
			Log.d(TAG, "htmtParameter.getTestType() == Constants.HearingTouchMemoryTrainingParameter.SCALE");			
			
			Integer musicInstrumentRedomCode = Integer.valueOf(musicInstrumentRandom.next());
			
			String[] scaleChoosedRedom = ArrayOperations.redomElements(
					parameter.getScale().getSmapleElementChoosed());
			
			contentArray = new String[scaleChoosedRedom.length];
			answerArray = new String[scaleChoosedRedom.length];
			for (int i = 0; i < scaleChoosedRedom.length; i++) {
				
				//get the music file path by music instrument and scale
				contentArray[i] = CommonUtil.getMusicFilePathByInstrumentScale(context, 
						musicInstrumentRedomCode, Integer.valueOf(scaleChoosedRedom[i]));
				Log.d(TAG, "contentArray[i]: " + contentArray[i]);
				
				//make sure the finger by scale
				int key = -1;								
				if (scaleChoosedRedom[i].equals("1") || scaleChoosedRedom[i].equals("4")//scale 1,4,7 -- third finger
						|| scaleChoosedRedom[i].equals("7")) {
					key = Constants.THIRD_FINGER;
				}else if (scaleChoosedRedom[i].equals("2") || scaleChoosedRedom[i].equals("5")) {//scale 2,5 -- middle finger
					key = Constants.MIDDLE_FINGER;
				}else if (scaleChoosedRedom[i].equals("3") || scaleChoosedRedom[i].equals("6") //scale 3,6,8 -- fore finger
						|| scaleChoosedRedom[i].equals("8")) {
					key = Constants.FORE_FINGER;
				}				
				
				Log.d(TAG, "key: " + key);
				answerArray[i] = String.valueOf(CommonUtil.getKeyCode(parameter.getHandType(), key));
				Log.d(TAG, "answerArray[i]: " + answerArray[i]);
			}
		}
		
		HtmtQuestion question = new HtmtQuestion();
		question.setId(questionId);
		question.setContents(contentArray);
		question.setAnswers(answerArray);
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
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
	public HtmtReport generateTestReport() {
		
		HtmtReport report = new HtmtReport();
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
		report.setChart(new MemoryTrainingReportChart(context, testingId, answerQuestionDao)
			.generateBarChart(parameter.getQuestionCount()));
		
		answerQuestionDao.close();
		return report;
	}
}
