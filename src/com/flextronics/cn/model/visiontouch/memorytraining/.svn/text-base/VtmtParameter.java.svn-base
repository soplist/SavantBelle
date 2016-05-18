package com.flextronics.cn.model.visiontouch.memorytraining;

import com.flextronics.cn.model.ChoosedSample;
import com.flextronics.cn.model.BaseParameter;

/**
 * <b>视觉触觉记忆训练</b></br>
 * 参数
 * @author ZhangGuoYin
 *
 */
public class VtmtParameter extends BaseParameter {
	/**
	 * 操作方式
	 */
	private int optionsType;
	/**
	 * 元素出现类型
	 */
	private int presentType;
	/**
	 * 位元类型
	 */
	private int bitType;
	/**
	 * 受测样本
	 */
	private ChoosedSample[] choosedSamples;
	/**
	 * 题目数量
	 */
	private int questionCount;
	/**
	 * 优势手类型
	 */
	private int handType;
	/**
	 * 开始位元
	 */
	private int startBit;
	
	public int getOptionsType() {
		return optionsType;
	}
	public void setOptionsType(int optionsType) {
		this.optionsType = optionsType;
	}
	public int getPresentType() {
		return presentType;
	}
	public void setPresentType(int presentType) {
		this.presentType = presentType;
	}
	public int getBitType() {
		return bitType;
	}
	public void setBitType(int bitType) {
		this.bitType = bitType;
	}
	public ChoosedSample[] getChoosedSamples() {
		return choosedSamples;
	}
	public void setChoosedSamples(ChoosedSample[] choosedSamples) {
		this.choosedSamples = choosedSamples;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getHandType() {
		return handType;
	}
	public void setHandType(int handType) {
		this.handType = handType;
	}	
	public int getStartBit() {
		return startBit;
	}
	public void setStartBit(int startBit) {
		this.startBit = startBit;
	}
	
	@Override
	public String toString() {
		String str = "time:" + this.getTime() + " totalTime:" + this.getTotalTime() + " optionsType:" + optionsType
			 + " presentType:" + presentType + " bitType:" + bitType + " questionCount:" + questionCount
			 + " handType:" + handType+ " startBit:" + startBit + " choosedSamples:" ;
		for (int i = 0; i < choosedSamples.length; i++) {
			str += choosedSamples[i].getSample() + " ";
		}
		return str;
	}
}
