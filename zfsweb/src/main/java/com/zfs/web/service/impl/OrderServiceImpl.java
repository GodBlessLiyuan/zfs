package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.mapper.OrderMapper;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.OrderVO;
import com.zfs.web.service.IOrderService;
import com.zfs.web.utils.DTPageInfo;
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
    public DTPageInfo<OrderVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<OrderBO> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderBO> pos = orderMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), OrderVO.convert(pos));
    }
}
