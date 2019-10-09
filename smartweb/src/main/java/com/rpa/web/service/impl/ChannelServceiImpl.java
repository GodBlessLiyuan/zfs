package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.mapper.ChannelMapper;
import com.rpa.web.pojo.ChannelPO;
import com.rpa.web.service.ChannelService;
import com.rpa.web.utils.DTPageInfo;
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

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param proName
     * @return
     */
    @Override
    public DTPageInfo<ChannelDTO> query(int draw, int pageNum, int pageSize, String chanNickname, String proName) {

        // 分页
        Page<ChannelDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("chanNickname", chanNickname);
        map.put("proName", proName);

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
     * 通过aId从t_admin_user中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.channelMapper.queryUsernameByAid(aId);
    }

    /**
     * 插入
     * @param channelDTO
     * @return
     * @TODO 需插入管理员字段a_id，从session中获取
     */
    @Override
    public int insert(ChannelDTO channelDTO, HttpSession httpSession) {

        // 把 channelDTO 转换为 channelPO
        ChannelPO channelPO = new ChannelPO();
        channelPO.setChanNickname(channelDTO.getChanNickname());
        channelPO.setChanName(channelDTO.getChanName());
        channelPO.setProId(channelDTO.getProId());
        channelPO.setExtra(channelDTO.getExtra());
        channelPO.setDr((byte)1);
        channelPO.setCreateTime(new Date());
        channelPO.setUpdateTime(new Date());

        int count = this.channelMapper.insert(channelPO);
        return count;
    }
}
