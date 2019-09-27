package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.mapper.PromoterMapper;
import com.rpa.web.pojo.PromoterPO;
import com.rpa.web.service.PromoterService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 14:22 2019/9/27
 * @version: 1.0.0
 * @description: TODO
 */
@Service
public class PromoterServiceImpl implements PromoterService {

    @Autowired
    private PromoterMapper promoterMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param proName
     * @param phone
     * @return
     */
    @Override
    public DTPageInfo<PromoterDTO> query(int draw, int pageNum, int pageSize, String proName, String phone) {

        // 分页
        Page<PromoterDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("proName", proName);
        map.put("phone", phone);

        // 按照条件查询数据
        List<PromoterPO> lists_PO = promoterMapper.query(map);

        // 将查询到的 PromoterPO 数据转换为 PromoterDTO
        List<PromoterDTO> lists_DTO = new ArrayList<>();
        for(PromoterPO po: lists_PO) {
            PromoterDTO dto = new PromoterDTO();
            dto.setProId(po.getProId());
            dto.setProName(po.getProName());
            dto.setPhone(po.getPhone());
            dto.setExtra(po.getExtra());
            dto.setaId(po.getaId());
            dto.setCreateTime(po.getCreateTime());
            dto.setUpdateTime(po.getUpdateTime());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param promoterDTO
     * @return
     */
    @Override
    public int insert(PromoterDTO promoterDTO) {

        // 把 promoterDTO 转换为 prometerPO
        PromoterPO promoterPO = new PromoterPO();
        promoterPO.setProName(promoterDTO.getProName());
        promoterPO.setPhone(promoterDTO.getPhone());
        promoterPO.setExtra(promoterDTO.getExtra());
        promoterPO.setaId(promoterDTO.getaId());
        promoterPO.setCreateTime(new Date());
        promoterPO.setUpdateTime(new Date());

        int count = this.promoterMapper.insert(promoterPO);
        return count;
    }

    /**
     * 修改
     * @param promoterDTO
     * @return
     */
    @Override
    public int update(PromoterDTO promoterDTO) {

        // 把 promoterDTO 转换为 promoterPO
        PromoterPO promoterPO = new PromoterPO();
        promoterPO.setProId(promoterDTO.getProId());
        promoterPO.setProName(promoterDTO.getProName());
        promoterPO.setPhone(promoterDTO.getPhone());
        promoterPO.setExtra(promoterDTO.getExtra());
        promoterPO.setaId(promoterDTO.getaId());
        promoterPO.setUpdateTime(new Date());

        // 更新
        int count = promoterMapper.updateByPrimaryKeySelective(promoterPO);
        return count;

    }
}
