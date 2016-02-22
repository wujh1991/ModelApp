package com.volley.wjh.modelapp.DataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wujh on 2016/2/21.
 * Email:1049334820@qq.com
 */
public class Custom implements Serializable {
    @SerializedName("status")
    public  String status;

    @SerializedName("message")
    public String message;
}
