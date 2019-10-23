package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.mapper.ChannelMapper;
import com.rpa.web.mapper.PromoterMapper;
import com.rpa.web.pojo.ChannelPO;
import com.rpa.web.pojo.PromoterPO;
import com.rpa.web.service.ChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 9:56 2019/10/7
 * @version: 1.0.0
 * @description: 渠道推广
 */
@Service
public class ChannelServceiImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PromoterMapper promoterMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param proId
     * @return
     */
    @Override
    public DTPageInfo<ChannelDTO> query(int draw, int pageNum, int pageSize, String chanNickname, Integer proId) {

        // 分页
        Page<ChannelDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("chanNickname", chanNickname);
        map.put("proId", proId);

        // 按照条件查询数据
        List<ChannelPO> lists_PO = channelMapper.query(map);

        // 将查询到的 ChannelPO 数据转换为 ChannelDTO
        List<ChannelDTO> lists_DTO = new ArrayList<>();
        for(ChannelPO po: lists_PO) {
            ChannelDTO dto = new ChannelDTO();
            dto.setChanNickname(po.getChanNickname());
            dto.setChanName(po.getChanName());
            dto.setProName(po.getProName());
            dto.setPhone(po.getPhone());
            dto.setExtra(po.getExtra());
            dto.setCreateTime(po.getCreateTime());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }


    /**
     * 查询：从t_channel表中查出当前所有的pro_id，及其所对应的pro_name
     * @return
     */
    @Override
    public ResultVO queryProNames() {

        List<ChannelPO> POs = this.channelMapper.queryProNames();

        // 将查询到的 PO 转换为 DTO
        List<ChannelDTO> DTOs = new ArrayList<>();
        for (ChannelPO po : POs) {
            ChannelDTO dto = new ChannelDTO();
            dto.setProId(po.getProId());
            dto.setProName(po.getProName());
            DTOs.add(dto);
        }
        return ResultVOUtil.success(DTOs);
    }

    /**
     * 查询：从t_promoter表中查出当前所有的pro_id，及其所对应的pro_name
     * @return
     */
    @Override
    public ResultVO queryAllProNames() {

        List<PromoterPO> POs = this.promoterMapper.queryAllProNames();

        // 将查询到的 PO 转换为 DTO
        List<PromoterDTO> DTOs = new ArrayList<>();
        for (PromoterPO po : POs) {
            PromoterDTO dto = new PromoterDTO();
            dto.setProId(po.getProId());
            dto.setProName(po.getProName());
            DTOs.add(dto);
        }
        return ResultVOUtil.success(DTOs);
    }

    /**
     * 插入
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(ChannelDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 把 DTO 转换为 PO
        ChannelPO po = new ChannelPO();
        po.setChanNickname(dto.getChanNickname());
        po.setChanName(dto.getChanName());
        po.setProId(dto.getProId());
        po.setExtra(dto.getExtra());
        po.setDr((byte)1);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId

        int count = this.channelMapper.insert(po);
        return count ==1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
    }




    /**
     * 通过aId从t_admin_user中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }
}
