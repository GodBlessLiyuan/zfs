package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.BatchInfoDTO;
import com.rpa.web.mapper.BatchInfoMapper;
import com.rpa.web.pojo.BatchInfoPO;
import com.rpa.web.service.BatchInfoService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 19:51 2019/10/8
 * @version: 1.0.0
 * @description: TODO
 */

@Service
public class BatchInfoServiceImpl implements BatchInfoService {

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param vipkey
     * @return
     */
    @Override
    public DTPageInfo<BatchInfoDTO> query(int draw, int pageNum, int pageSize, String vipkey) {

        // 分页
        Page<BatchInfoDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("vipkey", vipkey);

        // 按照条件查询数据
        List<BatchInfoPO> lists_PO = batchInfoMapper.query(map);

        // 将查询到的 BatchInfoPO 数据转换为 BatchInfoDTO
        List<BatchInfoDTO> lists_DTO = new ArrayList<>();
        for(BatchInfoPO po: lists_PO) {
            BatchInfoDTO dto = new BatchInfoDTO();
            dto.setVipkey(po.getVipkey());
            dto.setChanNickname(po.getChanNickname());
            dto.setChanName(po.getChanName());
            dto.setComTypeName(po.getComTypeName());
            dto.setCreateTime(po.getCreateTime());
            dto.setStatus(po.getStatus());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }
}
