package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.constant.CommonConstant;
import com.rpa.server.constant.ConfigConstant;
import com.rpa.server.mapper.AdConfigMapper;
import com.rpa.server.mapper.KeyValueMapper;
import com.rpa.server.pojo.AdConfigPO;
import com.rpa.server.service.AdconfigServcie;
import com.rpa.server.vo.AdconfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResultVO query() {

        // 查询广告展现策略
        String strategy = this.keyValueMapper.queryValue(ConfigConstant.SHOW_INTERVAL);

        // 查询开启状态的广告配置信息
        List<AdConfigPO> POs = this.adConfigMapper.query();
        if (null == POs) {
            return new ResultVO(1000, "");
        }

        AdconfigVO vo = new AdconfigVO();
        List<Map<String, Object>> lists = new ArrayList<>();
        for (AdConfigPO po : POs) {
            Map<String, Object> map = new HashMap<>();
            map.put("adid", po.getAdId());
            map.put("sort", po.getPriority());
            map.put("total", po.getTotal());
            map.put("number", po.getAdNumber());

            lists.add(map);
        }

        vo.setStrategy(strategy);
        vo.setAds(lists);
        return new ResultVO(1000, vo);
    }
}
