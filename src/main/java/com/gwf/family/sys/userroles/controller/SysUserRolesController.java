package com.gwf.family.sys.userroles.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import com.gwf.family.sys.userroles.service.SysUserRolesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by gwf on 2017-8-10 14:15:19.
*/
@RestController
@RequestMapping("/sys/user/roles")
public class SysUserRolesController {
    @Autowired
    private SysUserRolesService sysUserRolesService;

    @PostMapping("/add")
    public Result add(SysUserRoles sysUserRoles) {
        sysUserRolesService.save(sysUserRoles);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        sysUserRolesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysUserRoles sysUserRoles) {
        sysUserRolesService.update(sysUserRoles);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        SysUserRoles sysUserRoles = sysUserRolesService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserRoles);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserRoles> list = sysUserRolesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
