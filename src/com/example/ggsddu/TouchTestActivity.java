package com.example.ggsddu;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ggsddu.view.TouchTestLayout;

/**
 * Created by Tom on 2017/10/18.
 */

public class TouchTestActivity extends Activity implements View.OnTouchListener, View.OnClickListener {

    private static final String TAG = "TouchTestActivity";
    private TouchTestLayout mTouchTestLayout;
    private Button mButton1;
    private Button mButton2;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_test_activity);
        Log.i(TAG, "onCreate");
        mTouchTestLayout = (TouchTestLayout) findViewById(R.id.touch_test_layout);
        mButton1 = (Button) findViewById(R.id.touch_test_button1);
        mButton2 = (Button) findViewById(R.id.touch_test_button2);
        mImageView = (ImageView) findViewById(R.id.touch_test_imageview);

        addListener();
    }

    private void addListener() {
    	Log.i(TAG, "addListener");
        mTouchTestLayout.setOnTouchListener(this);
        mTouchTestLayout.setOnClickListener(this);
        mButton1.setOnTouchListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnTouchListener(this);
        mButton2.setOnClickListener(this);
        mImageView.setOnTouchListener(this);
//        mImageView.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,"onTouchEvent event---"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"dispatchTouchEvent event---"+ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;
//        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.touch_test_layout:
                Log.i(TAG, "onTouch: touch_test_layout---"+event.getAction());
                return false;
            case R.id.touch_test_button1:
                Log.i(TAG, "onTouch: touch_test_button1---"+event.getAction());
                return false;
            case R.id.touch_test_button2:
                Log.i(TAG, "onTouch: touch_test_button2---"+event.getAction());
                return false;
            case R.id.touch_test_imageview:
                Log.i(TAG, "onTouch: touch_test_imageview---"+event.getAction());
                return false;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touch_test_layout:
                Log.i(TAG, "onClick: touch_test_layout");
                break;
            case R.id.touch_test_button1:
                Log.i(TAG, "onClick: touch_test_button1");
                break;
            case R.id.touch_test_button2:
                Log.i(TAG, "onClick: touch_test_button2");
                break;
            case R.id.touch_test_imageview:
                Log.i(TAG, "onClick: touch_test_imageview");
                break;
        }
    }
}
