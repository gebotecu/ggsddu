package com.example.ggsddu.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.example.ggsddu.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FreeStyleListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Integer> mDatas;
	private LayoutInflater mLayoutInflater;
	public FreeStyleListViewAdapter(Context context, List<Integer> datas) {
		mContext = context;
		mDatas = datas;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mDatas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public int getItemViewType(int position) {
		if (position % 2 == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		ViewHolder1 viewHolder1;
		ViewHolder2 viewHolder2;
		if(convertView==null){
			if(type == 0){
				viewHolder1 = new ViewHolder1();
				convertView = mLayoutInflater.inflate(R.layout.freestyle_listview_item1, null);
				viewHolder1.textView = (TextView) convertView.findViewById(R.id.textview);
				viewHolder1.imageView = (ImageView) convertView.findViewById(R.id.imageview);
				convertView.setTag(viewHolder1);
			} else {
				viewHolder2 = new ViewHolder2();
				convertView = mLayoutInflater.inflate(R.layout.freestyle_listview_item2, null);
				viewHolder2.textView = (TextView) convertView.findViewById(R.id.textview);
				viewHolder2.imageView1 = (ImageView) convertView.findViewById(R.id.imageview1);
				viewHolder2.imageView2 = (ImageView) convertView.findViewById(R.id.imageview2);
				convertView.setTag(viewHolder2);
			}
		}
		if(type==0){
			
			viewHolder1 = (ViewHolder1) convertView.getTag();
			viewHolder1.textView.setText("type0---position:"+mDatas.get(position));
		} else {
			viewHolder2 = (ViewHolder2) convertView.getTag();
			viewHolder2.textView.setText("type1---position:"+mDatas.get(position));
		}
			
		return convertView;
	}

	class ViewHolder1 {

		private TextView textView;
		private ImageView imageView;
	}

	class ViewHolder2 {

		private TextView textView;
		private ImageView imageView1;
		private ImageView imageView2;
	}
}
