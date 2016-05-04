package com.volley.wjh.modelapp.CustomWidget.SwipeView;

import android.app.Activity;
import android.os.Bundle;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;

/**
 * Created by wujh on 2016/4/20.
 * Email:1049334820@qq.com
 * 侧滑退出基类Activity
 */
public abstract class BaseSwipeBackActivity extends BaseActivity{
    private SwipeLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToActivity(this);
    }

    public  void attachToActivity(Activity activity){
        swipeLayout = new SwipeLayout(this);
        swipeLayout.attachToActivity(activity);
    }
}
