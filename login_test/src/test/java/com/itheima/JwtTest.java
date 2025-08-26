package com.itheima;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    /**
     * 生成Jwt令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String,Object> dateMap = new HashMap<>();
        dateMap.put("id",1);
        dateMap.put("username","itheima");

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jwt = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS256,secretKey)
                                .setClaims(dateMap)
                                        .setExpiration(new Date(System.currentTimeMillis() + 3600 *1000))
                                                .compact();
        System.out.println(jwt);
    }
}
