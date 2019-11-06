package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.RoleDTO;
import com.rpa.web.mapper.RoleMapper;
import com.rpa.web.pojo.RolePO;
import com.rpa.web.service.RoleService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 9:54 2019/10/11
 * @version: 1.0.0
 * @description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询
     *
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @Override
    public DTPageInfo<RoleDTO> query(int draw, int start, int length) {

        // 分页
        Page<RoleDTO> page = PageHelper.startPage(start, length);

        // 按照条件查询数据
        List<RolePO> lists_PO = roleMapper.query();

        // 将查询到的 PO 数据转换为 DTO
        List<RoleDTO> lists_DTO = new ArrayList<>();
        for (RolePO po : lists_PO) {
            RoleDTO dto = new RoleDTO();
            dto.setRoleId(po.getRoleId());
            dto.setRoleNum(po.getRoleNum());
            dto.setRoleName(po.getRoleName());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }
}