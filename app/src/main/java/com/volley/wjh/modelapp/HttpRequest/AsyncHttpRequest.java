package com.volley.wjh.modelapp.HttpRequest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by wujh on 2016/4/7.
 * Email:1049334820@qq.com
 */
public class AsyncHttpRequest {
    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void doGetRequest(String url,AsyncHttpCallBack asyncHttpCallBack){
        client.get(url,asyncHttpCallBack);
    }

    public static void doPostRequest(String url,RequestParams params,AsyncHttpCallBack asyncHttpCallBack){
        client.post(url,params,asyncHttpCallBack);
    }
}
