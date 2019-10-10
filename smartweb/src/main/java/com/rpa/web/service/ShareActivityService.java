package com.rpa.web.service;

import com.rpa.web.dto.ShareActivityDTO;
import com.rpa.web.utils.DTPageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 10:19 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface ShareActivityService {
    DTPageInfo<ShareActivityDTO> query(int draw, int pageNum, int pageSize, int type);

    int insert(ShareActivityDTO shareActivityDTO, HttpSession httpSession);

    int update(ShareActivityDTO shareActivityDTO, HttpSession httpSession);

    int delete(int materialId);
}
