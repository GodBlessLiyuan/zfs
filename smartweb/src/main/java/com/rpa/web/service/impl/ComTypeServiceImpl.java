package com.rpa.web.service.impl;

import com.rpa.web.mapper.ComTypeMapper;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.service.IComTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 10:44
 * @description: 会员中心-产品列表-服务实现
 * @version: 1.0
 */
@Service
public class ComTypeServiceImpl implements IComTypeService {

    @Resource
    private ComTypeMapper mapper;

    @Override
    public void insert(ComTypePO po) {
        mapper.insert(po);
    }

    @Override
    public List<ComTypePO> query() {
        return mapper.query();
    }
}
