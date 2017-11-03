package com.example.ggsddu;

import com.example.ggsddu.view.HistogramView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class HistogramActivity extends Activity {

	private static final String TAG = "HistogramActivity";
	private int[][] mInfo;
	private HistogramView mHistogramView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.histogram_activity);
		Log.i(TAG, "onCreate");
		mHistogramView = (HistogramView) findViewById(R.id.historyview);
		mInfo = new int[][] { {1500,Color.RED}, {2017,Color.GREEN},{8346,Color.GRAY},{4788,Color.YELLOW},{3200,Color.WHITE},{800,Color.LTGRAY},{8234,Color.CYAN} };
		mHistogramView.setInfo(mInfo);
	}
}
