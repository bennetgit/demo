package spring.demo.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an example implementation of the OATH TOTP algorithm. Visit
 * www.openauthentication.org for more information.
 *
 * @author Johan Rydell, PortWise, Inc.
 */

public final class TOTP {

    private static final Logger LOGGER = LoggerFactory.getLogger(TOTP.class);

    public static final String HMAC_SHA512 = "HmacSHA512";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String RAW = "RAW";
    // 0 1 2 3 4 5 6 7 8
    private static final int[] DIGITS_POWER = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

    private TOTP() {
    }

    /**
     * This method uses the JCE to provide the crypto algorithm. HMAC computes a
     * Hashed Message Authentication Code with the crypto hash algorithm as a
     * parameter.
     *
     * @param crypto
     *            : the crypto algorithm (HmacSHA1, HmacSHA256, HmacSHA512)
     * @param keyBytes
     *            : the bytes to use for the HMAC key
     * @param text
     *            : the message or text to be authenticated
     */
    private static byte[] hmacSha(String crypto, byte[] keyBytes, byte[] text) {
        try {
            Mac hmac;
            hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, RAW);
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    /**
     * This method converts a HEX string to Byte[]
     *
     * @param hex
     *            : the HEX string
     *
     * @return: a byte array
     */

    private static byte[] hexStr2Bytes(String hex) {
        // Adding one byte to get the right conversion
        // Values starting with "0" can be converted
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

        // Copy all the REAL bytes, not the "first"
        byte[] ret = new byte[bArray.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = bArray[i + 1];
        return ret;
    }

    /**
     * This method generates a TOTP value for the given set of parameters.
     *
     * @param key
     *            : the shared secret, HEX encoded
     * @param time
     *            : a value that reflects a time
     * @param returnDigits
     *            : number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *          {@link truncationDigits} digits
     */

    public static String generateTOTP(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, HMAC_SHA1);
    }

    /**
     * This method generates a TOTP value for the given set of parameters.
     *
     * @param key
     *            : the shared secret, HEX encoded
     * @param time
     *            : a value that reflects a time
     * @param returnDigits
     *            : number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *          {@link truncationDigits} digits
     */

    public static String generateTOTP256(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, HMAC_SHA256);
    }

    /**
     * This method generates a TOTP value for the given set of parameters.
     *
     * @param key
     *            : the shared secret, HEX encoded
     * @param time
     *            : a value that reflects a time
     * @param returnDigits
     *            : number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *          {@link truncationDigits} digits
     */

    public static String generateTOTP512(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, HMAC_SHA512);
    }

    /**
     * This method generates a TOTP value for the given set of parameters.
     *
     * @param key
     *            : the shared secret, HEX encoded
     * @param time
     *            : a value that reflects a time
     * @param returnDigits
     *            : number of digits to return 返回长度 --6
     * @param crypto
     *            : the crypto function to use
     *
     * @return: a numeric String in base 10 that includes
     *          {@link truncationDigits} digits
     */

    public static String generateTOTP(String key, String time, String returnDigits, String crypto) {
        int codeDigits = Integer.decode(returnDigits).intValue();
        String result;
        String tmpTime = time;

        // Using the counter
        // First 8 bytes are for the movingFactor
        // Compliant with base RFC 4226 (HOTP)
        while (tmpTime.length() < 16)
            tmpTime = "0" + tmpTime;

        // Get the HEX in a Byte[]
        byte[] msg = hexStr2Bytes(tmpTime);
        byte[] k = hexStr2Bytes(key);
        byte[] hash = hmacSha(crypto, k, msg);

        // put selected bytes into result int
        int offset = hash[hash.length - 1] & 0xf;

        int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
                | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[codeDigits];

        result = Integer.toString(otp);
        while (result.length() < codeDigits) {
            result = "0" + result;
        }
        return result;
    }

    public static int getRandom(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static int getRandom(int max) {
        return new Random().nextInt(max);
    }

    public static String generateEncodedString(String userToken, String userPrivateKey, String truckId,
            int userIdMaxLen, int truckIdMaxLen, int totoCodeSize)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        LOGGER.info("userToken={}; truckId={}; userIdMaxLen={}; truckIdMaxLen={}; totoCodeSize={}", userToken, truckId,
                userIdMaxLen, truckIdMaxLen, totoCodeSize);
        // Random
        int random = getRandom(1, 9) * 10 + getRandom(0, 10);

        // Truck ID info
        String truckIdNew = "000000000000" + truckId;
        truckIdNew = truckIdNew.substring(truckIdNew.length() - truckIdMaxLen);

        // UTC TimeStamp
        long lnCurrentTime = System.currentTimeMillis();
        long timeStamp = lnCurrentTime / 1000 / 30;
        timeStamp = timeStamp * 100 + random;

        LOGGER.info("random={}; timeStamp={}; truckIdNew={}", random, timeStamp, truckIdNew);

        // Encode User Info
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String userInfo = new String(
                Hex.encodeHex(md.digest((userToken + truckId + userPrivateKey).getBytes("utf-8"))));
        LOGGER.info("userInfo=" + userInfo);

        // Time Step
        String timeStep = Long.toHexString(timeStamp).toUpperCase();

        // TOTP Code
        String totpCode = generateTOTP(userInfo, timeStep, String.valueOf(totoCodeSize), HMAC_SHA256);
        String totpCodeNew = (new StringBuilder(totpCode)).reverse().toString();
        LOGGER.info("timeStep={}; totpCode={}; totpCodeNew={}", timeStep, totpCode, totpCodeNew);

        // TOTP Code2
        String tempInfo = new String(Hex.encodeHex(md.digest((totpCode + random + totpCodeNew).getBytes("utf-8"))));
        String timeStempNew = Long.toHexString(Long.parseLong(random + totpCode + totpCodeNew, 10)).toUpperCase();

        String totpCode2 = generateTOTP(tempInfo, timeStempNew, "8", HMAC_SHA256);
        String totpCode2New = (new StringBuilder(totpCode2)).reverse().toString();

        totpCode2 += totpCode2New;

        totpCode2 = totpCode2.substring(0,
                userIdMaxLen + truckIdMaxLen - (String.valueOf(random)).length() - totoCodeSize);
        LOGGER.info("tempInfo={}; timeStempNew={}; totpCode2={}", tempInfo, timeStempNew, totpCode2);

        // Encoded User's Token
        long encodedToken = Long.parseLong(userToken + truckIdNew, 10)
                + Long.parseLong(random + totpCodeNew + totpCode2, 10);

        String strEncodedToken = String.valueOf(encodedToken);
        int padding = getRandom(0, 10);
        if (strEncodedToken.length() <= userIdMaxLen + truckIdMaxLen) {
            if (padding % 2 == 1) {
                padding += 1;
            }
        } else {
            if (padding % 2 == 0) {
                padding += 1;
            }
        }
        padding = padding % 10;
        strEncodedToken = padding + strEncodedToken.substring(strEncodedToken.length() - userIdMaxLen - truckIdMaxLen);
        LOGGER.info("strEncodedToken={}", strEncodedToken);

        // Offline Authorization Code
        StringBuilder oac = new StringBuilder();
        oac.append(random);
        oac.append(totpCode);
        oac.append(strEncodedToken);

        String oacLine = oac.toString();
        LOGGER.info("Offline Authorization Code=" + oacLine);
        return oacLine;
    }

    public static void main(String[] args) throws Exception {

        long ln1 = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (int k = 0; k < 1; k++) {

            ///////////////////////////////////// Mobile Side Encode
            ///////////////////////////////////// ///////////////////////////////////////
            // // User Account Info
            int userLen = 8;
            String userToken = "150";
            String userPrivateKey = "8474a2395450df363a02dae5bd40bd70";

            // // Truck ID info
            int truckLen = 7;
            String truckId = "16750";

            int totpSize = 6;
            String oacLine = generateEncodedString(userToken, userPrivateKey, truckId, userLen, truckLen, totpSize);
            LOGGER.debug("Offline Authorization Code=" + oacLine);

            ///////////////////////////////////// Server Side DECODE
            ///////////////////////////////////// ////////////////////////////////////////

            // Random
            String tmp0 = String.valueOf(oacLine.charAt(0));
            String tmp1 = String.valueOf(oacLine.charAt(1));
            String strRandom = tmp0 + tmp1;
            int intRandom = Integer.parseInt(strRandom, 10);

            map.put(oacLine, String.valueOf(intRandom) + "-" + k);
            map2.put(String.valueOf(intRandom), oacLine);

            // TOTP Code
            String strTOTPCode = oacLine.substring(2, 2 + totpSize);
            String strTOTPCodeNew = (new StringBuilder(strTOTPCode)).reverse().toString();

            // Time Step
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String tempInfoNew = new String(
                    Hex.encodeHex(md.digest((strTOTPCode + strRandom + strTOTPCodeNew).getBytes("utf-8"))));
            String strTimeStempNew = Long.toHexString(Long.parseLong(strRandom + strTOTPCode + strTOTPCodeNew, 10))
                    .toUpperCase();
            String strTotpCode2 = generateTOTP(tempInfoNew, strTimeStempNew, "8", HMAC_SHA256);
            String strTotpCode2New = (new StringBuilder(strTotpCode2)).reverse().toString();

            strTotpCode2 += strTotpCode2New;

            strTotpCode2 = strTotpCode2.substring(0, userLen + truckLen - strRandom.length() - totpSize);

            long lnTotpCode = Long.parseLong(intRandom + strTOTPCodeNew + strTotpCode2, 10);

            // Encode User's Token
            String strPadding = String.valueOf(oacLine.charAt(2 + totpSize));
            int intPadding = Integer.parseInt(strPadding, 10);
            String strToken = (intPadding % 2) + oacLine.substring(2 + totpSize + 1);
            long userId = Long.parseLong(strToken, 10);

            long decodeToken = userId - lnTotpCode;

            String decodeUser = String.valueOf(decodeToken);
            String decodeTruck = String
                    .valueOf(Long.parseLong(decodeUser.substring(decodeUser.length() - truckLen), 10));
            decodeUser = decodeUser.substring(0, decodeUser.length() - truckLen);

            // Priavte Key and Encoded
            String userInfo2 = new String(
                    Hex.encodeHex(md.digest((decodeUser + decodeTruck + userPrivateKey).getBytes("utf-8"))));
            LOGGER.debug("userInfo=" + userInfo2);

            // Server Side Timestamp
            long serverTimeStamp = System.currentTimeMillis() / 1000 / 30;
            LOGGER.debug("serverTimeStamp=" + serverTimeStamp);

            // Mobile Side Time <= Server Side Time ( 5 minutes)
            boolean blBefore = false;
            for (long i = serverTimeStamp; i > serverTimeStamp - 10; i--) {
                // Time Step
                String timeStemp2 = Long.toHexString(i * 100 + intRandom).toUpperCase();
                LOGGER.debug("timeStemp2=" + timeStemp2);

                // TOTP Code
                String newTOTPCode = generateTOTP(userInfo2, timeStemp2, String.valueOf(totpSize), HMAC_SHA256);
                LOGGER.debug(newTOTPCode + "| SHA256   |");
                if (newTOTPCode.equals(strTOTPCode)) {
                    LOGGER.debug(strTOTPCode + "| Before OK |");
                    blBefore = true;
                    break;
                }
            }

            // Mobile Side Time > Server Side Time ( 3 minutes)
            if (!blBefore) {
                for (long i = serverTimeStamp + 1; i < serverTimeStamp + 6; i++) {
                    // Time Step
                    String timeStemp2 = Long.toHexString(i * 100 + intRandom).toUpperCase();

                    // TOTP Code
                    String newTOTPCode = generateTOTP(userInfo2, timeStemp2, String.valueOf(totpSize), HMAC_SHA256);
                    LOGGER.debug(newTOTPCode + "| SHA256   |");
                    if (newTOTPCode.equals(strTOTPCode)) {
                        LOGGER.debug(strTOTPCode + "| After OK |");
                        break;
                    }
                }
            }
        }
        LOGGER.debug("Total=" + (System.currentTimeMillis() - ln1));
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            LOGGER.debug(entry.getKey() + "[" + (i++) + "]=" + entry.getValue());
        }

        i = 0;
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            LOGGER.debug(entry.getKey() + "[" + (i++) + "]=" + entry.getValue());
        }
    }

}