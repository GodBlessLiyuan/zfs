package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.constant.CommonConstant;
import com.rpa.server.dto.VipCommodityDTO;
import com.rpa.server.mapper.VipCommodityMapper;
import com.rpa.server.pojo.VipCommodityPO;
import com.rpa.server.service.IVipCommodityService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.VipCommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 15:39
 * @description: 商品列表
 * @version: 1.0
 */
@Service
public class VipCommodityServiceImpl implements IVipCommodityService {
    @Resource
    private VipCommodityMapper vipCommodityMapper;
    @Autowired
    private RedisCacheUtil cache;

    @Override
    public ResultVO getCommodity(VipCommodityDTO dto) {
        List<VipCommodityPO> vcPOs = vipCommodityMapper.queryByChanId(CommonConstant.CHAN_DEF);
        Map<Integer, VipCommodityPO> vcMap = new HashMap<>(vcPOs.size());
        for (VipCommodityPO po : vcPOs) {
            vcMap.put(po.getComTypeId(), po);
        }

        List<VipCommodityPO> pos = vipCommodityMapper.queryByChanId(cache.getSoftChannelId(dto.getChannel()));
        for (VipCommodityPO po : pos) {
            vcMap.put(po.getComTypeId(), po);
        }
        if (vcMap.size() == 0) {
            return new ResultVO(1000);
        }

        List<VipCommodityVO> vos = new ArrayList<>();
        for (VipCommodityPO po : vcMap.values()) {
            VipCommodityVO vo = new VipCommodityVO();
            vo.setCmdid(po.getCmdyId());
            vo.setDescription(po.getDescription());
            vo.setPrice(po.getPrice());
            vo.setDiscount(po.getDiscount());
            vo.setShowdiscount(po.getShowDiscount());
            vo.setTypename(po.getComTypeName());
            vo.setDays(po.getDays());
            vo.setIstop(po.getIstop());
            vos.add(vo);
        }

        return new ResultVO<>(1000, vos);
    }
}
