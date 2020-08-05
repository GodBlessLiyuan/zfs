package com.zfs.web.service;

import com.zfs.web.vo.DeviceVO;
import com.zfs.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 11:45
 * @description: 未注册用户信息
 * @version: 1.0
 */
public interface IUnregisteredService {

    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<DeviceVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
