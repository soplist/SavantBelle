/**
 * 
 */
package com.flextronics.cn.activity.coordination.impl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.flextronics.cn.activity.coordination.CoordinationTrainingView;
import com.flextronics.cn.activity.coordination.TrainingResultView;

/**
 * @author Zhaohe.Guo
 * 
 *         2011-1-12 下午05:03:40
 */
public class DoubleViewTrainingListener implements
		CoordinationTrainingView.TrainingListener {
	private static final String TAG = DoubleViewTrainingListener.class
			.getSimpleName();

	SingleViewTrainingListener leftListener = new SingleViewTrainingListener();
	SingleViewTrainingListener rightListener = new SingleViewTrainingListener();

	boolean leftEnd = false;
	boolean rightEnd = false;

	SingleCTViewImpl left;
	SingleCTViewImpl right;

	int leftTimeCost;
	int rightTimeCost;

	public DoubleViewTrainingListener(SingleCTViewImpl left,
			SingleCTViewImpl right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void onEnd(CoordinationTrainingView view) {
		if (view == left) {
			leftEnd = true;
			leftTimeCost = (int) ((System.currentTimeMillis() - leftListener
					.getStartTime()) / 1000);
		}
		if (view == right) {
			rightEnd = true;
			rightTimeCost = (int) ((System.currentTimeMillis() - rightListener
					.getStartTime()) / 1000);
		}
		if (leftEnd && rightEnd) {
			// end... 显示测试结果
			View leftView = new TrainingResultView(left.getWrappedView()
					.getContext(), "左手", leftTimeCost, leftListener
					.getWrongCount(), left.getPattern(), leftListener
					.getWrongPoints()).getWrappedView();
			View rightView = new TrainingResultView(right.getWrappedView()
					.getContext(), "右手", rightTimeCost, rightListener
					.getWrongCount(), right.getPattern(), rightListener
					.getWrongPoints()).getWrappedView();
			LinearLayout layout = new LinearLayout(view.getWrappedView()
					.getContext());
			layout.addView(leftView);
			layout.addView(rightView);
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view
					.getWrappedView().getContext()).setTitle("测试结束");
			dialogBuilder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO go to UI for select
				}
			});
			AlertDialog dialog = dialogBuilder.create();
			WindowManager.LayoutParams params = dialog.getWindow()
					.getAttributes();
			params.alpha = 0.9f;
			dialog.getWindow().setAttributes(params);
			dialog.setView(layout);
			dialog.show();
		}
	}

	@Override
	public void onRight(CoordinationTrainingView view) {
		if (left.isActived()) {
			leftListener.onRight(left);
			if (!rightEnd) {
				left.deActive();
				left.stopFlash();
				right.active();
				right.startFlash();
			}
		} else {
			rightListener.onRight(right);
			if (!leftEnd) {
				right.deActive();
				right.stopFlash();
				left.active();
				left.startFlash();
			}
		}
	}

	@Override
	public void onWrong(CoordinationTrainingView view, int x, int y) {
		// 此处不能使用view==left来判断，应为左手点错的位置可能在右手的区域，此时回调onWrong的是right
		if (left.isActived()) {
			leftListener.increaseWrongCount();
		} else {
			rightListener.increaseWrongCount();
		}
		// 错误点位于哪个view中，将该点加入相应的listener的错误记录中
		if (view == left) {
			leftListener.addWrongPoint(x, y);
		} else {
			rightListener.addWrongPoint(x, y);
		}
	}

	@Override
	public void onStart(CoordinationTrainingView view) {
		if (view == left) {
			Log.d(TAG, "left start...");
			leftListener.onStart(view);
		} else {
			Log.d(TAG, "right start...");
			rightListener.onStart(view);
		}
	}

	@Override
	public void onTimeOut(CoordinationTrainingView view) {
		if (view == left) {
			leftListener.onTimeOut(view);
		} else {
			rightListener.onTimeOut(view);
		}
	}

}
