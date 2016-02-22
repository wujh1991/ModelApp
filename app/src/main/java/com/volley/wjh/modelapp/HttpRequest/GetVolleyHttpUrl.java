package com.volley.wjh.modelapp.HttpRequest;

import com.android.volley.Request;

import java.util.HashMap;

/**
 * Created by wujh on 2016/2/22.
 * Email:1049334820@qq.com
 */
public class GetVolleyHttpUrl {

    public static String getWeatherIngo(){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("citykey","101010100");
        return VolleyHttpUrl.urlBuilder(Request.Method.GET,"weather_mini"
                ,hashMap);
    }
}
