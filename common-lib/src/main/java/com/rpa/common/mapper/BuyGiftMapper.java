package com.rpa.common.mapper;

import com.rpa.common.bo.BuyGiftBO;
import com.rpa.common.pojo.BuyGiftPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * BuyGiftMapper继承基类
 */
@Mapper
public interface BuyGiftMapper extends BaseMapper<BuyGiftPO, Long> {
    List<BuyGiftBO> queryByUserId(Long ud);
}