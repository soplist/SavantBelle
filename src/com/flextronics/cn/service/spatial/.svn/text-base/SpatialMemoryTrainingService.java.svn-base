package com.flextronics.cn.service.spatial;

import java.util.Map;

import com.flextronics.cn.exception.CanNotSupportSuchBitsException;
import com.flextronics.cn.exception.LackOfParametersException;
import com.flextronics.cn.exception.OutOfMaxQuestionsException;
import com.flextronics.cn.exception.ParameterIsInvalidException;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingAnswer;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingQuestion;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingReport;
import com.flextronics.cn.model.spatial.memorytraining.SpatialMemoryTrainingResult;

/**<b>E-空间位置记忆训练 </b>
 * 服务申明类<br>
 * <b>该服务提供：</b>初始化init(),开始服务start(),创建题目createQuestion(),
 * 回答题目answerQuestion(),停止服务stop(),生成报告generateReport()<br>
 * 
 * <b>使用规则：</b>先根据参数进行初始化init();初始化完毕后开始服务start();此后创建题目createQuestion();
 * 题目创建完毕后即可开始回答题目startAnswer();回答题目answerQuestion();停止服务stop();最后生成报告generateReport();
 * 创建题目--->回答题目之间是个可循环的过程，针对每一题都是按 创建-开始作答-回答 这个流程执行。
 * 
 * @author ZhangGuoYin *
 */
public interface SpatialMemoryTrainingService {
	
	/**
	 * 初始化服务
	 * @param parameters
	 * @throws LackOfParametersException
	 * @throws Exception
	 */
	public void init(Map<String, Object> parameters) throws LackOfParametersException, Exception;
	/**
	 * 开始服务
	 */
	public void start();
	/**
	 * 创建题目
	 * @return
	 * @throws LackOfParametersException
	 * @throws ParameterIsInvalidException
	 * @throws OutOfMaxQuestionsException
	 * @throws Exception
	 */
	public SpatialMemoryTrainingQuestion createQuestion() throws 
		LackOfParametersException, ParameterIsInvalidException, OutOfMaxQuestionsException, CanNotSupportSuchBitsException;
	/**
	 * 开始回答题目
	 */
	public void startAnswer();
	/**
	 * 回答题目
	 * @param question
	 * @param answer
	 * @return
	 */
	public SpatialMemoryTrainingResult answerQuestion(
			SpatialMemoryTrainingQuestion question, SpatialMemoryTrainingAnswer answer);
	/**
	 * 停止服务
	 */
	public void stop();
	/**
	 * 生成测试报告
	 * @return
	 */
	public SpatialMemoryTrainingReport generateReport();
}
