package com.flextronics.cn.activity.rockertraining;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.MainMenuActivity;
import com.flextronics.cn.activity.R;

public class SeparateControlActivity  extends BaseActivity{
	private final static String TAG = "SeparateControlActivity";

    private Paint mPaint01;
    private Paint cPaint01;
    private Paint mPaint02;
    private Paint cPaint02;
	private RelativeLayout.LayoutParams params;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "RockerTestActivity on create");
		super.onCreate(savedInstanceState);
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		
		mPaint01 = new Paint();
        mPaint01.setAntiAlias(true);
        mPaint01.setDither(true);
        mPaint01.setColor(android.graphics.Color.WHITE);
        mPaint01.setStyle(Paint.Style.STROKE);
        mPaint01.setStrokeJoin(Paint.Join.ROUND);
        mPaint01.setStrokeCap(Paint.Cap.ROUND);
        mPaint01.setStrokeWidth(3);
        
        cPaint01 = new Paint();
        cPaint01.setAntiAlias(true);
        cPaint01.setDither(true);
        cPaint01.setColor(android.graphics.Color.WHITE);
        cPaint01.setStyle(Paint.Style.STROKE);
        cPaint01.setStrokeJoin(Paint.Join.ROUND);
        cPaint01.setStrokeCap(Paint.Cap.ROUND);
        cPaint01.setStrokeWidth(3);
        cPaint01.setStyle(Paint.Style.FILL);
        
        mPaint02 = new Paint();
        mPaint02.setAntiAlias(true);
        mPaint02.setDither(true);
        mPaint02.setColor(android.graphics.Color.GRAY);
        mPaint02.setStyle(Paint.Style.STROKE);
        mPaint02.setStrokeJoin(Paint.Join.ROUND);
        mPaint02.setStrokeCap(Paint.Cap.ROUND);
        mPaint02.setStrokeWidth(3);
        
        cPaint02 = new Paint();
        cPaint02.setAntiAlias(true);
        cPaint02.setDither(true);
        cPaint02.setColor(android.graphics.Color.GRAY);
        cPaint02.setStyle(Paint.Style.STROKE);
        cPaint02.setStrokeJoin(Paint.Join.ROUND);
        cPaint02.setStrokeCap(Paint.Cap.ROUND);
        cPaint02.setStrokeWidth(3);
        cPaint02.setStyle(Paint.Style.FILL);
        
		layout.addView((RelativeLayout)getBaseLayoutInflater().inflate(
				R.layout.rocker_test, null), getBaseLayoutParams());
		
		params=new RelativeLayout.LayoutParams(810,500);
		//距离左边距80dip
		params.leftMargin=100;
		//距离上边距150dip
		params.topMargin=150;
		layout.addView(new MyView(this),params);
		setContentView(layout);	
		setupViews();
		setupListeners();
	}
	private void setupViews(){
		setUserNameEnabled(true);
		setUserIconEnable(true);
		setCancelButtonEnable(false);
		setOkButtonEnable(false);
		setBackButtonEnable(true);
	}
	private void setupListeners(){
		setOnBackButtonTouchListener(new ImageView.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startActivity(new Intent(getApplicationContext(), ModeSelectActivity.class));
					finish();
				}
				return false;
			}
		});
	}
    public class MyView extends View implements Runnable  {
        
        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;
        
        private Bitmap  mBitmap01;
        private Canvas  mCanvas01;
        private Path    mPath01;
        private Paint   mBitmapPaint01;
        
        private Bitmap  mBitmap02;
        private Canvas  mCanvas02;
        private Path    mPath02;
        private Paint   mBitmapPaint02;
        
        public MyView(Context c) {
            super(c);
            this.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            this.setFocusable(true);
            mBitmap01 = Bitmap.createBitmap(400,500, Bitmap.Config.ARGB_8888);
            mCanvas01 = new Canvas(mBitmap01);
            mPath01 = new Path();
            mBitmapPaint01 = new Paint(Paint.DITHER_FLAG);
            
            mBitmap02 = Bitmap.createBitmap(400,500, Bitmap.Config.ARGB_8888);
            mCanvas02 = new Canvas(mBitmap02);
            mPath02 = new Path();
            mBitmapPaint02 = new Paint(Paint.DITHER_FLAG);
        }
        @Override
        public void run() {
        	RefreshHandler mRedrawHandler = new RefreshHandler(Looper.getMainLooper());
            while (!Thread.currentThread().isInterrupted()) {
                  Message m = new Message();
                  m.what = 0x101;
                  mRedrawHandler.sendMessage(m);
                  try {
                       Thread.sleep(100);
                  } catch (InterruptedException e) {
                       e.printStackTrace();
                  }
           }
        }
        class RefreshHandler extends Handler {
        	    public RefreshHandler (Looper looper){
                    super(looper);
                }
                @Override
                public void handleMessage(Message msg) {
                        if (msg.what == 0x101) {
                                MyView.this.invalidate();
                        }
                        super.handleMessage(msg);
                }
        };

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(android.graphics.Color.TRANSPARENT);
            canvas.drawBitmap(mBitmap01,410,0,mBitmapPaint01);
            canvas.drawPath(mPath01, mPaint01);
            canvas.drawCircle(rX01, rY01, 5, cPaint01);
            
            canvas.drawColor(android.graphics.Color.TRANSPARENT);
            canvas.drawBitmap(mBitmap02,0,0,mBitmapPaint02);
            canvas.drawPath(mPath02, mPaint02);
            canvas.drawCircle(rX02, rY02, 5, cPaint02);
        }
        
        private float rX01=200, rY01=210;
        private float rX02=600, rY02=210;
        private float itm = 2;
        
        private void key_down(int key_code){
        	if(0 == key_code){
        		if(rY01-itm>=10){
        		mPath01.moveTo(rX01,rY01);
                mPath01.lineTo(rX01, rY01-itm);
                rY01 = rY01-itm;
        		}
            }
        	if(1 == key_code){
        		if(rY01+itm<=500){
                mPath01.moveTo(rX01,rY01);
                mPath01.lineTo(rX01, rY01+itm);
                rY01 = rY01+itm;
        		}
            }
        	if(2 == key_code){
        		 if(rX01-itm>=10){
        		 mPath01.moveTo(rX01,rY01);
                 mPath01.lineTo(rX01-itm,rY01);
                 rX01 = rX01-itm;
        		 }
            }
        	if(3 == key_code){
        		if(rX01+itm<=395){
        		mPath01.moveTo(rX01,rY01);
                mPath01.lineTo(rX01+itm,rY01);
                rX01 = rX01+itm;
        		}
            }
        	if(4 == key_code){
        		if(rY02-itm>=10){
        		mPath02.moveTo(rX02,rY02);
                mPath02.lineTo(rX02, rY02-itm);
                rY02 = rY02-itm;
        		}
            }
        	if(5 == key_code){
        		if(rY02+itm<=500){
                mPath02.moveTo(rX02,rY02);
                mPath02.lineTo(rX02, rY02+itm);
                rY02 = rY02+itm;
        		}
            }
        	if(6 == key_code){
        		 if(rX02-itm>=415){
        		 mPath02.moveTo(rX02,rY02);
                 mPath02.lineTo(rX02-itm,rY02);
                 rX02 = rX02-itm;
        		 }
            }
        	if(7 == key_code){
        		if(rX02+itm<=800){
        		mPath02.moveTo(rX02,rY02);
                mPath02.lineTo(rX02+itm,rY02);
                rX02 = rX02+itm;
        		}
            }
        }
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
        	if(keyCode == KeyEvent.KEYCODE_W){
     		   key_down(0);
     		   invalidate();
                return (true);
            }
     	   if(keyCode == KeyEvent.KEYCODE_S){
     		   key_down(1);
     		   invalidate();
                return (true);
            }
            if (keyCode == KeyEvent.KEYCODE_A) {
         	   key_down(2);
         	   invalidate();
                return (true);
            }
            if (keyCode == KeyEvent.KEYCODE_D) {
         	   key_down(3);
         	   invalidate();
                return (true);
            }
            if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
     		   key_down(4);
     		   invalidate();
                return (true);
            }
     	   if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
     		   key_down(5);
     		   invalidate();
                return (true);
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
         	   key_down(6);
         	   invalidate();
                return (true);
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
         	   key_down(7);
         	   invalidate();
                return (true);
            }
               return super.onKeyDown(keyCode, event);
           }
    }
}
