package com.gwf.family.business.core.entity;

import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.user.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUser user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                map2GrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> map2GrantedAuthorities(List<SysRole> authorities){
        return authorities.stream()
                .map(e -> role2SimpleGrantedAuthority(e))
                .collect(Collectors.toList());
    }

    private static SimpleGrantedAuthority role2SimpleGrantedAuthority(SysRole role){
        return new SimpleGrantedAuthority(role.getName());
    }
}
