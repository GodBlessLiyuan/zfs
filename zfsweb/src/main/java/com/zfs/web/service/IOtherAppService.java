package com.zfs.web.service;

import com.zfs.web.vo.OtherAppVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

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
    DTPageInfo<OtherAppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param oName        应用名称
     * @param extra        应用简介
     * @param iconUrl      应用图标
     * @param downloadType 下载方式
     * @param appUrl       下载地址
     * @param apkUrl
     * @param aId          管理员Id
     * @return
     */
    ResultVO insert(String oName, String extra, MultipartFile iconUrl, byte downloadType, String appUrl, MultipartFile apkUrl, int aId);

    /**
     * 删除
     *
     * @param oId 主键
     * @return
     */
    int delete(int oId);
}
