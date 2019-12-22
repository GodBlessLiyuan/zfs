package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.WxsupportMapper;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.WxsupportDTO;
import com.rpa.server.service.WxsupportService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.WxsupportVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 9:51 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class WxsupportServiceImpl implements WxsupportService {

    @Resource
    private WxsupportMapper wxSupportMapper;

    @Resource
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Override
    public ResultVO query(WxsupportDTO dto) {

        WxsupportVO vo;

        //Redis中的key
        String key = RedisKeyUtil.genSupportRedisKey(dto.getIndex());
        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vo = JSON.parseObject(cache.getCacheByKey(key), WxsupportVO.class);
        } else {
            vo = new WxsupportVO();
            int maxId = this.wxSupportMapper.queryMaxId();
            vo.setIndex(maxId);

            if (dto.getIndex() != maxId) {
                List<String> pkgs = this.wxSupportMapper.queryPkgs();
                vo.setPkgs(pkgs);
            }

            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vo ,24, TimeUnit.HOURS);
        }

        return new ResultVO<>(1000, vo);
    }
}
