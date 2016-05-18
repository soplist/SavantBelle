package com.flextronics.cn.model.color.responsetraining;

import com.flextronics.cn.model.BaseQuestion;

/**<b>C-颜色反应训练</b><br>
 * 题目
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorResponseTrainingQuestion extends BaseQuestion{

	/**
	 * 题目内容
	 */
	private String content;
	
	/**显示形体
	 */
	private String displayBody;
	/**
	 * 标准答案
	 */
	private String answer;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDisplayBody() {
		return displayBody;
	}
	public void setDisplayBody(String displayBody) {
		this.displayBody = displayBody;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
