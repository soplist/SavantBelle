package com.flextronics.cn.model.spatial.memorytraining;

import com.flextronics.cn.model.BaseQuestion;

/**<b>E-空间位置记忆训练</b><br>
 * 题目
 * 
 * @author ZhangGuoYin
 *
 */
public class SpatialMemoryTrainingQuestion extends BaseQuestion{
	
	/**
	 * 题目内容,由一系列的样本元素编码组成
	 */
	private String[] contents;
	/**
	 * 显示位置,由1-36的阿拉伯数字组成
	 */
	private String[] locations;
	/**
	 * 问题标准答案
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
		
		String str = "\nid:              " + getId();
		if (getContents() != null) {
			str += "\ncontents:        ";
			for (int i = 0; i < getContents().length; i++) {
				str += getContents()[i] + "  ";
			}		
		}
		if (getAnswers() != null) {
			str += "\nanswers:         ";
			for (int i = 0; i < getAnswers().length; i++) {
				str += getAnswers()[i] + "  ";
			}	
		}
		if (getLocations() != null) {
			str += "\nlocations:       ";
			for (int i = 0; i < getLocations().length; i++) {
				str += getLocations()[i] + "  ";
			}
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
		str += "\ncreateTime:      " + getCreateTime();
		
		return str;
	}
}
