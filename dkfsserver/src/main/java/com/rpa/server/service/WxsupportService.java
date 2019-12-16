package com.rpa.server.service;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.WxsupportDTO;

/**
 * @author: dangyi
 * @date: Created in 9:49 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface WxsupportService {
    ResultVO query(WxsupportDTO dto);
}
