package com.volley.wjh.modelapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volley.wjh.modelapp.Adapter.RecyclerView.BaseRecyclerViewAdapter;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/3/7.
 * Email:1049334820@qq.com
 */
public class QQMenuVH extends BaseRecyclerViewAdapter.SparseArrayViewHolder {
    public ImageView ivMenuIcon;
    public TextView tvMenuName;

    public QQMenuVH(View itemView) {
        super(itemView);
        ivMenuIcon = (ImageView)itemView.findViewById(R.id.ivMenuIcon);
        tvMenuName = (TextView)itemView.findViewById(R.id.tvMenuName);
    }
}
