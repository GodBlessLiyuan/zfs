package com.zfs.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author: velve
 * @date: Created in 2020/5/22 19:10
 * @description: json字符串转换成Object类型
 * @version: 1.0
 */
public class StringToObjUtil {

    /**
     * 将String类型转为Object类型
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T strToObj(String str, Class<T> clazz){
        if(!StringUtils.isEmpty(str)){

            try {
                if(clazz == List.class){
                    return (T)  JSON.toJavaObject(JSONObject.parseArray(str),clazz);
                }else {
                    return (T) JSON.toJavaObject(JSON.parseObject(str), clazz);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
