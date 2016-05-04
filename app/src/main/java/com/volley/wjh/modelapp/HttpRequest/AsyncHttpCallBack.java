package com.volley.wjh.modelapp.HttpRequest;


import com.loopj.android.http.AsyncHttpResponseHandler;
import  cz.msebera.android.httpclient.Header;

/**
 * Created by wujh on 2016/4/7.
 * Email:1049334820@qq.com
 */
public abstract class AsyncHttpCallBack extends AsyncHttpResponseHandler {

    @Override
    public void onSuccess(int statuCode, Header[] headers, byte[] bytes) {
        onRequestSuccess(statuCode, headers, bytes);
    }

    @Override
    public void onFailure(int statuCode, Header[] headers, byte[] bytes, Throwable throwable) {
        onRequestFailure(statuCode, headers, bytes, throwable);
    }

    public abstract void onRequestSuccess(int statuCode, Header[] headers, byte[] bytes);

    public abstract void onRequestFailure(int statuCode, Header[] headers, byte[] bytes, Throwable throwable);

}
