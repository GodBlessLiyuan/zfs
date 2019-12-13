package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.*;
import com.rpa.common.pojo.InviteDetailPO;
import com.rpa.common.pojo.RevenueUserPO;
import com.rpa.web.common.PageHelper;
import com.rpa.common.bo.InviteUserBO;
import com.rpa.web.vo.InviteDetailVO;
import com.rpa.web.vo.InviteUserVO;
import com.rpa.web.vo.RevenueUserVO;
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
    private ViptypeMapper vipTypeMapper;

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
    public DTPageInfo<RevenueUserVO> query(int draw, int start, int length, String phone, int orderby) {

        // 分页
        Page<RevenueUserVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("orderby", orderby);

        // 按照条件查询数据
        List<RevenueUserPO> pos = this.revenueUserMapper.query(map);

        // 将查询到的 PO 数据转换为 VO
        List<RevenueUserVO> vos = new ArrayList<>();
        for (RevenueUserPO po : pos) {
            if (po.getInviteCount() != 0) {
                RevenueUserVO vo = new RevenueUserVO();
                vo.setUserId(po.getUserId());
                vo.setPhone(queryPhoneByUserId(po.getUserId()));
                vo.setInviteCount(po.getInviteCount());
                vo.setRegisterCount(po.getRegisterCount());
                vo.setPayCount(po.getPayCount());
                if (null != po.getTotalRevenue()) {
                    vo.setTotalRevenue(Double.valueOf(po.getTotalRevenue())*0.01);
                }
                if (null != po.getRemaining()) {
                    vo.setRemaining(Double.valueOf(po.getRemaining())*0.01);
                }
                if (null != po.getWithdraw()) {
                    vo.setWithdraw(Double.valueOf(po.getWithdraw())*0.01);
                }
                vo.setWithdrawTime(po.getWithdrawTime());

                vos.add(vo);
            }
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
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
    public DTPageInfo<InviteUserVO> queryInviteduser(int draw, int start, int length, Integer userId, String invitePhone) {

        // 分页
        Page<InviteUserVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("invitePhone", invitePhone);

        // 按照条件查询数据
        List<InviteUserBO> bos = this.inviteUserMapper.queryInviteduser(map);

        // 将查询到的 bo 数据转换为 vo
        List<InviteUserVO> vos = new ArrayList<>();
        for (InviteUserBO bo : bos) {

            /**
             * 这里所查询的userId，是根据invitePhone从t_user表中查询到的被邀请人的userId
             * t_invite表中的user_id是邀请人的，不是被邀请人的，作用仅限于连表，不必返回给前端
             */
            InviteUserVO vo = new InviteUserVO();
            vo.setUserId(bo.getUserId());
            vo.setInvitePhone(bo.getInvitePhone());
            vo.setAcceptTime(bo.getAcceptTime());
            vo.setRegisterTime(bo.getRegisterTime());
            if (null != bo.getEarnings()) {
                vo.setEarnings(bo.getEarnings()*0.01);
            }

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
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
    public DTPageInfo<InviteDetailVO> queryInviteduserDetail(int draw, int start, int length, Integer userId, Integer viptypeId, String startTime, String endTime) {

        // 分页
        Page<InviteDetailVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("viptypeId", viptypeId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        // 按照条件查询数据
        List<InviteDetailPO> pos = this.inviteDetailMapper.queryInviteduserDetail(map);

        // 将查询到的 po 数据转换为 vo
        List<InviteDetailVO> vos = new ArrayList<>();
        for (InviteDetailPO po : pos) {
            InviteDetailVO vo = new InviteDetailVO();
            vo.setPayTime(po.getPayTime());
            vo.setComTypeName(po.getComTypeName());
            if (null != po.getPay()) {
                vo.setPay(Double.valueOf(po.getPay())*0.01);
            }
            vo.setVipname(queryVipnameByVipid(po.getViptypeId()));
            vo.setProportion(po.getProportion()+"%");
            if (null != po.getEarnings()) {
                vo.setEarnings(Double.valueOf(po.getEarnings())*0.01);
            }

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
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
