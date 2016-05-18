package com.flextronics.cn.adapter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.HearingConstants;

public class ScaleAdapter extends BaseAdapter {
	//声明一个Context对象
	private Context context;
	//声明一个boolean变量
	private boolean select;
	//声明一个ImageView数组
	private ImageView[] imageViews;
	//声明一个int数组
	private int[] scale;
	//声明一个int数组
	private int [] scaleImageViewArray=
	{
	R.drawable.hs_do_button,R.drawable.hs_re_button,R.drawable.hs_mi_button,R.drawable.hs_fa_button,R.drawable.hs_so_button,
	R.drawable.hs_la_button,R.drawable.hs_si_button,R.drawable.hs_doo_button
	};
	//声明一个Map集合
	private Map<Integer, ImageView> scaleMap=new HashMap<Integer, ImageView>();
	
	public ScaleAdapter(Context context,boolean select,int[] scale){
		this.context=context;
		this.select=select;
		this.scale=scale;
		this.imageViews=new ImageView[scale.length];
		this.scale();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return scale.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		for (int i = 0; i < scale.length; i++) {
			imageViews[i]=scaleMap.get(scale[i]);
		}
		return imageViews[position];
	}
	
	private void scale(){
		for (int i = 0; i < scaleImageViewArray.length; i++) {
			ImageView view=new ImageView(context);
			view.setImageResource(scaleImageViewArray[i]);
			if(select==true){
				view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
			}else {
				view.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));
			}
			view.setAdjustViewBounds(false);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setPadding(8, 8, 8, 8);
			scaleMap.put(HearingConstants.Scale.ARRAY[i], view);
		}
	}
}
