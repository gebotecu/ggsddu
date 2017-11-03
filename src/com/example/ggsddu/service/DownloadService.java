package com.example.ggsddu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import com.example.ggsddu.bean.DownloadFileInfo;
import com.example.ggsddu.sqlite.DownloadDataBaseHeleper;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class DownloadService extends Service {

	private static final String TAG = "DownloadService";
	public static final String START_DOWNLOAD = "start download";
	public static final String PAUSE_DOWNLOAD = "pause download";
	public static final String DOWNLOAD_FILEINFO = "download fileinfo";
	public static final String DOWNLOAD_UPDATE = "download update";
	public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/downloads/";
	public static final String DOWNLOAD_TABLE_NAME = "downloadtable";
	public static final int INIT_FINISHED = 100;
	private DownLoadServiceHandler mDownLoadServiceHandler;
	private DownloadFileInfo mDownloadFileInfo;
	private DownloadUtils mDownloadUtils;
	
	private long totalDownSize;
	private boolean mIsPause;
	private DownloadDataBaseHeleper mDownloadDataBaseHeleper;
	private SQLiteDatabase mSqLiteDatabase;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mDownLoadServiceHandler = new DownLoadServiceHandler();
		mDownloadDataBaseHeleper = new DownloadDataBaseHeleper(this, null, null, 0);
		mSqLiteDatabase = mDownloadDataBaseHeleper.getWritableDatabase();
		mSqLiteDatabase.execSQL("create table if not exists downloadtable (_id integer primary key autoincrement,file_id,file_url,file_alreadydown);");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.i(TAG, "onStartCommand");
		mDownloadFileInfo = (DownloadFileInfo) intent.getSerializableExtra(DOWNLOAD_FILEINFO);
		switch (intent.getAction()) {
		case START_DOWNLOAD:
			Log.i(TAG, "onStartCommand---START_DOWNLOAD="+mDownloadFileInfo.toString());
//			mDownloadUtils.mIsPause = false;
//			new DownLoadThread(mDownloadFileInfo).start();
			new InitThread(mDownloadFileInfo).start();
			break;

		case PAUSE_DOWNLOAD:
			Log.i(TAG, "onStartCommand---PAUSE_DOWNLOAD="+mDownloadFileInfo.getUrl());
			mDownloadUtils.mIsPause = true;
			break;
		}
		return super.onStartCommand(intent, flags, startId);
	}

	class DownLoadServiceHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == INIT_FINISHED){
				int length = msg.arg1;
				mDownloadFileInfo.setLength(length);
				Log.i(TAG, "mDownloadFileInfo="+mDownloadFileInfo.toString());
				
				mDownloadUtils = new DownloadUtils(DownloadService.this, mDownloadFileInfo);
				mDownloadUtils.download();
			}
		}
	}
	class InitThread extends Thread{
		
		private DownloadFileInfo downloadFileInfo;
		public InitThread(DownloadFileInfo downloadFileInfo) {
			this.downloadFileInfo = downloadFileInfo;
		}
		@Override
		public void run() {
			HttpURLConnection conn = null;
			RandomAccessFile raf = null;
			try {
				Log.i(TAG, "InitThread---1");
				URL url = new URL(downloadFileInfo.getUrl());
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				int fileLength = conn.getContentLength();
				Log.i(TAG, "InitThread---2");
				
				File fileDir = new File(DOWNLOAD_PATH);
				if(!fileDir.exists()){
					fileDir.mkdir();
				}
				Log.i(TAG, "InitThread---3");
				File file = new File(fileDir, downloadFileInfo.getName());
				raf = new RandomAccessFile(file, "rwd");
				Log.i(TAG, "fileLength="+fileLength);
				raf.setLength(fileLength);
				Log.i(TAG, "InitThread---4");
				
				Message msg = mDownLoadServiceHandler.obtainMessage();
				msg.what = INIT_FINISHED;
				msg.arg1 = fileLength;
				Log.i(TAG, "InitThread---5");
				mDownLoadServiceHandler.sendMessage(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					conn.disconnect();
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	class DownLoadThread extends Thread{
		
		private DownloadFileInfo downloadFileInfo;
		
		public DownLoadThread(DownloadFileInfo downloadFileInfo) {
			this.downloadFileInfo = downloadFileInfo;
		}
		@Override
		public void run() {
			DownloadFile();
		}

		private void DownloadFile() {
			Log.i(TAG, "DownloadFile");
			HttpURLConnection connection1 = null;
			InputStream is = null;
			FileOutputStream os = null;
			long fileLength = 0;
			RandomAccessFile raf = null;
			File file = null;
			HttpURLConnection connection = null;
			try {
				Log.i(TAG, "DownloadFile---1");
				URL url1 = new URL(downloadFileInfo.getUrl());
				Log.i(TAG, "DownloadFile---2");
				connection1 = (HttpURLConnection) url1.openConnection();
				connection1.setConnectTimeout(5000);
			    connection1.setRequestMethod("GET");
				fileLength = connection1.getContentLength();
				Log.i(TAG, "DownloadFile---fileLength="+fileLength);
				
				
				URL url = new URL(downloadFileInfo.getUrl());
				connection = (HttpURLConnection) url.openConnection();
				Log.i(TAG, "totalDownSize="+totalDownSize);
				connection.setRequestProperty("Range","bytes="+totalDownSize+"-"+fileLength);
				is = connection1.getInputStream();
				Log.i(TAG, "DownloadFile---3");
				
				File filePath = new File(DownloadService.DOWNLOAD_PATH);
				if(!filePath.exists()){
					filePath.mkdir();
				}
				file = new File(filePath, downloadFileInfo.getName());
				raf = new RandomAccessFile(file, "rwd");
				raf.setLength(fileLength);
				raf.seek(totalDownSize);
//				os = new FileOutputStream(file);
				byte[] b = new byte[4*1024];
				int length;
				long time = System.currentTimeMillis();
				while((length = is.read(b))!=-1 && !mIsPause){
//					Log.i(TAG, "length!=-1");
					raf.write(b,0,length);
					totalDownSize+=length;
					if(System.currentTimeMillis()-time>200){
						time = System.currentTimeMillis();
						//给activity发送广播
						updateProgressBroadcast(fileLength);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i(TAG, "e="+e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i(TAG, "e="+e.toString());
			} finally{
				Log.i(TAG, "DownloadFile---finally");
				updateProgressBroadcast(fileLength);
				String[] args = {String.valueOf(downloadFileInfo.getUrl())};
//				Cursor cursor = mSqLiteDatabase.query(DOWNLOAD_TABLE_NAME, null, "file_url=?", args, null, null, null);
//				Log.i(TAG, "cursor?null="+cursor.getCount());
				
				mSqLiteDatabase.delete(DOWNLOAD_TABLE_NAME, "file_url=?", args);
				if(totalDownSize<fileLength){
					Log.i(TAG, "totalDownSize="+totalDownSize+"---fileLength="+fileLength);
					//插入新数据
					ContentValues cv = new ContentValues();
					cv.put("file_id", downloadFileInfo.getId());
					cv.put("file_url", downloadFileInfo.getUrl());
					cv.put("file_alreadydown",totalDownSize );
					mSqLiteDatabase.insert(DOWNLOAD_TABLE_NAME, null, cv);
				} else {
					totalDownSize = 0;
					updateProgressBroadcast(fileLength);
//					Intent intent = new Intent(Intent.ACTION_VIEW);
//					intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//					startActivity(intent);
				}
//				if(cursor.getCount()==0){
//				} else {
//					
//				}
				try {
					connection.disconnect();
					is.close();
//					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	private void updateProgressBroadcast(long fileLength) {
		Intent intent = new Intent(DOWNLOAD_UPDATE);
		intent.putExtra("progress", totalDownSize*100/fileLength);
		sendBroadcast(intent);
	}
}
