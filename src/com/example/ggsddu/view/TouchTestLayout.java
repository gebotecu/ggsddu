package com.example.ggsddu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Tom on 2017/10/18.
 */

public class TouchTestLayout extends RelativeLayout {

    private static  final String TAG = "TouchTestLayout";
    public TouchTestLayout(Context context) {
        super(context);
    }

    
    public TouchTestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}


	public TouchTestLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	@Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: event---"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: event---"+ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: event---"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return false;
//        return true;
    }
}
