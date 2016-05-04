package com.volley.wjh.modelapp.Base.LeakHandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by wujh on 2016/4/5.
 * Email:1049334820@qq.com
 */
public  abstract class BaseHandler<T> extends Handler {
    private WeakReference<T>  mReference = null;

    public BaseHandler(T reference){
        super();
        this.mReference = new WeakReference<T>(reference);
    }

    public BaseHandler(Callback callback, T reference) {
        super(callback);
        this.mReference = new WeakReference<T>(reference);
    }

    public BaseHandler(Looper looper, T reference) {
        super(looper);
        this.mReference = new WeakReference<T>(reference);
    }

    public BaseHandler(Looper looper, Callback callback, T reference) {
        super(looper, callback);
        this.mReference = new WeakReference<T>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        final T reference = mReference.get();
        if(reference != null){
            doHandleMessage(reference,msg);
        }
    }

    public abstract void doHandleMessage(T reference,Message msg);
}
