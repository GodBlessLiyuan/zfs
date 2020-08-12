package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dangyi
 * @date: Created in 19:50 2019/10/8
 * @version: 1.0.0
 * @description: TODO
 */
public interface BatchInfoService {
    ResultVO query( int start, int length, String vipkey);

    ResultVO queryByBatchid( int start, int length, Integer batchId, Byte status);

    void export(Integer batchId, Byte status, HttpServletResponse response);
}
