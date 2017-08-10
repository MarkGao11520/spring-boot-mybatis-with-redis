package com.gwf.family.sys.userroles.service.impl;

import com.gwf.family.sys.userroles.dao.SysUserRolesRepository;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import com.gwf.family.sys.userroles.service.SysUserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-8-10 14:15:19.
 */
@Service
@Transactional
public class SysUserRolesServiceImpl extends AbstractService<SysUserRoles> implements SysUserRolesService {
    @Autowired
    private SysUserRolesRepository sysUserRolesRepository;

}
