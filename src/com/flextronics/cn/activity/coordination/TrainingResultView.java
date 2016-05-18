package com.flextronics.cn.activity.coordination;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flextronics.cn.activity.R;

/**
 * 负责对训练结果进行显示的页面
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-2-28 下午02:06:43
 */
public class TrainingResultView {
	private static final String TAG = TrainingResultView.class.getSimpleName();

	private Context context;
	private RelativeLayout relativeLayout;

	public TrainingResultView(Context context, String title, int timeCost,
			int wrongCount, CoordinationPattern pattern, List<Point> wrongPoints) {
		this.context = context;
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		{
			TextView tv = new TextView(context);
			tv.setText(title);
			tv.setGravity(Gravity.CENTER);
			layout.addView(tv);

		}
		{
			View view = new SingleViewTrainingResultView(context, pattern,
					wrongPoints);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			layout.addView(view, params);
		}
		{
			TextView tv = new TextView(context);
			tv.setText("花费时间:" + timeCost + "秒.");
			tv.setGravity(Gravity.LEFT);
			layout.addView(tv);
		}
		{
			TextView tv = new TextView(context);
			tv.setText("错误次数:" + wrongCount + "次.");
			tv.setGravity(Gravity.LEFT);
			layout.addView(tv);
		}

		relativeLayout = new RelativeLayout(context);
		relativeLayout.setGravity(Gravity.CENTER);
		relativeLayout.addView(layout);
	}

	public View getWrappedView() {
		return relativeLayout;
	}

	public static class SingleViewTrainingResultView extends View {
		private static final float FACTOR = 0.7f;
		private CoordinationPattern pattern;
		List<Point> wrongPoints;

		public SingleViewTrainingResultView(Context context,
				CoordinationPattern pattern, List<Point> wrongPoints) {
			super(context);
			this.pattern = pattern;
			this.wrongPoints = new ArrayList<Point>();
			for (int i = 0; i < wrongPoints.size(); i++) {
				this.wrongPoints.add(wrongPoints.get(i));
			}
		}

		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Log.d(TAG, "width=" + canvas.getWidth() + " height="
					+ canvas.getHeight());
			Bitmap wrongCross = BitmapFactory.decodeResource(this.getContext()
					.getResources(), R.drawable.red_cross);
			for (Point p : wrongPoints) {
				canvas.drawBitmap(wrongCross,
						(p.getX() - wrongCross.getWidth() / 2) * FACTOR, (p
								.getY() - wrongCross.getHeight() / 2)
								* FACTOR, null);
			}
			pattern.draw(canvas, FACTOR);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			Log.d(TAG, "on measuer ...widthMeasureSpec=" + widthMeasureSpec
					+ " heightMeasureSpec=" + heightMeasureSpec);
			this.setMeasuredDimension((int) (Helper.CTV_WIDTH * FACTOR),
					(int) (Helper.CTV_HEIGHT * FACTOR));
		}
	}
}
