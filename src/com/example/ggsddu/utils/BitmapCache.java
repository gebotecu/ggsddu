package com.example.ggsddu.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache{

	
	private LruCache<String, Bitmap> mCache;
	int max = 10*1024*1024;
	
	public BitmapCache() {
		mCache = new LruCache<String, Bitmap>(max){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes()*value.getHeight();
			}
		};
	}
	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
		
	}

}
