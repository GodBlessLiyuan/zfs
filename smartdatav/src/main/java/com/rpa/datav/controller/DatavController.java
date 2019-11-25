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

    Random random = new Random();

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



    int vip_num = 54800;
    long lastTime1;
    @GetMapping("vipusernumber")
    public String vipUserNumber() {

        long diff = System.currentTimeMillis()-lastTime1;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
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

    int new_add_num = 15000;
    long lastTime2;
    @GetMapping("newaddusernumber")
    public String newaddUserNumber() {

        long diff = System.currentTimeMillis()-lastTime2;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
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

    int huoyue_num = 138000;
    long lastTime3;
    @GetMapping("huoyueusernumber")
    public String huoyueUserNumber() {

        long diff = System.currentTimeMillis()-lastTime3;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
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

    int total_pinci = 1219300;
    long lastTime4;
    @GetMapping("totalusepincinum")
    public String totalusepinciNumber() {

        long diff = System.currentTimeMillis()-lastTime4;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
            total_pinci = random.nextInt(50) + total_pinci;
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

    int totalhour_num = 1115530;
    long lastTime5;
    @GetMapping("totalhournumber")
    public String totalhourNumber() {

        long diff = System.currentTimeMillis()-lastTime5;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
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

}
