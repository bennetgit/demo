package com.hong610.common.encryp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数据加密算法
 * Created by Hong on 2016/12/12.
 */
public class EncrypSHA {

    public byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要  
        md5.update(srcBytes);
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String msg = "Hong";
        EncrypSHA sha = new EncrypSHA();
        byte[] resultBytes = sha.eccrypt(msg);
        System.out.println("明文是：" + msg);
        System.out.println("密文是：" + new String(resultBytes));

    }
}
