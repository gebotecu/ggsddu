package com.example.ggsddu.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> mViews;
	private View mConvertView;
	private int mPosition;
	public ViewHolder(Context context,ViewGroup parent,int layoutId,int position) {
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
		mConvertView.setTag(this);
		mViews = new SparseArray<View>();
		this.mPosition = position;
	}
	
	public static ViewHolder getViewHolder(Context context,View convertView ,ViewGroup parent,int layoutId,int position) {
		if(convertView==null){
			return new ViewHolder(context, parent, layoutId,position);
		} else {
			ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mPosition = position;
			return viewHolder;
		}

	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int id) {
		
		View view = mViews.get(id);
		if(view==null){
			view = mConvertView.findViewById(id);
			mViews.put(id, view);
		}
		return (T) view;
	}
	
	public View getConvertView() {
		return mConvertView;
	}
	
	public ViewHolder setText(int id,String text) {
		TextView textView = ((TextView)getView(id));
		textView.setText(text);
		return this;
	}
}
