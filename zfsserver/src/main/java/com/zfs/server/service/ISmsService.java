package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.SmsDTO;

/**
 * @author: velve
 * @date: Created in 2019/10/18 10:23
 * @description: 发送短信模块
 * @version: 1.0
 */
public interface ISmsService {
    int sendSMS(String phone);

    /**
     * 发送验证码
     *
     * @param ud
     */
    ResultVO sendSMS(Long ud);

    /**
     * 验证验证码
     *
     * @param code
     * @return
     */
    ResultVO validateSMS(SmsDTO code);
}
