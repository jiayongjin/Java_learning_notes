package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
//    令牌过期时间毫秒
    private final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

//    签名密钥
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return generateToken(new HashMap<>(),username);
    }

    /**
     * 生成包含自定义声明的JWT令牌
     * @param claims
     * @param username
     * @return
     */
    public String generateToken(Map<String,Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从令牌中获取过期时间
     * @param token
     * @return
     */
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从令牌中获取指定声明
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    public static <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseEncryptedClaims(token)
                .getBody();
    }

    /**
     * 检查令牌是否过期
     * @param token JWT令牌
     * @return 如果过期返回true，否则返回false
     */
    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 验证令牌是否有效
     * @param token JWT令牌
     * @param username 用户名
     * @return 如果有效返回true，否则返回false
     */
    public static boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
