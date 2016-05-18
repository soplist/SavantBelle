/**
 * 
 */
package com.flextronics.cn.activity.symboltraining.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 一般显示
 * 
 * @author Zhaohe.Guo
 * 
 *         2010-12-9 上午11:23:56
 */
public class CommonDisplayPanel extends DisplayPanel {

	private static final String TAG = CommonDisplayPanel.class.getSimpleName();

	private LinearLayout linearLayout = null;

	public CommonDisplayPanel(Context context, int[] drawables, boolean random) {
		super(context, drawables, random);
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
	}

	/**
	 * 根据符号元数初始化界面的布局
	 */
	protected void setupLayout(int elementCount) {
		imagePosition2array = getdArray(elementCount, random);
		linearLayout.removeAllViews();
		children = new ImageView[elementCount];
		LinearLayout newL = null;
		for (int i = 0; i < elementCount; i++) {
			if (i % 10 == 0) {
				newL = new LinearLayout(context);
				newL.setOrientation(LinearLayout.HORIZONTAL);
				linearLayout.addView(newL, ViewGroup.LayoutParams.FILL_PARENT,
						ViewGroup.LayoutParams.FILL_PARENT);
			}
			ImageView iv = new ImageView(context);
			newL.addView(iv);
			children[imagePosition2array[i]] = iv;
		}
	}

	@Override
	public View getDisplayableView() {
		return linearLayout;
	}

	@Override
	public ImageView[] getAllElements() {
		return children;
	}

	@Override
	public int getMaxUnitCount() {
		return 30;
	}
}
