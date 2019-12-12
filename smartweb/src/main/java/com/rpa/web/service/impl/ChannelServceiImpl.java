package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.ChannelBO;
import com.rpa.common.mapper.PromoterMapper;
import com.rpa.common.pojo.ChannelPO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.dto.PromoterDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.common.mapper.ChannelMapper;
import com.rpa.common.pojo.PromoterPO;
import com.rpa.web.service.ChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.utils.OperatorUtil;
import com.rpa.web.vo.ChannelVO;
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
     * @param start
     * @param length
     * @param chanNickname
     * @param proId
     * @return
     */
    @Override
    public DTPageInfo<ChannelVO> query(int draw, int start, int length, String chanNickname, Integer proId) {

        // 分页
        Page<ChannelVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("chanNickname", chanNickname);
        map.put("proId", proId);

        // 按照条件查询数据
        List<ChannelBO> bos = channelMapper.query(map);

        // 将查询到的 bo 数据转换为 vo
        List<ChannelVO> vos = new ArrayList<>();
        for(ChannelBO bo: bos) {
            ChannelVO vo = new ChannelVO();
            vo.setChanNickname(bo.getChanNickname());
            vo.setChanName(bo.getChanName());
            vo.setProName(bo.getProName());
            vo.setPhone(bo.getPhone());
            vo.setExtra(bo.getExtra());
            vo.setCreateTime(bo.getCreateTime());
            vo.setOperator(queryUsernameByAid(bo.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }


    /**
     * 查询：从t_channel表中查出当前所有的pro_id，及其所对应的pro_name
     * @return
     */
    @Override
    public ResultVO queryProNames() {

        List<ChannelBO> bos = this.channelMapper.queryProNames();

        // 将查询到的 bo 转换为 vo
        List<ChannelVO> vos = new ArrayList<>();
        for (ChannelBO bo : bos) {
            ChannelVO vo = new ChannelVO();
            vo.setProId(bo.getProId());
            vo.setProName(bo.getProName());
            vos.add(vo);
        }
        return new ResultVO(1000, vos);
    }

    /**
     * 查询：从t_promoter表中查出当前所有的pro_id，及其所对应的pro_name
     * @return
     */
    @Override
    public ResultVO queryAllProNames() {

        List<PromoterPO> pos = this.promoterMapper.queryAllProNames();

        // 将查询到的 PO 转换为 DTO
        List<PromoterDTO> dtos = new ArrayList<>();
        for (PromoterPO po : pos) {
            PromoterDTO dto = new PromoterDTO();
            dto.setProId(po.getProId());
            dto.setProName(po.getProName());
            dtos.add(dto);
        }
        return new ResultVO(1000, dtos);
    }

    /**
     * 插入
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(ChannelDTO dto, HttpSession httpSession) {

        // 把 dto 转换为 po
        ChannelPO po = new ChannelPO();
        po.setChanNickname(dto.getChanNickname());
        po.setChanName(dto.getChanName());
        po.setProId(dto.getProId());
        po.setExtra(dto.getExtra());
        po.setDr((byte)1);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        this.channelMapper.insert(po);
        return new ResultVO(1000);
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
