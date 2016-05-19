package com.volley.wjh.modelapp.UI;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.DataModel.ImageSize;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Image.ImageUtil;
import com.volley.wjh.modelapp.Utils.SdCard.SdCardUtil;

import java.io.File;

/**
 * Created by wujh on 2016/5/9.
 * Email:1049334820@qq.com
 */
public class BitMapSampleSizeActivity extends BaseActivity{
    private static final String TAG = "BitMapSampleSizeActivity";
    private ImageView ivSmaple;
    @Override
    public int getResourceLayout() {
        return R.layout.activity_bitmap_sample;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        ivSmaple = (ImageView) findViewById(R.id.ivSmaple);
    }

    @Override
    public void init() {
        displayBitMap();
    }

    @Override
    public void setListener() {

    }

    public void displayBitMap(){
        String filePath = SdCardUtil.getSdCardPath() + File.separator + "testBitMap.jpg";
        Log.e(TAG,"path:" + SdCardUtil.getSdCardPath());
        ImageSize imageSize = getImageSize(ivSmaple);
        Log.e(TAG,"width--" + imageSize.getWidth() + "height--" + imageSize.getHeight());
        ivSmaple.setImageBitmap(ImageUtil.getSmallBitMap(filePath, imageSize.getWidth(), imageSize.getHeight()));
    }

    public ImageSize getImageSize(ImageView imageView){
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        Log.e(TAG,"dwidth--" + displayMetrics.widthPixels + "dheight--" + displayMetrics.heightPixels);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int width = imageView.getWidth();
        if(width <= 0){
            width = lp.width;
        }
        if(width <= 0){
            width = imageView.getMaxWidth();
        }
        if(width <= 0){
            width = displayMetrics.widthPixels;
        }

        int height = imageView.getHeight();
        if(height <= 0){
            height = lp.height;
        }
        if(height <= 0){
            height = imageView.getMaxHeight();
        }
        if(height <= 0){
            height = displayMetrics.heightPixels;
        }

        imageSize.setWidth(width);
        imageSize.setHeight(height);

        return imageSize;
    }
}
