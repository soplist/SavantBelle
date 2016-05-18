/**
 * 
 */
package com.flextronics.cn.activity.coordination.impl;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.coordination.Point;
import com.flextronics.cn.activity.coordination.Circle;

/**
 * 所有圆圈拍成直线状的图样
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午11:16:06
 */
public class LinePattern extends BaseCoordinationPattern {
	private static final String TAG = LinePattern.class.getSimpleName();

	private List<Circle> circles = null;

	private int circleCount = 10;// 每条线之间圆圈的数量

	private int flashingIndex;
	private boolean flashing;

	public LinePattern(List<Point>... pointsCollections) {
		circles = new ArrayList<Circle>();
		for (List<Point> points : pointsCollections) {
			initCircles(points);
		}
	}

	/**
	 * 已指定的线段为圆心排列圆的位置
	 * 
	 * @param l
	 */
	private void initCircles(List<Point> points) {
		boolean drawFirstCircle = true;
		for (int i = 0; i < points.size() - 1; i++) {
			Point startPoint = points.get(i);
			Point endPoint = points.get(i + 1);
			double l = Math.sqrt(Math.pow(startPoint.getX() - endPoint.getX(),
					2)
					+ Math.pow(startPoint.getY() - endPoint.getY(), 2));
			float r = (float) (l / (circleCount - 1) / 2);
			int xOff = (endPoint.getX() - startPoint.getX())
					/ (circleCount - 1);
			int yOff = (endPoint.getY() - startPoint.getY())
					/ (circleCount - 1);
			int centerX = startPoint.getX();
			int centerY = startPoint.getY();
			if (!drawFirstCircle) {
				centerX += xOff;
				centerY += yOff;
			}
			while (centerX * xOff <= endPoint.getX() * xOff
					&& centerY * yOff <= endPoint.getY() * yOff) {
				circles.add(new Circle(centerX, centerY, r));
				centerX += xOff;
				centerY += yOff;
			}
			drawFirstCircle = false;
		}
	}
}
