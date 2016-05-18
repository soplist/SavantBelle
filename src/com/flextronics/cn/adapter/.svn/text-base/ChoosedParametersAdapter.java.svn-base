package com.flextronics.cn.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ChoosedParametersAdapter extends BaseAdapter {
	private static final String TAG="ChoosedParametersAdapter";
	private Object[] objects;
	
	public ChoosedParametersAdapter(Object[] objects){
		this.objects=objects;
		Log.d(TAG,""+objects.length);
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		return (View) objects[position];
	}

}
