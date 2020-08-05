package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.utils.LogUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.SoftChannelVO;
import com.zfs.common.mapper.SoftChannelMapper;
import com.zfs.common.pojo.SoftChannelPO;
import com.zfs.web.service.ISoftChannelService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(SoftChannelServiceImpl.class);

    @Resource
    private SoftChannelMapper softChannelMapper;

    @Override
    public DTPageInfo<SoftChannelVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<SoftChannelPO> page = PageHelper.startPage(pageNum, pageSize);
        List<SoftChannelPO> pos = softChannelMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), SoftChannelVO.convert(pos));
    }

    @Override
    public List<SoftChannelVO> queryAll() {
        List<SoftChannelPO> pos = softChannelMapper.queryAll();
        return SoftChannelVO.convert(pos);
    }

    @Override
    public ResultVO insert(String channelName, String extra) {
        SoftChannelPO po = new SoftChannelPO();

        po.setName(channelName);
        po.setExtra(extra);
        po.setCreateTime(new Date());

        int result = softChannelMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }

        return new ResultVO(1000);
    }
}
