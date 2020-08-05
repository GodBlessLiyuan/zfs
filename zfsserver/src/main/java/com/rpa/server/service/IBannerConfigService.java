package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BannerConfigDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:10
 * @description: banner广告信息
 * @version: 1.0
 */
public interface IBannerConfigService {
    /**
     * 查询 BannerConfig信息
     *
     * @param dto
     * @return
     */
    ResultVO queryBanConf(BannerConfigDTO dto);
}
