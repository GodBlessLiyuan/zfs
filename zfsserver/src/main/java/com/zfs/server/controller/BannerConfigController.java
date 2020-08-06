package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.BannerConfigDTO;
import com.zfs.server.service.IBannerConfigService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:02
 * @description: banner广告信息
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class BannerConfigController {
    @Autowired
    private IBannerConfigService service;

    @PostMapping("bannerconfig")
    public ResultVO bannerConfig(@RequestBody BannerConfigDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.varifyDevice();
        }

        return service.queryBanConf(dto);
    }
}
