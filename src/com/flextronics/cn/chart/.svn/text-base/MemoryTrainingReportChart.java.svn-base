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
public class MemoryTrainingReportChart {
	
	private final static String TAG = "MemoryTrainingReportChart";
	private Context context;
	private long testingId;
	private double yMaxValue;
	private AnswerQuestionDao answerQuestionDao;
	
	public MemoryTrainingReportChart(Context context, long testingId, 
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
	
	private XYMultipleSeriesDataset getBarDemoDataset(int questionCount) {		
		
		int unitCount = questionCount/3;
		int more = questionCount%3;
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		if (more == 0) {
			
			XYSeries xySeries = new XYSeries(context.getString(R.string._rightCount2));
			setSeries(xySeries, testingId, 0, unitCount, true, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2, true, 1.5);
			setSeries(xySeries, testingId, unitCount*2+1, unitCount*3, true, 2.5);
			dataset.addSeries(xySeries);

			xySeries = new XYSeries(context.getString(R.string._wrongCount2));
			setSeries(xySeries, testingId, 0, unitCount, false, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2, false, 1.5);
			setSeries(xySeries, testingId, unitCount*2+1, unitCount*3, false, 2.5);
			dataset.addSeries(xySeries);
		}else if (more == 1) {
			XYSeries xySeries = new XYSeries(context.getString(R.string._rightCount2));
			setSeries(xySeries, testingId, 0, unitCount, true, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2, true, 1.5);
			setSeries(xySeries, testingId, unitCount*2+1, unitCount*3+1, true, 2.5);
			dataset.addSeries(xySeries);

			xySeries = new XYSeries(context.getString(R.string._wrongCount2));
			setSeries(xySeries, testingId, 0, unitCount, false, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2, false, 1.5);
			setSeries(xySeries, testingId, unitCount*2+1, unitCount*3+1, false, 2.5);
			dataset.addSeries(xySeries);
		}else {
			XYSeries xySeries = new XYSeries(context.getString(R.string._rightCount2));
			setSeries(xySeries, testingId, 0, unitCount, true, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2+1, true, 1.5);
			setSeries(xySeries, testingId, unitCount*2+2, unitCount*3+2, true, 2.5);
			dataset.addSeries(xySeries);

			xySeries = new XYSeries(context.getString(R.string._wrongCount2));
			setSeries(xySeries, testingId, 0, unitCount, false, 0.5);
			setSeries(xySeries, testingId, unitCount+1, unitCount*2+1, false, 1.5);
			setSeries(xySeries, testingId, unitCount*2+2, unitCount*3+2, false, 2.5);
			dataset.addSeries(xySeries);
		}	
			
		return dataset;
	}
	
	private void setSeries(XYSeries xySeries, long testingId, long startQuestionId, long endQuestionId, boolean value, double xValue){
		double countValue = answerQuestionDao.getAnswerQuestionCountInQuestions(testingId, startQuestionId, endQuestionId, value);
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
	
	public BarChart generateBarChart(int questionCount){
				
		XYMultipleSeriesDataset dataSet = getBarDemoDataset(questionCount);
		
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