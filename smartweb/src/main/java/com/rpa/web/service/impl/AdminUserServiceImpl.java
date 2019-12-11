package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.bo.AdminUserBO;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.RoleDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.RoleMapper;
import com.rpa.common.pojo.AdminUserPO;
import com.rpa.web.pojo.RolePO;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.Md5Util;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 账号管理——查询
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param extra
     * @return
     */
    @Override
    public DTPageInfo<AdminUserDTO> query(int draw, int start, int length, String phone, String extra) {

        // 分页
        Page<AdminUserDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("extra", extra);

        // 按照条件查询数据
        List<AdminUserBO> lists_DO = adminUserMapper.queryBy(map);

        // 将查询到的 DO 数据转换为 DTO
        List<AdminUserDTO> lists_DTO = new ArrayList<>();
        for (AdminUserBO adminUserBO : lists_DO) {
            AdminUserDTO dto = new AdminUserDTO();
            dto.setaId(adminUserBO.getaId());
            dto.setUsername(adminUserBO.getUsername());
            dto.setName(adminUserBO.getName());
            dto.setPhone(adminUserBO.getPhone());
            dto.setEmail(adminUserBO.getEmail());
            dto.setRoleId(adminUserBO.getRoleId());
            dto.setRoleName(adminUserBO.getRoleName());
            dto.setExtra(adminUserBO.getExtra());
            dto.setOperator(queryUsernameByAid(adminUserBO.getRelationAId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 账号管理——插入
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(AdminUserDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 把 DTO 转换为 PO
        AdminUserPO po = new AdminUserPO();
        po.setRoleId(dto.getRoleId());
        po.setUsername(dto.getUsername());

        String password = null;
        try {
            password = Md5Util.encodeByMd5(Constant.SALT + dto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        po.setPassword(password);

        po.setName(dto.getName());
        po.setPhone(dto.getPhone());
        po.setEmail(dto.getEmail());
        po.setExtra(dto.getExtra());
        po.setCreateTime(new Date());
        po.setRelationAId(aId);

        this.adminUserMapper.insert(po);
        return new ResultVO(1000);
    }


    /**
     * 查询所有的角色
     * @return
     */
    @Override
    public ResultVO queryAllRoles() {

        List<RolePO> POs = this.roleMapper.queryAllRoles();

        if (null == POs) {
            return new ResultVO(1002);
        }

        // 将 po 转换为 dto
        List<RoleDTO> dtos = new ArrayList<>();
        for (RolePO po : POs) {
            RoleDTO dto = new RoleDTO();
            dto.setRoleId(po.getRoleId());
            dto.setRoleName(po.getRoleName());
            dtos.add(dto);
        }
        return new ResultVO<>(1000, dtos);
    }


    /**
     * 查询
     * @param aId
     * @return
     */
    @Override
    public ResultVO queryById(Integer aId) {

        AdminUserBO adminUserBO = this.adminUserMapper.queryById(aId);

        if (null == adminUserBO) {
            return new ResultVO(1002);
        }

        // 将 do 转换为 dto
        AdminUserDTO dto = new AdminUserDTO();
        dto.setaId(adminUserBO.getaId());
        dto.setUsername(adminUserBO.getUsername());
        dto.setPassword(adminUserBO.getPassword());
        dto.setName(adminUserBO.getName());
        dto.setPhone(adminUserBO.getPhone());
        dto.setEmail(adminUserBO.getEmail());
        dto.setRoleId(adminUserBO.getRoleId());
        dto.setRoleName(adminUserBO.getRoleName());
        dto.setExtra(adminUserBO.getExtra());
        dto.setOperator(queryUsernameByAid(adminUserBO.getRelationAId()));

        return new ResultVO<>(1000, dto);
    }


    /**
     * 账号管理——修改
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(AdminUserDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 根据 a_id，从数据库获取要修改的数据对象
        AdminUserPO po = this.adminUserMapper.selectByPrimaryKey(dto.getaId());

        // 把 DTO 转换为 PO
        po.setRoleId(dto.getRoleId());
        po.setUsername(dto.getUsername());

        String password = null;
        try {
            password = Md5Util.encodeByMd5(Constant.SALT + dto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        po.setPassword(password);

        po.setName(dto.getName());
        po.setPhone(dto.getPhone());
        po.setEmail(dto.getEmail());
        po.setExtra(dto.getExtra());
        po.setRelationAId(aId);

        adminUserMapper.updateByPrimaryKey(po);
        return new ResultVO(1000);
    }



    /**
     * 账号管理——删除
     * @param aId
     * @return
     */
    @Override
    public ResultVO delete(Integer aId) {
        this.adminUserMapper.deleteByPrimaryKey(aId);
        return new ResultVO(1000);
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
