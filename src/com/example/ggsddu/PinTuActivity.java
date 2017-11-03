package com.example.ggsddu;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class PinTuActivity extends Activity implements OnClickListener {

	private static final String TAG = "PinTuActivity";
	private GridLayout mGridLayout;
	private ImageView[][] imageViews = new ImageView[5][3]; // 存放图片
	private Bitmap[][] bitmaps = new Bitmap[5][3];
	private ImageView mNullImageView;
	private boolean mIsAnimationPlaying;

	private GestureDetector mGestureDetector;
	protected long startTouchTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pintu_activity);
		mGridLayout = (GridLayout) findViewById(R.id.pintu_gridlayout);

		initData();

	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.big_pic);

		for (int i = 0; i < imageViews.length; i++) {
			for (int j = 0; j < imageViews[0].length; j++) {
				imageViews[i][j] = new ImageView(this);
				imageViews[i][j].setOnClickListener(this);
				imageViews[i][j].setScaleType(ScaleType.FIT_XY);
				int width = bitmap.getWidth() / 3;
				int height = bitmap.getHeight() / 5;
				bitmaps[i][j] = Bitmap.createBitmap(bitmap, j * width, i
						* height, width, height);
				imageViews[i][j].setImageBitmap(bitmaps[i][j]);
				imageViews[i][j].setTag(new ImageViewInfo(i, j, bitmaps[i][j]));
				int screenWidth = getWindowManager().getDefaultDisplay()
						.getWidth();
				int screenHeight = getWindowManager().getDefaultDisplay()
						.getHeight();
				imageViews[i][j]
						.setLayoutParams(new RelativeLayout.LayoutParams(
								screenWidth / 3, screenHeight / 5));
				imageViews[i][j].setPadding(2, 2, 2, 2);
				mGridLayout.addView(imageViews[i][j]);
			}
		}
		imageViews[2][1].setImageBitmap(null);
		mNullImageView = imageViews[4][2];
		resetGame();
		initGesture();
	}

	private void initGesture() {
		mGestureDetector = new GestureDetector(this, new OnGestureListener() {

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return false;
			}

			@Override
			public void onShowPress(MotionEvent e) {
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				int orientation = -1;
				float startX = e1.getX();
				float startY = e1.getY();
				float endX = e2.getX();
				float endY = e2.getY();
				Log.i(TAG, "onFling---startX" + startX + "---startY" + startY
						+ "---endX=" + endX + "---endY=" + endY);
				long touchTime = System.currentTimeMillis() - startTouchTime;
				float moveX = Math.abs(startX - endX);
				float moveY = Math.abs(startY - endY);
				if (moveX > moveY) {
					// 认为横向移动
					if (startX - endX > 0) {
						orientation = 4;
					} else {
						orientation = 3;
					}
					if (moveX > 200) {
						float speed = moveX * 10000 / touchTime;
						if (speed > 4800) {

						}
					}
				} else {
					// 认为纵向移动
					if (startY - endY > 0) {
						orientation = 1;
					} else {
						orientation = 2;
					}
					if (moveY > 200) {
						float speed = moveY * 10000 / touchTime;
						if (speed > 4800) {

						}
					}
				}
				movePicByOrientation(orientation);
				return false;
			}

			@Override
			public boolean onDown(MotionEvent e) {
				startTouchTime = System.currentTimeMillis();
				return false;
			}
		});
	}

	/**
	 * 点击的图片是否和空图片相邻
	 * 
	 * @param imageView
	 *            点击的图片
	 * @return
	 */
	private boolean isNearByNullPic(final ImageView imageView) {

		ImageViewInfo info = (ImageViewInfo) imageView.getTag();
		ImageViewInfo nullPicInfo = (ImageViewInfo) mNullImageView.getTag();

		TranslateAnimation ta = null;
		if (info.y == nullPicInfo.y && info.x - nullPicInfo.x == -1) {
			// 在空格上
			Log.i(TAG, "click position---up---info=" + info.x + "&" + info.y);
			ta = new TranslateAnimation(0, 0, 0, imageView.getHeight());
			setAnimation(imageView, ta);

			// movePic(imageView);
			return true;
		} else if (info.y == nullPicInfo.y && info.x - nullPicInfo.x == 1) {
			// 在空格下
			Log.i(TAG, "click position---down---info=" + info.x + "&" + info.y);
			ta = new TranslateAnimation(0, 0, 0, -imageView.getHeight());
			setAnimation(imageView, ta);
			// movePic(imageView);
			return true;
		} else if (info.x == nullPicInfo.x && info.y - nullPicInfo.y == -1) {
			// 在空格左
			Log.i(TAG, "click position---left---info=" + info.x + "&" + info.y);
			ta = new TranslateAnimation(0, imageView.getWidth(), 0, 0);
			setAnimation(imageView, ta);
			// movePic(imageView);
			return true;
		} else if (info.x == nullPicInfo.x && info.y - nullPicInfo.y == 1) {
			// 在空格右
			Log.i(TAG, "click position---right---info=" + info.x + "&" + info.y);
			ta = new TranslateAnimation(0, -imageView.getWidth(), 0, 0);
			setAnimation(imageView, ta);
			// movePic(imageView);
			return true;
		}

		return false;

	}

	/**
	 * 判断是否game over
	 * 
	 * @param imageView
	 * @param ta
	 */
	private boolean isGameOver() {
		for (int i = 0; i < imageViews.length; i++) {
			for (int j = 0; j < imageViews[0].length; j++) {
				ImageViewInfo info = (ImageViewInfo) (imageViews[i][j].getTag());
				if (info.x != info.bitmap_x || info.y != info.bitmap_y) {
					return false;
				}
			}
		}
		return true;
	}

	private void setAnimation(final ImageView imageView, TranslateAnimation ta) {
		ta.setDuration(300);
		ta.setFillAfter(true);
		ta.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				mIsAnimationPlaying = true;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				imageView.clearAnimation();
				movePic(imageView);
				mIsAnimationPlaying = false;
				boolean isGameOver = isGameOver();
				Log.i(TAG, "isGameOver=" + isGameOver);
				if (isGameOver) {
					showGameOverDialog();
				}
			}
		});
		imageView.startAnimation(ta);
	}

	protected void showGameOverDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon).setMessage("你太牛逼了吧！！！！")
				.setPositiveButton("再来一局", new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						resetGame();
					}
				}).setNegativeButton("退出", new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						PinTuActivity.this.finish();
					}
				});
		builder.create().show();
	}

	protected void resetGame() {
		Log.i(TAG, "resetGame");

		for (int i = 0; i < 300; i++) {
			int orientation = (new Random().nextInt(4)) + 1;
			movePicByOrientation(orientation);
		}
	}

	/**
	 * 根据方向移动图片
	 */
	private void movePicByOrientation(int orientation) {
		Log.i(TAG, "movePicByOrientation---" + orientation);
		ImageViewInfo nullImageViewInfo = (ImageViewInfo) mNullImageView
				.getTag();
		int x = nullImageViewInfo.x;
		int y = nullImageViewInfo.y;
		switch (orientation) {
		case 1:
			// 图片向上移动
			x++;
			break;
		case 2:
			// 图片向下移动
			x--;
			break;
		case 3:
			// 图片向右移动
			y--;
			break;
		case 4:
			// 图片向左移动
			y++;
			break;
		}
		if (x == nullImageViewInfo.x && y == nullImageViewInfo.y) {
			return;
		}
		if (x >= 0 && y >= 0 && x < imageViews.length
				&& y < imageViews[0].length) {
			movePic(imageViews[x][y]);
		}
	}

	/**
	 * 移动图片
	 * 
	 * @author Tom
	 * 
	 */
	private void movePic(ImageView imageView) {
		int bitmap_x;
		int bitmap_y;
		ImageViewInfo info = (ImageViewInfo) imageView.getTag();
		ImageViewInfo mNullImageInfo = (ImageViewInfo) mNullImageView.getTag();
		Log.i(TAG, "movePic---info.bitmap_x=" + info.bitmap_x
				+ "---info.bitmap_y=" + info.bitmap_y
				+ "---mNullImageInfo.bitmap_x=" + mNullImageInfo.bitmap_x
				+ "---mNullImageInfo.bitmap_y=" + mNullImageInfo.bitmap_y);
		bitmap_x = info.bitmap_x;
		bitmap_y = info.bitmap_y;
		info.bitmap_x = mNullImageInfo.bitmap_x;
		info.bitmap_y = mNullImageInfo.bitmap_y;
		mNullImageInfo.bitmap_x = bitmap_x;
		mNullImageInfo.bitmap_y = bitmap_y;
		mNullImageView.setImageBitmap(info.bitmap);
		mNullImageInfo.bitmap = info.bitmap;
		imageView.setImageBitmap(null);
		info.bitmap = null;
		mNullImageView = imageView;
		Log.i(TAG, "movePic---info.bitmap_x=" + info.bitmap_x
				+ "---info.bitmap_y=" + info.bitmap_y
				+ "---mNullImageInfo.bitmap_x=" + mNullImageInfo.bitmap_x
				+ "---mNullImageInfo.bitmap_y=" + mNullImageInfo.bitmap_y);
	}

	class ImageViewInfo {
		int x;
		int y;
		Bitmap bitmap;
		int bitmap_x;
		int bitmap_y;

		public ImageViewInfo(int x, int y, Bitmap bitmap) {
			super();
			this.x = x;
			this.y = y;
			this.bitmap = bitmap;
			this.bitmap_x = x;
			this.bitmap_y = y;
		}

	}

	@Override
	public void onClick(View v) {
		if (!mIsAnimationPlaying) {

			boolean isNearByNullPic = isNearByNullPic((ImageView) v);
			Log.i(TAG, "onClick---isNearByNullPic=" + isNearByNullPic);

		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mGestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
