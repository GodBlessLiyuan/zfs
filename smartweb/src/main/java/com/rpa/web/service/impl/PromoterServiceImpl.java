package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.PromoterDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.PromoterMapper;
import com.rpa.web.pojo.PromoterPO;
import com.rpa.web.service.PromoterService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 14:22 2019/9/27
 * @version: 1.0.0
 * @description: TODO
 */
@Service
public class PromoterServiceImpl implements PromoterService {

    @Autowired
    private PromoterMapper promoterMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param proName
     * @param phone
     * @return
     */
    @Override
    public DTPageInfo<PromoterDTO> query(int draw, int start, int length, String proName, String phone) {

        // 分页
        Page<PromoterDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("proName", proName);
        map.put("phone", phone);

        // 按照条件查询数据
        List<PromoterPO> lists_PO = promoterMapper.query(map);

        // 将查询到的 PromoterPO 数据转换为 PromoterDTO
        List<PromoterDTO> lists_DTO = new ArrayList<>();
        for(PromoterPO po: lists_PO) {
            PromoterDTO dto = new PromoterDTO();
            dto.setProId(po.getProId());
            dto.setProName(po.getProName());
            dto.setPhone(po.getPhone());
            dto.setExtra(po.getExtra());
            dto.setaId(po.getaId());
            dto.setCreateTime(po.getCreateTime());
            dto.setUpdateTime(po.getUpdateTime());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param dto
     * @return
     */
    @Override
    public ResultVO insert(PromoterDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 把 dto 转换为 po
        PromoterPO po = new PromoterPO();
        po.setProName(dto.getProName());
        po.setPhone(dto.getPhone());
        po.setExtra(dto.getExtra());
        po.setaId(dto.getaId());
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setaId(aId);

        this.promoterMapper.insert(po);
        return new ResultVO(1000);
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @Override
    public ResultVO update(PromoterDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 从数据库中查询出要修改的数据
        PromoterPO po = this.promoterMapper.selectByPrimaryKey(dto.getProId());

        if (null == po) {
            return new ResultVO(1002);
        }

        // 把 dto 转换为 po
        po.setProName(dto.getProName());
        po.setPhone(dto.getPhone());
        po.setExtra(dto.getExtra());
        po.setaId(dto.getaId());
        po.setUpdateTime(new Date());
        po.setaId(aId);

        promoterMapper.updateByPrimaryKey(po);
        return new ResultVO(1000);

    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }
}
