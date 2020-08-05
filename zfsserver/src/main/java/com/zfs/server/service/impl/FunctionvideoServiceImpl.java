package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.FunctionvideoMapper;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.FunctionvideoDTO;
import com.zfs.server.service.FunctionvideoService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.FunctionvideoVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 14:39 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class FunctionvideoServiceImpl implements FunctionvideoService {

    @Resource
    private FunctionvideoMapper functionVideoMapper;

    @Resource
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO query(FunctionvideoDTO dto) {

        FunctionvideoVO vo;

        //Redis中的key
        String key = RedisKeyUtil.genFunctionvideoRedisKey(dto.getFunction());

        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vo = JSON.parseObject(cache.getCacheByKey(key), FunctionvideoVO.class);
        } else {
            String url = this.functionVideoMapper.queryUrl(dto.getFunction());
            vo = new FunctionvideoVO();
            if (null == url) {
                vo.setUrl(url);
            } else {
                vo.setUrl(publicPath + url);
            }

            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vo ,24, TimeUnit.HOURS);
        }
        return new ResultVO(1000, vo);
    }
}
