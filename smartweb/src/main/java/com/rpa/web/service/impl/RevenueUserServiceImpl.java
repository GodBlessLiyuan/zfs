package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.InviteUserDO;
import com.rpa.web.dto.InviteDetailDTO;
import com.rpa.web.dto.InviteUserDTO;
import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.mapper.*;
import com.rpa.web.pojo.InviteDetailPO;
import com.rpa.web.pojo.RevenueUserPO;
import com.rpa.web.service.RevenueUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 10:54 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Service
public class RevenueUserServiceImpl implements RevenueUserService {

    @Autowired
    private RevenueUserMapper revenueUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InviteUserMapper inviteUserMapper;

    @Autowired
    private InviteDetailMapper inviteDetailMapper;

    @Autowired
    private VipTypeMapper vipTypeMapper;

    /**
     * 查询：查询邀请人的收益信息
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param orderby
     * @return
     */
    @Override
    public DTPageInfo<RevenueUserDTO> query(int draw, int start, int length, String phone, int orderby) {

        // 分页
        Page<RevenueUserDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("orderby", orderby);

        // 按照条件查询数据
        List<RevenueUserPO> lists_PO = this.revenueUserMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<RevenueUserDTO> lists_DTO = new ArrayList<>();
        for (RevenueUserPO po : lists_PO) {
            RevenueUserDTO dto = new RevenueUserDTO();
            dto.setUserId(po.getUserId());
            dto.setPhone(queryPhoneByUserId(po.getUserId()));
            dto.setInviteCount(po.getInviteCount());
            dto.setRegisterCount(po.getRegisterCount());
            dto.setPayCount(po.getPayCount());
            dto.setTotalRevenue(po.getTotalRevenue());
            dto.setRemaining(po.getRemaining());
            dto.setWithdraw(po.getWithdraw());
            dto.setWithdrawTime(po.getWithdrawTime());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 查询：根据邀请人的userId，查询该邀请人名下的所有被邀请人大概信息
     * @param draw
     * @param start
     * @param length
     * @param userId
     * @param invitePhone
     * @return
     */
    @Override
    public DTPageInfo<InviteUserDTO> queryInviteduser(int draw, int start, int length, Integer userId, String invitePhone) {

        // 分页
        Page<InviteUserDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("invitePhone", invitePhone);

        // 按照条件查询数据
        List<InviteUserDO> DOs = this.inviteUserMapper.queryInviteduser(map);

        // 将查询到的 DO 数据转换为 DTO
        List<InviteUserDTO> DTOs = new ArrayList<>();
        for (InviteUserDO inviteUserDO : DOs) {

            /**
             * 这里所查询的userId，是根据invitePhone从t_user表中查询到的被邀请人的userId
             * t_invite表中的user_id是邀请人的，不是被邀请人的，作用仅限于连表，不必返回给前端
             */
            InviteUserDTO dto = new InviteUserDTO();
            dto.setUserId(inviteUserDO.getUserId());
            dto.setInvitePhone(inviteUserDO.getInvitePhone());
            dto.setAcceptTime(inviteUserDO.getAcceptTime());
            dto.setRegisterTime(inviteUserDO.getRegisterTime());
            dto.setEarnings(inviteUserDO.getEarnings());

            DTOs.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), DTOs);
    }


    /**
     * 查询：根据被邀请人的userId，查询其购买商品的详细信息
     * @param draw
     * @param start
     * @param length
     * @param userId
     * @param viptypeId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public DTPageInfo<InviteDetailDTO> queryInviteduserDetail(int draw, int start, int length, Integer userId, Integer viptypeId, Date startTime, Date endTime) {

        // 分页
        Page<InviteDetailDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("viptypeId", viptypeId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        // 按照条件查询数据
        List<InviteDetailPO> POs = this.inviteDetailMapper.queryInviteduserDetail(map);

        // 将查询到的 PO 数据转换为 DTO
        List<InviteDetailDTO> DTOs = new ArrayList<>();
        for (InviteDetailPO po : POs) {
            InviteDetailDTO dto = new InviteDetailDTO();
            dto.setPayTime(po.getPayTime());
            dto.setComTypeName(po.getComTypeName());
            dto.setPay(po.getPay());
            dto.setVipname(queryVipnameByVipid(po.getViptypeId()));
            dto.setEarnings(po.getEarnings());

            DTOs.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), DTOs);
    }


    /**
     * 根据userId，从t_user表中查询phone
     *
     * @param userId
     * @return
     */
    private String queryPhoneByUserId(Long userId) {
        return this.userMapper.queryPhoneByUserId(userId);
    }

    /**
     * 根据viptypeId，从t_viptype中查询vipname
     * @param viptypeId
     * @return
     */
    private String queryVipnameByVipid(Integer viptypeId) {
        return this.vipTypeMapper.queryVipnameByVipid(viptypeId);
    }
}
