package com.volley.wjh.modelapp.DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wujh on 2016/2/21.
 * Email:1049334820@qq.com
 */
public class WeatherInfo implements Serializable {

    @SerializedName("city")
    public String city;

    @SerializedName("wendu")
    public String wendu;

    @SerializedName("ganmao")
    public String ganmao;

    @SerializedName("aqi")
    public String aqi;
}
