package com.example.ggsddu;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewTestActivity extends Activity implements OnPreparedListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnTouchListener, OnClickListener {

	private static final String TAG = "VideoViewTestActivity";
	private VideoView mVideoView;
	private MediaController mMediaController;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview_test_activity);
		
		initVideoView();
	
	}
	@SuppressLint("NewApi")
	private void initVideoView() {
		Log.i(TAG, "initVideoView");
		mVideoView = (VideoView) findViewById(R.id.videoview);
		mVideoView.setOnPreparedListener(this);
		mVideoView.setOnCompletionListener(this);
		mVideoView.setOnErrorListener(this);
		mVideoView.setOnInfoListener(this);
		mVideoView.setOnTouchListener(this);
		mVideoView.setOnClickListener(this);
		Log.i(TAG, "initVideoView---1");
		mMediaController = new MediaController(this);
		mVideoView.setMediaController(mMediaController);
		Log.i(TAG, "initVideoView---2");
		mVideoView.setVideoURI(Uri.parse("http://n2.vod01.icntvcdn.com/newtv2/2017/7/18/2170a24451d2a48de5b3/756bd344ee104349ad84661bfc4bca97_21merge.m3u8"));
		Log.i(TAG, "initVideoView---3");
		mVideoView.start();
		Log.i(TAG, "initVideoView---4");
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.i(TAG, "onTouch");
		return false;
	}
	@Override
	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
		Log.i(TAG, "onInfo---arg1="+arg1+"---arg2="+arg2);
		return false;
	}
	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		Log.i(TAG, "onError---arg1="+arg1+"---arg2="+arg2);
		return false;
	}
	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.i(TAG, "onCompletion");
		
	}
	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i(TAG, "onPrepared");
		
	}
	@Override
	public void onClick(View arg0) {
		Log.i(TAG, "onClick");
		if(mMediaController.isShowing()){
			mMediaController.hide();
		} else {
			
			mMediaController.show();
		}
	}
}
