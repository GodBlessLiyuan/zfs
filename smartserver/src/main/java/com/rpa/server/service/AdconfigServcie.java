package com.rpa.server.service;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AdconfigDTO;

/**
 * @author: dangyi
 * @date: Created in 14:59 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface AdconfigServcie {
    ResultVO query(AdconfigDTO dto);
}
