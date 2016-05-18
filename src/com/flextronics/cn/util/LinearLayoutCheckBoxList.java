package com.flextronics.cn.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;

import com.flextronics.cn.ui.LinearLayoutBtn;
import com.flextronics.cn.ui.LinearLayoutCheckBox;

public class LinearLayoutCheckBoxList{
	
	/**
	 * 包含的所有LinearLayoutCheckBox
	 */	
	private List<LinearLayoutCheckBox> mLinearLayoutCheckBoxList;
	private LinearLayoutCheckBox[] mLinearLayoutCheckBoxs;
	/**
	 * 当前选中的
	 */
	private int mChooseIndex = -1;
	private OnSelectListener onSelectListener;
	/**
	 * 单选标志
	 */
	private boolean mSingleChoose;
	
	public static abstract interface OnSelectListener {
		public abstract void onSelect(int position);
	}
	
	public LinearLayoutCheckBoxList(Context context) {
		mSingleChoose = false;
		mLinearLayoutCheckBoxList = new ArrayList<LinearLayoutCheckBox>();
		onSelectListener = new OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	/**
	 * 获得所有的LinearLayoutCheckBox
	 * @return
	 */
	public LinearLayoutCheckBox[] getLinearLayoutCheckBoxs() {
		if(mLinearLayoutCheckBoxList == null || mLinearLayoutCheckBoxList.size()<0 ||
				mLinearLayoutCheckBoxList.get(0)==null){
			return null;
		}
		mLinearLayoutCheckBoxs = new LinearLayoutCheckBox[mLinearLayoutCheckBoxList.size()];
		mLinearLayoutCheckBoxList.toArray(mLinearLayoutCheckBoxs);
		return mLinearLayoutCheckBoxs;
	}

	public void addView(LinearLayoutCheckBox checkBox){
		mLinearLayoutCheckBoxList.add(checkBox);
		
		if(mLinearLayoutCheckBoxList != null && mLinearLayoutCheckBoxList.size()>0 && 
				mLinearLayoutCheckBoxList.get(0)!=null){
			for(int i=0; i<mLinearLayoutCheckBoxList.size(); i++){
				
				final LinearLayoutCheckBox _checkBox = mLinearLayoutCheckBoxList.get(i);
				_checkBox.setLongClickable(false);				
				_checkBox.setOnClickListener(new LinearLayoutBtn.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(mSingleChoose){
							for (int j = 0; j < mLinearLayoutCheckBoxList.size(); j++) {
								if(_checkBox != mLinearLayoutCheckBoxList.get(j)){
									mLinearLayoutCheckBoxList.get(j).reset();
								}else {									
									mChooseIndex = j;
								}
							}
						}
						onSelectListener.onSelect(mChooseIndex);
					}				
				});
			}
		}
		getLinearLayoutCheckBoxs();
	}
			
	public void setSingleChoose(boolean singleChoose) {
		this.mSingleChoose = singleChoose;
	}
	
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		this.onSelectListener = onSelectListener;
	}
	
	public List<LinearLayoutCheckBox> getSelectItems(){
		List<LinearLayoutCheckBox> list = new ArrayList<LinearLayoutCheckBox>();
		for(LinearLayoutCheckBox checkBox : getLinearLayoutCheckBoxs()){
			if(checkBox.isChecked()){
				list.add(checkBox);
			}		
		}
		return list;
	}
	
	public void checkLinearLayoutCheckBox(int[] index, boolean enableOthers){
		for(LinearLayoutCheckBox checkBox : mLinearLayoutCheckBoxList){
			checkBox.reset();
			checkBox.setEnabled(enableOthers);
		}
		for(int i : index){
			mLinearLayoutCheckBoxList.get(i).check();
		}
	}
}