package com.example.ggsddu.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ggsddu.R;


public class ViewPagerFragment3 extends Fragment{
	private TextView mTextView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_fragment, container,false);
		view.setBackgroundColor(Color.BLUE);
		mTextView = (TextView) view.findViewById(R.id.viewpager_fragment_textview);
		mTextView.setText("Fragment3");
		return view;
	}
}
