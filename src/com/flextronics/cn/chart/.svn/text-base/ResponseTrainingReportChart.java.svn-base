package com.flextronics.cn.chart;

import org.achartengine.chart.BarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.dao.AnswerQuestionDao;

/**
 * 
 * @author zhangguoyin
 *
 */
public class ResponseTrainingReportChart {
	
	private final static String TAG = "TestReportChart";
	private Context context;
	private long testingId;
	private double yMaxValue;
	private AnswerQuestionDao answerQuestionDao;
	
	public ResponseTrainingReportChart(Context context, long testingId, 
			AnswerQuestionDao answerQuestionDao) {
		super();
		this.context = context;
		this.testingId = testingId;
		this.answerQuestionDao = answerQuestionDao;
	}

	private XYMultipleSeriesRenderer getBarDemoRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		r.setColor(Color.RED);
		renderer.addSeriesRenderer(r);
		return renderer;
	}
	
	private XYMultipleSeriesDataset getBarDemoDataset(long startTime, long stopTime) {		
		
		if (startTime >= stopTime) {
			return null;
		}
		
		long periodTime = (stopTime - startTime)/3;
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		XYSeries xySeries = new XYSeries(context.getString(R.string._rightCount));
		setSeries(xySeries, testingId, startTime, startTime+periodTime, true, 0.5);
		setSeries(xySeries, testingId, startTime+periodTime+1, startTime+periodTime*2, true, 1.5);
		setSeries(xySeries, testingId, startTime+periodTime*2+1, stopTime, true, 2.5);
		dataset.addSeries(xySeries);

		xySeries = new XYSeries(context.getString(R.string._wrongCount));
		setSeries(xySeries, testingId, startTime, startTime+periodTime, false, 0.5);
		setSeries(xySeries, testingId, startTime+periodTime+1, startTime+periodTime*2, false, 1.5);
		setSeries(xySeries, testingId, startTime+periodTime*2+1, startTime+stopTime, false, 2.5);
		dataset.addSeries(xySeries);
		
		return dataset;
	}
	
	private void setSeries(XYSeries xySeries, long testingId, long startTime, long stopTime, boolean value, double xValue){
		double countValue = answerQuestionDao.getAnswerQuestionCount(testingId, startTime, stopTime, value);
	//	countValue = countValue==0?0.1:countValue;
		yMaxValue = yMaxValue<countValue?countValue:yMaxValue;			
		xySeries.add(xValue, countValue);
	}
	
	private void setChartSettings(XYMultipleSeriesRenderer renderer, double yMaxValue) {
	//	renderer.setChartTitle(" ");
		renderer.setXTitle(context.getString(R.string.testPeriod));
		renderer.setYTitle(context.getString(R.string.testResult));
		renderer.setShowLabels(true);
		renderer.setXAxisMin(0);
		renderer.setXAxisMax(3);
		renderer.setXLabels(1);
		renderer.addTextLabel(0.5, context.getString(R.string.period1));
		renderer.addTextLabel(1, " ");
		renderer.addTextLabel(1.5, context.getString(R.string.period2));
		renderer.addTextLabel(2, " ");
		renderer.addTextLabel(2.5, context.getString(R.string.period3));
		renderer.addTextLabel(3, " ");
		renderer.setYLabels(11);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(yMaxValue+1);
		renderer.setDisplayChartValues(true);
		renderer.setLabelsColor(Color.rgb(73, 73, 73));
		renderer.setAxesColor(Color.rgb(73, 73, 73));
		renderer.setLabelsTextSize(11);
	}
	
	public BarChart generateBarChart(long startTime, long stopTime){
				
		XYMultipleSeriesDataset dataSet = getBarDemoDataset(startTime, stopTime);
		
		XYMultipleSeriesRenderer renderer = getBarDemoRenderer();
		setChartSettings(renderer, yMaxValue);
		
		if ((dataSet != null) && (renderer != null) && 
				(dataSet.getSeriesCount() == renderer.getSeriesRendererCount())){
			
			BarChart localBarChart = new BarChart(dataSet, renderer, Type.DEFAULT);
			return localBarChart;
		}else {
			Log.e(TAG, "Dataset and renderer should be not null and should have the same number of series");
			return null;
		}
	}
}