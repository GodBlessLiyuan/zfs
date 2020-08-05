package com.rpa.web.service;

import com.rpa.web.vo.VipCommodityVO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:52
 * @description: 商品列表接口
 * @version: 1.0
 */
public interface IVipCommodityService {

    /**
     * 查询
     *
     * @param draw     draw
     * @param pageNum  页面下标
     * @param pageSize 页面大小
     * @param reqData  请求数据
     * @return
     */
    DTPageInfo<VipCommodityVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 根据主键查询数据
     *
     * @param cmdyId 主键
     * @return
     */
    VipCommodityVO queryById(int cmdyId);

    /**
     * 新增
     *
     * @param channelId    销售渠道Id
     * @param comTypeId    产品类型Id
     * @param comName      商品名称
     * @param description  商品描述
     * @param price        原价
     * @param showDiscount 折扣
     * @param discount     售价
     * @param aId          管理员Id
     * @return
     */
    ResultVO insert(int commAttr,int channelId, int comTypeId, String comName, String description, String price, String showDiscount, float discount, int aId);

    /**
     * 更新
     *
     * @param cmdyId       主键
     * @param comName      商品名称
     * @param description  商品描述
     * @param price        原价
     * @param showDiscount 折扣
     * @param discount     售价
     * @return
     */
    int update(int cmdyId, String comName, String description, String price, String showDiscount, float discount);

    /**
     * 更新是否上架
     *
     * @param cmdyId
     * @param status
     * @return
     */
    int updateStatus(int cmdyId, byte status);

    /**
     * 更新是否置顶
     *
     * @param cmdyId
     * @param isTop
     * @return
     */
    int updateIsTop(int cmdyId, byte isTop);
}
