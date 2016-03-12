package com.volley.wjh.modelapp.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.volley.wjh.modelapp.DataModel.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by wujh on 2016/3/7.
 * Email:1049334820@qq.com
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String PROVINCE_CITY_AREA_DB_PATH  = "/data/data/com.volley.wjh.modelapp/databases/";
    private static final String PROVINCE_CITY_AREA_DB_NAME = "city.db";

    private static DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Address> addressesList;
    private Context mContext;

    public static DbHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    public DbHelper(Context context){
        this(context, PROVINCE_CITY_AREA_DB_NAME, null, 1);
        this.mContext = context;
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Address> queryAddress(String tableName,String selection,String[] selectionArgs,boolean isProvince){
        DbManager.getInstance().createDataBase(mContext,PROVINCE_CITY_AREA_DB_PATH,PROVINCE_CITY_AREA_DB_NAME);
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(PROVINCE_CITY_AREA_DB_PATH + PROVINCE_CITY_AREA_DB_NAME,null);
        addressesList = new ArrayList<Address>();
        Cursor cursor = sqLiteDatabase.query(tableName,null,selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()) {
            Address address = new Address();
            address.setId(cursor.getString(cursor.getColumnIndex("id")));
            if(!isProvince){
                address.setPid(cursor.getString(cursor.getColumnIndex("pid")));
            }
            address.setName(cursor.getString(cursor.getColumnIndex("name")));
            addressesList.add(address);
        }
        cursor.close();
        sqLiteDatabase.close();

        return addressesList;
    }
}
