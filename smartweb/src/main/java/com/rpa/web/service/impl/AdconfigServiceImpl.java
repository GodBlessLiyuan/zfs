package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.common.Constant;
import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.mapper.AdconfigMapper;
import com.rpa.web.mapper.KeyValueMapper;
import com.rpa.web.pojo.AdconfigPO;
import com.rpa.web.pojo.KeyValuePO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.rpa.web.common.Constant.SHOW_INTERVAL;

/**
 * @author: dangyi
 * @date: Created in 18:56 2019/9/25
 * @version: 1.0.0
 * @description:
 */

@Service
public class AdconfigServiceImpl implements AdconfigService {

    @Autowired
    private AdconfigMapper adconfigMapper;

    @Autowired
    private KeyValueMapper keyValueMapper;

    /**
     * 查询
     *
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
        for (AdconfigPO po : lists_PO) {
            AdconfigDTO dto = new AdconfigDTO();
            dto.setAdId(po.getAdId());
            dto.setAdNumber(po.getAdNumber());
            dto.setName(po.getName());
            dto.setContacts(po.getContacts());
            dto.setPhone(po.getPhone());
            dto.setPriority(po.getPriority());
            dto.setTotal(po.getTotal());
            dto.setStatus(po.getStatus());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 查询广告策略
     * @param showInterval
     * @return
     */
    @Override
    public KeyValueDTO queryStrategy(int showInterval) {

        KeyValuePO keyValuePO = this.keyValueMapper.selectByPrimaryKey(showInterval);
        KeyValueDTO keyValueDTO = new KeyValueDTO();
        keyValueDTO.setKeyName(keyValuePO.getKeyName());
        keyValueDTO.setValue(keyValuePO.getValue());

        return keyValueDTO;
    }


    /**
     * 插入
     *
     * @param adconfigDTO
     * @TODO 需插入操作人，即管理员ID，需从session中获取
     */
    @Override
    public int insert(AdconfigDTO adconfigDTO, HttpSession httpSession) {

        // 把adconfigDTO 转换为 adconfigPO
        AdconfigPO adconfigPO = new AdconfigPO();
        adconfigPO.setAdNumber(adconfigDTO.getAdNumber());
        adconfigPO.setName(adconfigDTO.getName());
        adconfigPO.setContacts(adconfigDTO.getContacts());
        adconfigPO.setPhone(adconfigDTO.getPhone());
        adconfigPO.setPriority(adconfigDTO.getPriority());
        adconfigPO.setTotal(adconfigDTO.getTotal());
        adconfigPO.setUpdateTime(new Date());
        adconfigPO.setCreateTime(new Date());
        adconfigPO.setStatus((byte) 1);
        adconfigPO.setDr((byte) 1);

        int count = this.adconfigMapper.insert(adconfigPO);
        return count;
    }

    /**
     * 修改
     *
     * @param adconfigDTO
     * @param httpSession
     * @TODO 还需要修改操作人，即管理员a_id字段，需从session中获取
     */
    @Override
    public int update(AdconfigDTO adconfigDTO, HttpSession httpSession) {

        // 根据 ad_id，从数据库获取要修改的数据对象
        AdconfigPO adconfigPO = this.adconfigMapper.selectByPrimaryKey(adconfigDTO.getAdId());

        // 把 adconfigDTO 转换为 adconfigPO
        adconfigPO.setAdNumber(adconfigDTO.getAdNumber());
        adconfigPO.setName(adconfigDTO.getName());
        adconfigPO.setContacts(adconfigDTO.getContacts());
        adconfigPO.setPhone(adconfigDTO.getPhone());
        adconfigPO.setPriority(adconfigDTO.getPriority());
        adconfigPO.setTotal(adconfigDTO.getTotal());
        adconfigPO.setUpdateTime(new Date());

        int count = adconfigMapper.updateByPrimaryKey(adconfigPO);
        return count;
    }

    /**
     * 修改状态
     *
     * @param adconfigDTO
     * @param httpSession
     * @return
     * @TODO 还需要修改操作人，即管理员a_id字段，需从session中获取
     */
    @Override
    public int updateStatus(AdconfigDTO adconfigDTO, HttpSession httpSession) {

        // 根据主键ad_id，从数据库查出要修改的数据
        AdconfigPO adconfigPO = this.adconfigMapper.selectByPrimaryKey(adconfigDTO.getAdId());

        adconfigPO.setStatus(adconfigDTO.getStatus());
        adconfigPO.setUpdateTime(new Date());

        return this.adconfigMapper.updateByPrimaryKey(adconfigPO);
    }

    /**
     * 修改广告展现间隔
     *
     * @param show_interval
     * @return
     */
    @Override
    public int updateStrategy(String show_interval) {

        KeyValuePO po = new KeyValuePO();
        po.setKeyName(SHOW_INTERVAL);
        po.setValue(show_interval);

        // 先查询下t_key_value表中是否有SHOW_INTERVAL数据
        KeyValuePO keyValuePO = this.keyValueMapper.selectByPrimaryKey(SHOW_INTERVAL);
        if (keyValuePO == null) {
            return this.keyValueMapper.insert(po);
        }
        return this.keyValueMapper.updateByPrimaryKey(po);





    }



    /**
     * 删除广告
     *
     * @param adId
     * @return
     */
    @Override
    public int delete(int adId) {
        int count = adconfigMapper.deleteByPrimaryKey(adId);
        return count;
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     *
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adconfigMapper.queryUsernameByAid(aId);
    }
}
