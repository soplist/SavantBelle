package com.flextronics.cn.service.hearing;

import java.util.Map;

import com.flextronics.cn.model.hearing.memory.MemoryAnswer;
import com.flextronics.cn.model.hearing.memory.MemoryQuestion;
import com.flextronics.cn.model.hearing.memory.MemoryReport;
import com.flextronics.cn.model.hearing.memory.MemoryResult;



public interface IMemoryService {
	public void init(Map<String, Object> parameters);

	public void start();
	
	public void startAnswer();

	public MemoryQuestion createQuestion();

	public MemoryResult answerQuestion(MemoryQuestion question,MemoryAnswer answer);

	public void stop();

	public MemoryReport generateTestReport();
}
