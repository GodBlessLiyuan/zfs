package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.WxsupportDTO;

/**
 * @author: dangyi
 * @date: Created in 9:49 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface WxsupportService {
    ResultVO query(WxsupportDTO dto);
}
