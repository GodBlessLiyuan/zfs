package com.zfs.web.utils;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.record.formula.functions.T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-24 11:26
 */
public class List2Str {
    /**
     * 参数为： list<Integer>/list<String>
    * */
    // "[所有, vbooster]",
    @Deprecated
    public static String listAttr2Str(List list){
        String[] array = (String[]) list.toArray(new String[list.size()]);
        return Arrays.toString(array);
    }
    //"[\"所有\",\"vbooster\"]",
    @Deprecated
    public static String listAttr2StrByJSON(List list){
        return JSON.toJSONString(list);
    }
    public static String StrJoinSep(List list){
        if(list==null||list.size()==0){
            return "";
        }
        return String.join(",",list);
    }
}
