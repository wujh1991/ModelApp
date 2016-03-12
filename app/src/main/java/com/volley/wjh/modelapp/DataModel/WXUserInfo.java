package com.volley.wjh.modelapp.DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wujh on 2016/3/8.
 * Email:1049334820@qq.com
 */
public class WXUserInfo implements Serializable {
    @SerializedName("openid")
    public String openid;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("sex")
    public String sex;

    @SerializedName("province")
    public String province;

    @SerializedName("city")
    public String city;

    @SerializedName("country")
    public String country;

    @SerializedName("headimgurl")
    public String headimgurl;

    @SerializedName("privilege")
    public List<String> privilege;

    @SerializedName("unionid")
    public String unionid;

}
