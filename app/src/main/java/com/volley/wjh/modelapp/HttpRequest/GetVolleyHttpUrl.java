package com.volley.wjh.modelapp.HttpRequest;

import com.android.volley.Request;
import com.volley.wjh.modelapp.App.MyConstant;

import java.util.HashMap;

/**
 * Created by wujh on 2016/2/22.
 * Email:1049334820@qq.com
 */
public class GetVolleyHttpUrl {

    public static String getWeatherIngo(){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("citykey","101010100");
        return VolleyHttpUrl.urlBuilder(Request.Method.GET,null,"weather_mini"
                ,hashMap);
    }

    /**
     * 获取微信AccessToken
     * @param code
     * @return
     */
    public static String getWxAccessToken(String code){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("appid",MyConstant.APP_ID);
        hashMap.put("secret", MyConstant.SECRET);
        hashMap.put("code", code);
        hashMap.put("grant_type","authorization_code");
        return VolleyHttpUrl.urlBuilder(Request.Method.GET,"https://api.weixin.qq.com/sns/oauth2/","access_token",hashMap);
    }

    public static String getWxUserInfo(String accessToken,String openId){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("access_token",accessToken);
        hashMap.put("openid", openId);
        return VolleyHttpUrl.urlBuilder(Request.Method.GET,"https://api.weixin.qq.com/sns/","userinfo",hashMap);
    }
}
