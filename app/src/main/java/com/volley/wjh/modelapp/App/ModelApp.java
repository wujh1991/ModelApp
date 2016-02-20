package com.volley.wjh.modelapp.App;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wujh on 2016/2/20.
 * Email:1049334820@qq.com
 */
public class ModelApp extends Application {
    private static ModelApp mInstance;
    private static Context mContext;
    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueue(){
        return mRequestQueue;
    }

    private ModelApp(Context context){
        mContext = context;
    }

    public static synchronized ModelApp getInstance(Context context){
        if(mInstance == null){
            mInstance = new ModelApp(context);
        }

        return mInstance;
    }
}
