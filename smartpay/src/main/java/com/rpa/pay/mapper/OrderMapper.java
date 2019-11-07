package com.rpa.pay.mapper;

import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
}