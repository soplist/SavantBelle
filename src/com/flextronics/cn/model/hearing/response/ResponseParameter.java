package com.flextronics.cn.model.hearing.response;

import com.flextronics.cn.model.BaseParameter;
import com.flextronics.cn.model.ChoosedSample;

public class ResponseParameter extends BaseParameter {
	/**
	 * 样本类型
	 */
	private String smapleType;     
	/**
	 * 元素类型
	 */
	private String elementType;
	/**
	 * 乐器元素组
	 */
	private ChoosedSample musicInstrumentsSet; 
	/**
	 * 音阶元素组
	 */
	private ChoosedSample scaleSet;
	/**
	 * 是否随机
	 */
	private boolean randomEnabled;
	/**
	 * 题目数
	 */
	private int questionTotal;
	public String getSmapleType() {
		return smapleType;
	}
	public void setSmapleType(String smapleType) {
		this.smapleType = smapleType;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public ChoosedSample getMusicInstrumentsSet() {
		return musicInstrumentsSet;
	}
	public void setMusicInstrumentsSet(ChoosedSample musicInstrumentsSet) {
		this.musicInstrumentsSet = musicInstrumentsSet;
	}
	public ChoosedSample getScaleSet() {
		return scaleSet;
	}
	public void setScaleSet(ChoosedSample scaleSet) {
		this.scaleSet = scaleSet;
	}
	public boolean isRandomEnabled() {
		return randomEnabled;
	}
	public void setRandomEnabled(boolean randomEnabled) {
		this.randomEnabled = randomEnabled;
	}
	public int getQuestionTotal() {
		return questionTotal;
	}
	public void setQuestionTotal(int questionTotal) {
		this.questionTotal = questionTotal;
	}
}