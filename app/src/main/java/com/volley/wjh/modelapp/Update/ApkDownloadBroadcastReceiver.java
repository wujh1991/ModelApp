package com.volley.wjh.modelapp.Update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

/**
 * Created by wujh on 2016/3/3.
 * Email:1049334820@qq.com
 */
public class ApkDownloadBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("downLoadApk",0);
        long referenceID = sharedPreferences.getLong("apkID", 0);

        long downLoadApkID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);

        if(referenceID == downLoadApkID){
            DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = downloadManager.getUriForDownloadedFile(downLoadApkID);
            Intent mIntent = new Intent(Intent.ACTION_VIEW);
            mIntent.setDataAndType(uri,"application/vnd.android.package-archive");
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }
    }
}
