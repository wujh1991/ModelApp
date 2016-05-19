package com.volley.wjh.modelapp.Utils.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    /**
     * 网络加载图片
     * @param imageView
     * @param url
     */
    public static void displayImage(ImageView imageView,String url){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueue(),new BitmapCache());
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(imageView, R.mipmap.test,R.mipmap.test);
        imageLoader.get(url,imageListener,100,100);
    }

    /**
     * 根据图片路劲获取压缩图片
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap getSmallBitMap(String filePath,int reqWidth,int reqHeight){
        BitmapFactory.Options options =  new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);

        options.inSampleSize = caculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath,options);
    }

    /**
     * 获取图片缩放比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize  = 1;

        int width = options.outWidth;
        int height = options.outHeight;

        if(width > reqWidth || height > reqHeight){
            int wRatio = Math.round(width * 1.0f / reqWidth);
            int hRatio = Math.round(height * 1.0f / reqHeight);

            inSampleSize = Math.max(wRatio, hRatio);
        }

        return inSampleSize;
    }
}
