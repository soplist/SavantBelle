package com.flextronics.cn.service.visiontouch;

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
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtAnswer;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtParameter;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtQuestion;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtReport;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtResult;
import com.flextronics.cn.model.visiontouch.responsetraining.VtrtRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.util.RandomNext;

/**VtrtQuestionAnswerService的实现类
 * 
 * @author ZhangGuoYin
 * 
 */
public class VtrtQuestionAnswerServiceImpl implements VtrtQuestionAnswerService {

	private final static String TAG = "VtrtQuestionAnswerServiceImpl";
	
	/**
	 * 触摸屏按键编码
	 */
	private final static String[] TOUCH_PAD_CODES = {"1","2","3","4","5","6"};
	/**
	 * 键盘按键编码(3个按键)
	 */
	private final static String[] KEY_STOKE_CODES = {String.valueOf(Constants.FORE_FINGER), 
		String.valueOf(Constants.MIDDLE_FINGER), String.valueOf(Constants.THIRD_FINGER)};
	/**
	 * 键盘按键编码(2个按键)
	 */
	private final static String[] KEY_STOKE_CODES_2 = {String.valueOf(Constants.FORE_FINGER),
		String.valueOf(Constants.THIRD_FINGER)};


	private long _id;
	/**
	 * 当前用户的ID
	 */
	private Integer userId;
	/**
	 * 参数
	 */
	private VtrtParameter parameter;
	/**
	 * 规则
	 */
	private VtrtRule rule;
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
	/**
	 * 候选元素
	 */
	private String[] candidateElements;
	/**
	 * 上次题目内容
	 */
	private String lastContent;
	private RandomNext randomLocation;
	/**
	 * 题目内容重复次数
	 */
	private int repeatTimes;
	/**
	 * 题目内容非选择的样本元素的重复次数
	 */
	private int repeatTimes2;
	/**
	 * 题目内容是选择的样本元素中的一员的重复次数
	 */
	private int repeatTimes3;
	/**
	 * 出现位置编码范围
	 */
	private String[] codes;
	private TestingDao testingDao;
	private AnswerQuestionDao answerQuestionDao;
	private Context context;
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

	
	public VtrtQuestionAnswerServiceImpl() {
		super();
		parameter = new VtrtParameter();
		rule = new VtrtRule();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		candidateElements = null;
	}

	/**
	 * 初始化服务
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException, ParameterIsInvalidException{
		//参数不能为空
		if (parameters == null) {
			Log.e(TAG, "parameters is null, it's error!");
			throw new LackOfParametersException();
		}
		parameter = (VtrtParameter)parameters.get(Constants.PARAMETER);
		rule = (VtrtRule)parameters.get(Constants.RULE);
		context = (Context)parameters.get(Constants.CONTEXT);
		
		//参数不能为空
		if (parameter == null) {
			
			Log.e(TAG, "vtrtParameter is null, it's error!");
			throw new LackOfParametersException();			
		}else if (rule == null) {
			
			Log.e(TAG, "vtrtRule is null, it's error!");
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
		
		//根据操作类型是触摸屏或按键，确定题目的编码范围 
		if (parameter.getOptionsType() == Constants.TOUCH) {
			
			Log.d(TAG, "touch pad option");
			codes = ArrayOperations.redomElements(TOUCH_PAD_CODES);
		}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {//如果是按键操作

			Log.d(TAG, "key stoke option");
			
			//如果是反射回应且原始键, 则codes取KEY_STOKE_CODES
			if (parameter.getAnswerType() == 
				Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER && 
				parameter.getResponseType() == Constants.VisioTouchResponseTrainingUIParameter.ORIGINAL_KEY_RESPONSE) {
				
				codes = ArrayOperations.redomElements(KEY_STOKE_CODES);
			}else if (parameter.getAnswerType() == 
				Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER && 
				parameter.getResponseType() == Constants.VisioTouchResponseTrainingUIParameter.OPPOSITE_KEY_RESPONSE) {
				
				codes = ArrayOperations.redomElements(KEY_STOKE_CODES_2);
			}else if (parameter.getAnswerType() == 
				Constants.VisioTouchResponseTrainingUIParameter.FIXED_POINT_ANSWER || parameter.getAnswerType() == 
				Constants.VisioTouchResponseTrainingUIParameter.INSTEAD_ANSWER) {
				
				codes = ArrayOperations.redomElements(KEY_STOKE_CODES_2);
			}else {
				Log.e(TAG, "lack of answer type or value is error");
				throw new ParameterIsInvalidException();
			}
		}else {
			Log.e(TAG, "optionsType is lack of value or value error");
			throw new ParameterIsInvalidException();
		}
		
		if (parameter.getAnswerType() == Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER){
			candidateElements = parameter.getChoosedSample().getSmapleElementChoosed();
		}

		startAnswerTime = System.currentTimeMillis();
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);
		
		//取得当前用户的userId
		/*userId = context.getSharedPreferences(
				Constants.USER_INFO, Context.MODE_PRIVATE).getInt(Constants.USER_NAME, -1);*/
		_id = testingDao.insertTesting(testingId, userId, null, null);
		Log.d(TAG, "_id: " + _id);
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
	public VtrtResult answerQuestion(VtrtQuestion question, VtrtAnswer answer) {

		VtrtResult result = new VtrtResult();
		boolean value;

		if (question == null || question.getId()==0) { //当题目为空时,不做任何处理
			return result;
		}else if (answer==null || answer.getAnswers()==null) {//当答案为空时

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

		//向数据库中增加此道题的答题记录
		answerQuestionDao.insertAnswerQuestion(question.getId(), 
				startAnswerTime, System.currentTimeMillis(), value, testingId);
		//更新startAnswerTime		
		startAnswerTime = System.currentTimeMillis();
		
		return result;
	}
	
	/**
	 * 创建题目
	 */
	public VtrtQuestion createQuestion() throws LackOfParametersException, ParameterIsInvalidException{
		
		Log.d(TAG, "createQuestion()");
		if (parameter==null) {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new LackOfParametersException();
		}		
		
		Log.d(TAG, parameter.toString());
		
		//questionId自增
		questionId ++;
		
		//取出用户选择的样本
		ChoosedSample choosedSample = parameter.getChoosedSample();
		if (choosedSample == null) {
			Log.e(TAG, "lack of choosed sample");
			throw new LackOfParametersException();
		}
		
		//如果randomLocation为空，创建一个
		if (randomLocation == null) {
			randomLocation = new RandomNext(codes);
		}
		
		//题目内容
		String[] contentsArray;
		//显示位置
		String[] locationsArray;
		//题目的标准答案
		String[] answersArray;
		//题目的临时答案
		String[] answersArrayTemp;
		
		//如果需要的位元数大于样本元素个数
		if (parameter.getBit() > 
			parameter.getChoosedSample().getSmapleElementChoosed().length) {
			int needBit = parameter.getBit() - parameter.getChoosedSample().getSmapleElementChoosed().length;
			
			if (choosedSample.getSample() == Constants.Sample.WHITE) {
				candidateElements = CommonUtil.getWhiteLight();
			}else if (choosedSample.getSample()>=Constants.Sample.COLORS && 
					choosedSample.getSample()<=Constants.Sample.PERCUSSION_INSTRUMENT && 
					choosedSample.getSample()%10000==0) {
				candidateElements = getCandidateElements(
					CommonUtil.getSampleElementsInSample(choosedSample.getSample()), 
					choosedSample.getSmapleElementChoosed(), needBit);
			}else {
				Log.e(TAG, "choosedSample is error!");
				throw new ParameterIsInvalidException();
			}
		}
		
		//将候选元素打乱
		candidateElements = ArrayOperations.redomElements(candidateElements);
		contentsArray = ArrayOperations.redomElements(candidateElements, 1);
		if (contentsArray!=null &&  contentsArray[0]!=null) {
			//题目内容跟上次一样，记一次
			if (contentsArray[0].equals(lastContent)) {
				repeatTimes++;
			}else {
				repeatTimes = 0;
			}
			
			//题目内容不是选择的样本元素，记一次
			if (!ArrayOperations.inElements(
					parameter.getChoosedSample().getSmapleElementChoosed(), contentsArray[0])) {
				repeatTimes2++;
			}else {
				repeatTimes2 = 0;
			}
			
			//题目内容是选择的样本元素中的一个，记一次
			if (ArrayOperations.inElements(
					parameter.getChoosedSample().getSmapleElementChoosed(), contentsArray[0])) {
				repeatTimes3++;
			}else {
				repeatTimes3 = 0;
			}			
			
			Log.d(TAG, "repeatTimes:  " + repeatTimes);
			Log.d(TAG, "repeatTimes2: " + repeatTimes2);
			Log.d(TAG, "repeatTimes3: " + repeatTimes3);
			
			
			if (parameter.getAnswerType() == Constants.VisioTouchResponseTrainingUIParameter.REFLECT_ANSWER) {
				//如果是反射回应, 非白光样本
				if (parameter.getChoosedSample().getSample() != Constants.Sample.WHITE) {
					//题目内容重复次数超过2次,即连续出现了3次, 需重新出题,直到不重复为止
					if (repeatTimes>=2 && candidateElements.length>1) {
						do {
							contentsArray = ArrayOperations.redomElements(candidateElements, 1);
						} while (contentsArray[0].equals(lastContent));
						repeatTimes = 0;
					}
				}
			}else {
				//如果不是反射回应
				//题目内容重复次数超过2次,即连续出现了3次, 需重新出题,直到不重复为止
				if (repeatTimes>=2 && candidateElements.length>1) {
					do {
						contentsArray = ArrayOperations.redomElements(candidateElements, 1);
					} while (contentsArray[0].equals(lastContent));
					repeatTimes = 0;
				}else if (repeatTimes3>parameter.getChoosedSample().getSmapleElementChoosed().length+1
						&& parameter.getChoosedSample().getSmapleElementChoosed().length>=1) {
					//如果repeatTimes3超过选择样本元素的个数2个,则重新出题，直到不重复为止
					do {
						contentsArray = ArrayOperations.redomElements(candidateElements, 1);
					} while (ArrayOperations.inElements(
							parameter.getChoosedSample().getSmapleElementChoosed(), contentsArray[0]));
					repeatTimes3 = 0;
					
				}else if (repeatTimes2>parameter.getChoosedSample().getSmapleElementChoosed().length+2
						&& parameter.getChoosedSample().getSmapleElementChoosed().length>=1) {
					//如果repeatTimes2超过选择样本元素的个数3个,则重新出题，直到不重复为止
					do {
						contentsArray = ArrayOperations.redomElements(candidateElements, 1);
					} while (!ArrayOperations.inElements(
							parameter.getChoosedSample().getSmapleElementChoosed(), contentsArray[0]));
					repeatTimes2 = 0;
				}
			}
			
			lastContent = contentsArray[0];
		}else {
			return null;
		}
		
		locationsArray = new String[]{randomLocation.next()};
		Log.d(TAG, "location:"+locationsArray[0]);
		
		//答案暂为显示位置的编码
		answersArrayTemp = new String[locationsArray.length];
		for (int i = 0; i < locationsArray.length; i++) {
			answersArrayTemp[i] = locationsArray[i];
		}
		
		answersArray = answersArrayTemp;
				
		//如果样本是白光
		if (choosedSample.getSample() == Constants.Sample.WHITE) {
			
			if (parameter.getOptionsType() == Constants.TOUCH) {//如果是触摸屏操作
				answersArray = answersArrayTemp;
			}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {
				//如果是按键操作, 需要根据左右手确定按键编码，最终确定答案
				for (int i = 0; i < locationsArray.length; i++) {
					answersArray[i] = String.valueOf(
							CommonUtil.getKeyCode(parameter.getHandType(), 
									Integer.valueOf(locationsArray[i])));
				}
			}
		}else {
			//遍历题目内容
			for (int i = 0; i < contentsArray.length; i++) {
				//是否为替代回应
				boolean isInsteadAnswer = parameter.getAnswerType() == 
					Constants.VisioTouchResponseTrainingUIParameter.INSTEAD_ANSWER;
				//题目内容是否在已选择的样本元素中
				boolean elementIsInChoosedSample = ArrayOperations.inElements(
						choosedSample.getSmapleElementChoosed(), contentsArray[i]);
				
				if((isInsteadAnswer&&elementIsInChoosedSample) || 
						(!isInsteadAnswer&&!elementIsInChoosedSample)){
					
					if (parameter.getOptionsType() == Constants.TOUCH) {
						answersArray[i] = "7";
					}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {
						answersArray[i] = String.valueOf(
								CommonUtil.getKeyCode(parameter.getHandType(), Constants.MIDDLE_FINGER));
					}					
				}else if ((!isInsteadAnswer&&elementIsInChoosedSample) || 
						(isInsteadAnswer&&!elementIsInChoosedSample)) {
					
					if (parameter.getOptionsType() == Constants.TOUCH) {
						answersArray[i] = locationsArray[i];
					}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {
						answersArray[i] = String.valueOf(
								CommonUtil.getKeyCode(parameter.getHandType(), Integer.valueOf(locationsArray[i])));
					}					
				}
			}		
		}		
		
		VtrtQuestion question = new VtrtQuestion();
		question.setId(questionId);		
		question.setContents(contentsArray);
		question.setLocations(locationsArray);
		question.setAnswers(answersArray);
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
	public VtrtReport generateReport() {
		
		VtrtReport report = new VtrtReport();
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
	
	private String[] getCandidateElements(String[] sampleElements, String[] smapleElementChoosed, int bit){
		
		if (sampleElements==null || smapleElementChoosed==null || bit==0) {
			return null;
		}
		Log.d(TAG, "sampleElements.length：              " + sampleElements.length);
		Log.d(TAG, "smapleElementChoosed.length:" + smapleElementChoosed.length);
		Log.d(TAG, "bit:"+bit);
		
		String[] otherElements = ArrayOperations.otherElements(
				sampleElements, smapleElementChoosed);
		otherElements = ArrayOperations.redomElements(otherElements, bit);
		return ArrayOperations.addElements(smapleElementChoosed, otherElements);
	}
}