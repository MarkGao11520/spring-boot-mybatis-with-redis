package com.gwf.family.sys.userroles.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@Entity
@Data
@Table(name = "sys_user_roles")
public class SysUserRoles  implements Serializable{
    private static final long serialVersionUID = 6379522951278191261L;

    public SysUserRoles(){
    }

    public SysUserRoles(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Id
    private Integer id;
    /** sys_user_id */
    private Integer userId;
    /** roles_id */
    private Integer roleId;

}

