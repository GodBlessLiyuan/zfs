package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
/**
 * @author: dangyi
 * @date: Created in 9:52 2019/10/11
 * @version: 1.0.0
 * @description:
 */
public interface RoleService {
    ResultVO query(int pageNum, int pageSize);
}
