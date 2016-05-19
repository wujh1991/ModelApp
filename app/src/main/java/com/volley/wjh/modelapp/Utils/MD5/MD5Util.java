package com.volley.wjh.modelapp.Utils.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wujh on 2016/5/19.
 * Email:1049334820@qq.com
 * MD5加密
 */
public class MD5Util {

    public static String md5(String source) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(source.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    sbf.append("0");
                    sbf.append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    sbf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return sbf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
