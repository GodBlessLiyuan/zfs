package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.AppDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:59
 * @description: 版本更新
 * @version: 1.0
 */
public interface IAppService {
    /**
     * 校验
     *
     * @param dto
     * @param req
     * @return
     */
    ResultVO check(AppDTO dto, HttpServletRequest req);
}
