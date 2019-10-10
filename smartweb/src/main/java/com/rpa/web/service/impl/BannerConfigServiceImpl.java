package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.BannerConfigDTO;
import com.rpa.web.mapper.BannerConfigMapper;
import com.rpa.web.pojo.BannerConfigPO;
import com.rpa.web.service.BannerConfigService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 18:52 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class BannerConfigServiceImpl implements BannerConfigService {

    @Autowired
    private BannerConfigMapper bannerConfigMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<BannerConfigDTO> query(int draw, int pageNum, int pageSize, String name, Byte status) {

        // 分页
        Page<BannerConfigDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("status", status);

        // 按照条件查询数据
        List<BannerConfigPO> lists_PO = bannerConfigMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<BannerConfigDTO> lists_DTO = new ArrayList<>();
        for (BannerConfigPO po : lists_PO) {
            BannerConfigDTO dto = new BannerConfigDTO();
            dto.setBannerId(po.getBannerId());
            dto.setName(po.getName());
            dto.setStartTime(po.getStartTime());
            dto.setPicPath(po.getPicPath());
            dto.setUrl(po.getUrl());
            dto.setStatus(po.getStatus());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param bannerConfigDTO
     * @param httpSession
     * @return
     * @TODO 还需插入管理员a_id，需从session中获取
     */
    @Override
    public int insert(BannerConfigDTO bannerConfigDTO, HttpSession httpSession) {

        // 把 DTO 转换为 PO
        BannerConfigPO bannerConfigPO = new BannerConfigPO();
        bannerConfigPO.setName(bannerConfigDTO.getName());
        bannerConfigPO.setPicPath(bannerConfigDTO.getPicPath());
        bannerConfigPO.setUrl(bannerConfigDTO.getUrl());
        bannerConfigPO.setCreateTime(new Date());
        bannerConfigPO.setUpdateTime(new Date());
        bannerConfigPO.setStatus((byte)1);
        bannerConfigPO.setDr((byte)1);

        int count = this.bannerConfigMapper.insert(bannerConfigPO);
        return count;
    }

    /**
     * 修改状态
     * @param bannerConfigDTO
     * @param httpSession
     * @return
     * @TODO 需插入操作人，即管理员a_id
     */
    @Override
    public int update(BannerConfigDTO bannerConfigDTO, HttpSession httpSession) {

        // 先查出要修改的数据
        BannerConfigPO bannerConfigPO = this.bannerConfigMapper.selectByPrimaryKey(bannerConfigDTO.getBannerId());
        bannerConfigPO.setStatus(bannerConfigDTO.getStatus());
        bannerConfigPO.setUpdateTime(new Date());
        if (bannerConfigDTO.getStatus() == 2) {
            bannerConfigPO.setStartTime(new Date());
        }

        return this.bannerConfigMapper.updateByPrimaryKey(bannerConfigPO);
    }

    /**
     * 删除
     * @param bannerId
     * @return
     */
    @Override
    public int delete(int bannerId) {
        return this.bannerConfigMapper.deleteByPrimaryKey(bannerId);
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.bannerConfigMapper.queryUsernameByAid(aId);
    }
}
