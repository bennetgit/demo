package spring.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Crypto {

    private final static SecureRandom random = new SecureRandom();

    private final static byte FORMAT_VERSION = 4;

    public static String newRandomId() {

        // 120 bits of randomness
        // a Type 4 UUID has 122
        // but multiples of 40 are nicer for BASE-32
        // and 160 bits seems overkill (and inconvenient to type)
        // this will make a 24 digit code

        byte[] randomBytes = new byte[15];
        random.nextBytes(randomBytes);
        return new Base32().encodeAsString(randomBytes);

    }

    public static String new64RandomId() {

        byte[] randomBytes = new byte[40];
        random.nextBytes(randomBytes);
        return new Base32().encodeAsString(randomBytes);

    }

    public static String newHashId(int numberOfRandomBytes, byte[]... parts) {
        byte[] bytes = new byte[numberOfRandomBytes];
        if (numberOfRandomBytes > 0) {
            random.nextBytes(bytes);
        }
        for (byte[] part : parts) {
            bytes = ArrayUtils.addAll(bytes, part);
        }
        byte[] hashedBytes = ArrayUtils.subarray(DigestUtils.md5(bytes), 0, 15);
        return new Base32().encodeAsString(hashedBytes);
    }

    public static String newEncryptionKey() {
        try {
            KeyGenerator key = KeyGenerator.getInstance("AES");
            key.init(128);

            SecretKey s = key.generateKey();
            byte[] raw = s.getEncoded();
            return new Base32().encodeAsString(raw);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static OutputStream encryptStream(OutputStream out, String key) throws IOException {
        try {
            byte[] randomBytes = new byte[16];
            random.nextBytes(randomBytes);
            IvParameterSpec iv = new IvParameterSpec(randomBytes);
            SecretKey secret = new SecretKeySpec(new Base32().decode(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret, iv);
            out.write(randomBytes);
            return new CipherOutputStream(out, cipher);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    static InputStream decryptStream(InputStream in, String key) throws IOException, InvalidKeyException {
        if (StringUtils.isBlank(key))
            return in;
        try {
            byte[] ivBytes = new byte[16];
            in.read(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            SecretKey secret = new SecretKeySpec(new Base32().decode(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, iv);
            return new CipherInputStream(in, cipher);
        } catch (InvalidKeyException e) {
            throw e;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static String new16RandomId() {
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        return new Base32().encodeAsString(randomBytes);
    }
}
