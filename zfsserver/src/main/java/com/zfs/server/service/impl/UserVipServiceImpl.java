package com.zfs.server.service.impl;

import com.zfs.common.mapper.UserDeviceMapper;
import com.zfs.common.mapper.UserVipMapper;
import com.zfs.common.pojo.UserDevicePO;
import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.constant.CommonConstant;
import com.zfs.server.dto.UserVipDTO;
import com.zfs.server.service.IUserVipService;
import com.zfs.server.vo.UserVipVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 18:38
 * @description: 用户会员
 * @version: 1.0
 */
@Service
public class UserVipServiceImpl implements IUserVipService {
    private final static Logger logger = LoggerFactory.getLogger(UserVipServiceImpl.class);

    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private UserDeviceMapper userDeviceMapper;

    @Override
    public ResultVO validate(UserVipDTO dto) {
        UserDevicePO userDevicePO = userDeviceMapper.selectByPrimaryKey(dto.getUdd());
        if (null == userDevicePO) {
            LogUtil.log(logger,"UserVipServiceImpl--validate","没查询到用户设备:{}",dto.getUdd());
            return ResultVO.serverInnerError();
        }
        if (CommonConstant.SIGN_OUT == userDevicePO.getStatus()) {
            // 登出
            return new ResultVO(1012);
        }

        if (!userDevicePO.getDeviceId().equals(dto.getId()) || !userDevicePO.getUserId().equals(dto.getUd())) {
            // 数据有误
            LogUtil.log(logger,"UserVipServiceImpl--validate","设备用户不匹配：用户信息{}，设备信息{}",
                    userDevicePO.getDeviceId()+","+userDevicePO.getUserId(),
                    dto.getId()+","+dto.getUd());
            return ResultVO.serverInnerError();
        }

        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        if (null == userVipPO || CommonConstant.NONE_VIP == userVipPO.getViptypeId()) {
            // 非会员
            return new ResultVO(1005);
        }

        Date date = new Date();
        if (CommonConstant.YEAR_VIP == userVipPO.getViptypeId()) {
            // 判断时间
            if (date.compareTo(userVipPO.getVendTime()) > 0) {
                userVipPO.setViptypeId(CommonConstant.COMM_VIP);
            } else {
                // 年会员
                UserVipVO vo = new UserVipVO();
                vo.setVip(userVipPO.getEndTime());
                vo.setAdvanced(userVipPO.getVendTime());
                return new ResultVO<>(1007, vo);
            }
        }

        if (CommonConstant.COMM_VIP == userVipPO.getViptypeId()) {
            // 判断时间
            if (date.compareTo(userVipPO.getEndTime()) > 0) {
                userVipPO.setViptypeId(CommonConstant.NONE_VIP);
            } else {
                // 普通会员
                UserVipVO vo = new UserVipVO();
                vo.setVip(userVipPO.getEndTime());
                return new ResultVO<>(1006, vo);
            }
        }

        // 更新
        int result = userVipMapper.updateByPrimaryKey(userVipPO);
        if (result == 0) {
            LogUtil.log(logger, "validate", "更新失败", userVipPO);
        }

        return new ResultVO(1005);
    }
}
