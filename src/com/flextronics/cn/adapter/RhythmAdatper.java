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

public class RhythmAdatper extends BaseAdapter {
	private Context context;
	
	private boolean select;

	private ImageView[] imageViews;

	private int[] rhythm;

	private int [] rhythmImageViewArray=
	{
	R.drawable.hs_rhythm_01_button,R.drawable.hs_rhythm_02_button,R.drawable.hs_rhythm_03_button,R.drawable.hs_rhythm_04_button,
	R.drawable.hs_rhythm_05_button,R.drawable.hs_rhythm_06_button,R.drawable.hs_rhythm_07_button,R.drawable.hs_rhythm_08_button,
	R.drawable.hs_rhythm_09_button,R.drawable.hs_rhythm_10_button,R.drawable.hs_rhythm_11_button,R.drawable.hs_rhythm_12_button,
	R.drawable.hs_rhythm_13_button,R.drawable.hs_rhythm_14_button,R.drawable.hs_rhythm_15_button,R.drawable.hs_rhythm_16_button,
	R.drawable.hs_rhythm_17_button,R.drawable.hs_rhythm_18_button,R.drawable.hs_rhythm_19_button,R.drawable.hs_rhythm_20_button,
	R.drawable.hs_rhythm_21_button,R.drawable.hs_rhythm_22_button,R.drawable.hs_rhythm_23_button,R.drawable.hs_rhythm_24
	};
	//声明一个Map集合
	private Map<Integer, ImageView> rhythmMap=new HashMap<Integer, ImageView>();
	
	public RhythmAdatper(Context context,boolean select,int[] rhythm){
		this.context=context;
		this.select=select;
		this.rhythm=rhythm;
		this.imageViews=new ImageView[rhythm.length];
		this.rhythm();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return rhythm.length;
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
		for (int i = 0; i < rhythm.length; i++) {
			imageViews[i]=rhythmMap.get(rhythm[i]);
		}
		return imageViews[position];
	}

	private void rhythm(){
		for (int i = 0; i < rhythmImageViewArray.length; i++) {
			ImageView view=new ImageView(context);
			view.setImageResource(rhythmImageViewArray[i]);
			if(select==true){
				view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
			}else {
				view.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));
			}
			view.setAdjustViewBounds(false);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setPadding(8, 8, 8, 8);
			rhythmMap.put(HearingConstants.Rhythm.ARRAY[i],view);
		}
	}
}
