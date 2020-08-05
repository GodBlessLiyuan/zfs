package com.zfs.common.mapper;

import com.zfs.common.bo.OrderBO;
import com.zfs.common.pojo.OrderPO;
import com.zfs.common.pojo.OrderPOKey;
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
    List<OrderBO> queryByUserId(Long userId);

    int queryPayCount();

    Long queryDayRevenue();

    Long queryMonthRevenue();
}