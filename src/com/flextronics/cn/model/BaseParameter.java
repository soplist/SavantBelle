package com.flextronics.cn.model;

/**测试参数的基类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseParameter {
	
	/**
	 * 每小题的时间
	 */
	private long time;
	/**
	 * 整场考试的所有时间
	 */
	private long totalTime;
	
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
