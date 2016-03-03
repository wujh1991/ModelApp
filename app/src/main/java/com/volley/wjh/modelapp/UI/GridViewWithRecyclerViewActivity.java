package com.volley.wjh.modelapp.UI;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.volley.wjh.modelapp.Adapter.GridViewAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.WaterFallView.SpaceItemDecoraTion;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/2/29.
 * Email:1049334820@qq.com
 */
public class GridViewWithRecyclerViewActivity extends BaseActivity{
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private GridViewAdapter gridViewAdapter;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_gridview_with_recyclerview;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        recyclerView = (RecyclerView)findViewById(R.id.rcy_grid);

    }

    @Override
    public void init() {
        initRecyclerView();
    }

    @Override
    public void setListener() {

    }

    public void initRecyclerView(){
        gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoraTion(10));
        gridViewAdapter = new GridViewAdapter(new int[]{R.mipmap.gril_one,R.mipmap.gril_two,R.mipmap.gril_three,R.mipmap.gril_four,R.mipmap.gril_five,R.mipmap.gril_six
                ,R.mipmap.gril_seven,R.mipmap.gril_eight,R.mipmap.gril_nine,R.mipmap.gril_tent,R.mipmap.gril_eleven,R.mipmap.gril_tween,R.mipmap.gril_fourty,
                R.mipmap.gril_fifty,R.mipmap.gril_sixty,R.mipmap.gril_seventy});
        recyclerView.setAdapter(gridViewAdapter);
    }
}
