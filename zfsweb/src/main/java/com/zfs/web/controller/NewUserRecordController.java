package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.INewUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 17:58
 * @description: 新用户赠送记录
 * @version: 1.0
 */
@RequestMapping("newuserrecord")
@RestController
public class NewUserRecordController {

    @Autowired
    private INewUserRecordService service;

    @RequestMapping("query")
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "phone", required = false) String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(pageNum, pageSize, reqData);
    }
}
