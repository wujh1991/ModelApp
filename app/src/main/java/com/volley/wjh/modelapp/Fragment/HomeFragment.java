package com.volley.wjh.modelapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.Base.Fragment.BaseFragment;
import com.volley.wjh.modelapp.CustomWidget.CountTimeButton;
import com.volley.wjh.modelapp.DataModel.Weather;
import com.volley.wjh.modelapp.HttpRequest.GetVolleyHttpUrl;
import com.volley.wjh.modelapp.HttpRequest.VolleyHttpRequest;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.AddressSelectActivity;
import com.volley.wjh.modelapp.UI.GridViewWithRecyclerViewActivity;
import com.volley.wjh.modelapp.UI.RecyclerViewActivity;
import com.volley.wjh.modelapp.UI.SwpeRefreshLayoutActivity;
import com.volley.wjh.modelapp.UI.WaterFallViewWithRecyclerViewActivity;

/**
 * Created by wujh on 2016/3/10.
 * Email:1049334820@qq.com
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private Button btnVolley;
    private Button btnSwipeRefreshLayout;
    private Button btnRecyclerView;
    private Button btnGridView;
    private Button btnWaterFallView;
    private CountTimeButton cbtnTime;
    private Button btnWxLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void findViews(View rootView) {
        btnVolley = (Button)rootView.findViewById(R.id.btnVolley);
        btnSwipeRefreshLayout = (Button)rootView.findViewById(R.id.btnSwipeRefreshLayout);
        btnRecyclerView = (Button)rootView.findViewById(R.id.btnRecyclerView);
        btnGridView = (Button)rootView.findViewById(R.id.btnGridView);
        btnWaterFallView = (Button)rootView.findViewById(R.id.btnWaterFallView);
        cbtnTime = (CountTimeButton)rootView.findViewById(R.id.cbtnTime);
        btnWxLogin = (Button)rootView.findViewById(R.id.btnWxLogin);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnVolley.setOnClickListener(this);
        btnSwipeRefreshLayout.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnGridView.setOnClickListener(this);
        btnWaterFallView.setOnClickListener(this);
        btnWxLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolley:
                Toast.makeText(getActivity(), "已点击", Toast.LENGTH_LONG).show();
                getRequestTest();
                break;
            case R.id.btnSwipeRefreshLayout:
                startActivity(new Intent(getActivity(), SwpeRefreshLayoutActivity.class));
                break;
            case R.id.btnRecyclerView:
                startActivity(new Intent(getActivity(), RecyclerViewActivity.class));
                break;
            case R.id.btnGridView:
                startActivity(new Intent(getActivity(),GridViewWithRecyclerViewActivity.class));
                break;
            case R.id.btnWaterFallView:
                startActivity(new Intent(getActivity(),WaterFallViewWithRecyclerViewActivity.class));
                //app下载
//                ApkDownloadManager apkDownloadManager = new ApkDownloadManager(this,"http://file.liqucn.com/upload/2013/tongxun/net.qihoo.launcher.app.whitespot_1.3_liqucn.com.apk");
//                apkDownloadManager.download();
                break;
            case R.id.btnWxLogin:
//                startActivity(new Intent(this, WXEntryActivity.class));
                startActivity(new Intent(getActivity(),AddressSelectActivity.class));
                break;
        }
    }

    public  void getRequestTest(){
        VolleyHttpRequest<Weather> request = new VolleyHttpRequest<Weather>(GetVolleyHttpUrl.getWeatherIngo(), Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
                Toast.makeText(getActivity(),"success"  + "\n"
                        + "status" + weather.status + "\n"
                        + "城市：" + weather.weatherinfo.city + "\n"
                        +  "温度：" + weather.weatherinfo.wendu + "\n"
                        + "aqi:" +  weather.weatherinfo.aqi + "\n"
                        + "感冒:" + "\n"
                        + weather.weatherinfo.ganmao
                        ,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
            }
        });
        MyApplication.getHttpQueue().add(request);
    }
}
