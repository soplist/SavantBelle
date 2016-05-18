package com.flextronics.cn.service.hearing;

import java.util.Map;

import com.flextronics.cn.model.hearing.response.ResponseAnswer;
import com.flextronics.cn.model.hearing.response.ResponseQuestion;
import com.flextronics.cn.model.hearing.response.ResponseReport;
import com.flextronics.cn.model.hearing.response.ResponseResult;

public interface IResponseService {
	public void init(Map<String, Object> parameters);
	public void start();
	public void startAnswer();
	public void stop();
	public ResponseResult answerQuestion(ResponseQuestion question,
			ResponseAnswer answer);
	public ResponseQuestion createQuestion();
	public ResponseReport generateTestReport();
}
