package com.volley.wjh.modelapp.UI;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.volley.wjh.modelapp.Adapter.RecyclerAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.RecyclerView.RecyclerViewItemDecoration;
import com.volley.wjh.modelapp.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wujh on 2016/2/27.
 * Email:1049334820@qq.com
 */
public class SwpeRefreshLayoutActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_COMPLETE = 0X110;
    private static final int ADD_MORE_COMPLETE = 0x111;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json",
            "HTML"));

    private int lastVisibleItem = 0;
    private int total = 0;
    private boolean isSlidingToLast = false;
    private Handler mHandler;


    @Override
    public int getResourceLayout() {
        return R.layout.activity_swperefreshlayout;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sp_lv);
        recyclerView = (RecyclerView) findViewById(R.id.rv_my);
    }


    @Override
    public void init() {
        mHandler = new MyHandler(this);
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
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    //初始化SwipeRefreshLayout
    public void initSwipeRefreshLayout() {
        //设置刷新动画的颜色
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
    }

    //初始化RecyclerView
    public void initRecyclerView() {
        //创建默认的线性LayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(this));
        //创建并设置Adapter
        recyclerAdapter = new RecyclerAdapter(mDatas);
        recyclerView.setAdapter(recyclerAdapter);
    }

    //加载更多
    public void addMore(){
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    total = linearLayoutManager.getItemCount();

                    if (lastVisibleItem == total - 1 && isSlidingToLast) {
                        Toast.makeText(getApplicationContext(), "正在加载。。。", Toast.LENGTH_LONG).show();
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
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    private static class MyHandler extends Handler{
        private final WeakReference<SwpeRefreshLayoutActivity> mActivity;
        public MyHandler(SwpeRefreshLayoutActivity activity){
            mActivity = new WeakReference<SwpeRefreshLayoutActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mActivity.get() == null){
                return;
            }
            mActivity.get().toDo(msg);
        }
    }

    public void toDo(Message msg){
        switch (msg.what) {
            case REFRESH_COMPLETE:
                mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
                recyclerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                break;
            case ADD_MORE_COMPLETE:
                mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "加载完成。。。", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
