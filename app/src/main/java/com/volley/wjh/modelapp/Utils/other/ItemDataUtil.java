package com.volley.wjh.modelapp.Utils.other;

import com.volley.wjh.modelapp.DataModel.QQMenu;
import com.volley.wjh.modelapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujh on 2016/3/6.
 * Email:1049334820@qq.com
 */
public class ItemDataUtil {
    public static List<QQMenu> getMenuData(){
        List<QQMenu> dataList = new ArrayList<QQMenu>();
        dataList.add(new QQMenu(R.mipmap.ic_business,"开通会员"));
        dataList.add(new QQMenu(R.mipmap.ic_purse,"QQ钱包"));
        dataList.add(new QQMenu(R.mipmap.ic_decoration,"个性装扮"));
        dataList.add(new QQMenu(R.mipmap.ic_favorit,"我的收藏"));
        dataList.add(new QQMenu(R.mipmap.ic_album,"我的相册"));
        dataList.add(new QQMenu(R.mipmap.ic_file,"我的文件"));
        return dataList;
    }
}
