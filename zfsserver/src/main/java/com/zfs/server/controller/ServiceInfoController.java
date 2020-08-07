package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:在线客服接口
 * @author: liyuan
 * @data 2020-08-07 16:22
 */
@RestController
@RequestMapping("v1.0")
public class ServiceInfoController {
    @Autowired
    private IServiceInfoService serviceInfoService;
    @PostMapping("getServiceInfo")
    public ResultVO getServiceInfo(){
        return serviceInfoService.query();
    }
}
