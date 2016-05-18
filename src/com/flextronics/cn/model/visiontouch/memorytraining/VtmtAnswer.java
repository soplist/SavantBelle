package com.flextronics.cn.model.visiontouch.memorytraining;

import com.flextronics.cn.model.BaseAnswer;

/**
 * <b>视觉触觉记忆训练</b></br>
 * 答案
 * @author ZhangGuoYin
 *
 */
public class VtmtAnswer extends BaseAnswer{
	/**
	 * 用户作答的答案
	 */
	private String[] answers;

	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}	
}
