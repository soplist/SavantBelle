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

public class CooperativeControlActivity extends BaseActivity{
	private final static String TAG = "RockerTestActivity";

    private Paint mPaint;
    private Paint cPaint;
	private RelativeLayout.LayoutParams params;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "RockerTestActivity on create");
		super.onCreate(savedInstanceState);
		//set content view
		RelativeLayout layout = getBaseRelativeLayout();
		
		mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(android.graphics.Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
        
        cPaint = new Paint();
        cPaint.setAntiAlias(true);
        cPaint.setDither(true);
        cPaint.setColor(android.graphics.Color.WHITE);
        cPaint.setStyle(Paint.Style.STROKE);
        cPaint.setStrokeJoin(Paint.Join.ROUND);
        cPaint.setStrokeCap(Paint.Cap.ROUND);
        cPaint.setStrokeWidth(3);
        cPaint.setStyle(Paint.Style.FILL);
        
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
        
        private Bitmap  mBitmap;
        private Canvas  mCanvas;
        private Path    mPath;
        private Paint   mBitmapPaint;
        
        public MyView(Context c) {
            super(c);
            this.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            this.setFocusable(true);
            mBitmap = Bitmap.createBitmap(810,500, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
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
            
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            
            canvas.drawPath(mPath, mPaint);
            
            canvas.drawCircle(rX, rY, 5, cPaint);
        }
        
        private float rX=400, rY=210;
        private float itm = 1;
        
        private void key_down(int key_code){
        	if(0 == key_code){
        		if(rY-itm>=10){
        		mPath.moveTo(rX,rY);
                mPath.lineTo(rX, rY-itm);
                rY = rY-itm;
        		}
            }
        	if(1 == key_code){
        		if(rY+itm<=500){
                mPath.moveTo(rX,rY);
                mPath.lineTo(rX, rY+itm);
                rY = rY+itm;
        		}
            }
        	if(2 == key_code){
        		 if(rX-itm>=10){
        		 mPath.moveTo(rX,rY);
                 mPath.lineTo(rX-itm,rY);
                 rX = rX-itm;
        		 }
            }
        	if(3 == key_code){
        		if(rX+itm<=800){
        		mPath.moveTo(rX,rY);
                mPath.lineTo(rX+itm,rY);
                rX = rX+itm;
        		}
            }
        }
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
               if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
        		   key_down(0);
        		   invalidate();
                   return (true);
               }
        	   if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
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
               return super.onKeyDown(keyCode, event);
           }
    }

}
