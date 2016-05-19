package com.volley.wjh.modelapp.Utils.SdCard;

import android.os.Environment;

/**
 * Created by wujh on 2016/5/9.
 * Email:1049334820@qq.com
 */
public class SdCardUtil {

    /**
     * 判断sdcard是否存在
     * @return
     */
    public static boolean isExistSdCard(){
        boolean exist = false;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            exist = true;
        }

        return exist;
    }

    /**
     * 获取sdcard路径
     * @return
     */
    public static String getSdCardPath(){
        String path = "";
        if(isExistSdCard()){
            path = Environment.getExternalStorageDirectory().getPath();
        }

        return  path;
    }
}
