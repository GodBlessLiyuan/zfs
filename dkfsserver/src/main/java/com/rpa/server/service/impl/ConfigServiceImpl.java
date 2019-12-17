package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.KeyValueMapper;
import com.rpa.common.pojo.KeyValuePO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.ConfigConstant;
import com.rpa.server.dto.ConfigDTO;
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
        if (null != vo && dto.getIndex().toString().equals(vo.getIndex())) {
            return new ResultVO<>(1019);
        }
        if (null != vo) {
            return new ResultVO<>(1000, vo);
        }

        vo = new ConfigVO();
        KeyValuePO po = keyValueMapper.selectByPrimaryKey(ConfigConstant.INDEX);
        if (null != po) {
            vo.setIndex(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.TRAIN);
        if (null != po) {
            vo.setTrain(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.QQ);
        if (null != po) {
            vo.setQq(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.WX);
        if (null != po) {
            vo.setWx(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.SHARE_URL);
        if (null != po) {
            vo.setShareurl(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.PROBLEM_URL);
        if (null != po) {
            vo.setProblemurl(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.FREE_VIP);
        if (null != po) {
            vo.setFreevip(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.PROTOCOL);
        if (null != po) {
            vo.setProtocol(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.JOIN_QQ_CODE);
        if (null != po) {
            vo.setJoinqqcode(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.VIP_PROTOCOL);
        if (null != po) {
            vo.setVipprotocol(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.MEMBER_RIGHTS);
        if (null != po) {
            vo.setMemberrights(po.getValue());
        }
        po = keyValueMapper.selectByPrimaryKey(ConfigConstant.CLEAR_FANS);
        if (null != po) {
            vo.setClearfans(po.getValue());
        }
        
        cacheUtil.setCache(ConfigConstant.REDIS_KEY, vo, 1, TimeUnit.HOURS);

        return new ResultVO<>(1000, vo);
    }
}
