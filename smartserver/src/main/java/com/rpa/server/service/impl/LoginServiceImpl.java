package com.rpa.server.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.LoginDTO;
import com.rpa.server.mapper.NewUserRecordMapper;
import com.rpa.server.mapper.UserDeviceMapper;
import com.rpa.server.mapper.UserMapper;
import com.rpa.server.pojo.NewUserRecordPO;
import com.rpa.server.pojo.UserDevicePO;
import com.rpa.server.pojo.UserPO;
import com.rpa.server.service.ILoginService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.utils.RequestUtil;
import com.rpa.server.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    private NewUserRecordMapper newUserRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO register(LoginDTO dto, HttpServletRequest req) {
        UserPO userPO = userMapper.queryByPhone(dto.getPh());
        if (userPO == null) {
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

            // 新用户送会员
            NewUserRecordPO newUserRecordPO = new NewUserRecordPO();
            newUserRecordPO.setUserId(po.getUserId());
            newUserRecordPO.setDeviceId(dto.getId());
            newUserRecordPO.setUserDeviceId(userDevPO.getUserDeviceId());
            // t_user_gifts 只有一条数据，其ID为1
            newUserRecordPO.setNugId(1);
            newUserRecordPO.setCreateTime(new Date());
            newUserRecordMapper.insert(newUserRecordPO);

            return this.buildResultVO(userDevPO);
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

        return this.buildResultVO(userDevicePO);
    }

    /**
     * 根据用户信息和用户设备信息，返回结果
     *
     * @param userDevPO
     * @return
     */
    private ResultVO buildResultVO(UserDevicePO userDevPO) {
        LoginVO loginVO = new LoginVO();
        loginVO.setUd(userDevPO.getUserId());
        loginVO.setUm(DigestUtils.md5DigestAsHex(userDevPO.getUserId().toString().getBytes()));
        loginVO.setUdd(userDevPO.getUserDeviceId());
        loginVO.setToken(JWT.create().withAudience(userDevPO.getUserId().toString(), userDevPO.getDeviceId().toString(),
                userDevPO.getUserDeviceId().toString()).sign(Algorithm.HMAC256(userDevPO.getUserId().toString())));

        return new ResultVO<>(1000, loginVO);
    }
}
