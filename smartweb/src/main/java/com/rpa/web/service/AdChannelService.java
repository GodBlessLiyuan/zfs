package com.rpa.web.service;

import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.AdChannelVO;
import com.rpa.web.dto.AdChannelDTO;

import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 18:43 2019/10/11
 * @version: 1.0.0
 * @description:
 */
public interface AdChannelService {
    DTPageInfo<AdChannelVO> query(int draw, int start, int length, int adId, String name, int appId);

    ResultVO update(List<AdChannelDTO> list);

    ResultVO queryVersionname();
}
