package com.rpa.web.service;

import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 19:48 2019/10/7
 * @version: 1.0.0
 * @description: TODO
 */
public interface ChBatchService {

    DTPageInfo<ChBatchDTO> query(int draw, int pageNum, int pageSize, String chanNickname, Integer comTypeId, Byte status, String operator);

    ResultVO insert(ChBatchDTO chBatchDTO, HttpSession httpSession);

    ResultVO queryComTypes();

    ResultVO queryChanNicknames();

    ResultVO updateStatus(Integer batchId, Byte status, HttpSession httpSession);
}
