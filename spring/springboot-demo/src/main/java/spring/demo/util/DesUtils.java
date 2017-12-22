package spring.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.demo.util.helper.EncryptHelper;

public final class DesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesUtils.class);

    private DesUtils() {
    }

    private static final String strKey = "hys%iekvz*jdam&^gnx<c$qf@b~u#twr";
    private static final String ENCODING = "UTF-8";
    private static final String ALGORITHM = "DES";

    private static Key getKey() {
        byte[] keyBtye = strKey.getBytes();

        byte[] _keyByte = new byte[8];

        for (int i = 0; (i < keyBtye.length) && (i < _keyByte.length); i++) {
            _keyByte[i] = keyBtye[i];
        }

        return new SecretKeySpec(_keyByte, ALGORITHM);
    }

    private static String encrypt(byte[] data, String algorithm) {
        try {
            Key key = getKey();
            Cipher c1 = Cipher.getInstance(algorithm);
            c1.init(1, key);
            byte[] cipherByte = c1.doFinal(data);
            return EncryptHelper.byte2hex(cipherByte);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("The encrypt Algorithm has no api support.");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("The encrypt has no Padding support.");
        } catch (InvalidKeyException e) {
            LOGGER.error("The encrypt key is invalid.");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("The encrypt BlockSize is invalid.");
        } catch (BadPaddingException e) {
            LOGGER.error("The encrypt Padding is bad.");
        }
        return null;
    }

    private static byte[] decrypt(byte[] data, String algorithm) {
        try {
            Key key = getKey();
            Cipher c1 = Cipher.getInstance(algorithm);
            c1.init(2, key);
            return c1.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("The encrypt Algorithm has no api support.");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("The encrypt has no Padding support.");
        } catch (InvalidKeyException e) {
            LOGGER.error("The encrypt key is invalid.");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("The encrypt BlockSize is invalid.");
        } catch (BadPaddingException e) {
            LOGGER.error("The encrypt Padding is bad.");
        }
        return null;
    }

    public static String desDecrypt(String ciperData) {
        return desDecrypt(ciperData, "UTF-8");
    }

    public static String desDecrypt(String ciperData, String encoding) {
        if (StringUtils.isBlank(ciperData)) {
            return null;
        }
        try {
            byte[] b = decrypt(EncryptHelper.hex2byte(ciperData), ALGORITHM);
            return new String(b, ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("The encoding {} is not supported.", ENCODING, e);
        } catch (Exception e) {
            LOGGER.error("decrypt {} exception", ciperData, e);
        }
        return null;
    }

    public static Long desLongDecrypt(String ciperData) {
        if (StringUtils.isBlank(ciperData)) {
            return null;
        }
        try {
            byte[] b = decrypt(EncryptHelper.hex2byte(ciperData), ALGORITHM);
            if (b == null) {
                LOGGER.info("decrypt {} failure", ciperData);
                return null;
            }
            return Long.parseLong(new String(b, ENCODING));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("The encoding {} is not supported.", ENCODING, e);
        } catch (NumberFormatException e) {
            LOGGER.error("decrypt {}, and can't parse to long", ciperData, e);
        } catch (Exception e) {
            LOGGER.error("The ciperData {} is incorrect.", ciperData, e);
        }
        return null;
    }

    public static Long desLongDecrypt(String ciperData, String encoding) {
        if (StringUtils.isBlank(ciperData)) {
            return null;
        }
        try {
            byte[] b = decrypt(EncryptHelper.hex2byte(ciperData), ALGORITHM);
            if (b == null) {
                LOGGER.info("decrypt {} failure", ciperData);
                return null;
            }
            return Long.parseLong(new String(b, ENCODING));
        } catch (NumberFormatException e) {
            LOGGER.error("decrypt {}, and can't parse to long", ciperData, e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("The encoding {} is not supported.", ENCODING, e);
        } catch (Exception e) {
            LOGGER.error("The ciperData {} is incorrect.", ciperData, e);
        }
        return null;
    }

    public static String desEncrypt(String origin) {
        return desEncrypt(origin, "UTF-8");
    }

    public static String desEncrypt(String origin, String encoding) {
        if (StringUtils.isBlank(origin)) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase("null", origin)) {
            return null;
        }

        try {
            return encrypt(origin.getBytes(ENCODING), ALGORITHM);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("The encoding {} is not supported.", ENCODING, e);
        } catch (Exception e) {
            LOGGER.error("encrypt {} exception", origin, e);
        }

        return null;
    }
}
