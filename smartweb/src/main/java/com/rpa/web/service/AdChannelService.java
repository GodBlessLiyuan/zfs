package com.rpa.web.service;

import com.rpa.web.dto.AdChannelDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 18:43 2019/10/11
 * @version: 1.0.0
 * @description:
 */
public interface AdChannelService {
    DTPageInfo<AdChannelDTO> query(int draw, int pageNum, int pageSize, int adId, String name, int appId);

    ResultVO update(List<AdChannelDTO> adChannelDTOs);
}
