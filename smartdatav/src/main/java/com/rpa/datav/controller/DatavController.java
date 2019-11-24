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

    int num = 230000;
    long lastTime;
    Random random = new Random();
    @GetMapping("totalusernumber")
    public String totalUserNumber() {

        long diff = System.currentTimeMillis()-lastTime;
        if(diff>=6*1000){
            lastTime = System.currentTimeMillis();
            num = random.nextInt(2) + num;
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",num);
        }else {
            return String.format("[\n" +
                    "{\n" +
                    "\"name\": \"\",\n" +
                    "\"value\": %s\n" +
                    "}\n" +
                    "]",num);
        }
    }


}
