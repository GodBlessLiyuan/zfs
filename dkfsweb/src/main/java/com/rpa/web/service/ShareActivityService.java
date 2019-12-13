package com.rpa.web.service;

import com.rpa.web.vo.ShareActivityVO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 10:19 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface ShareActivityService {
    DTPageInfo<ShareActivityVO> query(int draw, int start, int length, Byte type);
    
    ResultVO delete(int materialId);

    ResultVO queryById(Integer materialId);

    ResultVO update(Integer materialId, Byte type, String contentText, MultipartFile contentImage, String extra, HttpSession httpSession);

    ResultVO insert(Byte type, String contentText, MultipartFile contentImage, String extra, HttpSession httpSession);
}