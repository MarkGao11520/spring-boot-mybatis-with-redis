package com.gwf.family.sys.user.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.sys.user.entity.SysUser;
import com.gwf.family.sys.user.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by gwf on 2017-8-10 14:15:19.
*/
@RestController
@RequestMapping("/sys/user")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/add")
    public Result add(SysUser sysUser) {
        sysUserService.save(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        sysUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysUser sysUser) {
        sysUserService.update(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostAuthorize("returnObject.data.username == principal.username or hasRole('ROLE_ADMIN')")
    @PostMapping("/detail")
    public Result detail(Integer id) {
        SysUser sysUser = sysUserService.findById(id);
        return ResultGenerator.genSuccessResult(sysUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
