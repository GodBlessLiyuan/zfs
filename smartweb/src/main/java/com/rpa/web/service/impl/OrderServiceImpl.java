package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.OrderDO;
import com.rpa.web.dto.OrderDTO;
import com.rpa.web.mapper.OrderMapper;
import com.rpa.web.pojo.OrderPO;
import com.rpa.web.service.IOrderService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 14:01
 * @description: 订单信息
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public DTPageInfo<OrderDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<OrderDO> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderDO> pos = orderMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), OrderDTO.convert(pos));
    }
}
