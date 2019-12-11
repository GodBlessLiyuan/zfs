package com.rpa.web.service;

import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 16:18 2019/10/9
 * @version: 1.0.0
 * @description:
 */
public interface FunctionVideoService {
    DTPageInfo<FunctionVideoDTO> query(int draw, int start, int length, String funName);

    ResultVO queryById(Integer functionId);


    ResultVO delete(Integer functionId);

    ResultVO insert(HttpSession httpSession, String funName, MultipartFile url, String extra);

    ResultVO update(HttpSession httpSession, Integer functionId, String funName, MultipartFile url, String extra);
}
