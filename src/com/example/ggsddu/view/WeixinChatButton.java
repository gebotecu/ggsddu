package com.example.ggsddu.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.ggsddu.utils.Constans;
import com.example.ggsddu.utils.MediaRecorderManager;

public class WeixinChatButton extends Button {

	private static final String TAG = "WeixinChatButton";
	private static final int DISMISS_DIALOG = 200;
	private Context mContext;
	private WeixinChatDialogManager mWeixinChatDialogManager;
	private int mState = Constans.NOMAL_STATE;
	private MyHandler mHandler;
	private long mActionDownTime;
	private OnRecordCompleteListener mOnRecordCompleteListener;
	public WeixinChatButton(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}


	public WeixinChatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WeixinChatButton(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mWeixinChatDialogManager = new WeixinChatDialogManager(context);
		mHandler = new MyHandler();
		setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Log.i(TAG, "onLongClick");
				mWeixinChatDialogManager.showDialog(mState);
				return false;
			}
		});
	}
	
	
	public interface OnRecordCompleteListener{
		void onComplete(String path,long length);
	}
	
	public void setOnRecordCompleteListener(OnRecordCompleteListener onRecordCompleteListener){
		mOnRecordCompleteListener = onRecordCompleteListener;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int actionX = (int) event.getX();
		int actionY = (int) event.getY();
		Log.i(TAG, "actionX="+actionX+"---actionY="+actionY);
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "onTouchEvent---ACTION_DOWN");
			mState = Constans.RECORDING_STATE;
			mActionDownTime = System.currentTimeMillis();
			setBackgroundColor(Color.parseColor("#dddddd"));
			setText("�ɿ�����");
			MediaRecorderManager.getInstance(mContext).startRecord();
			
			break;

		case MotionEvent.ACTION_UP:
			Log.i(TAG, "onTouchEvent---ACTION_UP");
			setBackgroundColor(Color.parseColor("#ffffff"));
			setText("��ס˵��");
			long actionUptime = System.currentTimeMillis();
			long length = actionUptime-mActionDownTime;
			if(length<2000){
				mState = Constans.TOO_SHORT_STATE;
				mWeixinChatDialogManager.changeDialogContent(mState);
				MediaRecorderManager.getInstance(mContext).cancel();
			} else {
				MediaRecorderManager.getInstance(mContext).release();
				mOnRecordCompleteListener.onComplete(MediaRecorderManager.getInstance(mContext).getPath(),length);
			}
			if(mHandler!=null){
				mHandler.removeMessages(DISMISS_DIALOG);
				mHandler.sendEmptyMessageDelayed(DISMISS_DIALOG, 300);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "onTouchEvent---ACTION_MOVE");
			if(actionX<0 || actionX>getMeasuredWidth() || actionY<-200 || actionY>getMeasuredHeight()){
				mState = Constans.CANCEL_STATE;
				
				setBackgroundColor(Color.parseColor("#eeeeee"));
				setText("�ɿ�ȡ��");
				
				mWeixinChatDialogManager.changeDialogContent(mState);
			} else {
				mState = Constans.RECORDING_STATE;
				setBackgroundColor(Color.parseColor("#dddddd"));
				setText("�ɿ�����");
				
				mWeixinChatDialogManager.changeDialogContent(mState);				
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DISMISS_DIALOG:
				mWeixinChatDialogManager.dismissDialog();
				mState = Constans.NOMAL_STATE;
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	}
}
