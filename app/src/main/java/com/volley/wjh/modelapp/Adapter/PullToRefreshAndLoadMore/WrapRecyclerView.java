package com.volley.wjh.modelapp.Adapter.PullToRefreshAndLoadMore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.volley.wjh.modelapp.Adapter.RecyclerView.BaseRecyclerViewAdapter;
import com.volley.wjh.modelapp.DataModel.QQMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujh on 2016/3/22.
 * Email:1049334820@qq.com
 */
public class WrapRecyclerView  extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<View>();

    private ArrayList<View> mFooterViews = new ArrayList<View>();

    private BaseRecyclerViewAdapter mAdapter;

    private List<QQMenu> mList;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view,List<QQMenu> mList){
        mHeaderViews.clear();
        mHeaderViews.add(view);

        if(mAdapter != null){
//            if(!(mAdapter instanceof PullToRefreshAndLoadMoreAdapter))
//                 mAdapter = new PullToRefreshAndLoadMoreAdapter(mList,mAdapter,mHeaderViews,mFooterViews);
//                mAdapter.notifyDataSetChanged();
        }

        this.mList = mList;
    }

    public void addFooterView(View view,List<QQMenu> mList){
        mFooterViews.clear();
        mFooterViews.add(view);

        if(mAdapter != null){
//            if(!(mAdapter instanceof RecyclerWrapAdapter))
//                mAdapter = new PullToRefreshAndLoadMoreAdapter(mList,mAdapter,mHeaderViews,mFooterViews);
//                mAdapter.notifyDataSetChanged();
        }

        this.mList = mList;
    }

    public void setAdapter(BaseRecyclerViewAdapter adapter) {
        if(mHeaderViews.isEmpty() && mFooterViews.isEmpty()){
            super.setAdapter(adapter);
        }else{
//            adapter = new PullToRefreshAndLoadMoreAdapter(mList,adapter,mHeaderViews,mFooterViews);
//            super.setAdapter(adapter);
        }
        mAdapter = adapter;
    }
}
