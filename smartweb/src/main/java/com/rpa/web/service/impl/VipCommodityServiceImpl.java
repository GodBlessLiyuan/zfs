package com.rpa.web.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.VipCommodityDTO;
import com.rpa.web.mapper.ComTypeMapper;
import com.rpa.web.mapper.VipCommodityMapper;
import com.rpa.web.pojo.ComTypePO;
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
    private VipCommodityMapper vipCommodityMapper;

    @Resource
    private ComTypeMapper comTypeMapper;

    @Override
    public DTPageInfo<VipCommodityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {

        Page<VipCommodityPO> page = PageHelper.startPage(pageNum, pageSize);
        List<VipCommodityPO> data = vipCommodityMapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), VipCommodityDTO.convert(data));
    }

    @Override
    public void insert(int channelId, int comTypeId, String comName, String description, int price, String showDiscount, float discount, int aId) {

        // 查询产品信息数据
        ComTypePO comTypePO = comTypeMapper.selectByPrimaryKey(comTypeId);
        VipCommodityPO vipCommodityPO = new VipCommodityPO();

        vipCommodityPO.setaId(aId);
        vipCommodityPO.setSoftChannelId(channelId);
        vipCommodityPO.setComTypeId(comTypeId);
        vipCommodityPO.setComTypeName(comTypePO.getName());
        vipCommodityPO.setDays(comTypePO.getDays());
        vipCommodityPO.setComName(comName);
        vipCommodityPO.setDescription(description);
        vipCommodityPO.setPrice(price);
        vipCommodityPO.setShowDiscount(showDiscount);
        vipCommodityPO.setDiscount(discount);
        vipCommodityPO.setCreateTime(new Date());
        vipCommodityPO.setStatus((byte) 1);
        vipCommodityPO.setIstop((byte) 1);

        vipCommodityPO.setaId(1);
        vipCommodityPO.setViptypeId(1);
        vipCommodityPO.setSoftChannelId(1);

        vipCommodityMapper.insert(vipCommodityPO);
    }

    @Override
    public VipCommodityDTO queryById(int cmdyId) {
        return VipCommodityDTO.convert(vipCommodityMapper.selectByPrimaryKey(cmdyId));
    }

    @Override
    public int update(int cmdyId, String comName, String description, int price, String showDiscount, float discount) {
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(cmdyId);

        vipCommodityPO.setComName(comName);
        vipCommodityPO.setDescription(description);
        vipCommodityPO.setPrice(price);
        vipCommodityPO.setShowDiscount(showDiscount);
        vipCommodityPO.setDiscount(discount);
        vipCommodityPO.setUpdateTime(new Date());

        return vipCommodityMapper.updateByPrimaryKey(vipCommodityPO);
    }

    @Override
    public int updateStatus(int cmdyId, byte status) {
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(cmdyId);

        vipCommodityPO.setStatus(status);

        return vipCommodityMapper.updateByPrimaryKey(vipCommodityPO);
    }

    @Override
    public int updateIsTop(int cmdyId, byte isTop) {
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(cmdyId);

        vipCommodityPO.setIstop(isTop);

        return vipCommodityMapper.updateByPrimaryKey(vipCommodityPO);
    }
}
