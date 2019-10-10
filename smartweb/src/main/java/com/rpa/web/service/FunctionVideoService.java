package com.rpa.web.service;

import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 16:18 2019/10/9
 * @version: 1.0.0
 * @description:
 */
public interface FunctionVideoService {
    DTPageInfo<FunctionVideoDTO> query(int draw, int pageNum, int pageSize, String funName);

    int insert(FunctionVideoDTO functionVideoDTO, HttpSession httpSession);
}
