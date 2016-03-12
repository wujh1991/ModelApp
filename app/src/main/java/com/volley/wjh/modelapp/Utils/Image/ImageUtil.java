package com.volley.wjh.modelapp.Utils.Image;

import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.Cache.Image.BitmapCache;
import com.volley.wjh.modelapp.R;

/**
 * Created by wujh on 2016/2/23.
 * Email:1049334820@qq.com
 */
public class ImageUtil {

    public static void displayImage(ImageView imageView,String url){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueue(),new BitmapCache());
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(imageView, R.mipmap.test,R.mipmap.test);
        imageLoader.get(url,imageListener,100,100);
    }
}
