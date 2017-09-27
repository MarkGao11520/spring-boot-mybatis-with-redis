package com.gwf.family.business.core.service;


import com.gwf.family.sys.user.entity.SysUser;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
public interface AuthService {
    void register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
