package com.example.ggsddu;

import java.util.ArrayList;
import java.util.List;

import com.example.ggsddu.adapter.FreeStyleListViewAdapter;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class FreeStyleActivity extends Activity implements OnClickListener {

	private ListView mListView;
	private List<Integer> mDatas;
	private FreeStyleListViewAdapter mFreeStyleListViewAdapter;

	private Button mInsertButton;
	private Button mAddButton;
	private Button mDeleteButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.freestyle_activity);

		mInsertButton = (Button) findViewById(R.id.insert);
		mAddButton = (Button) findViewById(R.id.add);
		mDeleteButton = (Button) findViewById(R.id.delete);

		mInsertButton.setOnClickListener(this);
		mAddButton.setOnClickListener(this);
		mDeleteButton.setOnClickListener(this);
		mListView = (ListView) findViewById(R.id.listview);
		mDatas = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			mDatas.add(i);
		}
		mFreeStyleListViewAdapter = new FreeStyleListViewAdapter(this, mDatas);
		mListView.setAdapter(mFreeStyleListViewAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.insert:
			mDatas.add(5, mDatas.size());
			mFreeStyleListViewAdapter.notifyDataSetChanged();
			break;

		case R.id.add:
			mDatas.add(mDatas.size());
			mFreeStyleListViewAdapter.notifyDataSetChanged();
			break;

		case R.id.delete:
			mDatas.remove(3);
			mFreeStyleListViewAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}

	}
}
