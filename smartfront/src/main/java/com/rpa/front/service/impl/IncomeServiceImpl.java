package com.rpa.front.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.front.bo.InviteUserBO;
import com.rpa.front.common.ErrorCode;
import com.rpa.front.common.ResultVO;
import com.rpa.front.config.ShortUrlConfig;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.DownLoadDTO;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.dto.base.TokenDTO;
import com.rpa.front.entity.ShortUrlEntity;
import com.rpa.front.mapper.*;
import com.rpa.front.pojo.*;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.utils.RequestUtil;
import com.rpa.front.vo.DetailsVO;
import com.rpa.front.vo.IncomeVO;
import com.rpa.front.vo.RecordVO;
import com.rpa.front.vo.ShareCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 19:16
 * @description: 爱收益
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class IncomeServiceImpl implements IIncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeServiceImpl.class);

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
    @Autowired
    private ShortUrlConfig shortUrlConfig;

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
        if (null != po.getRemaining()) {
            vo.setBalance(po.getRemaining() / 100f);
        }
        vo.setInvitenum(po.getInviteCount());
        vo.setPaynum(po.getPayCount());
        if (null != po.getTotalRevenue()) {
            vo.setTotalmny(po.getTotalRevenue() / 100f);
        }

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
        BigDecimal bd = new BigDecimal(Float.toString(dto.getMoney()));
        withdrawUserPO.setWithdraw(bd.multiply(new BigDecimal("100")).longValue());
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
        if (null != po.getTotalRevenue()) {
            vo.setTotalmny(po.getTotalRevenue() / 100f);
        }

        List<InviteUserBO> bos = inviteUserMapper.queryByUserId(loginInfo.getUd());
        List<DetailsVO.Detail> details = new ArrayList<>();
        if (bos != null) {
            for (InviteUserBO bo : bos) {
                // 手机号中间四位以*代替
                if (null != bo.getInvitePhone()) {
                    DetailsVO.Detail detail = vo.new Detail();
                    StringBuilder sb = new StringBuilder(bo.getInvitePhone());
                    detail.setPh(sb.replace(3, 7, "****").toString());
                    detail.setCtime(bo.getCreateTime());
                    if (null != bo.getEarnings()) {
                        detail.setEarnings(bo.getEarnings() / 100f);
                    } else {
                        detail.setEarnings(0.0f);
                    }
                    details.add(detail);
                }
            }
        }
        vo.setDetails(details);
        return new ResultVO<>(1000, vo);
    }

    @Override
    public ResultVO getShareUrl(TokenDTO dto) {
        RevenueUserPO po = revenueUserMapper.selectByPrimaryKey(dto.getUd());
        if (null == po) {
            return new ResultVO(2000);
        }

        if (null == po.getSharecode()) {
            po.setSharecode(UUID.randomUUID().toString().replace("-", ""));
        }

        if (null == po.getShorturl() || null == po.getEndTime() || new Date().compareTo(po.getEndTime()) > 0) {
            ShortUrlEntity entity = this.longUrl2ShortUrl(shareUrl + po.getSharecode());
            if (null == entity || 0 != entity.getCode()) {
                logger.warn(null == entity ? null : entity.getErrMsg());
                return new ResultVO(2000);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, "1-year".equals(shortUrlConfig.getValidity()) ? 1 : 100);

            po.setShorturl(entity.getShortUrl());
            po.setEndTime(calendar.getTime());
            revenueUserMapper.updateByPrimaryKey(po);
        }

        ShareCodeVO vo = new ShareCodeVO();
        vo.setUrl(po.getShorturl());

        return new ResultVO<>(1000, vo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO getDownloadURL(DownLoadDTO dto, HttpServletRequest req) {
        // 获取最新发布应用
        AppPO appPO = appMapper.queryLatestRelease();
        String appUrl = publicPath + appPO.getUrl();

        if (dto == null) {
            return new ResultVO<>(ErrorCode.SHARE_CODE_ERROR, appUrl);
        }
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


    /**
     * 长链接 转 短链接
     *
     * @param longUrl
     * @return
     */
    private ShortUrlEntity longUrl2ShortUrl(String longUrl) {
        // 请求参数
        String params = "{\"Url\":\"" + longUrl + "\",\"TermOfValidity\":\"" + shortUrlConfig.getValidity() + "\"}";

        try {
            URL url = new URL(shortUrlConfig.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Token", shortUrlConfig.getKey());

            // 发起请求
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            out.append(params);
            out.flush();
            out.close();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            reader.close();
            connection.disconnect();

            return JSON.parseObject(sb.toString(), ShortUrlEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
