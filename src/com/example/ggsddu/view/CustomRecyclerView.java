package com.example.ggsddu.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

public class CustomRecyclerView extends RecyclerView {

	private static final String TAG = "tag";
	private Scroller mScroller;
	private int mLastx;
	private int mTargetPos;
	private float mPxPerMillsec; // 用于设置自动平移时候的速度
	
	private int mCenterLeft = 268; // 计算子view居中后相对于父view的左边距
	private int mItemWidth = 182;
	private int mCenterRight = mCenterLeft + mItemWidth; // 计算子view居中后相对于父view的右边距

	public CustomRecyclerView(Context context) {
		super(context);
		init(context);
	}

	public CustomRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mScroller = new Scroller(context);
		this.setChildrenDrawingOrderEnabled(true);
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
       
	}

	@Override
	public void computeScroll() {
		if (mScroller != null && mScroller.computeScrollOffset()) {
			// Log.e(TAG, "computeScroll mLastX : " + mLastx + " scroller.getCurrX() : " + mScroller.getCurrX());
			scrollBy(mLastx - mScroller.getCurrX(), 0);
			mLastx = mScroller.getCurrX();
			postInvalidate();// 让系统继续重绘，则会继续重复执行computeScroll
		}
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		Log.i(TAG, "getChildDrawingOrder---"+i);
		View view = this.getFocusedChild();
        int focusedItemIdx = this.indexOfChild(view);
        if (focusedItemIdx <= -1) {
            return i;
        }

        if (focusedItemIdx == i) {
            return childCount - 1;
        } else if (i == childCount - 1) {
            return focusedItemIdx;
        } else {
            return i;
        }
	}
	
	/**
	 * 将指定item平滑移动到整个view的中间位置
	 * @param position
	 */
	public void smoothToCenter(int position) {
		// 获取可视范围内的选项的头尾位置
		int targetPos = position - ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
		View targetChild = getChildAt(targetPos);// 获取目标item在当前可见视图item集合中的位置

        if (targetChild == null) {
        	    return;
        }		
		int childLeftPx = targetChild.getLeft();// 子view相对于父view的左边距
		int childRightPx = targetChild.getRight();// 子view相对于父view的右边距
		//Log.i(TAG, "target-->left:" + targetChild.getLeft() + "   right:" + targetChild.getRight());

		if (childLeftPx > mCenterLeft) {// 子view左边距比居中view大（说明子view靠父view的右边，此时需要把子view向左平移, 平移的起始位置就是子view的左边距，平移的距离就是两者之差
			mLastx = childLeftPx;
			mScroller.startScroll(childLeftPx, 0, mCenterLeft - childLeftPx, 0, 300);// 600为移动时长，可自行设定
			postInvalidate();
		} else if (childRightPx < mCenterRight) {
			mLastx = childRightPx;
			mScroller.startScroll(childRightPx, 0, mCenterRight - childRightPx, 0, 300);
			postInvalidate();
		}
	}


	// 调用此方法滚动到目标位置
//	public void smoothScrollTo(int fx, int fy, int duration) {
//		int dx = 0;
//		int dy = 0;
//		if (fx != 0) {
//			dx = fx - mScroller.getFinalX();
//		}
//		if (fy != 0) {
//			dy = fy - mScroller.getFinalY();
//		}
//		smoothScrollBy(dx, dy, duration);
//	}

	// 调用此方法设置滚动的相对偏移
//	private void smoothScrollBy(int dx, int dy, int duration) {
//		if (duration > 0) {
//			mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, duration);
//		} else {
//			// 设置mScroller的滚动偏移量
//			mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
//		}
//		/**
//		 * 重绘整个view，重绘过程会调用到computeScroll()方法
//		 */
//		invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面,看不到滚动效果
//	}

//	public void checkAutoAdjust(int position) {
//		int childcount = getChildCount();
//		// 获取可视范围内的选项的头尾位置
//		int firstvisiableposition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
//		int lastvisiableposition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
//
//		if (position == (firstvisiableposition + 1) || position == firstvisiableposition) {
//			// 当前位置需要向右平移
//			leftScrollBy(position, firstvisiableposition);
//		} else if (position == (lastvisiableposition - 1) || position == lastvisiableposition) {
//			// 当前位置需要向右平移
//			rightScrollBy(position, lastvisiableposition);
//		}
//	}

//	private void leftScrollBy(int position, int firstvisiableposition) {
//
//		View leftChild = getChildAt(0);
//		if (leftChild != null) {
//			int leftx = leftChild.getLeft();
//			int startleft = leftx;
//			int endleft = position == firstvisiableposition ? leftChild.getWidth() : 0;
//			autoAdjustScroll(startleft, endleft);
//		}
//	}

//	private void rightScrollBy(int position, int lastvisiableposition) {
//
//		int childcount = getChildCount();
//		View rightChild = getChildAt(childcount - 1);
//		if (rightChild != null) {
//			int rightx = rightChild.getRight();
//			int dx = rightx - getWidth();
//			int startright = dx;
//			int endright = position == lastvisiableposition ? - 1 * rightChild.getWidth() : 0;
//			autoAdjustScroll(startright, endright);
//		}
//	}

	/**
	 * @param startleft 滑动起始位置
	 * @param endleft 滑动结束位置
	 */
//	private void autoAdjustScroll(int start, int end) {
//
//		int duration = 0;
//		if (mPxPerMillsec != 0) {
//			duration = (int) (Math.abs(end - start) / mPxPerMillsec);
//		}
//
//		mLastx = start;
//		if (duration > 0) {
//			mScroller.startScroll(start, 0, end - start, 0, duration);
//		} else {
//			mScroller.startScroll(start, 0, end - start, 0);
//		}
//		postInvalidate();
//	}
}

//public class CustomRecycleView extends RecyclerView {
//
//	private static final String TAG = "TAG";
//	private Scroller mScroller;
//	private int mLastx;
//	private int mTargetPos;
//	private float mPxPerMillsec; // 用于设置自动平移时候的速度
//
//	public CustomRecycleView(Context context) {
//		super(context);
//		init(context);
//	}
//
//	public CustomRecycleView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init(context);
//	}
//
//	public CustomRecycleView(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//		init(context);
//	}
//
//	private void init(Context context) {
//		mScroller = new Scroller(context);
//	}
//
//	@Override
//	public void computeScroll() {
//		super.computeScroll();
//		// computeScrollOffset返回true表示滚动还在继续，持续时间应该就是startScroll设置的时间
//		if (mScroller != null && mScroller.computeScrollOffset()) {
//			// Log.e(TAG, "computeScroll    mLastX : " + mLastx + " scroller.getCurrX() : " + mScroller.getCurrX());
//			scrollBy(mLastx - mScroller.getCurrX(), 0);
//			mLastx = mScroller.getCurrX();
//			postInvalidate();// 让系统继续重绘，则会继续重复执行computeScroll
//		}
//	}
//
//	/**
//	 * 将指定item平滑移动到整个view的中间位置
//	 *
//	 * @param position
//	 */
//	public void smoothToCenter(int position) {
//		// 获取可视范围内的选项的头尾位置
//		int targetPos = position - ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
//		View targetChild = getChildAt(targetPos);// 获取目标item在当前可见视图item集合中的位置
//
//		int childLeftPx = targetChild.getLeft();// 子view相对于父view的左边距
//		int childRightPx = targetChild.getRight();// 子view相对于父view的右边距
//		Log.i(TAG, "target-->left:" + targetChild.getLeft() + "   right:" + targetChild.getRight());
//
//		int parentWidth = getWidth();// 获取父视图的宽度
//		int childWidth = targetChild.getWidth();
//		int centerLeft = parentWidth / 2 - childWidth / 2;// 计算子view居中后相对于父view的左边距
//		int centerRight = parentWidth / 2 + childWidth / 2;// 计算子view居中后相对于父view的右边距
//		Log.i(TAG, "rv width:" + parentWidth + "   item width:" + childWidth + "   centerleft:" + centerLeft + "   centerRight:" + centerRight);
//
//		if (childLeftPx > centerLeft) {// 子view左边距比居中view大（说明子view靠父view的右边，此时需要把子view向左平移, 平移的起始位置就是子view的左边距，平移的距离就是两者之差
//			mLastx = childLeftPx;
//			mScroller.startScroll(childLeftPx, 0, centerLeft - childLeftPx, 0, 600);// 600为移动时长，可自行设定
//			postInvalidate();
//		} else if (childRightPx < centerRight) {
//			mLastx = childRightPx;
//			mScroller.startScroll(childRightPx, 0, centerRight - childRightPx, 0, 600);
//			postInvalidate();
//		}
//	}
//
//
//
//
//
//
//
//
//
//	// 调用此方法滚动到目标位置
////	public void smoothScrollTo(int fx, int fy, int duration) {
////		int dx = 0;
////		int dy = 0;
////		if (fx != 0) {
////			dx = fx - mScroller.getFinalX();
////		}
////		if (fy != 0) {
////			dy = fy - mScroller.getFinalY();
////		}
////		smoothScrollBy(dx, dy, duration);
////	}
//
//	// 调用此方法设置滚动的相对偏移
////	private void smoothScrollBy(int dx, int dy, int duration) {
////		if (duration > 0) {
////			mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, duration);
////		} else {
////			// 设置mScroller的滚动偏移量
////			mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
////		}
////		/**
////		 * 重绘整个view，重绘过程会调用到computeScroll()方法
////		 */
////		invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面,看不到滚动效果
////	}
//
////	public void checkAutoAdjust(int position) {
////		int childcount = getChildCount();
////		// 获取可视范围内的选项的头尾位置
////		int firstvisiableposition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
////		int lastvisiableposition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
////
////		if (position == (firstvisiableposition + 1) || position == firstvisiableposition) {
////			// 当前位置需要向右平移
////			leftScrollBy(position, firstvisiableposition);
////		} else if (position == (lastvisiableposition - 1) || position == lastvisiableposition) {
////			// 当前位置需要向右平移
////			rightScrollBy(position, lastvisiableposition);
////		}
////	}
//
////	private void leftScrollBy(int position, int firstvisiableposition) {
////
////		View leftChild = getChildAt(0);
////		if (leftChild != null) {
////			int leftx = leftChild.getLeft();
////			int startleft = leftx;
////			int endleft = position == firstvisiableposition ? leftChild.getWidth() : 0;
////			autoAdjustScroll(startleft, endleft);
////		}
////	}
//
////	private void rightScrollBy(int position, int lastvisiableposition) {
////
////		int childcount = getChildCount();
////		View rightChild = getChildAt(childcount - 1);
////		if (rightChild != null) {
////			int rightx = rightChild.getRight();
////			int dx = rightx - getWidth();
////			int startright = dx;
////			int endright = position == lastvisiableposition ? - 1 * rightChild.getWidth() : 0;
////			autoAdjustScroll(startright, endright);
////		}
////	}
//
//	/**
//	 * @param startleft 滑动起始位置
//	 * @param endleft 滑动结束位置
//	 */
////	private void autoAdjustScroll(int start, int end) {
////
////		int duration = 0;
////		if (mPxPerMillsec != 0) {
////			duration = (int) (Math.abs(end - start) / mPxPerMillsec);
////		}
////
////		mLastx = start;
////		if (duration > 0) {
////			mScroller.startScroll(start, 0, end - start, 0, duration);
////		} else {
////			mScroller.startScroll(start, 0, end - start, 0);
////		}
////		postInvalidate();
////	}
//}
