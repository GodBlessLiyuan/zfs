package com.zfs.server.service.impl;

import com.zfs.common.mapper.PhoneBrandMapper;
import com.zfs.common.pojo.PhoneBrandPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IPhoneBrandService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.utils.StringToObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-17 16:06
 */
@Service
public class PhoneBrandServiceImpl implements IPhoneBrandService {
    @Autowired
    private PhoneBrandMapper phoneBrandMapper;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Override
    public ResultVO phoneBrand() {
        String key = RedisKeyUtil.genRedisKey("phoneBrand", "all");
        List<PhoneBrandPO> list =StringToObjUtil.strToObj(redisCacheUtil.getCacheByKey(key),List.class);
        if(list==null){
            list = phoneBrandMapper.queryAll();
            redisCacheUtil.setCache(key,list,1, TimeUnit.HOURS);
        }
        return new ResultVO(1000,list);
    }
}
