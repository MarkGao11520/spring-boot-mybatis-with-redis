package com.gwf.family.sys.user.service.impl;

import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.entity.SysUser;
import com.gwf.family.sys.user.service.SysUserService;
import com.gwf.family.sys.userroles.dao.SysUserRolesRepository;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by gwf on 2017-8-10 14:15:19.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

}
