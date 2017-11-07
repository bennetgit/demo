package com.hong610.common.encryp;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Base64
 * Created by Hong on 2016/12/12.
 */
public class EncrypBase64 {
    // 加密  
    public String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        EncrypBase64 de1 = new EncrypBase64();
        String msg = "Hong";
        String encontent = de1.getBase64(msg);
        String decontent = de1.getFromBase64(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + encontent);
        System.out.println("解密后:" + decontent);
    }
}
