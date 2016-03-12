package com.volley.wjh.modelapp.DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wujh on 2016/3/8.
 * Email:1049334820@qq.com
 */
public class WXAccessToken implements Serializable{
    @SerializedName("access_token")
    public String access_token;

    @SerializedName("expires_in")
    public String expires_in;

    @SerializedName("refresh_token")
    public String refresh_token;

    @SerializedName("openid")
    public String openid;

    @SerializedName("scope")
    public String scope;

    @SerializedName("unionid")
    public String unionid;
}
