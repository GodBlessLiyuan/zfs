package com.zfs.web.service;

import com.zfs.web.vo.WhiteDeviceVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:23
 * @description: 测试白名单
 * @version: 1.0
 */
public interface IWhiteDeviceService {

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param phone  phone
     * @param extra 备注
     * @param aId
     * @return
     */
    ResultVO insert(String phone, String extra, int aId);

    /**
     * 删除
     *
     * @param deviceId 设备Id
     * @return
     */
    int deleteByDeviceId(int deviceId);
}
