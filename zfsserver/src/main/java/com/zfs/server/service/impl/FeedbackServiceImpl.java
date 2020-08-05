package com.zfs.server.service.impl;

import com.zfs.common.mapper.FeedbackMapper;
import com.zfs.common.pojo.FeedbackPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.FeedbackDTO;
import com.zfs.server.service.IFeedbackService;
import com.zfs.server.utils.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 10:59
 * @description: 用户反馈
 * @version: 1.0
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    private final static Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public ResultVO insert(FeedbackDTO dto) {
        FeedbackPO feedbackPO = new FeedbackPO();
        // 基本信息
        feedbackPO.setUserId(dto.getUd());
        feedbackPO.setDeviceId(dto.getId());
        feedbackPO.setUserDeviceId(dto.getUdd());
        feedbackPO.setContext(dto.getContext());
        feedbackPO.setContact(dto.getPh());
        feedbackPO.setCreateTime(new Date());
        feedbackPO.setVersioncode(dto.getSoftv());
        feedbackPO.setManufacturer(dto.getFactory());
        feedbackPO.setAndroidmodel(dto.getModel());
        feedbackPO.setBuildversion(dto.getOsv());
        feedbackPO.setBuildrelease(dto.getOsre());
        // 图片处理
        if(dto.getPicdata1() != null) {
            feedbackPO.setUrl1(UploadUtil.uploadBase64Image(dto.getPicdata1()));
        }
        if(dto.getPicdata2() != null) {
            feedbackPO.setUrl2(UploadUtil.uploadBase64Image(dto.getPicdata2()));
        }
        if(dto.getPicdata3() != null) {
            feedbackPO.setUrl3(UploadUtil.uploadBase64Image(dto.getPicdata3()));
        }

        int result = feedbackMapper.insert(feedbackPO);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", feedbackPO);
        }

        return new ResultVO(1000);
    }
}
