package com.flextronics.cn.model.spatial.memorytraining;

import com.flextronics.cn.model.BaseAnswer;

/**<b>E-空间位置记忆训练</b><br>
 * 用户回答的答案
 * 
 * @author ZhangGuoYin
 *
 */
public class SpatialMemoryTrainingAnswer extends BaseAnswer{

	/**
	 * 用户回答的内容
	 */
	private String[] answers;

	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}	
}
