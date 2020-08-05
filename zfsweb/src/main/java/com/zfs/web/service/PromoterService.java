package com.zfs.web.service;

import com.zfs.web.dto.PromoterDTO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.PromoterVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 14:21 2019/9/27
 * @version: 1.0.0
 * @description:
 */
public interface PromoterService {
    DTPageInfo<PromoterVO> query(int draw, int start, int length, String proName, String phone);

    ResultVO insert(PromoterDTO promoterDTO, HttpSession httpSession);

    ResultVO update(PromoterDTO promoterDTO, HttpSession httpSession);
}
