package com.flextronics.cn.ui;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ���ڰ���һ��ImageViewBtn�������ڵ�ѡ��ť��
 * @author ZhangGuoYin
 *
 */
public class ImageViewBtnGroup extends LinearLayout {
	private final static String TAG = "ImageViewBtnGroup";
	
	/**
	 * ��ImageViewBtnGroup�а�����ImageViewBtnԪ���б�
	 */
	private List<ImageViewBtn> mImageViewBtnsList;
	private ImageViewBtn[] mImageViewBtns;
	/**
	 * ��ǰѡ�е�λ��
	 */
	private int mChooseIndex = -1;
	/**
	 * ImageViewBtn����������ʾ������text view
	 */
	private List<TextView> mBtnBackTextViewList;
	private Animation mBtnBackShowAnimation;
	private FrameLayout.LayoutParams mFrameLayoutParams;
	private LinearLayout.LayoutParams mLinearLayoutParams;
	/**
	 * �����ťʱ�Ƿ���ض���
	 */
	private boolean mAnimationOnClick;
	/**
	 * �Ƿ�ť��ʾ����
	 */
	private boolean mIsShowBtnBackgd;
	/**
	 * ��ť����ͼ
	 */
	private Drawable mBtnBackgd;
	private boolean mIsFirstTimeOnLayout = true;
	private int mSelectedChildIndex;
	private OnSelectListener onSelectListener;
	
	/**
	 * ѡ��ĳ��λ���ϵ�ImageViewBtn�Ļص�����
	 * @author ZhangGuoYin
	 *
	 */
	public static abstract interface OnSelectListener {
		public abstract void onSelect(int position);
	}
	
	public ImageViewBtnGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageViewBtnGroup);
		mAnimationOnClick = typedArray.getBoolean(R.styleable.ImageViewBtnGroup_animation_on_click, true);
		mIsShowBtnBackgd = typedArray.getBoolean(R.styleable.ImageViewBtnGroup_show_botton_background, true);
		mBtnBackgd = typedArray.getDrawable(R.styleable.ImageViewBtnGroup_botton_background);
		mSelectedChildIndex = typedArray.getInt(R.styleable.ImageViewBtnGroup_selected_child_index, -1);
		if(mBtnBackgd == null){
			mBtnBackgd = new BitmapDrawable(BitmapFactory
					.decodeResource(getResources(), R.drawable.ui_image_view_btn_backgd));
		}
		
		mImageViewBtnsList = new ArrayList<ImageViewBtn>();
		mBtnBackTextViewList = new ArrayList<TextView>();
		mBtnBackShowAnimation = AnimationUtils.loadAnimation(
				getContext(), R.anim.ui_image_view_btn_group_alpha);	
		mFrameLayoutParams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER);
		onSelectListener = new OnSelectListener() {
			
			@Override
			public void onSelect(int position) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	/**
	 * ȡ�����е�ImageViewBtn
	 * @return
	 */
	public ImageViewBtn[] getImageViewBtns() {
		if(mImageViewBtnsList == null || mImageViewBtnsList.size()<0 ||
				mImageViewBtnsList.get(0)==null){
			return null;
		}
		mImageViewBtns = new ImageViewBtn[mImageViewBtnsList.size()];
		mImageViewBtnsList.toArray(mImageViewBtns);
		return mImageViewBtns;
	}

	
	public boolean getAnimationOnClick() {
		return mAnimationOnClick;
	}

	public void setAnimationOnClick(boolean animationOnClick) {
		this.mAnimationOnClick = animationOnClick;
	}

	public boolean isShowButtonBackground() {
		return mIsShowBtnBackgd;
	}

	public void setShowButtonBackground(boolean showBtnBackgd) {
		this.mIsShowBtnBackgd = showBtnBackgd;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		this.onSelectListener = onSelectListener;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onFinishInflate()");
		
		super.onFinishInflate();
		
		for (int i = 0; i < getChildCount(); i++) {
			//�����Ԫ����ImageViewBtn, ���½�һ��FrameLayout,�������һ��text view,
			//Ȼ���ٷ���ImageViewBtn			
			if(getChildAt(i) instanceof ImageViewBtn){
				FrameLayout frameLayout = new FrameLayout(getContext());
				frameLayout.setPadding(5, 5, 5, 5);
				
				TextView textView = new TextView(getContext());
				textView.setBackgroundDrawable(mBtnBackgd);
				textView.setVisibility(View.INVISIBLE);
				frameLayout.addView(textView, mFrameLayoutParams);
				
				ImageViewBtn imageViewBtn = (ImageViewBtn)getChildAt(i);
				mLinearLayoutParams = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 
						((LinearLayout.LayoutParams)imageViewBtn.getLayoutParams()).weight);
				mLinearLayoutParams.gravity = Gravity.CENTER;
				//��ԭ�ȸ�λ�������ImageViewBtn�Ƴ�
				removeViewAt(i);
				frameLayout.addView(imageViewBtn, mFrameLayoutParams);
				frameLayout.setLayoutParams(mLinearLayoutParams);
				//Ȼ��frameLayout��ӵ���λ����
				addView(frameLayout, i);
								
				mImageViewBtnsList.add(imageViewBtn);
				mBtnBackTextViewList.add(textView);
			}
		}
		
		if(mImageViewBtnsList != null && mImageViewBtnsList.size()>0 && 
				mImageViewBtnsList.get(0)!=null){
			for(int i=0; i<mImageViewBtnsList.size(); i++){
				final ImageViewBtn btn = mImageViewBtnsList.get(i);
				btn.setOnClickListener(new ImageViewBtn.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for (int j = 0; j < mImageViewBtnsList.size(); j++) {

							if(btn != mImageViewBtnsList.get(j)){
								mImageViewBtnsList.get(j).reset();
								
								if(mBtnBackTextViewList.get(j).getVisibility() == View.VISIBLE){
									mBtnBackTextViewList.get(j).setVisibility(View.INVISIBLE);									
								}
								if(mBtnBackTextViewList.get(j).getAnimation() != null){
									mBtnBackTextViewList.get(j).getAnimation().cancel();
									mBtnBackTextViewList.get(j).setAnimation(null);
								}
							}else {
								mChooseIndex = j;
								if(mIsShowBtnBackgd){
									mBtnBackTextViewList.get(j).setVisibility(View.VISIBLE);									
								}
								if(mAnimationOnClick){
									mBtnBackTextViewList.get(j).startAnimation(mBtnBackShowAnimation);									
								}
							}
						}
						onSelectListener.onSelect(mChooseIndex);
					}				
				});
			}
		}
		getImageViewBtns();
		//����Ĭ��ѡ�е������ѡ��һ����Ԫ��
		select(mSelectedChildIndex);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onLayout()");
		
		super.onLayout(changed, l, t, r, b);
		if(mIsFirstTimeOnLayout){
			if(mImageViewBtns != null){
				for (int i = 0; i < mImageViewBtns.length; i++) {
					Bitmap bitmap = ((BitmapDrawable)mImageViewBtns[i].getImageView().getDrawable()).getBitmap();
					mBtnBackTextViewList.get(i).setWidth(bitmap.getWidth()+16);
					mBtnBackTextViewList.get(i).setHeight(bitmap.getHeight()+16);
				}
			}			
		}
		mIsFirstTimeOnLayout = false;
	}

	/**
	 * ȡ��ѡ���ֵ
	 * @return
	 */
	public String getValue(){
		if(getImageViewBtns() == null){
			return null;
		}
		if(mChooseIndex < 0){
			return null;
		}
		return mImageViewBtnsList.get(mChooseIndex).getValue();
	}
	
	/**
	 * ѡ��ָ��λ��
	 * @param position
	 */
	public void select(int position){
		if(position < 0){
			return;
		}
		if(getImageViewBtns() == null){
			return;
		}
		if(position > getImageViewBtns().length-1){
			return;
		}
		for (int i = 0; i < mImageViewBtns.length; i++) {
			if(i != position){
				mImageViewBtns[i].reset();
				if(mBtnBackTextViewList.get(i).getVisibility() == View.VISIBLE){
					mBtnBackTextViewList.get(i).setVisibility(View.INVISIBLE);									
				}
				if(mBtnBackTextViewList.get(i).getAnimation() != null){
					mBtnBackTextViewList.get(i).getAnimation().cancel();
					mBtnBackTextViewList.get(i).setAnimation(null);
				}
			}else{
				if(mIsShowBtnBackgd){
					mBtnBackTextViewList.get(i).setVisibility(View.VISIBLE);									
				}
				if(mAnimationOnClick){
					mBtnBackTextViewList.get(i).startAnimation(mBtnBackShowAnimation);									
				}
			}
		}
		getImageViewBtns()[position].select();
		mChooseIndex = position;
		onSelectListener.onSelect(mChooseIndex);
	}
	
	/**
	 * ѡ��ָ��ֵ
	 * @param value
	 */
	public void select(String value){
		if(value == null || value.trim().length()<1){
			return;
		}
		if(getImageViewBtns() == null){
			return;
		}
		int position = -1;
		for (int i = 0; i < mImageViewBtns.length; i++) {
			ImageViewBtn imageViewBtn = mImageViewBtns[i];
			if(imageViewBtn.getValue() != null){
				if(imageViewBtn.getValue().trim().equals(value.trim())){
					position = i;
					break;
				}
			}
		}
		select(position);
	}
}
