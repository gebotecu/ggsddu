package com.example.ggsddu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CarouselImagesViewGroup extends ViewGroup {

	private static final String TAG = "CarouselImagesViewGroup";
	private int childrenCount;
	private int childWidth;
	private int childHeight;
	private int startX;
	private int index;
	public CarouselImagesViewGroup(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CarouselImagesViewGroup(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CarouselImagesViewGroup(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.i(TAG, "onMeasure");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		childrenCount = getChildCount();
		Log.i(TAG, "onMeasure---childrenCount=" + childrenCount);
		if (childrenCount == 0) {
			setMeasuredDimension(0, 0);
			return;
		}
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		View iv = getChildAt(0);
		childWidth = iv.getMeasuredWidth();
		childHeight = iv.getMeasuredHeight();

		Log.i(TAG, "onMeasure---" + childWidth + "---" + childHeight);
		setMeasuredDimension(childWidth * childrenCount, childHeight);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		Log.i(TAG, "onLayout");
		if (arg0) {
			for (int i = 0; i < childrenCount; i++) {
				View iv = getChildAt(i);
				int leftMargin = arg1 + childWidth * i;
				iv.layout(leftMargin, 0, leftMargin + childWidth, childHeight);
			}
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "ACTION_DOWN");
			startX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "ACTION_MOVE");
			int moveX = (int) event.getX();
			int distance = moveX-startX;
			scrollBy(-distance, 0);
			startX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			Log.i(TAG, "ACTION_UP");
			int x = getScrollX();
			if(x<-childWidth/2){
				index = childrenCount-1;
			} else{
				
				index = (x+childWidth/2)/childWidth;
				if(index>childrenCount-1){
					index = 0;
				}
			}
			Log.i(TAG, "x="+x+"---index="+index);
			scrollTo(index*childWidth, 0);
			break;
		default:
			break;
		}
		return true;
	}
}
