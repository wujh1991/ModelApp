package com.volley.wjh.modelapp.UI;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/5/7.
 * Email:1049334820@qq.com
 * 百度地图定位
 */
public class BDMapActivity extends BaseActivity{
    private MapView bmapView;
    private BaiduMap bdMap;
    private LocationClient mLocClient;
    private LocationClientOption option ;
    private MyLocationListener myLocationListener;
    @Override
    public int getResourceLayout() {
        return R.layout.activity_bd_map;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        bmapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    public void init() {
        //初始化百度地图
        bdMap = bmapView.getMap();
        //显示当前位置
        bdMap.setMyLocationEnabled(true);
        //实例化地图核心类
        mLocClient = ((MyApplication)getApplication()).mLocClient;
        myLocationListener = new MyLocationListener();
        mLocClient.registerLocationListener(myLocationListener);
        //设置地图参数
        option = new LocationClientOption();
        //设置坐标系
        option.setCoorType("bd09ll");
        //启用高精度定位
        option.setOpenGps(true);
        //设置间隔5s定位
        option.setScanSpan(5000);
        mLocClient.setLocOption(option);
        //启动地图定位
        if(mLocClient.isStarted()){
            mLocClient.stop();
        }
        mLocClient.start();

    }

    @Override
    public void setListener() {

    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation == null || bmapView == null){
                return;
            }
            //封装数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            //绑定地图数据
            bdMap.setMyLocationData(locData);
            //获取坐标点
            LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            //更新地图坐标位置
            bdMap.animateMapStatus(update);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocClient.isStarted()){
            mLocClient.stop();
            mLocClient.unRegisterLocationListener(myLocationListener);
        }
        bmapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }
}
