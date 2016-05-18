package com.flextronics.cn.model.hearingtouch.responsetraining;

import org.achartengine.chart.BarChart;

import com.flextronics.cn.model.BaseReport;

/**
 * <b>听觉触觉反应训练</b></br>
 * 测试报告
 * @author ZhangGuoYin
 *
 */
public class HtrtReport extends BaseReport {

	private static final long serialVersionUID = -3852548297907030369L;
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
