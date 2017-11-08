package spring.demo.util.helper;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spring.demo.util.HexStringUtils;

/**
 * Created by wangfacheng on 2017-11-08.
 */
public final class PasswordHelper {

    static Logger log = LoggerFactory.getLogger(PasswordHelper.class);

    /**
     * 密码加密
     * 
     * @param password
     *            原始密码
     * @return 加密后的密码
     */
    public static String password(String password) {
        try {
            return HexStringUtils.toHexString(EncryptHelper.eccrypt(password));
        } catch (NoSuchAlgorithmException e) {
            log.error("密码加密失败：" + e.toString());
            e.printStackTrace();
        }
        return password;
    }

    public static void main(String[] args) {
        System.out.println("xxxxxxx " + password("a"));
    }
}
