package com.flextronics.cn.util;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public abstract class TouchScreenOnTouchListener {

	private final static String TAG = "TouchScreenOnTouchListener";
	
	public abstract void doSthInAvailableArea();
	public abstract void doSthNotInAvailableArea();

	public View.OnTouchListener createOnTouchListener(final ImageView imageView, final double r, String answerStr){

		return new View.OnTouchListener(){

			public boolean onTouch(View v, MotionEvent event) {
				
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int layout_x;	//imageView左上角顶点的横坐标
					int layout_y;	//imageView左上角顶点的纵坐标
					int height;	//imageView高度
					int width;	//imageView宽度
					int center_x;	//imageView中心点的横坐标
					int center_y;	//imageView中心点的纵坐标
					int[] location = new int[2];

					imageView.getLocationOnScreen(location);
					layout_x = location[0];
					layout_y = location[1];
					width = imageView.getWidth();
					height = imageView.getHeight();

					center_x = layout_x + width/2;
					center_y = layout_y + height/2;

					Log.d(TAG, "layout_x:" + layout_x + ", layout_y:" + layout_y + ", width:" + width +
							", height:" + height + ", center_x:" + center_x + ", center_y:" + center_y);

					float event_x = layout_x + event.getX();
					float event_y = layout_y + event.getY();

					Log.d(TAG, "event_x:" + event_x + ", event_y:" + event_y);

					float distance2 = (event_x-center_x)*(event_x-center_x) + (event_y-center_y)*(event_y-center_y);
					if (distance2 <= r*r) {
						Log.d(TAG, "in available operation area");
						doSthInAvailableArea();
					}else {
						Log.d(TAG, "not in available operation area");
						doSthNotInAvailableArea();
					}
				}							

				return true;				
			}        	
		};
	}
}
