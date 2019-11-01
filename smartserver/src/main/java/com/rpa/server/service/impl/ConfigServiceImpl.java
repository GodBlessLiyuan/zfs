package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.server.common.ResultVO;
import com.rpa.server.constant.ConfigConstant;
import com.rpa.server.dto.ConfigDTO;
import com.rpa.server.mapper.KeyValueMapper;
import com.rpa.server.pojo.KeyValuePO;
import com.rpa.server.service.IConfigService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.ConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:35
 * @description: 获取砖助智能助手的基础信息
 * @version: 1.0
 */
@Service
public class ConfigServiceImpl implements IConfigService {
    @Resource
    private KeyValueMapper keyValueMapper;
    @Autowired
    private RedisCacheUtil cacheUtil;

    @Override
    public ResultVO queryConfigInfo(ConfigDTO dto) {
        ConfigVO vo = JSON.parseObject(cacheUtil.getCacheByKey(ConfigConstant.REDIS_KEY), ConfigVO.class);
        if (vo != null && dto.getIndex().toString().equals(vo.getIndex())) {
            return new ResultVO(1019);
        }

        vo = new ConfigVO();
        KeyValuePO po = keyValueMapper.selectByPrimaryKey(ConfigConstant.INDEX);
        if (po != null) {
            vo.setIndex(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.TRAIN);
        if (po != null) {
            vo.setTrain(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.QQ);
        if (po != null) {
            vo.setQq(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.WX);
        if (po != null) {
            vo.setWx(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.SHARE_URL);
        if (po != null) {
            vo.setShareurl(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.PROBLEM_URL);
        if (po != null) {
            vo.setProblemurl(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.FREE_VIP);
        if (po != null) {
            vo.setFreevip(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.PROTOCOL);
        if (po != null) {
            vo.setProtocol(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.JOIN_QQ_CODE);
        if (po != null) {
            vo.setJoinqqcode(po.getValue());
        }

        cacheUtil.setCache(ConfigConstant.REDIS_KEY, vo, 1, TimeUnit.DAYS);

        return new ResultVO<>(1000, vo);
    }
}
