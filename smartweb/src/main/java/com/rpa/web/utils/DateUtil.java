package com.rpa.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    /**
     * String转date，加一天后再转回String
     * @param strDate
     * @return
     */
    public static String plusOneDay(String strDate) {
        if (null == strDate || "".equals(strDate)) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();

            return format.format(date);
        }
    }
}
