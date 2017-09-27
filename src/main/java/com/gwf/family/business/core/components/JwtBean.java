package com.gwf.family.business.core.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/8/9.
 */

public class JwtBean {

    @Value("${jwt.secret}")
    private String secret;


    /**
     * 根据token获取用户信息
     */
    public Claims getClaimsFromToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 设置用户信息进jwt
     */
    public String generateToken(String subject, Map<String,Object> map){
        String token = Jwts
                .builder()
                .setClaims(map)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }
}
