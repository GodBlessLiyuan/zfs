package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.PluginDTO;
import com.rpa.server.mapper.PluginMapper;
import com.rpa.server.pojo.PluginPO;
import com.rpa.server.service.IPluginService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.PluginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisCacheUtil cache;

    @Override
    public ResultVO check(PluginDTO dto) {
        String redisKey = "smarthelper_plugin_" + dto.getPluginv();
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            PluginVO vo = JSON.parseObject(redisValue, PluginVO.class);
            if(null == vo) {
                return new ResultVO(1008);
            }
            return new ResultVO<>(1009, vo);
        }

        PluginPO pluginPO = pluginMapper.queryMaxByPluId(dto.getPluginv());
        if(pluginPO == null) {
            cache.setCache(redisKey, null, 1, TimeUnit.DAYS);
            return new ResultVO(1008);
        }

        PluginVO vo = new PluginVO();
        vo.setPluginv(pluginPO.getPluginId());
        vo.setUrl(pluginPO.getUrl());
        vo.setMd5(pluginPO.getMd5());

        cache.setCache(redisKey, vo, 1, TimeUnit.DAYS);
        return new ResultVO<>(1009, vo);
    }
}
