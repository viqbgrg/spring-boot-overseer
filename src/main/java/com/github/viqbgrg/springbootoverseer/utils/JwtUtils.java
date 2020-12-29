package com.github.viqbgrg.springbootoverseer.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class JwtUtils {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60;
    public static String sign(String username, String salt) {
        return sign(username, salt, EXPIRE_TIME);
    }
    public static String sign(String username, String salt, long time) {
        try {
            Date date = new Date(System.currentTimeMillis() + time * 1000);
            Algorithm algorithm = Algorithm.HMAC256(salt);
            String token = JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            log.error("Token Error:{}", exception.getMessage());
            return null;
        }
    }

    public static boolean verify(String token, String username, String salt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(salt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build(); //Reusable verifier instance
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            log.error("Token Error:{}", exception.getMessage());
            return false;
        }
    }

    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String username = jwt.getClaim(claim).asString();
            return username;
        } catch (JWTDecodeException exception) {
            return null;
        }
    }

    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @param token
     * @return true 过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }
}
