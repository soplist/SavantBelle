package com.flextronics.cn.model;

import org.achartengine.chart.BarChart;

import com.flextronics.cn.model.BaseReport;

/**<b>记忆训练的测试报告的基类</b><br>
 * 各记忆训练的测试报告可继承此类
 * 
 * @author ZhangGuoYin
 *
 */
public class BaseMemoryTrainingReport extends BaseReport {

	private static final long serialVersionUID = -8527188984279106013L;
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
