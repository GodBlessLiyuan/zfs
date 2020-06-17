package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.VipcommodityMapper;
import com.rpa.common.pojo.VipcommodityPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.CommonConstant;
import com.rpa.server.dto.VipCommodityDTO;
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
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 15:39
 * @description: 商品列表
 * @version: 1.0
 */
@Service
public class VipCommodityServiceImpl implements IVipCommodityService {
    @Resource
    private VipcommodityMapper vipCommodityMapper;
    @Autowired
    private RedisCacheUtil cache;

    @Override
    public ResultVO getCommodity(VipCommodityDTO dto) {
        String redisKey = RedisKeyUtil.genVipCommodityRedisKey(dto.getSoftv(), dto.getChannel());
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            List<VipCommodityVO> vos = JSON.parseObject(redisValue, List.class);
            if (null == vos) {
                return new ResultVO(1000);
            }
            return new ResultVO<>(1000, vos);
        }

        List<VipcommodityPO> vcPOs = vipCommodityMapper.queryByChanId(CommonConstant.CHAN_DEF);
        Map<Integer, VipcommodityPO> vcMap = new HashMap<>(vcPOs.size());
        for (VipcommodityPO po : vcPOs) {
            vcMap.put(po.getComTypeId(), po);
        }

        List<VipcommodityPO> pos = vipCommodityMapper.queryByChanId(cache.getSoftChannelId(dto.getChannel()));
        for (VipcommodityPO po : pos) {
            vcMap.put(po.getComTypeId(), po);
        }
        if (vcMap.size() == 0) {
            cache.setCache(redisKey, null, 1, TimeUnit.DAYS);
            return new ResultVO(1000);
        }

        List<VipCommodityVO> vos = new ArrayList<>();
        for (VipcommodityPO po : vcMap.values()) {
            VipCommodityVO vo = new VipCommodityVO();
            vo.setCmdid(po.getCmdyId());
            vo.setDescription(po.getDescription());
            vo.setPrice(po.getPrice());
            if (null != po.getDiscount()) {
                vo.setDiscount(po.getDiscount() / 100F);
            }
            vo.setShowdiscount(po.getShowDiscount());
            vo.setTypename(po.getComName());
            vo.setDays(po.getDays());
            /**
             *  置顶值为4，通用值为2，普通值为1
             * 置顶：
             * */
            if(po.getIstop()==2){//置顶
                po.setIstop((byte) 4);
            }else {
                po.setIstop((byte) 0);
            }
            //通用商品
            if(po.getCommAttr()==2){
                vo.setIstop((byte) (po.getIstop()+ 2));
            }
            //独立商品
            else{
                vo.setIstop((byte) (po.getIstop()+1));
            }
            vos.add(vo);
        }

        cache.setCache(redisKey, vos, 1, TimeUnit.DAYS);
        return new ResultVO<>(1000, vos);
    }
}
