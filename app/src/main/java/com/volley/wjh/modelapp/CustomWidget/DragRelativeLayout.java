package com.volley.wjh.modelapp.CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wujh on 2016/3/6.
 * Email:1049334820@qq.com
 */
public class DragRelativeLayout extends RelativeLayout {
    private DragLayout mDragLayout;

    public DragRelativeLayout(Context context) {
        super(context);
    }

    public DragRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mDragLayout != null && mDragLayout.getStatus() != DragLayout.Status.Close){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mDragLayout != null && mDragLayout.getStatus() != DragLayout.Status.Close){
            if(event.getAction() == MotionEvent.ACTION_UP){
                mDragLayout.close();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setDragLayout(DragLayout dragLayout){
        mDragLayout = dragLayout;
    }
}
