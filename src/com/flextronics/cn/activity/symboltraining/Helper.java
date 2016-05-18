/**
 * 
 */
package com.flextronics.cn.activity.symboltraining;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**
 * @author Zhaohe.Guo
 * 
 *         2010-12-9 下午03:04:55
 */
public class Helper {
	private static final String TAG = Helper.class.getSimpleName();

	private static final Map<Integer, Integer> patternToUnitcount = new HashMap<Integer, Integer>();
	static {
		patternToUnitcount.put(
				Constants.SymbolTraingParam.DISPLAY_PATTERN_CIRCULAR, 16);
		patternToUnitcount.put(
				Constants.SymbolTraingParam.DISPLAY_PATTERN_TRIANGLE, 28);
	}

	/**
	 * 根据用户选择的样本类型加载操作面板
	 * 
	 * @param sampleSet
	 * @return
	 */
	public static ViewGroup getOperationPanel(int sampleSet,
			BaseActivity context, final OperationPanelListener l) {
		int resourceID = 0;
		switch (sampleSet) {
		case Constants.Sample.NUMBERS:
			resourceID = R.layout.symbol_response_training_operate_panel_2_5;
			break;
		case Constants.Sample.ROME_NUMBERS:
			resourceID = R.layout.symbol_response_training_operate_panel_2_5;
			break;
		case Constants.Sample.ENGLISH_LETTERS:
			resourceID = R.layout.symbol_response_training_operate_panel_3_10;
			break;
		case Constants.Sample.MUSIC_MARKS:
			resourceID = R.layout.symbol_response_training_operate_panel_2_10;
			break;
		case Constants.Sample.COMMON_MARKS:
			resourceID = R.layout.symbol_response_training_operate_panel_2_10;
			break;
		default:
			Log.e(TAG, "sample set type ERROR!type=" + sampleSet);
		}
		ViewGroup viewGroup = (ViewGroup) context.getBaseLayoutInflater()
				.inflate(resourceID, null);
		int length = CommonUtil.getSampleElementImageResBySampleCode(sampleSet).length;

		for (int i = 0; i < length; i++) {
			int id = context.getResources().getIdentifier("ImageView" + i,
					"id", context.getPackageName());
			int imageID = CommonUtil
					.getSampleElementImageResBySampleCode(sampleSet)[i];
			ImageView imageView = ((ImageView) viewGroup.findViewById(id));
			imageView.setImageResource(imageID);
			final int index = i;
			imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					l.onClick(v, index);
				}
			});
		}
		return viewGroup;
	}

	/**
	 * 返回相应的layout的ID号
	 * 
	 * @param pattern
	 *            图样编号
	 * @param unitCount
	 *            位元个数
	 * @return
	 */
	public static int getLayoutResourceID(Context context, int pattern,
			int unitCount) {
		int resID = 0;
		String prefix = "symbol_tuyang_";
		String name = "";
		if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_CIRCULAR) {// 圆形图样
			if (unitCount > 16)
				unitCount = 16;// 圆形图样的最大位元数为16
			name = prefix + "3_" + unitCount;
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_TRIANGLE) {
			if (unitCount > 18) {
				unitCount = 18;
			}
			name = prefix + "4";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_SQUARE) {
			name = prefix + "5";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_HEXAGON) {
			name = prefix + "6";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_RECTANGLE) {
			name = prefix + "7";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_SECTOR) {
			if (unitCount <= 12) {
				name = prefix + "8_1_" + unitCount;
			} else if (unitCount <= 15) {
				name = prefix + "8_2";
			} else if (unitCount <= 18) {
				name = prefix + "8_3";
			} else if (unitCount <= 20) {
				name = prefix + "8_4";
			} else if (unitCount <= 24) {
				name = prefix + "8_5";
			} else if (unitCount <= 30) {
				name = prefix + "8_6";
			}
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_PARALLELOGRAM) {
			name = prefix + "9";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_DIAMOND) {
			name = prefix + "10";
		} else if (pattern == Constants.SymbolTraingParam.DISPLAY_PATTERN_PENTAGON) {
			name = prefix + "11";
		}
		resID = context.getResources().getIdentifier(name, "layout",
				context.getPackageName());
		return resID;
	}

	/**
	 * 返回指定图样所能支持的最大位元数
	 * 
	 * @param displayPattern
	 * @return
	 */
	public static int getMaxUnitCount(int displayPattern) {
		if (patternToUnitcount.get(displayPattern) == null) {
			return 30;
		} else {
			return patternToUnitcount.get(displayPattern);
		}
	}

	public static Bitmap getScaledImage(Context context, int drawableID,
			float x, float y) {
		Bitmap d = ((BitmapDrawable) context.getResources().getDrawable(
				drawableID)).getBitmap();
		Matrix m = new Matrix();
		m.postScale(x, y);
		return Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m,
				true);
	}

	public static interface OperationPanelListener {
		/**
		 * @param view
		 *            被点击的view
		 * @param index
		 *            被点击的view在OperationPanel中所处的index
		 */
		public void onClick(View view, int index);
	}
}
