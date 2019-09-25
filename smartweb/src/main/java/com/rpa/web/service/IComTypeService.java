package com.rpa.web.service;

import com.rpa.web.pojo.ComTypePO;

import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 10:45
 * @description: 会员中心-产品列表-服务接口
 * @version: 1.0
 */
public interface IComTypeService {

    /**
     * 新增
     */
    void insert(ComTypePO po);

    /**
     * 查询
     * @return
     */
    List<ComTypePO> query();

}
