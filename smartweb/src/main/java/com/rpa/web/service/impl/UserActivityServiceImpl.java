package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.domain.UserActivityDO;
import com.rpa.web.dto.UserActivityDTO;
import com.rpa.web.mapper.UserActivityMapper;
import com.rpa.web.service.IUserActivityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:15
 * @description: 活动赠送记录
 * @version: 1.0
 */
@Service
public class UserActivityServiceImpl implements IUserActivityService {

    @Resource
    private UserActivityMapper userActivityMapper;

    @Override
    public DTPageInfo<UserActivityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserActivityDO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserActivityDO> dos = userActivityMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserActivityDTO.convert(dos));
    }
}
