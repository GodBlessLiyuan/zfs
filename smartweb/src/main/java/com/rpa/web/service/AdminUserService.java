package com.rpa.web.service;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

public interface AdminUserService {

    DTPageInfo<AdminUserDTO> query(int draw, int pageNum, int pageSize, String phone, String extra);

    ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO delete(Integer aId);

    ResultVO queryAllRoles();

    ResultVO queryById(Integer aId);
}
