package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.BannerConfigDTO;
import com.rpa.server.service.IBannerConfigService;
import com.rpa.server.utils.VerifyUtil;
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
        if (!VerifyUtil.checkDeviceId(dto.getId(), dto.getVerify())) {
            return new ResultVO(2000);
        }

        return service.queryBanConf(dto);
    }
}
