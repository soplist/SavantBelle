/**
 * 
 */
package com.flextronics.cn.activity.symboltraining;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.CommonUtil;
import com.flextronics.cn.util.Constants;

/**
 * 临时的符号辨识训练的参数选择页面
 * 
 * @author Zhaohe.Guo
 * 
 *         2010-12-23 下午02:31:23
 */
public class TempSymbolTrainingGuideActivity extends Activity {
	private static final String TAG = TempSymbolTrainingGuideActivity.class
			.getSimpleName();

	private LayoutInflater inflater;
	private ViewGroup layout;
	private ResponseSettingPanel responseSettingPanel = null;
	private MemorySettingPanel memorySettingPanel = null;
	private State state;

	enum State {
		responseSetting, memorySetting
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		state = State.responseSetting;
		setupViews();

		setupListeners();
	}

	private void setupViews() {
		inflater = LayoutInflater.from(this);
		this.layout = (ViewGroup) inflater.inflate(
				R.layout.symbol_temp_ui_main, null);
		responseSettingPanel = new ResponseSettingPanel();
		memorySettingPanel = new MemorySettingPanel();
		layout.addView(responseSettingPanel.getWrappedContent(), layout
				.getChildCount() - 1);
		this.setContentView(layout);

		// ====setup question count spinner
		Spinner questionCountSpinner = (Spinner) layout
				.findViewById(R.id.spinner_question_count);
		questionCountSpinner.setAdapter(new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, new Integer[] { 15, 30,
						45 }));
	}

	/**
	 * @return 返回所选的样本集
	 */
	private int getSampleSet() {
		RadioGroup radioGroup = (RadioGroup) layout
				.findViewById(R.id.radio_group_sample_set);
		int radioButtonID = radioGroup.getCheckedRadioButtonId();
		if (radioButtonID == R.id.radio_button_number) {
			return Constants.Sample.NUMBERS;
		} else if (radioButtonID == R.id.radio_button_roma_number) {
			return Constants.Sample.ROME_NUMBERS;
		} else if (radioButtonID == R.id.radio_button_english) {
			return Constants.Sample.ENGLISH_LETTERS;
		} else if (radioButtonID == R.id.radio_button_common_mark) {
			return Constants.Sample.COMMON_MARKS;
		} else if (radioButtonID == R.id.radio_button_music_mark) {
			return Constants.Sample.MUSIC_MARKS;
		}
		Log.e(TAG, "sampleSet wronng.radioButtonID=" + radioButtonID);
		return 0;
	}

	private int getQuestionCount() {
		return (Integer) ((Spinner) layout
				.findViewById(R.id.spinner_question_count)).getSelectedItem();
	}

	private void setupListeners() {
		((RadioGroup) layout.findViewById(R.id.radio_group_training_type))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.radio_button_response) {
							if (state == State.memorySetting) {
								TempSymbolTrainingGuideActivity.this.state = State.responseSetting;
								changeToResponseSettingPanel();
							}
						} else {
							if (state == State.responseSetting) {
								TempSymbolTrainingGuideActivity.this.state = State.memorySetting;
								changeToMermorySettingPanel();
							}
						}
					}
				});
		// 确定按钮
		layout.findViewById(R.id.enterButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.putExtra(
								Constants.SymbolTraingParam.Key.SAMPLE_SET,
								getSampleSet());
						intent.putExtra(
								Constants.SymbolTraingParam.Key.QUESTION_COUNT,
								getQuestionCount());
						if (TempSymbolTrainingGuideActivity.this.state == State.responseSetting) {
							startResponseTraining(intent);
						} else {
							startMemoryTrainning(intent);
						}
					}
				});
	}

	protected void startMemoryTrainning(Intent intent) {
		intent.putExtra(Constants.SymbolTraingParam.Key.ANIMATION_SETTING,
				memorySettingPanel.getAnimationSetting());
		intent.putExtra(Constants.SymbolTraingParam.Key.DISPLAY_PATTERN,
				memorySettingPanel.getPattern());
		intent.putExtra(Constants.SymbolTraingParam.Key.DISPLAY_SETTING,
				memorySettingPanel.getDisplaySetting());
		intent.putExtra(Constants.SymbolTraingParam.Key.UNIT_COUNT_SETTING,
				memorySettingPanel.getUnitCountType());
		intent.putExtra(Constants.SymbolTraingParam.Key.UNIT_COUNT,
				memorySettingPanel.getUnitCount());
		intent.setClass(this, SymbolMemeryTrainingActivity.class);
		this.startActivity(intent);
	}

	protected void startResponseTraining(Intent intent) {
		int sampleElement = responseSettingPanel.getSelectedSampleElement();
		intent.putExtra(Constants.SymbolTraingParam.Key.SAMPLE_ELEMENT,
				sampleElement);
		intent.setClass(this, SymbolResponseTrainingActivity.class);
		this.startActivity(intent);
	}

	private void changeToResponseSettingPanel() {
		layout.removeView(memorySettingPanel.getWrappedContent());
		layout.addView(responseSettingPanel.getWrappedContent(), layout
				.getChildCount() - 1);
	}

	private void changeToMermorySettingPanel() {
		layout.removeView(responseSettingPanel.getWrappedContent());
		layout.addView(memorySettingPanel.getWrappedContent(), layout
				.getChildCount() - 1);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private abstract class SettingPanel {
		View content;

		SettingPanel(int layoutID) {
			this.content = inflater.inflate(layoutID, null);
		}

		public View getWrappedContent() {
			return content;
		}
	}

	/**
	 * 符号反映训练设置面板
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2010-12-24 上午11:45:35
	 */
	private class ResponseSettingPanel extends SettingPanel {
		Gallery sampleElementSelector;

		int sampleElement = Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING;

		public ResponseSettingPanel() {
			super(R.layout.symbol_temp_ui_response);
			this.sampleElementSelector = (Gallery) content
					.findViewById(R.id.gallery_sample_elements_selector);
			sampleElementSelector.setBackgroundColor(Color.WHITE);
			sampleElementSelector
					.setAdapter(new ImageSpinnerAdaptor(
							TempSymbolTrainingGuideActivity.this,
							CommonUtil
									.getSampleElementImageResBySampleCode(getSampleSet())));
			sampleElementSelector
					.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							sampleElement = Integer
									.parseInt(CommonUtil
											.getSampleElementsInSample(getSampleSet())[position]);
							for (int i = 0; i < parent.getChildCount(); i++) {
								View v = parent.getChildAt(i);
								if (v == view) {
									v.setBackgroundColor(Color.BLUE);
								} else {
									v.setBackgroundColor(Color.TRANSPARENT);
								}
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							Log.d(TAG, "nothing selected");
						}
					});
			sampleElementSelector.setVisibility(View.INVISIBLE);
			RadioGroup rg = (RadioGroup) content
					.findViewById(R.id.RadioGroup01);
			rg
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							if (checkedId == R.id.radio_button_sampleSetting_manual) {
								sampleElementSelector
										.setVisibility(View.VISIBLE);
								sampleElement = 0;
							} else if (checkedId == R.id.radio_button_sampleSetting_default) {
								sampleElement = Constants.SymbolTraingParam.SAMPLE_ELEMENT_SETTING;
							} else if (checkedId == R.id.radio_button_sampleSetting_random) {
								sampleElement = Constants.SymbolTraingParam.SAMPLE_ELEMENT_RANDOM;
							}
						}
					});

		}

		public int getSelectedSampleElement() {
			return sampleElement;
		}

	}

	private class MemorySettingPanel extends SettingPanel {

		// private int unitCountSettingType =
		// Constants.SymbolTraingParam.UNIT_COUNT_SETTING_DEFAULT;
		private Spinner unitCountSelector;
		private Spinner patternSelector;

		MemorySettingPanel() {
			super(R.layout.symbol_temp_ui_memory);
			_setupViews();
			_setupListeners();
		}

		private void _setupViews() {
			unitCountSelector = (Spinner) content
					.findViewById(R.id.spinner_unit_count_selector);
			unitCountSelector.setVisibility(View.INVISIBLE);
			Integer[] array = new Integer[28];
			for (int i = 0; i < array.length; i++) {
				array[i] = i + 3;
			}
			ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
					TempSymbolTrainingGuideActivity.this,
					android.R.layout.simple_spinner_item, array);
			;
			unitCountSelector.setAdapter(arrayAdapter);

			patternSelector = (Spinner) content
					.findViewById(R.id.spinner_pattern_selector);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					TempSymbolTrainingGuideActivity.this,
					android.R.layout.simple_spinner_item,
					SettingMap.PATTERN_SETTING.keySet().toArray(new String[0]));
			patternSelector.setAdapter(adapter);
			// patternSelector.setEnabled(false);
		}

		private void _setupListeners() {
			((RadioGroup) content.findViewById(R.id.RadioGroup01))
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							if (checkedId == R.id.radio_button_default) {
								unitCountSelector.setVisibility(View.INVISIBLE);
							} else if (checkedId == R.id.radio_button_seqencial) {
								unitCountSelector.setVisibility(View.INVISIBLE);
							} else {
								unitCountSelector.setVisibility(View.VISIBLE);
							}
						}
					});
		}

		int getUnitCountType() {
			int buttonID = ((RadioGroup) content
					.findViewById(R.id.RadioGroup01)).getCheckedRadioButtonId();
			return SettingMap.UNIT_COUNT_TYPE.get(buttonID);
		}

		/**
		 * @return 符号位元数
		 */
		int getUnitCount() {
			return (Integer) unitCountSelector.getSelectedItem();
		}

		/**
		 * @return 显示设置类型
		 */
		int getDisplaySetting() {
			int buttonID = ((RadioGroup) content
					.findViewById(R.id.RadioGroup02)).getCheckedRadioButtonId();
			return SettingMap.DISPLAY_SETTING.get(buttonID);
		}

		/**
		 * @return 动画显示类型
		 */
		int getAnimationSetting() {
			int buttonID = ((RadioGroup) content
					.findViewById(R.id.RadioGroup03)).getCheckedRadioButtonId();
			return SettingMap.ANIMATION_SETTING.get(buttonID);
		}

		/**
		 * @return 选择的图样
		 */
		int getPattern() {
			return SettingMap.PATTERN_SETTING.get((String) patternSelector
					.getSelectedItem());
		}
	}

	private static class ImageSpinnerAdaptor extends BaseAdapter {
		Context context;
		int[] imageResIDs;

		public ImageSpinnerAdaptor(Context context, int[] imageResIDs) {
			this.imageResIDs = imageResIDs;
			this.context = context;
		}

		@Override
		public int getCount() {
			return imageResIDs.length;
		}

		@Override
		public Object getItem(int position) {
			return imageResIDs[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView v;
			if (convertView != null) {
				v = (ImageView) convertView;
			} else {
				v = new ImageView(context);
				v.setImageResource(imageResIDs[position]);
			}
			return v;
		}
	}

	private static final class SettingMap {
		public static final Map<Integer, Integer> UNIT_COUNT_TYPE = new HashMap<Integer, Integer>();
		public static final Map<Integer, Integer> DISPLAY_SETTING = new HashMap<Integer, Integer>();
		public static final Map<Integer, Integer> ANIMATION_SETTING = new HashMap<Integer, Integer>();
		public static final Map<String, Integer> PATTERN_SETTING = new HashMap<String, Integer>();

		static {
			UNIT_COUNT_TYPE.put(R.id.radio_button_default,
					Constants.SymbolTraingParam.UNIT_COUNT_SETTING_DEFAULT);
			UNIT_COUNT_TYPE.put(R.id.radio_button_seqencial,
					Constants.SymbolTraingParam.UNIT_COUNT_SETTING_SEQENTIAL);
			UNIT_COUNT_TYPE.put(R.id.radio_button_manual,
					Constants.SymbolTraingParam.UNIT_COUNT_SETTING_MANUAL);
		}
		static {
			DISPLAY_SETTING.put(R.id.radio_button_display_common,
					Constants.SymbolTraingParam.DISPLAY_SETTING_COMMON);
			DISPLAY_SETTING.put(R.id.radio_button_display_pattern_random,
					Constants.SymbolTraingParam.DISPLAY_SETTING_PATTERN_RANDOM);
			DISPLAY_SETTING
					.put(
							R.id.radio_button_display_pattern_seqential,
							Constants.SymbolTraingParam.DISPLAY_SETTING_PATTERN_SEQUENTIAL);

		}
		static {
			ANIMATION_SETTING
					.put(
							R.id.radio_button_animation_same_fadein_fadeout,
							Constants.SymbolTraingParam.ANIMATION_SETTING_SAME_TIME_FADEIN_FADEOUT);
			ANIMATION_SETTING
					.put(
							R.id.radio_button_animation_single_fadein_fadeout,
							Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_FADEOUT);
			ANIMATION_SETTING
					.put(
							R.id.radio_button_animation_single_fadein_same_fadeout,
							Constants.SymbolTraingParam.ANIMATION_SETTING_SINGLE_FADEIN_SAME_FADEOUT);
		}

		static {
			PATTERN_SETTING.put("圆形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_CIRCULAR);
			PATTERN_SETTING.put("菱形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_DIAMOND);
			PATTERN_SETTING.put("六边形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_HEXAGON);
			PATTERN_SETTING.put("平行四边形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_PARALLELOGRAM);
			PATTERN_SETTING.put("五边形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_PENTAGON);
			PATTERN_SETTING.put("矩形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_RECTANGLE);
			PATTERN_SETTING.put("扇形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_SECTOR);
			PATTERN_SETTING.put("正方形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_SQUARE);
			PATTERN_SETTING.put("三角形图样",
					Constants.SymbolTraingParam.DISPLAY_PATTERN_TRIANGLE);
		}
	}
}
