package com.gwf.family.sys.userroles.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import com.gwf.family.sys.userroles.service.SysUserRolesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by gwf on 2017-8-10 14:15:19.
*/
@RestController
@RequestMapping("/sys/user/roles")
public class SysUserRolesController {
    @Autowired
    private SysUserRolesService sysUserRolesService;

    @PostMapping
    public Result add(SysUserRoles sysUserRoles) {
        sysUserRolesService.save(sysUserRoles);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    public Result delete(@PathVariable Integer id) {
        sysUserRolesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    public Result update(SysUserRoles sysUserRoles) {
        sysUserRolesService.update(sysUserRoles);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    public Result detail(@PathVariable Integer id) {
        SysUserRoles sysUserRoles = sysUserRolesService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserRoles);
    }

    @GetMapping
    public Result list(@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserRoles> list = sysUserRolesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
