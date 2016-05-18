package com.flextronics.cn.model.color.memorytraining;

import com.flextronics.cn.model.BaseQuestion;

/**<b>C-颜色记忆训练</b><br>
 * 题目
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorMemoryTrainingQuestion extends BaseQuestion{
	
	/**
	 * 题目内容,由一系列的颜色编码组成
	 */
	private String[] contents;
	/**
	 * 显示位置
	 */
	private String[] locations;
	/**
	 * 问题标准答案
	 */
	private String[] answers;
	/**
	 * 显示形体
	 */
	private String[] displayBody;
	
	public String[] getContents() {
		return contents;
	}
	public void setContents(String[] contents) {
		this.contents = contents;
	}
	public String[] getLocations() {
		return locations;
	}
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	public String[] getDisplayBody() {
		return displayBody;
	}
	public void setDisplayBody(String[] displayBody) {
		this.displayBody = displayBody;
	}
	
	@Override
	public String toString() {
		
		String str = "\nid:           " + getId();
		if (getContents() != null) {
			str += "\ncontents:     ";
			for (int i = 0; i < getContents().length; i++) {
				str += getContents()[i] + "  ";
			}		
		}
		if (getAnswers() != null) {
			str += "\nanswers:      ";
			for (int i = 0; i < getAnswers().length; i++) {
				str += getAnswers()[i] + "  ";
			}	
		}
		if (getLocations() != null) {
			str += "\nlocations:    ";
			for (int i = 0; i < getLocations().length; i++) {
				str += getLocations()[i] + "  ";
			}
		}
		if (getDisplayBody() != null) {
			str += "\ndisplayBodys: ";
			for (int i = 0; i < getDisplayBody().length; i++) {
				str += getDisplayBody()[i] + "  ";
			}
		}
		str += "\ncreateTime:   " + getCreateTime();
		
		return str;
	}
}
