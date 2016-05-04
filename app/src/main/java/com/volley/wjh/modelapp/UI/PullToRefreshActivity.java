package com.volley.wjh.modelapp.UI;

import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.CustomWidget.PullToRefresh.PullToRefreshBase;
import com.volley.wjh.modelapp.CustomWidget.PullToRefresh.PullToRefreshListView;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.other.ItemDataUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wujh on 2016/4/16.
 * Email:1049334820@qq.com
 */
public class PullToRefreshActivity extends BaseActivity{
    private PullToRefreshListView ptrLv;
    private ArrayAdapter<String> adater;
    private ArrayList<String> list;
    private boolean isPullDownRefresh;
    @Override
    public int getResourceLayout() {
        return R.layout.activity_pull_to_refresh;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        ptrLv = (PullToRefreshListView) findViewById(R.id.ptr_list);
    }

    @Override
    public void init() {
        ptrLv.setMode(PullToRefreshBase.Mode.BOTH);

        list = new ArrayList<String>();
        list.addAll(Arrays.asList(ItemDataUtil.getPullToRefreshListData()));
        adater = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        ptrLv.setAdapter(adater);
    }

    @Override
    public void setListener() {
        ptrLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//
//                // Update the LastUpdatedLabel
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                isPullDownRefresh = true;
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                isPullDownRefresh = false;
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });
    }

    private class GetDataTask extends AsyncTask<Void,Void,String[]>{

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(isPullDownRefresh){
                return  ItemDataUtil.getPullToRefreshListData();
            }else{
                return  new String[]{"加载更多数据"};
            }
        }

        @Override
        protected void onPostExecute(String[] strings) {
            if(isPullDownRefresh){
                list.clear();
            }
            list.addAll(Arrays.asList(strings));
            adater.notifyDataSetChanged();
            ptrLv.onRefreshComplete(false,true);
        }
    }
}
