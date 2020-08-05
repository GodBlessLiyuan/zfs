package com.zfs.web.utils;

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
     * String转date
     */
    public static Date str2date1(String strDate) {
        if (null == strDate || strDate.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * String转date，并设置为当天最后一刻
     */
    public static Date str2date2(String strDate) {

        if (null == strDate || strDate.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date = calendar.getTime();

        return date;
    }


    /**
     * String转date，加一天后再转回String
     * @param strDate
     * @return
     */
    public static String str2str1(String strDate) {
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

    /**
     * String转date，设置为当天最后一刻后，再转回String
     */
    public static String str2str2(String strDate) {
        if (null == strDate || "".equals(strDate)) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(str2date2(strDate));
        }
    }

    /**
     * String转date，只要时间
     */
    public static Date str2time(String strTime) {
        if (null == strTime || strTime.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;

        try {
            date = format.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
