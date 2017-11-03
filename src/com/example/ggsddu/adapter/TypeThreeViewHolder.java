package com.example.ggsddu.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ggsddu.R;

public class TypeThreeViewHolder extends AbstractViewHolder{

	public TypeThreeViewHolder(View arg0) {
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
		button = (Button) arg0.findViewById(R.id.recyclerview_item_button);
	}


	private static final String TAG = "TypeThreeViewHolder";
	private Button button;
	

	@Override
	public void bindview() {
		Log.i(TAG, "bindview");
		button.setText("button");
		
	}

}
