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
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 好评活动查询
     * @author dangyi
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @return
     */
    @Override
    public DTPageInfo<UserActivityDTO> goodCommentQuery(int draw, int pageNum, int pageSize, String phone) {

        // 分页
        Page<UserActivityDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("phone", phone);

        // 按照条件查询数据
        List<UserActivityDO> lists_DO = this.userActivityMapper.goodCommentQuery(map);

        // 将查询到的 UserActivityDO 数据转换为 UserActivityDTO
        List<UserActivityDTO> lists_DTO = new ArrayList<>();
        for(UserActivityDO userActivityDO : lists_DO) {
            UserActivityDTO dto = new UserActivityDTO();
            dto.setPhone(userActivityDO.getPhone());
            dto.setCreateTime(userActivityDO.getCreateTime());
            dto.setUrl(userActivityDO.getUrl());
            dto.setComTypeName(userActivityDO.getComTypeName());
            dto.setStatus(userActivityDO.getStatus());
            dto.setOperator(queryUsernameByAid(userActivityDO.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.userActivityMapper.queryUsernameByAid(aId);
    }
}
