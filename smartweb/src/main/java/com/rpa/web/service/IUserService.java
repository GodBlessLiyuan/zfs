package com.rpa.web.service;

import com.rpa.web.pojo.UserPO;

import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:00
 * @description: TODO
 * @version: 1.0
 */
public interface IUserService {

    /**
     * 分页查询
     * @return
     */
    List<UserPO> list();
}
