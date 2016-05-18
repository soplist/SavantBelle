package com.flextronics.cn.activity.symboltraining.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flextronics.cn.activity.symboltraining.Helper;

/**
 * 按照指定的图样进行显示(如圆形，三角形等)
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-2-28 下午01:50:05
 */
public class PatternDisplayPanel extends DisplayPanel {
	LinearLayout layout = null;
	int pattern;// 图形图样

	public PatternDisplayPanel(Context context, int[] drawables,
			boolean random, int pattern) {
		super(context, drawables, random);
		layout = new LinearLayout(context);
		this.pattern = pattern;
	}

	protected void setupLayout(int size) {
		layout.removeAllViews();
		children = new ImageView[size];
		int viewID = Helper.getLayoutResourceID(context, pattern, size);
		View view = inflater.inflate(viewID, null);
		layout.addView(view);
		int imageViewCount = getImageViwCount(viewID);
		ImageView[] tempChildren = new ImageView[imageViewCount];
		imagePosition2array = getdArray(imageViewCount, random);
		for (int i = 0; i < imageViewCount; i++) {
			String append = (i + 1) < 10 ? ("0" + (i + 1)) : ("" + (i + 1));
			tempChildren[imagePosition2array[i]] = (ImageView) view
					.findViewById(context.getResources().getIdentifier(
							"ImageView" + append, "id",
							context.getPackageName()));
		}
		System.arraycopy(tempChildren, 0, children, 0, size);
	}

	@Override
	public ImageView[] getAllElements() {
		return children;
	}

	@Override
	public View getDisplayableView() {
		return layout;
	}

	@Override
	protected float getImageScale() {
		// if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_CIRCULAR)
		// {
		// return 0.65f;
		// } else if (pattern ==
		// Constants.SymbolTraingParam.DISPLAY_PATTERN_DIAMOND) {
		// return 1.0f;
		// }
		return 0.65f;
	}

	/**
	 * 检查parent中有多少个以ImageView+index的规则命名的ImageView
	 * 
	 * @param parent
	 * @return
	 */
	private int getImageViwCount(int parentLayoutID) {
		View parent = inflater.inflate(parentLayoutID, null);
		int index = 1;
		while (true) {
			String append = index < 10 ? ("0" + index) : ("" + index);
			int id = context.getResources().getIdentifier("ImageView" + append,
					"id", context.getPackageName());
			if (id == 0) {
				return index - 1;
			}
			if (parent.findViewById(id) == null) {
				return index - 1;
			}
			index++;
		}
	}

	@Override
	public int getMaxUnitCount() {
		return Helper.getMaxUnitCount(pattern);
	}
}
