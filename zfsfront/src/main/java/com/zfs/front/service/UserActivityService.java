package com.zfs.front.service;

import com.zfs.front.dto.UserActivityDTO;

/**
 * @author: dangyi
 * @date: Created in 18:54 2019/11/4
 * @version: 1.0.0
 * @description:
 */
public interface UserActivityService {
    Integer query(UserActivityDTO dto);
}
