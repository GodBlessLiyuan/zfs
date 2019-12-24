package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.AvatarMapper;
import com.rpa.common.pojo.AvatarPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.AvatarDTO;
import com.rpa.server.service.IAvatarService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.AvatarVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/12/24 15:01
 * @description: 分身应用
 * @version: 1.0
 */
@Service
public class AvatarServiceImpl implements IAvatarService {
    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private RedisCacheUtil cache;
    @Override
    public ResultVO check(AvatarDTO dto, HttpServletRequest req) {
        String redisKey = RedisKeyUtil.genAppRedisKey(dto.getSoftv(), dto.getChannel(), dto.getAvatarv());
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            AvatarVO vo = JSON.parseObject(redisValue, AvatarVO.class);
            if (null == vo) {
                return new ResultVO(1008);
            }
            return new ResultVO<>(1009, vo);
        }

        // 从Redis中取出设备白名单
        int chanId = cache.getSoftChannelId(dto.getChannel());
//        AvatarPO appPO = avatarMapper.queryMaxByVerId(dto.getSoftv(), chanId, dto.getAvatarv());
//        if (null == appPO) {
//            cache.setCache(redisKey, null, 1, TimeUnit.DAYS);
//            // 最新版本
//            return new ResultVO(1008);
//        }

        // 需要更新
        AvatarVO vo = new AvatarVO();
//        vo.setType(appPO.getUpdateType());

        cache.setCache(redisKey, vo, 1, TimeUnit.DAYS);

        return new ResultVO<>(1009, vo);
    }
}
