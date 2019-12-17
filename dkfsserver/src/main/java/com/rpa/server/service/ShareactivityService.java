package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ShareactivityDTO;

/**
 * @author: dangyi
 * @date: Created in 11:19 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface ShareactivityService {
    ResultVO query(ShareactivityDTO dto);
}
