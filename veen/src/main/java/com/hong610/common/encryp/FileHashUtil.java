package com.hong610.common.encryp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;

/**
 * 文件Hash值算法
 * Created by Hong on 2016/12/12.
 */
public class FileHashUtil {
    public static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String[] hashTypes = new String[]{"MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512"};

    public Map<String, String> MD5File(String fileName) throws Exception {
        List<MessageDigest> mds = new ArrayList<MessageDigest>();
        for (String hashType : hashTypes) {
            MessageDigest md = MessageDigest.getInstance(hashType);
            mds.add(md);
        }
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                for (MessageDigest md : mds) {
                    md.update(buffer, 0, numRead);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        for (MessageDigest md : mds) {
            map.put(md.getAlgorithm(), toHexString(md.digest()));
        }
        return map;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        String[] fileName = new String[]{"E:/1.jpg","E:/1 - 副本.jpg"};
        FileHashUtil files = new FileHashUtil();
        for (int i = 0; i < fileName.length; i++) {
            Map map = files.MD5File(fileName[i]);
            System.err.println(map.toString());
        }
    }
}
