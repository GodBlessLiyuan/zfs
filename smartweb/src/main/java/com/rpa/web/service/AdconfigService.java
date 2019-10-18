package com.rpa.web.service;

import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:37 2019/9/25
 * @version: 1.0.0
 * @description: 开屏广告
 */
public interface AdconfigService {
    public DTPageInfo<AdconfigDTO> query(int draw, int pageNum, int pageSize, String name, String adNumber, Byte status);

    ResultVO insert(AdconfigDTO adconfigDTO, HttpSession httpSession);

    ResultVO update(AdconfigDTO adconfigDTO, HttpSession httpSession);

    ResultVO delete(int adId);

    ResultVO updateStatus(Integer adId, HttpSession httpSession);

    ResultVO updateStrategy(String show_interval);

    ResultVO queryStrategy(int showInterval);

    ResultVO queryById(int id);
}
