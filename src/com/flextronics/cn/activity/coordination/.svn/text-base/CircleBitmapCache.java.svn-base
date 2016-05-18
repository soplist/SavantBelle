/**
 * 
 */
package com.flextronics.cn.activity.coordination;

import java.util.Map;
import java.util.WeakHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.flextronics.cn.activity.R;

/**
 * @author Zhaohe.Guo
 * 
 *         2011-1-5 上午10:39:02
 */
public class CircleBitmapCache {
	private static final String TAG = CircleBitmapCache.class.getSimpleName();
	/**
	 * 进行缩放之后的图片的缓存(半径到图片的映射)
	 */
	public static final Map<Integer, Bitmap> cache = new WeakHashMap<Integer, Bitmap>();
	
	public static final Map<Integer, Bitmap> clickedCache = new WeakHashMap<Integer, Bitmap>();

	/**
	 * 用于缩放的原始图片
	 */
	public static Bitmap original;
	public static int originalRadius;
	public static Bitmap clickedOriginal;
	public static int clieckedOriginalRadius;

	public static void init(Context context) {
		original = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.white_light);
		originalRadius = original.getWidth() / 2;
		clickedOriginal = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.red_light);
		clieckedOriginalRadius = clickedOriginal.getWidth() / 2;
		Log.d(TAG, "originalRadius=" + originalRadius);
	}

	public static Bitmap getCircleBitmap(int radius) {
		Bitmap bm = cache.get(radius);
		if (bm == null) {
			Matrix m = new Matrix();
			m.postScale((float) radius / originalRadius, (float) radius
					/ originalRadius);
			bm = Bitmap.createBitmap(original, 0, 0, original.getWidth(),
					original.getHeight(), m, true);
			cache.put(radius, bm);
		}
		return bm;
	}
	
	public static Bitmap getClickedCircleBitmap(int radius){
		Bitmap bm = clickedCache.get(radius);
		if (bm == null) {
			Matrix m = new Matrix();
			m.postScale((float) radius / clieckedOriginalRadius, (float) radius
					/ clieckedOriginalRadius);
			bm = Bitmap.createBitmap(clickedOriginal, 0, 0, clickedOriginal.getWidth(),
					clickedOriginal.getHeight(), m, true);
			clickedCache.put(radius, bm);
		}
		return bm;
	}
}
