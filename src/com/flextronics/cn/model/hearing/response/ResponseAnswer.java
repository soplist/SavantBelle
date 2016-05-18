package com.flextronics.cn.model.hearing.response;

import com.flextronics.cn.model.BaseAnswer;

public class ResponseAnswer extends BaseAnswer {
	//答案
	private String[] answers;

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
