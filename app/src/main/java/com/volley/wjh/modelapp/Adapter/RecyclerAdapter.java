package com.volley.wjh.modelapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.volley.wjh.modelapp.R;

import java.util.List;

/**
 * Created by wujh on 2016/2/28.
 * Email:1049334820@qq.com
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_ITEM = 0;
    private static int TYPE_FOOTER = 1;

    private List<String> mDataSet;

    private int totalSize;

    public RecyclerAdapter(List<String> mDataSet,int totalSize){
        this.mDataSet = mDataSet;
        this.totalSize = totalSize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
            viewHolder = new ItemViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_footer,parent,false);
            viewHolder = new FooterViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ((ItemViewHolder)holder).tvName.setText(mDataSet.get(position));
        }else{
            if(totalSize <= getItemCount()-1){
                ((FooterViewHolder)holder).pbFooter.setVisibility(View.GONE);
                ((FooterViewHolder)holder).tvFooter.setText("数据完成全部加载");
            }else{
                ((FooterViewHolder)holder).pbFooter.setVisibility(View.VISIBLE);
                ((FooterViewHolder)holder).tvFooter.setText("数据加载中...");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar pbFooter;
        public TextView tvFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            pbFooter = (ProgressBar) itemView.findViewById(R.id.pbFooter);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.rcv_name);
        }
    }
}
