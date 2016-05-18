package com.flextronics.cn.util;

import android.util.Log;

public class Mathematics {
	private static String TAG = "mathematics";
	public static float calculateDistanceBetweenTwoPoint(Point p_1,Point p_2){
		//d=_/(x1-x2)^2+(y1-y2)^2
		float x_diminish_square = (float)Math.pow(p_1.getX()-p_2.getX(),2);
		float y_diminish_square = (float)Math.pow(p_1.getY()-p_2.getY(),2);
		float dis = (float) Math.sqrt(x_diminish_square+y_diminish_square);
		return dis;
	}
	public static int ceilAfterDivide(int a,int b){
		Log.d(TAG, ""+Math.ceil((double)a/(double)b));
		return (int)Math.ceil((double)a/(double)b);
	}
}
