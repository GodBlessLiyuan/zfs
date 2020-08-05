package com.zfs.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 10:40
 * @description: 日期工具
 * @version: 1.0
 */
public class DateUtil {
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 计算相隔天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int daysApart(Date start, Date end) {
        if (end.getTime() <= start.getTime()) {
            return 0;
        }

        try {
            start = sdf1.parse(sdf1.format(start));
            end = sdf1.parse(sdf1.format(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (end.getTime() == start.getTime()) {
            return 1;
        }

        return (int) ((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000));

    }
}
