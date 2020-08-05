package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 11:13
 * @description: 注册/登录
 * @version: 1.0
 */
public interface ILoginService {
    /**
     * 注册/登录服务接口
     * @param dto 前端参数
     * @param req 请求
     * @return
     */
    ResultVO register(LoginDTO dto, HttpServletRequest req);

    ResultVO regettoken(LoginDTO dto, HttpServletRequest req);

    ResultVO logout(LoginDTO dto, HttpServletRequest req);
}
