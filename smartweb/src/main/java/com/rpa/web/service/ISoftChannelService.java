package com.rpa.web.service;

import com.rpa.web.dto.SoftChannelDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 8:49
 * @description: 渠道信息
 * @version: 1.0
 */
public interface ISoftChannelService {

    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<SoftChannelDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 查询全部数据
     * @return
     */
    List<SoftChannelDTO> queryAll();

    /**
     * 插入
     * @param channelName 渠道名称
     * @param extra 备注
     * @return
     */
    ResultVO insert(String channelName, String extra);
}
