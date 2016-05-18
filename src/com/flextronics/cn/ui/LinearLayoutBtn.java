package com.flextronics.cn.ui;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 带有图片+文字的按钮，含值
 * @author ZhangGuoYin
 *
 */
public class LinearLayoutBtn extends LinearLayout {

	/**
	 * 正常情况下的图片
	 */
	private Drawable mNormalDrawable;
	/**
	 * 按下时的背景图片
	 */
	private Drawable mBackgdDrawable;
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
	 * 图片按钮旁边的字体正常时的颜色
	 */
	private ColorStateList mBtnTextColorNormal;
	/**
	 * 图片按钮旁边的字体选中之后的颜色
	 */
	private ColorStateList mBtnTextColorPressed;	
	/**
	 * 按钮上的图片
	 */
	private ImageView mImageView;
	/**
	 * 按钮上的文字
	 */
	private TextView mTextView;
	private boolean mIsfirstTimeOnLayout = true;
	
	public LinearLayoutBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutBtn);
		
		mNormalDrawable = a.getDrawable(R.styleable.LinearLayoutBtn_image_normal_src);
		mBackgdDrawable = a.getDrawable(R.styleable.LinearLayoutBtn_image_pressing_src);
		mPressedDrawable = a.getDrawable(R.styleable.LinearLayoutBtn_image_pressed_src);
		mValue = a.getString(R.styleable.LinearLayoutBtn_btn_value);
		mText = a.getString(R.styleable.LinearLayoutBtn_btn_text);
		mBtnTextColorNormal = a.getColorStateList(R.styleable.LinearLayoutBtn_btn_text_color_normal);
		mBtnTextColorPressed = a.getColorStateList(R.styleable.LinearLayoutBtn_btn_text_color_pressed);
		mTextSize = a.getInt(R.styleable.LinearLayoutBtn_btn_text_normal_size, 14);
		RuntimeException e = null;
		
		if(mNormalDrawable == null){
			e = new IllegalArgumentException(a.getPositionDescription() + 
					": The image_normal_src attribute is required and must refer to a valid image.");
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
		
		removeAllViews();
		mImageView = new ImageView(getContext());
		mImageView.setImageDrawable(mNormalDrawable);
		mTextView = new TextView(getContext());
		if(mText == null){
			mText = "";
		}
		mTextView.setText(mText);
		if(mBtnTextColorNormal == null){
			mBtnTextColorNormal = ColorStateList.valueOf(Color.BLACK);
		}
		mTextView.setTextColor(mBtnTextColorNormal);
		mTextView.setTextSize(mTextSize);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER;
		addView(mImageView, layoutParams);
		addView(mTextView, layoutParams);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(mIsfirstTimeOnLayout){
			if(mBackgdDrawable != null){
				setBackgroundDrawable(mBackgdDrawable);
			}
			invalidate();
			setBackgroundDrawable(null);
			setLinearLayoutOnTouchListener();			
		}
		mIsfirstTimeOnLayout = false;
	}
	public String getValue() {
		return mValue;
	}
	public void setValue(String value) {
		this.mValue = value;
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
		this.mBackgdDrawable = pressingDrawable;
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

	public ImageView getImageView() {
		return mImageView;
	}

	public TextView getTextView() {
		return mTextView;
	}

	public void reset(){
		setBackgroundDrawable(null);
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
	
	private void setLinearLayoutOnTouchListener(){
		setOnTouchListener(new LinearLayout.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					if(mBackgdDrawable != null){
						setBackgroundDrawable(mBackgdDrawable);
					}else {
						setBackgroundDrawable(null);						
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