package com.flextronics.cn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * @author ZhangGuoYin
 *
 */
public class ViewAdapter4ChooseTraining extends BaseAdapter {
	 
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

	public ViewAdapter4ChooseTraining(Context context, int[] layoutResourceIds) {
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
				
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View view =	layoutInflater.inflate(layoutResourceId[position], null);
		
		onViewCreatedListener.onViewCreated(view, position);
		return view;
	}
}