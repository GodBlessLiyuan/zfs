package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.OtherAppMapper;
import com.zfs.common.pojo.OtherAppPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.OtherAppDTO;
import com.zfs.server.service.IOtherAppService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.OtherAppVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:15
 * @description: 其他应用
 * @version: 1.0
 */
@Service
public class OtherAppServiceImpl implements IOtherAppService {
    @Resource
    private OtherAppMapper otherAppMapper;
    @Autowired
    private RedisCacheUtil cache;

    @Value("${file.publicPath}")
    private String filePublicPath;

    @Override
    public ResultVO query(OtherAppDTO dto) {
        String redisKey = RedisKeyUtil.genOtherAppRedisKey(dto.getChannel());
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            List<OtherAppVO> vos = JSON.parseObject(redisValue, List.class);
            return new ResultVO<>(1000, vos);
        }

        List<OtherAppPO> pos = otherAppMapper.queryAll();
        List<OtherAppVO> vos = new ArrayList<>();
        for (OtherAppPO po : pos) {
            OtherAppVO vo = new OtherAppVO();
            vo.setName(po.getoName());
            vo.setIconurl(filePublicPath + po.getIconUrl());
            vo.setType(po.getDownloadType());
            if (po.getDownloadType() == 1) {
                vo.setAppurl(filePublicPath + po.getAppUrl());
            } else {
                vo.setAppurl(po.getAppUrl());
            }
            vo.setExtra(po.getExtra());
            vos.add(vo);
        }

        cache.setCache(redisKey, vos, 1, TimeUnit.DAYS);
        return new ResultVO<>(1000, vos);
    }
}
