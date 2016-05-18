package com.flextronics.cn.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**<b>测试报告的基类</b><br>
 * 各训练的测试报告可继承此类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseReport implements Serializable{	

	private static final long serialVersionUID = 6367700183182378057L;
	
	/**
	 * 测试编号
	 */
	private Long testingId;	
	/**
	 * 题目总数
	 */
	private int questionCount;
	/**
	 *做错题数
	 */
	private int errorCount;		
	/**
	 * 做对题数
	 */
	private int rightCount;		
	/**
	 * 总分
	 */
	private int scores;			
	/**
	 * 开始做题时间，也即第一题的生成时间
	 */
	private Timestamp startTime;	
	/**
	 * 最后结束时间
	 */
	private Timestamp endTime;		
	/**
	 * 总共持续的时间
	 */
	private long time;		
	/**
	 * 完成状态
	 */
	private int status;			
	/**
	 * 正确百分比
	 */
	private double rightPercentage;	
	/**
	 * 错误百分比
	 */
	private double errorPercentage;		
	
	
	public Long getTestingId() {
		return testingId;
	}
	public void setTestingId(Long testingId) {
		this.testingId = testingId;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public int getRightCount() {
		return rightCount;
	}
	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getRightPercentage() {
		return rightPercentage;
	}
	public void setRightPercentage(double rightPercentage) {
		this.rightPercentage = rightPercentage;
	}
	public double getErrorPercentage() {
		return errorPercentage;
	}
	public void setErrorPercentage(double errorPercentage) {
		this.errorPercentage = errorPercentage;
	}
}
