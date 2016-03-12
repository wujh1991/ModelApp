package com.volley.wjh.modelapp.DataModel;

/**
 * Created by wujh on 2016/3/6.
 * Email:1049334820@qq.com
 */
public class QQMenu {
    private int imgId;
    private String name;

    public QQMenu(int imgId,String name){
        this.imgId = imgId;
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
