package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.UserActivityDO;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.UserActivityDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.mapper.UserActivityMapper;
import com.rpa.web.pojo.UserActivityPO;
import com.rpa.web.service.IUserActivityService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
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

    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Value("${file.publicPath}")
    private String publicPath;

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
            dto.setuAId(userActivityDO.getuAId());
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

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 根据uAId，查询出要修改的数据
        UserActivityPO po = this.userActivityMapper.selectByPrimaryKey(uAId);

        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.QUERY_ERROR);
        }

        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setaId(aId);

        int count = this.userActivityMapper.updateStatus(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
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
