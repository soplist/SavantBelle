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
 * ����ͼƬ+���ֵĸ�ѡ��ť����ֵ
 * @author ZhangGuoYin
 *
 */
public class LinearLayoutCheckBox extends LinearLayout {

	/**
	 * ��������µ�ͼƬ
	 */
	private Drawable mNormalDrawable;
	/**
	 * ����ʱ�ı���ͼƬ
	 */
	private Drawable mBackgdDrawable;
	/**
	 * ѡ��֮���ͼƬ
	 */
	private Drawable mPressedDrawable;
	/**
	 * ��ť�����ֵ
	 */
	private String mValue;
	/**
	 * ��ť�Աߵ�����
	 */
	private String mText;
	/**
	 * ��ť�Աߵ����ֵĴ�С
	 */
	private int mTextSize;
	/**
	 * ͼƬ��ť�Աߵ���������ʱ����ɫ
	 */
	private ColorStateList mBtnTextColorNormal;
	/**
	 * ͼƬ��ť�Աߵ�����ѡ��֮�����ɫ
	 */
	private ColorStateList mBtnTextColorPressed;	
	/**
	 * ��ť�ϵ�ImageView
	 */
	private ImageView mImageView;
	/**
	 * ��ť�ϵ�TextView
	 */
	private TextView mTextView;
	private boolean mIsfirstTimeOnLayout = true;
	/**
	 * ѡ�б�־
	 */
	private boolean mIsChecked;
	private boolean mIsHoldPressingBackgd;
	private long lastActionTime;
	private OnToggleListener onToggleListener;
	
	public static abstract interface OnToggleListener {
		public abstract void OnToggle(boolean isChecked);
	}
	
	public LinearLayoutCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutCheckBox);
		
		mNormalDrawable = a.getDrawable(R.styleable.LinearLayoutCheckBox_check_box_image_normal_src);
		mBackgdDrawable = a.getDrawable(R.styleable.LinearLayoutCheckBox_check_box_image_pressing_src);
		mPressedDrawable = a.getDrawable(R.styleable.LinearLayoutCheckBox_check_box_image_pressed_src);
		mValue = a.getString(R.styleable.LinearLayoutCheckBox_check_box_btn_value);
		mText = a.getString(R.styleable.LinearLayoutCheckBox_check_box_btn_text);
		mBtnTextColorNormal = a.getColorStateList(R.styleable.LinearLayoutCheckBox_check_box_btn_text_color_normal);
		mBtnTextColorPressed = a.getColorStateList(R.styleable.LinearLayoutCheckBox_check_box_btn_text_color_pressed);
		mTextSize = a.getInt(R.styleable.LinearLayoutCheckBox_check_box_btn_text_normal_size, 14);
		mIsChecked = a.getBoolean(R.styleable.LinearLayoutCheckBox_check_box_checked, false);
		mIsHoldPressingBackgd = a.getBoolean(R.styleable.LinearLayoutCheckBox_check_box_hold_pressing_backgd, false);
		
		a.recycle();
		setClickable(true);
		onToggleListener = new OnToggleListener() {
			
			@Override
			public void OnToggle(boolean open) {
				// TODO Auto-generated method stub
				
			}
		};
		setLinearLayoutOnTouchListener();
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
		
		if(mIsChecked){
			check();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(mIsfirstTimeOnLayout){
			if(mBackgdDrawable != null){
				setBackgroundDrawable(mBackgdDrawable);
			}
			//invalidate();
			setBackgroundDrawable(null);
		}
		mIsfirstTimeOnLayout = false;
	}
		
	public boolean isChecked() {
		return mIsChecked;
	}
	public void setChecked(boolean isChecked) {
		this.mIsChecked = isChecked;
		if(isChecked){
			check();			
		}
	}

	public boolean isHoldPressingBackgd() {
		return mIsHoldPressingBackgd;
	}

	public void setHoldPressingBackgd(boolean isHoldPressingBackgd) {
		this.mIsHoldPressingBackgd = isHoldPressingBackgd;
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

	public void setOnToggleListener(OnToggleListener onToggleListener) {
		this.onToggleListener = onToggleListener;
	}

	public void reset(){
		mImageView.setImageDrawable(mNormalDrawable);
		mTextView.setTextColor(mBtnTextColorNormal);
		if(mIsHoldPressingBackgd){
			setBackgroundDrawable(null);
		}
		mIsChecked = false;
	}
	
	public void check(){
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
		if(mIsHoldPressingBackgd){
			setBackgroundDrawable(mBackgdDrawable);
		}
		mIsChecked = true;
	}
	
	public void toggle(){

		//���ε����ʱ��������С��800����
		if(System.currentTimeMillis() - lastActionTime > 500){
			if(!mIsChecked){
				check();
			}else{
				reset();
			}
			lastActionTime = System.currentTimeMillis();
			onToggleListener.OnToggle(!mIsChecked);
		}
	}
	
	public void setLinearLayoutOnTouchListener(){
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
					toggle();
				}else {
					if (!mIsHoldPressingBackgd) {
						setBackgroundDrawable(null);						
					}
				}
				return false;
			}			
		});		
	}
}