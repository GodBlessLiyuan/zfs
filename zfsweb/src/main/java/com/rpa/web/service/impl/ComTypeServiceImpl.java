package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.ComTypeBO;
import com.rpa.common.mapper.ComTypeMapper;
import com.rpa.common.pojo.ComTypePO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.ComTypeVO;
import com.rpa.web.service.IComTypeService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
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
    public ResultVO insert(String name, int days, String extra, int aId) {
        ComTypePO po = new ComTypePO();
        po.setName(name);
        po.setDays(days);
        po.setExtra(extra);
        po.setaId(aId);
        po.setCreateTime(new Date());

        mapper.insert(po);

        return new ResultVO(1000);
    }

    @Override
    public DTPageInfo<ComTypeVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {

        Page<ComTypeBO> page = PageHelper.startPage(pageNum, pageSize);
        List<ComTypeBO> data = mapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), ComTypeVO.convert(data));
    }

    @Override
    public List<ComTypeVO> queryAll() {
        List<ComTypeBO> bos = mapper.queryAll();
        return ComTypeVO.convert(bos);
    }
}
