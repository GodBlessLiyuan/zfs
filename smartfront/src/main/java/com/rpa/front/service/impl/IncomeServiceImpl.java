package com.rpa.front.service.impl;

import com.rpa.front.bo.InviteUserBO;
import com.rpa.front.common.ErrorCode;
import com.rpa.front.common.ResultVO;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.DownLoadDTO;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.dto.base.TokenDTO;
import com.rpa.front.mapper.*;
import com.rpa.front.pojo.*;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.utils.RequestUtil;
import com.rpa.front.vo.DetailsVO;
import com.rpa.front.vo.IncomeVO;
import com.rpa.front.vo.RecordVO;
import com.rpa.front.vo.ShareCodeVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 19:16
 * @description: 爱收益
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class IncomeServiceImpl implements IIncomeService {
    @Resource
    private RevenueUserMapper revenueUserMapper;
    @Resource
    private InviteUserMapper inviteUserMapper;
    @Resource
    private WithdrawUserMapper withdrawUserMapper;
    @Resource
    private AppMapper appMapper;
    @Resource
    private UserMapper userMapper;

    @Value("${project.shareurl}")
    private String shareUrl;
    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO query(IncomeDTO dto) {
        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(dto.getUd());
        if (po == null) {
            // 初始化
            po = new RevenueUserPO();
            po.setUserId(dto.getUd());
            po.setInviteCount(0);
            po.setPayCount(0);
            po.setRegisterCount(0L);
            po.setTotalRevenue(0L);
            po.setWithdraw(0L);
            po.setWithdrawTime(0);
            po.setRemaining(0L);

            revenueUserMapper.insert(po);
        }

        IncomeVO vo = new IncomeVO();
        vo.setBalance(po.getRemaining());
        vo.setInvitenum(po.getInviteCount());
        vo.setPaynum(po.getPayCount());
        vo.setTotalmny(po.getTotalRevenue());

        return new ResultVO<>(1000, vo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO determine(DetermineDTO dto, IncomeDTO loginInfo) {
        List<WithdrawUserPO> pos = withdrawUserMapper.queryByUserId(loginInfo.getUd());
        for (WithdrawUserPO po : pos) {
            if (1 == po.getStatus() || 3 == po.getStatus()) {
                // 当前存在尚未结束的提款申请
                return new ResultVO(ErrorCode.WITHDRAW_UNFINISHED);
            }
        }

        RevenueUserPO revenueUserPO = revenueUserMapper.selectByPrimaryKey(loginInfo.getUd());
        if (revenueUserPO.getRemaining() < dto.getMoney()) {
            // 余额不足
            return new ResultVO(ErrorCode.INSUFFICIENT_BALANCE);
        }

        WithdrawUserPO withdrawUserPO = new WithdrawUserPO();
        withdrawUserPO.setCreateTime(new Date());
        withdrawUserPO.setUserId(loginInfo.getUd());
        withdrawUserPO.setDeviceId(loginInfo.getId());
        withdrawUserPO.setUserDeviceId(loginInfo.getUdd());
        withdrawUserPO.setWithdraw(dto.getMoney());
        withdrawUserPO.setRemaining(revenueUserPO.getRemaining() - dto.getMoney());
        withdrawUserPO.setAliAccount(dto.getAccount());
        withdrawUserPO.setAliName(dto.getName());
        withdrawUserPO.setWithdrawTime(revenueUserPO.getWithdrawTime() + 1);
        withdrawUserPO.setStatus((byte) 1);
        withdrawUserMapper.insert(withdrawUserPO);

        revenueUserPO.setWithdraw(revenueUserPO.getWithdraw() + dto.getMoney());
        revenueUserPO.setWithdrawTime(revenueUserPO.getWithdrawTime() + 1);
        revenueUserPO.setRemaining(revenueUserPO.getRemaining() - dto.getMoney());
        revenueUserMapper.updateByPrimaryKey(revenueUserPO);

        return new ResultVO<>(1000, revenueUserPO.getRemaining());
    }

    @Override
    public ResultVO queryRecords(IncomeDTO loginInfo) {
        List<WithdrawUserPO> pos = withdrawUserMapper.queryByUserId(loginInfo.getUd());
        if (pos == null || pos.size() == 0) {
            return null;
        }

        List<RecordVO> vos = new ArrayList<>();
        for (WithdrawUserPO po : pos) {
            RecordVO vo = new RecordVO();
            vo.setCtime(po.getCreateTime());
            vo.setAccount(po.getAliAccount());
            vo.setName(po.getAliName());
            vo.setStatus(po.getStatus());
            vo.setMoney(po.getWithdraw());
            vos.add(vo);
        }

        return new ResultVO<>(1000, vos);
    }

    @Override
    public ResultVO queryDetails(IncomeDTO loginInfo) {
        DetailsVO vo = new DetailsVO();

        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(loginInfo.getUd());
        vo.setBalance(po.getRemaining());
        vo.setInvitenum(po.getInviteCount());
        vo.setPaynum(po.getPayCount());
        vo.setTotalmny(po.getTotalRevenue());

        List<InviteUserBO> bos = inviteUserMapper.queryByUserId(loginInfo.getUd());
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

    @Override
    public ResultVO getShareUrl(TokenDTO dto) {
        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(dto.getUd());
        if (po == null) {
            return new ResultVO(2000);
        }

        if (po.getSharecode() == null) {
            po.setSharecode(UUID.randomUUID().toString().replace("-", ""));
            revenueUserMapper.updateByPrimaryKey(po);
        }

        ShareCodeVO vo = new ShareCodeVO();
        vo.setUrl(shareUrl + po.getSharecode());

        return new ResultVO<>(1000, vo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO getDownloadURL(DownLoadDTO dto, HttpServletRequest req) {
        // 获取最新发布应用
        AppPO appPO = appMapper.queryLatestRelease();
        String appUrl = publicPath + appPO.getUrl();
        // 根据shareCode获取userId
        RevenueUserPO revenueUserPO = revenueUserMapper.queryByShareCode(dto.getCode());
        if (null == revenueUserPO) {
            return new ResultVO<>(ErrorCode.SHARE_CODE_ERROR, appUrl);
        }
        // 当前手机是否注册
        UserPO userPO = userMapper.queryByPhone(dto.getPhone());
        if (null != userPO) {
            return new ResultVO<>(ErrorCode.PHONE_REGISTERED, appUrl);
        }
        // 当前手机是否被其他用户邀请
        InviteUserPO inviteUserPO = inviteUserMapper.queryByPhone(dto.getPhone());
        if (null != inviteUserPO && !revenueUserPO.getUserId().equals(inviteUserPO.getUserId())) {
            return new ResultVO<>(ErrorCode.PHONE_INVITED, appUrl);
        }

        if (null == inviteUserPO) {
            // 更新用户邀请收入表
            revenueUserPO.setInviteCount(revenueUserPO.getInviteCount() + 1);
            revenueUserMapper.updateByPrimaryKey(revenueUserPO);
            // 新增用户邀请人详情表
            inviteUserPO = new InviteUserPO();
            inviteUserPO.setUserId(revenueUserPO.getUserId());
            inviteUserPO.setInvitePhone(dto.getPhone());
            inviteUserPO.setCreateTime(new Date());
            inviteUserPO.setIp(RequestUtil.getIpAddr(req));
            inviteUserMapper.insert(inviteUserPO);
        } else {
            // 更新用户邀请人详情表
            inviteUserPO.setIp(RequestUtil.getIpAddr(req));
            inviteUserPO.setUpdateTime(new Date());
            inviteUserMapper.updateByPrimaryKey(inviteUserPO);
        }

        return new ResultVO<>(1000, appUrl);
    }
}
