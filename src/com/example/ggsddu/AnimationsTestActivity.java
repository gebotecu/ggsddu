package com.example.ggsddu;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimationsTestActivity extends Activity implements OnClickListener {

	private static final String TAG = "AnimationsTestActivity";
	private ImageView mImageView;
	private ImageView mImageView1;
	private ImageView mImageView2;
	private ImageView mImageView3;
	private ImageView mImageView4;
	private ImageView mImageView5;
	private ImageView mImageView6;
	private List<ImageView> mImageViews;
	private Button mJavaAlphaButton;
	private Button mJavaRotateButton;
	private Button mJavaTranslateButton;
	private Button mJavaScaleButton;
	
	private Button mXmlAlphaButton;
	private Button mXmlRotateButton;
	private Button mXmlTranslateButton;
	private Button mXmlScaleButton;
	private Button mXmlSetButton;
	private Button mXmlFrameButton;
	
	private Button mObjectAnimatorButton;
	
	private boolean mIsImagesExpend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animations_test_activity);
		initView();
	}

	private void initView() {
		mImageView = (ImageView) findViewById(R.id.animation_test_imageview);
		mImageView1 = (ImageView) findViewById(R.id.animation_test_imageview1);
		mImageView2 = (ImageView) findViewById(R.id.animation_test_imageview2);
		mImageView3 = (ImageView) findViewById(R.id.animation_test_imageview3);
		mImageView4 = (ImageView) findViewById(R.id.animation_test_imageview4);
		mImageView5 = (ImageView) findViewById(R.id.animation_test_imageview5);
		mImageView6 = (ImageView) findViewById(R.id.animation_test_imageview6);
		
		mImageViews = new ArrayList<>();
		mImageViews.add(mImageView1);
		mImageViews.add(mImageView2);
		mImageViews.add(mImageView3);
		mImageViews.add(mImageView4);
		mImageViews.add(mImageView5);
		mImageViews.add(mImageView6);
		
		mImageView.setOnClickListener(this);
		mImageView1.setOnClickListener(this);
		mImageView2.setOnClickListener(this);
		mImageView3.setOnClickListener(this);
		mImageView4.setOnClickListener(this);
		mImageView5.setOnClickListener(this);
		mImageView6.setOnClickListener(this);
		
		mJavaAlphaButton = (Button) findViewById(R.id.alpha_java);
		mJavaRotateButton = (Button) findViewById(R.id.rotate_java);
		mJavaTranslateButton = (Button) findViewById(R.id.translate_java);
		mJavaScaleButton = (Button) findViewById(R.id.scale_java);
		mXmlAlphaButton = (Button) findViewById(R.id.alpha_xml);
		mXmlRotateButton = (Button) findViewById(R.id.rotate_xml);
		mXmlTranslateButton = (Button) findViewById(R.id.translate_xml);
		mXmlScaleButton = (Button) findViewById(R.id.scale_xml);
		mXmlSetButton = (Button) findViewById(R.id.set_xml);
		mXmlFrameButton = (Button) findViewById(R.id.frame_xml);
		mObjectAnimatorButton = (Button) findViewById(R.id.objectanimator);
		
		mJavaAlphaButton.setOnClickListener(this);
		mJavaRotateButton.setOnClickListener(this);
		mJavaTranslateButton.setOnClickListener(this);
		mJavaScaleButton.setOnClickListener(this);
		mXmlAlphaButton.setOnClickListener(this);
		mXmlRotateButton.setOnClickListener(this);
		mXmlTranslateButton.setOnClickListener(this);
		mXmlScaleButton.setOnClickListener(this);
		mXmlSetButton.setOnClickListener(this);
		mXmlFrameButton.setOnClickListener(this);
		mObjectAnimatorButton.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
//		ad.stop();
//		ad.selectDrawable(0);
		mImageView.clearAnimation();
		switch (v.getId()) {
		case R.id.alpha_java:
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			alphaAnimation.setDuration(3000);
			alphaAnimation.setFillAfter(false);
			mImageView.startAnimation(alphaAnimation);
			break;

		case R.id.rotate_java:
			RotateAnimation retateAnimation = new RotateAnimation(0, 180,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			retateAnimation.setDuration(3000);
			retateAnimation.setFillAfter(false);
			mImageView.startAnimation(retateAnimation);
			break;
		case R.id.translate_java:
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
					1, Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 1);
			translateAnimation.setDuration(3000);
			translateAnimation.setFillAfter(false);
			mImageView.startAnimation(translateAnimation);
			break;
		case R.id.scale_java:
			ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
					Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
					0);
			scaleAnimation.setDuration(3000);
			scaleAnimation.setFillAfter(false);
			mImageView.startAnimation(scaleAnimation);
			break;
		case R.id.alpha_xml:
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.alpha_animation);

			mImageView.startAnimation(animation);
			break;
		case R.id.rotate_xml:
			Animation animation1 = AnimationUtils.loadAnimation(this,
					R.anim.retate_animation);

			mImageView.startAnimation(animation1);
			break;
		case R.id.translate_xml:
			Animation animation2 = AnimationUtils.loadAnimation(this,
					R.anim.translate_animation);

			mImageView.startAnimation(animation2);
			break;
		case R.id.scale_xml:
			Animation animation3 = AnimationUtils.loadAnimation(this,
					R.anim.scale_animatiom);

			mImageView.startAnimation(animation3);
			break;
		case R.id.set_xml:
			Animation animation4 = AnimationUtils.loadAnimation(this,
					R.anim.set_animation);

			mImageView.startAnimation(animation4);
			break;
			
		case R.id.frame_xml:
			Log.i(TAG, "onClick---R.id.frame_xml");
//			mImageView.setBackgroundResource(R.anim.frame_animation);
			AnimationDrawable ad = (AnimationDrawable) mImageView.getBackground();
			ad.start();
			break;
		case R.id.objectanimator:
			Log.i(TAG, "onClick---R.id.objectanimator");
//			ObjectAnimator oa = new ObjectAnimator();
			ObjectAnimator oa = ObjectAnimator.ofFloat(mImageView,"alpha",0f,1.0f);
			oa.setDuration(3000);
			oa.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					Toast.makeText(AnimationsTestActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
					super.onAnimationEnd(animation);
				}
			});
			oa.start();
			break;
		
		case R.id.animation_test_imageview:
			Log.i(TAG, "onClick---R.id.animation_test_imageview");
			if(mIsImagesExpend){
				regainImages();
			} else {
				
				expendImages();
			}
			break;
		
		case R.id.animation_test_imageview1:
			Log.i(TAG, "onClick---R.id.animation_test_imageview1");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.animation_test_imageview2:
			Log.i(TAG, "onClick---R.id.animation_test_imageview2");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.animation_test_imageview3:
			Log.i(TAG, "onClick---R.id.animation_test_imageview3");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview3", Toast.LENGTH_SHORT).show();
			break;
		case R.id.animation_test_imageview4:
			Log.i(TAG, "onClick---R.id.animation_test_imageview4");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview4", Toast.LENGTH_SHORT).show();
			break;
		case R.id.animation_test_imageview5:
			Log.i(TAG, "onClick---R.id.animation_test_imageview5");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview5", Toast.LENGTH_SHORT).show();
			break;
		case R.id.animation_test_imageview6:
			Log.i(TAG, "onClick---R.id.animation_test_imageview6");
			Toast.makeText(AnimationsTestActivity.this, "onClick---R.id.animation_test_imageview6", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

	@SuppressLint("NewApi")
	private void regainImages() {

		for(int i=0;i<mImageViews.size();i++){
			ObjectAnimator oaY = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", -50*i,0);
			oaY.setDuration(2000);
			ObjectAnimator oaX = ObjectAnimator.ofFloat(mImageViews.get(i), "translationX", 50*(5-i),0);
			oaX.setDuration(2000);
			
			AnimatorSet set = new AnimatorSet();
			set.setInterpolator(AnimationUtils.loadInterpolator(this,  
					android.R.anim.accelerate_decelerate_interpolator));
			set.playTogether(oaX,oaY);
			set.start();
		}
		mIsImagesExpend = false;
	}

	@SuppressLint("NewApi")
	private void expendImages() {
		
		for(int i=0;i<mImageViews.size();i++){
			ObjectAnimator oaY = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", 0,-50*i);
			oaY.setDuration(2000);
			ObjectAnimator oaX = ObjectAnimator.ofFloat(mImageViews.get(i), "translationX", 0,50*(5-i));
			oaX.setDuration(2000);
			
			AnimatorSet set = new AnimatorSet();
			set.setInterpolator(AnimationUtils.loadInterpolator(this,  
					android.R.anim.bounce_interpolator));
			set.playTogether(oaX,oaY);
			set.start();
		}
		mIsImagesExpend = true;
	}
}
