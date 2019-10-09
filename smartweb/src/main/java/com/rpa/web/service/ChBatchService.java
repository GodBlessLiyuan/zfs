package com.rpa.web.service;

import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 19:48 2019/10/7
 * @version: 1.0.0
 * @description: TODO
 */
public interface ChBatchService {

    DTPageInfo<ChBatchDTO> query(int draw, int pageNum, int pageSize, String chanNickname, String comTypeName, String status, String username);

    int insert(ChBatchDTO chBatchDTO, HttpSession httpSession);

    int update(ChBatchDTO chBatchDTO, HttpSession httpSession);
}
