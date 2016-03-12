package com.volley.wjh.modelapp.Update;

import android.app.DownloadManager;
import  android.app.DownloadManager.Request;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

/**
 * Created by wujh on 2016/3/3.
 * Email:1049334820@qq.com
 */
public class ApkDownloadManager {
    private static final String APK_NAME = "wjh.apk";
    private Context mContext;
    private String mUrl;

    public ApkDownloadManager(Context mContext,String mUrl){
        this.mContext = mContext;
        this.mUrl = mUrl;
    }

    public void download(){
        DownloadManager downloadManager = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(mUrl);
        Request request = new Request(uri);
        request.setDestinationInExternalPublicDir("download",APK_NAME);
        request.setDescription("360白点新版本下载");
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");
        request.setVisibleInDownloadsUi(true);
        long referenceId = downloadManager.enqueue(request);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("downLoadApk",0);
        sharedPreferences.edit().putLong("apkID",referenceId).commit();
    }
}
