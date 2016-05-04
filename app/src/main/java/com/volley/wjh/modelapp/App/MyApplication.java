package com.volley.wjh.modelapp.App;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.openim.OpenIMAgent;
import com.volley.wjh.dao.DaoMaster;
import com.volley.wjh.dao.DaoSession;

/**
 * Created by wujh on 2016/2/20.
 * Email:1049334820@qq.com
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static Context mContext;
    //volley请求队列
    public static RequestQueue mRequestQueue;
    //微信api
    public static IWXAPI mWxapi;

    //GreenDao框架对象
    public static DaoMaster mDaoMaster;
    public static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        registerWX();

        //友盟IM
        OpenIMAgent im = OpenIMAgent.getInstance(this);
        im.init();

        //GreenDao
        initDao();
    }

    public static RequestQueue getHttpQueue(){
        return mRequestQueue;
    }

    /**
     * 注册微信
     */
    public void registerWX(){
        mWxapi = WXAPIFactory.createWXAPI(getApplicationContext(),MyConstant.APP_ID);
        mWxapi.registerApp(MyConstant.APP_ID);
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }

    public void initDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(),MyConstant.DB_NAME,null);
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }



//    private MyApplication(Context context){
//        mContext = context;
//    }
//
//    public static synchronized MyApplication getInstance(Context context){
//        if(mInstance == null){
//            mInstance = new MyApplication(context);
//        }
//
//        return mInstance;
//    }
}
