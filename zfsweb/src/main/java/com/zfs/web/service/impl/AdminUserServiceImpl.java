package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.constant.Constant;
import com.zfs.common.mapper.RoleMapper;
import com.zfs.common.utils.LogUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.common.bo.AdminUserBO;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.common.mapper.AdminUserMapper;
import com.zfs.common.pojo.AdminUserPO;
import com.zfs.common.pojo.RolePO;
import com.zfs.web.service.AdminUserService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.Md5Util;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.AdminUserVO;
import com.zfs.web.vo.RoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final static Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
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
    public DTPageInfo<AdminUserVO> query(int draw, int start, int length, String phone, String extra) {

        // 分页
        Page<AdminUserVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("extra", extra);

        // 按照条件查询数据
        List<AdminUserBO> bos = adminUserMapper.queryBy(map);

        // 将查询到的 bo 数据转换为 vo
        List<AdminUserVO> vos = new ArrayList<>();
        for (AdminUserBO bo : bos) {
            vos.add(bo2vo(bo));
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 账号管理——插入
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(AdminUserDTO dto, HttpSession httpSession) {

        //准备一个对象
        AdminUserPO po = new AdminUserPO();

        //将dto转换为po
        dto2po(dto, po, httpSession);
        po.setCreateTime(new Date());

        //插入
        int result = this.adminUserMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }

        return new ResultVO(1000);
    }


    /**
     * 查询所有的角色
     * @return
     */
    @Override
    public ResultVO queryAllRoles() {

        List<RolePO> pos = this.roleMapper.queryAllRoles();

        if (null == pos) {
            return new ResultVO(1002);
        }

        // 将 po 转换为 vo
        List<RoleVO> vos = new ArrayList<>();
        for (RolePO po : pos) {
            RoleVO vo = new RoleVO();
            vo.setRoleId(po.getRoleId());
            vo.setRoleName(po.getRoleName());
            vos.add(vo);
        }
        return new ResultVO<>(1000, vos);
    }


    /**
     * 查询
     * @param aId
     * @return
     */
    @Override
    public ResultVO queryById(Integer aId) {

        AdminUserBO bo = this.adminUserMapper.queryById(aId);

        if (null == bo) {
            return new ResultVO(1002);
        } else {
            return new ResultVO<>(1000, bo2vo(bo));
        }

    }


    /**
     * 账号管理——修改
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(AdminUserDTO dto, HttpSession httpSession) {

        // 根据 a_id，从数据库获取要修改的数据对象
        AdminUserPO po = this.adminUserMapper.selectByPrimaryKey(dto.getaId());

        // 把 dto 转换为 po
        dto2po(dto, po, httpSession);

        // 修改
        int result = adminUserMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "update", "修改失败", po);
        }

        return new ResultVO(1000);
    }



    /**
     * 账号管理——删除
     */
    @Override
    public ResultVO delete(Integer aId) {
        int result = this.adminUserMapper.deleteByPrimaryKey(aId);
        if (result == 0) {
            LogUtil.log(logger, "delete", "删除失败", aId);
        }
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


    /**
     * 将 AdminUserBO 转换为 AdminUserVO
     * @param bo
     * @return
     */
    private AdminUserVO bo2vo(AdminUserBO bo) {
        AdminUserVO vo = new AdminUserVO();
        vo.setaId(bo.getaId());
        vo.setUsername(bo.getUsername());
        vo.setName(bo.getName());
        vo.setPhone(bo.getPhone());
        vo.setEmail(bo.getEmail());
        vo.setRoleId(bo.getRoleId());
        vo.setRoleName(bo.getRoleName());
        vo.setExtra(bo.getExtra());
        vo.setOperator(queryUsernameByAid(bo.getRelationAId()));
        return vo;
    }


    /**
     * 将 AdminUserDTO 转换为 AdminUserPO
     * @param dto
     * @param po
     * @param httpSession
     * @return
     */
    private void dto2po( AdminUserDTO dto, AdminUserPO po, HttpSession httpSession) {

        if (null != dto.getRoleId()) {
            po.setRoleId(dto.getRoleId());
        }
        if (null != dto.getUsername()) {
            po.setUsername(dto.getUsername());
        }
        if (null != dto.getPassword()) {
            String password = null;
            try {
                password = Md5Util.encodeByMd5(Constant.SALT + dto.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            po.setPassword(password);
        }
        if (null != dto.getName()) {
            po.setName(dto.getName());
        }
        if (null != dto.getPhone()) {
            po.setPhone(dto.getPhone());
        }
        if (null != dto.getEmail()) {
            po.setEmail(dto.getEmail());
        }
        if (null != dto.getExtra()) {
            po.setExtra(dto.getExtra());
        }

        po.setRelationAId(OperatorUtil.getOperatorId(httpSession));
    }
}
