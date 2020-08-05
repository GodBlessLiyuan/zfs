package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.UserActivityDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 18:59
 * @description: 用户活动
 * @version: 1.0
 */
public interface IUserActivityService {
    /**
     * 上传分享活动图片
     *
     * @param dto
     * @return
     */
    ResultVO uploadPic(UserActivityDTO dto);
}
