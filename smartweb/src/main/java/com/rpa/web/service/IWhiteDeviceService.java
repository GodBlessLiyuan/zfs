package com.rpa.web.service;

import com.rpa.web.dto.WhiteDeviceDTO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

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
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<WhiteDeviceDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param imei  imei
     * @param extra 备注
     * @param aId
     * @return
     */
    ResultVO insert(String imei, String extra, int aId);

    /**
     * 删除
     *
     * @param deviceId 设备Id
     * @return
     */
    int deleteByDeviceId(int deviceId);
}
