package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.NoticeDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:47
 * @description: 通知消息
 * @version: 1.0
 */
public interface INoticeService {
    /**
     * 查询
     * @param dto
     * @return
     */
    ResultVO queryNotice(NoticeDTO dto);
}
