package com.gwf.family.sys.role.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.sys.role.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@org.apache.ibatis.annotations.Mapper
public interface SysRoleRepository extends Mapper<SysRole> {
    @Select("select t2.* from sys_user_roles t1 left join sys_role t2 on t1.role_id = t2.id where user_id = #{user_id}")
    List<SysRole> findRolesByUserId(Integer userId);
}

