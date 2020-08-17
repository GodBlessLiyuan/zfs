package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.PluginMapper;
import com.zfs.common.pojo.PluginPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.PluginDTO;
import com.zfs.server.service.IPluginService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.PluginVO;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${file.uploadFolder}")
    private String publicPath;
    @Override
    public ResultVO check(PluginDTO dto) {
        String redisKey = RedisKeyUtil.genPluginRedisKey(dto.getPluginv(),dto.getPluginpkg());
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            PluginVO vo = JSON.parseObject(redisValue, PluginVO.class);
            if(null == vo) {
                return new ResultVO(1008);
            }
            return new ResultVO<>(1009, vo);
        }

        PluginPO pluginPO = pluginMapper.queryMaxByPluId(dto.getPluginv(),dto.getPluginpkg());
        if(pluginPO == null) {
            cache.setCache(redisKey, null, 1, TimeUnit.DAYS);
            return new ResultVO(1008);
        }

        PluginVO vo = new PluginVO();
        vo.setPluginv(pluginPO.getPluginId());
        vo.setUrl(publicPath+pluginPO.getUrl());
        vo.setMd5(pluginPO.getMd5());

        cache.setCache(redisKey, vo, 1, TimeUnit.DAYS);
        return new ResultVO<>(1009, vo);
    }
}
