package com.flextronics.cn.model;

import java.sql.Timestamp;

/**用户作答的答案的基类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseAnswer {
	
	/**
	 * 问题编号
	 */
	private long questionId;
	/**
	 * 答题时间点
	 */
	private Timestamp answerTime;
	
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public Timestamp getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
	}
}
