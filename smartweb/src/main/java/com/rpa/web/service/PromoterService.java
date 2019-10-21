package com.rpa.web.service;

import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

/**
 * @author: dangyi
 * @date: Created in 14:21 2019/9/27
 * @version: 1.0.0
 * @description: TODO
 */
public interface PromoterService {
    DTPageInfo<PromoterDTO> query(int draw, int pageNum, int pageSize, String proName, String phone);

    ResultVO insert(PromoterDTO promoterDTO);

    ResultVO update(PromoterDTO promoterDTO);
}
