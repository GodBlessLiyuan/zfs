package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.AdChannelMapper;
import com.zfs.common.mapper.AdconfigMapper;
import com.zfs.common.mapper.AppMapper;
import com.zfs.common.mapper.KeyValueMapper;
import com.zfs.common.pojo.AdconfigPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.constant.ConfigConstant;
import com.zfs.server.dto.AdconfigDTO;
import com.zfs.server.service.AdconfigServcie;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.AdconfigVO;
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

    @Resource
    private KeyValueMapper keyValueMapper;

    @Resource
    private AdconfigMapper adConfigMapper;

    @Resource
    private AppMapper appMapper;

    @Resource
    private AdChannelMapper adChannelMapper;

    @Resource
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Override
    public ResultVO query(AdconfigDTO dto) {

        //准备好要返回的数据对象
        AdconfigVO vo;

        //Redis中的key
        String key = RedisKeyUtil.genAdconfigRedisKey(dto.getChannel(), dto.getSoftv());

        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vo = JSON.parseObject(cache.getCacheByKey(key), AdconfigVO.class);
        }else {
            //从t_key_value表中查询广告展现策略
            String strategy = this.keyValueMapper.queryValue(ConfigConstant.SHOW_INTERVAL);
            //从t_soft_channel中查询ID
            Integer softChannelId = cache.getSoftChannelId(dto.getChannel());
            //从t_app中查询ID
            Integer appId = this.appMapper.queryIdByVersioncode(dto.getSoftv());

            //从t_ad_channel表中查询广告IDs
            List<Integer> adIds = null;
            if (0 != softChannelId && null != appId) {
                adIds = this.adChannelMapper.queryAdIds(softChannelId, appId);
            }

            //从t_adconfig表中查询广告数据，供开屏广告展现使用
            List<AdconfigPO> pos = null;
            if (null != adIds && adIds.size() != 0) {
                pos = this.adConfigMapper.queryById(adIds);
            }

            //将所查数据放入集合
            List<Map<String, Object>> lists = new ArrayList<>();
            if (null == pos) {
                lists = null;
            } else {
                for (AdconfigPO po : pos) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("adid", po.getAdId());
                    map.put("sort", po.getPriority());
                    map.put("total", po.getTotal());
                    map.put("number", po.getAdNumber());
                    lists.add(map);
                }
            }

            //将集合数据封装为对象
            vo = new AdconfigVO();
            vo.setStrategy(strategy);
            vo.setAds(lists);

            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vo ,24, TimeUnit.HOURS);
        }
        return new ResultVO(1000, vo);
    }
}
