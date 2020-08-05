package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.AvatarDTO;

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
     * @return
     */
    ResultVO check(AvatarDTO dto);
}
