/**
 * 
 */
package com.flextronics.cn.activity.coordination;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

/**
 * @author Zhaohe.Guo
 * 
 *         2011-1-5 上午10:33:24
 */
public class Helper {

	public static final int CTV_WIDTH = 450;
	public static final int CTV_HEIGHT = 500;

	private static final int[][] pattern0 = new int[][] { { 267, 10 },
			{ 236, 19 }, { 205, 33 }, { 179, 49 }, { 151, 72 }, { 130, 97 },
			{ 96, 94 }, { 66, 107 }, { 40, 129 }, { 20, 152 }, { 13, 185 },
			{ 16, 215 }, { 50, 227 }, { 86, 224 }, { 95, 249 }, { 100, 277 },
			{ 108, 306 }, { 118, 334 }, { 130, 359 }, { 147, 380 },
			{ 160, 403 }, { 154, 430 }, { 133, 458 }, { 109, 473 },
			{ 151, 473 }, { 178, 455 }, { 192, 425 }, { 220, 428 },
			{ 241, 452 }, { 215, 476 }, { 246, 483 }, { 273, 484 },
			{ 305, 485 }, { 338, 486 }, { 368, 480 }, { 396, 488 },
			{ 424, 483 }, { 438, 455 }, { 432, 422 }, { 399, 403 },
			{ 397, 374 }, { 393, 345 }, { 392, 315 }, { 386, 286 },
			{ 375, 259 }, { 360, 232 }, { 339, 209 }, { 313, 190 },
			{ 288, 176 }, { 260, 169 }, { 229, 166 }, { 198, 164 },
			{ 169, 150 }, { 156, 121 }, { 186, 101 }, { 212, 87 }, { 231, 66 },
			{ 255, 40 } };

	private static final int[][] pattern1 = new int[][] { { 133, 16 },
			{ 103, 51 }, { 83, 88 }, { 74, 128 }, { 70, 173 }, { 66, 224 },
			{ 64, 274 }, { 67, 321 }, { 77, 367 }, { 79, 408 }, { 46, 420 },
			{ 12, 436 }, { 65, 448 }, { 103, 440 }, { 123, 411 }, { 155, 432 },
			{ 188, 446 }, { 228, 466 }, { 264, 482 }, { 300, 470 },
			{ 256, 441 }, { 286, 435 }, { 322, 432 }, { 362, 428 },
			{ 399, 439 }, { 434, 425 }, { 387, 397 }, { 359, 366 },
			{ 336, 326 }, { 302, 309 }, { 261, 295 }, { 225, 274 },
			{ 202, 234 }, { 180, 191 }, { 164, 149 }, { 152, 108 }, { 150, 60 } };

	private static final int[][] pattern2 = new int[][] { { 133, 16 },
			{ 103, 51 }, { 83, 88 } };

	private static final int[][][] patterns = new int[][][] { pattern0,
			pattern1, pattern2 };

	/**
	 * @param context
	 * @param drawableID
	 * @param x
	 * @param y
	 * @return
	 */
	public static Bitmap getScaledImage(Context context, int drawableID,
			float x, float y) {
		Bitmap d = ((BitmapDrawable) context.getResources().getDrawable(
				drawableID)).getBitmap();
		Matrix m = new Matrix();
		m.postScale(x, y);
		return Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m,
				true);
	}

	/**
	 * 判断指定的点是否在圆圈内部
	 * 
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean inCircle(int centerX, int centerY, int radius, int x,
			int y) {
		return Math.pow((centerX - x), 2) + Math.pow((centerY - y), 2) < Math
				.pow(radius, 2);
	}

	/**
	 * @return 兔子图样的点集
	 */
	public static List<Point> getPattern(int index) {
		return getPoints(patterns[index]);
	}

	private static List<Point> getPoints(int[][] points) {
		List<Point> list = new ArrayList<Point>();
		for (int[] p : points) {
			list.add(new Point(p[0], p[1]));
			// list.add(new Point((int) (p[0] * X_RATE + X_OFFSET), (int) (p[1]
			// * Y_RATE + Y_OFFSET)));
		}
		return list;
	}
}
