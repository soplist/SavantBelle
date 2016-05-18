package com.flextronics.cn.activity.symboltraining;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.ui.ImageViewBtnGroup;
import com.flextronics.cn.ui.LinearLayoutBtnGroup;
import com.flextronics.cn.ui.ParamPanel;
import com.flextronics.cn.util.ArrayOperations;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**
 * 符号反映训练的参数选择页面
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-2-17 上午11:43:15
 */
public class SymbolResponseTrainingChooseParamsActivity extends BaseActivity {
	private static final String TAG = SymbolResponseTrainingChooseParamsActivity.class
			.getSimpleName();
	/**
	 * 样本集选择按钮
	 */
	private ImageViewBtnGroup symbolSetSelectorBtnGroup;

	/**
	 * 具体的受测符号选择按钮
	 */
	private ImageViewBtnGroup symboleSelectorBtnGroup;
	/**
	 * 自选受测符号时用的选择按钮
	 */
	private LinearLayoutBtnGroup symbolSelfSelector;
	private LinearLayoutBtnGroup symbolSelfSelector_number;
	private LinearLayoutBtnGroup symbolSelfSelector_roma_number;
	private LinearLayoutBtnGroup symbolSelfSelector_letter;
	private LinearLayoutBtnGroup symbolSelfSelector_common_mark;
	private LinearLayoutBtnGroup[] symbolSelfSelectors;

	/**
	 * 问题数图像按钮组
	 */
	private ImageViewBtnGroup questionCountImageViewBtnGroup;

	// =======================================================
	/**
	 * 颜色参数面板
	 */
	private ParamPanel colorParamPanel;
	/**
	 * 显示形体参数面板
	 */
	private ParamPanel displaySymbolParamPanel;
	/**
	 * 点击形状按钮后弹出的子面板
	 */
	// private ParamPanel shapesParamPanel;
	/**
	 * 点击自选形状弹出的子面板
	 */
	// private ParamPanel selfChooseShapesParamPanel;
	/**
	 * 问题数参数面板
	 */
	private ParamPanel questionCountParamPanel;

	/**
	 * 自选单一颜色图像按钮组
	 */
	// private ImageViewBtnGroup selfChooseColorImageViewBtnGroup;
	/**
	 * 选择单一形状的按钮组
	 */
	// private LinearLayoutBtnGroup shapesLinearLayoutBtnGroup;
	// private Map<String, String> uiTextMap;
	// private Map<String, Integer> uiParamValueMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 从BaseActivity中取得父布局
		RelativeLayout layout = getBaseRelativeLayout();
		// 将R.layout.color_memory_training_choose_parameter中定义的布局添加到父布局中，并显示出来
		layout.addView((RelativeLayout) getBaseLayoutInflater().inflate(
				R.layout.symbol_response_training_choose_parameter, null),
				getBaseLayoutParams());
		setContentView(layout);

		// setupHashMaps();
		setupViews();
		setupListeners();

		setupTexts();
	}

	private void setupViews() {
		// 设置训练标题
		setTrainingTitle("符号辨识|反应训练");
		// 显示用户名
		setUserNameEnabled(true);
		// 显示用户头像
		setUserIconEnable(true);
		setCancelButtonEnable(true);
		// 显示确定按钮
		setOkButtonEnable(true);
		// 显示后退按钮
		setBackButtonEnable(true);
		// 显示主页按钮
		setHomeButtonEnable(true);

		colorParamPanel = (ParamPanel) findViewById(R.id.ParamPanel01);
		// selfChooseColorParamPanel = (ParamPanel)
		// findViewById(R.id.ParamPanel06);
		displaySymbolParamPanel = (ParamPanel) findViewById(R.id.ParamPanel02);
		// shapesParamPanel = (ParamPanel) findViewById(R.id.ParamPanel03);
		// selfChooseShapesParamPanel = (ParamPanel)
		// findViewById(R.id.ParamPanelShapeContent);
		questionCountParamPanel = (ParamPanel) findViewById(R.id.ParamPanel04);

		symbolSetSelectorBtnGroup = (ImageViewBtnGroup) findViewById(R.id.ImageViewBtnGroup01);

		// selfChooseColorImageViewBtnGroup = (ImageViewBtnGroup)
		// findViewById(R.id.ImageViewBtnGroup06);

		symboleSelectorBtnGroup = (ImageViewBtnGroup) findViewById(R.id.ImageViewBtnGroup02);
		questionCountImageViewBtnGroup = (ImageViewBtnGroup) findViewById(R.id.ImageViewBtnGroup04);

		symbolSelfSelector_number = (LinearLayoutBtnGroup) findViewById(R.id.symbolSelfSelector_number);
		symbolSelfSelector_roma_number = (LinearLayoutBtnGroup) findViewById(R.id.symbolSelfSelector_roma_number);
		symbolSelfSelector_letter = (LinearLayoutBtnGroup) findViewById(R.id.symbolSelfSelector_letter);
		symbolSelfSelector_common_mark = (LinearLayoutBtnGroup) findViewById(R.id.symbolSelfSelector_common_mark);
		symbolSelfSelector = symbolSelfSelector_number;
		symbolSelfSelectors = new LinearLayoutBtnGroup[] {
				symbolSelfSelector_number, symbolSelfSelector_letter,
				symbolSelfSelector_common_mark, symbolSelfSelector_roma_number };
		for (LinearLayoutBtnGroup bg : symbolSelfSelectors) {
			bg.setVisibility(View.GONE);
		}
		// selfChooseSymbolSelector = (LinearLayoutBtnGroup)
		// findViewById(R.id.LinearLayoutBtnGroup_Shape);
		// shapesLinearLayoutBtnGroup = (LinearLayoutBtnGroup)
		// findViewById(R.id.LinearLayoutBtnGroup05);

		// selfChooseColorParamPanel.getBtnRelativeLayout().setVisibility(
		// View.GONE);
		// shapesParamPanel.getBtnRelativeLayout().setVisibility(View.GONE);
		// selfChooseShapesParamPanel.getBtnRelativeLayout().setVisibility(
		// View.GONE);
	}

	private void setupListeners() {

		setOnHomeButtonTouchListener(new ImageView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(),
							MainMenuActivity.class));
					finish();
				}
				return false;
			}
		});

		setOnBackButtonTouchListener(new ImageView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(
							SymbolResponseTrainingChooseParamsActivity.this,
							Main.class));
					finish();
				}
				return false;
			}
		});

		setOnCancelButtonTouchListener(new ImageView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(
							SymbolResponseTrainingChooseParamsActivity.this,
							Main.class));
					finish();
				}
				return false;
			}
		});

		setOnOkButtonTouchListener(new ImageView.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent();
					intent.putExtra(Constants.SymbolTraingParam.Key.SAMPLE_SET,
							getSampleSet());
					intent.putExtra(
							Constants.SymbolTraingParam.Key.QUESTION_COUNT,
							getQuestionCount());
					intent.putExtra(
							Constants.SymbolTraingParam.Key.SAMPLE_ELEMENT,
							getSampleElement());
					intent.setClass(
							SymbolResponseTrainingChooseParamsActivity.this,
							SymbolResponseTrainingActivity.class);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});

		// 选择样本集
		symbolSetSelectorBtnGroup
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						setupSymbolSetResultText();
						for (LinearLayoutBtnGroup bg : symbolSelfSelectors) {
							if (bg == symbolSelfSelector) {
								if (getSampleElement() != Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM
										&& getSampleElement() != Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING) {
									bg.setVisibility(View.VISIBLE);
								} else {
									bg.setVisibility(View.GONE);
								}
							} else {
								bg.setVisibility(View.GONE);
							}
						}
						setupSymboleResultText();
					}
				});

		// 选择“选择样本形式”(随机/设定/自选)
		symboleSelectorBtnGroup
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						// TODO Auto-generated method stub
						setupTexts();
						String value = symboleSelectorBtnGroup.getValue();
						if (value != null) {
							int i = Integer.parseInt(value.trim());
							if (i != Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM
									&& i != Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING) {
								for (LinearLayoutBtnGroup bg : symbolSelfSelectors) {
									if (symbolSelfSelector == bg) {
										bg.setVisibility(View.VISIBLE);
									} else {
										bg.setVisibility(View.GONE);
									}
								}
							} else {
								for (LinearLayoutBtnGroup bg : symbolSelfSelectors) {
									bg.setVisibility(View.GONE);
								}
							}
						}
					}
				});

		// 选中具体的题目数后将结果显示在右上角
		questionCountImageViewBtnGroup
				.setOnSelectListener(new ImageViewBtnGroup.OnSelectListener() {

					@Override
					public void onSelect(int position) {
						// TODO Auto-generated method stub
						setupQuestionCountResultText();
					}
				});

		symbolSelfSelector_number
				.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						setupSymboleResultText();
					}
				});

		symbolSelfSelector_letter
				.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						setupSymboleResultText();
					}
				});

		symbolSelfSelector_common_mark
				.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						setupSymboleResultText();
					}
				});

		symbolSelfSelector_roma_number
				.setOnSelectListener(new LinearLayoutBtnGroup.OnSelectListener() {
					@Override
					public void onSelect(int position) {
						setupSymboleResultText();
					}
				});
	}

	/**
	 * 显示“颜色”参数值
	 */
	private void setupSymbolSetResultText() {
		String value = symbolSetSelectorBtnGroup.getValue();
		if (value != null) {
			Integer i = Integer.parseInt(value.trim());
			switch (i) {
			case Constants.Sample.NUMBERS:
				colorParamPanel.setResultText("阿拉伯数字");
				symbolSelfSelector = symbolSelfSelector_number;
				break;
			case Constants.Sample.ROME_NUMBERS:
				colorParamPanel.setResultText("罗马数字");
				symbolSelfSelector = symbolSelfSelector_roma_number;
				break;
			case Constants.Sample.ENGLISH_LETTERS:
				colorParamPanel.setResultText("英文字母");
				symbolSelfSelector = symbolSelfSelector_letter;
				break;
			case Constants.Sample.COMMON_MARKS:
				colorParamPanel.setResultText("一般符号");
				symbolSelfSelector = symbolSelfSelector_common_mark;
				break;
			}
		}
	}

	/**
	 * 显示“题目数”参数值
	 */
	private void setupQuestionCountResultText() {
		questionCountParamPanel.setResultText(questionCountImageViewBtnGroup
				.getValue()
				+ getString(R.string.question_count_unit));
	}

	/**
	 * 显示各参数面板的参数值
	 */
	private void setupTexts() {
		setupSymbolSetResultText();
		setupSymboleResultText();
		setupQuestionCountResultText();
	}

	private void setupSymboleResultText() {
		displaySymbolParamPanel.setResultText(getSampleElementStr());
		// String value = this.symboleSelectorBtnGroup.getValue();
		// if (value != null) {
		// int i = Integer.parseInt(value.trim());
		// switch (i) {
		// case Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM:
		// displaySymbolParamPanel.setResultText("随机符号");
		// break;
		// case Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING:
		// displaySymbolParamPanel.setResultText("设定符号");
		// break;
		// default:
		// displaySymbolParamPanel.setResultText("自选符号");
		// break;
		// }
		// }
	}

	private int getSampleSet() {
		return Integer.parseInt(symbolSetSelectorBtnGroup.getValue());
	}

	private int getQuestionCount() {
		return Integer.parseInt(questionCountImageViewBtnGroup.getValue());
	}

	private int getSampleElement() {
		int i = Integer.parseInt(symboleSelectorBtnGroup.getValue().trim());
		if (i == Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM
				|| i == Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING) {
			return i;
		}
		int sampleElement = Integer.parseInt(symbolSelfSelector.getValue());
		return sampleElement;
	}

	private String getSampleElementStr() {
		int i = getSampleElement();
		if (i == Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM) {
			return "随机符号";
		}
		if (i == Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING) {
			return "设定符号";
		}
		// TODO
		return "自选符号|" + getStringByElementID(i);
	}

	protected void startResponseTraining(Intent intent) {
		int sampleElement = 0;
		intent.putExtra(Constants.SymbolTraingParam.Key.SAMPLE_ELEMENT,
				sampleElement);
		intent.setClass(this, SymbolResponseTrainingActivity.class);
		this.startActivity(intent);
	}

	private String getStringByElementID(int elementID) {
		if (elementID < 0) {
			return "";
		}
		String elementIDStr = String.valueOf(elementID);
		Log.d(TAG, "elmentID=" + elementID);
		if (getSampleSet() == Constants.Sample.NUMBERS) {
			return Constants.Numbers.STRING_NUMBERS[ArrayOperations
					.indexInElement(Constants.Numbers.NUMBERS, elementIDStr)];
		} else if (getSampleSet() == Constants.Sample.ROME_NUMBERS) {
			return Constants.RomeNumbers.STRING_ROMA_NUMBER[ArrayOperations
					.indexInElement(Constants.RomeNumbers.NUMBERS, elementIDStr)];
		} else if (getSampleSet() == Constants.Sample.ENGLISH_LETTERS) {
			return Constants.EnglishLetters.STRING_LETTERS[ArrayOperations
					.indexInElement(Constants.EnglishLetters.LETTERS,
							elementIDStr)];
		} else if (getSampleSet() == Constants.Sample.COMMON_MARKS) {
			return Constants.CommonMarks.STRING_COMMON_MARKS[ArrayOperations
					.indexInElement(Constants.CommonMarks.MARKS,
							elementIDStr)];
		}
		return "";
	}
}