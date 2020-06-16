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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Value("${dkfs.config.gray}")
    private boolean gray;

    @Override
    public ResultVO check(AvatarDTO dto) {
        int status = this.gray || cache.checkWhiteDeviceByDevId(dto.getId()) ? 0 : 2;
        String redisKey = RedisKeyUtil.genAvatarRedisKey(dto.getSoftv(), dto.getChannel(), dto.getOs_version(),dto.getAvatarv(), status);
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
        AvatarPO avatarPO = avatarMapper.queryMaxByVerId(dto.getSoftv(), chanId, dto.getAvatarv(), dto.getOs_version(),status);
        if (null == avatarPO) {
            cache.setCache(redisKey, null, 1, TimeUnit.DAYS);
            // 最新版本
            return new ResultVO(1008);
        }

        // 需要更新
        AvatarVO vo = new AvatarVO();
        vo.setType(avatarPO.getUpdateType());
        vo.setId(avatarPO.getAvatarId());
        vo.setSoftv(avatarPO.getVersionCode());

        cache.setCache(redisKey, vo, 1, TimeUnit.DAYS);

        return new ResultVO<>(1009, vo);
    }
}
