package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.WithdrawUserDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.WithdrawUserMapper;
import com.rpa.web.pojo.WithdrawUserPO;
import com.rpa.web.service.WithdrawUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private WithdrawUserMapper withdrawUserMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<WithdrawUserDTO> query(int draw, int pageNum, int pageSize, String phone, Byte status) {

        // 分页
        Page<WithdrawUserDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", phone);
        map.put("status", status);

        // 按照条件查询数据
        List<WithdrawUserPO> lists_PO = this.withdrawUserMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<WithdrawUserDTO> lists_DTO = new ArrayList<>();
        for (WithdrawUserPO po : lists_PO) {
            WithdrawUserDTO dto = new WithdrawUserDTO();
            dto.setCreateTime(po.getCreateTime());
            dto.setPhone(queryPhoneByUserId(po.getUserId()));
            dto.setWithdraw(po.getWithdraw());
            dto.setRemaining(po.getRemaining());
            dto.setAliAccount(po.getAliAccount());
            dto.setAliName(po.getAliName());
            dto.setWithdrawTime(po.getWithdrawTime());
            dto.setAuditTime(po.getAuditTime());
            dto.setEndTime(po.getEndTime());
            dto.setStatus(po.getStatus());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 修改状态
     * @param withdrawUserDTO
     * @param httpSession
     * @return
     * @TODO 还需要修改操作人，即管理员a_id字段，需从session中获取
     */
    @Override
    public ResultVO update(WithdrawUserDTO withdrawUserDTO, HttpSession httpSession) {

        // 根据主键withdraw_id，从数据库查出要修改的数据
        WithdrawUserPO withdrawUserPO = this.withdrawUserMapper.selectByPrimaryKey(withdrawUserDTO.getWithdrawId());

        withdrawUserPO.setStatus(withdrawUserDTO.getStatus());
        if (withdrawUserDTO.getStatus() == 1) {
            withdrawUserPO.setAuditTime(new Date());
        }

        int count = this.withdrawUserMapper.updateByPrimaryKey(withdrawUserPO);

        // 调用支付宝接口，进行付款操作
        this.AliPay(withdrawUserDTO.getAliAccount(), withdrawUserDTO.getAliName());

        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     *
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.withdrawUserMapper.queryUsernameByAid(aId);
    }

    /**
     * 根据userId，从t_user表中查询phone
     *
     * @param userId
     * @return
     */
    private String queryPhoneByUserId(Long userId) {
        return this.withdrawUserMapper.queryPhoneByUserId(userId);
    }


    private int AliPay(String aliAccount, String aliName) {

        // 业务逻辑：根据该方法返回状态，对提现数据的状态进行修改，并更新结束时间
        return 0;
    }
}
