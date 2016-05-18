package com.flextronics.cn.ui;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 图片按钮，带有值
 * @author ZhangGuoYin
 *
 */
public class ImageViewBtn extends RelativeLayout {

	/**
	 * 正常情况下的图片
	 */
	private Drawable mNormalDrawable;
	/**
	 * 按下时的图片
	 */
	private Drawable mPressingDrawable;
	/**
	 * 选中之后的图片
	 */
	private Drawable mPressedDrawable;
	/**
	 * 按钮代表的值
	 */
	private String mValue;
	/**
	 * 按钮旁边的文字
	 */
	private String mText;
	/**
	 * 按钮旁边的文字的大小
	 */
	private int mTextSize;
	/**
	 * 图片上的字体正常时的颜色
	 */
	private ColorStateList mBtnTextColorNormal;
	/**
	 * 图片上的字体选中之后的颜色
	 */
	private ColorStateList mBtnTextColorPressed;	
	/**
	 * 图片上的字体选中时的颜色
	 */
	private ColorStateList mBtnTextColorPressing;
	/**
	 * 按钮上的图片
	 */
	private ImageView mImageView;
	/**
	 * 按钮上的文字
	 */
	private TextView mTextView;
	
	public ImageViewBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageViewBtn);
		
		mNormalDrawable = a.getDrawable(R.styleable.ImageViewBtn_normal_src);
		mPressingDrawable = a.getDrawable(R.styleable.ImageViewBtn_pressing_src);
		mPressedDrawable = a.getDrawable(R.styleable.ImageViewBtn_pressed_src);
		mValue = a.getString(R.styleable.ImageViewBtn_value);
		mText = a.getString(R.styleable.ImageViewBtn_img_text);
		mBtnTextColorNormal = a.getColorStateList(R.styleable.ImageViewBtn_img_text_color_normal);
		mBtnTextColorPressed = a.getColorStateList(R.styleable.ImageViewBtn_img_text_color_pressed);
		mBtnTextColorPressing = a.getColorStateList(R.styleable.ImageViewBtn_img_text_color_pressing);
		mTextSize = a.getInt(R.styleable.ImageViewBtn_img_text_normal_size, 14);
		
		RuntimeException e = null;
		
		if(mNormalDrawable == null){
			e = new IllegalArgumentException(a.getPositionDescription() + 
					": The normalSrc attribute is required and must refer to a valid image.");
		}
		
		a.recycle();
		
		if (e != null) {
			throw e;
		}	
		
		setClickable(true);
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		
		inflate(getContext(), R.layout.ui_imageview_btn, this);
		mImageView = (ImageView)findViewById(R.id.ImageViewUIBtn);
		mImageView.setImageDrawable(mNormalDrawable);
		mTextView = (TextView)findViewById(R.id.TextViewUIBtn);
		if(mText == null){
			mText = "";
		}
		mTextView.setText(mText);
		if(mBtnTextColorNormal == null){
			mBtnTextColorNormal = ColorStateList.valueOf(Color.BLACK);
		}
		mTextView.setTextColor(mBtnTextColorNormal);
		mTextView.setTextSize(mTextSize);		

		setImageViewOnTouchListener();
	}

	public String getText() {
		if(mTextView.getText() != null){
			return mTextView.getText().toString().trim();			
		}
		return null;
	}
	public void setText(String text) {
		this.mText = text;
		mTextView.setText(mText);
	}

	public int getTextSize() {
		return mTextSize;
	}
	public void setTextSize(int textSize) {
		this.mTextSize = textSize;
		mTextView.setTextSize(mTextSize);
	}

	public void setNormalDrawable(Drawable normalDrawable) {
		this.mNormalDrawable = normalDrawable;
	}

	public void setPressingDrawable(Drawable pressingDrawable) {
		this.mPressingDrawable = pressingDrawable;
	}

	public void setPressedDrawable(Drawable pressedDrawable) {
		this.mPressedDrawable = pressedDrawable;
	}	
	
	public void setBtnTextColorNormal(ColorStateList btnTextColorNormal) {
		this.mBtnTextColorNormal = btnTextColorNormal;
	}

	public void setBtnTextColorPressed(ColorStateList btnTextColorPressed) {
		this.mBtnTextColorPressed = btnTextColorPressed;
	}

	public void setBtnTextColorPressing(ColorStateList btnTextColorPressing) {
		this.mBtnTextColorPressing = btnTextColorPressing;
	}

	public ImageView getImageView() {
		return mImageView;
	}

	public TextView getTextView() {
		return mTextView;
	}
	
	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}	
	
	public void reset(){
		mImageView.setImageDrawable(mNormalDrawable);
		mTextView.setTextColor(mBtnTextColorNormal);	
	}
	
	public void select(){
		if(mPressedDrawable != null){
			mImageView.setImageDrawable(mPressedDrawable);			
		}else {
			mImageView.setImageDrawable(mNormalDrawable);
		}
		if(mBtnTextColorPressed != null){
			mTextView.setTextColor(mBtnTextColorPressed);
		}else {
			mTextView.setTextColor(mBtnTextColorNormal);
		}
	}
	
	private void setImageViewOnTouchListener(){
		setOnTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					if(mPressingDrawable != null){
						mImageView.setImageDrawable(mPressingDrawable);
					}else {
						mImageView.setImageDrawable(mNormalDrawable);
					}
					if(mBtnTextColorPressed != null){
						mTextView.setTextColor(mBtnTextColorPressing);
					}else {
						mTextView.setTextColor(mBtnTextColorNormal);
					}	
				}else {					
					setBackgroundDrawable(null);
					if(mPressedDrawable != null){
						mImageView.setImageDrawable(mPressedDrawable);					
					}else {
						mImageView.setImageDrawable(mNormalDrawable);
					}
					if(mBtnTextColorPressed != null){
						mTextView.setTextColor(mBtnTextColorPressed);
					}else {
						mTextView.setTextColor(mBtnTextColorNormal);
					}					
				}
				return false;
			}			
		});		
	}
}
