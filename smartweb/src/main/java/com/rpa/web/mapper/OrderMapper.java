package com.rpa.web.mapper;

import com.rpa.web.domain.OrderDO;
import com.rpa.web.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseDAO<OrderDO, OrderPOKey> {
    /**
     * 根据userId查询数据
     *
     * @param userId
     * @return
     */
    List<OrderDO> queryByUserId(int userId);

    int queryPayCount();

    Float queryDayRevenue();

    Float queryMonthRevenue();
}