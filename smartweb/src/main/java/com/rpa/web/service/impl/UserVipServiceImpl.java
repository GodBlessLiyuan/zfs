package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.domain.UserVipDO;
import com.rpa.web.dto.UserVipDTO;
import com.rpa.web.mapper.UserVipMapper;
import com.rpa.web.service.IUserVipService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:29
 * @description: 用户会员数据
 * @version: 1.0
 */
@Service
public class UserVipServiceImpl implements IUserVipService {

    @Resource
    private UserVipMapper userVipMapper;

    @Override
    public DTPageInfo<UserVipDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserVipDO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserVipDO> dos = userVipMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserVipDTO.convert(dos));
    }
}
