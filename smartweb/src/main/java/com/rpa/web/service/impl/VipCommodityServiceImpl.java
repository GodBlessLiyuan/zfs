package com.rpa.web.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.VipCommodityDTO;
import com.rpa.web.mapper.VipCommodityMapper;
import com.rpa.web.pojo.VipCommodityPO;
import com.rpa.web.service.IVipCommodityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 12:03
 * @description: TODO
 * @version: 1.0
 */
@Service
public class VipCommodityServiceImpl implements IVipCommodityService {

    @Resource
    private VipCommodityMapper mapper;

    @Override
    public void update(VipCommodityPO po) {
        mapper.updateByPrimaryKey(po);
    }

    @Override
    public DTPageInfo<VipCommodityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {

        Page<VipCommodityPO> page = PageHelper.startPage(pageNum, pageSize);
        List<VipCommodityPO> data = mapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), VipCommodityDTO.convert(data));
    }

    @Override
    public void insert(String channelName, String comTypeName, String comName, String description, int price, String showDiscount, float discount, int aId) {
        VipCommodityPO po = new VipCommodityPO();

        po.setaId(aId);
        po.setName(channelName);
        po.setComTypeName(comTypeName);
        po.setComName(comName);
        po.setDescription(description);
        po.setPrice(price);
        po.setShowDiscount(showDiscount);
        po.setDiscount(discount);
        po.setCreateTime(new Date());
        po.setStatus((byte) 1);
        po.setIstop((byte) 1);

        po.setaId(1);
        po.setViptypeId(1);
        po.setComTypeId(1);
        po.setSoftChannelId(1);

        mapper.insert(po);
    }
}
