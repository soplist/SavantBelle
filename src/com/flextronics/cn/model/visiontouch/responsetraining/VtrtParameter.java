package com.flextronics.cn.model.visiontouch.responsetraining;

import com.flextronics.cn.model.BaseParameter;
import com.flextronics.cn.model.ChoosedSample;

/**
 * <b>视觉触觉反应训练</b></br>
 * 参数
 * @author ZhangGuoYin
 *
 */
public class VtrtParameter extends BaseParameter {
	
	/**
	 * 操作方式
	 */
	private int optionsType;
	/**
	 * 受测样本位元数
	 */
	private int bit;
	/**
	 * 选择的受测样本
	 */
	private ChoosedSample choosedSample;
	/**
	 * 优势手类型
	 */
	private int handType;
	/**
	 * 反应类型
	 */
	private int responseType;
	/**
	 * 回应类型
	 */
	private int answerType;
	
	public int getOptionsType() {
		return optionsType;
	}
	public void setOptionsType(int optionsType) {
		this.optionsType = optionsType;
	}	
	public int getBit() {
		return bit;
	}
	public void setBit(int bit) {
		this.bit = bit;
	}	
	public ChoosedSample getChoosedSample() {
		return choosedSample;
	}
	public void setChoosedSample(ChoosedSample choosedSample) {
		this.choosedSample = choosedSample;
	}
	public int getHandType() {
		return handType;
	}
	public void setHandType(int handType) {
		this.handType = handType;
	}
	public int getAnswerType() {
		return answerType;
	}
	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}	
	public int getResponseType() {
		return responseType;
	}
	public void setResponseType(int responseType) {
		this.responseType = responseType;
	}
	
	@Override
	public String toString() {
		String str = "time:" + this.getTime() + " totalTime:" + this.getTotalTime() + " answerType:" + answerType
			+ " optionsType:" + optionsType + " bit:" + bit  + " handType:" + handType+ " answerType:" + answerType
			+ " choosedSample.sampleId" + choosedSample.getSample() + " choosedSample.elements:";
		
		 String[] elements = choosedSample.getSmapleElementChoosed();
		 for (int i = 0; i < elements.length; i++) {
			 str += elements[i]+" ";
		}	
		
		return str;
	}
}
