package com.volley.wjh.modelapp.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.HttpRequest.AsyncHttpCallBack;
import com.volley.wjh.modelapp.HttpRequest.AsyncHttpRequest;
import com.volley.wjh.modelapp.R;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wujh on 2016/4/7.
 * Email:1049334820@qq.com
 */
public class AsyncHttpActivity extends BaseActivity implements View.OnClickListener {
    private Button btnDownLoadImg;
    private Button btnUpLoadfile;

    private ImageView ivShow;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_async_http;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        btnDownLoadImg = (Button) findViewById(R.id.btnDownLoadImg);
        btnUpLoadfile = (Button) findViewById(R.id.btnUpLoadfile);

        ivShow = (ImageView) findViewById(R.id.ivShow);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnDownLoadImg.setOnClickListener(this);
        btnUpLoadfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownLoadImg:
                getImg();
                break;
            case R.id.btnUpLoadfile:
                getText();
//                postText();
                break;
        }
    }

    public void getImg(){
        AsyncHttpRequest.doGetRequest("http://avatar.csdn.net/6/6/D/1_lfdfhl.jpg", new AsyncHttpCallBack() {
            @Override
            public void onRequestSuccess(int statuCode, Header[] headers, byte[] bytes) {
                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ivShow.setImageBitmap(b);
            }

            @Override
            public void onRequestFailure(int statuCode, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void getText(){
        AsyncHttpRequest.doGetRequest("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100", new AsyncHttpCallBack() {
            @Override
            public void onRequestSuccess(int statuCode, Header[] headers, byte[] bytes) {
                String text =  new String(bytes);
                Toast.makeText(AsyncHttpActivity.this,text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestFailure(int statuCode, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void postText(){
        RequestParams params = new RequestParams();
        params.put("citykey","101010100");
        AsyncHttpRequest.doPostRequest("http://wthrcdn.etouch.cn/weather_mini?", params,new AsyncHttpCallBack() {
            @Override
            public void onRequestSuccess(int statuCode, Header[] headers, byte[] bytes) {
                String text = new String(bytes);
                Toast.makeText(AsyncHttpActivity.this, text, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestFailure(int statuCode, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
