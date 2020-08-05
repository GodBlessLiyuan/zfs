package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.VipcommodityBO;
import com.zfs.common.mapper.ComTypeMapper;
import com.zfs.common.mapper.VipcommodityMapper;
import com.zfs.common.pojo.VipcommodityPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.web.common.VipCommodityConstant;
import com.zfs.web.vo.VipCommodityVO;
import com.zfs.common.mapper.SoftChannelMapper;
import com.zfs.common.pojo.SoftChannelPO;
import com.zfs.web.service.IVipCommodityService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.zfs.common.pojo.ComTypePO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 12:03
 * @description: 商品列表
 * @version: 1.0
 */
@Service
public class VipCommodityServiceImpl implements IVipCommodityService {
    private final static Logger logger = LoggerFactory.getLogger(VipCommodityServiceImpl.class);

    @Resource
    private VipcommodityMapper vipcommodityMapper;
    @Resource
    private ComTypeMapper comTypeMapper;
    @Resource
    private SoftChannelMapper softChannelMapper;
    @Autowired
    private StringRedisTemplate template;

    @Override
    public DTPageInfo<VipCommodityVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {

        Page<VipcommodityBO> page = PageHelper.startPage(pageNum, pageSize);
        List<VipcommodityBO> data = vipcommodityMapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), VipCommodityVO.convert(data));
    }

    @Override
    public VipCommodityVO queryById(int cmdyId) {
        return VipCommodityVO.convert(vipcommodityMapper.selectByPrimaryKey(cmdyId));
    }

    @Override
    public ResultVO insert(int commAttr,int channelId, int comTypeId, String comName, String description, String price, String showDiscount,
                           float discount, int aId) {
        VipcommodityPO po = vipcommodityMapper.queryByChanIdAndComTypeId(channelId, comTypeId,commAttr);
        if (po != null) {
            // 当前渠道-产品已存在！
            return new ResultVO(1103);
        }

        VipcommodityPO vipCommodityPO = new VipcommodityPO();
        vipCommodityPO.setCommAttr((byte) commAttr);
        vipCommodityPO.setSoftChannelId(channelId);
        vipCommodityPO.setComTypeId(comTypeId);
        vipCommodityPO.setComName(comName);
        vipCommodityPO.setDescription(description);
        vipCommodityPO.setPrice(price);
        vipCommodityPO.setShowDiscount(showDiscount);
        vipCommodityPO.setDiscount((long) (100 * discount));
        vipCommodityPO.setaId(aId);

        // 查询产品信息数据
        ComTypePO comTypePO = comTypeMapper.selectByPrimaryKey(comTypeId);
        /**
         * 产品天数大于年会员规定天数则设置未年会员，否则设置未普通会员
         */
        int vipTypeId = comTypePO.getDays() >= VipCommodityConstant.YEAR_MEMBER_DAY ?
                VipCommodityConstant.YEAR_MEMBER_KEY : VipCommodityConstant.COMM_MEMBER_KEY;
        vipCommodityPO.setViptypeId(vipTypeId);
        vipCommodityPO.setComTypeName(comTypePO.getName());
        vipCommodityPO.setDays(comTypePO.getDays());

        // 查询渠道信息数据
        SoftChannelPO softChannelPO = softChannelMapper.selectByPrimaryKey(channelId);
        vipCommodityPO.setName(softChannelPO.getName());

        vipCommodityPO.setCreateTime(new Date());
        vipCommodityPO.setStatus((byte) 1);
        vipCommodityPO.setIstop((byte) 1);

        int result = vipcommodityMapper.insert(vipCommodityPO);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", vipCommodityPO);
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public int update(int cmdyId, String comName, String description, String price, String showDiscount, float discount) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setComName(comName);
        vipCommodityPO.setDescription(description);
        vipCommodityPO.setPrice(price);
        vipCommodityPO.setShowDiscount(showDiscount);
        BigDecimal bd = new BigDecimal(Float.toString(discount));
        vipCommodityPO.setDiscount(bd.multiply(new BigDecimal("100")).longValue());
        vipCommodityPO.setUpdateTime(new Date());
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "update", "更新失败", vipCommodityPO);
        }
        this.deleteRedis();
        return first;
    }

    @Override
    public int updateStatus(int cmdyId, byte status) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setStatus(status);
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "updateStatus", "更新失败", vipCommodityPO);
        }
        this.deleteRedis();
        return first;
    }

    @Override
    public int updateIsTop(int cmdyId, byte isTop) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setIstop(isTop);
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "updateIsTop", "更新失败", vipCommodityPO);
        }
        this.deleteRedis();
        return first;
    }

    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genVipCommodityRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
