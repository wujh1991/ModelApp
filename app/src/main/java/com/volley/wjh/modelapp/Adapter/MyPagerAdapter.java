package com.volley.wjh.modelapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;

import java.util.List;

/**
 * Created by wujh on 2016/3/10.
 * Email:1049334820@qq.com
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList;

    public MyPagerAdapter(FragmentManager fm,List<BaseFragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
