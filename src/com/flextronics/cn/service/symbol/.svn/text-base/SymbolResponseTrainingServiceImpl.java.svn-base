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
import com.flextronics.cn.model.symbol.SymbolMemeryTrainingReport;
import com.flextronics.cn.model.symbol.SymbolMemoryTrainingParameter;
import com.flextronics.cn.model.symbol.SymbolResponseTrainingParameter;
import com.flextronics.cn.model.symbol.SymbolResponseTrainingReport;
import com.flextronics.cn.util.Constants;

public class SymbolResponseTrainingServiceImpl implements SymbolResponseTrainingService{
	private final static String TAG = "SymbolResponseTrainingServiceImpl";

	private long _id;
	
	private long testingId;
	
	private static long questionId;
	
	private static int questionCount;
	
	private static int errorCount;
	
	private static int rightCount;
	
	private Context context;
	private static int scores;
	private SimpleDateFormat sdf;
	private AnswerQuestionDao answerQuestionDao;
	private SymbolResponseTrainingParameter parameter;
	private long testingStartTime;
	private long startAnswerTime;
	private long testingStopTime;
	private TestingDao testingDao;
    public SymbolResponseTrainingServiceImpl(){
    	super();
		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		questionId = 0;
		questionCount = 0;
		rightCount = 0;
		errorCount = 0;
		scores = 0;
    }
    
	@Override
	public void init(Map<String, Object> parameters)
			throws LackOfParametersException {
		// TODO Auto-generated method stub
		if(parameters == null){
		    Log.e(TAG, "init error, parameters is null");
		    throw new LackOfParametersException();			
	    }
	    parameter = (SymbolResponseTrainingParameter)parameters.get(Constants.PARAMETER);    
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
	@Override
	public void start() {
		// TODO Auto-generated method stub
		//此次测试的开始时间
		testingStartTime = System.currentTimeMillis();
		//更新数据库中此次测试的开始时间
		if(null==testingDao)
			Log.d(TAG,"null==testingDao");
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, null);
	}
	
	@Override
	public void startAnswer() {
		// TODO Auto-generated method stub
		startAnswerTime = System.currentTimeMillis();
	}

	
	@Override
	public void answerQuestion(long id, boolean value) {
		// TODO Auto-generated method stub
		answerQuestionDao.insertAnswerQuestion(id, startAnswerTime, System.currentTimeMillis(), value, testingId);
	}

	@Override
	public SymbolResponseTrainingReport generateReport(int questionCount,
			int errorCount) {
		// TODO Auto-generated method stub
		int rightCount = questionCount-errorCount;
		//创建测试报告对象
		SymbolResponseTrainingReport report = new SymbolResponseTrainingReport();
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


	@Override
	public void stop() {
		// TODO Auto-generated method stub
        Log.d(TAG, "stop()");
		
		testingStopTime = System.currentTimeMillis();
		
		testingDao.updateTesting(Integer.valueOf(_id+""), testingId, null, testingStartTime, testingStopTime);
		
		testingDao.close();
	}
}
