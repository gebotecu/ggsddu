package com.example.ggsddu.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggsddu.R;
import com.example.ggsddu.utils.Constans;
import com.example.ggsddu.utils.MediaRecorderManager;

public class WeixinChatDialogManager {

	private static final String TAG = "WeixinChatDialogManager";
	private Context mContext;
	private Dialog mDialog;
	private LayoutInflater mLayoutInflater;
	private ImageView mMicImageView, mVoiceImageView;
	private TextView mTextView;
	private Timer mTimer = new Timer();
	private TimerTask mTimerTask;
	private WeixinChatDialogManagerHandler mMediaRecorderManagerHandler = new WeixinChatDialogManagerHandler();

	public WeixinChatDialogManager(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		View view = mLayoutInflater.inflate(R.layout.weixin_chat_dialog, null);
		mMicImageView = (ImageView) view
				.findViewById(R.id.weixin_chat_dialog_mic_image);
		mVoiceImageView = (ImageView) view
				.findViewById(R.id.weixin_chat_dialog_voice_image);
		mTextView = (TextView) view
				.findViewById(R.id.weixin_chat_dialog_textview);
//		mDialog = new Dialog(mContext, R.style.CustomDialogStyle);
		mDialog.setContentView(view);
		// Window dialogWindow = mDialog.getWindow();
		// dialogWindow.setGravity(Gravity.CENTER);
		// WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		// ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.dimAmount = 0f;
		// p.height = Utils.Dp2Px(context, 200);
		// p.width = Utils.Dp2Px(context, 200);
		// dialogWindow.setAttributes(p);
	}

	public void showDialog(int state) {
		if (mDialog != null && !mDialog.isShowing()) {
			changeDialogContent(state);
			mDialog.show();

			if (mTimer == null) {
				mTimer = new Timer();
			}

			mTimerTask = new TimerTask() {

				@Override
				public void run() {
					Log.i(TAG,
							"mMediaRecorderManagerHandler.sendEmptyMessage(100)");
					mMediaRecorderManagerHandler.sendEmptyMessage(100);

				}
			};
			mTimer.schedule(mTimerTask, 0,100);

		}

	}

	public void dismissDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
			if (mTimer != null) {
				mTimer.cancel();
				mTimer = null;
			}
		}
	}

	public void changeDialogContent(int state) {
		switch (state) {
		case Constans.NOMAL_STATE:
			mMicImageView.setImageResource(R.drawable.recorder);
			mVoiceImageView.setVisibility(View.VISIBLE);
			mVoiceImageView.setImageResource(R.drawable.v7);
			mTextView.setText("�ɿ�����");
			break;

		case Constans.RECORDING_STATE:
			mMicImageView.setImageResource(R.drawable.recorder);
			mVoiceImageView.setVisibility(View.VISIBLE);
			mVoiceImageView.setImageResource(R.drawable.v7);
			mTextView.setText("�ɿ�����");
			break;
		case Constans.CANCEL_STATE:
			mMicImageView.setImageResource(R.drawable.cancel);
			mVoiceImageView.setVisibility(View.GONE);
			mTextView.setText("�ɿ�ȡ��");
			break;
		case Constans.TOO_SHORT_STATE:
			mMicImageView.setImageResource(R.drawable.voice_to_short);
			mVoiceImageView.setVisibility(View.GONE);
			mTextView.setText("ʱ�����");
			break;
		default:
			break;
		}
	}

	public void refreshVoiceImage(int resId) {
		Log.i(TAG, "refreshVoiceImage");
		if (mVoiceImageView != null) {
			mVoiceImageView.setImageResource(resId);
		}
	}

	class WeixinChatDialogManagerHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 100) {

				refreshVoiceImage(MediaRecorderManager.getInstance(mContext)
						.getDialogVoiceImageId());
			}
		}
	}
}
