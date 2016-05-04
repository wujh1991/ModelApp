package com.volley.wjh.modelapp.CustomWidget.SwipeView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by wujh on 2016/4/20.
 * Email:1049334820@qq.com
 * 自动义侧滑退出activity布局
 */
public class SwipeLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mContentView;
    private int mContentWidth;
    private int mContentHeight;
    //距离屏幕左边的距离
    private int mMoveLeft;
    //主要用于替换当前布局
    private ViewGroup mDecor;
    private ViewGroup mRootView;
    private Activity mActivity;
    //绘制阴影画笔
    private Paint mSPaint;

    //页面状态 默认为关闭
    private Status status = Status.Close;
    //滑动监听器
    private DragListener mDragListener;

    /**
     * 绑定到相应的activity
     * @param activity
     */
    public void attachToActivity(Activity activity) {
        mActivity = activity;
        mDecor = (ViewGroup) activity.getWindow().getDecorView();
        mRootView = (ViewGroup) mDecor.getChildAt(0);
        mRootView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mDecor.removeView(mRootView);
        setContentView(mRootView);
        this.addView(mRootView);
        mDecor.addView(this);
    }

    public SwipeLayout(Context context) {
        super(context);
        init(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mViewDragHelper = ViewDragHelper.create(this, new SwipeDragCallBack());
        initShadowPaint();
    }

    /**
     * 初始化阴影画笔
     */
    public void initShadowPaint(){
        mSPaint = new Paint();
        mSPaint.setStrokeWidth(2);
        mSPaint.setAntiAlias(true);
        mSPaint.setColor(Color.GRAY);
    }

    /**
     * 初始化类容视图
     * @param view
     */
    public void setContentView(View view) {
        mContentView = view;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mContentView != null) {
            mContentWidth = mContentView.getMeasuredWidth();
            mContentHeight = mContentView.getMeasuredHeight();
            mContentView.layout(mMoveLeft, 0, mMoveLeft + mContentWidth, mContentHeight);
        }
    }

    /**
     * 距离不超过屏幕一半时，回弹动画
     */
    public void open() {
        if (mViewDragHelper.smoothSlideViewTo(mContentView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * 距离超过屏幕一半时，结束动画
     */
    public void close() {
        if (mViewDragHelper.smoothSlideViewTo(mContentView, mContentWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mActivity.finish();
                    mActivity.overridePendingTransition(0, 0);
                }
            }, 500);

        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawShadow(canvas);
        super.dispatchDraw(canvas);
    }

    /**
     * 阴影设置
     * @param canvas
     */
    private void drawShadow(Canvas canvas) {
        canvas.save();
        //构造一个渐变
        Shader mShader = new LinearGradient(mMoveLeft - 15, 0, mMoveLeft, 0, new int[]{Color.parseColor("#1edddddd"), Color.parseColor("#6e666666"), Color.parseColor("#9e666666")}, null, Shader.TileMode.REPEAT);
        //设置着色器
        mSPaint.setShader(mShader);
        //绘制时，注意向左边偏移
        RectF rectF = new RectF(mMoveLeft - 15, 0, mMoveLeft, mContentHeight);
        canvas.drawRect(rectF, mSPaint);
        canvas.restore();
    }

    class SwipeDragCallBack extends Callback{
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (mMoveLeft + dx < 0) {
                return 0;
            } else if (mMoveLeft + dx > mContentWidth) {
                return mContentWidth;
            } else {
                return left;
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mContentWidth;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (mMoveLeft > (mContentWidth / 2) && releasedChild == mRootView) {
                close();
            } else {
                open();
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mMoveLeft = left;

            //界面重绘，显示阴影效果
            invalidate();
//                dispatchDragEvent(mMoveLeft);
        }

    }

    /**
     * 进行处理拖拽事件
     *
     * @param moveLeft
     */
    private void dispatchDragEvent(int moveLeft) {
        if (mDragListener == null) {
            return;
        }
        float percent = moveLeft / (float) mContentWidth;
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
        ViewHelper.setTranslationX(mContentView, -mContentView.getWidth() / 2.5f + mContentView.getWidth() / 2.5f * percent);
    }

    /**
     * 获取页面状态
     *
     * @return
     */
    public Status getStatus() {
        if (mMoveLeft == 0) {
            status = Status.Open;
        } else if (mMoveLeft == mContentWidth) {
            status = Status.Close;
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
