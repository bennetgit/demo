package com.hong610.common.helper;

import com.hong610.common.encryp.EncrypMD5;
import com.hong610.common.encryp.HexStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

/**
 * 密码帮助类
 * Created by Hong on 2016/12/13.
 */
public class PasswordHelper {

    static Logger log = LoggerFactory.getLogger(PasswordHelper.class);

    /**
     * 密码加密
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String password(String password) {
        try {
            return HexStringUtils.toHexString(EncrypMD5.eccrypt(password));
        } catch (NoSuchAlgorithmException e) {
            log.error("密码加密失败：" + e.toString());
            e.printStackTrace();
        }
        return password;
    }

}
