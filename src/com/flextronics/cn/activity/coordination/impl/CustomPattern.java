/**
 * 
 */
package com.flextronics.cn.activity.coordination.impl;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.coordination.Point;
import com.flextronics.cn.activity.coordination.Circle;

/**
 * 自定义的图样，如兔子，飞机等
 * @author Zhaohe.Guo
 * 
 *         2011-1-5 下午04:35:55
 */
public class CustomPattern extends BaseCoordinationPattern {

	private static final int RADIUS = 13;

	public CustomPattern(List<Point> points) {
		circles = new ArrayList<Circle>();
		for (Point point : points) {
			circles.add(new Circle(point.getX(), point.getY(), RADIUS));
		}
	}

}
