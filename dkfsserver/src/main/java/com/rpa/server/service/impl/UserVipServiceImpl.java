package com.rpa.server.service.impl;

import com.rpa.common.mapper.UserDeviceMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.pojo.UserDevicePO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.CommonConstant;
import com.rpa.server.dto.UserVipDTO;
import com.rpa.server.service.IUserVipService;
import com.rpa.server.vo.UserVipVO;
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
    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private UserDeviceMapper userDeviceMapper;

    @Override
    public ResultVO validate(UserVipDTO dto) {
        UserDevicePO userDevicePO = userDeviceMapper.selectByPrimaryKey(dto.getUdd());
        if (null == userDevicePO) {
            return new ResultVO(2000);
        }
        if (CommonConstant.SIGN_OUT == userDevicePO.getStatus()) {
            // 登出
            return new ResultVO(1012);
        }

        if (!userDevicePO.getDeviceId().equals(dto.getId()) || !userDevicePO.getUserId().equals(dto.getUd())) {
            // 数据有误
            return new ResultVO(2000);
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
        userVipMapper.updateByPrimaryKey(userVipPO);

        return new ResultVO(1005);
    }
}
