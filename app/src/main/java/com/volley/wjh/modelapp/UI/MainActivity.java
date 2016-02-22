package com.volley.wjh.modelapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.DataModel.Weather;
import com.volley.wjh.modelapp.HttpRequest.GetVolleyHttpUrl;
import com.volley.wjh.modelapp.HttpRequest.VolleyHttpRequest;
import com.volley.wjh.modelapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
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
        MyApplication.getHttpQueue().start();
    }

    public void findViews(){
        button = (Button)findViewById(R.id.button);
    }

    public void setListener(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(MainActivity.this, "已点击", Toast.LENGTH_LONG ).show();
                getRequestTest();
                break;
        }
    }
}
