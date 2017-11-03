package com.example.ggsddu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ggsddu.adapter.ViewHolderTestAdapter;
import com.example.ggsddu.bean.ViewHolderTestListViewBean;
import com.example.ggsddu.utils.JsonHelper;

public class ViewHolderTestActivity extends Activity{

	public static final String TAG = "ViewHolderTestActivity";
	private ListView mListView;
	private List<String> mListViewDatas = new ArrayList<>();
	private LayoutInflater mLayoutInflater;
//	private TextView mLoadMoreTextView;
	private View mRootView;
	private View mHeadView;
	private static final int MAX_NUM = 50;
	private int mLastVisibleItemIndex;
	private ViewHolderTestAdapter mViewHolderTestAdapter;
	private Handler mHandler;
	private static final String PATH = "http://epg.newtv2.ottcn.com:8080/newtv-2.0-epg/epg/programSeries/getRelationRecommend.json10?icntvId=011234567000005&userId=&programSeriesId=2974879&prefectureId=null&pageNumber=1&pageSize=1000";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewholder_test_activity);
		mLayoutInflater = LayoutInflater.from(this);
		initView();
		initData();
	}

	private void initData() {
		
		ViewHolderTestAsyncTask viewHolderTestAsyncTask = new ViewHolderTestAsyncTask();
		viewHolderTestAsyncTask.execute(PATH);
		
		
	}
	
	
	class ViewHolderTestAsyncTask extends AsyncTask<String, Void, List<ViewHolderTestListViewBean>>{

		@Override
		protected List<ViewHolderTestListViewBean> doInBackground(
				String... params) {
			String jsonString = downLoadJson(params[0]);
			return (List<ViewHolderTestListViewBean>) JsonHelper.getInstance().parseViewHolderTestInfo(jsonString);
		}
		
		@Override
		protected void onPostExecute(List<ViewHolderTestListViewBean> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null && result.size()>0){
				
				mViewHolderTestAdapter = new ViewHolderTestAdapter(ViewHolderTestActivity.this,result);
				mListView.setAdapter(mViewHolderTestAdapter);
			}
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
	}
	

	public String downLoadJson(String string) {
		String jsonString = null ;
		try {
			String line;
			URL url = new URL(string);
			InputStream isInputStream = url.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(isInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while((line = bufferedReader.readLine())!=null){
				jsonString+=line;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	private void initView() {
		
		mHandler = new ViewHolderTestHandler();
		mListView = (ListView) findViewById(R.id.viewholder_test_listview);
//		mHeadView = mLayoutInflater.inflate(R.layout.viewholder_test_listview_headview, null);
//		measureView(mHeadView);
//		mRootView = mLayoutInflater.inflate(R.layout.viewholder_test_listview_rootview, null);
//		mHeadView.setPadding(mHeadView.getPaddingLeft(), -mHeadView.getMeasuredHeight(), mHeadView.getPaddingRight(), mHeadView.getPaddingBottom());
//		mHeadView.invalidate();
//		Log.i(TAG, "mHeadView.getMeasuredHeight()="+mHeadView.getMeasuredHeight());
//		mListView.addHeaderView(mHeadView);
//		mListView.addFooterView(mRootView);
		
		mListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.i(TAG, "onScrollStateChanged---1");
				if(scrollState == SCROLL_STATE_IDLE && mLastVisibleItemIndex == mViewHolderTestAdapter.getCount()){
					Log.i(TAG, "onScrollStateChanged---2");
//					if(mViewHolderTestAdapter.getCount()<MAX_NUM){
//						mRootView.setVisibility(View.VISIBLE);
//						mRootView.postDelayed(new Runnable() {
//							
//							@Override
//							public void run() {
//								Log.i(TAG, "onScrollStateChanged---3");
//								loadMoreData();
//								
//							}
//						}, 2000);
//						
//					} 
				}
				
				if(scrollState == SCROLL_STATE_IDLE && mLastVisibleItemIndex == MAX_NUM-1){
					Toast.makeText(ViewHolderTestActivity.this, "û�и���������", Toast.LENGTH_SHORT).show();
				}
			}
			
			

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				mLastVisibleItemIndex = firstVisibleItem + visibleItemCount -1;
				Log.i(TAG, "mLastVisibleItemIndex="+mLastVisibleItemIndex);
			}
		});
	}
	


	class ViewHolderTestHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
	}
	private void loadMoreData() {
		Log.i(TAG, "loadMoreData");
		if(mViewHolderTestAdapter.getCount()<MAX_NUM - 10){
			for(int i=0;i<10;i++){
				mListViewDatas.add(System.currentTimeMillis()+"����"+mListViewDatas.size());
			}
		} else {
			for(int i=mViewHolderTestAdapter.getCount();i<MAX_NUM;i++){
				Log.i(TAG, "i="+i+"mViewHolderTestAdapter.getCount()"+mViewHolderTestAdapter.getCount());
				mListViewDatas.add(System.currentTimeMillis()+"����"+mListViewDatas.size());
			}
			mListView.removeFooterView(mRootView);
		}
		mViewHolderTestAdapter.notifyDataSetChanged();
		mRootView.setVisibility(View.GONE);
		
	}
	
	
	
	
	
	public int Dp2Px(Context context, float dp) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
	}
	
	// �˷���ֱ���հ��������ϵ�һ������ˢ�µ�demo���˴��ǡ����ơ�headView��width�Լ�height
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

}
