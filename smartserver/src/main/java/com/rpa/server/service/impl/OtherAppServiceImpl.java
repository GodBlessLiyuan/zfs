package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OtherAppDTO;
import com.rpa.server.mapper.OtherAppMapper;
import com.rpa.server.pojo.OtherAppPO;
import com.rpa.server.service.IOtherAppService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.OtherAppVO;
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
        String redisKey = "smarthelper_otherapp_" + dto.getChannel();
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
