package com.zfs.web.utils;

import org.springframework.util.StringUtils;
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
    /**
     * 文件大小 ：除以1024则和显示的不一致，所以除了1000；
     *  除法太费计算，则使用字符串移位的形式实现
     *  整个代码替换计算： (2238249f/1000/1000)
     * */
    public static String get2WeiFileSize(int size,int len){
        String tmp=String.valueOf(size);
        StringBuilder stringBuilder=new StringBuilder();
        String result;
        if(tmp.length()>len){
            stringBuilder.append(tmp.substring(0,tmp.length()-len)).append(".");
            result=stringBuilder.append(tmp.substring(tmp.length()-len)).toString();
            result=new DecimalFormat("0.00").format(Float.valueOf(result));
        }else if(len-tmp.length()>2){
            return "0.00";
        }else{
            //0.xx,0.0xx,0.00xx的的形式下取小数点两位
            int shou=len-tmp.length();
            stringBuilder.append("0.");
            //补0
            for(;shou>0;shou--){
                stringBuilder.append("0");
            }
            result=stringBuilder.append(tmp).toString();
            result=new DecimalFormat("0.00").format(Float.valueOf(result));
        }
        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(get2WeiFileSize(2238249,6));//2.24
//    }
}
