package ${basePackage}.controller;
import ${corePackage}.results.Result;
import ${corePackage}.results.ResultGenerator;
import ${basePackage}.entity.${table.entityName};
import ${basePackage}.service.${table.entityName}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by ${author} on ${.now}.
*/
@RestController
@RequestMapping("${table.mappingPath}")
public class ${table.entityName}Controller {
    @Autowired
    private ${table.entityName}Service ${table.entityName?uncap_first}Service;

    @PostMapping
    public Result add(${table.entityName} ${table.entityName?uncap_first}) {
        ${table.entityName?uncap_first}Service.save(${table.entityName?uncap_first});
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    public Result delete(@PathVariable  Integer id) {
        ${table.entityName?uncap_first}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    public Result update(${table.entityName} ${table.entityName?uncap_first}) {
        ${table.entityName?uncap_first}Service.update(${table.entityName?uncap_first});
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    public Result detail(@PathVariable Integer id) {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.findById(id);
        return ResultGenerator.genSuccessResult(${table.entityName?uncap_first});
    }

    @GetMapping
    public Result list(@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<${table.entityName}> list = ${table.entityName?uncap_first}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
