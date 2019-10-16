package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.constant.CommonConstant;
import com.rpa.server.dto.UserVipDTO;
import com.rpa.server.mapper.UserDeviceMapper;
import com.rpa.server.mapper.UserVipMapper;
import com.rpa.server.pojo.UserDevicePO;
import com.rpa.server.pojo.UserVipPO;
import com.rpa.server.service.IUserVipService;
import com.rpa.server.vo.UserVipVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        if (CommonConstant.SIGN_OUT == userDevicePO.getStatus()) {
            // 登出
            return new ResultVO(1012);
        }

        if (!userDevicePO.getDeviceId().equals(dto.getId()) || !userDevicePO.getUserId().equals(dto.getUd())) {
            // 数据有误
            return new ResultVO(2000);
        }

        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        if (userVipPO == null) {
            // 非会员
            return new ResultVO(1005);
        }
        if (userVipPO.getViptypeId() == CommonConstant.COMM_VIP) {
            // 普通会员
            UserVipVO vo = new UserVipVO();
            vo.setVip(userVipPO.getEndTime());
            return new ResultVO<>(1006, vo);
        }
        if (userVipPO.getViptypeId() == CommonConstant.YEAR_VIP) {
            // 年会员
            UserVipVO vo = new UserVipVO();
            vo.setVip(userVipPO.getEndTime());
            vo.setAdvanced(userVipPO.getVendTime());
            return new ResultVO<>(1007, vo);
        }

        return new ResultVO(2000);
    }
}
