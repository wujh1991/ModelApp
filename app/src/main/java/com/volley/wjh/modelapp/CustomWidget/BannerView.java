package com.volley.wjh.modelapp.CustomWidget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.volley.wjh.modelapp.Adapter.BannerPagerAdapter;
import com.volley.wjh.modelapp.Base.LeakHandler.BaseHandler;
import com.volley.wjh.modelapp.CustomWidget.ViewPager.FixedSpeedScroller;
import com.volley.wjh.modelapp.R;

import java.lang.reflect.Field;

/**
 * Created by wujh on 2016/4/3.
 * Email:1049334820@qq.com
 * 自定义广告轮播图
 */
public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener,View.OnTouchListener{

    private Context mContext;

    private ViewPager mViewPager;

    private LinearLayout mLlDots;

    private TextView mTvTitle;

    private BannerPagerAdapter mBannerPagerAdapter;

    private int mTotalCount = Integer.MAX_VALUE;

    private int mRealCount = 0;

    private String[] mTitles;

    private int mLastIndex = 0;

    private boolean isTouch = false;

    private static final int MSG_WHAT = 0x001;

    private Handler handler;

    private static final long delayedTime = 2000;

    //ViewPager的滚动监听
    private FixedSpeedScroller mScroller;

    //ViewPager的切换速度
    private static final int scrollerDuration = 1500;

    public BannerView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init() {
        findViews();
    }

    public void findViews() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner_view, null, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vpBanner);
        mTvTitle = (TextView) view.findViewById(R.id.tvBannerTitle);
        mLlDots = (LinearLayout) view.findViewById(R.id.llDots);
        addView(view);
    }

    public void setListener() {
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);
    }

    public void setAdapter(BannerPagerAdapter bannerPagerAdapter,String[] titles) {
        this.mBannerPagerAdapter = bannerPagerAdapter;
        this.mTitles = titles;
        mViewPager.setAdapter(mBannerPagerAdapter);
        initDotViews();
        initData();
        setListener();
        //修改ViewPager的切换速度
        resetViewPager();

        handler = new MyHandler(mContext);
        startView();
    }

    //修改ViewPager的切换速度
    public void resetViewPager(){
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroller(mViewPager.getContext(),sInterpolator);
            mField.set(mViewPager, mScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };

    public void initDotViews() {
        if (mBannerPagerAdapter != null) {
            mRealCount = mBannerPagerAdapter.getRealCount();
        }

        if (mRealCount != 0) {
            for (int i = 0; i < mRealCount; i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setBackgroundResource(R.drawable.selector_dot);
                if (i != 0) {
                    LinearLayout.LayoutParams layoutParams = new
                            LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 0, 0, 0);
                    imageView.setLayoutParams(layoutParams);
                }
                mLlDots.addView(imageView);

                if (i == 0) {
                    imageView.setEnabled(true);
                } else {
                    imageView.setEnabled(false);
                }
            }
        }
    }

    public void initData(){
        int item = mTotalCount / 2 - (mTotalCount / 2 % mRealCount);
        int startPosition = item % mRealCount;
        mViewPager.setCurrentItem(item);
        if(mTitles != null){
            mTvTitle.setText(mTitles[startPosition]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int realPosition = position % mRealCount;
        if(mTitles != null){
            mTvTitle.setText(mTitles[realPosition]);
        }
        mLlDots.getChildAt(mLastIndex).setEnabled(false);
        mLlDots.getChildAt(realPosition).setEnabled(true);
        mLastIndex = realPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                isTouch =true;
                handler.removeMessages(MSG_WHAT);
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                startView();
                break;
        }
        return false;
    }

    private class MyHandler extends BaseHandler<Context> {

        public MyHandler(Context reference) {
            super(reference);
        }

        @Override
        public void doHandleMessage(Context reference, Message msg) {
            int item = mViewPager.getCurrentItem() + 1;
            mViewPager.setCurrentItem(item);
            //修改ViewPager的切换速度
            mScroller.setmDuration(scrollerDuration);

            if(!isTouch){
                startView();
            }
        }
    }

    /**
     * 处理内存泄露问题
     */
    public void removeHandlerMessages(){
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    //停止切换
    public void stopView(){
        if(handler != null){
            handler.removeMessages(MSG_WHAT);
        }
    }

    //启动切换
    public void startView(){
        if(handler != null){
            Message msg = new Message();
            msg.what = MSG_WHAT;
            handler.sendMessageDelayed(msg, delayedTime);
        }
    }
}
