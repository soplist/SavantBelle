package com.flextronics.cn.model;

import org.achartengine.chart.BarChart;


public class BaseSymbolResponseTrainingReport  extends BaseReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 柱状图
	 */
	private BarChart chart;
	
	public BarChart getChart() {
		return chart;
	}
	public void setChart(BarChart chart) {
		this.chart = chart;
	}
}
