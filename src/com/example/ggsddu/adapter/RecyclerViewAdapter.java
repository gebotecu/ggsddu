package com.example.ggsddu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.ggsddu.R;
import com.example.ggsddu.bean.RecyclerViewTestBean;

import java.util.List;

public class RecyclerViewAdapter extends
		Adapter<RecyclerView.ViewHolder> implements OnClickListener {
	private static final String TAG = "RecyclerViewAdapter";
	private Context mContext;
	private List<RecyclerViewTestBean> mRecyclerViewTestBeans;
	private OnItemClickListener mOnItemClickListener;

	public RecyclerViewAdapter(Context context,
			List<RecyclerViewTestBean> recyclerViewTestBeans) {
		mContext = context;
		mRecyclerViewTestBeans = recyclerViewTestBeans;
	}

	public static interface OnItemClickListener {
		void onItemClick(View view, RecyclerViewTestBean recyclerViewTestBean);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mRecyclerViewTestBeans.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
//		arg0.textView.setText(mRecyclerViewTestBeans.get(arg1).getCount() + "");
//		Log.i(TAG, "arg1=" + arg1
//				+ "mContext.getResources().getDisplayMetrics().density="
//				+ mContext.getResources().getDisplayMetrics().density);
//		arg0.textView.setWidth((int) (mContext.getResources()
//				.getDisplayMetrics().density * 60));
//		arg0.itemView.setTag(mRecyclerViewTestBeans.get(arg1));
		
		((AbstractViewHolder)arg0).bindview();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
//		View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item1, arg0,false);
//		view.setOnClickListener(this);
//		ViewHolder viewHolder = new ViewHolder(view);
		
		switch (arg1) {
		case 1:
			return new TypeOneViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item1, arg0,false));
		case 2:
			return new TypeTwoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item2, arg0,false));
			
		case 3:
			return new TypeThreeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item3, arg0,false));
		}
		return null;
	}
	
//	class ViewHolder extends RecyclerView.ViewHolder{
//		
//		private TextView textView;
//		public ViewHolder(View arg0) {
//			super(arg0);
//			textView = (TextView) arg0.findViewById(R.id.recyclerview_item_textview);
//		}
//		
//	}

	@Override
	public void onClick(View v) {
		Log.i(TAG,
				"onClick---position="
						+ ((RecyclerViewTestBean) v.getTag()).getCount());
		mOnItemClickListener.onItemClick(v, (RecyclerViewTestBean) v.getTag());

	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return mRecyclerViewTestBeans.get(position).getType();
	}
}
