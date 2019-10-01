package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.AppDTO;
import com.rpa.web.mapper.AppMapper;
import com.rpa.web.pojo.AppPO;
import com.rpa.web.service.IAppService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:49
 * @description: 版本更新
 * @version: 1.0
 */
@Service
public class AppServiceImpl implements IAppService {

    @Resource
    private AppMapper appMapper;

    @Override
    public DTPageInfo<AppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<AppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<AppPO> pos = appMapper.query(reqData);
        return new DTPageInfo<AppDTO>(draw, page.getTotal(), AppDTO.convert(pos));
    }
}
