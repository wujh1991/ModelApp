//package com.volley.wjh.modelapp.HttpRequest.Retrofit;
//
//import android.widget.TextView;
//
//import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
//import com.volley.wjh.modelapp.R;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
///**
// * Created by wujh on 2016/5/17.
// * Email:1049334820@qq.com
// */
//public class RetrofitActivity extends BaseActivity {
//    private static final String BASE_URL = "http://v.juhe.cn/";
//    private TextView tvRetorfit;
//
//    @Override
//    public int getResourceLayout() {
//        return R.layout.activity_retorfit;
//    }
//
//    @Override
//    public void getExtra() {
//
//    }
//
//    @Override
//    public void findViews() {
//        tvRetorfit = (TextView) findViewById(R.id.tvRetorfit);
//    }
//
//    @Override
//    public void init() {
//        request();
//    }
//
//    @Override
//    public void setListener() {
//
//    }
//
//    public void request() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
//        WeatherInfoService weatherInfoService = retrofit.create(WeatherInfoService.class);
//        Call<String> call = weatherInfoService.getString();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                tvRetorfit.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable throwable) {
//                tvRetorfit.setText("failure");
//            }
//        });
//    }
//}
