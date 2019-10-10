package com.rpa.web.service;

import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:37 2019/9/25
 * @version: 1.0.0
 * @description: 开屏广告
 */
public interface AdconfigService {
    public DTPageInfo<AdconfigDTO> query(int draw, int pageNum, int pageSize, String name, String adNumber, Byte status);

    int insert(AdconfigDTO adconfigDTO, HttpSession httpSession);

    int update(AdconfigDTO adconfigDTO, HttpSession httpSession);

    int delete(int adId);

    int updateStatus(AdconfigDTO adconfigDTO, HttpSession httpSession);

    int updateStrategy(int show_interval);
}
