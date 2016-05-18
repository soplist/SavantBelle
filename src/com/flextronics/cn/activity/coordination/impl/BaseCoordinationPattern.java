package com.flextronics.cn.activity.coordination.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.flextronics.cn.activity.coordination.Circle;
import com.flextronics.cn.activity.coordination.CircleBitmapCache;
import com.flextronics.cn.activity.coordination.CoordinationPattern;

/**
 * 所有图样的公共父类
 * @author Zhaohe.Guo
 * 
 *         2011-1-5 下午04:41:48
 */
public class BaseCoordinationPattern implements CoordinationPattern {
	private static final String TAG = BaseCoordinationPattern.class
			.getSimpleName();
	protected List<Circle> circles = new ArrayList<Circle>();

	private int flashingIndex;
	private boolean flashing;

	@Override
	public void draw(Canvas canvas) {
		this.draw(canvas, 1.0f);
	}

	@Override
	public void flash(int i) {
		flashingIndex = i;
		flashing = !flashing;
	}

	@Override
	public List<Circle> getCircles() {
		return Collections.unmodifiableList(circles);
	}

	@Override
	public void draw(Canvas canvas, float factor) {

		for (int i = 0; i < circles.size(); i++) {
			Circle circle = circles.get(i);
			Bitmap target = null;
			if (circle.isClicked()) {
				target = CircleBitmapCache.getClickedCircleBitmap((int) (circle
						.getRadius() * factor));
			} else if (flashing && (i == flashingIndex)) {
				target = CircleBitmapCache.getClickedCircleBitmap((int) (circle
						.getRadius() * factor));
			} else {
				target = CircleBitmapCache.getCircleBitmap((int) (circle
						.getRadius() * factor));
			}
			canvas.drawBitmap(target,
					(circle.getCenterX() - target.getWidth() / 2) * factor,
					(circle.getCenterY() - target.getHeight() / 2) * factor,
					null);
		}
	}

}
