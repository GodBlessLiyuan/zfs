package com.zfs.web.service;

import com.zfs.web.vo.ComTypeVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 10:45
 * @description: 产品列表接口
 * @version: 1.0
 */
public interface IComTypeService {

    /**
     * 新增
     */
    /**
     * 新增
     *  @param name  产品类型
     * @param days  产品天数
     * @param extra 备注信息
     * @param aId   管理员Id
     * @return
     */
    ResultVO insert(String name, int days, String extra, int aId);

    /**
     * 查询
     *
     * @param draw     draw
     * @param pageNum  页面下标
     * @param pageSize 页面大小
     * @param reqData  请求数据
     * @return
     */
    DTPageInfo<ComTypeVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 查询所有数据
     * @return
     */
    List<ComTypeVO> queryAll();
}
