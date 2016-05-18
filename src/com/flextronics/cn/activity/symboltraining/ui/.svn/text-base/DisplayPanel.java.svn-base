/**
 * 
 */
package com.flextronics.cn.activity.symboltraining.ui;

import java.util.Map;
import java.util.WeakHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.flextronics.cn.activity.symboltraining.Helper;
import com.flextronics.cn.util.ArrayOperations;

/**
 * 负责显示问题的面板
 * 
 * @author Zhaohe.Guo
 * 
 *         2010-12-9 上午11:24:07
 */
public abstract class DisplayPanel {
	private static final String TAG = DisplayPanel.class.getSimpleName();
	protected Context context;
	/**
	 * 样本集的所有图片
	 */
	protected int[] drawables;

	protected int[] imagePosition2array = null;

	protected LayoutInflater inflater = null;

	protected ImageView[] children;

	protected Map<Integer, Bitmap> cache = new WeakHashMap<Integer, Bitmap>();

	/**
	 * 显示的时候是否是随机（不按照序号）显示
	 */
	protected boolean random = false;

	public DisplayPanel(Context context, int[] drawables, boolean random) {
		this.context = context;
		// this.elementCount = elementCount;
		this.drawables = drawables;
		this.random = random;
		inflater = LayoutInflater.from(context);
	}

	public Context getContext() {
		return context;
	}

	/**
	 * 设置指定位置的图片
	 * 
	 * @param index
	 * @param resourceID
	 */
	public void setImageResource(int index, int resourceID) {
		ImageView view = getAllElements()[index];
		view.setImageBitmap(getBitMap(resourceID));
		if (view.getVisibility() == View.INVISIBLE) {
			view.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 清除所有的动画和图片
	 */
	public void clearAnimationAndImage() {
		getDisplayableView().clearAnimation();
		for (ImageView v : getAllElements()) {
			v.setVisibility(View.INVISIBLE);
			v.clearAnimation();
		}
	}

	/**
	 * 显示问题
	 * 
	 * @param question
	 */
	public final void arrangeQuestion(int[] question) {
		setupLayout(question.length);
		for (int i = 0; i < children.length; i++) {
			Log.d(TAG, "arrange question :id=" + i);
			children[i].setImageResource(this.drawables[question[i]]);
			// Matrix m = new Matrix();
			// m.postScale(getImageScale(), getImageScale());
			// children[i].setImageMatrix(m);
			children[i].setImageBitmap(getBitMap(drawables[question[i]]));
		}
	}

	/**
	 * 返回要显示的View
	 * 
	 * @return
	 */
	public abstract View getDisplayableView();

	/**
	 * 布局中所有的用于显示样本元素的View
	 * 
	 * @return
	 */
	public abstract ImageView[] getAllElements();

	/**
	 * 显示问题之前，根据元素的个数进行布局
	 * 
	 * @param elementCount
	 */
	protected abstract void setupLayout(int elementCount);

	/**
	 * 返回该显示面板所支持的最大位元数
	 * 
	 * @return
	 */
	public abstract int getMaxUnitCount();

	protected int[] getdArray(int size, boolean shuffle) {
		int[] shuffeledArray = new int[size];
		for (int i = 0; i < shuffeledArray.length; i++) {
			shuffeledArray[i] = i;
		}
		if (shuffle) {
			return ArrayOperations.shuffle(shuffeledArray);
		} else {
			return shuffeledArray;
		}
	}

	protected float getImageScale() {
		return 1.0f;
	}

	protected Bitmap getBitMap(int resourceID) {
		Bitmap bitmap = cache.get(resourceID);
		if (bitmap == null) {
			bitmap = Helper.getScaledImage(context, resourceID,
					getImageScale(), getImageScale());
			cache.put(resourceID, bitmap);
		}
		return bitmap;
	}
}
