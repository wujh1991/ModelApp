package com.volley.wjh.modelapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.volley.wjh.modelapp.UI.WaterFallViewWithRecyclerViewActivity;

import java.util.ArrayList;

/**
 * Created by wujh on 2016/3/31.
 * Email:1049334820@qq.com
 */
public class BannerPagerAdapter extends PagerAdapter implements View.OnClickListener{
    private ArrayList<ImageView> imageViews;
    private Context mContext;

    public BannerPagerAdapter(Context context,ArrayList<ImageView> imageViews){
        this.mContext = context;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViews.get(position % imageViews.size());
        container.addView(imageView);
        setOnClickListener(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public int getRealCount(){
        return imageViews.size();
    }

    public void setOnClickListener(ImageView imageView){
       imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext, WaterFallViewWithRecyclerViewActivity.class));
    }
}
