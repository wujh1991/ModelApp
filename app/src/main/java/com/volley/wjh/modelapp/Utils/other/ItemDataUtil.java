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
    public static List<QQMenu> getMenuData() {
        List<QQMenu> dataList = new ArrayList<QQMenu>();
        dataList.add(new QQMenu(R.mipmap.ic_business, "开通会员"));
        dataList.add(new QQMenu(R.mipmap.ic_purse, "QQ钱包"));
        dataList.add(new QQMenu(R.mipmap.ic_decoration, "个性装扮"));
        dataList.add(new QQMenu(R.mipmap.ic_favorit, "我的收藏"));
        dataList.add(new QQMenu(R.mipmap.ic_album, "我的相册"));
        dataList.add(new QQMenu(R.mipmap.ic_file, "我的文件"));
        dataList.add(new QQMenu(R.mipmap.ic_purse, "QQ钱包"));
        dataList.add(new QQMenu(R.mipmap.ic_decoration, "个性装扮"));
        dataList.add(new QQMenu(R.mipmap.ic_favorit, "我的收藏"));
        dataList.add(new QQMenu(R.mipmap.ic_album, "我的相册"));
        dataList.add(new QQMenu(R.mipmap.ic_file, "我的文件"));
        return dataList;
    }

    public static List<QQMenu> getAddMenuData() {
        List<QQMenu> dataList = new ArrayList<QQMenu>();
        dataList.add(new QQMenu(R.mipmap.ic_decoration, "个性装扮"));
        dataList.add(new QQMenu(R.mipmap.ic_favorit, "我的收藏"));
        dataList.add(new QQMenu(R.mipmap.ic_album, "我的相册"));
        dataList.add(new QQMenu(R.mipmap.ic_file, "我的文件"));
        return dataList;
    }

    public static List<QQMenu> getItemData() {
        List<QQMenu> dataList = new ArrayList<QQMenu>();
        for (int i = 0; i < 12; i++) {
            dataList.add(new QQMenu(R.mipmap.ic_business, "会员" + i));
        }
        return dataList;
    }

    public static ArrayList<String> getBannerViewData() {
        ArrayList<String> imgUrls = new ArrayList<String>();
        imgUrls.add("http://img3.imgtn.bdimg.com/it/u=1301955051,2845257222&fm=21&gp=0.jpg");
        imgUrls.add("http://img4.imgtn.bdimg.com/it/u=760894216,1423431198&fm=21&gp=0.jpg");
        imgUrls.add("http://img5.imgtn.bdimg.com/it/u=4071158335,3702431960&fm=21&gp=0.jpg");
        imgUrls.add("http://img3.imgtn.bdimg.com/it/u=806417772,318028917&fm=21&gp=0.jpg");
        imgUrls.add("http://img2.imgtn.bdimg.com/it/u=141290284,3371500900&fm=21&gp=0.jpg");

        return imgUrls;
    }

    //下拉刷新测试数据
    public static String[] getPullToRefreshListData(){
         String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
                "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
                "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
                "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
                "Allgauer Emmentaler" };
        return mStrings;
    }


}
