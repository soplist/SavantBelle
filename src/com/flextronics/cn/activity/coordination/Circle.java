/**
 * 
 */
package com.flextronics.cn.activity.coordination;

/**
 * 圆圈(设计为不可变类)
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 下午02:10:27
 */
public final class Circle {
	private int centerX;
	private int centerY;
	private float radius;
	// 标识该圆圈是否被点中
	volatile private boolean clicked = false;

	public Circle(int centerX, int centerY, float radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean b) {
		this.clicked = b;
	}
}
