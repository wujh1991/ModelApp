package com.volley.wjh.modelapp.Interface;

import android.view.View;

/**
 * Created by wujh on 2016/3/7.
 * Email:1049334820@qq.com
 */
public interface OnRecyclerViewItemLongClickListener<T> {
    void onLongClick(View view,T item,int position);
}
