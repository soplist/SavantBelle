package com.flextronics.cn.model;

/**测试规则的基类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseRule {
	/**
	 * 每题的分数
	 */
	private int score;
	/**
	 * 每题的时间
	 */
	private long time;
	/**
	 * 整场考试的所有时间
	 */
	private long totalTime;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
}
