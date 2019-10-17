package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.SoftChannelDTO;
import com.rpa.web.mapper.SoftChannelMapper;
import com.rpa.web.pojo.SoftChannelPO;
import com.rpa.web.service.ISoftChannelService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 8:49
 * @description: 渠道信息
 * @version: 1.0
 */
@Service
public class SoftChannelServiceImpl implements ISoftChannelService {

    @Resource
    private SoftChannelMapper softChannelMapper;

    @Override
    public DTPageInfo<SoftChannelDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<SoftChannelPO> page = PageHelper.startPage(pageNum, pageSize);
        List<SoftChannelPO> pos = softChannelMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), SoftChannelDTO.convert(pos));
    }

    @Override
    public List<SoftChannelDTO> queryAll() {
        List<SoftChannelPO> pos = softChannelMapper.queryAll();
        return SoftChannelDTO.convert(pos);
    }

    @Override
    public int insert(String channelName, String extra) {
        SoftChannelPO po = new SoftChannelPO();

        po.setName(channelName);
        po.setExtra(extra);
        po.setCreateTime(new Date());

        return softChannelMapper.insert(po);
    }
}
