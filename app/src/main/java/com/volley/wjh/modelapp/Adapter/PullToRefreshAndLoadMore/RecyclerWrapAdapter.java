package com.volley.wjh.modelapp.Adapter.PullToRefreshAndLoadMore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.volley.wjh.modelapp.Adapter.RecyclerView.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujh on 2016/3/22.
 * Email:1049334820@qq.com
 */
public  class RecyclerWrapAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.SparseArrayViewHolder> implements WrapperAdapter {
    private BaseRecyclerViewAdapter mAdapter;

    private ArrayList<View> mHeaderViews;

    private ArrayList<View> mFooterViews;

    static final ArrayList<View> EMPTY_INFO_LIST =
            new ArrayList<View>();


    public RecyclerWrapAdapter(List<T> list,BaseRecyclerViewAdapter adpter, ArrayList<View> headerViews, ArrayList<View> footerViews) {
        this.mAdapter = adpter;
        if (headerViews == null) {
            this.mHeaderViews = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViews = headerViews;
        }

        if (footerViews == null) {
            this.mFooterViews = EMPTY_INFO_LIST;
        } else {
            this.mFooterViews = footerViews;
        }
    }

    public int getHeaderCount() {
        if(mHeaderViews != null){
            return mHeaderViews.size();
        }else{
            return 0;
        }
    }

    public int getFooterCount() {
        if(mFooterViews != null){
            return mFooterViews.size();
        }else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeaderCount();
        if (position >= numHeaders) {
            if (mAdapter != null) {
                int adjPosition = position - numHeaders;
                int adapterCount = mAdapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItemViewType(adjPosition);
                }
            }
        } else {
            return RecyclerView.INVALID_TYPE;
        }

        return RecyclerView.INVALID_TYPE - 1;
    }

    @Override
    public long getItemId(int position) {
        int numHeaders = getHeaderCount();
        if (mAdapter != null && position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adpterCount = mAdapter.getItemCount();
            if (adjPosition < adpterCount) {
                return mAdapter.getItemId(adjPosition);
            }
        }
        return RecyclerView.INVALID_TYPE;
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getHeaderCount() + getFooterCount() + mAdapter.getItemCount();
        } else {
            return getHeaderCount() + getFooterCount();
        }
    }

    @Override
    public RecyclerView.Adapter getWrappedAdapter() {
        return mAdapter;
    }

    @Override
    public BaseRecyclerViewAdapter.SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new BaseRecyclerViewAdapter.SparseArrayViewHolder(mHeaderViews.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new BaseRecyclerViewAdapter.SparseArrayViewHolder(mFooterViews.get(0));
        }
        return null;
    }


    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.SparseArrayViewHolder holder, int position) {
        int numHeaders = getHeaderCount();
        if (position < numHeaders) {
            return;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
            }
        }
    }
}
