package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.WxsupportDTO;
import com.zfs.server.service.WxsupportService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 9:25 2019/10/29
 * @version: 1.0.0
 * @description: 支持辅助微信功能的应用
 */
@RequestMapping("v1.0")
@RestController
public class WxsupportController {

    @Autowired
    private WxsupportService wxsupportService;

    @PostMapping("support")
    public ResultVO query(@RequestBody WxsupportDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return this.wxsupportService.query(dto);
    }

}
