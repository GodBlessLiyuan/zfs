package com.rpa.server.mapper;

import com.rpa.common.pojo.OrderPO;
import com.rpa.common.pojo.OrderPOKey;
import com.rpa.server.bo.OrderBO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
    /**
     * 查询UserId的购买信息
     *
     * @param userId
     * @return
     */
    List<OrderBO> queryByUserId(Long userId);
}