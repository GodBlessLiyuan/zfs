package com.rpa.datav.controller;

import com.rpa.datav.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author: xiahui
 * @date: Created in 2019/11/21 11:31
 * @description: 数据大脑
 * @version: 1.0
 */
@RequestMapping("datav")
@RestController
public class DatavController {

    private final static Logger logger = LoggerFactory.getLogger(DatavController.class);

    /*全国数据 右上角*/
    @GetMapping("userfrequency")
    public String userFrequency(@RequestParam String id) {

        logger.error("userFrequency   id:"+id);

        try {
            int num = Integer.valueOf(id);
            if (num == 6) {
                return CommonConstant.userFrequency1;
            } else if (num == 7) {
                return CommonConstant.userFrequency2;
            }
        }catch (Exception e){
            return CommonConstant.userFrequency1;
        }
        return null;
    }
    /*全国数据页面 5个tab键请求数据*/
    @GetMapping("usernumber")
    public String userNumber(@RequestParam String fid) {
        logger.error("userNumber   fid:"+fid);
        try {
            int num = Integer.valueOf(fid);
            if (num == 1) {
                return CommonConstant.userNumber1;
            } else if (num == 2) {
                return CommonConstant.userNumber2;
            }else if (num == 3){
                return CommonConstant.userNumber3;
            }else if (num == 4){
                return CommonConstant.userNumber4;
            }else if (num == 5){
                return CommonConstant.userNumber5;
            }
        }catch (Exception e){
            return CommonConstant.userNumber1;
        }
        return null;
    }

    @GetMapping("zjinfonumber")
    public String userNumber(@RequestParam String id) {
        try {
            int num = Integer.valueOf(id);
            if (num == 8) {
                return ZJCommonConstant.zjuserNumber1;
            } else if (num == 9) {
                return ZJCommonConstant.zjuvipNumber2;.
            } else if (num == 10) {
                return ZJCommonConstant.zjhuoyueNumber3;
            } else if (num == 11) {
                return ZJCommonConstant.zjtotalhourNumber4;
            } else if (num == 12) {
                return ZJCommonConstant.zjpinciNumber5;
            }
        } catch (Exception e) {
            return ZJCommonConstant.zjuserNumber1;
        }
        return null;
    }


    Random random = new Random();

    /*全国 平台激活用户总数*/
    int activate_num = 230000;
    long lastTime;
    @GetMapping("totalusernumber")
    public String totalUserNumber() {

        long diff = System.currentTimeMillis()-lastTime;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
            activate_num = random.nextInt(3) + activate_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",activate_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",activate_num);
        }
    }


    /* 全国 当前VIP用户数*/
    int vip_num = 54800;
    long lastTime1;
    @GetMapping("vipusernumber")
    public String vipUserNumber() {

        long diff = System.currentTimeMillis()-lastTime1;
        if(diff>=6*1000){
            lastTime1 = System.currentTimeMillis();
            vip_num = random.nextInt(3) + vip_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",vip_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",vip_num);
        }
    }

    /* 全国 本月新增用户数*/
    int new_add_num = 15000;
    long lastTime2;
    @GetMapping("newaddusernumber")
    public String newaddUserNumber() {

        long diff = System.currentTimeMillis()-lastTime2;
        if(diff>=6*1000){
            lastTime2 = System.currentTimeMillis();
            new_add_num = random.nextInt(3) + new_add_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",new_add_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",new_add_num);
        }
    }

    /*全国 本月活跃用户*/
    int huoyue_num = 138000;
    long lastTime3;
    @GetMapping("huoyueusernumber")
    public String huoyueUserNumber() {

        long diff = System.currentTimeMillis()-lastTime3;
        if(diff>=6*1000){
            lastTime3 = System.currentTimeMillis();
            huoyue_num = random.nextInt(3) + huoyue_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",huoyue_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",huoyue_num);
        }
    }

    /*全国 本月使用总频次*/
    int total_pinci = 1219300;
    long lastTime4;
    @GetMapping("totalusepincinum")
    public String totalusepinciNumber() {

        long diff = System.currentTimeMillis()-lastTime4;
        if(diff>=6*1000){
            lastTime4 = System.currentTimeMillis();
            total_pinci = random.nextInt(20) + total_pinci;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",total_pinci);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",total_pinci);
        }
    }

    /*全国 本月使用总时长*/
    int totalhour_num = 1115530;
    long lastTime5;
    @GetMapping("totalhournumber")
    public String totalhourNumber() {

        long diff = System.currentTimeMillis()-lastTime5;
        if(diff>=6*1000){
            lastTime5 = System.currentTimeMillis();
            totalhour_num = random.nextInt(20) + totalhour_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",totalhour_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",totalhour_num);
        }
    }



    /* 左1 浙江平台激活用户总数*/
    int zj_activate_num = 32000;
    long lastTime_zj;
    @GetMapping("zjtotalusernumber")
    public String zjtotalUserNumber() {

        long diff = System.currentTimeMillis()- lastTime_zj;
        if(diff>=6*1000){
            lastTime_zj = System.currentTimeMillis();
            zj_activate_num = random.nextInt(3) + zj_activate_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]", zj_activate_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]", zj_activate_num);
        }
    }


    /* 左2 浙江本月新增用户数*/
    int zj_new_add_num = 15000;
    long lastTime_zj1;
    @GetMapping("zjnewaddusernumber")
    public String zjnewaddUserNumber() {

        long diff = System.currentTimeMillis()- lastTime_zj1;
        if(diff>=6*1000){
            lastTime_zj1 = System.currentTimeMillis();
            zj_new_add_num = random.nextInt(3) + zj_new_add_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_new_add_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_new_add_num);
        }
    }


    /* 左3 浙江当前VIP用户数*/
    int zj_vip_num = 4800;
    long lastTime1_zj;
    @GetMapping("zjvipusernumber")
    public String zjvipUserNumber() {

        long diff = System.currentTimeMillis()- lastTime1_zj;
        if(diff>=6*1000){
            lastTime1_zj = System.currentTimeMillis();
            zj_vip_num = random.nextInt(3) + zj_vip_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]", zj_vip_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]", zj_vip_num);
        }
    }

    /* 右1 浙江本月使用总频次*/
    int zj_total_pinci = 253000;
    long lastTime4_zj;
    @GetMapping("zjtotalusepincinum")
    public String zjtotalusepinciNumber() {

        long diff = System.currentTimeMillis()-lastTime4_zj;
        if(diff>=6*1000){
            lastTime4_zj = System.currentTimeMillis();
            zj_total_pinci = random.nextInt(10) + zj_total_pinci;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_total_pinci);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_total_pinci);
        }
    }


    /*右2 浙江本月使用总时长*/
    int zj_totalhour_num = 482200;
    long lastTime5_zj;
    @GetMapping("zjtotalhournumber")
    public String zjtotalhourNumber() {

        long diff = System.currentTimeMillis()-lastTime5_zj;
        if(diff>=6*1000){
            lastTime5_zj = System.currentTimeMillis();
            zj_totalhour_num = random.nextInt(10) + zj_totalhour_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_totalhour_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_totalhour_num);
        }
    }


    /*右3 浙江本月活跃用户*/
    int zj_huoyue_num = 6980;
    long lastTime3_zj;
    @GetMapping("zjhuoyueusernumber")
    public String zjhuoyueUserNumber() {

        long diff = System.currentTimeMillis()-lastTime3_zj;
        if(diff>=6*1000){
            lastTime3_zj = System.currentTimeMillis();
            zj_huoyue_num = random.nextInt(3) + zj_huoyue_num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_huoyue_num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",zj_huoyue_num);
        }
    }



}
