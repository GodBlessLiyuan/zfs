package com.zfs.web.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * @Description:除法
 * @author: liyuan
 * @data 2020-08-25 15:10
 */
public class MultiUtil {

    public static  String strBystr(String qian,String hou) {
        if(StringUtils.isEmpty(hou)||hou.equals("0")){
            return "0.00%";
        }else {
            Float f=Float.valueOf(qian);
            Integer l = Integer.valueOf(hou);
            DecimalFormat df=new DecimalFormat("0.00");
            return df.format(f*100 / l)+"%";
        }
    }
}
