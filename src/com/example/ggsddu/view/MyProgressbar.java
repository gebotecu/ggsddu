package com.example.ggsddu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.ggsddu.R;

public class MyProgressbar extends ProgressBar {

	private static final String TAG = "MyProgressbar";
	private int mLeftLineHeight;
	private int mLeftLineColor;
	private int mRightLineHeight;
	private int mRightLineColor;
	private int mTextSize;
	private int mTextColor;
	private int mBitmapWidth;
	private int mBitmapHeight;
	private Paint mPaint;
	private Rect mRect;
	private Bitmap mBitmap;

	public MyProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.MyProgressbar);
		mLeftLineHeight = (int) ta.getDimension(
				R.styleable.MyProgressbar_leftlineheight, sp2px(15));
		mLeftLineColor = ta.getColor(R.styleable.MyProgressbar_leftlinecolor,
				Color.RED);
		mRightLineHeight = (int) ta.getDimension(
				R.styleable.MyProgressbar_rightlineheight, sp2px(10));
		mRightLineColor = ta.getColor(R.styleable.MyProgressbar_rightlinecolor,
				Color.GREEN);
		mTextSize = (int) ta.getDimension(R.styleable.MyProgressbar_textsize,
				sp2px(10));
		mTextColor = (int) ta.getColor(R.styleable.MyProgressbar_textcolor,
				Color.BLUE);
		mBitmapWidth = (int) ta.getDimension(
				R.styleable.MyProgressbar_bitmapwidth, dp2px(15));
		mBitmapHeight = (int) ta.getDimension(
				R.styleable.MyProgressbar_bitmapheight, dp2px(15));
		Log.i(TAG, "mLeftLineHeight=" + mLeftLineHeight);
		Log.i(TAG, "mRightLineHeight=" + mRightLineHeight);
		Log.i(TAG, "mTextSize=" + mTextSize);
		Log.i(TAG, "mBitmapWidth=" + mBitmapWidth);
		ta.recycle();

		mPaint = new Paint();
		mRect = new Rect();
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	}

	public MyProgressbar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyProgressbar(Context context) {
		this(context, null);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		Log.i(TAG, "onDraw---getMeasuredWidth=" + getMeasuredWidth());
		String text = getProgress() + "%";
		mPaint.getTextBounds(text, 0, text.length(), mRect);
		// draw left line
		// canvas.translate(getPaddingLeft(), getHeight()/2);
		mPaint.setColor(mLeftLineColor);
		mPaint.setStrokeWidth(mLeftLineHeight);
		canvas.drawLine(getPaddingLeft(), getHeight() / 2, getProgress() * 1.0f
				/ getMax() * getWidth() - mRect.width(), getHeight() / 2,
				mPaint);

		// draw text
		// mPaint.setColor(mTextColor);
		// mPaint.setTextSize(mTextSize);
		// canvas.drawText(text,
		// getProgress()*1.0f/getMax()*getWidth()-mRect.width(), getHeight() /
		// 2+mRect.height()/2, mPaint);

		// drawbitmap
		if (getProgress() * 1.0f / getMax() * getWidth() > mBitmapWidth) {

			canvas.drawBitmap(mBitmap, null, new Rect((int) (getProgress()
					* 1.0f / getMax() * getWidth() - mBitmapWidth), getHeight()
					/ 2 - mBitmapHeight / 2, (int) (getProgress() * 1.0f
					/ getMax() * getWidth()), getHeight() / 2 + mBitmapHeight
					/ 2), mPaint);
		} else {
			canvas.drawBitmap(mBitmap, null, new Rect(0, getHeight() / 2
					- mBitmapHeight / 2, mBitmapWidth, getHeight() / 2
					+ mBitmapHeight / 2), mPaint);

		}

		// draw right line
		mPaint.setColor(mRightLineColor);
		mPaint.setStrokeWidth(mRightLineHeight);
		if (getProgress() * 1.0f / getMax() * getWidth() > mBitmapWidth) {

			canvas.drawLine(getProgress() * 1.0f / getMax() * getWidth(),
					getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
		} else {
			canvas.drawLine(mBitmapWidth,
					getHeight() / 2, getWidth(), getHeight() / 2, mPaint);

		}
		canvas.drawLine(getProgress() * 1.0f / getMax() * getWidth(),
				getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		Log.i(TAG, "onMeasure---widthSize="+MeasureSpec.getSize(widthMeasureSpec)+"---heightSize="+MeasureSpec.getSize(heightMeasureSpec));
		
		int height = measureHeight(heightMeasureSpec);
		Log.i(TAG, "onMeasure---height="+height);
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
	}
	private int measureHeight(int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		if(heightMode==MeasureSpec.EXACTLY){
			Log.i(TAG, "heightMode==MeasureSpec.EXACTLY");
			return heightSize;
		} else if(heightMode==MeasureSpec.AT_MOST){
			Log.i(TAG, "heightMode==MeasureSpec.AT_MOST");
			return Math.min(heightSize, Math.max(mBitmapHeight,Math.max(mRect.height(),Math.max(mLeftLineHeight, mRightLineColor))));
		} else {
			Log.i(TAG, "heightMode==MeasureSpec.UNSPECIFIED");
			return Math.max(mBitmapHeight,Math.max(mRect.height(),Math.max(mLeftLineHeight, mRightLineColor)));
		}

	}

	private float sp2px(int i) {
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, i,
				getResources().getDisplayMetrics());
		return px;
	}

	private float dp2px(int i) {
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i,
				getResources().getDisplayMetrics());
		return px;
	}

}
