package com.rpa.common.mapper;

import com.rpa.common.bo.OrderBO;
import com.rpa.common.pojo.OrderPO;
import com.rpa.common.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
    /**
     * 根据userId查询数据
     *
     * @param userId
     * @return
     */
    List<OrderBO> queryByUserId(int userId);

    int queryPayCount();

    float queryDayRevenue();

    float queryMonthRevenue();
}