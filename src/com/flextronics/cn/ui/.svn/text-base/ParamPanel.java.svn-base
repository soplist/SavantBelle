package com.flextronics.cn.ui;

import com.flextronics.cn.activity.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**��ͼƬ��ť�����ݵ���Ͽؼ������ͼƬ��������ʾ����
 * 
 * @author ZhangGuoYin
 *
 */
public class ParamPanel extends LinearLayout {

	public final static int CONTENT = 0;
	public final static int ALL = 1;
	private boolean show;
	private long lastActionTime;
	private boolean isFirstTimeOnLayout = true;
	
	/**
	 * ͼƬ��ť��ͼƬ��Դ
	 */
	private Drawable mSrc;
	/**
	 * ͼƬ��ť�������ఴť��������
	 */
	private String mText;
	/**
	 * ͼƬ��ť������Ҳ�����������
	 */
	private String mResultText;
	/**
	 * ͼƬ��ť�������ఴť������ɫ
	 */
	private ColorStateList mBtnTextColor;
	/**
	 * ͼƬ��ť������Ҳ���������ɫ
	 */
	private ColorStateList mBtnResultTextColor;
	/**
	 * ͼƬ��ť�������ఴť�����С
	 */	
	private int mBtnTextSize;
	/**
	 * ͼƬ��ť������Ҳ��������С
	 */
	private int mBtnResultTextSize;
	
	private int mContentId;
	private View mContent;

	private LayoutInflater mInflater;
	private RelativeLayout mBtnRelativeLayout;
	private ImageView mBtnImageView;
	private ImageView mBtnEnvelopImageView;
	private TextView mBtnTextView;
	private TextView mBtnResultTextView;
	/**
	 * ��ʾ�Ķ���
	 */
	///private Animation mShowAnimation;
	/**
	 * ���صĶ���
	 */
	///private Animation mHideAnimation;
	/**
	 * ��ʾ�����ļ�����
	 */
	///private AnimationListener mShowAnimationListener;
	/**
	 * ���ض����ļ�����
	 */
	///private AnimationListener mHideAnimationListener;
	private OnToggleListener onToggleListener;
	
	public static abstract interface OnToggleListener {
		public abstract void OnToggle(boolean open);
	}
	
	public ParamPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParamPanel);
		
		RuntimeException e = null;
		
		mSrc = a.getDrawable(R.styleable.ParamPanel_src);
		mBtnTextColor = a.getColorStateList(R.styleable.ParamPanel_btn_text_color);
		mBtnResultTextColor = a.getColorStateList(R.styleable.ParamPanel_result_text_color);
		mBtnTextSize = a.getInt(R.styleable.ParamPanel_result_text_size, 0);
		mBtnResultTextSize = a.getInt(R.styleable.ParamPanel_result_text_size, 0);
		mText = a.getString(R.styleable.ParamPanel_text);
		mResultText = a.getString(R.styleable.ParamPanel_result_text);

		mContentId = a.getResourceId(R.styleable.ParamPanel_content, 0);
		if (mContentId == 0) {
			e = new IllegalArgumentException(a.getPositionDescription() + 
					": The content attribute is required and must refer to a valid child.");
		}
		a.recycle();
		
		if (e != null) {
			throw e;
		}
		
		setOrientation(VERTICAL);
		mInflater = LayoutInflater.from(context);
		//��xml�ж�ȡԤ�ȶ���Ķ���
		///mShowAnimation = AnimationUtils.loadAnimation(context, R.anim.ui_param_panel_content_in);
		///mHideAnimation = AnimationUtils.loadAnimation(context, R.anim.ui_param_panel_content_out);
		
//		mShowAnimationListener = new AnimationListener() {
//			
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				show = true;
//			}
//		};
		
//		mHideAnimationListener = new AnimationListener() {
//			
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				mContent.setVisibility(GONE);
//				show = false;
//			}
//		};
		///mShowAnimation.setAnimationListener(mShowAnimationListener);
		///mHideAnimation.setAnimationListener(mHideAnimationListener);
		setBaselineAligned(false);
		onToggleListener = new OnToggleListener() {
			
			@Override
			public void OnToggle(boolean open) {
				// TODO Auto-generated method stub
				
			}
		};		
	}
		
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(isFirstTimeOnLayout){
			getContent().setVisibility(View.GONE);			
		}
		isFirstTimeOnLayout = false;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mBtnRelativeLayout = (RelativeLayout)mInflater.inflate(R.layout.ui_params_btn, null);
		mBtnImageView = (ImageView)mBtnRelativeLayout.findViewById(R.id.ImageViewParamBtn);
		mBtnEnvelopImageView = (ImageView)mBtnRelativeLayout.findViewById(R.id.ImageViewParamEnvelop);
		mBtnTextView = (TextView)mBtnRelativeLayout.findViewById(R.id.TextViewParamBtnText);
		mBtnResultTextView = (TextView)mBtnRelativeLayout.findViewById(R.id.TextViewParamBtnResult);
		
		mContent = findViewById(mContentId);
		if (mContent == null) {
			String name = getResources().getResourceEntryName(mContentId);
            throw new RuntimeException("Your Panel must have a child View whose id attribute is 'R.id." + name + "'");
		}
		
		mBtnRelativeLayout.setClickable(true);
		mContent.setClickable(true);
		//mContent.setVisibility(GONE);
		removeAllViews();
		addView(mBtnRelativeLayout);
		addView(mContent);
		if(mSrc != null){
			mBtnImageView.setImageDrawable(mSrc);
		}
		if(mText != null){
			mBtnTextView.setText(mText);
		}
		if(mBtnTextColor != null){
			mBtnTextView.setTextColor(mBtnTextColor);
		}
		if(mBtnTextSize != 0){
			mBtnTextView.setTextSize(mBtnTextSize);
		}
		
		if(mResultText != null){
			mBtnResultTextView.setText(mResultText);
		}
		if(mBtnResultTextColor != null){
			mBtnResultTextView.setTextColor(mBtnResultTextColor);
		}
		if(mBtnResultTextSize != 0){
			mBtnResultTextView.setTextSize(mBtnResultTextSize);
		}
		setupListeners();		

		ViewGroup.LayoutParams params = mContent.getLayoutParams();
		params.width = ViewGroup.LayoutParams.FILL_PARENT;		
		mContent.setLayoutParams(params);
	}
	
	/**
	 * ȡ������
	 * @return
	 */
	public View getContent() {
		return mContent;
	}
	
	/**
	 * ��������
	 * @param content
	 */
	public void setContent(int layoutRes) {
		setContent(mInflater.inflate(layoutRes, null));
	}
	
	/**
	 * ��������
	 * @param content
	 */
	public void setContent(View content) {
		this.mContent = content;
		ViewGroup.LayoutParams params = mContent.getLayoutParams();
		params.width = ViewGroup.LayoutParams.FILL_PARENT;	
		mContent.setLayoutParams(params);
	}

	public int getContentId() {
		return mContentId;
	}
	
	/**
	 * ��������ť����
	 * @return
	 */
	public RelativeLayout getBtnRelativeLayout() {
		return mBtnRelativeLayout;
	}

	/**
	 * ��ð�ťͼ��
	 * @return
	 */
	public ImageView getBtnImageView() {
		return mBtnImageView;
	}

	/**
	 * ��ð�ť��ߵ�˵��Text view
	 * @return
	 */
	public TextView getBtnTextView() {
		return mBtnTextView;
	}

	/**
	 * ��ð�ť�ұߵĽ��Text view
	 * @return
	 */
	public TextView getBtnResultTextView() {
		return mBtnResultTextView;
	}

	/**
	 * �趨��ť��ͼƬ
	 * @param src
	 */
	public void setSrc(Drawable src) {
		this.mSrc = src;
		mBtnImageView.setImageDrawable(this.mSrc);
		mBtnImageView.invalidate();
	}

	public void setBtnTextColor(ColorStateList btnTextColor) {
		this.mBtnTextColor = btnTextColor;
		mBtnTextView.setTextColor(mBtnTextColor);
	}

	public void setBtnResultTextColor(ColorStateList btnResultTextColor) {
		this.mBtnResultTextColor = btnResultTextColor;
		mBtnResultTextView.setTextColor(mBtnResultTextColor);
	}

	public void setBtnTextSize(int btnTextSize) {
		this.mBtnTextSize = btnTextSize;
		mBtnTextView.setTextSize(mBtnTextSize);
	}

	public void setBtnResultTextSize(int btnResultTextSize) {
		this.mBtnResultTextSize = btnResultTextSize;
		mBtnResultTextView.setTextSize(mBtnResultTextSize);
	}

	public void setBtnResultTextView(TextView btnResultTextView) {
		this.mBtnResultTextView = btnResultTextView;
	}

	public void setBtnTextView(TextView btnTextView) {
		this.mBtnTextView = btnTextView;
	}
	
	public void setOnToggleListener(OnToggleListener onToggleListener) {
		this.onToggleListener = onToggleListener;
	}

	/**
	 * ȡ�ð�ť��ߵ�����
	 * @return
	 */
	public String getText() {
		if(null != getBtnTextView().getText()){
			return getBtnTextView().getText().toString();			
		}
		return null;
	}

	
	/**
	 * ���ð�ť��ߵ�����
	 * @param mText
	 */
	public void setText(String text) {
		this.mText = text;
		getBtnTextView().setText(mText);
	}

	
	/**
	 * ȡ�ð�ť�ұߵ�����
	 * @return
	 */
	public String getResultText() {
		if(null != getBtnResultTextView().getText()){
			return getBtnResultTextView().getText().toString();			
		}
		return null;
	}

	
	/**
	 * ���ð�ť�ұߵ�����
	 * @param resultText
	 */
	public void setResultText(String resultText) {
		this.mResultText = resultText;
		getBtnResultTextView().setText(mResultText);
	}

	/**
	 * �ر�content
	 */
	public void close(){
		///mContent.startAnimation(mHideAnimation);		

		mContent.setVisibility(GONE);
		show = false;
	}
	
	/**
	 * ��content
	 */
	public void open(){
		mContent.setVisibility(VISIBLE);
		///mContent.startAnimation(mShowAnimation);
		show = true;
	}	
	
	/**
	 * ��/�ر�content
	 * @param part �ǽ������content�������panel
	 */
	public void toggle(int part){
		//���ε����ʱ��������С��800����
		if(System.currentTimeMillis() - lastActionTime > 800){
			onToggleListener.OnToggle(!show);
			if(!show){
				if(part == ALL){
					setBtnTextColor(ColorStateList.valueOf(Color.WHITE));
					getBtnImageView().setImageResource(R.drawable.ui_params_btn_select);
				}
				open(); 
			}else{
				if(part == ALL){
					setBtnTextColor(ColorStateList.valueOf(Color.parseColor("#58595B")));
					getBtnImageView().setImageResource(R.drawable.ui_params_btn_normal);
				}
				close();
			}
			lastActionTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * content�Ƿ��
	 * @return
	 */
	public boolean isOpen(){		
		return show;
	}
	
	/**
	 * content�Ƿ�ر�
	 * @return
	 */
	public boolean isClose(){
		return !show;
	}
	
	private void setupListeners(){
		mBtnRelativeLayout.setOnClickListener(new RelativeLayout.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toggle(ALL);
			}			
		});
	}
	
	/**
	 * �����ParamPanel���ر�content+�ҵ�+��ֹ���
	 */
	public void lock(){
		mBtnRelativeLayout.setClickable(false);
		mBtnEnvelopImageView.setVisibility(VISIBLE);
		setBtnTextColor(ColorStateList.valueOf(Color.parseColor("#58595B")));
		getBtnImageView().setImageResource(R.drawable.ui_params_btn_normal);
		if(isOpen()){
			close();
		}
	}
	
	/**
	 * �������ParamPanel,�ָ���
	 */
	public void unlock(){
		mBtnRelativeLayout.setClickable(true);
		mBtnEnvelopImageView.setVisibility(GONE);
	}
}
