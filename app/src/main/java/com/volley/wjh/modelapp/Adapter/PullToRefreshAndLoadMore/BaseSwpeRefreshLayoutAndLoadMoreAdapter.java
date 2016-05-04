package com.volley.wjh.modelapp.Adapter.PullToRefreshAndLoadMore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volley.wjh.modelapp.Adapter.RecyclerView.BaseRecyclerViewAdapter;
import com.volley.wjh.modelapp.DataModel.QQMenu;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Log.LogUtils;

import java.util.List;

/**
 * Created by wujh on 2016/4/11.
 * Email:1049334820@qq.com
 */
public class BaseSwpeRefreshLayoutAndLoadMoreAdapter extends BaseRecyclerViewAdapter<QQMenu> {
    private static final String TAG = BaseSwpeRefreshLayoutAndLoadMoreAdapter.class.getSimpleName();
    private static int TYPE_ITEM = 0;
    private static int TYPE_FOOTER = 1;

    private List<QQMenu> mDataSet;
    private int totalSize;
    private SparseArrayViewHolder mFooterVh;

    public BaseSwpeRefreshLayoutAndLoadMoreAdapter(List<QQMenu> list, int totalSize) {
        super(list);
        this.mDataSet = list;
        this.totalSize = totalSize;
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SparseArrayViewHolder viewHolder = null;
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_content, parent, false);
            viewHolder = new SparseArrayViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_footer, parent, false);
            viewHolder = new SparseArrayViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    protected void bindDataToItemView(SparseArrayViewHolder vh, QQMenu item, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            vh.setImageResource(R.id.ivMenuIcon, item.getImgId());
            vh.setText(R.id.tvMenuName, item.getName());
        } else {
            LogUtils.v(TAG,"count------------->" + (getItemCount() - 1));
            mFooterVh = vh;
            if (totalSize <= getItemCount() - 1) {
                vh.setVisible(R.id.pbFooter, false);
                vh.setText(R.id.tvFooter, "数据已全部加载");
            } else {
                vh.setVisible(R.id.pbFooter, true);
                vh.setText(R.id.tvFooter, "数据加载中...");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setFooterVisiable(boolean isVisiable) {
        if (mFooterVh != null) {
            mFooterVh.setVisible(R.id.rlFooter, isVisiable);
        }
    }
}
