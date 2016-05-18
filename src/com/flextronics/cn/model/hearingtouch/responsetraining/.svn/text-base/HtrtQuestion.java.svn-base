package com.flextronics.cn.model.hearingtouch.responsetraining;

import com.flextronics.cn.model.BaseQuestion;

/**
 * <b>听觉触觉反应训练</b></br>
 * 问题
 * @author ZhangGuoYin
 *
 */
public class HtrtQuestion extends BaseQuestion{

	/**
	 * 题目内容
	 */
	private String[] contents;
	/**
	 * 题目显示位置
	 */
	private String[] locations;
	/**
	 * 题目答案
	 */
	private String[] answers;
	
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
	
	@Override
	public String toString() {
		String str = "---------------question info------------\n";
		str += "id: " + getId() + "\n";
		str += "createdTime: " + getCreateTime() + "\n";
		if (contents != null) {
			str += "contents: ";
			for (int i = 0; i < contents.length; i++) {
				str += contents[i] + " ";
			}
			str += "\n";
		}
		if (locations != null) {
			str += "locations: ";
			for (int i = 0; i < locations.length; i++) {
				str += locations[i] + " ";
			}
			str += "\n";
		}
		if (answers != null) {
			str += "answers: ";
			for (int i = 0; i < answers.length; i++) {
				str += answers[i] + " ";
			}
			str += "\n";
		}
		str += "---------------------------------------";
		return str;
	}
}
