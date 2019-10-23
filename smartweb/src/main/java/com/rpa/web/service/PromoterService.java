package com.rpa.web.service;

import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 14:21 2019/9/27
 * @version: 1.0.0
 * @description:
 */
public interface PromoterService {
    DTPageInfo<PromoterDTO> query(int draw, int pageNum, int pageSize, String proName, String phone);

    ResultVO insert(PromoterDTO promoterDTO, HttpSession httpSession);

    ResultVO update(PromoterDTO promoterDTO, HttpSession httpSession);
}
