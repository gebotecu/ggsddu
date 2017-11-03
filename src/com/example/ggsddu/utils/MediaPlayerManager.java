package com.example.ggsddu.utils;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerManager implements MediaPlayer.OnCompletionListener{

	private static MediaPlayerManager mMediaPlayerManager;
	private MediaPlayer mMediaPlayer;
	private Context mContext;
	private OnCompletionListener mOnCompletionListener;
	public static MediaPlayerManager getInstance(){
		if(mMediaPlayerManager==null){
			synchronized (MediaPlayerManager.class) {
				mMediaPlayerManager = new MediaPlayerManager();
			}
		}
		return mMediaPlayerManager;
	}
	
	public synchronized void initMediaPlayer(Context context){
		mContext = context;
	}
	
	public interface OnCompletionListener{
		void onCompletion();
	}
	public void playRecord(String path,OnCompletionListener onCompletionListener){
		try {
			if(mMediaPlayer==null){
				mMediaPlayer = new MediaPlayer();
				mMediaPlayer.setOnCompletionListener(this);
			} else {
				mMediaPlayer.reset();
			}
			mOnCompletionListener = onCompletionListener;
//			mMediaPlayer.release();
//			mMediaPlayer.reset();
//			mMediaPlayer.stop();
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mOnCompletionListener.onCompletion();
		
	}
}
