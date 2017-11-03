package com.example.ggsddu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View{

	private static final String TAG = "DrawView";

	private Paint mPaint;
	private int mCount = 0;
	private Rect mRect;
	private float mPointX = -100;
	private float mPointY = -100;
	public DrawView(Context context) {
		super(context);
		initView(context);
	}


	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	private void initView(Context context) {
//		View view = LayoutInflater.from(context).inflate(R.layout.draw_view, null);
		mPaint = new Paint();
		mRect = new Rect();
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "onClick");
				mCount++;
				invalidate();
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i(TAG, "onDraw---"+this.getId());
		mPaint.setColor(Color.RED);
		canvas.drawCircle(100, 100, 100, mPaint);
		mPaint.setColor(Color.BLACK);
		mPaint.setTextSize(30);
		String text = String.valueOf(mCount);
		mPaint.getTextBounds(text, 0, text.length(), mRect);
		int width = mRect.width();
		int height = mRect.height();
		canvas.drawText(text, getWidth()/2-width/2, getHeight()/2+height/2, mPaint);
		
		mPaint.setColor(Color.BLUE);
		canvas.drawCircle(mPointX, mPointY, 10, mPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			Log.i(TAG, "onTouchEvent---MotionEvent.ACTION_DOWN");
			mPointX = event.getX();
			mPointY = event.getY();
		} else if(event.getAction() == MotionEvent.ACTION_UP){
			Log.i(TAG, "onTouchEvent---MotionEvent.ACTION_UP");	
			mPointX = -100;
			mPointY = -100;
		} else if(event.getAction() == MotionEvent.ACTION_MOVE){
			Log.i(TAG, "onTouchEvent---MotionEvent.ACTION_MOVE");	
			mPointX = event.getX();
			mPointY = event.getY();
			invalidate();
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i(TAG, "onMeasure---widthMeasureSpec="+widthMeasureSpec+"---heightMeasureSpec="+heightMeasureSpec);
//		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//		
//		
//		setMeasuredDimension(widthSize, heightSize);
		
	}
	
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		// TODO Auto-generated method stub
//		super.onLayout(changed, left, top, right, bottom);
//	}
}
