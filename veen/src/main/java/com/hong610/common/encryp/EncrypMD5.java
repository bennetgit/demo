package com.hong610.common.encryp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单向加密
 * Created by Hong on 2016/12/12.
 */
public class EncrypMD5 {

    public static byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象  
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要  
        md5.update(srcBytes);
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }


    public static void main(String args[]) throws NoSuchAlgorithmException {
        String msg = "Hong";
        byte[] resultBytes = EncrypMD5.eccrypt(msg);
        System.out.println("密文是：" + new String(resultBytes));
        System.out.println("明文是：" + msg);
    }
}
