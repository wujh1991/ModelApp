package com.volley.wjh.modelapp.Adapter;

import android.view.View;
import android.view.ViewGroup;

import com.volley.wjh.modelapp.Adapter.RecyclerView.BaseRecyclerViewAdapter;
import com.volley.wjh.modelapp.DataModel.QQMenu;
import com.volley.wjh.modelapp.R;

import java.util.List;

/**
 * Created by wujh on 2016/3/6.
 * Email:1049334820@qq.com
 */
public class QQMenuAdapter extends BaseRecyclerViewAdapter<QQMenu>{

    public QQMenuAdapter(List<QQMenu> list) {
        super(list);
    }

    @Override
    protected void bindDataToItemView(SparseArrayViewHolder sparseArrayViewHolder, QQMenu item,int positon) {
        sparseArrayViewHolder.setImageResource(R.id.ivMenuIcon,item.getImgId());
        sparseArrayViewHolder.setText(R.id.tvMenuName, item.getName());
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_drag_view_left_recycler);
        SparseArrayViewHolder viewHolder = new SparseArrayViewHolder(view);
        return viewHolder;
    }
}
