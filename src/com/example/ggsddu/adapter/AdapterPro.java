package com.example.ggsddu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ggsddu.utils.ViewHolder;

public abstract class AdapterPro<T> extends BaseAdapter {
	
	private List<T> mDatas;
	private Context mContext;
	private int mLayoutId;
	public AdapterPro(Context context,List<T> datas,int layoutId) {
		mContext = context;
		mDatas = datas;
		mLayoutId = layoutId;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId, position);
		
		convert(position, viewHolder);
		
		return viewHolder.getConvertView();
	}
	
	public abstract void convert(int position, ViewHolder viewHolder);

}
