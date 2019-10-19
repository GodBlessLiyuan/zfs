package com.rpa.web.service;

import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 16:18 2019/10/9
 * @version: 1.0.0
 * @description:
 */
public interface FunctionVideoService {
    DTPageInfo<FunctionVideoDTO> query(int draw, int pageNum, int pageSize, String funName);

    ResultVO insert(FunctionVideoDTO functionVideoDTO, HttpSession httpSession);

    ResultVO queryById(Integer functionId);

    ResultVO update(FunctionVideoDTO functionVideoDTO, HttpSession httpSession);

    ResultVO delete(Integer functionId);
}
