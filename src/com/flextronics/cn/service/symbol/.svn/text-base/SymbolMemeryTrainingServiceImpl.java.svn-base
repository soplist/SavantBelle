package com.flextronics.cn.service.symbol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.flextronics.cn.chart.MemoryTrainingReportChart;
import com.flextronics.cn.dao.AnswerQuestionDao;
import com.flextronics.cn.dao.TestingDao;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingAnswer;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingParameter;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingQuestion;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingReport;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingResult;
import com.flextronics.cn.model.color.memorytraining.ColorMemoryTrainingRule;
import com.flextronics.cn.model.symbol.SymbolMemeryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolMemoryTrainingParameter;
import com.flextronics.cn.util.Constants;

public class SymbolMemeryTrainingServiceImpl implements SymbolMemeryTrainingService {
	
	private final static String TAG = "SymbolMemeryTrainingServiceImpl";

	private long _id;
	
	private long testingId;
	
	private static long questionId;
	
	private static int questionCount;
	
	private static int errorCount;
	
	private static int rightCount;
	
	private Context context;
	private static int scores;
	private TestingDao testingDao;
	private SimpleDateFormat sdf;
	private AnswerQuestionDao answerQuestionDao;
	private SymbolMemoryTrainingParameter parameter;
	private long testingStartTime;
	private long startAnswerTime;
	private long testingStopTime;
	public SymbolMemeryTrainingServiceImpl(){
		super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;
	}
	
	
	
    public void init(Map<String, Object> parameters) throws LackOfParametersException{		
	    if(parameters == null){
		    Log.e(TAG, "init error, parameters is null");
		    throw new LackOfParametersException();			
	    }
	    parameter = (SymbolMemoryTrainingParameter)parameters.get(
			Constants.PARAMETER);    
	    context = (Context)parameters.get(Constants.CONTEXT);
		
		//将当前时间格式化为此次测试的ID
		testingId = Long.valueOf(sdf.format(new Date()));
		Log.d(TAG, "testingId: " + testingId);
		
		testingDao = new TestingDao(context);
		answerQuestionDao = new AnswerQuestionDao(context);
		startAnswerTime = System.currentTimeMillis();
		
		//向数据库中保存此次测试记录
		_id = testingDao.insertTesting(testingId, null, null, null);
		
		Log.d(TAG, "_id=" + _id);
	}
    
	public void start() {
		//此次测试的开始时间
		testingStartTime = System.currentTimeMillis();
		//更新数据库中此次测试的开始时间
		if(null==testingDao)
			Log.d(TAG,"null==testingDao");
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, null);
	}
    
	public void startAnswer(){
		startAnswerTime = System.currentTimeMillis();
	}
	
	public void answerQuestion(long id,boolean value) {
		answerQuestionDao.insertAnswerQuestion(id, startAnswerTime, System.currentTimeMillis(), value, testingId);
	}
	public void stop() {
		Log.d(TAG, "stop()");
		
		testingStopTime = System.currentTimeMillis();
		
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, testingStopTime);
		
		testingDao.close();
	}
	/**生成测试报告
	 * 
	 * @return
	 */
	public SymbolMemeryTrainingReport generateReport(int questionCount,int errorCount) {
		
		//this.questionCount = questionCount;
		//this.errorCount = errorCount;
		int rightCount = questionCount-errorCount;
		//创建测试报告对象
		SymbolMemeryTrainingReport report = new SymbolMemeryTrainingReport();
		//此次测试编号
		report.setTestingId(testingId);
		//问题数目
		report.setQuestionCount(questionCount);
		//错误数
		report.setErrorCount(errorCount);
		//正确数
		Log.d(TAG,"rightCount="+rightCount);
		report.setRightCount(rightCount);
		//总分数
		report.setScores(rightCount*2);
		if (errorCount+rightCount != 0) {
			report.setRightPercentage(rightCount/(errorCount+rightCount));
			report.setErrorPercentage(errorCount/(errorCount+rightCount));
		}else {
			report.setRightPercentage(0);
			report.setErrorPercentage(0);
		}
		    report.setChart(new MemoryTrainingReportChart(context, testingId, answerQuestionDao)
				.generateBarChart(parameter.getQuestionCount()));			
			
		//关闭数据库操作
		answerQuestionDao.close();
		
		return report;
	}	
}
