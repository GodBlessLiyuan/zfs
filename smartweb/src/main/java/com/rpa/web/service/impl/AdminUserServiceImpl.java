package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.AdminUserDO;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.rpa.web.enumeration.ExceptionEnum.*;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 账号管理——查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param extra
     * @return
     */
    @Override
    public DTPageInfo<AdminUserDTO> query(int draw, int pageNum, int pageSize, String phone, Byte extra) {

        // 分页
        Page<AdminUserDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", phone);
        map.put("status", extra);

        // 按照条件查询数据
        List<AdminUserDO> lists_DO = adminUserMapper.query(map);

        // 将查询到的 DO 数据转换为 DTO
        List<AdminUserDTO> lists_DTO = new ArrayList<>();
        for (AdminUserDO adminUserDO : lists_DO) {
            AdminUserDTO dto = new AdminUserDTO();
            dto.setaId(adminUserDO.getaId());
            dto.setUsername(adminUserDO.getUsername());
            dto.setName(adminUserDO.getName());
            dto.setPhone(adminUserDO.getPhone());
            dto.setEmail(adminUserDO.getEmail());
            dto.setRoleName(adminUserDO.getRoleName());
            dto.setExtra(adminUserDO.getExtra());
            dto.setOperator(queryUsernameByAid(adminUserDO.getRelationAId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 账号管理——插入
     * @param adminUserDTO
     * @param httpSession
     * @return
     * @TODO 需插入操作人，即relation_a_id，需从session中获取
     */
    @Override
    public ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession) {

        // 把 DTO 转换为 PO
        AdminUserPO adminUserPO = new AdminUserPO();
        adminUserPO.setRoleId(adminUserDTO.getRoleId());
        adminUserPO.setUsername(adminUserDTO.getUsername());
        adminUserPO.setPassword(adminUserDTO.getPassword());
        adminUserPO.setName(adminUserDTO.getName());
        adminUserPO.setPhone(adminUserDTO.getPhone());
        adminUserPO.setEmail(adminUserDTO.getEmail());
        adminUserPO.setExtra(adminUserDTO.getExtra());
        adminUserPO.setCreateTime(new Date());

        int count = this.adminUserMapper.insert((AdminUserDO) adminUserPO);
        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(INSERT_ERROR);
    }

    /**
     * 账号管理——修改
     * @param adminUserDTO
     * @param httpSession
     * @return
     * @TODO 需插入操作人，即relation_a_id，需从session中获取
     */
    @Override
    public ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession) {

        // 根据 a_id，从数据库获取要修改的数据对象
        AdminUserPO adminUserPO = this.adminUserMapper.selectByPrimaryKey(adminUserDTO.getaId());

        // 把 DTO 转换为 PO
        adminUserPO.setRoleId(adminUserDTO.getRoleId());
        adminUserPO.setUsername(adminUserDTO.getUsername());
        adminUserPO.setPassword(adminUserDTO.getPassword());
        adminUserPO.setName(adminUserDTO.getName());
        adminUserPO.setPhone(adminUserDTO.getPhone());
        adminUserPO.setEmail(adminUserDTO.getEmail());
        adminUserPO.setExtra(adminUserDTO.getExtra());

        int count = adminUserMapper.updateByPrimaryKey((AdminUserDO) adminUserPO);
        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(UPDATE_ERROR);
    }

    /**
     * 账号管理——删除
     * @param aId
     * @return
     */
    @Override
    public ResultVO delete(int aId) {
        int count = this.adminUserMapper.deleteByPrimaryKey(aId);
        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(DELETE_ERROR);
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     *
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }

}
