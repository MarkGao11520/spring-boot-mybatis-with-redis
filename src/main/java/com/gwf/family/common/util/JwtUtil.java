package com.gwf.family.common.util;

import com.gwf.family.business.core.components.JwtBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Component
public class JwtUtil {
    private static JwtBean jwtBean;

    @Autowired
    private JwtUtil(JwtBean jwtBean){
        this.jwtBean = jwtBean;
    }


    /**
     * 根据token获取用户名
     */
    public static String getUsernameFromToken(String token){
        try {
            Claims claims = jwtBean.getClaimsFromToken(token);
            String username = claims.getSubject();
            return username;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 设置用户信息进jwt
     */
    public static String generateToken(UserDetails userDetails){
        Map<String,Object> map = new HashedMap();
        map.put(userDetails.getUsername(),userDetails);
        String token = jwtBean.generateToken(userDetails.getUsername(),map);
        return token;
    }

    /**
     * 验证token是否正确
     * @param token
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String token,UserDetails userDetails){
        Claims claims = jwtBean.getClaimsFromToken(token);
        LinkedHashMap<String,Object> tokenUserDetails = (LinkedHashMap<String,Object>) claims.get(userDetails.getUsername());
        boolean usernameValid = userDetails.getUsername().equals(tokenUserDetails.get("username"));
        boolean passwordValid = userDetails.getPassword().equals(tokenUserDetails.get("password"));
        if(usernameValid||passwordValid)
            return true;
        else
            return false;
    }
}
