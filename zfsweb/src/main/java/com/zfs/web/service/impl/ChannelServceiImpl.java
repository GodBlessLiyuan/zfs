package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.ChannelBO;
import com.zfs.common.mapper.PromoterMapper;
import com.zfs.common.pojo.ChannelPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.dto.ChannelDTO;
import com.zfs.web.dto.PromoterDTO;
import com.zfs.common.mapper.AdminUserMapper;
import com.zfs.common.mapper.ChannelMapper;
import com.zfs.common.pojo.PromoterPO;
import com.zfs.web.service.ChannelService;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.ChannelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private final static Logger logger = LoggerFactory.getLogger(ChannelServceiImpl.class);

    @Resource
    private ChannelMapper channelMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private PromoterMapper promoterMapper;

    /**
     * 查询
     * @param start
     * @param length
     * @param chanNickname
     * @param proId
     * @return
     */
    @Override
    public ResultVO query(int start, int length, String chanName, String proName) {

        // 分页
        Page<ChannelVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("chanName", chanName);
        map.put("proName", proName);

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
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), vos));
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

        int result = this.channelMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
            return ResultVO.serverInnerError();
        }
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
