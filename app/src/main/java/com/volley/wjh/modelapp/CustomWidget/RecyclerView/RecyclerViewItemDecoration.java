package com.volley.wjh.modelapp.CustomWidget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wujh on 2016/2/28.
 * Email:1049334820@qq.com
 */
public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTR = {android.R.attr.listDivider};
    private Drawable mDivider;

    public RecyclerViewItemDecoration(Context context){
        TypedArray typedArray = context.obtainStyledAttributes(ATTR);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for(int i = 0;i < count;i++){
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params =  (RecyclerView.LayoutParams)view.getLayoutParams();
            int top = view.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }


}
