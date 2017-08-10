package com.gwf.family.sys.role.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@Entity
@Data
@Table(name = "sys_role")
public class SysRole  implements Serializable{
    private static final long serialVersionUID = 3433359804434539319L;

    public SysRole(){
    }

    public SysRole(Integer id,String name){
        this.id=id;
        this.name=name;
    }

    /** id */
    @Id
    private Integer id;
    /** name */
    private String name;

}

