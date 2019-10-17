package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.PluginDTO;
import com.rpa.server.service.IPluginService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:26
 * @description: 插件更新
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class PluginController {

    @Autowired
    private IPluginService pluginService;

    @PostMapping("checkplugin")
    public ResultVO checkPlugin(@RequestBody PluginDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto.getId(), dto.getVerify())) {
            return new ResultVO(2000);
        }

        return pluginService.check(dto);
    }
}
