package com.volley.wjh.modelapp.CustomWidget.PullToRefresh2;

import android.os.Handler;
import android.os.Message;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.Base.LeakHandler.BaseHandler;
import com.volley.wjh.modelapp.CustomWidget.PullToRefresh2.PullToRefreshLayout.OnRefreshListener;
import com.volley.wjh.modelapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujh on 2016/5/8.
 * Email:1049334820@qq.com
 */
public class TestActivity extends BaseActivity implements PullableListView.OnLoadListener {
    private PullableListView listView;
    private PullToRefreshLayout ptrlList;
    private MyAdapter adapter;
    private MHandler mHandler;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_pull_to_refresh_lv;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        ptrlList = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        listView = (PullableListView) findViewById(R.id.content_view);
    }

    @Override
    public void init() {
        initListView();
        listView.setHasMoreData(false);
        mHandler = new MHandler(TestActivity.this);
    }

    @Override
    public void setListener() {
        setOnRefreshListener();
        listView.setOnLoadListener(this);

    }

    public void setOnRefreshListener() {
        ptrlList.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                // 下拉刷新操作
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        if (adapter.getCount() > 30) {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                            listView.setHasMoreData(false);
                            return;
                        }
                        for (int i = 0; i < 4; i++) {
                            adapter.addItem("这里是item " + i);
                        }
                        adapter.notifyDataSetChanged();
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                        listView.setHasMoreData(true);
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }
        });
    }

    @Override
    public void onLoad(final PullableListView pullableListView) {
        mHandler.sendEmptyMessageDelayed(0, 2000);
//        new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                for (int i = 0; i < 8; i++) {
//                    adapter.addItem("这里是自动加载进来的item");
//                }
//                pullableListView.finishLoading();
//                adapter.notifyDataSetChanged();
//                if (adapter.getCount() > 30) {
//                    listView.setHasMoreData(false);
//                }
//            }
//        }.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * ListView初始化方法
     */
    private void initListView() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            items.add("按菜单键查看更多，点击查看Webview下拉刷新 " + i);
        }
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ptrlList.removeHandlerMessages();
        if(mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private class MHandler extends BaseHandler<TestActivity>{

        public MHandler(TestActivity reference) {
            super(reference);
        }

        @Override
        public void doHandleMessage(TestActivity reference, Message msg) {
            loadMoreData();
        }
    }

    /**
     * 加载更多数据
     */
    private void loadMoreData(){
        for (int i = 0; i < 8; i++) {
            adapter.addItem("这里是自动加载进来的item");
        }
        listView.finishLoading();
        adapter.notifyDataSetChanged();
        if (adapter.getCount() > 30) {
            listView.setHasMoreData(false);
        }
    }
}


