/**
 * 
 */
package com.flextronics.cn.activity.coordination;

/**
 * 点(设计成不可变类)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午11:06:11
 */
public final class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point src) {
		this.x = src.x;
		this.y = src.y;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

}
