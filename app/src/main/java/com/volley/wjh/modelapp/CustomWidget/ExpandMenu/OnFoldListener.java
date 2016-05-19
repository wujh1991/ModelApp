package com.volley.wjh.modelapp.CustomWidget.ExpandMenu;

/**
 * Created by wujh on 2016/5/5.
 * Email:1049334820@qq.com
 */
public interface OnFoldListener {
    public void onStartFold(float foldFactor);

    public void onFoldingState(float foldFactor, float foldDrawHeight);

    public void onEndFold(float foldFactor);
}

