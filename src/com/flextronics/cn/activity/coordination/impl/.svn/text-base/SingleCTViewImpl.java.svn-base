/**
 * 
 */
package com.flextronics.cn.activity.coordination.impl;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.flextronics.cn.activity.coordination.CoordinationPattern;
import com.flextronics.cn.activity.coordination.CoordinationTrainingView;
import com.flextronics.cn.activity.coordination.Helper;
import com.flextronics.cn.activity.coordination.Circle;

/**
 * 单个图样的训练界面(单手操作时只有一个图样)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午10:58:18
 */
public class SingleCTViewImpl extends View implements CoordinationTrainingView {
	private static final String TAG = SingleCTViewImpl.class.getSimpleName();
	public static final int FPS = 2;// 界面的刷新频率
	public static final float INNER_CIRCLE_SCALE = 0.8F;// 内圈半径占总半径的比例

	private CoordinationPattern drawable;

	private int flashingIndex = 0;
	private Runnable flashRunnable = new FlashRunnable();
	private Handler handler;
	TrainingListener trainingListener = new SingleViewTrainingListener();

	boolean actived = true;

	public SingleCTViewImpl(Context context, CoordinationPattern drawable) {
		super(context);
		this.drawable = drawable;
		this.handler = new Handler();
		this.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log
						.d(TAG, "touched...x=" + event.getX() + " y="
								+ event.getY());
				List<Circle> circles = SingleCTViewImpl.this.drawable
						.getCircles();
				for (int i = 0; i < circles.size(); i++) {
					Circle circle = circles.get(i);
					if (Helper.inCircle(circle.getCenterX(), circle
							.getCenterY(),
							(int) (circle.getRadius() * INNER_CIRCLE_SCALE),
							(int) event.getX(), (int) event.getY())) {
						onCircleClicked(i, (int) event.getX(), (int) event
								.getY());
						return false;
					}
				}
				onNothingClicked((int) event.getX(), (int) event.getY());
				return false;
			}
		});
	}

	/**
	 * 碰触到外圈或者屏幕的其他地方
	 * 
	 * @param y
	 * @param x
	 */
	private void onNothingClicked(int x, int y) {
		Log.d(TAG, "nothing is clicked...");
		if (trainingListener != null) {
			trainingListener.onWrong(this, x, y);
		}
	}

	/**
	 * 碰触到某个圆圈的内圈
	 * 
	 * @param i
	 * @param x
	 * @param y
	 */
	private void onCircleClicked(int i, int x, int y) {
		Log.d(TAG, "circle " + i + " is clicked...");
		if (actived && i == flashingIndex) {
			// 回答正确
			changeColor(i);
			flashingIndex++;
			if (trainingListener != null) {
				trainingListener.onRight(this);
			}
			if (flashingIndex >= this.drawable.getCircles().size()) {
				if (trainingListener != null) {
					trainingListener.onEnd(this);
				}
			}
		} else {
			if (trainingListener != null) {
				trainingListener.onWrong(this, x, y);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawable.draw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.d(TAG, "widthMesureSpec=" + widthMeasureSpec + " heightMesureSpec="
				+ heightMeasureSpec);
		this.setMeasuredDimension(Helper.CTV_WIDTH, Helper.CTV_HEIGHT);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Log.d(TAG, "on detached from window");
		handler.removeCallbacksAndMessages(null);
	}

	public void flash(int flashIndex) {
		this.flashingIndex = flashIndex;
	}

	public void startFlash() {
		handler.post(flashRunnable);
		if (flashingIndex == 0 && this.trainingListener != null) {
			this.trainingListener.onStart(this);
		}
	}

	public void stopFlash() {
		handler.removeCallbacks(flashRunnable);
	}

	public int getCircleCount() {
		return drawable.getCircles().size();
	}

	public void changeColor(int index) {
		Circle circle = drawable.getCircles().get(index);
		circle.setClicked(true);
		Log.d(TAG, "set circle " + index + " clicked...");
		invalidate(circle.getCenterX() - (int) circle.getRadius(), circle
				.getCenterY()
				- (int) circle.getRadius(), circle.getCenterX()
				+ (int) circle.getRadius(), circle.getCenterY()
				+ (int) circle.getRadius());
	}

	class FlashRunnable implements Runnable {
		@Override
		public void run() {
			drawable.flash(flashingIndex);
			invalidate();
			handler.postDelayed(this, 1000 / FPS);
		}
	}

	@Override
	public View getWrappedView() {
		return this;
	}

	public void deActive() {
		this.actived = false;
	}

	public void active() {
		this.actived = true;
	}

	public boolean isActived() {
		return this.actived;
	}

	public void setTrainingListener(TrainingListener l) {
		this.trainingListener = l;
	}

	public CoordinationPattern getPattern() {
		return this.drawable;
	}
}
