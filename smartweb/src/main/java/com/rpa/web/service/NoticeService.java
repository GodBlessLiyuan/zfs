package com.rpa.web.service;

import com.rpa.web.dto.NoticeDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 18:47 2019/9/26
 * @version: 1.0.0
 * @description: TODO
 */
public interface NoticeService {
    DTPageInfo<NoticeDTO> query(int draw, int pageNum, int pageSize, Date startTime, Date endTime, Integer status, Byte type, String title);

    int insert(NoticeDTO noticeDTO);

    int delete(int id);
}
