/**
 * 
 */
package com.flextronics.cn.activity.coordination.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.flextronics.cn.activity.coordination.CoordinationTrainingView;
import com.flextronics.cn.activity.coordination.Point;
import com.flextronics.cn.activity.coordination.TrainingResultView;

/**
 * @author Zhaohe.Guo
 * 
 *         2011-1-10 下午04:24:31
 */
public class SingleViewTrainingListener implements
		CoordinationTrainingView.TrainingListener {
	private static final String TAG = SingleViewTrainingListener.class
			.getSimpleName();

	List<Point> wrongPoint = new ArrayList<Point>();
	int timeOutCount = 0;
	int wrongCount = 0;
	long startTime;
	int timeCost;

	@Override
	public void onEnd(CoordinationTrainingView view) {
		Log.d(TAG, "training end...");
		timeCost = (int) ((System.currentTimeMillis() - startTime) / 1000);
		View resultView = new TrainingResultView(view.getWrappedView()
				.getContext(), "左手", timeCost, getWrongCount(),
				((SingleCTViewImpl) view).getPattern(), getWrongPoints())
				.getWrappedView();
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view
				.getWrappedView().getContext()).setTitle("测试结束");
		dialogBuilder.setPositiveButton("重新再来", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO go to UI for select
			}
		});
		AlertDialog dialog = dialogBuilder.create();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.alpha = 0.9f;
		dialog.getWindow().setAttributes(params);
		dialog.setView(resultView);
		dialog.show();
	}

	@Override
	public void onRight(CoordinationTrainingView view) {
		Log.d(TAG, "answer right...");
	}

	@Override
	public void onWrong(CoordinationTrainingView view, int x, int y) {
		Log.d(TAG, "answer wrong...");
		increaseWrongCount();
		wrongPoint.add(new Point(x, y));
	}

	int getWrongCount() {
		return wrongCount + timeOutCount;
	}

	void increaseWrongCount() {
		wrongCount++;
	}

	void addWrongPoint(int x, int y) {
		wrongPoint.add(new Point(x, y));
	}

	List<Point> getWrongPoints() {
		return this.wrongPoint;
	}

	long getStartTime() {
		return startTime;
	}

	@Override
	public void onStart(CoordinationTrainingView view) {
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public void onTimeOut(CoordinationTrainingView view) {
		timeOutCount++;
	}

}
