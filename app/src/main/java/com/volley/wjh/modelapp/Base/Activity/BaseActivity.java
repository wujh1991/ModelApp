package com.volley.wjh.modelapp.Base.Activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by wujh on 2016/2/22.
 * Email:1049334820@qq.com
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        getExtra();
        findViews();
        init();
        setListener();
    }

    public abstract int getResourceLayout();

    public abstract  void getExtra();

    public abstract void findViews();

    public  abstract void init();

    public  abstract void setListener();
}
