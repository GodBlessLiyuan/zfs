package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.ShareActivityMapper;
import com.rpa.common.pojo.ShareActivityPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ShareactivityDTO;
import com.rpa.server.service.ShareactivityService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.ShareactivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 11:20 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class ShareacticityServiceImpl implements ShareactivityService {

    @Autowired
    private ShareActivityMapper shareActivityMapper;

    @Autowired
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO query(ShareactivityDTO dto) {

        List<ShareactivityVO> vos;

        //Redis中的key
        String key = RedisKeyUtil.genShareRedisKey();
        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vos = JSON.parseObject(cache.getCacheByKey(key), List.class);
        } else {
            vos = new ArrayList<>();
            List<ShareActivityPO> pos = this.shareActivityMapper.queryAll();
            for (ShareActivityPO po : pos) {
                ShareactivityVO vo = new ShareactivityVO();
                vo.setMid(po.getMaterialId());
                vo.setType(po.getType().intValue());
                if (po.getType() == 1) {
                    vo.setContent(po.getContent());
                } else {
                    vo.setContent(publicPath + po.getContent());
                }
                vos.add(vo);
            }

            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vos ,24, TimeUnit.HOURS);
        }

        return new ResultVO(1000, vos);
    }
}
