package ${basePackage}.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* Created with ${author} on ${.now}.
*/
@Entity
@Data
@Table(name = "${table.tableName}")
public class ${table.entityName}  implements Serializable{
    public ${table.entityName}(){
    }

    public ${table.entityName}(<#list table.columnModels as item><#if item_index!=0>,</#if>${item.javaType} ${item.columnNameLowerCamel}</#list>){
    <#list table.columnModels as item>
        this.${item.columnNameLowerCamel}=${item.columnNameLowerCamel};
    </#list>
    }

    <#list table.columnModels as item>
    /** ${item.remarks} */
    <#if item.isKey>
    @Id
    </#if>
    private ${item.javaType} ${item.columnNameLowerCamel};
    </#list>

}

