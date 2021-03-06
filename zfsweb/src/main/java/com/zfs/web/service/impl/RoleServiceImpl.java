package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.RoleMapper;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.common.pojo.RolePO;
import com.zfs.web.service.RoleService;
import com.zfs.web.vo.RoleVO;
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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultVO query(int pageNum, int pageSize) {

        // 分页
        Page<RoleVO> page = PageHelper.startPage(pageNum, pageSize);

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
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(),vos));
    }
}