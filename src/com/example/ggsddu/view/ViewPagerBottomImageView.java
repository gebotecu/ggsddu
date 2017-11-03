package com.example.ggsddu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ViewPagerBottomImageView extends ImageView {
	
	private static final String TAG = "ViewPagerBottomImageView";
	private int mAlpha = 0;
	private Bitmap mOnFocusBitmap;
	private Bitmap mUnFocusBitmap;
	private Paint mPaint;
	public ViewPagerBottomImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	

	public ViewPagerBottomImageView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public ViewPagerBottomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				invalidate();
				
			}
		});
	}

	public void init(int onFocusDrawableId,int unFocusDrawableId) {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mOnFocusBitmap = BitmapFactory.decodeResource(getResources(), onFocusDrawableId);
		mUnFocusBitmap = BitmapFactory.decodeResource(getResources(), unFocusDrawableId);
//		setLayoutParams(new LinearLayout.LayoutParams(mOnFocusBitmap.getWidth(),mOnFocusBitmap.getHeight()));
	}
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "onDraw---"+this.getId()+"---mAlpha="+mAlpha);
		super.onDraw(canvas);
		if(mPaint!=null){
			mPaint.setAlpha(255-mAlpha);
			canvas.drawBitmap(mUnFocusBitmap, null, new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), mPaint);
			mPaint.setAlpha(mAlpha);
			canvas.drawBitmap(mOnFocusBitmap, null, new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), mPaint);
		}
	}
	
	public void setAlpha(int alpha){
		Log.i(TAG, "setAlpha---alpha="+alpha+"---"+this.getId());
		mAlpha = alpha;
		invalidate();
	}
}
