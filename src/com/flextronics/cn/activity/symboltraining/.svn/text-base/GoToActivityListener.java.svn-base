/**
 * 
 */
package com.flextronics.cn.activity.symboltraining;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

/**
 * {@link View.OnTouchListener}的实现类，实现了由指定的Activity跳转向指定的另外的Activity的功能
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-2-23 下午01:48:02
 */
public class GoToActivityListener implements View.OnTouchListener {

	private Activity currentActivity;

	private Class<?> target;

	/**
	 * 是否需要进行确认
	 */
	private boolean confirm;

	public GoToActivityListener(Activity currentActivity, Class<?> target) {
		this(currentActivity, target, true);
	}

	public GoToActivityListener(Activity currentActivity, Class<?> target,
			boolean confirm) {
		this.currentActivity = currentActivity;
		this.target = target;
		this.confirm = confirm;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!confirm) {
				gotoActivity();
			} else {
				new AlertDialog.Builder(currentActivity).setTitle("退出")
						.setMessage("确定要退出?").setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										gotoActivity();
									}
								}).setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
			}
		}
		return false;
	}

	private void gotoActivity() {
		currentActivity.startActivity(new Intent(currentActivity
				.getApplicationContext(), target));
		currentActivity.finish();
	}

}
