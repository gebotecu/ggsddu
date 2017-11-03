package com.example.ggsddu.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ggsddu.R;

public class TypeOneViewHolder extends AbstractViewHolder{
	
	private static final String TAG = "TypeOneViewHolder";
	private TextView textView;
	public TypeOneViewHolder(View arg0) {
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
		textView = (TextView) arg0.findViewById(R.id.recyclerview_item_textview);
	}


	

	@Override
	public void bindview() {
		Log.i(TAG, "bindview");
		textView.setText("textview");
		
	}

}
