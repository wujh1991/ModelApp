package com.volley.wjh.modelapp.UI;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.volley.wjh.modelapp.Adapter.WaterFallViewAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.WaterFallView.SpaceItemDecoraTion;
import com.volley.wjh.modelapp.DataModel.Gril;
import com.volley.wjh.modelapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujh on 2016/3/1.
 * Email:1049334820@qq.com
 */
public class WaterFallViewWithRecyclerViewActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private WaterFallViewAdapter waterFallViewAdapter;
    private List<Gril> mData;
    private Gril gril;
    private int[] gImg = {R.mipmap.gril_one,R.mipmap.gril_two,R.mipmap.gril_three,R.mipmap.gril_four,R.mipmap.gril_five,R.mipmap.gril_six
    ,R.mipmap.gril_seven,R.mipmap.gril_eight,R.mipmap.gril_nine,R.mipmap.gril_tent,R.mipmap.gril_eleven,R.mipmap.gril_tween,R.mipmap.gril_fourty,
            R.mipmap.gril_fifty,R.mipmap.gril_sixty,R.mipmap.gril_seventy};

    @Override
    public int getResourceLayout() {
        return R.layout.activity_waterfallview_with_recyclerview;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        recyclerView = (RecyclerView)findViewById(R.id.rcv_water_fall);
    }

    @Override
    public void init() {
        initData();
        initRecyclerView();
    }

    @Override
    public void setListener() {

    }

    public void initRecyclerView(){
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoraTion(5));
        waterFallViewAdapter = new WaterFallViewAdapter(mData);
        recyclerView.setAdapter(waterFallViewAdapter);
    }

    public void initData(){
        mData = new  ArrayList<Gril>();
        for(int i = 0 ; i < gImg.length;i++){
            gril = new Gril();
            gril.setImg(gImg[i]);
            gril.setName("美女" +i );

            mData.add(gril);
        }
    }
}
