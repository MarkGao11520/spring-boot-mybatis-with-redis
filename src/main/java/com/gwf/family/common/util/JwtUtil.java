package com.gwf.family.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private static String secret;


    public static Claims getClaim(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("abcdefg")
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }

    public static String setClaim(String subject){
        String token = Jwts
                .builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "abcdefg")
                .compact();
        return token;
    }
}
