package com.volley.wjh.modelapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.CustomWidget.CountTimeButton;
import com.volley.wjh.modelapp.DataModel.Weather;
import com.volley.wjh.modelapp.HttpRequest.GetVolleyHttpUrl;
import com.volley.wjh.modelapp.HttpRequest.VolleyHttpRequest;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Image.ImageUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnVolley;
    private Button btnSwipeRefreshLayout;
    private Button btnRecyclerView;
    private Button btnGridView;
    private Button btnWaterFallView;
    private CountTimeButton cbtnTime;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        showImage();
    }

    public  void getRequestTest(){
        VolleyHttpRequest<Weather> request = new VolleyHttpRequest<Weather>(GetVolleyHttpUrl.getWeatherIngo(), Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
                Toast.makeText(MainActivity.this,"success"  + "\n"
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
                Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_LONG).show();
            }
        });
        MyApplication.getHttpQueue().add(request);
    }

    public void findViews(){
        btnVolley = (Button)findViewById(R.id.btnVolley);
        btnSwipeRefreshLayout = (Button)findViewById(R.id.btnSwipeRefreshLayout);
        btnRecyclerView = (Button)findViewById(R.id.btnRecyclerView);
        btnGridView = (Button)findViewById(R.id.btnGridView);
        btnWaterFallView = (Button)findViewById(R.id.btnWaterFallView);
        cbtnTime = (CountTimeButton)findViewById(R.id.cbtnTime);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setListener(){
        btnVolley.setOnClickListener(this);
        btnSwipeRefreshLayout.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnGridView.setOnClickListener(this);
        btnWaterFallView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolley:
                Toast.makeText(MainActivity.this, "已点击", Toast.LENGTH_LONG ).show();
                getRequestTest();
                break;
            case R.id.btnSwipeRefreshLayout:
                startActivity(new Intent(this, SwpeRefreshLayoutActivity.class));
                break;
            case R.id.btnRecyclerView:
               startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btnGridView:
                startActivity(new Intent(this,GridViewWithRecyclerViewActivity.class));
                break;
            case R.id.btnWaterFallView:
                startActivity(new Intent(this,WaterFallViewWithRecyclerViewActivity.class));
                break;

        }
    }

    public void showImage(){
        ImageUtil.displayImage(imageView,"http://avatar.csdn.net/6/6/D/1_lfdfhl.jpg");
    }
}
