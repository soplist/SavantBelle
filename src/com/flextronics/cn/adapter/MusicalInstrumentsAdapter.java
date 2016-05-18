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

public class MusicalInstrumentsAdapter extends BaseAdapter {
	//声明一个Context对象
	private Context context;
	//声明一个ImageView对象
	private ImageView view;
	//声明一个ImageView数组
	private View[] views;
	//声明一个boolean变量
	private boolean select;
	//声明一个int数组
	private int[] musicalInstruments;
	//声明一个int数组
	private int[] musicalInstrumentsImage=
	{
	R.drawable.hs_accordian_,R.drawable.hs_acoustic_guitar_,R.drawable.hs_basson_,R.drawable.hs_cello_,R.drawable.hs_church_organ_,
	R.drawable.hs_clarinet_,R.drawable.hs_electric_bass_,R.drawable.hs_electric_guitar_,R.drawable.hs_electric_piano_,R.drawable.hs_flute_,
	R.drawable.hs_french_horn_,R.drawable.hs_glock_,R.drawable.hs_harp_,R.drawable.hs_harpsichord_,R.drawable.hs_oboe_,
	R.drawable.hs_piano_,R.drawable.hs_piccolo_,R.drawable.hs_sax_,R.drawable.hs_trombone_,R.drawable.hs_trumpet_,
	R.drawable.hs_tuba_,R.drawable.hs_tubular_bells_,R.drawable.hs_viola_,R.drawable.hs_violin_,R.drawable.hs_xylophone_,R.drawable.hs_banjo_,R.drawable.hs_di_zi_,R.drawable.hs_dulcimer_,R.drawable.hs_er_hu_,R.drawable.hs_gu_zheng_,
	R.drawable.hs_irish_whistle_,R.drawable.hs_liu_qin_,R.drawable.hs_ma_tou_qin_,R.drawable.hs_mandolin_,R.drawable.hs_pi_pa_,
	R.drawable.hs_ruan_,R.drawable.hs_shakuhachi_,R.drawable.hs_shamisen_,R.drawable.hs_sheng_,R.drawable.hs_sitar_,
	R.drawable.hs_suo_na_,R.drawable.hs_uilleann_bagpipe_,R.drawable.hs_xiao_,R.drawable.hs_zhong_,R.drawable.hs_ban_,R.drawable.hs_bongo_,R.drawable.hs_cabasa_,R.drawable.hs_conga_,R.drawable.hs_cow_bell_,
	R.drawable.hs_cymbals_,R.drawable.hs_da_luo_,R.drawable.hs_guo_,R.drawable.hs_kick_,R.drawable.hs_snare_,
	R.drawable.hs_taiko_drums_,R.drawable.hs_tambourine_,R.drawable.hs_timbales_,R.drawable.hs_timpani_,R.drawable.hs_triangle_,
	R.drawable.hs_woodblock_,R.drawable.hs_xiao_luo_
	};
	//声明一个int数组
	private int[] musicalInstrumentsDetailImage=
	{
	R.drawable.hs_accordian,R.drawable.hs_acoustic_guitar,R.drawable.hs_basson,R.drawable.hs_cello,R.drawable.hs_church_organ,
	R.drawable.hs_clarinet,R.drawable.hs_electric_bass,R.drawable.hs_electric_guitar,R.drawable.hs_electric_piano,R.drawable.hs_flute,
	R.drawable.hs_french_horn,R.drawable.hs_glock,R.drawable.hs_harp,R.drawable.hs_harpsichord,R.drawable.hs_oboe,
	R.drawable.hs_piano,R.drawable.hs_piccolo,R.drawable.hs_sax,R.drawable.hs_trombone,R.drawable.hs_trumpet,
	R.drawable.hs_tuba,R.drawable.hs_tubular_bells,R.drawable.hs_viola,R.drawable.hs_violin,R.drawable.hs_xylophone,R.drawable.hs_banjo,R.drawable.hs_di_zi,R.drawable.hs_dulcimer,R.drawable.hs_er_hu,R.drawable.hs_gu_zheng,
	R.drawable.hs_irish_whistle,R.drawable.hs_liu_qin,R.drawable.hs_ma_tou_qin,R.drawable.hs_mandolin,R.drawable.hs_pi_pa,
	R.drawable.hs_ruan,R.drawable.hs_shakuhachi,R.drawable.hs_shamisen,R.drawable.hs_sheng,R.drawable.hs_sitar,
	R.drawable.hs_suo_na,R.drawable.hs_uilleann_bagpipe,R.drawable.hs_xiao,R.drawable.hs_zhong,R.drawable.hs_ban,R.drawable.hs_bongo,R.drawable.hs_cabasa,R.drawable.hs_conga,R.drawable.hs_cow_bell,
	R.drawable.hs_cymbals,R.drawable.hs_da_luo,R.drawable.hs_guo,R.drawable.hs_kick,R.drawable.hs_snare,
	R.drawable.hs_taiko_drums,R.drawable.hs_tambourine,R.drawable.hs_timbales,R.drawable.hs_timpani,R.drawable.hs_triangle,
	R.drawable.hs_woodblock,R.drawable.hs_xiao_luo
	};
	

	//声明一个Map集合
	private Map<Integer, View> musicalInstrumentsMap=new HashMap<Integer, View>();
	
	public MusicalInstrumentsAdapter(Context context,boolean select,int[] musicalInstruments){
		this.context=context;
		this.select=select;
		this.musicalInstruments=musicalInstruments;
		this.views=new View[musicalInstruments.length];
		musicalInstruments();
	}
	public int[] getMusicalInstrumentsDetailImage() {
		return musicalInstrumentsDetailImage;
	}

	public void clear(){
		musicalInstrumentsMap.clear();
		musicalInstrumentsMap=null;
		views=null;
		view=null;
		System.gc();
	}
	
	public int getCount() {
		return musicalInstruments.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		for (int i = 0; i < musicalInstruments.length; i++) {
			views[i]=musicalInstrumentsMap.get(musicalInstruments[i]);
		}
		return views[position];
	}
	
	private void musicalInstruments(){
		for (int i = 0; i < musicalInstrumentsImage.length; i++) {
			view=new ImageView(context);
			view.setImageResource(musicalInstrumentsImage[i]);
			if(select==true){
				view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
			}else {
				view.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));
			}
			view.setAdjustViewBounds(false);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setPadding(8, 8, 8, 8);
			musicalInstrumentsMap.put(HearingConstants.MusicalInstruments.ARRAY[i], view);
		}
	}
}
