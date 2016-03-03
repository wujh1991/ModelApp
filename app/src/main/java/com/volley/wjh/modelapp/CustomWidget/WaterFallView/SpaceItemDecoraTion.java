package com.volley.wjh.modelapp.CustomWidget.WaterFallView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wujh on 2016/3/1.
 * Email:1049334820@qq.com
 */
public class SpaceItemDecoraTion extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoraTion(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = space;
        }
    }
}
