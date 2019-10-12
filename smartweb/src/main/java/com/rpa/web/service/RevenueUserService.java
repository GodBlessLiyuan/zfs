package com.rpa.web.service;

import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.utils.DTPageInfo;

/**
 * @author: dangyi
 * @date: Created in 10:52 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface RevenueUserService {
    DTPageInfo<RevenueUserDTO> query(int draw, int pageNum, int pageSize, String phone, int orderBy);
}
