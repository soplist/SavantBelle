package com.flextronics.cn.service.color;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.exception.CanNotSupportSuchBitsException;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.OutOfMaxQuestionsException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingAnswer;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingParameter;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingQuestion;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingReport;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingResult;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**ColorMemoryTrainingService的实现类
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorMemoryTrainingServiceImpl implements ColorMemoryTrainingService {

	private final static String TAG = "ColorMemoryTrainingServiceImpl";

	private long _id;
	/**
	 * 测试编号
	 */
	private long testingId;
	/**
	 * 问题编号
	 */
	private static long questionId;
	/**
	 * 问题数目
	 */
	private static int questionCount;
	/**
	 * 错误数
	 */
	private static int errorCount;
	/**
	 * 正确数
	 */
	private static int rightCount;
	/**
	 * 分数
	 */
	private static int scores;
	
	/**
	 * 位元数
	 */
	private int bits;
	/**
	 * 开始位元
	 */
	private Integer startBit;
	
	/**
	 * 测试记录数据库操作对象
	 */
	private TestingDao testingDao;
	/**
	 * 回答记录数据库操作对象
	 */
	private AnswerQuestionDao answerQuestionDao;
	
	/**
	 * 调用此service的activity的context
	 */
	private Context context;
	
	private SimpleDateFormat sdf;
	/**
	 * 测试开始时间
	 */
	private long testingStartTime;
	/**
	 * 测试停止时间
	 */
	private long testingStopTime;
	/**
	 * 开始答题时间
	 */
	private long startAnswerTime;

	/**
	 * 颜色记忆训练参数
	 */
	private ColorMemoryTrainingParameter parameter;
	/**
	 * 颜色记忆训练规则
	 */
	private ColorMemoryTrainingRule rule;	
	private int[] displayBodys;
	
	public ColorMemoryTrainingServiceImpl() {
		super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");	//年月日时分秒毫秒
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
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException{		
		
		if(parameters == null){
			Log.e(TAG, "init error, parameters is null");
			throw new LackOfParametersException();			
		}
		parameter = (ColorMemoryTrainingParameter)parameters.get(
				Constants.PARAMETER);
		rule = (ColorMemoryTrainingRule)parameters.get(Constants.RULE);		
		context = (Context)parameters.get(Constants.CONTEXT);
		
		if (parameter == null) {
			Log.e(TAG, "init error, paameter is null");
			throw new LackOfParametersException();
		}else if (rule == null) {
			Log.e(TAG, "init error, rule is null");
			throw new LackOfParametersException();			
		}else if (context == null) {
			Log.e(TAG, "init error, context is null");
			throw new LackOfParametersException();			
		}
		
		displayBodys = parameter.getDisplayBodys();
		
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		
		startBit = parameter.getStartBit();	
		startAnswerTime = System.currentTimeMillis();
		
		//将当前时间格式化为此次测试的ID
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);
		
		//向数据库中保存此次测试记录
		_id = testingDao.insertTesting(testingId, null, null, null);
		Log.d(TAG, "_id: " + _id);
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

	
	/**开始答题
	 * 
	 */	
	public void startAnswer(){
		startAnswerTime = System.currentTimeMillis();
	}
	
	
	/**创建题目
	 * 
	 * @return
	 * @throws LackOfParametersException
	 * @throws OutOfMaxQuestionsException 
	 * @throws ParameterIsInvalidException 
	 * @throws CanNotSupportSuchBitsException 
	 */
	public ColorMemoryTrainingQuestion createQuestion() throws LackOfParametersException, 
			OutOfMaxQuestionsException, ParameterIsInvalidException, CanNotSupportSuchBitsException {
		
		Log.d(TAG, "createQuestion()");
		//parameter不允许为空
		if (parameter == null) {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new LackOfParametersException();
		}

		//questionId自增
		questionId ++;
		
		//如果不是连续位元，题目最多只能出指定个
		if (parameter.getBitType() != Constants.BitType.CONTINUED_BIT) {
			if (questionId > parameter.getQuestionCount()) {
				Log.e(TAG, "We have created " + parameter.getQuestionCount() + 
						" questions. We can not create any questions more.");
				throw new OutOfMaxQuestionsException();
			}
		}
		Log.d(TAG, "question id is " + questionId);
				
		//如果的指定位元,则位元数是4
		if (parameter.getBitType() == Constants.BitType.GIVEN_BIT) {
			Log.d(TAG, "default bits: 4");
			bits = 4;
		}else if (parameter.getBitType() == Constants.BitType.CONTINUED_BIT) {
			//如果连续位元,位元数将自动增长
			Log.d(TAG, "bits will automatically crease");
			//每阶段满5题,位元增1
			if (questionId%5==1 && questionId>1) {
				startBit++;
			}
			bits = startBit;
		}else if (parameter.getBitType()>=3 && parameter.getBitType()<=36) {
			//3-36之间为指定位元
			Log.d(TAG, "bits value is given by user");
			bits = parameter.getBitType();
		}else {
			Log.e(TAG, "BitType is lack of value or value error, we stop creating question.");
			throw new ParameterIsInvalidException();
		}
		Log.d(TAG, "bits is " + bits);
		
		//颜色样本不能空
		ChoosedSample color = parameter.getColor();
		if (color == null) {
			Log.e(TAG, "lack of choosed sample, we stop creating question.");
			throw new LackOfParametersException();
		}
		

		//题目内容
		String[] contentsArray = null;
		//显示位置
		String[] locationsArray = null;
		//题目答案
		String[] answersArray = null;
		//显示形体
		String[] displayBodyArray = null;
		//从颜色样本中取出bits位元素组合成题目内容，要求尽量不允许出现连续重复元素
		contentsArray = ArrayOperations.redomElements(color.getSmapleElementChoosed(), bits);
		//答案和题目一样
		answersArray = contentsArray;
		
		//显示形体不能缺少
		if (displayBodys == null) {
			Log.e(TAG, "lack of display body, we stop creating question.");
			throw new LackOfParametersException();
		}
		
		if(displayBodys.length == 1){
			if(displayBodys[0] == Constants.DisplayBody.POINT || 
					displayBodys[0] == Constants.DisplayBody.LINE || 
					displayBodys[0] == Constants.DisplayBody.CURVE || 
					ArrayOperations.inElements(Constants.Shapes.SHAPES, String.valueOf(displayBodys[0]))){
				//如果参数显示形体是点,线,曲线;题目的显示形体即为参数提供的显示形体
				displayBodyArray = new String[bits];
				for (int i = 0; i < displayBodyArray.length; i++) {
					displayBodyArray[i] = displayBodys[0]+"";
				}
				
			}else if (displayBodys[0]>=Constants.DisplayBody.TuYang.LINE &&
					displayBodys[0]<=Constants.DisplayBody.TuYang.PENTAGON) {
				//如果显示形体是图样
				
				//因为每个图样所能支持的显示位数不同,因此位元不能超过该图样支持的最大位元数
				if (bits > CommonUtil.getCanSupportedMaxBitByTuYang(displayBodys[0])) {
					Log.e(TAG, "The display body in TuYang which code is " + displayBodys[0] + 
							" can not support " + bits + " elements. We must stop creating question.");
					throw new CanNotSupportSuchBitsException();
				}
				
				//显示形体为图样的,需要考虑显示顺序
				//如果显示顺序为按序号,则显示位置从1开始
				if (parameter.getDisplayOrder() == Constants.DisplayOrder.ORDER_BY_INDEX) {
					locationsArray = new String[bits];
					for (int i = 0; i < bits; i++) {
						locationsArray[i] = (i+1)+"";
					}
				}else if (parameter.getDisplayOrder() == Constants.DisplayOrder.NOT_ORDER_BY_INDEX) {
					//如果显示顺序不按序号,则显示位置是乱的				
					
					//如果显示形体是圆形
					if (displayBodys[0] == Constants.DisplayBody.TuYang.CIRCLE) {
						//location只能在1-bits之间取
						String[] _locationsArray = new String[bits];
						for (int i = 0; i < bits; i++) {
							_locationsArray[i] = (i+1)+"";
						}
						locationsArray = ArrayOperations.redomElements(_locationsArray);
						
					}else if (displayBodys[0] == Constants.DisplayBody.TuYang.SECTOR) {
						//如果显示形体是扇形
						int max = bits;
						if (bits>=13 && bits<=15) {
							max = 15;
						}else if (bits>=16 && bits<=18) {
							max = 18;
						}else if (bits>=19 && bits<=20) {
							max = 20;
						}else if (bits>=21 && bits<=24) {
							max = 24;
						}else if (bits>=25 && bits<=30) {
							max = 30;
						}
											
						String[] _locationsArray = new String[max];
						for (int i = 0; i < _locationsArray.length; i++) {
							_locationsArray[i] = (i+1)+"";
						}
						locationsArray = ArrayOperations.redomElements(_locationsArray, bits);
						
					}else {
						//location可在1-该图样支持的最大位元值之间取
						String[] locations = new String[CommonUtil.getCanSupportedMaxBitByTuYang(displayBodys[0])];
						for (int i = 0; i < locations.length; i++) {
							locations[i] = (i+1)+"";
						}
						locationsArray = ArrayOperations.redomElements(locations, bits);
					}				
					
					//在不按序号显示的情况下,存在同步显示同时消失的情况,
					//题目的答案则按序号回答
					if (parameter.getDisplayMode() == Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
						
						//将locationsArray复制一份,并按升序排列
						String[] locationArray2 = new String[bits];
						for (int i = 0; i < locationsArray.length; i++) {
							locationArray2[i] = locationsArray[i];
						}
						for (int i = 0; i < locationArray2.length-1; i++) {
							for (int j = i+1; j < locationArray2.length; j++) {
								if (Integer.valueOf(locationArray2[i]) > Integer.valueOf(locationArray2[j])) {
									String s = locationArray2[i];
									locationArray2[i] = locationArray2[j];
									locationArray2[j] = s;
									s = null;
								}
							}
						}
						
						//重新确定答案
						answersArray = new String[bits];
						for (int i = 0; i < locationArray2.length; i++) {						
							answersArray[i] = contentsArray[ArrayOperations.indexInElement(locationsArray, locationArray2[i])];
						}
					}
				}else {
					Log.e(TAG, "lack of display order, we stop creating question.");
					throw new LackOfParametersException();
				}
			}/*else {//如果显示形体是图案
				
			}*/
		}else if(displayBodys.length > 1){
			//如果参数显示形体是形状,则题目的显示形体即是由形状各元素组成
			displayBodyArray = ArrayOperations.redomElements(ArrayOperations.toStringArray(displayBodys), bits);
		}		
		
		//创建颜色记忆训练问题对象
		ColorMemoryTrainingQuestion question = new ColorMemoryTrainingQuestion();
		//编号
		question.setId(questionId);
		//题目内容
		question.setContents(contentsArray);
		//显示位置
		question.setLocations(locationsArray);
		//显示形体
		question.setDisplayBody(displayBodyArray);
		//题目答案
		question.setAnswers(answersArray);
		//创建时间
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		//题目总数自增
		questionCount++;
		
		Log.d(TAG, question.toString());
		return question;
	}
	
	
	/**回答题目，并返回回答结果
	 * 
	 * @param question
	 * @param answer
	 * @return
	 */
	public ColorMemoryTrainingResult answerQuestion(
			ColorMemoryTrainingQuestion question, ColorMemoryTrainingAnswer answer) {
		Log.d(TAG, "answerQuestion()");
		
		//创建结果对象
		ColorMemoryTrainingResult result = new ColorMemoryTrainingResult();
		boolean value;

		if (question == null || question.getId() == 0) { //当题目为空时,不做任何处理
			return result;
		}else if (parameter.getBitType()!=Constants.BitType.CONTINUED_BIT &&
				question.getId()>parameter.getQuestionCount()) {
			//如果非连续位元且题目总数大于参数指定题目数,则不做任何处理
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
				scores += rule.getScore();
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
	
	
	/**停止服务
	 * 
	 */
	public void stop() {
		Log.d(TAG, "stop()");
		
		//服务停止时间
		testingStopTime = System.currentTimeMillis();
		//更新此次测试的停止时间
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, testingStopTime);
		//关闭数据库操作
		testingDao.close();
	}
	
	
	/**生成测试报告
	 * 
	 * @return
	 */
	public ColorMemoryTrainingReport generateReport() {
		//创建测试报告对象
		ColorMemoryTrainingReport report = new ColorMemoryTrainingReport();
		//此次测试编号
		report.setTestingId(testingId);
		//问题数目
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
		
		//如果不是连续位元,生成图表
		if (parameter.getBitType() != Constants.BitType.CONTINUED_BIT) {
			//生成图并付给测试报告
			report.setChart(new MemoryTrainingReportChart(context, testingId, answerQuestionDao)
				.generateBarChart(parameter.getQuestionCount()));			
		}		
		//关闭数据库操作
		answerQuestionDao.close();
		
		return report;
	}	
}