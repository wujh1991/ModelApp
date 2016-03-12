package com.volley.wjh.modelapp.Interface;

import android.view.View;

/**
 * Created by wujh on 2016/2/28.
 * Email:1049334820@qq.com
 */
public interface OnRecyclerViewItemClickListener<T> {
    void onClick(View view,T item,int position);
}
