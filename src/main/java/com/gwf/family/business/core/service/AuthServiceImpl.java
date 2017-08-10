package com.gwf.family.business.core.service;

import com.gwf.family.common.util.JwtUtil;
import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private SysUserRepository userRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           SysUserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public SysUser register(SysUser userToAdd) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = JwtUtil.setClaim(username);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
