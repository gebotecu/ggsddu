package com.example.ggsddu;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggsddu.view.CombinationView;
import com.example.ggsddu.view.ExtendsView;

public class CustomViewTestActivity extends Activity {

	protected static final String TAG = "CustomViewTestActivity";
	private CombinationView mCombinationView;
	private ExtendsView mExtendsView;
	private List<String> mExtendsViewDataList; 
	private ExtendsViewAdapter mExtendsViewAdapter;
	private float mDownX;
	private float mUpX;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_view_test_acitivity);
		initView();
	}
	private void initView() {
		mCombinationView = (CombinationView) findViewById(R.id.combination_view);
		mCombinationView.setLeftImageClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "onClick---combination-view-left-image");
				finish();
			}
		});
		mCombinationView.setRightImageClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "onClick---combination-view-right-image");
				Toast.makeText(CustomViewTestActivity.this, "进入用户中心", Toast.LENGTH_LONG).show();
			}
		});
		
		mExtendsView = (ExtendsView) findViewById(R.id.extends_view);
		mExtendsView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					Log.i(TAG, "onTouch---mExtendsView---MotionEvent.ACTION_DOWN");
				} else if(event.getAction() == MotionEvent.ACTION_MOVE){
					Log.i(TAG, "onTouch---mExtendsView---MotionEvent.ACTION_MOVE");
				} else if(event.getAction() == MotionEvent.ACTION_UP){
					Log.i(TAG, "onTouch---mExtendsView---MotionEvent.ACTION_UP");
					mUpX = event.getX();
				}
				return false;
			}
		});
		mExtendsViewDataList = new ArrayList<>();
		for(int i=0;i<100;i++){
			mExtendsViewDataList.add("item"+i);
		}
		mExtendsViewAdapter = new ExtendsViewAdapter();
		mExtendsView.setAdapter(mExtendsViewAdapter);
	}
	
	class ExtendsViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mExtendsViewDataList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mExtendsViewDataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder viewHolder;
			if(convertView==null){
				convertView = LayoutInflater.from(CustomViewTestActivity.this).inflate(R.layout.extends_view_item, null);
				viewHolder = new ViewHolder();
				viewHolder.textView = (TextView) convertView.findViewById(R.id.extends_view_item_textview);
				viewHolder.button = (Button) convertView.findViewById(R.id.extends_view_item_button);
				viewHolder.horizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.extends_view_item_horizontalscrollview);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.textView.setText(mExtendsViewDataList.get(position));
//			convertView.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					if(event.getAction() == MotionEvent.ACTION_DOWN){
//						Log.i(TAG, "onTouch---MotionEvent.ACTION_DOWN");
//					} else if(event.getAction() == MotionEvent.ACTION_MOVE){
//						Log.i(TAG, "onTouch---MotionEvent.ACTION_MOVE");
//					} else if(event.getAction() == MotionEvent.ACTION_UP){
//						Log.i(TAG, "onTouch---MotionEvent.ACTION_UP");
//					}
//					return false;
//				}
//			});
			
			viewHolder.horizontalScrollView.setOnTouchListener(new OnTouchListener() {
				
				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						Log.i(TAG, "onTouch---horizontalScrollView---MotionEvent.ACTION_DOWN");
						mDownX = event.getX();
					} else if(event.getAction() == MotionEvent.ACTION_MOVE){
						Log.i(TAG, "onTouch---horizontalScrollView---MotionEvent.ACTION_MOVE");
					} else if(event.getAction() == MotionEvent.ACTION_UP){
						Log.i(TAG, "onTouch---horizontalScrollView---MotionEvent.ACTION_UP");
						mUpX = event.getX();
						if(mUpX-mDownX<-50){
							Log.i(TAG, "mUpX-mDownX<-50");
							viewHolder.horizontalScrollView.scrollBy(viewHolder.button.getWidth(),0);
						} else if(mUpX-mDownX>50){
							Log.i(TAG, "mUpX-mDownX>50");
							viewHolder.horizontalScrollView.scrollBy(-viewHolder.button.getWidth(),0);
						} else{
							Log.i(TAG, "mUpX-mDownX<50 && mUpX-mDownX>-50");
							viewHolder.horizontalScrollView.scrollBy((int) (mUpX-mDownX),0);
						}
					}
					return false;
				}
			});
			return convertView;
		}
		
		class ViewHolder{
			private TextView textView;
			private Button button;
			private HorizontalScrollView horizontalScrollView;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			Log.i(TAG, "onTouch---activity---MotionEvent.ACTION_DOWN");
		} else if(event.getAction() == MotionEvent.ACTION_MOVE){
			Log.i(TAG, "onTouch---activity---MotionEvent.ACTION_MOVE");
		} else if(event.getAction() == MotionEvent.ACTION_UP){
			Log.i(TAG, "onTouch---activity---MotionEvent.ACTION_UP");
			 
		}
		return super.onTouchEvent(event);
	}
}
