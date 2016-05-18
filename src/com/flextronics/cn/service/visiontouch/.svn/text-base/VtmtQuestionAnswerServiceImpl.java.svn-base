package com.flextronics.cn.service.visiontouch;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.exception.CanNotSupportSuchBitsException;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.OutOfMaxQuestionsException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtAnswer;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtParameter;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtQuestion;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtReport;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtResult;
import com.flextronics.cn.model.visiontouch.memorytraining.VtmtRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**
 * VtmtQuestionAnswerService的实现类
 * @author ZhangGuoYin
 *
 */
public class VtmtQuestionAnswerServiceImpl implements VtmtQuestionAnswerService {

	private final static String TAG = "VtmtQuestionAnswerServiceImpl";

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
	private final static String[] TOUCH_PAD_CODES = {"1","2","3","4","5","6","7","8","9"};
	private final static String[] KEY_STOKE_CODES = {String.valueOf(Constants.FORE_FINGER),
		String.valueOf(Constants.MIDDLE_FINGER),String.valueOf(Constants.THIRD_FINGER)};

	/**
	 * 记忆训练参数
	 */
	private VtmtParameter parameter = new VtmtParameter();
	/**
	 * 记忆训练规则
	 */
	private VtmtRule rule;

	private String[] codes;
	/**
	 * 单一样本群中的样本元素
	 */
	private String[] elementsInSingleSample;
	/**
	 * 多种样本群的样本元素集合
	 */
	private String[][] elementsInMultSample;
	/**
	 * 所有样本群的样本群编码
	 */
	private String[] sampleIdStrs;
	
	
	public VtmtQuestionAnswerServiceImpl() {
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
		
		parameter = (VtmtParameter)parameters.get(
				Constants.PARAMETER);
		rule = (VtmtRule)parameters.get(Constants.RULE);
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
		
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		
		startBit = parameter.getStartBit();		
		startAnswerTime = System.currentTimeMillis();
		
		//确定测试的答题方式，触摸屏or按键
		if (parameter.getOptionsType() == Constants.TOUCH) {
			Log.d(TAG, "touch pad option");
			codes = TOUCH_PAD_CODES;
		}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {
			Log.d(TAG, "key stoke option");
			codes = KEY_STOKE_CODES;
		}else {
			Log.e(TAG, "optionsType is lack of value or value error");
		}
		
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
	public VtmtQuestion createQuestion() throws 
		LackOfParametersException, ParameterIsInvalidException{
		
		Log.d(TAG, "createQuestion()");
		if (parameter==null || codes==null) {
			Log.e(TAG, "parameter is null, we stop creating question.");
			throw new LackOfParametersException();
		}
		
		Log.d(TAG, parameter.toString());
		
		//questionId自增
		questionId ++;
				
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
			Log.e(TAG, "BitType is lack of value or value error");
			throw new ParameterIsInvalidException();
		}
				
		//取得选择的样本群
		ChoosedSample[] choosedSamples = parameter.getChoosedSamples();
		if (choosedSamples == null) {
			Log.e(TAG, "lack of choosed samples");
			throw new LackOfParametersException();
		}
		
		//String[] candidateElements = null;		//将生成题目的候选元素
		String[] contentsArray = new String[bits];;		//题目的内容
		String[] locationsArray = null;	//题目的显示位置
		String[] answersArray = null;		//题目的答案
		String[] answersArrayTemp;		
		//候选答题按钮按键值
		String[][] buttonValues = null;
		
		/***********************生成题目的content***********************/
		//如果是单一受测样本,当设定为白光的时候，显示内容只能是白光;
		//当是其他样本的时候，显示内容就是从该样本中随机出bits位元素
		if(choosedSamples.length == 1){
			if (choosedSamples[0].getSample() == Constants.Sample.WHITE) {
				//如果设定单一受测样本,且为白光
				//题目的内容即是白光
				for (int i = 0; i < bits; i++) {
					contentsArray[i] = String.valueOf(Constants.WhiteLight.WHITELIGHT);
				}				
			} else if(choosedSamples[0].getSample()>=Constants.Sample.COLORS &&
					choosedSamples[0].getSample()<=Constants.Sample.PERCUSSION_INSTRUMENT &&
					choosedSamples[0].getSample()%10000==0){

				//如果样本为从颜色到打击乐中的一种,则从该样本中取得bits位
				if (elementsInSingleSample == null) {
					elementsInSingleSample = CommonUtil.getSampleElementsInSample(choosedSamples[0].getSample());
				}
				contentsArray = ArrayOperations.redomElements(elementsInSingleSample, bits);
			} else {
				Log.e(TAG, "choosed sample is error, we stop creating question.");
				throw new ParameterIsInvalidException();
			}
		}else if(choosedSamples.length > 1){//如果是多种受测样本

			//根据样本群取得该样本群中元素
			if (elementsInMultSample == null) {
				elementsInMultSample = new String[choosedSamples.length][];
				for (int i = 0; i < elementsInMultSample.length; i++) {
					elementsInMultSample[i] = CommonUtil.getSampleElementsInSample(choosedSamples[i].getSample());
				}
			}
			//从诸多样本群元素中取得bits位
			contentsArray = ArrayOperations.redomElements(elementsInMultSample, bits);
		}		
		
		/***********************生成题目的location***********************/
		locationsArray = ArrayOperations.redomElements(codes, bits);
				
		/***********************生成题目的answers***********************/
		if (parameter.getPresentType() == Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_LOCATION) {
			//order by the present location 
			Log.d(TAG, "order by sample location");

			answersArrayTemp = new String[locationsArray.length];
			for (int i = 0; i < locationsArray.length; i++) {
				answersArrayTemp[i] = locationsArray[i];
			}
			answersArray = answersArrayTemp;
			
			if (parameter.getOptionsType() == Constants.TOUCH) {//the operation type is touch screen
				answersArray = answersArrayTemp;
			}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {//the operation type is press key
				for (int i = 0; i < locationsArray.length; i++) {
					answersArray[i] = String.valueOf(
							CommonUtil.getKeyCode(parameter.getHandType(), 
									Integer.valueOf(locationsArray[i])));
				}
			}
			
		}else if (parameter.getPresentType() == Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_ELEMENT) {
			//order by the sample element
			Log.d(TAG, "order by sample element order in sample group");
			
			if (parameter.getOptionsType() == Constants.TOUCH) {//the operation type is touch screen
				answersArray = contentsArray;
			}else if (parameter.getOptionsType() == Constants.KEY_STOKE) {//the operation type is press key, it's wrong
				Log.e(TAG, "There is no key stoke operation for visio touch memory training -- sample element");
				throw new ParameterIsInvalidException();
			}
			
		}else {
			Log.e(TAG, "presentType is lack of value");
			throw new ParameterIsInvalidException();
		}		
		
		/******************根据选择的样本群创建候选操作按键值:每次生成的按钮编码，
		 * 需要包含答案编码的值，此外还需要增加几个******************/
		
		if (parameter.getPresentType() == Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_ELEMENT) {

			buttonValues = new String[choosedSamples.length][];
			
			if (choosedSamples.length==1) {//如果样本群为单一样本群
				if(choosedSamples[0].getSample() == Constants.Sample.WHITE){	//是白光			
					String[] samples = ArrayOperations.redomElements(
							ArrayOperations.otherElements(CommonUtil.getSamples(), new String[]{Constants.Sample.WHITE+""}), 1);				
					buttonValues[0] = ArrayOperations.addElements(Constants.WhiteLight.LIGHTS, 
							ArrayOperations.redomElements(CommonUtil.getSampleElementsInSample(Integer.valueOf(samples[0])), 3));
				}else {//不是白光
					buttonValues[0] = elementsInSingleSample;
				}
			}else if (choosedSamples.length > 1) {//如果样本群为多种样本
				
				if (sampleIdStrs == null) {
					//将样本群编号组装到数组中，并按升序排列
					sampleIdStrs = new String[choosedSamples.length];
					for (int i = 0; i < choosedSamples.length; i++) {
						sampleIdStrs[i] = choosedSamples[i].getSample()+"";
					}
					sampleIdStrs = sortArrays(sampleIdStrs);	
				}
				
				//遍历样本元素编号
				for (int i = 0; i < sampleIdStrs.length; i++) {
					List<String> list = new ArrayList<String>();
					//根据样本群编号取得该样本群内所有元素
					String[] sampleElements = CommonUtil.getSampleElementsInSample(Integer.valueOf(sampleIdStrs[i]));
					//遍历题目内容
					for (int j = 0; j < contentsArray.length; j++) {
						//如果题目内容属于编号为sampleIdStrs[i]中的元素
						//将该元素添加到list中,该list中的数据唯一
						Log.d(TAG, contentsArray[j]);
						Log.d(TAG, sampleIdStrs[i].substring(0, sampleIdStrs[i].length()-1));
						Log.d(TAG, contentsArray[j].startsWith(sampleIdStrs[i].substring(0, sampleIdStrs[i].length()-2), 0)+"");
						if (contentsArray[j].startsWith(sampleIdStrs[i].substring(0, sampleIdStrs[i].length()-2), 0)) {
							if (!list.contains(contentsArray[j])) {
								list.add(contentsArray[j]);
								//如果此时list的大小已和该样本群内元素个数相同
								//表明该样本群内元素在题目中全部出现了，可以跳出循环
								if (list.size() == sampleElements.length) {
									break;
								}
							}
						}
					}
					
					//若list中有数据
					if (list!=null && list.size()>0 && list.get(0)!=null) {
						//若list中数据小于元素个数
						if (list.size() < sampleElements.length) {
							//从该样本群中未选择的元素中最多取3位
							int more = sampleElements.length - list.size();
							more = more>3?3:more;
							String[] otherElements = ArrayOperations.redomElements(
									ArrayOperations.otherElements(sampleElements, list.toArray(new String[list.size()])), more);
							//将取得的元素添加到list中
							list.addAll(Arrays.asList(otherElements));
						}
						
						//将list做升序处理
						Collections.sort(list);
						//将list转换为数组，并赋值给buttonValues[i]
						buttonValues[i] = (String[])list.toArray(new String[list.size()]);
					}
				}
			}
		}
		
		
		VtmtQuestion question = new VtmtQuestion();
		//编号
		question.setId(questionId);
		//创建时间
		question.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//题目内容
		question.setContents(contentsArray);
		//显示位置
		question.setLocations(locationsArray);
		//题目答案
		question.setAnswers(answersArray);
		//候选答题按钮按键值
		question.setButtonValues(buttonValues);
		Log.d(TAG, question.toString());
		
		//题目总数自增
		questionCount++;
		return question;
	}
	
	/**回答题目，并返回回答结果
	 * 
	 * @param question
	 * @param answer
	 * @return
	 */
	public VtmtResult answerQuestion(VtmtQuestion question, VtmtAnswer answer) {
		Log.d(TAG, "answerQuestion()");
		
		VtmtResult result = new VtmtResult();
		boolean value;

		if (question==null || question.getId() == 0) {//当题目为空时,不做任何处理
			return result;
		}else if (parameter.getBitType()!=Constants.BitType.CONTINUED_BIT 
				&& question.getId()>parameter.getQuestionCount()) {
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
		}else if (question.getAnswers().length != answer.getAnswers().length) {//如果答案内容缺少
			//计错误一次
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
	 * @return 记忆训练测试报告
	 */
	public VtmtReport generateTestReport() {

		VtmtReport report = new VtmtReport();
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
		
		//如果不是连续位元,生成图表,此图标是按题数分成3等分,依次为前 中 后 期
		if (parameter.getBitType() != Constants.BitType.CONTINUED_BIT) {
			//创建图表工具MemoryTrainingReportChart,并生成图付给测试报告
			report.setChart(new MemoryTrainingReportChart(context, testingId, answerQuestionDao)
				.generateBarChart(parameter.getQuestionCount()));
		}		
		
		//关闭数据库操作
		answerQuestionDao.close();
		
		return report;
	}
	
	public int[] getSamples(VtmtQuestion question) {
		
		if (question == null) {
			return null;
		}
				
		String[] contents = question.getContents();
		List<Integer> sampleIdList = new ArrayList<Integer>(); 
		
		for (int i = 0; i < contents.length; i++) {
			int id = CommonUtil.getSampleCodeBySampleElementCode(
					Integer.valueOf(contents[i]));
			if (!sampleIdList.contains(id)) {
				sampleIdList.add(id);
			}
		}
		
		int[] sampleIds = new int[sampleIdList.size()];
		for (int i = 0; i < sampleIdList.size(); i++) {
			sampleIds[i] = sampleIdList.get(i);
		}
		
		return sampleIds;		
	}

	/**
	 * 将正整数的字符形式组成的字符串进行升序排列
	 * @param strs
	 * @return
	 */
	private String[] sortArrays(String[] strs){
		if (strs == null) {
			return null;
		}
		
		String[] results = new String[strs.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = strs[i];
		}
		for (int i = 0; i < results.length-1; i++) {
			for (int j = i+1; j < results.length; j++) {
				if (Integer.valueOf(results[i]) > Integer.valueOf(results[j])) {
					String s = results[i];
					results[i] = results[j];
					results[j] = s;
				}
			}
		}
		
		return results;
	}
}