package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.ShareactivityDTO;

/**
 * @author: dangyi
 * @date: Created in 11:19 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface ShareactivityService {
    ResultVO query(ShareactivityDTO dto);
}
