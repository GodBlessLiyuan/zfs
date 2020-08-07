package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.mapper.OrderMapper;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IOrderService;
import com.zfs.web.vo.OrderVO;
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
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {
        Page<OrderBO> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderBO> bos = orderMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), OrderVO.convert(bos)));
    }
}
