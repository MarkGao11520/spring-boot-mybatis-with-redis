package com.gwf.family.business.core.service;

import com.gwf.family.business.core.entity.JwtUserFactory;
import com.gwf.family.sys.role.dao.SysRoleRepository;
import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.entity.SysUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Data
@org.springframework.stereotype.Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userRepository.findByUserName(s);
        if (sysUser == null)
            throw new UsernameNotFoundException("用户名不存在");
        List<SysRole> roles = sysRoleRepository.findRolesByUserId(sysUser.getId());
        if (CollectionUtils.isEmpty(roles))
            roles = Collections.emptyList();
        sysUser.setRoles(roles);
        return JwtUserFactory.create(sysUser);
    }
}
