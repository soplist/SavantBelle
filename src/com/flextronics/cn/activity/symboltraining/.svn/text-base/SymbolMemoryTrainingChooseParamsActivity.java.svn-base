/**
 * 
 */
package com.flextronics.cn.activity.symboltraining;

import java.util.Queue;

import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.ImageViewBtnGroup;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.Constants;
import com.flextronics.cn.activity.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * 符号辨识记忆训练的参数选择页面
 * @author Zhaohe.Guo
 * 
 */
public class SymbolMemoryTrainingChooseParamsActivity extends BaseActivity {

	private ParamPanel sampleSetSettingPanel = null;
	private ImageViewBtnGroup sampleSetSelector = null;
	private ParamPanel unitSettingPanel = null;
	private ImageViewBtnGroup unitSettingSelector = null;
	private ImageViewBtnGroup unitSelector = null;
	private ParamPanel displaySettingPanel = null;
	private ImageViewBtnGroup displaySelector = null;
	private ImageViewBtnGroup pattenSelfSelector = null;
	private ParamPanel animationSettingPanel = null;
	private ImageViewBtnGroup animationSelector = null;
	private ParamPanel qustionCountSettingPanel = null;
	private ImageViewBtnGroup questionCountSelector = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this
				.getBaseRelativeLayout()
				.addView(
						getBaseLayoutInflater()
								.inflate(
										R.layout.symbol_memory_training_choose_parameter,
										null));
		this.setContentView(getBaseRelativeLayout());
		setupViews();
		setupListeners();
		setResultText();
	}

	private void setupViews() {
		this.setTrainingTitle("符号辨识|记忆训练");
		// 显示用户名
		setUserNameEnabled(true);
		// 显示用户头像
		setUserIconEnable(true);
		sampleSetSettingPanel = (ParamPanel) findViewById(R.id.sampleSetSettingPanel);
		sampleSetSelector = (ImageViewBtnGroup) findViewById(R.id.sampleSetSelector);
		unitSettingPanel = (ParamPanel) findViewById(R.id.unitSettingPanel);
		unitSettingSelector = (ImageViewBtnGroup) findViewById(R.id.unitSettingSelector);
		unitSelector = (ImageViewBtnGroup) findViewById(R.id.unitSelector);
		{
			unitSelector.setVisibility(View.GONE);
		}
		displaySettingPanel = (ParamPanel) findViewById(R.id.displaySettingPanel);
		displaySelector = (ImageViewBtnGroup) findViewById(R.id.displaySelector);
		pattenSelfSelector = (ImageViewBtnGroup) findViewById(R.id.pattenSelfSelector);
		{
			pattenSelfSelector.setVisibility(View.GONE);
		}
		animationSettingPanel = (ParamPanel) findViewById(R.id.animationSettingPanel);
		animationSelector = (ImageViewBtnGroup) findViewById(R.id.animationSelector);
		qustionCountSettingPanel = (ParamPanel) findViewById(R.id.qustionCountSettingPanel);
		questionCountSelector = (ImageViewBtnGroup) findViewById(R.id.qustionCountSelector);
	}

	private void setupListeners() {
		unitSettingSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						int i = Integer
								.parseInt(unitSettingSelector.getValue());
						if (i == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_MANUAL) {
							unitSelector.setVisibility(View.VISIBLE);
						} else {
							unitSelector.setVisibility(View.GONE);
						}
						setResultText();
					}
				});
		displaySelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						int i = Integer.parseInt(displaySelector.getValue());
						if (i == Constants.SymbolTraingParam.DISPLAY_SETTING_COMMON) {
							pattenSelfSelector.setVisibility(View.GONE);
						} else {
							pattenSelfSelector.setVisibility(View.VISIBLE);
						}
						setResultText();
					}
				});
		sampleSetSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setResultText();
					}
				});
		unitSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setResultText();
					}
				});
		pattenSelfSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setResultText();
					}
				});
		animationSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setResultText();
					}
				});
		questionCountSelector
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setResultText();
					}
				});
		this.setOnOkButtonTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent();
					intent.putExtra(Constants.SymbolTraingParam.Key.SAMPLE_SET,
							getSampleSet());
					intent.putExtra(
							Constants.SymbolTraingParam.Key.QUESTION_COUNT,
							getQuestionCount());
					startMemoryTrainning(intent);
					finish();
				}
				return false;
			}
		});
		this.setOnBackButtonTouchListener(new GoToActivityListener(this,MainMenuActivity.class,false));
		this.setOnCancelButtonTouchListener(new GoToActivityListener(this,MainMenuActivity.class,false));
		this.setOnHomeButtonTouchListener(new GoToActivityListener(this,MainMenuActivity.class,false));

	}

	private void startMemoryTrainning(Intent intent) {
		intent.putExtra(Constants.SymbolTraingParam.Key.ANIMATION_SETTING,
				getAnimationSetting());
		intent.putExtra(Constants.SymbolTraingParam.Key.DISPLAY_PATTERN,
				getPattern());
		intent.putExtra(Constants.SymbolTraingParam.Key.DISPLAY_SETTING,
				getDisplaySetting());
		intent.putExtra(Constants.SymbolTraingParam.Key.UNIT_COUNT_SETTING,
				getUnitSetting());
		intent.putExtra(Constants.SymbolTraingParam.Key.UNIT_COUNT,
				getUnitCount());
		intent.setClass(this, SymbolMemeryTrainingActivity.class);
		this.startActivity(intent);
	}

	private void setResultText() {
		setSampleSetText();
		setAnimationSettingText();
		setDisplaySettingText();
		setUnitSettingText();
		setQuestionCountText();
	}

	private int getSampleSet() {
		return Integer.parseInt(sampleSetSelector.getValue());
	}

	private void setSampleSetText() {
		String s = "";
		if (getSampleSet() == Constants.Sample.COMMON_MARKS) {
			s = "一般符号";
		} else if (getSampleSet() == Constants.Sample.ENGLISH_LETTERS) {
			s = "英文字母";
		} else if (getSampleSet() == Constants.Sample.NUMBERS) {
			s = "阿拉伯数字";
		} else if (getSampleSet() == Constants.Sample.ROME_NUMBERS) {
			s = "罗马文字";
		}
		sampleSetSettingPanel.setResultText(s);
	}

	private int getQuestionCount() {
		return Integer.parseInt(questionCountSelector.getValue());
	}

	private void setQuestionCountText() {
		qustionCountSettingPanel.setResultText(String
				.valueOf(getQuestionCount()));
	}

	private int getAnimationSetting() {
		return Integer.parseInt(animationSelector.getValue());
	}

	private void setAnimationSettingText() {
		String s = "符号";
		if (getAnimationSetting() == Constants.SymbolTraingParam.ANIMATION_SETTING_SAME_TIME_FADEIN_FADEOUT) {
			s += "同时显示同时消失";
		} else if (getAnimationSetting() == Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_FADEOUT) {
			s += "显示后消失";
		} else if (getAnimationSetting() == Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_SAME_FADEOUT) {
			s += "显示后保留";
		}
		animationSettingPanel.setResultText(s);
	}

	private int getPattern() {
		return Integer.parseInt(pattenSelfSelector.getValue());
	}

	private String getPatternName() {
		String name = "";
		switch (getPattern()) {
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_CIRCULAR:
			name = "圆形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_DIAMOND:
			name = "菱形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_HEXAGON:
			name = "六边形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_PARALLELOGRAM:
			name = "平行四边形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_PENTAGON:
			name = "五边形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_RECTANGLE:
			name = "矩形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_SECTOR:
			name = "扇形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_SQUARE:
			name = "正方形";
			break;
		case Constants.SymbolTraingParam.DISPLAY_PATTERN_TRIANGLE:
			name = "三角形";
			break;
		}
		return name;
	}

	private int getDisplaySetting() {
		return Integer.parseInt(displaySelector.getValue());
	}

	private void setDisplaySettingText() {
		String s = "";
		if (getDisplaySetting() == Constants.SymbolTraingParam.DISPLAY_SETTING_COMMON) {
			s = "一般显示";
		} else if (getDisplaySetting() == Constants.SymbolTraingParam.DISPLAY_SETTING_PATTERN_RANDOM) {
			s = "按图样显示(乱序)|";
			s += getPatternName();
		} else if (getDisplaySetting() == Constants.SymbolTraingParam.DISPLAY_SETTING_PATTERN_SEQUENTIAL) {
			s = "按图样显示(顺序)|";
			s += getPatternName();
		}
		displaySettingPanel.setResultText(s);
	}

	private int getUnitSetting() {
		return Integer.parseInt(unitSettingSelector.getValue());
	}

	private void setUnitSettingText() {
		String s = "";
		if (getUnitSetting() == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_DEFAULT) {
			s = "设定位元数";
		} else if (getUnitSetting() == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_SEQENTIAL) {
			s = "连续位元数";
		} else if (getUnitSetting() == Constants.SymbolTraingParam.UNIT_COUNT_SETTING_MANUAL) {
			s = "自选位元数|" + getUnitCount();
		}
		unitSettingPanel.setResultText(s);
	}

	private int getUnitCount() {
		return Integer.parseInt(unitSelector.getValue());
	}
}
