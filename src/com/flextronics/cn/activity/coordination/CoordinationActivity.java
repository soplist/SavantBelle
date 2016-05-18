/**
 * 
 */
package com.flextronics.cn.activity.coordination;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.coordination.impl.CustomPattern;
import com.flextronics.cn.activity.coordination.impl.DoubleCTViewImpl;
import com.flextronics.cn.activity.symboltraining.GoToActivityListener;

/**
 * 手眼协调训练
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-4 上午11:41:02
 */
public class CoordinationActivity extends BaseActivity {

	private static final String TAG = CoordinationActivity.class
			.getSimpleName();

	CoordinationTrainingView cView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int width = this.getWindowManager().getDefaultDisplay().getWidth();
		int height = this.getWindowManager().getDefaultDisplay().getHeight();
		Log.d(TAG, "width=" + width + ",height=" + height);
		CircleBitmapCache.init(this);
		// List<Point> points = new ArrayList<Point>();
		// points.add(new Point(0, 0));
		// points.add(new Point(SingleCTViewImpl.FIX_WIDTH / 2,
		// SingleCTViewImpl.FIX_HEIGHT));
		// points.add(new Point(SingleCTViewImpl.FIX_WIDTH, 0));
		CoordinationPattern drawable = new CustomPattern(Helper.getPattern(0));
		CoordinationPattern drawableRight = new CustomPattern(Helper
				.getPattern(1));
		// cView = new SingleCTViewImpl(this, drawableRight);
		cView = new DoubleCTViewImpl(this, drawable, drawableRight);
		RelativeLayout.LayoutParams centralParams = new RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		centralParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		this.getBaseRelativeLayout().addView(cView.getWrappedView(),
				centralParams);
		this.setContentView(getBaseRelativeLayout(), new LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));
		setupListeners();
		this.setTrainingTitle("协调度训练|双手");
		// 显示用户名
		setUserNameEnabled(true);
		// 显示用户头像
		setUserIconEnable(true);
	}

	private void setupListeners() {
		this.setOnHomeButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class));
		this.setOnBackButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class));
		this.setOkButtonEnable(false);
		this.setOnCancelButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class));
	}

	@Override
	protected void onStart() {
		super.onStart();
		cView.startFlash();
	}

	@Override
	protected void onStop() {
		super.onStop();
		cView.stopFlash();
	}

	protected void onDestroy() {
		super.onDestroy();
	}
}
