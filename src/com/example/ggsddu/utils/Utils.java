package com.example.ggsddu.utils;

import android.content.Context;

public class Utils {

	
	public static int Dp2Px(Context context, float dp) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
	}
}
