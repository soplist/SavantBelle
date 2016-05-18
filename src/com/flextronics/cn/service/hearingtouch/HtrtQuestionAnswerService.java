package com.flextronics.cn.service.hearingtouch;

import java.util.Map;

import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtAnswer;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtQuestion;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtReport;
import com.flextronics.cn.model.hearingtouch.responsetraining.HtrtResult;

/**<b>A-视觉触觉综合训练: 听觉触觉反应训练 </b>
 * 服务申明类<br>
 * 该服务提供：初始化init(),开始服务start(),创建题目createQuestion(),
 * 回答题目answerQuestion(),停止服务stop(),生成报告generateReport()<br>
 * 
 * 使用规则：先根据参数进行初始化init();初始化完毕后开始服务start();此后创建题目createQuestion();
 * 题目创建完毕后即可开始回答题目startAnswer();回答题目answerQuestion();停止服务stop();最后生成报告generateReport();
 * 创建题目--->回答题目之间是个可循环的过程，针对每一题都是按 创建-开始作答-回答 这个流程执行。
 * 
 * @author ZhangGuoYin *
 */
public interface HtrtQuestionAnswerService {
	/**
	 * 初始化服务
	 * @param parameters
	 * @throws LackOfParametersException
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException;
	/**
	 * 创建题目
	 * @return
	 * @throws LackOfParametersException
	 * @throws ParameterIsInvalidException
	 */
	public HtrtQuestion createQuestion() throws LackOfParametersException, ParameterIsInvalidException;
	/**
	 * 开始服务
	 */
	public void start();
	/**
	 * 开始答题
	 */
	public void startAnswer();
	/**
	 * 回答题目
	 * @param question 题目
	 * @param answer 用户答案
	 * @return
	 */
	public HtrtResult answerQuestion(HtrtQuestion question, HtrtAnswer answer);
	/**
	 * 停止服务
	 */
	public void stop();
	/**
	 * 生成测试报告
	 * @return
	 */
	public HtrtReport generateReport();	
}
