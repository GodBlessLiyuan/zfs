package com.zfs.server.service;

import com.zfs.common.pojo.PhoneInfoPO;
import com.zfs.common.vo.ResultVO;

/**
 * @author: xiahui
 * @date: Created in 2020/1/9 14:47
 * @description: 手机型号
 * @version: 1.0
 */
public interface IPhoneModelService {
    /**
     * 获取手机型号
     *
     * @return
     */
    ResultVO phoneModel();

    ResultVO queryPhone(Long modelId);

    void addModalInfo(PhoneInfoPO modalResPO);
}
