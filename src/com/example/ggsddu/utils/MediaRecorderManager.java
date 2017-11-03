package com.example.ggsddu.utils;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;

public class MediaRecorderManager {

	private static final String TAG = "MediaRecorderManager";
	private static MediaRecorderManager mMediaRecorderManager;
	private static MediaRecorder mMediaRecorder;
	private static File mDir;
	private static String mCurrentFilePath;
	private static Context mContext;
	private static boolean mIsPrepared;
	
	public MediaRecorderManager() {
		
		mDir = mContext.getDir("records",mContext.MODE_PRIVATE);
	}
	public static MediaRecorderManager getInstance(Context context) {
		if(mMediaRecorderManager==null){
			synchronized (MediaRecorderManager.class) {
				if(mMediaRecorderManager==null){
					mContext = context;
					mMediaRecorderManager = new MediaRecorderManager();
				}
			}
		}
		if(!mDir.exists()){
			Log.i(TAG, "mDir.mkdirs()");
			mDir.mkdirs();
		}
		return mMediaRecorderManager;
	}
	
	public String getPath(){
		return mCurrentFilePath;
	}
	public void startRecord(){
		if(mMediaRecorder==null){
			mMediaRecorder = new MediaRecorder();
			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		}
		if(mMediaRecorder!=null){
			Log.i(TAG, "startRecord");
			try {
//				Log.i(TAG, "startRecord---1");
				String fileName = System.currentTimeMillis()+".amr";
				Log.i(TAG, "startRecord---2"+mDir.getAbsolutePath());
				File file = new File(mDir, fileName);
//				Log.i(TAG, "startRecord---3");
				if(!file.exists()){
					Log.i(TAG, "file.createNewFile()");
					file.createNewFile();
				} else {
					Log.i(TAG, "file.exists()");
				}
//				Log.i(TAG, "startRecord---4");
				mCurrentFilePath = file.getAbsolutePath();
				Log.i(TAG, "startRecord---5---"+mCurrentFilePath);
				mMediaRecorder.setOutputFile(file.getAbsolutePath());
//				Log.i(TAG, "startRecord---6");
				
				mMediaRecorder.prepare();
				
//				Log.i(TAG, "startRecord---7");
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mMediaRecorder.start();
			
			
		} else {
			Log.i(TAG, "mMediaRecorder==null");
		}
	}
	
	public void release(){
		if(mMediaRecorder!=null){
			Log.i(TAG, "release");
			mMediaRecorder.stop();
			mMediaRecorder.release();
			mMediaRecorder = null;
			
		}
	}
	
	public void cancel(){
		if(mCurrentFilePath!=null){
			Log.i(TAG, "cancel");
			File file = new File(mCurrentFilePath);
			file.delete();
			mCurrentFilePath = null;
		}
		release();
	}
	
	

	public int getDialogVoiceImageId() {
		if(mMediaRecorder!=null){
			
			int level = 7*mMediaRecorder.getMaxAmplitude()/32768+1;
			int resId = mContext.getResources().getIdentifier("v"+level, "drawable", mContext.getPackageName());
			Log.i(TAG, "refreshVoiceImage---level="+level);
			return resId;
		} else {
			return mContext.getResources().getIdentifier("v1", "drawable", mContext.getPackageName());
		}
	}
}
