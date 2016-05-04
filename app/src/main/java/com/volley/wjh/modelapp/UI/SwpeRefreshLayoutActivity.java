package com.volley.wjh.modelapp.UI;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.volley.wjh.modelapp.Adapter.PullToRefreshAndLoadMore.BaseSwpeRefreshLayoutAndLoadMoreAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.Base.LeakHandler.BaseHandler;
import com.volley.wjh.modelapp.CustomWidget.MSwpeRefreshLayout;
import com.volley.wjh.modelapp.DataModel.QQMenu;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Log.LogUtils;
import com.volley.wjh.modelapp.Utils.Network.NetworkUtil;
import com.volley.wjh.modelapp.Utils.other.ItemDataUtil;

import java.util.List;

/**
 * Created by wujh on 2016/2/27.
 * Email:1049334820@qq.com
 */
public class SwpeRefreshLayoutActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = SwpeRefreshLayoutActivity.class.getSimpleName();
    private static final int REFRESH_COMPLETE = 0X110;
    private static final int ADD_MORE_COMPLETE = 0x111;

    private static final int REQUEST_DATA_SUCCESS = 0x112;

    private MSwpeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BaseSwpeRefreshLayoutAndLoadMoreAdapter swAdapter;
    private List<QQMenu> qlist;
    private int totalCount = 18;

    private int lastVisibleItem = 0;
    private int total = 0;
    private boolean isSlidingToLast = false;
    private Handler mHandler;

    //数据为空
    private LinearLayout llNotData;
    //没有网络的情况
    private LinearLayout llNotNetwork;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_swperefreshlayout;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        swipeRefreshLayout = (MSwpeRefreshLayout) findViewById(R.id.sp_lv);
        recyclerView = (RecyclerView) findViewById(R.id.rv_my);

        llNotData = (LinearLayout) findViewById(R.id.llNotData);
        llNotNetwork = (LinearLayout) findViewById(R.id.llNotNetwork);
    }


    @Override
    public void init() {
        mHandler = new MHandler(this);
        initSwipeRefreshLayout();
        initRecyclerView();
        addMore();
    }

    @Override
    public void setListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        cleanData();
        if (NetworkUtil.isNetworkAvailable(SwpeRefreshLayoutActivity.this)) {
            setAdapter();
        } else {
            swipeRefreshLayout.setRefreshing(false);
            llNotData.setVisibility(View.GONE);
            llNotNetwork.setVisibility(View.VISIBLE);
        }
    }

    //初始化SwipeRefreshLayout
    public void initSwipeRefreshLayout() {
        //设置刷新动画的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorQqTheme, R.color.colorAccent, R.color.colorCardView, R.color.colorPrimary);
    }

    //初始化RecyclerView
    public void initRecyclerView() {
        //创建默认的线性LayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //设置分隔线
//        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(this));
        setAdapter();
    }

    //创建并设置Adapter
    public void setAdapter() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(qlist == null){
                    qlist = ItemDataUtil.getMenuData();
                }else{
                    qlist.addAll(ItemDataUtil.getMenuData());
                }
                mHandler.sendEmptyMessage(REQUEST_DATA_SUCCESS);
            }
        }, 2000);
    }

    //加载更多
    public void addMore() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    total = linearLayoutManager.getItemCount();

                    if (lastVisibleItem == total - 1 && isSlidingToLast && lastVisibleItem < totalCount) {
                        swAdapter.setFooterVisiable(true);
                        mHandler.sendEmptyMessageDelayed(ADD_MORE_COMPLETE, 5000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < recyclerView.getHeight() && dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }

                //解决SwipeRefreshLayout和RecyclerView的滑动冲突
                int topRowPosition = (recyclerView == null || recyclerView.getChildCount() == 0)
                        ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowPosition >= 0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void toDo(Message msg) {
        switch (msg.what) {
            case REQUEST_DATA_SUCCESS:
                swipeRefreshLayout.setRefreshing(false);
                LogUtils.v(TAG,"net:" + NetworkUtil.isNetworkAvailable(SwpeRefreshLayoutActivity.this));
                if (qlist == null || qlist.size() == 0) {
                    llNotNetwork.setVisibility(View.GONE);
                    llNotData.setVisibility(View.VISIBLE);
                } else {
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                }
                break;
            case REFRESH_COMPLETE:
                llNotData.setVisibility(View.GONE);
                llNotNetwork.setVisibility(View.GONE);
                if (swAdapter == null) {
                    swAdapter = new BaseSwpeRefreshLayoutAndLoadMoreAdapter(qlist, totalCount);
                    recyclerView.setAdapter(swAdapter);
                }
                updateData();
                break;
            case ADD_MORE_COMPLETE:
                if (qlist.size() < totalCount) {
                    qlist.addAll(ItemDataUtil.getAddMenuData());
                    swAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    //清空数据
    private void cleanData() {
        if (qlist != null) {
            qlist.clear();
        }
        if (swAdapter != null) {
            swAdapter.setFooterVisiable(false);
            swAdapter.notifyDataSetChanged();
        }
    }

    //更新数据
    private void updateData() {
        if (swAdapter != null) {
            swAdapter.setFooterVisiable(true);
            swAdapter.notifyDataSetChanged();
        }
    }


    private class MHandler extends BaseHandler<Activity> {

        public MHandler(Activity reference) {
            super(reference);
        }

        @Override
        public void doHandleMessage(Activity reference, Message msg) {
            toDo(msg);
        }
    }

}
