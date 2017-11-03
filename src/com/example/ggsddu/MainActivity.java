package com.example.ggsddu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.ggsddu.utils.NotificationUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	private Button mViewHolderTestButton;
	private Button mSqliteTestButton;
	private Button mCustomViewTestButton;
	private Button mViewPagerTestButton;
	private Button mWeixinChatPageTestButton;
	private Button mRecyclerViewTestButton;
	private Button mAdapterProButton;
	private Button mAnimationButton;
	private Button mZxingButton;
	private Button mWebViewButton;
	private Button mBaiduMapButton;
	private Button mMobButton;
	private Button mDownLoadButton;
	private Button mPinTuButton;
	private Button mMediaPlayerButton;
	private Button mVideoViewButton;
	private Button mCarouselImagesButton;
	private Button mHistogramButton;
	private Button mFreeStyleButton;
	private Button mTouchTestButton;
	private Button mGaodeMapButton;

	private MyBroadcastReceiver mMyBroadcastReceiver;
	private MyBroadcastReceiver1 mMyBroadcastReceiver1;
	private NotificationUtils mNotificationUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "change from as");
		initViews();
		mMyBroadcastReceiver = new MyBroadcastReceiver();
		registerReceiver(mMyBroadcastReceiver, new IntentFilter(
				"com.changhong.video.history"));
		mMyBroadcastReceiver1 = new MyBroadcastReceiver1();
		registerReceiver(mMyBroadcastReceiver1, new IntentFilter(
				"com.changhong.video.favourite"));
		mNotificationUtils = new NotificationUtils(this);
		// sha1("cpCode=SOHUTVtId=004de666-551f-11e0b474-a4badb4689bcinputTime=20130618");
	}

	private void initViews() {
		ImageLoaderConfiguration imageLoaderConfiguration = ImageLoaderConfiguration
				.createDefault(this);
		ImageLoader.getInstance().init(imageLoaderConfiguration);
		mViewHolderTestButton = (Button) findViewById(R.id.viewholder_test);
		mViewHolderTestButton.setOnClickListener(this);
		mSqliteTestButton = (Button) findViewById(R.id.sqlite_test);
		mSqliteTestButton.setOnClickListener(this);
		mCustomViewTestButton = (Button) findViewById(R.id.custom_view_test);
		mCustomViewTestButton.setOnClickListener(this);
		mViewPagerTestButton = (Button) findViewById(R.id.viewpager_test);
		mViewPagerTestButton.setOnClickListener(this);
		mWeixinChatPageTestButton = (Button) findViewById(R.id.weixin_chat_page_test);
		mWeixinChatPageTestButton.setOnClickListener(this);
		mRecyclerViewTestButton = (Button) findViewById(R.id.recyclerview_test);
		mRecyclerViewTestButton.setOnClickListener(this);
		mAdapterProButton = (Button) findViewById(R.id.adapter_pro_test);
		mAdapterProButton.setOnClickListener(this);
		mAnimationButton = (Button) findViewById(R.id.animation_test);
		mAnimationButton.setOnClickListener(this);
		// mZxingButton = (Button) findViewById(R.id.zxing_test);
		// mZxingButton.setOnClickListener(this);
		mWebViewButton = (Button) findViewById(R.id.webview_test);
		mWebViewButton.setOnClickListener(this);
		mBaiduMapButton = (Button) findViewById(R.id.baidumap_test);
		mBaiduMapButton.setOnClickListener(this);
		mMobButton = (Button) findViewById(R.id.mob_test);
		mMobButton.setOnClickListener(this);
		mDownLoadButton = (Button) findViewById(R.id.download_test);
		mDownLoadButton.setOnClickListener(this);
		mPinTuButton = (Button) findViewById(R.id.pintu_test);
		mPinTuButton.setOnClickListener(this);
		mMediaPlayerButton = (Button) findViewById(R.id.mediaplayer_test);
		mMediaPlayerButton.setOnClickListener(this);
		mVideoViewButton = (Button) findViewById(R.id.videoview_test);
		mVideoViewButton.setOnClickListener(this);
		mCarouselImagesButton = (Button) findViewById(R.id.carousel_images_test);
		mCarouselImagesButton.setOnClickListener(this);
		mHistogramButton = (Button) findViewById(R.id.histogram_test);
		mHistogramButton.setOnClickListener(this);
		mFreeStyleButton = (Button) findViewById(R.id.freestyle_test);
		mFreeStyleButton.setOnClickListener(this);
		mTouchTestButton = (Button) findViewById(R.id.touch_test);
		mTouchTestButton.setOnClickListener(this);
		mGaodeMapButton = (Button) findViewById(R.id.gaode_map);
		mGaodeMapButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		mNotificationUtils.showNotification((String) ((Button) v).getText());
		switch (v.getId()) {
		case R.id.viewholder_test:
			Log.i(TAG, "onClick---R.id.viewholder_test");
			Intent intent = new Intent();
			intent.setClass(this, ViewHolderTestActivity.class);
			startActivity(intent);
			break;

		case R.id.sqlite_test:
			Log.i(TAG, "onClick---R.id.sqlite_test");
			Intent intent1 = new Intent();
			intent1.setClass(this, SqliteTestActivity.class);
			startActivity(intent1);
			break;

		case R.id.custom_view_test:
			Log.i(TAG, "onClick---R.id.sqlite_test");
			Intent intent2 = new Intent();
			intent2.setClass(this, CustomViewTestActivity.class);
			startActivity(intent2);
			break;

		case R.id.viewpager_test:
			Log.i(TAG, "onClick---R.id.sqlite_test");
			Intent intent3 = new Intent();
			intent3.setClass(this, ViewPagerTestActivity.class);
			startActivity(intent3);
			break;

		case R.id.weixin_chat_page_test:
			Log.i(TAG, "onClick---R.id.weixin_chat_page_test");
			Intent intent4 = new Intent();
			intent4.setClass(this, WeixinChatPageTestActivity.class);
			startActivity(intent4);
			break;

		case R.id.recyclerview_test:
			Log.i(TAG, "onClick---R.id.weixin_chat_page_test");
			Intent intent5 = new Intent();
			intent5.setClass(this, RecyclerViewTestActivity.class);
			startActivity(intent5);
			break;

		case R.id.adapter_pro_test:
			Log.i(TAG, "onClick---R.id.adapter_pro_test");
			Intent intent6 = new Intent();
			intent6.setClass(this, AdapterProTestActivity.class);
			startActivity(intent6);
			break;

		case R.id.animation_test:
			Log.i(TAG, "onClick---R.id.adapter_pro_test");
			Intent intent7 = new Intent();
			intent7.setClass(this, AnimationsTestActivity.class);
			startActivity(intent7);
			break;

		// case R.id.zxing_test:
		// Log.i(TAG, "onClick---R.id.zxing_test");
		// Intent intent8 = new Intent();
		// intent8.setClass(this, ZXingTestActivity.class);
		// startActivity(intent8);
		// break;

		case R.id.webview_test:
			Log.i(TAG, "onClick---R.id.webview_test");
			Intent intent9 = new Intent();
			intent9.setClass(this, WebViewTestActivity.class);
			startActivity(intent9);
			break;

		case R.id.baidumap_test:
			Log.i(TAG, "onClick---R.id.baidumap_test");
			Intent intent10 = new Intent();
			intent10.setClass(this, BaiduMapTestActivity.class);
			startActivity(intent10);
			break;

		case R.id.mob_test:
			Log.i(TAG, "onClick---R.id.mob_test");
			Intent intent11 = new Intent();
			intent11.setClass(this, MobTestActivity.class);
			startActivity(intent11);
			break;

		case R.id.download_test:
			Log.i(TAG, "onClick---R.id.download_test");
			Intent intent12 = new Intent();
			intent12.setClass(this, DownloadTestActivity.class);
			startActivity(intent12);
			break;

		case R.id.pintu_test:
			Log.i(TAG, "onClick---R.id.pintu_test");
			Intent intent13 = new Intent();
			intent13.setClass(this, PinTuActivity.class);
			startActivity(intent13);
			break;

		case R.id.mediaplayer_test:
			Log.i(TAG, "onClick---R.id.mediaplayer_test");
			Intent intent14 = new Intent();
			intent14.setClass(this, MeidaPlayerTestActivity.class);
			startActivity(intent14);
			break;

		case R.id.videoview_test:
			Log.i(TAG, "onClick---R.id.videoview_test");
			Intent intent15 = new Intent();
			intent15.setClass(this, VideoViewTestActivity.class);
			startActivity(intent15);
			break;

		case R.id.carousel_images_test:
			Log.i(TAG, "onClick---R.id.carousel_images_test");
			Intent intent16 = new Intent();
			intent16.setClass(this, CarouselImagesActivity.class);
			startActivity(intent16);
			break;

		case R.id.histogram_test:
			Log.i(TAG, "onClick---R.id.histogram_test");
			Intent intent17 = new Intent();
			intent17.setClass(this, HistogramActivity.class);
			startActivity(intent17);
			break;

		case R.id.freestyle_test:
			Log.i(TAG, "onClick---R.id.freestyle_test");
			Intent intent18 = new Intent();
			intent18.setClass(this, FreeStyleActivity.class);
			startActivity(intent18);
			break;

		case R.id.touch_test:
			Log.i(TAG, "onClick---R.id.touch_test");
			Intent intent19 = new Intent();
			intent19.setClass(this, TouchTestActivity.class);
			startActivity(intent19);
			break;

			case R.id.gaode_map:
				Log.i(TAG, "onClick---R.id.gaode_map");
				Intent intent20 = new Intent();
				intent20.setClass(this, GaodeMapActivity.class);
				startActivity(intent20);
				break;
		default:
			break;
		}

	}

	class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int action = intent.getIntExtra("action", 0);
			int k0 = intent.getIntExtra("k0", 0);
			String k1 = intent.getStringExtra("k1");
			Log.i(TAG, "onReceive---action=" + action + "---k0=" + k0);
			Log.i(TAG, "onReceive---k1=" + k1);
		}

	}

	class MyBroadcastReceiver1 extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int action = intent.getIntExtra("action", 0);
			int k0 = intent.getIntExtra("k0", 0);
			String k1 = intent.getStringExtra("k1");
			Log.i(TAG, "onReceive---action=" + action + "---k0=" + k0);
			Log.i(TAG, "onReceive---k1=" + k1);
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		unregisterReceiver(mMyBroadcastReceiver);
	}

}
