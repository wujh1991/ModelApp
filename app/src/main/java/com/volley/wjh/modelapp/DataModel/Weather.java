package com.volley.wjh.modelapp.DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wujh on 2016/2/21.
 * Email:1049334820@qq.com
 */
public class Weather extends Custom implements Serializable {
    @SerializedName("desc")
    public  String desc;

    @SerializedName("data")
    public  WeatherInfo weatherinfo;
}
