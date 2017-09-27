package com.gwf.family.configurer;

import com.gwf.family.business.core.results.AuthErrorEnum;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现AuthenticationEntryPoint的commence方法自定义校验不通过的方法
 * Created by gaowenfeng on 2017/8/9.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 捕获AuthenticationException中的message，并封装成自定义异常抛出
        response.setCharacterEncoding("utf-8");
        response.getWriter().write( new Result().setCode(ResultCode.UNAUTHORIZED).setMessage(AuthErrorEnum.AUTH_HEADER_ERROR.getMessage()).toString());
    }
}
