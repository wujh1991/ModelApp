package com.volley.wjh.modelapp.Utils.Log;

import android.util.Log;

/**
 * Created by wujh on 2016/4/14.
 * Email:1049334820@qq.com
 */
public final class LogUtils {

    public static void v(String tag,String msg){
        Log.v(tag,msg);
    }

    public static void d(String tag,String msg){
        Log.d(tag, msg);
    }

    public static void i(String tag,String msg) {
        Log.i(tag, msg);
    }

    public static void w(String tag,String msg){
        Log.w(tag, msg);
    }

    public static void e(String tag,String msg) {
        Log.e(tag, msg);
    }

}
