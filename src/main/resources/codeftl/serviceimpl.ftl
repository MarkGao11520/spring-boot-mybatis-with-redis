package ${basePackage}.service.impl;

import ${basePackage}.dao.${table.entityName}Repository;
import ${basePackage}.entity.${table.entityName};
import ${basePackage}.service.${table.entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${corePackage}.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by ${author} on ${.now}.
 */
@Service
@Transactional
public class ${table.entityName}ServiceImpl extends AbstractService<${table.entityName}> implements ${table.entityName}Service {
    @Autowired
    private ${table.entityName}Repository ${table.entityName?uncap_first}Repository;

}
