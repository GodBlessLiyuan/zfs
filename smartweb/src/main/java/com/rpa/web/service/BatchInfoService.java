package com.rpa.web.service;

import com.rpa.web.dto.BatchInfoDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dangyi
 * @date: Created in 19:50 2019/10/8
 * @version: 1.0.0
 * @description: TODO
 */
public interface BatchInfoService {
    DTPageInfo<BatchInfoDTO> query(int draw, int start, int length, String vipkey);

    DTPageInfo<BatchInfoDTO> queryByBatchid(int draw, int start, int length, Integer batchId, Byte status);

    void export(Integer batchId, Byte status, HttpServletResponse response);
}
