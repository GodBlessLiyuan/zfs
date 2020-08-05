package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.FunctionvideoDTO;

/**
 * @author: dangyi
 * @date: Created in 14:39 2019/10/29
 * @version: 1.0.0
 * @description:
 */
public interface FunctionvideoService {
    ResultVO query(FunctionvideoDTO dto);
}
