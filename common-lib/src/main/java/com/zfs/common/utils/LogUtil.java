package com.zfs.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 18:38 2019/12/21
 * @version: 1.0.0
 * @description:
 */
public class LogUtil {

    /**
     * 针对于逻辑错误，当条件触发时，调用该方法打印日志
     */
    public static void log(Logger logger, String methodName, String description, Object... params) {
        String current_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.error("逻辑错误：time={}, methodName = {}, description = {}, parameters = {}", current_date, methodName, description, JSON.toJSONString(params));
    }

}
