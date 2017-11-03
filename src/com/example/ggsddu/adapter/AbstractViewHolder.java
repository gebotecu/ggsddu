package com.example.ggsddu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder{

	
	public AbstractViewHolder(View arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public abstract void bindview();
}
