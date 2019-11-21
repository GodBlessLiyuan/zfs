package com.rpa.datav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/11/21 11:31
 * @description: 数据大脑
 * @version: 1.0
 */
@RequestMapping("datav")
@RestController
public class DatavController {
    private static final String userNumber1 = "[\n" +
            "{\n" +
            "\"x\": \"10/01\",\n" +
            "\"y\": 600\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/02\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/03\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/04\",\n" +
            "\"y\": 790\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/05\",\n" +
            "\"y\": 690\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/06\",\n" +
            "\"y\": 790\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/07\",\n" +
            "\"y\": 690\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/08\",\n" +
            "\"y\": 790\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/09\",\n" +
            "\"y\": 790\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/10\",\n" +
            "\"y\": 690\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/12\",\n" +
            "\"y\": 780\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/13\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/14\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/15\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/16\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/17\",\n" +
            "\"y\": 900\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/18\",\n" +
            "\"y\": 1000\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/19\",\n" +
            "\"y\": 900\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/20\",\n" +
            "\"y\": 900\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/21\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/22\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/23\",\n" +
            "\"y\": 900\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/24\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/25\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/26\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/27\",\n" +
            "\"y\": 800\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/28\",\n" +
            "\"y\": 900\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/29\",\n" +
            "\"y\": 700\n" +
            "},\n" +
            "{\n" +
            "\"x\": \"10/30\",\n" +
            "\"y\": 800\n" +
            "}\n" +
            "]";

    @GetMapping("usernumber")
    public String userNumber(@RequestParam Integer id) {
        if (id == 1) {
            return userNumber1;
        }
        return null;
    }
}
