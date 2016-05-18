package com.flextronics.cn.model.visiontouch.responsetraining;

import org.achartengine.chart.BarChart;

import com.flextronics.cn.model.BaseReport;

/**
 * <b>视觉触觉反应训练</b></br>
 * 测试报告
 * @author ZhangGuoYin
 *
 */
public class VtrtReport extends BaseReport {
	
	private static final long serialVersionUID = -8669468448167267843L;
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
