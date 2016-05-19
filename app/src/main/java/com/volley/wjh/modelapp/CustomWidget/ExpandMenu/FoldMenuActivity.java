package com.volley.wjh.modelapp.CustomWidget.ExpandMenu;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/5/5.
 * Email:1049334820@qq.com
 * 折叠动画菜单
 */
public class FoldMenuActivity extends BaseActivity implements View.OnClickListener {
    private String TAG_ARROW = "service_arrow";
    private String TAG_ITEM = "service_item";

    private View mBottomView;
    private LinearLayout mTrafficLayout, mLifeLayout, mMedicalLayout, mLiveLayout, mPublicLayout;
    private RelativeLayout mTrafficBarLayout, mLifeBarLayout, mMedicalBarLayout, mLiveBarLayout, mPublicBarLayout;
    private FoldingLayout mTrafficFoldingLayout, mLifeFoldingLayout, mMedicalFoldingLayout, mLiveFoldingLayout, mPublicFoldingLayout;

    private final int FOLD_ANIMATION_DURATION = 1000;

    private int mNumberOfFolds = 3;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_fold_menu;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        mTrafficLayout = (LinearLayout) findViewById(R.id.traffic_layout);
        mLifeLayout = (LinearLayout) findViewById(R.id.life_layout);
        mMedicalLayout = (LinearLayout) findViewById(R.id.medical_layout);
        mLiveLayout = (LinearLayout) findViewById(R.id.live_layout);
        mPublicLayout = (LinearLayout) findViewById(R.id.public_layout);

        mTrafficBarLayout = (RelativeLayout) findViewById(R.id.traffic_bar_layout);
        mLifeBarLayout = (RelativeLayout) findViewById(R.id.life_bar_layout);
        mMedicalBarLayout = (RelativeLayout) findViewById(R.id.medical_bar_layout);
        mLiveBarLayout = (RelativeLayout) findViewById(R.id.live_bar_layout);
        mPublicBarLayout = (RelativeLayout) findViewById(R.id.public_bar_layout);

        mTrafficFoldingLayout = ((FoldingLayout) mTrafficLayout.findViewWithTag(TAG_ITEM));
        mLifeFoldingLayout = ((FoldingLayout) mLifeLayout.findViewWithTag(TAG_ITEM));
        mMedicalFoldingLayout = ((FoldingLayout) mMedicalLayout.findViewWithTag(TAG_ITEM));
        mLiveFoldingLayout = ((FoldingLayout) mLiveLayout.findViewWithTag(TAG_ITEM));
        mPublicFoldingLayout = ((FoldingLayout) mPublicLayout.findViewWithTag(TAG_ITEM));

        mBottomView = findViewById(R.id.bottom_view);
    }

    @Override
    public void init() {
        mTrafficFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        mLifeFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        mMedicalFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        mLiveFoldingLayout.setNumberOfFolds(mNumberOfFolds);
        mPublicFoldingLayout.setNumberOfFolds(mNumberOfFolds);

        animateFold(mTrafficFoldingLayout, 350);
        setMarginToTop(1, mLifeLayout);

        animateFold(mLifeFoldingLayout, 350);
        setMarginToTop(1, mMedicalLayout);

        animateFold(mMedicalFoldingLayout, 350);
        setMarginToTop(1, mLiveLayout);

        animateFold(mLiveFoldingLayout, 350);
        setMarginToTop(1, mPublicLayout);

        animateFold(mPublicFoldingLayout, 350);
        setMarginToTop(1, mBottomView);

//        mTrafficFoldingLayout.setFoldFactor(1.0f);
//        mLifeFoldingLayout.setFoldFactor(1.0f);
//        mMedicalFoldingLayout.setFoldFactor(1.0f);
//        mLiveFoldingLayout.setFoldFactor(1.0f);
//        mPublicFoldingLayout.setFoldFactor(1.0f);
    }

    @Override
    public void setListener() {
        mTrafficBarLayout.setOnClickListener(this);
        mLifeBarLayout.setOnClickListener(this);
        mMedicalBarLayout.setOnClickListener(this);
        mLiveBarLayout.setOnClickListener(this);
        mPublicBarLayout.setOnClickListener(this);
    }

    private void handleAnimation(final View bar, final FoldingLayout foldingLayout, View parent, final View nextParent) {

        final ImageView arrow = (ImageView) parent.findViewWithTag(TAG_ARROW);

        foldingLayout.setFoldListener(new OnFoldListener() {

            @Override
            public void onStartFold(float foldFactor) {

                bar.setClickable(true);
                arrow.setBackgroundResource(R.mipmap.service_arrow_up);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onFoldingState(float foldFactor, float foldDrawHeight) {
                bar.setClickable(false);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onEndFold(float foldFactor) {

                bar.setClickable(true);
                arrow.setBackgroundResource(R.mipmap.service_arrow_down);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }
        });

        animateFold(foldingLayout, FOLD_ANIMATION_DURATION);

    }

    private void resetMarginToTop(View view, float foldFactor, View nextParent) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
        lp.topMargin = (int) (-view.getMeasuredHeight() * foldFactor) + dp2px(FoldMenuActivity.this, 10);
        nextParent.setLayoutParams(lp);
    }

    private void setMarginToTop(float foldFactor, View nextParent) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
        lp.topMargin = (int) (-dp2px(FoldMenuActivity.this, 135) * foldFactor) + dp2px(FoldMenuActivity.this, 10);
        nextParent.setLayoutParams(lp);
    }

    @SuppressLint("NewApi")
    public void animateFold(FoldingLayout foldLayout, int duration) {
        float foldFactor = foldLayout.getFoldFactor();

        ObjectAnimator animator = ObjectAnimator.ofFloat(foldLayout,
                "foldFactor", foldFactor, foldFactor > 0 ? 0 : 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    public final static int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.traffic_bar_layout:
                handleAnimation(v, mTrafficFoldingLayout, mTrafficLayout, mLifeLayout);
                break;
            case R.id.life_bar_layout:
                handleAnimation(v, mLifeFoldingLayout, mLifeLayout, mMedicalLayout);
                break;
            case R.id.medical_bar_layout:
                handleAnimation(v, mMedicalFoldingLayout, mMedicalLayout, mLiveLayout);
                break;
            case R.id.live_bar_layout:
                handleAnimation(v, mLiveFoldingLayout, mLiveLayout, mPublicLayout);
                break;
            case R.id.public_bar_layout:
                handleAnimation(v, mPublicFoldingLayout, mPublicLayout, mBottomView);
                break;
        }
    }
}