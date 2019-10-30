package com.rpa.front.service.impl;

import com.rpa.front.bo.InviteUserBO;
import com.rpa.front.common.ResultVO;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.mapper.InviteUserMapper;
import com.rpa.front.mapper.RevenueUserMapper;
import com.rpa.front.mapper.WithdrawUserMapper;
import com.rpa.front.pojo.RevenueUserPO;
import com.rpa.front.pojo.WithdrawUserPO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.vo.DetailsVO;
import com.rpa.front.vo.IncomeVO;
import com.rpa.front.vo.RecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 19:16
 * @description: 爱收益
 * @version: 1.0
 */
@Service
public class IncomeServiceImpl implements IIncomeService {
    @Resource
    private RevenueUserMapper revenueUserMapper;
    @Resource
    private InviteUserMapper inviteUserMapper;
    @Resource
    private WithdrawUserMapper withdrawUserMapper;

    @Override
    public ResultVO query(IncomeDTO dto) {
        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(dto.getUd());

        IncomeVO vo = new IncomeVO();
        vo.setBalance(po.getRemaining());
        vo.setInvitenum(po.getInviteCount());
        vo.setPaynum(po.getPayCount());
        vo.setTotalmny(po.getTotalRevenue());

        return new ResultVO<>(1000, vo);
    }

    @Override
    public ResultVO determine(DetermineDTO dto, long userId) {
        RevenueUserPO revenueUserPO = revenueUserMapper.selectByPrimaryKey(userId);
        if(revenueUserPO.getRemaining() < dto.getMoney()) {
            // 余额不足
            return new ResultVO(2000);
        }

        // TODO: 打款

        WithdrawUserPO withdrawUserPO = new WithdrawUserPO();
        withdrawUserPO.setCreateTime(new Date());
        withdrawUserPO.setUserId(userId);
        withdrawUserPO.setWithdraw(dto.getMoney());
        withdrawUserPO.setRemaining(revenueUserPO.getRemaining());


        return new ResultVO<>(1000, null);
    }

    @Override
    public ResultVO queryRecords(long userId) {
        List<WithdrawUserPO> pos = withdrawUserMapper.queryByUserId(userId);
        if (pos == null || pos.size() == 0) {
            return null;
        }

        List<RecordVO> vos = new ArrayList<>();
        for (WithdrawUserPO po : pos) {
            RecordVO vo = new RecordVO();
            vo.setCTime(po.getCreateTime());
            vo.setAccount(po.getAliAccount());
            vo.setName(po.getAliName());
            vo.setStatus(po.getStatus());
            vo.setMoney(po.getWithdraw());
            vos.add(vo);
        }

        return new ResultVO<>(1000, vos);
    }

    @Override
    public ResultVO queryDetails(long userId) {
        DetailsVO vo = new DetailsVO();

        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(userId);
        vo.setBalance(po.getRemaining());
        vo.setInvitenum(po.getInviteCount());
        vo.setPaynum(po.getPayCount());
        vo.setTotalmny(po.getTotalRevenue());

        List<InviteUserBO> bos = inviteUserMapper.queryByUserId(userId);
        List<DetailsVO.Detail> details = new ArrayList<>();
        for (InviteUserBO bo : bos) {
            DetailsVO.Detail detail = vo.new Detail();
            // 手机号中间四位以*代替
            StringBuilder sb = new StringBuilder(bo.getInvitePhone());
            detail.setPh(sb.replace(3, 7, "****").toString());
            detail.setCtime(bo.getCreateTime());
            detail.setEarnings(bo.getEarnings());
            details.add(detail);
        }
        vo.setDetails(details);

        return new ResultVO<>(1000, vo);
    }
}
