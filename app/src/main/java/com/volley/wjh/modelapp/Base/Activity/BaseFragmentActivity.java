package com.volley.wjh.modelapp.Base.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by wujh on 2016/3/12.
 * Email:1049334820@qq.com
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        findViews();
        init();
        setListener();
    }

    public abstract int getResourceLayout();

    public abstract void findViews();

    public abstract  void init();

    public abstract  void setListener();

}
