package com.volley.wjh.modelapp.Adapter.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volley.wjh.modelapp.Interface.OnRecyclerViewItemClickListener;
import com.volley.wjh.modelapp.Interface.OnRecyclerViewItemLongClickListener;

import java.util.List;


/**
 * Created by wujh on 2016/3/6.
 * Email:1049334820@qq.com
 */
public abstract class BaseRecyclerViewAdapter<T,VH extends BaseRecyclerViewAdapter.SparseArrayViewHolder> extends RecyclerView.Adapter<VH>{

    protected OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    protected OnRecyclerViewItemLongClickListener mOnRecyclerViewItemLongClickListener;

    private List<T> mList;

    public BaseRecyclerViewAdapter(List<T> list){
        this.mList = list;
    }

    protected  T getItem(int position){
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setmOnRecyclerViewItemLongClickListener(OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener){
        this.mOnRecyclerViewItemLongClickListener = onRecyclerViewItemLongClickListener;
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    @Override
    public final void onBindViewHolder(VH vh, int position) {
        final T item = getItem(position);
        bindDataToItemView(vh, item,position);
        bindItemViewClickListener(vh, item,position);
    }



    protected abstract void bindDataToItemView(VH vh, T item,int position);

    protected final void bindItemViewClickListener(VH vh, final T item, final int position) {
        if (mOnRecyclerViewItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerViewItemClickListener.onClick(view, item,position);
                }
            });
        }
        if (mOnRecyclerViewItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnRecyclerViewItemLongClickListener.onLongClick(v, item,position);
                    return true;
                }
            });
        }
    }

    public static class SparseArrayViewHolder extends RecyclerView.ViewHolder{
        private final SparseArray<View> views;

        public SparseArrayViewHolder(View itemView){
            super(itemView);
            views = new SparseArray<View>();
        }

        public <T extends View> T getView(int id){
            View view = views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.put(id,view);
            }
            return (T) view;
        }

        public SparseArrayViewHolder setText(int viewId, String value) {
            TextView view = getView(viewId);
            view.setText(value);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = getView(viewId);
            view.setImageResource(imageResId);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundResource(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return SparseArrayViewHolder.this;
        }
    }
}
