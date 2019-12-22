package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.UserActivityBO;
import com.rpa.common.mapper.UserActivityMapper;
import com.rpa.common.pojo.UserActivityPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.utils.OperatorUtil;
import com.rpa.web.vo.UserActivityDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.service.IUserActivityService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:15
 * @description: 活动赠送记录
 * @version: 1.0
 */
@Service
public class UserActivityServiceImpl implements IUserActivityService {
    private final static Logger logger = LoggerFactory.getLogger(UserActivityServiceImpl.class);

    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public DTPageInfo<UserActivityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserActivityBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserActivityBO> dos = userActivityMapper.query(reqData);
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
        List<UserActivityBO> lists_DO = this.userActivityMapper.goodCommentQuery(map);

        // 将查询到的 UserActivityDO 数据转换为 UserActivityDTO
        List<UserActivityDTO> lists_DTO = new ArrayList<>();
        for(UserActivityBO userActivityDO : lists_DO) {
            UserActivityDTO dto = new UserActivityDTO();
            dto.setUAId(userActivityDO.getuAId());
            dto.setPhone(userActivityDO.getPhone());
            dto.setCreateTime(userActivityDO.getCreateTime());
            if (null == userActivityDO.getUrl()) {
                dto.setUrl(userActivityDO.getUrl());
            } else {
                dto.setUrl(publicPath + userActivityDO.getUrl());
            }
            dto.setComTypeName(userActivityDO.getComTypeName());
            dto.setStatus(userActivityDO.getStatus());
            dto.setOperator(queryUsernameByAid(userActivityDO.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }


    /**
     * 好评活动：修改审核状态
     * @author dangyi
     * @param httpSession
     * @param uAId
     * @param status
     * @return
     */
    @Override
    public ResultVO updateStatus(HttpSession httpSession, Integer uAId, Byte status) {

        // 根据uAId，查询出要修改的数据
        UserActivityPO po = this.userActivityMapper.selectByPrimaryKey(uAId);

        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.userActivityMapper.updateStatus(po);
        if (result == 0) {
            LogUtil.log(logger, "updateStatus", "更新失败", po);
        }
        return new ResultVO(1000);
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @author dangyi
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }
}
