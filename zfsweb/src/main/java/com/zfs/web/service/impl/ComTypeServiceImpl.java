package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.ComTypeBO;
import com.zfs.common.mapper.ComTypeMapper;
import com.zfs.common.pojo.ComTypePO;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IComTypeService;
import com.zfs.web.vo.ComTypeVO;
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
    private ComTypeMapper comTypeMapper;

    @Override
    public ResultVO insert(String name, int days, String extra, int aId) {
        Integer s = comTypeMapper.exist(name,days);
        if(s!=null&&s>0){
            return new ResultVO(3006);
        }
        ComTypePO po = new ComTypePO();
        po.setName(name);
        po.setDays(days);
        po.setExtra(extra);
        po.setaId(aId);
        po.setCreateTime(new Date());

        comTypeMapper.insert(po);

        return new ResultVO(1000);
    }

    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {

        Page<ComTypeBO> page = PageHelper.startPage(pageNum, pageSize);
        List<ComTypeBO> data = comTypeMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), ComTypeVO.convert(data)));
    }

    @Override
    public ResultVO queryAll() {
        List<ComTypeBO> bos = comTypeMapper.queryAll();
        return new ResultVO(1000, ComTypeVO.convert(bos));
    }
}
