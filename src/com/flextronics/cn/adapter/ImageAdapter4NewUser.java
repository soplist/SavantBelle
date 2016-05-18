package com.flextronics.cn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 
 * @author ZhangGuoYin
 *
 */
public class ImageAdapter4NewUser extends BaseAdapter {
	 
	private final static String TAG = "ImageAdapter4NewUser";
	private Context mContext;
	private int[] imageResourceId;	
	private OnViewCreatedListener onViewCreatedListener;

	
	public static abstract interface OnViewCreatedListener {
		public abstract void onViewCreated(View view, int position);
	}
	
	public void setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener) {
		this.onViewCreatedListener = onViewCreatedListener;
	}

	public ImageAdapter4NewUser(Context context, int[] imageResourceIds) {
		super();
		this.mContext = context;  
		this.imageResourceId = imageResourceIds;
		this.onViewCreatedListener = new OnViewCreatedListener(){

			public void onViewCreated(View view, int position) {
				// TODO Auto-generated method stub				
			}			
		};
	}

	public int getCount() { 
		return imageResourceId.length;
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
		
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(imageResourceId[position]);

		onViewCreatedListener.onViewCreated(imageView, position);
		return imageView;
	}
}