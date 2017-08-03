package ${basePackage}.dao;

import ${corePackage}.mapper.Mapper;
import ${basePackage}.entity.${table.entityName};

/**
* Created with ${author} on ${.now}.
*/
@org.apache.ibatis.annotations.Mapper
public interface ${table.entityName}Repository extends Mapper<${table.entityName}> {

}

