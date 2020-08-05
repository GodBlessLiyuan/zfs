package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.VipcommodityMapper;
import com.zfs.common.pojo.VipcommodityPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.constant.CommonConstant;
import com.zfs.server.dto.VipCommodityDTO;
import com.zfs.server.service.IVipCommodityService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.VipCommodityVO;
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
    public ResultVO getCommodity(VipCommodityDTO dto, int version) {
        String redisKey = RedisKeyUtil.genVipCommodityRedisKey(dto.getSoftv(), dto.getChannel(),String.valueOf(version));
        String redisValue = cache.getCacheByKey(redisKey);
        if (null != redisValue) {
            List<VipCommodityVO> vos = JSON.parseObject(redisValue, List.class);
            if (null == vos) {
                return new ResultVO(1000);
            }
            return new ResultVO<>(1000, vos);
        }

        List<VipcommodityPO> vcPOs = vipCommodityMapper.queryByChanId(CommonConstant.CHAN_DEF);
        Map<String, VipcommodityPO> vcMap = new HashMap<>(vcPOs.size());
        for (VipcommodityPO po : vcPOs) {
            vcMap.put(po.getComTypeId()+"_"+po.getCommAttr()+"_"+po.getSoftChannelId(), po);
        }

        List<VipcommodityPO> pos = vipCommodityMapper.queryByChanId(cache.getSoftChannelId(dto.getChannel()));
        for (VipcommodityPO po : pos) {
            vcMap.put(po.getComTypeId()+"_"+po.getCommAttr()+"_"+po.getSoftChannelId(), po);
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
            //独立商品
            vo.setIstop((byte) (po.getIstop()|1));
            //通用商品的判断删除了
            vos.add(vo);

        }

        cache.setCache(redisKey, vos, 1, TimeUnit.DAYS);
        return new ResultVO<>(1000, vos);
    }
}
