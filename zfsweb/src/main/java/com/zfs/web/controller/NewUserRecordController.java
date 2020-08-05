package com.zfs.web.controller;

import com.zfs.web.vo.NewUserRecordVO;
import com.zfs.web.service.INewUserRecordService;
import com.zfs.web.utils.DTPageInfo;
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
    public DTPageInfo<NewUserRecordVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "phone") String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
