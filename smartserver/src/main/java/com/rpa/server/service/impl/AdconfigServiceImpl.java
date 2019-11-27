package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.server.common.ResultVO;
import com.rpa.server.constant.ConfigConstant;
import com.rpa.server.dto.AdconfigDTO;
import com.rpa.server.mapper.AdChannelMapper;
import com.rpa.server.mapper.AdConfigMapper;
import com.rpa.server.mapper.AppMapper;
import com.rpa.server.mapper.KeyValueMapper;
import com.rpa.server.pojo.AdConfigPO;
import com.rpa.server.service.AdconfigServcie;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.AdconfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 16:31 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class AdconfigServiceImpl implements AdconfigServcie {

    @Autowired
    private KeyValueMapper keyValueMapper;

    @Autowired
    private AdConfigMapper adConfigMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private AdChannelMapper adChannelMapper;

    @Autowired
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Override
    public ResultVO query(AdconfigDTO dto) {

        //准备好要返回的数据对象
        AdconfigVO vo;

        //Redis中的key
        String key ="smarthelper" + "adconfig" + dto.getChannel() + dto.getSoftv();

        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vo = JSON.parseObject(cache.getCacheByKey(key), AdconfigVO.class);
        }else {
            //从t_key_value表中查询广告展现策略
            String strategy = this.keyValueMapper.queryValue(ConfigConstant.SHOW_INTERVAL);
            //从t_soft_channel中查询ID
            int softChannelId = cache.getSoftChannelId(dto.getChannel());
            //从t_app中查询ID
            int appId = this.appMapper.queryIdByVersioncode(dto.getSoftv());
            //从t_ad_channel表中查询广告IDs
            List<Integer> adIds = this.adChannelMapper.queryAdIds(softChannelId, appId);

            //从t_adconfig表中查询广告数据，供开屏广告展现使用
            List<AdConfigPO> POs = this.adConfigMapper.query(adIds);
            if (null == POs) {
                return new ResultVO(1000, "");
            }

            List<Map<String, Object>> lists = new ArrayList<>();
            for (AdConfigPO po : POs) {
                Map<String, Object> map = new HashMap<>();
                map.put("adid", po.getAdId());
                map.put("sort", po.getPriority());
                map.put("total", po.getTotal());
                map.put("number", po.getAdNumber());
                lists.add(map);
            }
            vo = new AdconfigVO();
            vo.setStrategy(strategy);
            vo.setAds(lists);

            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vo ,24, TimeUnit.HOURS);
        }
        return new ResultVO(1000, vo);
    }
}
