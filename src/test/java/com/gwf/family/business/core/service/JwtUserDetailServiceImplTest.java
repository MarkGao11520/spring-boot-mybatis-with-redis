package com.gwf.family.business.core.service;

import com.gwf.family.SpringBootMybatisWithRedisApplication;
import com.gwf.family.business.core.entity.JwtUser;
import com.gwf.family.sys.user.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by gaowenfeng on 2017/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUserDetailServiceImplTest{

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Test
    public void loadUserByUsername() throws Exception {
        UserDetails jwtUser =  jwtUserDetailService.loadUserByUsername("gwf");
        Assert.assertNotNull(jwtUser);
    }

}