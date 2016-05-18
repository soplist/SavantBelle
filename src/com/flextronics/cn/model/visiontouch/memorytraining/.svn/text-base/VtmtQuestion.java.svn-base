package com.flextronics.cn.model.visiontouch.memorytraining;

import com.flextronics.cn.model.BaseQuestion;

/**
 * <b>视觉触觉记忆训练</b></br>
 * 问题
 * @author ZhangGuoYin
 *
 */
public class VtmtQuestion extends BaseQuestion{

	/**
	 * 题目内容
	 */
	private String[] contents;
	/**
	 * 题目显示位置
	 */
	private String[] locations;
	/**
	 * 题目标准答案
	 */
	private String[] answers;
	/**
	 * 答题按钮的编码,由一系列的样本元素编码组成
	 */
	private String[][] buttonValues;
	
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
	public String[][] getButtonValues() {
		return buttonValues;
	}
	public void setButtonValues(String[][] buttonValues) {
		this.buttonValues = buttonValues;
	}
	@Override
	public String toString() {
		String str = "---------------question info------------\n";
		str += "id: " + getId() + "\n";
		str += "createdTime: " + getCreateTime() + "\n";
		if (contents == null) {
			str += "contents: ";
			for (int i = 0; i < contents.length; i++) {
				str += contents[i] + " ";
			}
			str += "\n";
		}
		if (locations == null) {
			str += "locations: ";
			for (int i = 0; i < locations.length; i++) {
				str += locations[i] + " ";
			}
			str += "\n";
		}
		if (answers == null) {
			str += "answers: ";
			for (int i = 0; i < answers.length; i++) {
				str += answers[i] + " ";
			}
			str += "\n";
		}
		if (getButtonValues() != null) {
			for (int i = 0; i < getButtonValues().length; i++) {
				str += "\nbuttonValues[" + i + "]: ";
				if (getButtonValues()[i] != null) {
					for (int j = 0; j < getButtonValues()[i].length; j++) {
						str += getButtonValues()[i][j] + " ";
					}					
				}
			}
		}
		str += "---------------------------------------";
		return str;
	}	
}
