package spring.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import spring.demo.constant.Constants;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTs是一份草案，尽管在本质上它是一个老生常谈的一种更加具体的认证授权的机制。
 * 一个JWT被周期（period）分成了三个部分。JWT是URL-safe的，意味着可以用来查询字符参数。
 * （注：也就是可以脱离URL，不用考虑URL的信息）
 */
public class JWTUtil {

    public static final String HEADER_KEY_TYPE = Header.TYPE;
    public static final String HEADER_KEY_ALG = "alg";

    public static final String BODY_KEY_EXPIRATION = Claims.EXPIRATION;
    public static final String BODY_KEY_ISSUED_AT = Claims.ISSUED_AT;
    public static final String BODY_KEY_VERSION = "ver";
    public static final String BODY_KEY_EMAIL = "email";
    public static final String BODY_KEY_USERNAME = "userName";

    private static final String JWT_DEFAULT_VERSION = "0.1";

    private JWTUtil() {
    }

    /**
     * @param expirationTimes timestamp
     * @param secretKey
     * @return
     */
    public static String generateToken(long expirationTimes, String secretKey, Map<String, Object> extensionData)
            throws UnsupportedEncodingException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put(HEADER_KEY_TYPE, Header.JWT_TYPE);
        headerMap.put(HEADER_KEY_ALG, signatureAlgorithm.getValue());

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(BODY_KEY_EXPIRATION, expirationTimes / 1000);// need second
        bodyMap.put(BODY_KEY_ISSUED_AT, new Date().getTime());
        bodyMap.put(BODY_KEY_VERSION, JWT_DEFAULT_VERSION);

        if (MapUtils.isNotEmpty(extensionData)) {
            extensionData.entrySet().stream().forEach(entry -> bodyMap.put(entry.getKey(), entry.getValue()));
        }

        String jwtStr = Jwts.builder().setHeader(headerMap).setClaims(bodyMap).signWith(signatureAlgorithm, secretKey)
                .compact();
        return Base64.getUrlEncoder().encodeToString(jwtStr.getBytes(Constants.DEFAULT_CHAR_SET));
    }

    public static <T> T tokenParse(String token, String bodyKey, Class<T> bodyValueType, String secretKey) {
        try {

            if (StringUtils.isEmpty(token)) {
                return null;
            }

            byte[] tempToken = Base64.getUrlDecoder().decode(token.getBytes(Constants.DEFAULT_CHAR_SET));

            Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(new String(tempToken, Constants.DEFAULT_CHAR_SET));
            if (jws == null) {
                return null;
            }

            return jws.getBody().get(bodyKey, bodyValueType);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getEmail(String token, String secretKey) {
        return tokenParse(token, BODY_KEY_EMAIL, String.class, secretKey);
    }

    public static String getUserName(String token, String secretKey) {
        return tokenParse(token, BODY_KEY_USERNAME, String.class, secretKey);
    }

    public static boolean isValid(String token, String secretKey) {
        String version = tokenParse(token, BODY_KEY_VERSION, String.class, secretKey);
        if (version == null) {
            return false;
        }

        return true;
    }

}
