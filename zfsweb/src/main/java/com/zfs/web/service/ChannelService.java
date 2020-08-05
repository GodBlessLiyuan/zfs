package com.zfs.web.service;

import com.zfs.web.dto.ChannelDTO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.ChannelVO;

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
