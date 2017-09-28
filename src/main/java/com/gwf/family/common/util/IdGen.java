package com.gwf.family.common.util;

import java.security.SecureRandom;
import java.util.UUID;


/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:27
 * description:
 */
public class IdGen {
    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
