package com.volley.wjh.modelapp.CustomWidget.DragView;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/5.
 * Email:1049334820@qq.com
 * 侧滑自定义控件
 */
public class DragLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;

    private int mMainLeft;
    private int mRange;

    private int mScreenWidth;
    private int mScreenHeight;

    private ViewGroup mLeftView;
    private DragRelativeLayout mMainView;

    private ImageView ivShadow;
    private Context mContext;
    //滑动监听器
    private DragListener mDragListener;
    private boolean isShowShadow = true;
    //页面状态 默认为关闭
    private Status status = Status.Close;

    private boolean isAllowDrag = false;

    private GestureDetectorCompat mGestureDetectorCompat;

    public DragLayout(Context context) {
        super(context);
        init(context);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setDrag(boolean drag) {
        this.isAllowDrag = drag;
    }

    private void init(Context context) {
        mContext = context;

        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (mMainLeft + dx < 0) {
                    return 0;
                } else if (mMainLeft + dx > mRange) {
                    return mRange;
                } else {
                    return left;
                }
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return mRange;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (changedView == mMainView) {
                    mMainLeft = left;
                } else {
                    mMainLeft = mMainLeft + dx;
                }
                if (mMainLeft < 0) {
                    mMainLeft = 0;
                } else if (mMainLeft > mRange) {
                    mMainLeft = mRange;
                }

                if (isShowShadow) {
                    ivShadow.layout(mMainLeft, 0, mMainLeft + mScreenWidth, mScreenHeight);
                }
                if (changedView == mLeftView) {
                    mLeftView.layout(0, 0, mScreenWidth, mScreenHeight);
                    mMainView.layout(mMainLeft, 0, mMainLeft + mScreenWidth, mScreenHeight);
                }

                dispatchDragEvent(mMainLeft);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (xvel > 0) {
                    open();
                } else if (xvel < 0) {
                    close();
                } else if (releasedChild == mMainView && mMainLeft > mRange * 0.3) {
                    open();
                } else if (releasedChild == mLeftView && mMainLeft > mRange * 0.7) {
                    open();
                } else {
                    close();
                }
            }
        });

        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), mSimpleOnGestureListener);
    }

    private SimpleOnGestureListener mSimpleOnGestureListener = new SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if ((Math.abs(distanceX) > Math.abs(distanceY)) && distanceX < 0 && isAllowDrag != false && status == Status.Close) {
                return true;
            } else if ((Math.abs(distanceX) > Math.abs(distanceY)) && distanceX > 0 && isAllowDrag != false && status == Status.Open) {
                return true;
            } else {
                return false;
            }
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftView.layout(0, 0, mScreenWidth, mScreenHeight);
        mMainView.layout(mMainLeft, 0, mMainLeft + mScreenWidth, mScreenHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenWidth = getMeasuredWidth();
        mScreenHeight = getMeasuredHeight();

        mRange = (int) (mScreenWidth * 0.78);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isAllowDrag) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isShowShadow) {
            ivShadow = new ImageView(mContext);
            ivShadow.setImageResource(R.drawable.shadow);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(ivShadow, 1, lp);
        }
        mLeftView = (ViewGroup) getChildAt(0);
        mMainView = (DragRelativeLayout) getChildAt(isShowShadow ? 2 : 1);
        mMainView.setDragLayout(this);
        mLeftView.setClickable(true);
        mMainView.setClickable(true);
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void open() {
        open(true);
    }

    public void open(boolean animate) {
        if (animate) {
            //继续滑动
            if (mViewDragHelper.smoothSlideViewTo(mMainView, mRange, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mMainView.layout(mRange, 0, mRange * 2, mScreenHeight);
            dispatchDragEvent(mRange);
        }
    }

    public void close() {
        close(true);
    }

    public void close(boolean animate) {
        if (animate) {
            //继续滑动
            if (mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mMainView.layout(0, 0, mScreenWidth, mScreenHeight);
            dispatchDragEvent(0);
        }
    }

    /**
     * 进行处理拖拽事件
     *
     * @param mainLeft
     */
    private void dispatchDragEvent(int mainLeft) {
        if (mDragListener == null) {
            return;
        }
        float percent = mainLeft / (float) mRange;
        //滑动动画效果
        animateView(percent);
        //进行回调滑动的百分比
        mDragListener.onDrag(percent);
        Status lastStatus = status;
        if (lastStatus != getStatus() && status == Status.Close) {
            mDragListener.onClose();
        } else if (lastStatus != getStatus() && status == Status.Open) {
            mDragListener.onOpen();
        }
    }

    /**
     * 根据滑动的距离的比例,进行平移动画
     *
     * @param percent
     */
    private void animateView(float percent) {
        float f1 = 1 - percent * 0.5f;

        ViewHelper.setTranslationX(mLeftView, -mLeftView.getWidth() / 2.5f + mLeftView.getWidth() / 2.5f * percent);
        if (isShowShadow) {
            //阴影效果视图大小进行缩放
            ViewHelper.setScaleX(ivShadow, f1 * 1.2f * (1 - percent * 0.10f));
            ViewHelper.setScaleY(ivShadow, f1 * 1.85f * (1 - percent * 0.10f));
        }
    }

    /**
     * 获取页面状态
     *
     * @return
     */
    public Status getStatus() {
        if (mMainLeft == 0) {
            status = Status.Close;
        } else if (mMainLeft == mRange) {
            status = Status.Open;
        } else {
            status = Status.Drag;
        }
        return status;
    }

    /**
     * 滑动相关回调接口
     */
    public interface DragListener {
        //界面打开
        public void onOpen();

        //界面关闭
        public void onClose();

        //界面滑动过程中
        public void onDrag(float percent);
    }

    public void setDragListener(DragListener dragListener) {
        this.mDragListener = dragListener;
    }

    /**
     * 页面状态(滑动,打开,关闭)
     */
    public enum Status {
        Drag, Open, Close
    }
}
