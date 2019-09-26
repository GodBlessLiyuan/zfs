package com.rpa.web.service.impl;

import com.rpa.web.mapper.ComTypeMapper;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.service.IComTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void insert(String name, int days, String extra, int aId) {
        ComTypePO po = new ComTypePO();
        po.setName(name);
        po.setDays(days);
        po.setExtra(extra);
        po.setaId(aId);
        po.setCreateTime(new Date());

        mapper.insert(po);
    }

    @Override
    public List<ComTypePO> query(Map<String, Object> map) {
        return mapper.query(map);
    }
}
