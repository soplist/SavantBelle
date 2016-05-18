package com.flextronics.cn.activity.coordination.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flextronics.cn.activity.coordination.CoordinationPattern;
import com.flextronics.cn.activity.coordination.CoordinationTrainingView;
import com.flextronics.cn.activity.coordination.impl.DoubleViewTrainingListener;

/**
 * 两个图样时的训练界面(双手操作时有两个图样)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-10 下午02:37:23
 */
public class DoubleCTViewImpl implements CoordinationTrainingView {
	private static final String TAG = DoubleCTViewImpl.class.getSimpleName();

	ViewGroup wrappedView;
	SingleCTViewImpl left;
	SingleCTViewImpl right;

	public DoubleCTViewImpl(Context context, CoordinationPattern _left,
			CoordinationPattern _right) {
		wrappedView = new LinearLayout(context);
		this.left = new SingleCTViewImpl(context, _left);
		this.right = new SingleCTViewImpl(context, _right);
		wrappedView.addView(this.left.getWrappedView(),
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		wrappedView.addView(this.right.getWrappedView(),
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		left.active();
		right.deActive();
		TrainingListener l = new DoubleViewTrainingListener(left, right);
		left.setTrainingListener(l);
		right.setTrainingListener(l);
	}

	@Override
	public View getWrappedView() {
		return wrappedView;
	}

	@Override
	public void startFlash() {
		left.startFlash();
	}

	@Override
	public void stopFlash() {
		left.stopFlash();
		right.stopFlash();
	}
}
