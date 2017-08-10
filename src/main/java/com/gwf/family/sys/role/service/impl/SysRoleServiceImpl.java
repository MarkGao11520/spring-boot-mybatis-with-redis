package com.gwf.family.sys.role.service.impl;

import com.gwf.family.sys.role.dao.SysRoleRepository;
import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-8-10 14:15:19.
 */
@Service
@Transactional
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

}
