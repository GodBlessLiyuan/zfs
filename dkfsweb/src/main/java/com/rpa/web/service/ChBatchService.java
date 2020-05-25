package com.rpa.web.service;

import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.dto.ChBatchSyncDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.ChBatchVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 19:48 2019/10/7
 * @version: 1.0.0
 * @description: TODO
 */
public interface ChBatchService {

    DTPageInfo<ChBatchVO> query(int draw, int start, int length, String chanNickname, Integer comTypeId, Byte status, String operator);

    ResultVO insert(ChBatchDTO chBatchDTO, HttpSession httpSession);

    ResultVO queryComTypes();

    ResultVO queryChanNicknames();

    ResultVO updateStatus(Integer batchId, Byte status, HttpSession httpSession);

    void export(String chanNickname, Integer comTypeId, Byte status, String operator, HttpServletResponse response);

    ResultVO insertSync(ChBatchSyncDTO chBatchDTO, HttpSession httpSession);
}
