package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.BannerconfigMapper;
import com.rpa.common.pojo.BannerconfigPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BannerConfigDTO;
import com.rpa.server.service.IBannerConfigService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.BannerConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:10
 * @description: banner广告信息
 * @version: 1.0
 */
@Service
public class BannerConfigServiceImpl implements IBannerConfigService {
    @Resource
    private BannerconfigMapper bannerConfigMapper;

    @Autowired
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO queryBanConf(BannerConfigDTO dto) {

        //准备要返回数据的对象
        List<BannerConfigVO> vos;

        //Redis中的key
        String key = RedisKeyUtil.genBannerconfigRedisKey();

        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vos = JSON.parseObject(cache.getCacheByKey(key), List.class);
        } else {
            List<BannerconfigPO> bannerConfigPOs = bannerConfigMapper.queryAll();
            if (bannerConfigPOs == null || bannerConfigPOs.size() == 0) {
                return new ResultVO(1000);
            }

            vos = new ArrayList<>();
            for (BannerconfigPO po : bannerConfigPOs) {
                BannerConfigVO vo = new BannerConfigVO();
                vo.setAdid(po.getBannerId());
                if (po.getUrl() != null && !"".equals(po.getUrl())) {
                    vo.setLink(po.getUrl());
                }
                if (po.getPicPath() != null && !"".equals(po.getPicPath())) {
                    vo.setPicpath(publicPath + po.getPicPath());
                }
                vos.add(vo);
            }
            //将对象用JSON序列化，存入Redis
            cache.setCache(key, vos ,24, TimeUnit.HOURS);
        }

        return new ResultVO<>(1000, vos);
    }
}
