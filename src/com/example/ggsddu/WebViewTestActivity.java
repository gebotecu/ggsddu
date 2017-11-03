package com.example.ggsddu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ggsddu.view.MyProgressbar;

public class WebViewTestActivity extends Activity {

	protected static final String TAG = "WebViewTestActivity";
	private WebView mWebView;
//	private ProgressBar mProgressBar;
	private MyProgressbar mMyProgressbar;
	private NotificationManager mNotificationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_test_activity);
		init();
	}
	private void init() {
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		mProgressBar = (ProgressBar) findViewById(R.id.webview_test_progressbar);
		mMyProgressbar = (MyProgressbar) findViewById(R.id.webview_test_myprogressbar);
		mWebView = (WebView) findViewById(R.id.webview_test_webview);
		mWebView.loadUrl("https://www.wandoujia.com/");
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.i(TAG, "shouldOverrideUrlLoading---url="+url);
				view.loadUrl(url);
				return true;
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Log.i(TAG, "errorCode="+errorCode);
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
			
		});
		
		mWebView.setDownloadListener(new DownloadListener() {
			
			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype, long contentLength) {
				Log.i(TAG, "onDownloadStart---url="+url);
				MyAsycTask myAsycTask = new MyAsycTask();
				myAsycTask.execute(url);
			}
		});
	}
	
	
	@SuppressLint("NewApi")
	private void showNotification() {
		PendingIntent pendingIntent2 = PendingIntent.getActivity(WebViewTestActivity.this, 0,  
				new Intent(WebViewTestActivity.this, MainActivity.class), 0);  
		// 通过Notification.Builder来创建通知，注意API Level  
		// API11之后才支持  
		Notification notify2 = new Notification.Builder(WebViewTestActivity.this)  
		.setSmallIcon(R.drawable.icon) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap  
		// icon)  
		.setTicker("正在下载")// 设置在status  
		// bar上显示的提示文字  
		.setContentTitle("Notification Title")// 设置在下拉status  
		// bar后Activity，本例子中的NotififyMessage的TextView中显示的标题  
		.setContentText("This is the notification message")// TextView中显示的详细内容  
		.setContentIntent(pendingIntent2) // 关联PendingIntent  
		.setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。  
		.getNotification(); // 需要注意build()是在API level  
		// 16及之后增加的，在API11中可以使用getNotificatin()来代替  
		notify2.flags |= Notification.FLAG_AUTO_CANCEL;  
		mNotificationManager.notify(1, notify2);
	}
	class MyAsycTask extends AsyncTask<String, Integer, String>{

		@Override
		protected void onPreExecute() {
			
			mMyProgressbar.setVisibility(View.VISIBLE);
			mMyProgressbar.setProgress(0);
			super.onPreExecute();
			
		}
		@Override
		protected String doInBackground(String... params) {
			showNotification();  
			String urlString = params[0];
			Log.i(TAG, "doInBackground---urlString="+urlString);
			File file = null;
			try {
				URL url = new URL(urlString);
				URLConnection connection = url.openConnection();
				InputStream is = connection.getInputStream();
				float totalSize = connection.getContentLength();
				Log.i(TAG, "doInBackground---totalSize="+totalSize);
				OutputStream os = null;
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					file = new File(Environment.getExternalStorageDirectory(),"wandoujia.apk");
//					if(file.exists()){
					if(false){
						Log.i(TAG, "doInBackground---file.exists()");
						return file.getAbsolutePath();
					}
					Log.i(TAG, "doInBackground---file.getAbsolutePath="+file.getAbsolutePath());
					os = new FileOutputStream(file);
					Log.i(TAG, "doInBackground---Environment.getExternalStorageState()="+Environment.getExternalStorageState());
					Log.i(TAG, "doInBackground---os==null?"+(os==null));
				} else {
					Log.i(TAG, "doInBackground---Environment.getExternalStorageState()="+Environment.getExternalStorageState());
					
				}
				int len;
				float downloadSize = 0;
				byte[] b = new byte[10*1024];
				while((len=is.read(b))!=-1){
					if(os!=null){
						Log.i(TAG, "doInBackground---os.write(b, 0, len)");
						os.write(b, 0, len);
						downloadSize=downloadSize+len;
						publishProgress((int)(downloadSize/totalSize*100));
					}
				}
				
				if(is!=null){
					is.close();
				}
				if(os!=null){
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return file.getAbsolutePath();
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			mMyProgressbar.setProgress(values[0]);
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.i(TAG, "onPostExecute---result="+result);
			mMyProgressbar.setVisibility(View.INVISIBLE);
			
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(result)), "application/vnd.android.package-archive");
			startActivity(intent);
			super.onPostExecute(result);
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(mWebView.canGoBack()){
				mWebView.goBack();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
