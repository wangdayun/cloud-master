package com.cloud.core.common;

import com.cloud.core.constant.RsaResult;
import io.jsonwebtoken.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * Token工具
 *
 * @author : dayun_wang
 */
public class JwtUtils {
    private static RSAPrivateKey priKey;
    private static RSAPublicKey pubKey;

    private static class SingletonHolder {
        private static final JwtUtils INSTANCE = new JwtUtils();
    }

    public synchronized static JwtUtils getInstance(String modulus, String privateExponent, String publicExponent) {
        if (priKey == null && pubKey == null) {
            priKey = RsaUtils.getPrivateKey(modulus, privateExponent);
            pubKey = RsaUtils.getPublicKey(modulus, privateExponent);
        }
        return SingletonHolder.INSTANCE;
    }

    public synchronized static void reload(String modulus, String privateExponent, String publicExponent) {
        priKey = RsaUtils.getPrivateKey(modulus, privateExponent);
        pubKey = RsaUtils.getPublicKey(modulus, publicExponent);
    }

    public synchronized static JwtUtils getInstance() {
        if (pubKey == null && priKey == null) {
            priKey = RsaUtils.getPrivateKey(RsaUtils.modulus, RsaUtils.privateExponent);
            pubKey = RsaUtils.getPublicKey(RsaUtils.modulus, RsaUtils.publicExponent);
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取token
     *
     * @param uid  用户ID
     * @param data 失效时间，单位分钟
     * @return
     */
    public static String getToken(String uid, int data) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * data;
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 获取token
     *
     * @param uid
     * @return
     */
    public static String getToken(String uid) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 检验token是否合法
     *
     * @param token
     * @return
     */
    public static RsaResult checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
            String sub = claims.get("sub", String.class);
            return new RsaResult(true, sub, "合法请求", CodeUtils.SUCCESS_CODE.getCode());
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return new RsaResult(false, null, "token已过期", CodeUtils.TOKEN_TIMEOUT_CODE.getCode());
        } catch (Exception e) {
            return new RsaResult(false, null, "非法请求", CodeUtils.NO_AUTH_CODE.getCode());
        }
    }

    public static void main(String[] args) {
        getInstance();
        String value = getToken("wangdayun");
        System.out.println(value);
        System.out.println(checkToken(value));
    }
}
