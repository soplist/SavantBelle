package com.flextronics.cn.model;

import java.sql.Timestamp;

/**题目的基类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseQuestion {

	/**
	 * 题目编号
	 */
	private long id;
	/**
	 * 题目的创建时间
	 */
	private Timestamp createTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}	
	
}
