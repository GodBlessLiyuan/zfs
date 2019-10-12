package com.rpa.web.service;

import com.rpa.web.dto.WithdrawUserDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:06 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface WithdrawUserService {
    DTPageInfo<WithdrawUserDTO> query(int draw, int pageNum, int pageSize, String phone, Byte status);

    ResultVO update(WithdrawUserDTO withdrawUserDTO, HttpSession httpSession);
}
