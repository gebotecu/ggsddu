package com.example.ggsddu.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggsddu.R;
import com.example.ggsddu.bean.ViewHolderTestListViewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ViewHolderTestAdapter extends BaseAdapter {

	private static final String TAG = "ViewHolderTestAdapter";
	private Context mContext;
	private List<ViewHolderTestListViewBean> datas;
	public ViewHolderTestAdapter(Context context,List<ViewHolderTestListViewBean> datas){
		mContext = context;
		this.datas = datas;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if(convertView==null){
			Log.i(TAG, "convertView==null");
			convertView = LayoutInflater.from(mContext).inflate(R.layout.viewholder_test_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.viewholder_test_item_imageview);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.viewholder_test_item_textview);
			convertView.setTag(viewHolder);
		} else {
			Log.i(TAG, "convertView!=null");
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(this.datas.get(position).getV_picurl(), viewHolder.imageView);
//		viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
		viewHolder.textView.setText(this.datas.get(position).getName());
		return convertView;
	}
	
	class ViewHolder{
		private ImageView imageView;
		private TextView textView;
	}
}
