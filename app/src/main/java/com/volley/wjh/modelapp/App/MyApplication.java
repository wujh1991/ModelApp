package com.volley.wjh.modelapp.App;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by wujh on 2016/2/20.
 * Email:1049334820@qq.com
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static Context mContext;
    public static RequestQueue mRequestQueue;

    public static IWXAPI mWxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        registerWX();
    }

    public static RequestQueue getHttpQueue(){
        return mRequestQueue;
    }

    public void registerWX(){
        mWxapi = WXAPIFactory.createWXAPI(getApplicationContext(),"APPID");
        mWxapi.registerApp("APPID");
    }

//    private MyApplication(Context context){
//        mContext = context;
//    }
//
//    public static synchronized MyApplication getInstance(Context context){
//        if(mInstance == null){
//            mInstance = new MyApplication(context);
//        }
//
//        return mInstance;
//    }
}
