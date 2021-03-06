package com.zfs.web.service;

import com.zfs.web.vo.WxSupportVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:11
 * @description: 微信白名单
 * @version: 1.0
 */
public interface IWxSupportService {

    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<WxSupportVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);


    /**
     * 插入
     *
     * @param packageName 包名
     * @param extra       备注
     * @param aId
     * @return
     */
    ResultVO insert(String packageName, String extra, int aId);

    /**
     * 主键
     *
     * @param wId 主键
     * @return
     */
    int delete(int wId);

}
