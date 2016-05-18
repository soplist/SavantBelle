/**
 * 
 */
package com.flextronics.cn.activity.coordination;

/**
 * 线段(设计成不可变类)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午11:11:45
 */
public final class LineSegment {

	private Point start;
	private Point end;

	public LineSegment(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public final int getStartX() {
		return start.getX();
	}

	public final int getStartY() {
		return start.getY();
	}

	public final int getEndX() {
		return end.getX();
	}

	public final int getEndY() {
		return end.getY();
	}

}
