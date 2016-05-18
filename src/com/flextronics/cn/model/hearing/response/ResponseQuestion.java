package com.flextronics.cn.model.hearing.response;

import com.flextronics.cn.model.BaseQuestion;

public class ResponseQuestion extends BaseQuestion {
	
	/**
	 * 题目内容
	 */
	private String[] contents;
	/**
	 * 题目答案
	 */
	private String[] answers;
	/**
	 * 显示题目
	 */
	private String[] displayAnswer;
	
	public String[] getContents() {
		return contents;
	}
	public void setContents(String[] contents) {
		this.contents = contents;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	public String[] getDisplayAnswer() {
		return displayAnswer;
	}
	public void setDisplayAnswer(String[] displayAnswer) {
		this.displayAnswer = displayAnswer;
	}

	
}
