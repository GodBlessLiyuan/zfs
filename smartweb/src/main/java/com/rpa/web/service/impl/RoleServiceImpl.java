package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @return
     */
    @Override
    public DTPageInfo<RoleDTO> query(int draw, int pageNum, int pageSize, String phone) {

        // 分页
        Page<RoleDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("phone", phone);

        // 按照条件查询数据
        List<RolePO> lists_PO = roleMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<RoleDTO> lists_DTO = new ArrayList<>();
        for(RolePO po: lists_PO) {
            RoleDTO dto = new RoleDTO();
            dto.setRoleId(po.getRoleId());
            dto.setRoleNum(po.getRoleNum());
            dto.setRoleName(po.getRoleName());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 修改
     * @param roleDTO
     * @param httpSession
     * @return
     * @TODO 还需修改管理员a_id，需从session中获取
     */
    @Override
    public int update(RoleDTO roleDTO, HttpSession httpSession) {

        // 先查询出需要修改的数据
        RolePO rolePO = this.roleMapper.selectByPrimaryKey(roleDTO.getRoleId());
        rolePO.setRoleNum(roleDTO.getRoleNum());
        rolePO.setRoleName(roleDTO.getRoleName());
        rolePO.setUpdateTime(new Date());

        return this.roleMapper.updateByPrimaryKey(rolePO);
    }

    @Override
    public int delete(int roleId) {
        return this.roleMapper.deleteByPrimaryKey(roleId);
    }
}
