package com.volley.wjh.modelapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volley.wjh.modelapp.DataModel.Gril;
import com.volley.wjh.modelapp.R;

import java.util.List;

/**
 * Created by wujh on 2016/3/1.
 * Email:1049334820@qq.com
 */
public class WaterFallViewAdapter extends RecyclerView.Adapter<WaterFallViewAdapter.ViewHoler> {
    private List<Gril> mData;

    public WaterFallViewAdapter(List<Gril> mData){
        this.mData = mData;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waterfallview_with_recyclerview,parent,false);
        ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.ivGril.setImageResource(mData.get(position).getImg());
        holder.tvName.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHoler extends RecyclerView.ViewHolder{
        public ImageView ivGril;
        public TextView tvName;

        public ViewHoler(View itemView) {
            super(itemView);
            ivGril = (ImageView)itemView.findViewById(R.id.iv_gril);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
        }
    }
}
