package com.example.ggsddu;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.view.View;

import com.example.ggsddu.adapter.RecyclerViewAdapter;
import com.example.ggsddu.adapter.RecyclerViewAdapter.OnItemClickListener;
import com.example.ggsddu.bean.RecyclerViewTestBean;
import com.example.ggsddu.view.CustomRecyclerView;
import com.example.ggsddu.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewTestActivity extends Activity {

	protected static final String TAG = "RecyclerViewTestActivity";
	private CustomRecyclerView mRecyclerView;
	private List<RecyclerViewTestBean> mRecyclerViewTestBeans;
	private RecyclerViewAdapter mRecyclerViewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recyclerview_test_activity);
		initView();
	}
	private void initView() {
		mRecyclerView = (CustomRecyclerView) findViewById(R.id.recyclerview);
//		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, StaggeredGridLayoutManager.VERTICAL, false);
//		layoutManager.setOrientation(GridLayoutManager.VERTICAL);
//		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
//		layoutManager.setSpanSizeLookup(new SpanSizeLookup() {
//			
//			@Override
//			public int getSpanSize(int arg0) {
//				if(arg0%4==0){
//					return 1;
//				} else {
//					return 1;
//				}
//			}
//		});
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
//		mRecyclerView.setOnScrollListener(new MyOnScrollListener());
		mRecyclerViewTestBeans = new ArrayList<>();
		for(int i=0;i<10;i++){
			RecyclerViewTestBean recyclerViewTestBean = new RecyclerViewTestBean();
			recyclerViewTestBean.setCount(i);
			int type = new Random().nextInt(3)+1;
			recyclerViewTestBean.setType(type);
			mRecyclerViewTestBeans.add(recyclerViewTestBean);
		}
		mRecyclerViewAdapter = new RecyclerViewAdapter(this,mRecyclerViewTestBeans);
		mRecyclerView.setAdapter(mRecyclerViewAdapter);
		mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, RecyclerViewTestBean recyclerViewTestBean) {
				Log.i(TAG, "recyclerViewTestBean.getCount="+recyclerViewTestBean.getCount());
				if(recyclerViewTestBean.getCount()==2){
					mRecyclerViewTestBeans.add(5,recyclerViewTestBean);
//					mRecyclerViewAdapter.notifyItemInserted(5);
					mRecyclerViewAdapter.notifyDataSetChanged();
				}
			}
		});
//		mRecyclerView.addItemDecoration(new MyItemDecoration(10, 20));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
	}
	
	class MyOnScrollListener extends OnScrollListener{
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			Log.i(TAG, "onScrolled");
		}
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
			Log.i(TAG, "onScrollStateChanged---"+newState);
			if(newState==0){
				int[] location = new int[2];
				recyclerView.getFocusedChild().getLocationInWindow(location);
				Log.i(TAG, "location="+location[0]+"---"+location[1]);
			}
		}
	}
	class MyItemDecoration extends ItemDecoration{
		private int right;
		private int bottom;
		public MyItemDecoration(int right,int bottom) {
			this.right = right;
			this.bottom = bottom;
		}
		
		@Override
		public void getItemOffsets(Rect outRect, View view,
				RecyclerView parent, State state) {
			outRect.right = this.right;
			outRect.bottom = this.bottom;
		}
		
		
	}
}
