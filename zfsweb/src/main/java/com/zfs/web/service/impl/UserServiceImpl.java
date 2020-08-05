package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.UserBO;
import com.zfs.common.mapper.RegisterUserMapper;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.UserVO;
import com.zfs.web.service.IUserService;
import com.zfs.web.utils.DTPageInfo;
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
    private RegisterUserMapper registerUserMapper;
    @Override
    public DTPageInfo<UserVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserBO> dos = registerUserMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserVO.convert(dos));
    }
}
