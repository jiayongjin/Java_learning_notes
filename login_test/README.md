# 登录校验——令牌方案



<img src="assets\image-20250826085624336.png">

JWT令牌相关网站

[jwt官网](https://jwt.p2hp.com/)

引入相关依赖

```java
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.12.3</version>
</dependency>
```

生成jwt令牌

```java
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
```

用ai生成util就可以，直接使用就行

如文件JwtUtils



