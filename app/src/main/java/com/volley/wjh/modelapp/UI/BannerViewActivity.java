package com.volley.wjh.modelapp.UI;

import android.widget.ImageView;

import com.volley.wjh.modelapp.Adapter.BannerPagerAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.BannerView;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Image.ImageUtil;
import com.volley.wjh.modelapp.Utils.other.ItemDataUtil;

import java.util.ArrayList;

/**
 * Created by wujh on 2016/4/3.
 * Email:1049334820@qq.com
 */
public class BannerViewActivity extends BaseActivity{
    private BannerView bvHome;
    private BannerPagerAdapter bannerPagerAdapter;

    private int[] ids = {R.mipmap.gril_one,R.mipmap.gril_two,R.mipmap.gril_three,R.mipmap.gril_four,R.mipmap.gril_five};
    private String[] titles = {"美女一","美女二","美女三","美女四","美女五"};
    private ArrayList<ImageView> images;
    //网络图片
    private ArrayList<String> imgUrls;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_banner_view;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        bvHome = (BannerView) findViewById(R.id.bvHome);
    }

    @Override
    public void init() {
        //网络图片
//        imgUrls = ItemDataUtil.getBannerViewData();

        images = new ArrayList<ImageView>();
        for(int i = 0 ; i < ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
//            ImageUtil.displayImage(imageView,imgUrls.get(i));
            images.add(imageView);
        }

        bannerPagerAdapter = new BannerPagerAdapter(this,images);
        bvHome.setAdapter(bannerPagerAdapter, titles);
    }

    @Override
    public void setListener() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bvHome.startView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bvHome.stopView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bvHome.removeHandlerMessages();
    }
}
