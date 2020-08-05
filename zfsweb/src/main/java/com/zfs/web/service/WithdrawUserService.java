package com.zfs.web.service;

import com.zfs.web.vo.WithdrawUserVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:06 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface WithdrawUserService {
    DTPageInfo<WithdrawUserVO> query(int draw, int start, int length, String phone, Byte status);

    ResultVO update(Integer withdrawId, Byte status, HttpSession httpSession);
}
