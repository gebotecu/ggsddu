package com.example.ggsddu.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ggsddu.R;

public class TypeTwoViewHolder extends AbstractViewHolder{

	public TypeTwoViewHolder(View arg0) {
		super(arg0);
		arg0.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Log.i(TAG,"onFocusChange---"+v.getId()+"---"+hasFocus);
				if(hasFocus){
					v.setBackgroundColor(Color.BLACK);
				} else{
					v.setBackgroundColor(Color.WHITE);
				}
			}
		});
		imageView = (ImageView) arg0.findViewById(R.id.recyclerview_item_imageview);
	}


	private static final String TAG = "TypeTwoViewHolder";
	private ImageView imageView;
	

	@Override
	public void bindview() {
		Log.i(TAG, "bindview");
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

			}
		});
	}

}
