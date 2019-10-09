package com.rpa.web.service;

import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:55 2019/10/7
 * @version: 1.0.0
 * @description:
 */
public interface ChannelService {

    DTPageInfo<ChannelDTO> query(int draw, int pageNum, int pageSize, String chanNickname, String proName);

    int insert(ChannelDTO channelDTO, HttpSession httpSession);
}
