package com.rpa.web.service;

import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.BannerConfigVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:52 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface BannerConfigService {
    DTPageInfo<BannerConfigVO> query(int draw, int start, int length, String name, Byte status);

    ResultVO update(Integer bannerId, Byte status, HttpSession httpSession);

    ResultVO delete(int bannerId);

    ResultVO insert(String name, MultipartFile picPath, String url, HttpSession httpSession);
}
