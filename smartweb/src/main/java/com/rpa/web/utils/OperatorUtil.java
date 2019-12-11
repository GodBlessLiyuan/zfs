package com.rpa.web.utils;

import com.rpa.common.dto.AdminUserDTO;
import com.rpa.common.constant.Constant;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:20 2019/12/11
 * @version: 1.0.0
 * @description: 获取当前登录用户的ID
 */
public class OperatorUtil {

    /**
     * 从session中获取当前用户的a_id
     * 能从session中获取用户的信息，说明当前用户是登录状态
     */
    public static int getOperatorId(HttpSession httpSession) {
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();
        return aId;
    }
}
