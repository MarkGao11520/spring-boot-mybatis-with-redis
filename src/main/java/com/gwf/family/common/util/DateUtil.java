package com.gwf.family.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lcy on 17/2/17.
 */
public class DateUtil {
    /**
     * 获取格式化日期
     * @param format
     * @param date
     * @return
     */
    public static String getSimepleDate(String format, Date date){
        if(date == null)
            date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
