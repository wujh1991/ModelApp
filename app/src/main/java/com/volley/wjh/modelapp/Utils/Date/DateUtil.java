package com.volley.wjh.modelapp.Utils.Date;

import android.text.format.DateFormat;

import com.google.gson.internal.bind.DateTypeAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wujh on 2016/3/29.
 * Email:1049334820@qq.com
 */
public class DateUtil {

    public static String DateFormat(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy", Locale.US);
        Date rdate = null;
        try {
             rdate =sdf.parse(date.toGMTString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(rdate);
    }
}
