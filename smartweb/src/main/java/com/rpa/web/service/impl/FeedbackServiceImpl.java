package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.FeedbackDTO;
import com.rpa.web.mapper.FeedbackMapper;
import com.rpa.web.pojo.FeedbackPO;
import com.rpa.web.service.FeedbackService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 9:16 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param userId
     * @param contact
     * @return
     */
    @Override
    public DTPageInfo<FeedbackDTO> query(int draw, int pageNum, int pageSize, String startTime, String endTime, String userId, String contact) {

        // 分页
        Page<FeedbackDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("userId", userId);
        map.put("contact", contact);

        // 按照条件查询数据
        List<FeedbackPO> lists_PO = this.feedbackMapper.query(map);

        // 将查询到的 AdconfigPO 数据转换为 AdconfigDTO
        List<FeedbackDTO> lists_DTO = new ArrayList<>();
        for(FeedbackPO po: lists_PO) {
            FeedbackDTO dto = new FeedbackDTO();
            dto.setFeedbackId(po.getFeedbackId());
            dto.setUserId(po.getUserId());
            dto.setDeviceId(po.getDeviceId());
            dto.setManufacturer(po.getManufacturer());
            dto.setAndroidmodel(po.getAndroidmodel());
            dto.setBuildversion(po.getBuildversion());
            dto.setVersioncode(po.getVersioncode());
            dto.setCreateTime(po.getCreateTime());
            dto.setContact(po.getContact());
            dto.setContext(po.getContext());
            dto.setUrl(po.getUrl1());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }
}
