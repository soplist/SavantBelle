package com.flextronics.cn.model.hearing.memory;

import com.flextronics.cn.model.BaseQuestion;

public class MemoryQuestion extends BaseQuestion {
	private String[] contents; // 题目内容
	private String[] answers; // 题目答案
	private String[] displayContents;
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
	public String[] getDisplayContents() {
		return displayContents;
	}
	public void setDisplayContents(String[] displayContents) {
		this.displayContents = displayContents;
	}
}
