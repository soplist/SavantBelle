package com.flextronics.cn.ui;

import java.util.ArrayList;
import java.util.List;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.ArrayOperations;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonGroup extends LinearLayout{
	
	private final static String TAG = "ButtonGroup";
	private Drawable mButtonBackground;
	private Drawable mButtonBackgroundClicked;
	private Drawable mButtonBackgroundUnclicked;
	private int mButtonNums;
	private String mButtonsText;
	private ColorStateList mButtonsTextColor;
	private float mButtonsTextSize;
	private String mButtonsValue;
	private int mButtonPaddingLeft;
	private int mButtonPaddingTop;
	private int mButtonPaddingBottom;
	private int mButtonPaddingRight;
	private int mButtonMarginLeft;
	private int mButtonMarginTop;
	private int mButtonMarginBottom;
	private int mButtonMarginRight;
	private boolean mButtonIsFocusCircular;
	private int mButtonFocusPosition;
	
	private List<Button> buttonList;

	private String mValue;
	private String[] values;
	
	private OnButtonClickListener onButtonClickListener;

	public OnButtonClickListener getOnButtonClickListener() {
		return onButtonClickListener;
	}
	public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
		this.onButtonClickListener = onButtonClickListener;
	}

	public List<Button> getButtonList() {
		return buttonList;
	}
	
	public String getButtonsText() {
		return mButtonsText;
	}
	
	public String getValue() {
		return mValue;
	}
	
	public void setValue(String value) {

		if (value == null) {
			mValue = null;
			return;
		}
		
		int index = ArrayOperations.indexInElement(values, value);
		if (index < 0) {
			return;
		}
		
		mValue = value;
		for (int i = 0; i < buttonList.size(); i++) {
			if (i == index) {
				buttonList.get(i).setBackgroundDrawable(mButtonBackgroundClicked);
				buttonList.get(i).setTextColor(Color.WHITE);
				mButtonFocusPosition = index;
			}else {
				buttonList.get(i).setBackgroundDrawable(mButtonBackgroundUnclicked);
				buttonList.get(i).setTextColor(mButtonsTextColor);
			}			
		}
	}

	public static abstract interface OnButtonClickListener {
		public abstract void onButtonClick(String buttonValue);
	}	
	
	public boolean isButtonIsFocusCircular() {
		return mButtonIsFocusCircular;
	}
	public void setButtonIsFocusCircular(boolean buttonIsFocusCircular) {
		mButtonIsFocusCircular = buttonIsFocusCircular;
	}
	public int getButtonFocusPosition() {
		return mButtonFocusPosition;
	}
	public void setButtonFocusPosition(int buttonFocusPosition) {
		if (buttonFocusPosition > -1) {

			if (buttonList != null) {
				if (buttonFocusPosition>buttonList.size()-1) {
					return;
				}
				for (int i = 0; i < buttonList.size(); i++) {
					if (i == buttonFocusPosition) {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundClicked);
						buttonList.get(i).setTextColor(Color.WHITE);
					}else {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundUnclicked);
						buttonList.get(i).setTextColor(mButtonsTextColor);
					}
				}
			}

			mButtonFocusPosition = buttonFocusPosition;
		}else {
			mButtonFocusPosition = -1;
		}
	}
	
	
	
	public ButtonGroup(Context context, AttributeSet set) {
		super(context, set);

		TypedArray typedArray = context.obtainStyledAttributes(set, R.styleable.ButtonGroup);
		this.mButtonBackground = typedArray.getDrawable(R.styleable.ButtonGroup_buttonBackground);
		this.mButtonBackgroundClicked = typedArray.getDrawable(R.styleable.ButtonGroup_buttonBackgroundClicked);
		this.mButtonBackgroundUnclicked = typedArray.getDrawable(R.styleable.ButtonGroup_buttonBackgroundUnclicked);
		this.mButtonNums = typedArray.getInt(R.styleable.ButtonGroup_buttonNums, 1);
		this.mButtonsText = typedArray.getString(R.styleable.ButtonGroup_buttonsText);
		this.mButtonsTextColor = typedArray.getColorStateList(R.styleable.ButtonGroup_buttonsTextColor);
		this.mButtonsValue = typedArray.getString(R.styleable.ButtonGroup_buttonsValue);
		this.mButtonPaddingLeft = typedArray.getInt(R.styleable.ButtonGroup_buttonPaddingLeft, 0);
		this.mButtonPaddingTop = typedArray.getInt(R.styleable.ButtonGroup_buttonPaddingTop, 0);
		this.mButtonPaddingBottom = typedArray.getInt(R.styleable.ButtonGroup_buttonPaddingBottom, 0);
		this.mButtonPaddingRight = typedArray.getInt(R.styleable.ButtonGroup_buttonPaddingRight, 0);
		this.mButtonMarginLeft = typedArray.getInt(R.styleable.ButtonGroup_buttonMarginLeft, 0);
		this.mButtonMarginTop = typedArray.getInt(R.styleable.ButtonGroup_buttonMarginTop, 0);
		this.mButtonMarginBottom = typedArray.getInt(R.styleable.ButtonGroup_buttonMarginBottom, 0);
		this.mButtonMarginRight = typedArray.getInt(R.styleable.ButtonGroup_buttonMarginRight, 0);
		this.mButtonsTextSize = typedArray.getFloat(R.styleable.ButtonGroup_buttonsTextSize, 14);
		this.mButtonFocusPosition = typedArray.getInt(R.styleable.ButtonGroup_buttonFocusPosition, -1);
		this.mButtonIsFocusCircular = typedArray.getBoolean(R.styleable.ButtonGroup_buttonIsFocusCircular, true);
		
		buttonList = new ArrayList<Button>();
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(mButtonMarginLeft, mButtonMarginTop, mButtonMarginRight, mButtonMarginBottom);
		
		this.setOrientation(LinearLayout.VERTICAL);
		String[] texts = mButtonsText.split(";");
		values = mButtonsValue.split(";");
		
		onButtonClickListener = new OnButtonClickListener(){

			public void onButtonClick(String buttonValue) {
				// TODO Auto-generated method stub				
			}
		};
		
		for (int i = 0; i < mButtonNums; i++) {
			final String value = i>values.length-1?null:values[i];
			final Button button = new Button(context);
			button.setPadding(mButtonPaddingLeft, mButtonPaddingTop, mButtonPaddingRight, mButtonPaddingBottom);
			button.setGravity(Gravity.CENTER_HORIZONTAL);			
			button.setWidth(mButtonBackgroundClicked.getIntrinsicWidth());
			button.setHeight(mButtonBackgroundClicked.getIntrinsicHeight());			
			button.setBackgroundDrawable(mButtonBackground);
			button.setText(i>texts.length-1?"Button":texts[i]);
			button.setTextColor(mButtonsTextColor);
			button.setTextSize(mButtonsTextSize);
			buttonList.add(button);
			
			if (mButtonFocusPosition>-1 && mButtonFocusPosition<mButtonNums && i==mButtonFocusPosition) {
				button.setBackgroundDrawable(mButtonBackgroundClicked);
				button.setTextColor(Color.WHITE);
			}else {
				mButtonFocusPosition = -1;
			}
			
			button.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View paramView) {
					
					button.setBackgroundDrawable(mButtonBackgroundClicked);
					button.setTextColor(Color.WHITE);
					mValue = value;
					for (Button _button : buttonList) {
						if (_button != button) {
							_button.setBackgroundDrawable(mButtonBackgroundUnclicked);
							_button.setTextColor(mButtonsTextColor);
						}else {
							mButtonFocusPosition = buttonList.indexOf(_button);
						}
					}
					
					onButtonClickListener.onButtonClick(value);
					Log.d(TAG, "mValue: " + mValue);
				}
			});			
			this.addView(button, layoutParams);
		}
	}
	
	public void focusNextButton(){
		if (buttonList != null) {

			mButtonFocusPosition ++; 
			if (mButtonFocusPosition < buttonList.size()) {
				for (int i = 0; i < buttonList.size(); i++) {
					if (i == mButtonFocusPosition) {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundClicked);
						buttonList.get(i).setTextColor(Color.WHITE);
					}else {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundUnclicked);
						buttonList.get(i).setTextColor(mButtonsTextColor);
					}			
				}
			}else {
				if (mButtonIsFocusCircular) {
					mButtonFocusPosition = -1;
					focusNextButton();
				}else {
					mButtonFocusPosition--;
				}
			}
		}		
	}
	
	public void focusPreviousButton(){
		if (buttonList != null) {

			mButtonFocusPosition --; 
			if (mButtonFocusPosition > -1) {
				for (int i = 0; i < buttonList.size(); i++) {
					if (i == mButtonFocusPosition) {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundClicked);
						buttonList.get(i).setTextColor(Color.WHITE);
					}else {
						buttonList.get(i).setBackgroundDrawable(mButtonBackgroundUnclicked);
						buttonList.get(i).setTextColor(mButtonsTextColor);
					}			
				}
			}else {
				if (mButtonIsFocusCircular) {
					mButtonFocusPosition = buttonList.size();
					focusPreviousButton();
				}else {
					mButtonFocusPosition++;
				}
			}
		}		
	}
}