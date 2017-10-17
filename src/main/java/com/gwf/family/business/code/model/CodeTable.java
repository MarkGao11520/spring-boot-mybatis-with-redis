package com.gwf.family.business.code.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lcy on 17/7/1.
 *
 * code table
 */
@Data
@NoArgsConstructor
public class CodeTable implements Serializable {


    private static final long serialVersionUID = 1154645476624841358L;

    private String tableName;
}
