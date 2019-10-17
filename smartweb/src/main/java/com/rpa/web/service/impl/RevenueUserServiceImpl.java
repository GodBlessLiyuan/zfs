package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.mapper.RevenueUserMapper;
import com.rpa.web.pojo.RevenueUserPO;
import com.rpa.web.service.RevenueUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 10:54 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Service
public class RevenueUserServiceImpl implements RevenueUserService {

    @Autowired
    private RevenueUserMapper revenueUserMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param orderBy
     * @return
     */
    @Override
    public DTPageInfo<RevenueUserDTO> query(int draw, int pageNum, int pageSize, String phone, int orderBy) {

        // 分页
        Page<RevenueUserDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", phone);
        map.put("orderBy", orderBy);

        // 按照条件查询数据
        List<RevenueUserPO> lists_PO = this.revenueUserMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<RevenueUserDTO> lists_DTO = new ArrayList<>();
        for (RevenueUserPO po : lists_PO) {
            RevenueUserDTO dto = new RevenueUserDTO();
            dto.setInvitePhone(queryPhoneByUserId(po.getUserId()));
            dto.setInviteCount(po.getInviteCount());
            dto.setRegisterCount(po.getRegisterCount());
            dto.setPayCount(po.getPayCount());
            dto.setTotalRevenue(po.getTotalRevenue());
            dto.setRemaining(po.getRemaining());
            dto.setWithdraw(po.getWithdraw());
            dto.setWithdrawTime(po.getWithdrawTime());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }



    /**
     * 根据userId，从t_user表中查询phone
     *
     * @param userId
     * @return
     */
    private String queryPhoneByUserId(Long userId) {
        return this.revenueUserMapper.queryPhoneByUserId(userId);
    }
}
