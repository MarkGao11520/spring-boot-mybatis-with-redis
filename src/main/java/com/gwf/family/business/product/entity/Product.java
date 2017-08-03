package com.gwf.family.business.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
* Created with gwf on 2017-8-3 17:04:43.
*/
@Entity
@Data
@Table(name = "product")
public class Product implements Serializable{


    private static final long serialVersionUID = -4340070984769081797L;

    public Product(){
    }

    public Product(long id,String name,long price){
        this.id=id;
        this.name=name;
        this.price=price;
    }

    /**  */
    @Id
    @GeneratedValue(generator = "JDBC")
    private long id;
    /**  */
    private String name;
    /**  */
    private long price;

}

