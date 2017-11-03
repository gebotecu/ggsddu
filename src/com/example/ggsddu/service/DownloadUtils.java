package com.example.ggsddu.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ggsddu.bean.DownloadFileInfo;
import com.example.ggsddu.sqlite.DownloadDataBaseHeleper;

public class DownloadUtils {

	public static final String TAG = "DownloadUtils";
	private Context mContext;
	private DownloadFileInfo mDownloadFileInfo;
	public boolean mIsPause;
	private DownloadDataBaseHeleper mDownloadDataBaseHeleper;
	private SQLiteDatabase mSqLiteDatabase;

	public DownloadUtils(Context context, DownloadFileInfo downloadFileInfo) {
		this.mContext = context;
		this.mDownloadFileInfo = downloadFileInfo;
		mDownloadDataBaseHeleper = new DownloadDataBaseHeleper(mContext, null,
				null, 0);
		mSqLiteDatabase = mDownloadDataBaseHeleper.getWritableDatabase();
	}

	public void download() {
		new DownloadThread().start();
	}

	class DownloadThread extends Thread {

		long startPoint;
		@Override
		public void run() {
			Log.i(TAG, "DownloadThread");
			String[] arg = new String[]{mDownloadFileInfo.getUrl()};
			Cursor cursor = mSqLiteDatabase.query(DownloadService.DOWNLOAD_TABLE_NAME, null, "file_url=?", arg, null, null, null);
			if(cursor.getCount()==0){
				startPoint = 0;
			} else {
				cursor.moveToFirst();
				startPoint = cursor.getLong(cursor.getColumnIndex("file_alreadydown"));
			}
			Log.i(TAG, "cursor.getCount()="+cursor.getCount()+"---startPoint="+startPoint);
			cursor.close();
			
			/**
			 * httpclient实现
			 */
//			DefaultHttpClient httpClient;
//			HttpGet httpGet;
//			HttpResponse httpResponse;
//			InputStream is = null;
//			try {
//				httpClient = new DefaultHttpClient();
//				httpGet = new HttpGet(mDownloadFileInfo.getUrl());
//				Header header_size = new BasicHeader("Range", "bytes="+startPoint+"-"
//						+ mDownloadFileInfo.getLength());  
//				httpGet.addHeader(header_size);
//				httpResponse = httpClient.execute(httpGet);
//				if(httpResponse.getStatusLine().getStatusCode()==206){
//					is = httpResponse.getEntity().getContent();
//				}
//			} catch (ClientProtocolException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			/**
			 * httpurlconnection实现
			 */
			URL url;
			HttpURLConnection conn = null;
			RandomAccessFile raf = null;
			InputStream is = null;
			long totalDownSize = startPoint;
			try {
				url = new URL(mDownloadFileInfo.getUrl());
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Range", "bytes="+startPoint+"-"
						+ mDownloadFileInfo.getLength());
				Log.i(TAG, "DownloadThread---1");
				File file = new File(DownloadService.DOWNLOAD_PATH,
						mDownloadFileInfo.getName());
				raf = new RandomAccessFile(file, "rwd");
				raf.seek(startPoint);
//				Log.i(TAG, "DownloadThread---2---" + conn.getResponseCode());
				if (conn.getResponseCode()==HttpURLConnection.HTTP_PARTIAL) {
					is = conn.getInputStream();
					byte[] b = new byte[10 * 1024];
					int len;
					long time = System.currentTimeMillis();
					Log.i(TAG, "DownloadThread---3");
					while ((len = is.read(b)) != -1) {
						if (mIsPause) {
							String[] args = { String.valueOf(mDownloadFileInfo
									.getUrl()) };

							mSqLiteDatabase.delete(
									DownloadService.DOWNLOAD_TABLE_NAME,
									"file_url=?", args);
							// 插入新数据
							ContentValues cv = new ContentValues();
							cv.put("file_id", mDownloadFileInfo.getId());
							cv.put("file_url", mDownloadFileInfo.getUrl());
							cv.put("file_alreadydown", totalDownSize);
							mSqLiteDatabase.insert(DownloadService.DOWNLOAD_TABLE_NAME, null,
									cv);
							return;
						}
						raf.write(b, 0, len);
						totalDownSize += len;
						if (System.currentTimeMillis() - time > 200) {
							time = System.currentTimeMillis();
							if(totalDownSize<mDownloadFileInfo.getLength())
							updateProgressBroadcast(totalDownSize,
									mDownloadFileInfo.getLength());
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				Log.i(TAG, "DownloadThread---finally---totalDownSize="+totalDownSize);
				try {
					conn.disconnect();
					raf.close();
					is.close();
					
					updateProgressBroadcast(totalDownSize,
							mDownloadFileInfo.getLength());
					if(totalDownSize==mDownloadFileInfo.getLength()){
						String[] args = { String.valueOf(mDownloadFileInfo
								.getUrl()) };

						mSqLiteDatabase.delete(
								DownloadService.DOWNLOAD_TABLE_NAME,
								"file_url=?", args);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private void updateProgressBroadcast(long totalDownSize, long fileLength) {
		Intent intent = new Intent(DownloadService.DOWNLOAD_UPDATE);
		intent.putExtra("progress", totalDownSize * 100 / fileLength);
		mContext.sendBroadcast(intent);
	}
}
