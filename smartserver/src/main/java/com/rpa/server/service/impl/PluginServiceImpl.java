package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.PluginDTO;
import com.rpa.server.mapper.PluginMapper;
import com.rpa.server.pojo.PluginPO;
import com.rpa.server.service.IPluginService;
import com.rpa.server.vo.PluginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:37
 * @description: 插件更新
 * @version: 1.0
 */
@Service
public class PluginServiceImpl implements IPluginService {

    @Resource
    private PluginMapper pluginMapper;

    @Override
    public ResultVO check(PluginDTO dto) {
        PluginPO pluginPO = pluginMapper.queryMaxByPluId(dto.getPluginv());
        if(pluginPO == null) {
            return new ResultVO(1008);
        }

        PluginVO vo = new PluginVO();
        vo.setPluginv(pluginPO.getPluginId());
        vo.setUrl(pluginPO.getUrl());
        vo.setMd5(pluginPO.getMd5());
        return new ResultVO<>(1009, vo);
    }
}
