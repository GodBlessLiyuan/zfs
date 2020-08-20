package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.AppDTO;
import com.zfs.server.service.IAppService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:51
 * @description: 应用更新
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class AppController {
    @Autowired
    private IAppService appService;

    @PostMapping("check")
    public ResultVO check(@RequestBody AppDTO dto, HttpServletRequest req) {
//        if (!VerifyUtil.checkDeviceId(dto)) {
//            return ResultVO.varifyDevice();
//        }

        return appService.check(dto, req);
    }
}
