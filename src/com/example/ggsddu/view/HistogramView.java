package com.example.ggsddu.view;



import com.example.ggsddu.R;

import android.R.anim;
import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HistogramView extends View {

	private static final String TAG = "HistogramView";
	private String mTitle;
	private float mTitleSize;
	private float mScaleSize;
	private int mXColor;
	private float mXDegree;
	private int mYColor;
	private float mYDegree;
	
	private int mStartX;
	private int mStartY;
	
	private int mInfoAreaWidth;
	private int mInfoAreaHeight;
	private Paint mPaint;

	private int[][] mInfo;
	public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.HistogramView);
		
		mTitle = typedArray.getString(R.styleable.HistogramView_title);
		mTitleSize = typedArray.getDimension(R.styleable.HistogramView_titlesize, 50);
		mScaleSize = typedArray.getDimension(R.styleable.HistogramView_scalesize, 10);
		mXColor = typedArray.getColor(R.styleable.HistogramView_xcolor, context.getResources().getColor(android.R.color.black));
		mXDegree = typedArray.getDimension(R.styleable.HistogramView_xdegree, 5);
		mYColor = typedArray.getColor(R.styleable.HistogramView_ycolor, context.getResources().getColor(android.R.color.black));
		mYDegree = typedArray.getDimension(R.styleable.HistogramView_ydegree, 5);
		
		if(typedArray!=null)
			typedArray.recycle();
		if(mPaint==null){
			mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setAntiAlias(true);
			mPaint.setColor(Color.BLACK);
		}
	}

	public HistogramView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public HistogramView(Context context) {
		this(context,null);
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i(TAG, "onDraw");
		mStartX = getPaddingLeft()+130;
		mStartY = getHeight()-getPaddingBottom()-150;
		
		mInfoAreaWidth = getWidth()-mStartX-getPaddingRight()-100;
		mInfoAreaHeight = mStartY-getPaddingTop()-100;
		
		drawX(canvas);
		drawY(canvas);
		drawTitle(canvas);
		drawInfo(canvas);
	}
	
	private void drawInfo(Canvas canvas) {
		for(int i=0;i<mInfo.length;i++){
			int[] info = mInfo[i];
			mPaint.setColor(info[1]);
			int left = mStartX+20+i*mInfoAreaWidth/mInfo.length;
			Log.i(TAG, "info[1]="+info[1]);
			Log.i(TAG, "info[1]*1.0f/9000="+info[1]*1.0f/10000);
			Log.i(TAG, "info[1]*1.0f/9000*mInfoAreaHeight="+info[1]*1.0f/10000*mInfoAreaHeight);
			int top = (int) (mStartY-info[0]*1.0f/10000*mInfoAreaHeight);
			int right = mStartX+20+(i+1)*mInfoAreaWidth/mInfo.length;
			int bottom = mStartY;
			Log.i(TAG, "left="+left+"---top="+top+"---right="+right+"---bottom="+bottom);
			canvas.drawRect(left, top, right, bottom, mPaint);
		}
	}

	private void drawTitle(Canvas canvas) {
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(mTitleSize);
		canvas.drawText(mTitle, mStartX, 50, mPaint);
	}

	private void drawX(Canvas canvas) {
		//X轴线
		mPaint.setStrokeWidth(mXDegree);
		mPaint.setColor(mXColor);
		canvas.drawLine(mStartX, mStartY, getWidth()-getPaddingRight()-50, mStartY, mPaint);
		//X轴箭头
		Path path = new Path();
		path.moveTo(getWidth()-getPaddingRight()-20, mStartY);
		path.lineTo(getWidth()-getPaddingRight()-50, mStartY-10);
		path.lineTo(getWidth()-getPaddingRight()-50, mStartY+10);
		path.close();
		canvas.drawPath(path, mPaint);
		//X轴刻度
		mPaint.setTextSize(mScaleSize);
		mPaint.setColor(Color.WHITE);
		for(int i=0;i<mInfo.length;i++){
			String text = "第"+(i+1)+"天";
			canvas.drawText(text, mStartX+20+i*mInfoAreaWidth/mInfo.length+mInfoAreaWidth/mInfo.length/2-mPaint.measureText(text)/2, mStartY+50, mPaint);
		}
		
	}

	private void drawY(Canvas canvas) {
		mPaint.setStrokeWidth(mYDegree);
		mPaint.setColor(mYColor);
		canvas.drawLine(mStartX, mStartY, mStartX, getPaddingTop()+100, mPaint);
		
		Path path = new Path();
		path.moveTo(mStartX, getPaddingTop()+80);
		path.lineTo(mStartX-10, getPaddingTop()+100);
		path.lineTo(mStartX+10, getPaddingTop()+100);
		path.close();
		canvas.drawPath(path, mPaint);
		
		mPaint.setTextSize(mScaleSize);
		for(int i=1;i<10;i++){
			canvas.drawLine(mStartX, mStartY-mInfoAreaHeight/10*i, mStartX+10, mStartY-mInfoAreaHeight/10*i, mPaint);
			String text = i+"000";
			
			canvas.drawText(text, mStartX-mPaint.measureText(text)-30, mStartY-mInfoAreaHeight/10*i, mPaint);
		}
	}

	
	public void setInfo(int[][] info) {
		this.mInfo = info;
	}

	
}
