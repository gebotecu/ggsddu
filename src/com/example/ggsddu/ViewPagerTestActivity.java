package com.example.ggsddu;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ggsddu.view.ViewPagerBottomImageView;
import com.example.ggsddu.view.ViewPagerFragment1;
import com.example.ggsddu.view.ViewPagerFragment2;
import com.example.ggsddu.view.ViewPagerFragment3;
import com.example.ggsddu.view.ViewPagerFragment4;

public class ViewPagerTestActivity extends FragmentActivity implements
		OnClickListener {

	public static final String TAG = "ViewPagerTestActivity";

	private ViewPager mViewPager;

	private LinearLayout mButtomLinearLayout1, mButtomLinearLayout2,
			mButtomLinearLayout3, mButtomLinearLayout4;

	private ViewPagerBottomImageView mButtomImageView1, mButtomImageView2,
			mButtomImageView3, mButtomImageView4;

	private TextView mButtomTextView1, mButtomTextView2, mButtomTextView3,
			mButtomTextView4;
	private int R1;// δѡ�е�Redֵ
	private int G1;// δѡ�е�Greenֵ
	private int B1;// δѡ�е�Blueֵ
	private int R2;// ѡ�е�Redֵ
	private int G2;// ѡ�е�Greenֵ
	private int B2;// ѡ�е�Blueֵ
	private int Rm = R2 - R1;// Red�Ĳ�ֵ
	private int Gm = G2 - G1;// Green�Ĳ�ֵ
	private int Bm = B2 - B1;// Blue�Ĳ�ֵ
	private ViewPagerFragment1 mViewPagerFragment1;
	private ViewPagerFragment2 mViewPagerFragment2;
	private ViewPagerFragment3 mViewPagerFragment3;
	private ViewPagerFragment4 mViewPagerFragment4;

	private List<Fragment> mFragments = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_test_activity);

		initView();
	}

	private void initView() {

		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		mButtomLinearLayout1 = (LinearLayout) findViewById(R.id.view_pager_bottom_item1);
		mButtomLinearLayout2 = (LinearLayout) findViewById(R.id.view_pager_bottom_item2);
		mButtomLinearLayout3 = (LinearLayout) findViewById(R.id.view_pager_bottom_item3);
		mButtomLinearLayout4 = (LinearLayout) findViewById(R.id.view_pager_bottom_item4);

		mButtomImageView1 = (ViewPagerBottomImageView) findViewById(R.id.view_pager_bottom_imageview1);
		mButtomImageView2 = (ViewPagerBottomImageView) findViewById(R.id.view_pager_bottom_imageview2);
		mButtomImageView3 = (ViewPagerBottomImageView) findViewById(R.id.view_pager_bottom_imageview3);
		mButtomImageView4 = (ViewPagerBottomImageView) findViewById(R.id.view_pager_bottom_imageview4);

		mButtomImageView1.init(R.drawable.playbutton_focusing,
				R.drawable.playbutton_unfocused);
		// mButtomImageView1.setImageResource(R.drawable.combination_right_image);
		mButtomImageView2.init(R.drawable.sharebutton_focusing,
				R.drawable.sharebutton_unfocused);
		mButtomImageView3.init(R.drawable.collectbutton_focusing_collected,
				R.drawable.collectbutton_unfocused_uncollected);
		mButtomImageView4.init(R.drawable.actorbutton_focusing,
				R.drawable.actorbutton_unfocused);

		mButtomTextView1 = (TextView) findViewById(R.id.view_pager_bottom_textview1);
		mButtomTextView2 = (TextView) findViewById(R.id.view_pager_bottom_textview2);
		mButtomTextView3 = (TextView) findViewById(R.id.view_pager_bottom_textview3);
		mButtomTextView4 = (TextView) findViewById(R.id.view_pager_bottom_textview4);
		
		mButtomTextView1.setTextColor(Color.RED);
		mButtomTextView2.setTextColor(Color.BLUE);
		mButtomTextView3.setTextColor(Color.BLUE);
		mButtomTextView4.setTextColor(Color.BLUE);
		
		R1 = (Color.RED & 0xff0000) >> 16;
		G1 = (Color.RED & 0xff00) >> 8;
		B1 = (Color.RED & 0xff);
		R2 = (Color.BLUE & 0xff0000) >> 16;
		G2 = (Color.BLUE & 0xff00) >> 8;
		B2 = (Color.BLUE & 0xff);
		Rm = R1 - R2;
		Gm = G1 - G2;
		Bm = B1 - B2;

		mButtomLinearLayout1.setOnClickListener(this);
		mButtomLinearLayout2.setOnClickListener(this);
		mButtomLinearLayout3.setOnClickListener(this);
		mButtomLinearLayout4.setOnClickListener(this);

		mViewPagerFragment1 = new ViewPagerFragment1();
		mViewPagerFragment2 = new ViewPagerFragment2();
		mViewPagerFragment3 = new ViewPagerFragment3();
		mViewPagerFragment4 = new ViewPagerFragment4();

		mFragments.add(mViewPagerFragment1);
		mFragments.add(mViewPagerFragment2);
		mFragments.add(mViewPagerFragment3);
		mFragments.add(mViewPagerFragment4);

		mViewPager
				.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			Log.i(TAG, "onPageScrollStateChanged---arg0=" + arg0);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			Log.i(TAG, "onPageScrolled---arg0=" + arg0 + "---arg1=" + arg1
					+ "---arg2=" + arg2);
			scroll(arg0, arg1);
		}

		@Override
		public void onPageSelected(int arg0) {
			Log.i(TAG, "onPageSelected---arg0=" + arg0);
			resetImageViewsAndTextViews();
			switch (arg0) {
			case 0:
				mButtomImageView1.setAlpha(0);
				break;

			case 1:
				mButtomImageView2.setAlpha(0);
				break;

			case 2:
				mButtomImageView3.setAlpha(0);
				break;

			case 3:
				mButtomImageView4.setAlpha(0);
				break;

			default:
				break;
			}
		}

	}

	class MyFragmentAdapter extends FragmentPagerAdapter {

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return mFragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mFragments.size();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_pager_bottom_item1:
			resetImageViewsAndTextViews();
			mButtomImageView1.setAlpha(255);
			mButtomTextView1.setTextColor(Color.RED);
			mViewPager.setCurrentItem(0);
			break;

		case R.id.view_pager_bottom_item2:
			resetImageViewsAndTextViews();
			mButtomImageView2.setAlpha(255);
			mButtomTextView2.setTextColor(Color.RED);
			mViewPager.setCurrentItem(1);
			break;

		case R.id.view_pager_bottom_item3:
			resetImageViewsAndTextViews();
			mButtomImageView3.setAlpha(255);
			mButtomTextView3.setTextColor(Color.RED);
			mViewPager.setCurrentItem(2);
			break;

		case R.id.view_pager_bottom_item4:
			resetImageViewsAndTextViews();
			mButtomImageView4.setAlpha(255);
			mButtomTextView4.setTextColor(Color.RED);
			mViewPager.setCurrentItem(3);
			break;
		default:
			break;
		}

	}

	public void scroll(int arg0, float arg1) {
		Log.i(TAG, "scroll---arg0=" + arg0 + "---arg1=" + arg1);
		int leftAlpha = (int) (255 * (1 - arg1));
		int rightAlpha = (int) (255 * arg1);
		int leftTextColor = getColorInt(1-arg1);
		int rightTextColor = getColorInt(arg1);
		switch (arg0) {
		case 0:
			mButtomImageView1.setAlpha(leftAlpha);
			mButtomImageView2.setAlpha(rightAlpha);
			mButtomTextView1.setTextColor(leftTextColor);
			mButtomTextView2.setTextColor(rightTextColor);
			break;

		case 1:
			mButtomImageView2.setAlpha(leftAlpha);
			mButtomImageView3.setAlpha(rightAlpha);
			mButtomTextView2.setTextColor(leftTextColor);
			mButtomTextView3.setTextColor(rightTextColor);
			break;

		case 2:
			mButtomImageView3.setAlpha(leftAlpha);
			mButtomImageView4.setAlpha(rightAlpha);
			mButtomTextView3.setTextColor(leftTextColor);
			mButtomTextView4.setTextColor(rightTextColor);
			break;

		default:
			break;
		}

	}

	private void resetImageViewsAndTextViews() {
		mButtomImageView1.setAlpha(0);
		mButtomImageView2.setAlpha(0);
		mButtomImageView3.setAlpha(0);
		mButtomImageView4.setAlpha(0);

		mButtomTextView1.setTextColor(Color.BLUE);
		mButtomTextView2.setTextColor(Color.BLUE);
		mButtomTextView3.setTextColor(Color.BLUE);
		mButtomTextView4.setTextColor(Color.BLUE);
	}

	/**
	 * 192. ƴ����ɫֵ 193.
	 * 
	 * @param f
	 *            194.
	 * @return 195.
	 */

	private int getColorInt(float f) {
		int R = (int) (R2 + f * Rm);
		int G = (int) (G2 + f * Gm);
		int B = (int) (B2 + f * Bm);
		return 0xff << 24 | R << 16 | G << 8 | B;
	}
}
