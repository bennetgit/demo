package spring.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTs是一份草案，尽管在本质上它是一个老生常谈的一种更加具体的认证授权的机制。
 * 一个JWT被周期（period）分成了三个部分。JWT是URL-safe的，意味着可以用来查询字符参数。
 * （注：也就是可以脱离URL，不用考虑URL的信息）
 */
public class JWTUtil {

    private JWTUtil() {
    }

    /**
     * 
     * @param version
     * @param expirationTime
     * @param userName
     * @param clientId
     * @param secretKey
     *            私钥,服务端定义
     * @return
     */
    public static String getJWTString(String version, Long expirationTime, String userName, String clientId,
            String secretKey) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", Header.JWT_TYPE);
        headerMap.put("alg", signatureAlgorithm.getValue());

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("sub", userName);
        claimsMap.put("aud", clientId);
        claimsMap.put("exp", expirationTime);
        claimsMap.put("iat", new Date().getTime());
        claimsMap.put("ver", version);
        return Jwts.builder().setHeader(headerMap).setClaims(claimsMap).signWith(signatureAlgorithm, secretKey)
                .compact();
    }

    public static boolean isValid(String token, String key) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
