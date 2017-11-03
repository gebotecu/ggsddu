package com.example.ggsddu;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.rtp.AudioStream;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MeidaPlayerTestActivity extends Activity implements
		OnClickListener, OnPreparedListener, OnCompletionListener,
		OnErrorListener, OnInfoListener, OnSeekCompleteListener,OnBufferingUpdateListener {

	private static final String TAG = "MeidaPlayerTestActivity";
	private Button mStart;
	private Button mPause;
	private Button mStop;
	private Button mRelease;
	private Button mReset;
	private MediaPlayer mMediaPlayer;

	private FrameLayout mFrameLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer_test_activity);

		initView();
		initData();
	}

	private void initData() {
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.setOnCompletionListener(this);
		mMediaPlayer.setOnErrorListener(this);
		mMediaPlayer.setOnInfoListener(this);
		mMediaPlayer.setAudioStreamType(3);
		SurfaceView surfaceView= new SurfaceView(this);
		final SurfaceHolder surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mFrameLayout.addView(surfaceView);
		surfaceHolder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				Log.i(TAG, "surfaceDestroyed");
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				Log.i(TAG, "surfaceCreated");
				
				mMediaPlayer.setDisplay(surfaceHolder);
				try {
					mMediaPlayer
							.setDataSource("http://n2.vod01.icntvcdn.com/newtv2/2017/7/18/2170a24451d2a48de5b3/756bd344ee104349ad84661bfc4bca97_21merge.m3u8");
					mMediaPlayer.prepareAsync();
					

				} catch (IllegalArgumentException | SecurityException
						| IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				Log.i(TAG, "surfaceChanged---format="+format+"---width="+width+"height="+height);
				
			}
		});
		
		
	}

	private void initView() {
		mFrameLayout = (FrameLayout) findViewById(R.id.mediaplayer_view);
		mStart = (Button) findViewById(R.id.mediaplayer_start);
		mStart.setOnClickListener(this);
		mPause = (Button) findViewById(R.id.mediaplayer_pause);
		mStop = (Button) findViewById(R.id.mediaplayer_stop);
		mPause.setOnClickListener(this);
		mStop.setOnClickListener(this);
		mRelease = (Button) findViewById(R.id.mediaplayer_release);
		mRelease.setOnClickListener(this);
		mReset = (Button) findViewById(R.id.mediaplayer_reset);
		mReset.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mediaplayer_start:
			if(mMediaPlayer!=null){
				mMediaPlayer.start();
			}
			break;
		case R.id.mediaplayer_pause:
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
			} else {
				mMediaPlayer.start();
			}
			break;
		case R.id.mediaplayer_stop:
			mMediaPlayer.stop();
			break;

		case R.id.mediaplayer_release:
			mMediaPlayer.release();
			break;

		case R.id.mediaplayer_reset:
			mMediaPlayer.release();
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		Log.i(TAG, "onInfo---what=" + what + "---extra=" + extra);
		return false;
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.i(TAG, "onError---what=" + what + "---extra=" + extra);
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.i(TAG, "onCompletion");

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i(TAG, "onPrepared");
		if(mMediaPlayer!=null){
			mMediaPlayer.start();
		}
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		Log.i(TAG, "onBufferingUpdate---percent"+percent);
		
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		Log.i(TAG, "onSeekComplete");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(mMediaPlayer!=null){
			mMediaPlayer.release();
		}
	}
}
