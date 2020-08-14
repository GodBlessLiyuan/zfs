package com.zfs.server.service;

import com.zfs.common.pojo.UserPO;
import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.UserVipDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 18:38
 * @description: 用户会员
 * @version: 1.0
 */
public interface IUserVipService {
    /**
     * 验证
     * @param dto
     * @return
     */
    ResultVO validate(UserVipDTO dto);

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    UserVipPO queryByUserId(long uid);

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    UserPO selectByPrimaryKey(long uid);
}
