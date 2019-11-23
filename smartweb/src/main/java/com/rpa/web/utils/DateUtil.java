package com.rpa.web.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 11:43 2019/11/23
 * @version: 1.0.0
 * @description: 日期转换工具
 */
public class DateUtil {

    /**
     * date转String
     */
    public static String date2str(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
