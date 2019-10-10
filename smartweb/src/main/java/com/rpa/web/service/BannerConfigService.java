package com.rpa.web.service;

import com.rpa.web.dto.BannerConfigDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:52 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface BannerConfigService {
    DTPageInfo<BannerConfigDTO> query(int draw, int pageNum, int pageSize, String name, Byte status);

    int insert(BannerConfigDTO bannerConfigDTO, HttpSession httpSession);

    int update(BannerConfigDTO bannerConfigDTO, HttpSession httpSession);

    int delete(int bannerId);
}
