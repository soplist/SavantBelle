package com.flextronics.cn.activity.symboltraining;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.ImageViewBtnGroup;
import com.flextronics.cn.ui.ParamPanel;

/**
 * 颜色辨识训练选择训练种类(反映训练/记忆训练)
 */
public class Main extends BaseActivity {

	private static final String RESPONSE = "RESPONSE";
	private static final String MEMORY = "MEMORY";

	private ParamPanel trainingSettingPanel;
	private ImageViewBtnGroup trainingSelector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getBaseRelativeLayout().addView(
				getBaseLayoutInflater().inflate(R.layout.symbol_ui_temp_main,
						null));
		setContentView(getBaseRelativeLayout());

		setupViews();
		setupListeners();
		setText();
		setTrainingTitle("符号辨识");
	}

	private void setupViews() {
		// 显示用户名
		setUserNameEnabled(true);
		// 显示用户头像
		setUserIconEnable(true);
		trainingSettingPanel = (ParamPanel) findViewById(R.id.trainingSettingPanel);
		trainingSelector = (ImageViewBtnGroup) findViewById(R.id.trainingSelector);
	}

	private void setupListeners() {
		this.setBackButtonEnable(false);
		this.setOnHomeButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class, false));
		this.setOnCancelButtonTouchListener(new GoToActivityListener(this,
				MainMenuActivity.class, false));
		this.setOnOkButtonTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					next();
				}
				return false;
			}
		});
		trainingSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						setText();
					}
				});
	}

	private String getTrainingType() {
		return trainingSelector.getValue();
	}

	private void setText() {
		if (getTrainingType().equalsIgnoreCase(RESPONSE)) {
			trainingSettingPanel.setResultText("反应训练");
		} else {
			trainingSettingPanel.setResultText("记忆训练");
		}
	}

	private void next() {
		Intent intent = new Intent();
		if (getTrainingType().equalsIgnoreCase(RESPONSE)) {
			intent.setClass(getApplicationContext(),
					SymbolResponseTrainingChooseParamsActivity.class);
		} else {
			intent.setClass(getApplicationContext(),
					SymbolMemoryTrainingChooseParamsActivity.class);
		}
		startActivity(intent);
		finish();
	}

}
