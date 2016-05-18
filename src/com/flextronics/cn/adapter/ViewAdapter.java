package com.flextronics.cn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;

public class ViewAdapter extends BaseAdapter {
	 
	private final static String TAG = "ViewAdapter";
	private Context mContext;
	private int[] layoutResourceId;
	private OnViewCreatedListener onViewCreatedListener;

	
	public static abstract interface OnViewCreatedListener {
		public abstract void onViewCreated(View view, int position);
	}
	
	public void setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener) {
		this.onViewCreatedListener = onViewCreatedListener;
	}

	public ViewAdapter(Context context, int[] layoutResourceIds) {
		super();
		this.mContext = context;  
		this.layoutResourceId = layoutResourceIds;
		this.onViewCreatedListener = new OnViewCreatedListener(){

			public void onViewCreated(View view, int position) {
				// TODO Auto-generated method stub				
			}			
		};
	}

	public int getCount() { 
		return layoutResourceId.length;
	}
	
	public Object getItem(int position) {  
		Log.d(TAG, "getItem -- position: " + position);
		return position;
	}

	public long getItemId(int position) { 
		Log.d(TAG, "getItemId -- position: " + position);
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.d(TAG, "getView() -- position: " + position);
		
		if (convertView != null) {
		//	return convertView;
		}
		
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View view =	layoutInflater.inflate(layoutResourceId[position], null);			
		float scale = 0f;
		ScaleAnimation  myAnimation_Scale1 = new ScaleAnimation(scale, scale, scale, scale,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		myAnimation_Scale1.setFillAfter(true);
		myAnimation_Scale1.setInterpolator(new AccelerateInterpolator());
		view.setAnimation(myAnimation_Scale1);

		onViewCreatedListener.onViewCreated(view, position);
		return view;
	}
}