package com.flextronics.cn.service.symbol;

import java.util.Map;

import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.model.symbol.SymbolResponseTrainingReport;

public interface SymbolResponseTrainingService {
	public SymbolResponseTrainingReport generateReport(int questionCount,int errorCount);
	public void init(Map<String, Object> parameters) throws LackOfParametersException;
	public void start();
	public void answerQuestion(long id,boolean value);
	public void startAnswer();
	public void stop();
}
