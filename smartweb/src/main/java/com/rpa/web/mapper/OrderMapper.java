package com.rpa.web.mapper;

import com.rpa.web.pojo.OrderPO;
import com.rpa.web.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseDAO<OrderPO, OrderPOKey> {
}