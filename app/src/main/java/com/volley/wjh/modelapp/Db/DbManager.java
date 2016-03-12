package com.volley.wjh.modelapp.Db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wujh on 2016/3/9.
 * Email:1049334820@qq.com
 */
public class DbManager {
    
    private static DbManager dbManager;

    public static DbManager getInstance(){
        if(dbManager == null){
            dbManager = new DbManager();
        }

        return dbManager;
    }

    public void copyDataBase(Context context,String dbPath,String dbName){
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
              inputStream = assetManager.open(dbName);
              outputStream = new FileOutputStream(dbPath + dbName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer))>0){
                    outputStream.write(buffer, 0, length);
                }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDataBase(Context context,String dbPath,String dbName){
        File dir = new File(dbPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File dbf = new File(dbPath + dbName);
        if(dbf.exists()){
            dbf.delete();
        }
        SQLiteDatabase.openOrCreateDatabase(dbf, null);
        // 复制asseets中的数据库文件到DB_PATH下
        copyDataBase(context,dbPath,dbName);
    }
}
