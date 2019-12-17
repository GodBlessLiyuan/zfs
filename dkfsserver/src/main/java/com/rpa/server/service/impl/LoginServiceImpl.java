package com.rpa.server.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rpa.common.mapper.UserDeviceMapper;
import com.rpa.common.mapper.UserGiftsMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.pojo.*;
import com.rpa.common.utils.DateUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.LoginDTO;
import com.rpa.server.mapper.*;
import com.rpa.server.service.ILoginService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.utils.RequestUtil;
import com.rpa.server.utils.UserVipUtil;
import com.rpa.server.vo.LoginVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 11:13
 * @description: 注册/登录
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisCacheUtil cache;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDeviceMapper userDeviceMapper;
    @Resource
    private UserGiftsMapper userGiftsMapper;
    @Resource
    private NewUserRecordMapper newUserRecordMapper;
    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private GodinsecUserMapper godinsecUserMapper;

    @Autowired
    private AmqpTemplate template;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO register(LoginDTO dto, HttpServletRequest req) {
        UserPO userPO = userMapper.queryByPhone(dto.getPh());
        // 前端是否出弹框
        byte gift = 0;
        // 新用户送会员天数
        int days = 0;
        if (null == userPO) {
            // 注册
            // 新增用户
            UserPO po = new UserPO();
            po.setPhone(dto.getPh());
            po.setChanName(dto.getChannel());
            po.setSoftChannelId(cache.getSoftChannelId(dto.getChannel()));
            po.setCreateTime(new Date());
            po.setIp(RequestUtil.getIpAddr(req));
            userMapper.insert(po);

            // 登出当前设备所有在线用户
            userDeviceMapper.signOutByDevId(dto.getId());

            // 新增用户-设备
            UserDevicePO userDevPO = new UserDevicePO();
            userDevPO.setDeviceId(dto.getId());
            userDevPO.setUserId(po.getUserId());
            userDevPO.setStatus((byte) 1);
            userDevPO.setCreateTime(new Date());
            userDeviceMapper.insert(userDevPO);

            // 新用户是否送会员
            List<UserGiftsPO> userGiftsPOs = userGiftsMapper.queryOpenGift();
            if (null != userGiftsPOs && userGiftsPOs.size() == 1) {
                // 新用户送会员
                UserGiftsPO userGiftsPO = userGiftsPOs.get(0);

                NewUserRecordPO newUserRecordPO = new NewUserRecordPO();
                newUserRecordPO.setUserId(po.getUserId());
                newUserRecordPO.setDeviceId(dto.getId());
                newUserRecordPO.setUserDeviceId(userDevPO.getUserDeviceId());
                newUserRecordPO.setNugId(userGiftsPO.getNugId());
                newUserRecordPO.setCreateTime(new Date());
                newUserRecordMapper.insert(newUserRecordPO);

                gift = 1;
                days = userGiftsPO.getDays();
            }

            // 微商神器送会员
            GodinsecUserPO godinsecUserPO = godinsecUserMapper.selectByPrimaryKey(dto.getPh());
            int godDays = 0;
            if (null != godinsecUserPO) {
                godDays = DateUtil.daysApart(new Date(), godinsecUserPO.getEndTime());
                godinsecUserPO.setUpdateTime(new Date());
                godinsecUserPO.setDays(godDays);
                godinsecUserPO.setStatus((byte) 2);
                godinsecUserMapper.updateByPrimaryKey(godinsecUserPO);
            }

            if (days + godDays > 0) {
                // 更新会员天数
                UserVipPO userVipPO = UserVipUtil.buildUserVipVO(null, po.getUserId(), days + godDays, false);
                userVipMapper.insert(userVipPO);
            }

            // 事务提交完成后，发送消息
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                          @Override
                                                                          public void afterCommit() {
                                                                              // RabbitMQ
                                                                              template.convertAndSend("register", dto.getPh());
                                                                          }
                                                                      }
            );

            return this.buildResultVO(userDevPO, gift, days);
        }

        // 登录
        // 登出当前设备所有在线用户
        userDeviceMapper.signOutByDevId(dto.getId());
        // 登出当前用户所有在线设备
        userDeviceMapper.signOutByUserId(userPO.getUserId());

        // 更新当前用户信息
        userPO.setIp(RequestUtil.getIpAddr(req));
        userPO.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(userPO);

        UserDevicePO userDevicePO = userDeviceMapper.queryByDevIdAndUserId(dto.getId(), userPO.getUserId());
        if (userDevicePO == null) {
            // 新增用户-设备关系
            userDevicePO = new UserDevicePO();
            userDevicePO.setDeviceId(dto.getId());
            userDevicePO.setUserId(userPO.getUserId());
            userDevicePO.setStatus((byte) 1);
            userDevicePO.setCreateTime(new Date());
            userDeviceMapper.insert(userDevicePO);
        } else {
            userDevicePO.setStatus((byte) 1);
            userDeviceMapper.updateByPrimaryKey(userDevicePO);
        }

        return this.buildResultVO(userDevicePO, gift, days);
    }

    /**
     * 根据用户信息和用户设备信息，返回结果
     *
     * @param userDevPO
     * @param gift
     * @return
     */
    private ResultVO buildResultVO(UserDevicePO userDevPO, byte gift, int days) {
        LoginVO loginVO = new LoginVO();
        loginVO.setUd(userDevPO.getUserId());
        loginVO.setUm(DigestUtils.md5DigestAsHex(userDevPO.getUserId().toString().getBytes()));
        loginVO.setUdd(userDevPO.getUserDeviceId());
        loginVO.setToken(JWT.create().withAudience(userDevPO.getUserId().toString(), userDevPO.getDeviceId().toString(),
                userDevPO.getUserDeviceId().toString()).sign(Algorithm.HMAC256(userDevPO.getUserId().toString())));
        loginVO.setGift(gift);
        loginVO.setDays(days);

        return new ResultVO<>(1000, loginVO);
    }
}
