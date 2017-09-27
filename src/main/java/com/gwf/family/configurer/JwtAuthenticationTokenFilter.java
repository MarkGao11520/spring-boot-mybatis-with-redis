package com.gwf.family.configurer;

import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * 这里注入的是前面写的UserDetailsService的实现类
     */
    @Autowired
    private UserDetailsService customUserService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);  // 取得header
        if (authHeader != null && authHeader.startsWith(tokenHead)) {  //判断header头
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = JwtUtil.getUsernameFromToken(authToken);   //从jwt中获取信息，如果要缓存很多信息可以用Claims
            logger.info("checking authentication " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.customUserService.loadUserByUsername(username);  //验证jwt的信息是否正确

                if (JwtUtil.validateToken(authToken, userDetails)) {
                    //将验证信息放入SecurityContextHolder中，UsernamePasswordAuthenticationToken是Security验证账号密码的工具类
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
