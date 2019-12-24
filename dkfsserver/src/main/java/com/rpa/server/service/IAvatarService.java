package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.AvatarDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/12/24 15:01
 * @description: 分身应用
 * @version: 1.0
 */
public interface IAvatarService {

    /**
     * 检查更新
     *
     * @param dto
     * @param req
     * @return
     */
    ResultVO check(AvatarDTO dto, HttpServletRequest req);
}
