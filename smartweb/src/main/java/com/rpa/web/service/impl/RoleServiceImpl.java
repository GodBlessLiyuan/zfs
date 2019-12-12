package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.RoleMapper;
import com.rpa.web.common.PageHelper;
import com.rpa.common.pojo.RolePO;
import com.rpa.web.service.RoleService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DTPageInfo<RoleVO> query(int draw, int start, int length) {

        // 分页
        Page<RoleVO> page = PageHelper.startPage(start, length);

        // 按照条件查询数据
        List<RolePO> pos = roleMapper.query();

        // 将查询到的 PO 数据转换为 DTO
        List<RoleVO> vos = new ArrayList<>();
        for (RolePO po : pos) {
            RoleVO vo = new RoleVO();
            vo.setRoleId(po.getRoleId());
            vo.setRoleNum(po.getRoleNum());
            vo.setRoleName(po.getRoleName());

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }
}