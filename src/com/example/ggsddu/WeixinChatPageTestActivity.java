package com.example.ggsddu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ggsddu.utils.MediaPlayerManager;
import com.example.ggsddu.view.WeixinChatButton;
import com.example.ggsddu.view.WeixinChatButton.OnRecordCompleteListener;

public class WeixinChatPageTestActivity extends Activity implements OnItemClickListener{

	
	private static final String TAG = "WeixinChatPageTestActivity";
	private WeixinChatButton mWeixinChatButton;
	private ListView mWeixinChatListView;
	private List<ChatBean> mChatBeans;
	private ChatListAdapter mChatListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixin_chat_page_test_activity);
		mWeixinChatButton = (WeixinChatButton) findViewById(R.id.weixin_chat_button);
		mWeixinChatButton.setOnRecordCompleteListener(new OnRecordCompleteListener() {
			
			@Override
			public void onComplete(String path,long length) {
				recordComplete(path,length);
				
			}

		});
		mWeixinChatListView = (ListView) findViewById(R.id.weixin_chat_listview);
		mWeixinChatListView.setOnItemClickListener(this);
		mChatBeans = new ArrayList<>();
		MediaPlayerManager.getInstance().initMediaPlayer(this);
//		mChatListAdapter = new ChatListAdapter(mChatBeans);
	}
	
	private void recordComplete(String path, long length) {
		if(mChatBeans!=null){
			ChatBean chatBean = new ChatBean();
			chatBean.setPath(path);
			chatBean.setLength(length);
			mChatBeans.add(chatBean);
			if(mChatListAdapter==null){
				mChatListAdapter = new ChatListAdapter(mChatBeans);
				mWeixinChatListView.setAdapter(mChatListAdapter);
				
			} else {
				
				mChatListAdapter.notifyDataSetChanged();
			}
//			mWeixinChatListView.setSelection(mChatBeans.size()-1);
			mWeixinChatListView.smoothScrollToPosition(mChatBeans.size()-1);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int actionX = (int) event.getX();
		int actionY = (int) event.getY();
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "onTouchEvent---ACTION_DOWN");

			break;

		case MotionEvent.ACTION_UP:
			Log.i(TAG, "onTouchEvent---ACTION_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "onTouchEvent---ACTION_MOVE");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	class ChatListAdapter extends BaseAdapter{
		private List<ChatBean> chatBeans;
		public ChatListAdapter(List<ChatBean> chatBeans) {
			this.chatBeans = chatBeans;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.chatBeans.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return this.chatBeans.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if(convertView==null){
				Log.i(TAG, "getView---position="+position);
				convertView = LayoutInflater.from(WeixinChatPageTestActivity.this).inflate(R.layout.weixin_chat_listview_item, null);
				viewHolder = new ViewHolder();
				viewHolder.userIcon = (ImageView) convertView.findViewById(R.id.weixin_chat_listview_item_usericon);
				viewHolder.voice = (ImageView) convertView.findViewById(R.id.weixin_chat_listview_item_voice);
				
				viewHolder.voice.setImageResource(R.drawable.weixin_chat_item_voice_anim);
				final AnimationDrawable animationDrawable = (AnimationDrawable) viewHolder.voice.getDrawable();
				viewHolder.chatBg = (ImageView) convertView.findViewById(R.id.weixin_chat_listview_item_chatbg);
				viewHolder.chatBg.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.i(TAG, "viewHolder.chatBg---onClick---position="+position);
						MediaPlayerManager.getInstance().playRecord(chatBeans.get(position).getPath(),new MediaPlayerManager.OnCompletionListener() {
							
							@Override
							public void onCompletion() {
								animationDrawable.stop();
								
							}
						});
						animationDrawable.start();
					}
				});
				viewHolder.length = (TextView) convertView.findViewById(R.id.weixin_chat_listview_item_length);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			LayoutParams layoutParams = viewHolder.chatBg.getLayoutParams();
			layoutParams.width = getChatBgWidth(chatBeans.get(position).getLength());
			viewHolder.chatBg.setLayoutParams(layoutParams);
			viewHolder.length.setText((int)chatBeans.get(position).getLength()/1000+"''");
			return convertView;
		}
		
		class ViewHolder{
			private ImageView userIcon;
			private ImageView voice;
			private ImageView chatBg;
			private TextView length;
		}
		
	}
	
	private int getChatBgWidth(long length){
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int chatBgWidth = (int)length/1000*screenWidth/60+100;
		
		return chatBgWidth;
		
	}
	class ChatBean{
		private String path;
		private long length;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public long getLength() {
			return length;
		}
		public void setLength(long length2) {
			this.length = length2;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i(TAG, "onItemClick---position="+position);
		
	}
}
