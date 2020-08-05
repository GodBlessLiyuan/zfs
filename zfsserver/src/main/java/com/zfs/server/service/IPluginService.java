package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.PluginDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:37
 * @description: 插件更新
 * @version: 1.0
 */
public interface IPluginService {
    /**
     * 验证
     *
     * @param dto
     * @return
     */
    ResultVO check(PluginDTO dto);
}
