package com.example.ggsddu;

import com.example.ggsddu.view.CarouselImagesViewGroup;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CarouselImagesActivity extends Activity {

	private CarouselImagesViewGroup mCarouselImagesViewGroup;
	private int[] images = new int[] { R.drawable.actorbutton_focused,
			R.drawable.collectbutton_focused_collected,
			R.drawable.playbutton_focused };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carousel_images_activity);
		mCarouselImagesViewGroup = (CarouselImagesViewGroup) findViewById(R.id.carousel_images_viewgroup);
		addImages();
	}

	private void addImages() {
		for(int i=0;i<images.length;i++){
			ImageView iv = new ImageView(this);
			iv.setImageResource(images[i]);
			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			iv.setLayoutParams(lp);
			iv.setScaleType(ScaleType.FIT_XY);
			mCarouselImagesViewGroup.addView(iv);
		}
	}
}
