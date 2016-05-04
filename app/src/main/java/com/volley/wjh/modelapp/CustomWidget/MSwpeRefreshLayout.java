package com.volley.wjh.modelapp.CustomWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/4/14.
 * Email:1049334820@qq.com
 */
public class MSwpeRefreshLayout extends SwipeRefreshLayout{
    private int mScrollableChildId;
    private View mScrollableChild;

    public MSwpeRefreshLayout(Context context) {
       this(context,null);
    }

    public MSwpeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.CustomSwipeRefreshLayout);
        mScrollableChildId = typedArray.getResourceId(R.styleable.CustomSwipeRefreshLayout_scrollableChildId,0);
        mScrollableChild = findViewById(mScrollableChildId);
        typedArray.recycle();
    }

    @Override
    public boolean canChildScrollUp() {
        //判断有没有传入子控件
        ensureScrollableChild();

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mScrollableChild instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mScrollableChild;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mScrollableChild, -1) || mScrollableChild.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mScrollableChild, -1);
        }
    }

    private void ensureScrollableChild() {
        if (mScrollableChild == null) {
            mScrollableChild = findViewById(mScrollableChildId);
        }
    }
}
