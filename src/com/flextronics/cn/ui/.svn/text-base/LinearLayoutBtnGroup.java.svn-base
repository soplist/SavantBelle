package com.flextronics.cn.ui;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 用于包含一组LinearLayoutBtn，类似于单选按钮组
 * @author ZhangGuoYin
 *
 */
public class LinearLayoutBtnGroup extends LinearLayout {
	
	private final static String TAG = "LinearLayoutBtnGroup";
	
	/**
	 * 该LinearLayoutBtnGroup中包含的LinearLayoutBtn元素列表
	 */	
	private List<LinearLayoutBtn> mLinearLayoutBtnList;
	private LinearLayoutBtn[] mLinearLayoutBtns;
	/**
	 * 当前选中的位置
	 */
	private int mChooseIndex = -1;
	/**
	 * 选中的子元素的index
	 */
	private int mSelectedIndex;
	private OnSelectListener onSelectListener;
	
	/**
	 * 选中某个位置上的ImageViewBtn的回调函数
	 * @author ZhangGuoYin
	 *
	 */
	public static abstract interface OnSelectListener {
		public abstract void onSelect(int position);
	}
	
	public LinearLayoutBtnGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutBtnGroup);
		mSelectedIndex = typedArray.getInt(R.styleable.LinearLayoutBtnGroup_selected_index, -1);
		
		mLinearLayoutBtnList = new ArrayList<LinearLayoutBtn>();
		onSelectListener = new OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				
			}
		};
		setLongClickable(false);
	}

	/**
	 * 取出所有的LinearLayoutBtn
	 * @return
	 */
	public LinearLayoutBtn[] getLinearLayoutBtns() {
		if(mLinearLayoutBtnList == null || mLinearLayoutBtnList.size()<0 ||
				mLinearLayoutBtnList.get(0)==null){
			return null;
		}
		mLinearLayoutBtns = new LinearLayoutBtn[mLinearLayoutBtnList.size()];
		mLinearLayoutBtnList.toArray(mLinearLayoutBtns);
		return mLinearLayoutBtns;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onFinishInflate()");
		
		super.onFinishInflate();
		
		for (int i = 0; i < getChildCount(); i++) {
			//如果子元素是LinearLayoutBtn, 则将该元素添加到列表中
			if(getChildAt(i) instanceof LinearLayoutBtn){
				mLinearLayoutBtnList.add((LinearLayoutBtn)getChildAt(i));
			}
		}
		
		if(mLinearLayoutBtnList != null && mLinearLayoutBtnList.size()>0 && 
				mLinearLayoutBtnList.get(0)!=null){
			for(int i=0; i<mLinearLayoutBtnList.size(); i++){
				final LinearLayoutBtn btn = mLinearLayoutBtnList.get(i);
				btn.setLongClickable(false);
				btn.setOnClickListener(new LinearLayoutBtn.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for (int j = 0; j < mLinearLayoutBtnList.size(); j++) {
							if(btn != mLinearLayoutBtnList.get(j)){
								mLinearLayoutBtnList.get(j).reset();
							}else {
								mChooseIndex = j;
							}
						}
						onSelectListener.onSelect(mChooseIndex);
					}				
				});
			}
		}
		getLinearLayoutBtns();
		//根据默认选中的情况，选中一个子元素
		select(mSelectedIndex);
	}
	
	/**
	 * 取得选择的值
	 * @return
	 */
	public String getValue(){
		if(getLinearLayoutBtns() == null){
			return null;
		}
		if(mChooseIndex < 0){
			return null;
		}
		return mLinearLayoutBtnList.get(mChooseIndex).getValue();
	}
	
	/**
	 * 选中指定位置
	 * @param position
	 */
	public void select(int position){
		if(position < 0){
			return;
		}
		if(getLinearLayoutBtns() == null){
			return;
		}
		if(position > getLinearLayoutBtns().length-1){
			return;
		}
		for (int i = 0; i < mLinearLayoutBtns.length; i++) {
			if(i != position){
				mLinearLayoutBtns[i].reset();
			}else{
				mLinearLayoutBtns[i].select();
			}
		}
		mChooseIndex = position;
		onSelectListener.onSelect(mChooseIndex);
	}
	
	/**
	 * 选中指定值
	 * @param value
	 */
	public void select(String value){
		if(value == null || value.trim().length()<1){
			return;
		}
		if(getLinearLayoutBtns() == null){
			return;
		}
		int position = -1;
		for (int i = 0; i < mLinearLayoutBtns.length; i++) {
			LinearLayoutBtn btn = mLinearLayoutBtns[i];
			if(btn.getValue() != null){
				if(btn.getValue().trim().equals(value.trim())){
					position = i;
					break;
				}
			}
		}
		select(position);
	}
	
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		this.onSelectListener = onSelectListener;
	}
}
