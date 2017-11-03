package com.example.ggsddu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.ggsddu.bean.DownloadFileInfo;
import com.example.ggsddu.service.DownloadService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadTestActivity extends Activity implements OnClickListener {

	private static final String TAG = "DownloadTestActivity";
	private TextView mNameTextView;
	private ProgressBar mProgressBar;
	private Button mStartButton;
	private Button mPauseButton;
	private DownloadFileInfo mDownloadFileInfo;
	private DownloadFileBroadcastReceiver mDownloadFileBroadcastReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_test_activity);

		initView();
		initData();
		mDownloadFileBroadcastReceiver = new DownloadFileBroadcastReceiver();
		registerReceiver(mDownloadFileBroadcastReceiver, new IntentFilter(DownloadService.DOWNLOAD_UPDATE));
	}
	
	private void initData() {
		mDownloadFileInfo = new DownloadFileInfo(0, "http://s006.str.icntvcdn.com/live/5643_cctv3.m3u8", 0,"cctv3.m3u8");
		mNameTextView.setText(mDownloadFileInfo.getName());
	}

	private void initView() {
		mNameTextView = (TextView) findViewById(R.id.download_test_name);
		mProgressBar = (ProgressBar) findViewById(R.id.download_test_progressbar);
		mStartButton = (Button) findViewById(R.id.download_test_start);
		mStartButton.setOnClickListener(this);
		mPauseButton = (Button) findViewById(R.id.download_test_pause);
		mPauseButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.download_test_start:
			Log.i(TAG, "onClick---R.id.download_test_start");
			
			Intent startIntent = new Intent(this, DownloadService.class);
			startIntent.setAction(DownloadService.START_DOWNLOAD);
			startIntent.putExtra(DownloadService.DOWNLOAD_FILEINFO, mDownloadFileInfo);
			startService(startIntent);
			break;

		case R.id.download_test_pause:
			Log.i(TAG, "onClick---R.id.download_test_pause");
			Intent pauseIntent = new Intent(this, DownloadService.class);
			pauseIntent.setAction(DownloadService.PAUSE_DOWNLOAD);
			
			pauseIntent.putExtra(DownloadService.DOWNLOAD_FILEINFO, mDownloadFileInfo);
			startService(pauseIntent);
			break;
		
		}

	}
	
	class DownloadFileBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			int progress = (int) intent.getLongExtra("progress", 0);
			Log.i(TAG, "onReceive---progress="+progress);
			mProgressBar.setProgress(progress);
			if(progress==100){
				mProgressBar.setProgress(0);
//				Intent intent1 = new Intent(Intent.ACTION_VIEW);
//				intent1.setDataAndType(Uri.fromFile(new File(DownloadService.DOWNLOAD_PATH+mDownloadFileInfo.getName())), "application/vnd.android.package-archive");
//				startActivity(intent1);
				
				parseM3U8();
			}
		}

		
	}
	
	private void parseM3U8() {
		Log.i(TAG, "parseM3U8");
		File m3u8File = new File(DownloadService.DOWNLOAD_PATH+mDownloadFileInfo.getName());
		if(m3u8File.exists()){
			Log.i(TAG, "parseM3U8---1");
			try {
				InputStreamReader is = new InputStreamReader(new FileInputStream(m3u8File));
				BufferedReader br = new BufferedReader(is);
				String line;
				Log.i(TAG, "parseM3U8---2");
				while((line=br.readLine())!=null){
					Log.i(TAG, "line="+line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mDownloadFileBroadcastReceiver);
	}
}
