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
import com.volley.wjh.modelapp.CustomWidget.LoadingDialog.WLoadingDialog;
import com.volley.wjh.modelapp.CustomWidget.LoadingDialog.ZProgressHUD;
import com.volley.wjh.modelapp.DataModel.Weather;
import com.volley.wjh.modelapp.HttpRequest.GetVolleyHttpUrl;
import com.volley.wjh.modelapp.HttpRequest.VolleyHttpRequest;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.UI.AsyncHttpActivity;

/**
 * Created by wujh on 2016/3/12.
 * Email:1049334820@qq.com
 */
public class FirstFragment extends BaseFragment implements View.OnClickListener {
    private Button btnVolley;
    private Button btnAsyncHttp;

    private WLoadingDialog wLoadingDialog;

    private ZProgressHUD progressHUD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_first;
    }

    @Override
    public void findViews(View rootView) {
        btnVolley = (Button) rootView.findViewById(R.id.btnVolley);
        btnAsyncHttp = (Button) rootView.findViewById(R.id.btnAsyncHttp);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnVolley.setOnClickListener(this);
        btnAsyncHttp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVolley:
                getRequestTest();
                break;
            case R.id.btnAsyncHttp:
                startActivity(new Intent(getActivity(), AsyncHttpActivity.class));
                break;
        }
    }

    public void getRequestTest() {
//        openLoading();
        showLoadingDialog();
        VolleyHttpRequest<Weather> request = new VolleyHttpRequest<Weather>(GetVolleyHttpUrl.getWeatherIngo(), Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
//                progressHUD.dismiss();//关闭loading
                wLoadingDialog.dismiss();
                Toast.makeText(getActivity(), "success" + "\n"
                        + "status" + weather.status + "\n"
                        + "城市：" + weather.weatherinfo.city + "\n"
                        + "温度：" + weather.weatherinfo.wendu + "\n"
                        + "aqi:" + weather.weatherinfo.aqi + "\n"
                        + "感冒:" + "\n"
                        + weather.weatherinfo.ganmao
                        , Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                progressHUD.dismiss();//关闭loading
                wLoadingDialog.dismiss();
                Toast.makeText(getActivity(), "fail", Toast.LENGTH_LONG).show();
            }
        });
        MyApplication.getHttpQueue().add(request);
    }

    public void openLoading() {
        progressHUD = ZProgressHUD.getInstance(getActivity());
        progressHUD.setMessage("加载中...");
        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
        progressHUD.show();
    }

    public void showLoadingDialog() {
        wLoadingDialog = new WLoadingDialog(getActivity());
        wLoadingDialog.show();
    }

}
