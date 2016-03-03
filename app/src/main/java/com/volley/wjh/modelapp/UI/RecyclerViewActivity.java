package com.volley.wjh.modelapp.UI;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.volley.wjh.modelapp.Adapter.RecyclerAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.RecyclerView.RecyclerViewItemDecoration;
import com.volley.wjh.modelapp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wujh on 2016/2/28.
 * Email:1049334820@qq.com
 */
public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        recyclerView = (RecyclerView)findViewById(R.id.rv_my);
    }

    @Override
    public void init() {
        //创建默认的线性LayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(this));
        //创建并设置Adapter
        recyclerView.setAdapter(new RecyclerAdapter(new ArrayList<String>(Arrays.asList("java","c++","html","json"))));


    }

    @Override
    public void setListener() {

    }
}
