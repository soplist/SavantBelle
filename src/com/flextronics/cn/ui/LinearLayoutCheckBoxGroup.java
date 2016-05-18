package com.flextronics.cn.ui;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

/**支持单选，多选两种方式的LinearLayoutCheckBox组
 * 
 * @author ZhangGuoYin
 *
 */
public class LinearLayoutCheckBoxGroup extends RelativeLayout {
	
	private final static String TAG = "LinearLayoutBtnGroup";
	
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
	
	public LinearLayoutCheckBoxGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutCheckBoxGroup);
		mSingleChoose = typedArray.getBoolean(R.styleable.LinearLayoutCheckBoxGroup_single_choose, false);
		mLinearLayoutCheckBoxList = new ArrayList<LinearLayoutCheckBox>();
		onSelectListener = new OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				
			}
		};
		setLongClickable(false);
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

	
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onFinishInflate()");
		
		super.onFinishInflate();
		
		for (int i = 0; i < getChildCount(); i++) {
			//如果子元素是LinearLayoutCheckBox，则添加到mLinearLayoutCheckBoxList中
			if(getChildAt(i) instanceof LinearLayoutCheckBox){
				mLinearLayoutCheckBoxList.add((LinearLayoutCheckBox)getChildAt(i));
			}
		}
		
		if(mLinearLayoutCheckBoxList != null && mLinearLayoutCheckBoxList.size()>0 && 
				mLinearLayoutCheckBoxList.get(0)!=null){
			for(int i=0; i<mLinearLayoutCheckBoxList.size(); i++){
				
				final LinearLayoutCheckBox checkBox = mLinearLayoutCheckBoxList.get(i);
				checkBox.setLongClickable(false);				
				checkBox.setOnClickListener(new LinearLayoutBtn.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(mSingleChoose){
							for (int j = 0; j < mLinearLayoutCheckBoxList.size(); j++) {
								if(checkBox != mLinearLayoutCheckBoxList.get(j)){
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
}
