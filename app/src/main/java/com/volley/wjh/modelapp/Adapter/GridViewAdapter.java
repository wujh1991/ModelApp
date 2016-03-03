package com.volley.wjh.modelapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/2/29.
 * Email:1049334820@qq.com
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHoler> {
    private int[] img;

    public GridViewAdapter(int[] img){
        this.img = img;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview_with_recyclerview,parent,false);
        ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.ivItemOne.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public static class ViewHoler extends  RecyclerView.ViewHolder{
        public ImageView ivItemOne;

        public ViewHoler(View itemView) {
            super(itemView);
            ivItemOne = (ImageView)itemView.findViewById(R.id.iv_item_one);
        }
    }
}
