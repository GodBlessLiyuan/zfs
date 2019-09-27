package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.mapper.AdconfigMapper;
import com.rpa.web.pojo.AdconfigPO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 18:56 2019/9/25
 * @version: 1.0.0
 * @description: TODO
 */

@Service
public class AdconfigServiceImpl implements AdconfigService {

    @Autowired
    private AdconfigMapper adconfigMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param name
     * @param adNumber
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<AdconfigDTO> query(int draw, int pageNum, int pageSize, String name, String adNumber, Byte status) {

        // 分页
        Page<AdconfigDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("adNumber", adNumber);
        map.put("status", status);

        // 按照条件查询数据
        List<AdconfigPO> lists_PO = adconfigMapper.query(map);

        // 将查询到的 AdconfigPO 数据转换为 AdconfigDTO
        List<AdconfigDTO> lists_DTO = new ArrayList<>();
        for(AdconfigPO po: lists_PO) {
            AdconfigDTO dto = new AdconfigDTO();
            dto.setaId(po.getaId());
            dto.setAdId(po.getAdId());
            dto.setAdNumber(po.getAdNumber());
            dto.setName(po.getName());
            dto.setPriority(po.getPriority());
            dto.setPhone(po.getPhone());
            dto.setContacts(po.getContacts());
            dto.setTotal(po.getTotal());
            dto.setStatus(po.getStatus());
            dto.setUpdateTime(po.getUpdateTime());
            dto.setCreateTime(po.getCreateTime());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 新增广告
     * @param adconfigDTO
     */
    @Override
    public int insert(AdconfigDTO adconfigDTO) {

        // 把adconfigDTO 转换为 adconfigPO
        AdconfigPO adconfigPO = new AdconfigPO();
        adconfigPO.setaId(adconfigDTO.getaId());
        adconfigPO.setAdNumber(adconfigDTO.getAdNumber());
        adconfigPO.setName(adconfigDTO.getName());
        adconfigPO.setPriority(adconfigDTO.getPriority());
        adconfigPO.setPhone(adconfigDTO.getPhone());
        adconfigPO.setContacts(adconfigDTO.getContacts());
        adconfigPO.setTotal(adconfigDTO.getTotal());
        adconfigPO.setStatus(adconfigDTO.getStatus());
        adconfigPO.setUpdateTime(new Date());
        adconfigPO.setCreateTime(new Date());

        int count = this.adconfigMapper.insert(adconfigPO);
        return count;
    }

    /**
     * 修改广告
     * @param adconfigDTO
     */
    @Override
    public int update(AdconfigDTO adconfigDTO) {

        // 把adconfigDTO 转换为 adconfigPO
        AdconfigPO adconfigPO = new AdconfigPO();
        adconfigPO.setaId(adconfigDTO.getaId());
        adconfigPO.setAdId(adconfigDTO.getAdId());
        adconfigPO.setAdNumber(adconfigDTO.getAdNumber());
        adconfigPO.setName(adconfigDTO.getName());
        adconfigPO.setPriority(adconfigDTO.getPriority());
        adconfigPO.setPhone(adconfigDTO.getPhone());
        adconfigPO.setContacts(adconfigDTO.getContacts());
        adconfigPO.setTotal(adconfigDTO.getTotal());
        adconfigPO.setStatus(adconfigDTO.getStatus());
        adconfigPO.setUpdateTime(new Date());

        int count = adconfigMapper.updateByPrimaryKeySelective(adconfigPO);
        return count;
    }

    /**
     * 删除广告
     * @param adId
     * @return
     */
    @Override
    public int delete(int adId) {
        int count = adconfigMapper.deleteByPrimaryKey(adId);
        return count;
    }
}
