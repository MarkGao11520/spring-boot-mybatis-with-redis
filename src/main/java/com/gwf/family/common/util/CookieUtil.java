package com.gwf.family.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * Created by gaowenfeng on 2017/8/2.
 */
public class CookieUtil {

    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
    }

    public static void get(){

    }
}
