package com.rpa.web.service;

import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.RoleVO;

/**
 * @author: dangyi
 * @date: Created in 9:52 2019/10/11
 * @version: 1.0.0
 * @description:
 */
public interface RoleService {
    DTPageInfo<RoleVO> query(int draw, int start, int length);
}
