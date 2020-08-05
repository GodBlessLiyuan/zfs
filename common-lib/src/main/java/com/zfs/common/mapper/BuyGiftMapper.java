package com.zfs.common.mapper;

import com.zfs.common.bo.BuyGiftBO;
import com.zfs.common.pojo.BuyGiftPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * BuyGiftMapper继承基类
 */
@Mapper
public interface BuyGiftMapper extends BaseMapper<BuyGiftPO, Long> {
    List<BuyGiftBO> queryByUserId(Long ud);

    BuyGiftPO queryOrder(String orderNumber);
}