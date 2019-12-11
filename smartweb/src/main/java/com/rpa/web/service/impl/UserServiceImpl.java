package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.UserBO;
import com.rpa.common.mapper.UserMapper;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.UserVO;
import com.rpa.web.service.IUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:03
 * @description: 用户信息
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public DTPageInfo<UserVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserBO> dos = userMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserVO.convert(dos));
    }
}
