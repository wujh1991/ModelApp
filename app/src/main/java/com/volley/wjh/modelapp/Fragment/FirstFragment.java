package com.volley.wjh.modelapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/12.
 * Email:1049334820@qq.com
 */
public class FirstFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_first;
    }

    @Override
    public void findViews(View rootView) {

    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {

    }
}
