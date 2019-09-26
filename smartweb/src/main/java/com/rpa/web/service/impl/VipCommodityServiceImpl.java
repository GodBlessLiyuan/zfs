package com.rpa.web.service.impl;


import com.rpa.web.mapper.VipCommodityMapper;
import com.rpa.web.pojo.VipCommodityPO;
import com.rpa.web.service.IVipCommodityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 12:03
 * @description: TODO
 * @version: 1.0
 */
public class VipCommodityServiceImpl implements IVipCommodityService {

    @Autowired
    private VipCommodityMapper mapper;

    @Override
    public void insert(VipCommodityPO po) {
        mapper.insert(po);
    }

    @Override
    public void update(VipCommodityPO po) {
        mapper.updateByPrimaryKey(po);
    }

    @Override
    public List<VipCommodityPO> query(Map<String, Object> map) {
        return mapper.query(map);
    }
}
