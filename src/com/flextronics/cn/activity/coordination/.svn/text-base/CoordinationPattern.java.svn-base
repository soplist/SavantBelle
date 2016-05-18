/**
 * 
 */
package com.flextronics.cn.activity.coordination;

import java.util.List;

import android.graphics.Canvas;

/**
 * 手眼协调训练中用到的一些图样(如兔子，飞机等)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午11:00:32
 */
public interface CoordinationPattern {

	/**
	 * 按照1：1的比例绘制图样
	 * 
	 * @param canvas
	 */
	public void draw(Canvas canvas);

	/**
	 * 按照指定的比例绘制图样(如factor=2.0,则将原图样放大到原来的2倍)
	 * 
	 * @param canvas
	 * @param factor
	 *            指定的比例
	 */
	public void draw(Canvas canvas, float factor);

	/**
	 * @return 返回组成图样的所有圆圈的集合
	 */
	public List<Circle> getCircles();

	/**
	 * 闪烁指定的圆圈
	 * 
	 * @param i
	 */
	public void flash(int i);
}
