package com.volley.wjh.modelapp.UI;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.LoadingDialog.CircleProgressBar;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/16.
 * Email:1049334820@qq.com
 */
public class MeterialProgressActivity extends BaseActivity{
    private CircleProgressBar mProgressBar;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_material_progress;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        mProgressBar = (CircleProgressBar) findViewById(R.id.mProgressBar);
    }

    @Override
    public void init() {
        mProgressBar.setProgress(10);
    }

    @Override
    public void setListener() {

    }
}
