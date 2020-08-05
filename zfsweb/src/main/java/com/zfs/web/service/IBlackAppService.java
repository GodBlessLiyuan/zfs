package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.vo.BlankAppVO;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 15:58
 * @description: 应用黑名单
 * @version: 1.0
 */
public interface IBlackAppService {
    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<BlankAppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);


    /**
     * 插入
     *
     * @param packageName 包名
     * @param appName     应用名
     * @param extra       备注
     * @param aId
     * @return
     */
    ResultVO insert(String packageName, String appName, String extra, int aId);

    /**
     * 主键
     *
     * @param blankId 主键
     * @return
     */
    ResultVO delete(long blankId);
}
