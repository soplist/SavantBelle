package com.flextronics.cn.ui;

import java.util.ArrayList;

import com.flextronics.cn.activity.BaseActivity;
import com.flextronics.cn.activity.R;
import com.flextronics.cn.util.Mathematics;
import com.flextronics.cn.util.Point;
import com.flextronics.cn.util.PositionConstants;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;

public class DrawView extends View{
	public static final String TAG="DrawView";
	
    private static final float MINP = 0.25f;
    private static final float MAXP = 0.75f;
    
    private Context c;
    private Paint   drawPaint;
    private Paint   drawCirclePaint;
    private Paint   drawErrorPaint;
    private Bitmap  mBitmap;
    private Canvas  mCanvas;
    private Path    mPath;
    private Paint   mBitmapPaint;
    private Vibrator vibrator;
    //设置蜂鸣时间为500毫秒
    private long[] pattern = {500}; 
    private Chronometer chronometer;
    
    public PositionConstants pc;
    private boolean drawPathSwitch=true;
    private int firstTouch = 0;
    private boolean hasStarted = false;
    private boolean hasFinished = false;
    //画图终端
    private int intermittent = 0;

    public int getIntermittent() {
		return intermittent;
	}

	private static DrawView dv;
    public void setDrawPathSwitchOff(){
    	if(true == drawPathSwitch)
    	    drawPathSwitch = false;
    }
    public void setDrawPathSwitchOn(){
    	if(false == drawPathSwitch)
    	    drawPathSwitch = true;
    }
    public static void clearInstance(){
    	dv = null;
    }
    public static DrawView getDrawViewInstance(Context c){
    	if(dv == null)
    		dv = new DrawView(c);
    	return dv;
    }
    
    private DrawView(Context c){
    	 super(c);
    	 setupPaint();
    	 this.c = c;
         this.setBackgroundColor(android.graphics.Color.TRANSPARENT);
         
         mBitmap = Bitmap.createBitmap(580,520,Bitmap.Config.ARGB_8888);
         mCanvas = new Canvas(mBitmap);
         mPath = new Path();
         mBitmapPaint = new Paint(Paint.DITHER_FLAG);
         vibrator = (Vibrator)((Activity)c).getApplication().getSystemService(Service.VIBRATOR_SERVICE); 
         chronometer = new Chronometer(c);
         pc = new PositionConstants();
         
         int traNo = 1;
         drawBackgroundTrajectory(traNo);
         drawStartPlace((pc.getPointsByTraNo(traNo)).getX(),(pc.getPointsByTraNo(traNo)).getY());
    }
    private void setupPaint(){
    	drawPaint = createPaint(android.graphics.Color.BLACK,5);
		
		drawCirclePaint = createPaint(android.graphics.Color.YELLOW,3);
		
		drawErrorPaint = createPaint(android.graphics.Color.RED,3);
	}
    private Paint createPaint(int color,int strokeWidth){
    	Paint paint = new Paint();
    	paint.setAntiAlias(true);
    	paint.setDither(true);
    	paint.setColor(color);
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeJoin(Paint.Join.ROUND);
    	paint.setStrokeCap(Paint.Cap.ROUND);
    	paint.setStrokeWidth(strokeWidth);
    	return paint;
    }
    private void drawBackgroundTrajectory(int TraNo){
        int imageId = this.getResources().getIdentifier("trajectory_"+TraNo,"drawable",c.getPackageName());
        Log.d(TAG,"image id:"+imageId);
        
        Bitmap img = BitmapFactory.decodeResource(getResources(),imageId); 
        Paint paint = new Paint();
        paint.setAntiAlias(true); 
        mCanvas.drawBitmap(img, 0,0,paint);
    }
    
    private void judgePoints(float x,float y){
    	if(x < 580 && y < 520){
    		int color = mBitmap.getPixel((int)x, (int)y);
    		Log.d(TAG, "current color:"+color);
    		if(color == 0){
    			//-1不重复，非-1为从pattern的指定下标开始重复
    			vibrator.vibrate(pattern, -1);
			    pc.errorPoints.add(new Point(x,y));
    		}else{
    			pc.correctPoints.add(new Point(x,y));
    		}
		}
    }
    public void clearErrorPlace(){
    	drawErrorPaint.setColor(0x80000000);
    }
    public void changePaintColor(){
    	drawPaint.setColor(android.graphics.Color.RED);
    }
    public void drawErrorPlace(){
    	Point point;
    	for(int i=0;i<pc.errorPoints.size();i++){
    	  point = pc.errorPoints.get(i);
    	  mCanvas.drawCircle(point.getX(),point.getY(),2,drawErrorPaint);
    	}
    }
    public ArrayList<Point> getErrorPoints(){
    	if(pc != null)
    		return pc.errorPoints;
    	return null;
    }
    private void drawStartPlace(float x,float y){
    	mCanvas.drawCircle(x, y, 10, drawCirclePaint);
    }
    private void drawFinishedPlace(){
    	drawCirclePaint.setStyle(Paint.Style.FILL);
    	int traNo = 1;
    	mCanvas.drawCircle(pc.getPointsByTraNo(traNo).getX(),pc.getPointsByTraNo(traNo).getY(), 10, drawCirclePaint);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(android.graphics.Color.TRANSPARENT);
        
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        
        canvas.drawPath(mPath, drawPaint);
    }
    
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    
    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, drawPaint);
        // kill this so we don't double draw
        mPath.reset();
    }
    
    //获得定时器时间
    public Chronometer getChronometer(){
		return chronometer;
	}
    private boolean inRightStartPlace(float x,float y){
    	Point p = new Point(x,y);
    	int traNo = 1;
    	float dis = Mathematics.calculateDistanceBetweenTwoPoint(p,pc.getPointsByTraNo(traNo));
    	if(dis < 10 && firstTouch == 0){
    		firstTouch++;
    		hasStarted = true;
    		chronometer.setFormat("计时时间：(%s)");
    		chronometer.start();
    		return true;
    	}
    	return false;
    }
    private boolean inRightFinishedPlace(float x,float y){
    	Point p = new Point(x,y);
    	int traNo = 1;
    	float dis = Mathematics.calculateDistanceBetweenTwoPoint(p,pc.getPointsByTraNo(traNo));
    	if(dis < 10 && firstTouch != 0){
    		hasFinished = true;
    		hasStarted = false;
    		chronometer.stop();
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        
        Log.d(TAG,"x="+x+",y="+y);
        
	    if(true==drawPathSwitch){
           switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	//测试已开始
                	if(hasStarted == true && hasFinished == false){
                	   touch_start(x, y);
                	   judgePoints(x, y);
                       invalidate();
                	}else{
                		//判断是否从规定位置开始
                		if(true == inRightStartPlace(x,y)){
                			touch_start(x, y);
                			judgePoints(x, y);
                            invalidate();
                		}
                	}
                    break;
                case MotionEvent.ACTION_MOVE:
                	if(hasStarted == true && hasFinished == false){
                       touch_move(x, y);
                       judgePoints(x, y);
                       invalidate();
                	}
                    break;
                case MotionEvent.ACTION_UP:
                	if(hasStarted == true && hasFinished == false){
                	   if(true == inRightFinishedPlace(x,y)){
                		   touch_up();
                           invalidate();
                           drawFinishedPlace();
                           //设置显示结果按钮可选
                           ((Activity)c).findViewById(R.id.g_show_result_button).setEnabled(true);
                	   }else{
                	       touch_up();
                           invalidate();
                           intermittent++;
                	   }
                    }
                    break;
             }
	    }
        return true;
    }
}
