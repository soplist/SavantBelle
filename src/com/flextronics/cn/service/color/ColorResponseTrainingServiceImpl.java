package com.flextronics.cn.service.color;

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
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingAnswer;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingParameter;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingQuestion;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingReport;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingResult;
import com.flextronics.cn.model.color.responsetraining.ColorResponseTrainingRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.RandomNext;

/**ColorResponseTrainingService的实现类
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorResponseTrainingServiceImpl implements ColorResponseTrainingService {

	private final static String TAG = "ColorResponseTrainingServiceImpl";
	
	/**
	 * 调用该service的activity的context,用于创建数据库操作对象
	 */
	private Context context;
	/**
	 * 颜色反应训练的参数
	 */
	private ColorResponseTrainingParameter parameter;
	/**
	 * 规则
	 */
	private ColorResponseTrainingRule rule;
	
	/**
	 * 题目编号
	 */
	private static long questionId;
	/**
	 * 题目总数
	 */
	private static int questionCount;
	/**
	 * 答错数目
	 */
	private static int errorCount;
	/**
	 * 答对数目
	 */
	private static int rightCount;
	/**
	 * 得分
	 */
	private static int scores;

	
	private long _id;
	private long testingId;
	/**
	 * 数据库操作
	 */
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	/**
	 * 格式化当前时间
	 */
	private SimpleDateFormat sdf;
	/**
	 * 整场测试开始时间
	 */
	private long testingStartTime;
	/**
	 * 整场测试结束时间
	 */
	private long testingStopTime;
	/**
	 * 当前题目开始回答时间
	 */
	private long startAnswerTime;
	
	/**
	 * 用于对颜色、显示形体进行随机，以最大程度保证前后不出现相同元素
	 */
	private RandomNext colorRandom;
	private RandomNext displayBodyRandom;
	
	public ColorResponseTrainingServiceImpl() {
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");//年月日时分秒毫秒
		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;
	}

	/**根据参数初始化服务
	 * 
	 * @param parameters
	 * @throws LackOfParametersException 
	 * @throws Exception 
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException{
		
		if (parameters == null) {
			Log.e(TAG, "init parameter is null!");
			throw new LackOfParametersException();
		}
		//分离出各参数值
		parameter = (ColorResponseTrainingParameter)parameters.get(
				Constants.PARAMETER);
		rule = (ColorResponseTrainingRule)parameters.get(Constants.RULE);
		context = (Context)parameters.get(Constants.CONTEXT);
		
		if (parameter == null) {
			Log.e(TAG, "parameter is null!");
			throw new LackOfParametersException();
		}
		if (rule == null) {
			Log.e(TAG, "rule is null!");
			throw new LackOfParametersException();
		}
		if (context == null) {
			Log.e(TAG, "context is null!");
			throw new LackOfParametersException();
		}
		
		//创建数据库操作 
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		
		startAnswerTime = System.currentTimeMillis();
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "init() -- testingId: " + testingId);
		
		//向数据库中保存此次测试记录
		_id = testingDao.insertTesting(testingId, null, null, null);
		Log.d(TAG, "init() -- insert testing success, _id: " + _id);
				
		//根据颜色样本元素创建RandomNext对象
		colorRandom = new RandomNext(
				parameter.getColor().getSmapleElementChoosed());
		//根据显示形体创建RandomNext对象
		displayBodyRandom = new RandomNext(
				ArrayOperations.toStringArray(parameter.getDisplayBody()));
	}
	
	/**
	 * 开始服务
	 */
	public void start() {
		//此次测试的开始时间
		testingStartTime = System.currentTimeMillis();
		//更新数据库中此次测试的开始时间
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, null);
	}
	
	/**
	 * 开始答题
	 */	
	public void startAnswer(){
		startAnswerTime = System.currentTimeMillis();
	}
	
	/**回答题目，并返回回答结果
	 * 
	 * @param question
	 * @param answer
	 * @return
	 */
	public ColorResponseTrainingResult answerQuestion(
			ColorResponseTrainingQuestion question, ColorResponseTrainingAnswer answer) {

		ColorResponseTrainingResult result = new ColorResponseTrainingResult();
		boolean value;	
		
		if (question == null) { //当题目为空时,不做任何处理
			return result;
		}else if (answer == null) {//当答案为空时
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
		}else if (!question.getAnswer().equals(answer.getAnswer())) {
			errorCount++;
			value = false;
		}else {
			//计答对一次
			rightCount++;
			//加分
			scores += rule.getScore();
			value = true;
		}
		
		Log.d(TAG, "question answer: " + question.getAnswer());
		Log.d(TAG, "answer answer: " + answer.getAnswer());
		
		result.setQuestionId(question.getId());
		result.setValue(value);
		result.setContinuedTime(answer.getAnswerTime().getTime()-question.getCreateTime().getTime());
		
		//保存此道题的答题记录
		answerQuestionDao.insertAnswerQuestion(
				question.getId(), startAnswerTime, System.currentTimeMillis(), value, testingId);		
		startAnswerTime = System.currentTimeMillis();
		
		return result;
	}
	
	/**创建题目
	 * 
	 * @return
	 */
	public ColorResponseTrainingQuestion createQuestion() {
		
		Log.d(TAG, "createQuestion()");
		
		//题目编号自增1
		questionId ++;
		//如果已经超过此次测试的总题数
		if (questionId > parameter.getQuestionCount()) {
			Log.e(TAG, "We have created " + parameter.getQuestionCount() + 
					" questions. We can not create question more.");
			return null;
		}
		Log.d(TAG, "question id is " + questionId);
		
		//创建题目
		ColorResponseTrainingQuestion question = new ColorResponseTrainingQuestion();
		question.setId(questionId);
		//题目内容为colorRandom的下一个值
		//使用RandomNext的目的在于保证尽最大可能地让题目不会重复
		question.setContent(colorRandom.next());
		//答案的内容跟题目一样
		question.setAnswer(question.getContent());
		//显示形体为displayTypeRandom的下一个值
		question.setDisplayBody(displayBodyRandom.next());
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
				
		//题目总数自增1
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
		testingDao.updateTesting(
				Integer.valueOf(_id+""), testingId, null, testingStartTime, testingStopTime);
		//关闭testingDao
		testingDao.close();
	}
	
	/**生成测试报告
	 * 
	 * @return
	 */
	public ColorResponseTrainingReport generateReport() {
		
		ColorResponseTrainingReport report = new ColorResponseTrainingReport();
		//题目总数
		report.setQuestionCount(questionCount);
		//错误数
		report.setErrorCount(errorCount);
		//正确数
		report.setRightCount(rightCount);
		//总分数
		report.setScores(scores);
		//正确比例,错误比例
		if (errorCount+rightCount != 0) {
			report.setRightPercentage(rightCount/(errorCount+rightCount));
			report.setErrorPercentage(errorCount/(errorCount+rightCount));
		}else {
			report.setRightPercentage(0);
			report.setErrorPercentage(0);
		}
		
		//创建柱状图
		report.setChart(new MemoryTrainingReportChart(context, testingId, answerQuestionDao)
			.generateBarChart(questionCount));
		
		//关闭answerQuestionDao
		answerQuestionDao.close();
		return report;
	}	
}