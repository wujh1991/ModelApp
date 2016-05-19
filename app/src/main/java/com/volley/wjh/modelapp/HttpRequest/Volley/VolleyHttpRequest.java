package com.volley.wjh.modelapp.HttpRequest.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.volley.wjh.modelapp.App.MyApplication;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by wujh on 2016/2/21.
 * Email:1049334820@qq.com
 */
public class VolleyHttpRequest<T> extends  Request<T> {
    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClazz;

    public VolleyHttpRequest(int method, String url,Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        this.mClazz = clazz;
        this.mListener = listener;
    }

    public VolleyHttpRequest(String url,Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Request.Method.GET, url, clazz, listener, errorListener);
    }

    @Override
    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
//            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            parsed = new String(response.data, "UTF-8");
            return Response.success(mGson.fromJson(parsed, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var4) {
            return Response.error(new ParseError(var4));
        }
    }

    public static void doStringGetRequest(String url){
        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueue().add(stringRequest);
    }

    public static void doStringPostRequest(String url,final Map<String,String> params){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        MyApplication.getHttpQueue().add(stringRequest);
    }

    public static void doJsonObjectGetRequest(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueue().add(jsonObjectRequest);
    }

    public static void doJsonObjectPostRequest(String url,final Map<String,String> params){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        MyApplication.getHttpQueue().add(jsonObjectRequest);
    }
}
