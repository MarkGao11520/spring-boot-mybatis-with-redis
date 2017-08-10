package com.gwf.family.sys.user.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.sys.user.entity.SysUser;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@org.apache.ibatis.annotations.Mapper
public interface SysUserRepository extends Mapper<SysUser> {

    @Select("select * from sys_user where username = #{username}")
    @ResultMap("BaseResultMap")
    SysUser findByUserName(String username);
}

