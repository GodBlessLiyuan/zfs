package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zfs.common.mapper.*;
import com.zfs.common.pojo.*;
import com.zfs.common.utils.DateUtil;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.LoginDTO;
import com.zfs.server.service.ILoginService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.utils.RequestUtil;
import com.zfs.server.utils.UserVipUtil;
import com.zfs.server.utils.VerifyUtil;
import com.zfs.server.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

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
    private DeviceMapper deviceMapper;
    @Autowired
    private RegisterUserMapper registerUserMapper;
    @Autowired
    private AmqpTemplate template;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO register(LoginDTO dto, HttpServletRequest req) {
        // 短信码
        int code = cache.checkSmsByCache(dto.getPh(), dto.getSms());
        if (1000 != code) {
            return new ResultVO(code);
        }

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
            po.setRandomStr(dto.getRandomStr());
            int result1 = userMapper.insert(po);
            if (result1 == 0) {
                LogUtil.log(logger, "insert", "新增用户失败", po);
            }
            RegisterUserPO registerUserPO=new RegisterUserPO();
            registerUserPO.setPhone(po.getPhone());
            registerUserPO.setCreateTime(new Date());
            registerUserPO.setChanName(dto.getChannel());
            DevicePO devicePO = deviceMapper.selectByPrimaryKey(dto.getId());//设备信息
            if(devicePO!=null){
                registerUserPO.setVersionname(devicePO.getVersionname());
                registerUserPO.setBuildrelease(devicePO.getBuildrelease());
                registerUserPO.setManufacturer(devicePO.getManufacturer());
                registerUserPO.setAndroidmodel(devicePO.getAndroidmodel());
                registerUserMapper.insertSelective(registerUserPO);
            }else{
                devicePO=null;
                registerUserPO=null;
                logger.info("注册用户设备时数据库不存在该设备号："+JSON.toJSONString(dto));
            }

            // 登出当前设备所有在线用户
            userDeviceMapper.signOutByDevId(dto.getId());

            // 新增用户-设备
            UserDevicePO userDevPO = new UserDevicePO();
            userDevPO.setDeviceId(dto.getId());
            userDevPO.setUserId(po.getUserId());
            userDevPO.setStatus((byte) 1);
            userDevPO.setCreateTime(new Date());
            int result2 = userDeviceMapper.insert(userDevPO);
            if (result2 == 0) {
                LogUtil.log(logger, "insert", "新增用户设备失败", userDevPO);
            }

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
                int result3 = newUserRecordMapper.insert(newUserRecordPO);
                if (result3 == 0) {
                    LogUtil.log(logger, "insert", "新增新用户失败", userDevPO);
                }
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
                int result4 = godinsecUserMapper.updateByPrimaryKey(godinsecUserPO);
                if (result4 == 0) {
                    LogUtil.log(logger, "insert", "微商神器送会员新增失败", godinsecUserPO);
                }
            }

            if (days + godDays > 0) {
                // 更新会员天数
                UserVipPO userVipPO = UserVipUtil.buildUserVipVO(null, po.getUserId(), days + godDays, false);
                int result5 = userVipMapper.insert(userVipPO);
                if (result5 == 0) {
                    LogUtil.log(logger, "insert", "更新会员天数失败", userVipPO);
                }
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
        userPO.setRandomStr(dto.getRandomStr());
        int result6 = userMapper.updateByPrimaryKey(userPO);
        if (result6 == 0) {
            LogUtil.log(logger, "insert", "更新当前用户信息失败", userPO);
        }

        UserDevicePO userDevicePO = userDeviceMapper.queryByDevIdAndUserId(dto.getId(), userPO.getUserId());
        int result7;
        if (userDevicePO == null) {
            // 新增用户-设备关系
            userDevicePO = new UserDevicePO();
            userDevicePO.setDeviceId(dto.getId());
            userDevicePO.setUserId(userPO.getUserId());
            userDevicePO.setStatus((byte) 1);
            userDevicePO.setCreateTime(new Date());
            result7 = userDeviceMapper.insert(userDevicePO);
        } else {
            userDevicePO.setStatus((byte) 1);
            result7 = userDeviceMapper.updateByPrimaryKey(userDevicePO);
        }
        if (result7 == 0) {
            LogUtil.log(logger, "insert", "新增用户设备关系失败", userDevicePO);
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
    private ResultVO buildResultVO(UserDevicePO userDevPO, Byte gift, Integer days) {
        LoginVO loginVO = new LoginVO();
        loginVO.setUd(userDevPO.getUserId());
        loginVO.setUm(DigestUtils.md5DigestAsHex(userDevPO.getUserId().toString().getBytes()));
        loginVO.setUdd(userDevPO.getUserDeviceId());
        Date iatDate = new Date();
        Date expiresDate = VerifyUtil.geneTokenExpire();
        loginVO.setToken(JWT.create().withAudience(userDevPO.getUserId().toString(), userDevPO.getDeviceId().toString(),
                    userDevPO.getUserDeviceId().toString()).
                withIssuedAt(iatDate). // sign time 签发时间
                withExpiresAt(expiresDate). // expire time
                sign(Algorithm.HMAC256(userDevPO.getUserId().toString())));
        loginVO.setGift(gift);
        loginVO.setDays(days);

        return new ResultVO<>(1000, loginVO);
    }

    @Override
    public ResultVO regettoken(LoginDTO dto, HttpServletRequest req) {
        UserPO userPO = userMapper.selectByPrimaryKey(dto.getUd());
        if(userPO==null||userPO.getUserId()==null){
            return ResultVO.paramsError();
        }
        if(!userPO.getRandomStr().equals(dto.getRandomStr())){
            return ResultVO.paramsError();//随机数不同，则不再发送token
        }
        // 前端是否出弹框
        Byte gift = null;
        // 新用户送会员天数
        Integer days = null;
        UserDevicePO userDevicePO = userDeviceMapper.queryByDevIdAndUserId(dto.getId(), userPO.getUserId());
        if(userDevicePO==null){
            return ResultVO.paramsError();
        }
        return this.buildResultVO(userDevicePO, gift, days);
    }

    @Override
    public ResultVO logout(LoginDTO dto, HttpServletRequest req) {
        UserPO userPO = userMapper.selectByPrimaryKey(dto.getUd());
        if(userPO==null){
            return ResultVO.paramsError();
        }
        // 登出当前设备所有在线用户
        userDeviceMapper.signOutByDevId(dto.getId());
        // 登出当前用户所有在线设备
        userDeviceMapper.signOutByUserId(userPO.getUserId());
        userPO.setRandomStr("");//
        userMapper.updateByPrimaryKey(userPO);
        return new ResultVO(1000);
    }
}
