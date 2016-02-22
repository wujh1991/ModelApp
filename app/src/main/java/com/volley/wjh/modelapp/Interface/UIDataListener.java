package com.volley.wjh.modelapp.Interface;

/**
 * Created by wujh on 2016/2/20.
 * Email:1049334820@qq.com
 */
public interface UIDataListener<T> {
    public void onSuccess(T data);
    public void onError(String errorCode,String errorMessage);
}
