package ${basePackage}.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.*;

/**
* Created with ${author} on ${.now}.
*/
@Entity
@Data
@Table(name = "${table.tableName}")
@NoArgsConstructor
@AllArgsConstructor
public class ${table.entityName}  implements Serializable{

    <#list table.columnModels as item>
    /** ${item.remarks} */
    <#if item.isKey>
    @Id
    </#if>
    @ApiModelProperty(value = "${item.remarks}")
    private ${item.javaType} ${item.columnNameLowerCamel};
    </#list>

}

