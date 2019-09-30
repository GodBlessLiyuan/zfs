package com.rpa.web.service;

import com.rpa.web.dto.OtherAppDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:02
 * @description: 其他产品
 * @version: 1.0
 */
public interface IOtherAppService {

    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<OtherAppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param oName        应用名称
     * @param extra        应用简介
     * @param iconUrl      应用图标
     * @param downloadType 下载方式
     * @param appUrl       下载地址
     * @return
     */
    int insert(String oName, String extra, String iconUrl, byte downloadType, String appUrl);

    /**
     * 删除
     * @param oId 主键
     * @return
     */
    int delete(int oId);
}
