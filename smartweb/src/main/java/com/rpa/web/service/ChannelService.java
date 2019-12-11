package com.rpa.web.service;

import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.ChannelVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:55 2019/10/7
 * @version: 1.0.0
 * @description:
 */
public interface ChannelService {

    DTPageInfo<ChannelVO> query(int draw, int start, int length, String chanNickname, Integer proId);

    ResultVO insert(ChannelDTO channelDTO, HttpSession httpSession);

    ResultVO queryProNames();

    ResultVO queryAllProNames();
}
