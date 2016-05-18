package com.flextronics.cn.chart;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;



public class FingerStabilityTrainingReportChart{
	private Context context;
	public FingerStabilityTrainingReportChart(Context context) {
		super();
		this.context = context;
	}

	/**
	   * Returns the chart name.
	   * @return the chart name
	   */
	  public String getName() {
	    return "Budget chart";
	  }
	  
	  /**
	   * Returns the chart description.
	   * @return the chart description
	   */
	  public String getDesc() {
	    return "The budget per project for this year (pie chart)";
	  }
	  
	  /**
	   * Executes the chart demo.
	   * @param context the context
	   * @return the built intent
	   */
	  public GraphicalView execute(Context context,double correctNum,double errorNum) {
	    double[] values = new double[] {correctNum,errorNum};
	    int[] colors = new int[] {Color.BLUE,Color.RED};
	    DefaultRenderer renderer = buildCategoryRenderer(colors);
	    renderer.setLabelsTextSize(10);
	    return ChartFactory.getPieChartView(context,buildCategoryDataset("", values), renderer);
	  }
	  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		    DefaultRenderer renderer = new DefaultRenderer();
		    for (int color : colors) {
		      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		      r.setColor(color);
		      renderer.addSeriesRenderer(r);
		    }
		    return renderer;
	  }
	  protected CategorySeries buildCategoryDataset(String title, double[] values) {
		    CategorySeries series = new CategorySeries(title);
		    series.add("correct points", values[0]);
		    series.add("error points", values[1]);
		    
            return series;
		  }
}
