package com.example.ggsddu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ggsddu.R;

public class CombinationView extends RelativeLayout{
	
	private ImageView mLeftImageView;
	private ImageView mRightImageView;
	public CombinationView(Context context) {
		super(context);
		initView(context);
	}


	public CombinationView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}


	public CombinationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}


	private void initView(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.combination_view, this);
		mLeftImageView = (ImageView) view.findViewById(R.id.combination_left_image);
		mRightImageView = (ImageView) view.findViewById(R.id.combination_right_image);
	}
	
	public void setLeftImageClickListener(OnClickListener onClickListener){
		mLeftImageView.setOnClickListener(onClickListener);
	}
	
	public void setRightImageClickListener(OnClickListener onClickListener){
		mRightImageView.setOnClickListener(onClickListener);
	}
}
