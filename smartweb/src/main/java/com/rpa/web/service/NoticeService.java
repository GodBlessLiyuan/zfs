package com.rpa.web.service;

import com.rpa.web.dto.NoticeDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:47 2019/9/26
 * @version: 1.0.0
 * @description: TODO
 */
public interface NoticeService {
    DTPageInfo<NoticeDTO> query(int draw, int start, int length, String startTime, String endTime, Integer status, Byte type, String title);


    ResultVO delete(Integer noticeId);

    ResultVO updateStatus(Integer noticeId, Integer status, HttpSession httpSession);

    ResultVO insert(Byte type, String text, MultipartFile picurl, String title, String url, String showTime, String startTime, String endTime, HttpSession httpSession);
}
