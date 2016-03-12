package com.volley.wjh.modelapp.HttpRequest;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujh on 2016/2/21.
 * Email:1049334820@qq.com
 */
public class VolleyHttpUrl {
    public static final String hostUrl = "http://wthrcdn.etouch.cn/";

    public static String urlBuilder(int requestMethod,String ohostUrl,String methodName,HashMap<String,String> params){
        StringBuffer urlSb = new StringBuffer();
        if( ! "".equals(ohostUrl) && ohostUrl != null ){
            urlSb.append(ohostUrl);
        }else{
            urlSb.append(hostUrl);
        }
        urlSb.append(methodName);
        if(requestMethod == 0 && params != null){
            urlSb.append("?");
            urlSb.append(getParamsString(params));
        }

        return urlSb.toString();
    }

    public static String getParamsString(HashMap<String,String> map){
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
}
