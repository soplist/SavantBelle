package com.flextronics.cn.service.spatial;

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

import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.OutOfMaxQuestionsException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.exception.CanNotSupportSuchBitsException;
import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingAnswer;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingParameter;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingQuestion;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingReport;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingResult;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingRule;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**SpatialMemoryTrainingService的实现类
 * 
 * @author ZhangGuoYin
 *
 */
public class SpatialMemoryTrainingServiceImpl implements SpatialMemoryTrainingService {
		
	private final static String TAG = "SpatialMemoryTrainingServiceImpl";
	
	private long _id;

	/**
	 * 当前用户的ID
	 */
	private Integer userId;	
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
	 * 整场测试的开始时间
	 */
	private long testingStartTime;
	/**
	 * 整场测试的结束时间
	 */
	private long testingStopTime;
	/**
	 * 每小题的开始答题时间
	 */
	private long startAnswerTime;
	
	/**
	 * 空间位置记忆训练参数
	 */
	private SpatialMemoryTrainingParameter parameter;
	/**
	 * 空间位置记忆训练规则
	 */
	private SpatialMemoryTrainingRule rule;
	
	/**
	 * 1-36个显示位置
	 */
	private String[] locations;
	/**
	 * 针对按区域回应的位置
	 */
	private String[][] locations4regions;
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
	
	public SpatialMemoryTrainingServiceImpl() {
		super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");	//年月日时分秒毫秒
		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;
		
		locations = new String[36];
		for (int i = 0; i < locations.length; i++) {
			locations[i] = (i+1) + "";
		}
	}
	
	/**根据参数初始化服务
	 * 
	 * @param parameters
	 * @throws LackOfParametersException 
	 * @throws Exception 
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException, Exception {
		
		if (parameters == null) {
			Log.e(TAG, "init error, received parameters is null");
			throw new LackOfParametersException();
		}
		
		parameter = (SpatialMemoryTrainingParameter)parameters.get(
				Constants.PARAMETER);
		rule = (SpatialMemoryTrainingRule)parameters.get(Constants.RULE);
		context = (Context)parameters.get(Constants.CONTEXT);
		
		if (parameter == null) {
			Log.e(TAG, "init error, parameter is null");
			throw new LackOfParametersException();
		}else if (rule == null) {
			Log.e(TAG, "init error, rule is null");
			throw new LackOfParametersException();			
		}else if (context == null) {
			Log.e(TAG, "init error, context is null");
			throw new LackOfParametersException();			
		}
		
		//根据上下文创建各数据库访问对象
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		
		startBit = parameter.getStartBit();	
		startAnswerTime = System.currentTimeMillis();
		
		//将当前时间格式化为此次测试的ID
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);
		
		//取得当前用户的userId
		/*userId = context.getSharedPreferences(
				Constants.USER_INFO, Context.MODE_PRIVATE).getInt(Constants.USER_NAME, -1);*/
		//向数据库中保存此次测试记录, 此时测试的开始时间和结束时间都是空
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
	 * @throws ParameterIsInvalidException
	 * @throws OutOfMaxQuestionsException
	 * @throws Exception
	 */
	public SpatialMemoryTrainingQuestion createQuestion() throws 
		LackOfParametersException, ParameterIsInvalidException, OutOfMaxQuestionsException, CanNotSupportSuchBitsException{
		
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
				
		//如果是指定位元,则位元数是4
		if (parameter.getBitType() == Constants.BitType.GIVEN_BIT) {
			Log.d(TAG, "Given bit, the bit is 4.");
			bits = 4;
		}else if (parameter.getBitType() == Constants.BitType.CONTINUED_BIT) {
			//如果连续位元,位元数将自动增长
			Log.d(TAG, "Continued bit, the bit will automatically crease.");
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
		
		if (bits > Constants.MAX_BIT) {
			Log.e(TAG, "It reaches the max bit. We must stop creating question.");
			throw new CanNotSupportSuchBitsException();
		}
		
		//样本群不能缺少
		ChoosedSample[] samples = parameter.getChoosedSamples();
		if(samples == null){
			Log.e(TAG, "lack of choosed samples, we stop creating question.");
			throw new LackOfParametersException();
		}
		
		//题目内容
		String[] contentsArray = new String[bits];
		//显示位置
		String[] locationsArray = null;
		//题目答案
		String[] answersArray = null;
		//候选答题按钮按键值
		String[][] buttonValues = null;
		
		/***********************生成题目的location***********************/
		//如果回应类型是按顺序回应,指定回应,方位回应
		//显示位置实际上就是location中取指定个进行显示
		if (parameter.getAnswerType() == 
				Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER || 
			parameter.getAnswerType() == 
				Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER || 
			parameter.getAnswerType() == 
				Constants.SpatialMemoryTrainingUIParameter.ORIENTATION_ANSWER) {
			
			locationsArray = ArrayOperations.redomElements(locations, bits);
		} else if (parameter.getAnswerType() == 
			Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER){
			//如果回应类型是按区域回应,则需要从选定的区域中选定指定个位置进行显示,
			//这就要求要保证每个区域都有相近数量的位置(此处的规则暂定为此，如果用户没有特殊要求就按这种方式处理)
			
			
			//将每个区域中的location组合成一个二维数组,从这个二维数组中取得bits位元素,
			//每一维都会取到,且个数尽可能的相等(此处的规则暂定为此，如果用户没有特殊要求就按这种方式处理)
			if (locations4regions == null) {
				//取得参数中的区域
				String[] regions = parameter.getRegions();
				locations4regions = new String[regions.length][];
				for (int i = 0; i < regions.length; i++) {
					String[] strs = new String[9];
					int start = Integer.valueOf(Integer.valueOf(regions[i])-Constants.SpatialMemoryTrainingUIParameter.A_REGION)*9+1;				
					for (int j = 0; j < strs.length; j++) {
						strs[j] = String.valueOf(start);
						start++;
					}
					locations4regions[i] = strs;
				}
				regions = null;
			}
			locationsArray = ArrayOperations.redomElements(locations4regions, bits);
		}
		
		/***********************生成题目的content***********************/
		//如果是单一受测样本,当设定为白光的时候，显示内容只能是白光;
		//当是其他样本的时候，显示内容就是从该样本中随机出bits位元素
		if(samples.length == 1){
			if (samples[0].getSample() == Constants.Sample.WHITE) {
				//如果设定单一受测样本,且为白光
				//题目的内容即是白光
				for (int i = 0; i < bits; i++) {
					contentsArray[i] = String.valueOf(Constants.WhiteLight.WHITELIGHT);
				}				
			} else if(samples[0].getSample()>=Constants.Sample.COLORS &&
					samples[0].getSample()<=Constants.Sample.PERCUSSION_INSTRUMENT &&
					samples[0].getSample()%10000==0){

				//如果样本为从颜色到打击乐中的一种,则从该样本中取得bits位
				if (elementsInSingleSample == null) {
					elementsInSingleSample = CommonUtil.getSampleElementsInSample(samples[0].getSample());
				}
				contentsArray = ArrayOperations.redomElements(elementsInSingleSample, bits);
			} else {
				Log.e(TAG, "choosed sample is error, we stop creating question.");
				throw new ParameterIsInvalidException();
			}
		}else if(samples.length > 1){//如果是多种受测样本

			//根据样本群取得该样本群中元素
			if (elementsInMultSample == null) {
				elementsInMultSample = new String[samples.length][];
				for (int i = 0; i < elementsInMultSample.length; i++) {
					elementsInMultSample[i] = CommonUtil.getSampleElementsInSample(samples[i].getSample());
				}
			}
			//从诸多样本群元素中取得bits位
			contentsArray = ArrayOperations.redomElements(elementsInMultSample, bits);
		}		

		/***********************生成题目的answer***********************/
		if (parameter.getAnswerType() == Constants.SpatialMemoryTrainingUIParameter.ORDER_ANSWER ||
				parameter.getAnswerType() == Constants.SpatialMemoryTrainingUIParameter.SPECIFY_ANSWER) {
			//如果回应类型是按顺序回应或者指定回应
			
			//当样本为单一白光时，如果显示方式非同时显示同时消失，则答案和location相同
			//否则，答案为location升序
			if (samples.length==1 && samples[0].getSample()==Constants.Sample.WHITE) {
				if (parameter.getDisplayMode() != Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
					answersArray = locationsArray;
				}else {
					answersArray = sortArrays(locationsArray);
				}
			} else {
				//当样本非单一白光时，如果显示方式非同步显示同步消失，则答案和内容相同
				if (parameter.getDisplayMode() != Constants.DisplayMode.SHOW_DISAPPEAR_TOGETHER) {
					answersArray = contentsArray;				
				}else {
					String[] sortedLocation = sortArrays(locationsArray);
					answersArray = new String[bits];
					for (int i = 0; i < sortedLocation.length; i++) {
						answersArray[i] = contentsArray[ArrayOperations.indexInElement(
								locationsArray, sortedLocation[i])];
					}
				}
			}
		} else if (parameter.getAnswerType() == Constants.SpatialMemoryTrainingUIParameter.ORIENTATION_ANSWER ||
				parameter.getAnswerType() == Constants.SpatialMemoryTrainingUIParameter.REGION_ANSWER){
			if (samples.length==1 && samples[0].getSample()==Constants.Sample.WHITE) {
				answersArray = sortArrays(locationsArray);
			} else {
				String[] sortedLocation = sortArrays(locationsArray);
				answersArray = new String[bits];
				for (int i = 0; i < sortedLocation.length; i++) {
					answersArray[i] = contentsArray[ArrayOperations.indexInElement(
							locationsArray, sortedLocation[i])];
				}
			}
		}
		
		
		/******************根据选择的样本群创建候选操作按键值:每次生成的按钮编码，
		 * 需要包含答案编码的值，此外还需要增加几个******************/
		buttonValues = new String[samples.length][];
		
		if (samples.length==1 && samples[0].getSample()!=Constants.Sample.WHITE) {
			//如果样本群为单一样本群，且不是白光
			//则按钮为该样本群内所有元素
			//buttonValues[0] = CommonUtil.getSampleElementById(samples[0].getSample());
			buttonValues[0] = elementsInSingleSample;
		}else if (samples.length > 1) {//如果样本群为多种样本
			
			if (sampleIdStrs == null) {
				//将样本群编号组装到数组中，并按升序排列
				sampleIdStrs = new String[samples.length];
				for (int i = 0; i < samples.length; i++) {
					sampleIdStrs[i] = samples[i].getSample()+"";
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
				
		
		//创建颜色记忆训练问题对象
		SpatialMemoryTrainingQuestion question = new SpatialMemoryTrainingQuestion();
		//编号
		question.setId(questionId);
		//题目内容
		question.setContents(contentsArray);
		//显示位置
		question.setLocations(locationsArray);
		//题目答案
		question.setAnswers(answersArray);
		//候选答题按钮按键值
		question.setButtonValues(buttonValues);
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
	public SpatialMemoryTrainingResult answerQuestion(
			SpatialMemoryTrainingQuestion question, SpatialMemoryTrainingAnswer answer) {
		Log.d(TAG, "answerQuestion()");
		
		//创建结果对象
		SpatialMemoryTrainingResult result = new SpatialMemoryTrainingResult();
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
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, userId, testingStartTime, testingStopTime);
		//关闭数据库操作
		testingDao.close();
	}
	
	
	/**生成测试报告
	 * 
	 * @return 空间位置记忆训练测试报告
	 */
	public SpatialMemoryTrainingReport generateReport() {
		Log.d(TAG, "generateReport()");
		
		//创建测试报告对象
		SpatialMemoryTrainingReport report = new SpatialMemoryTrainingReport();
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