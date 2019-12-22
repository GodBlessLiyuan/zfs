package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.WithdrawUserMapper;
import com.rpa.common.utils.LogUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.utils.OperatorUtil;
import com.rpa.web.vo.WithdrawUserVO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.common.pojo.WithdrawUserPO;
import com.rpa.web.service.WithdrawUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 9:07 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Service
public class WithdrawUserServiceImpl implements WithdrawUserService {
    private final static Logger logger = LoggerFactory.getLogger(WithdrawUserServiceImpl.class);

    @Resource
    private WithdrawUserMapper withdrawUserMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<WithdrawUserVO> query(int draw, int start, int length, String phone, Byte status) {

        // 分页
        Page<WithdrawUserVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("status", status);

        // 按照条件查询数据
        List<WithdrawUserPO> pos = this.withdrawUserMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<WithdrawUserVO> vos = new ArrayList<>();
        for (WithdrawUserPO po : pos) {
            WithdrawUserVO vo = new WithdrawUserVO();
            vo.setWithdrawId(po.getWithdrawId());
            vo.setCreateTime(po.getCreateTime());
            vo.setPhone(queryPhoneByUserId(po.getUserId()));
            if (null != po.getWithdraw()) {
                vo.setWithdraw(po.getWithdraw()*0.01);
            }
            if (null != po.getRemaining()) {
                vo.setRemaining(po.getRemaining()*0.01);
            }
            vo.setAliAccount(po.getAliAccount());
            vo.setAliName(po.getAliName());
            vo.setWithdrawTime(po.getWithdrawTime());
            vo.setAuditTime(po.getAuditTime());
            vo.setEndTime(po.getEndTime());
            vo.setStatus(po.getStatus());
            vo.setOperator(queryUsernameByAid(po.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 修改状态
     * @param withdrawId
     * @param status
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(Integer withdrawId, Byte status, HttpSession httpSession) {

        // 根据主键withdraw_id，从数据库查出要修改的数据
        WithdrawUserPO po = this.withdrawUserMapper.selectByPrimaryKey(withdrawId);

        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        po.setAuditTime(new Date());
        po.setEndTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.withdrawUserMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "update", "修改失败", po);
        }

        /**
         * 调用支付宝接口，进行付款操作
         */
        this.AliPay(po.getAliAccount(), po.getAliName());


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
     * 根据userId，从t_user表中查询phone
     *
     * @param userId
     * @return
     */
    private String queryPhoneByUserId(Long userId) {
        return this.userMapper.queryPhoneByUserId(userId);
    }


    /**
     * 预留接口：Alipay
     * @param aliAccount
     * @param aliName
     * @return
     */
    private int AliPay(String aliAccount, String aliName) {

        // 业务逻辑：根据该方法返回状态，对提现数据的状态进行修改，并更新结束时间
        return 0;
    }
}
