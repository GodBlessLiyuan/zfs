package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.FeedbackDTO;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.mapper.FeedbackMapper;
import com.rpa.web.mapper.UserMapper;
import com.rpa.web.pojo.FeedbackPO;
import com.rpa.web.service.FeedbackService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private UserMapper userMapper;

    @Value("${file.publicPath}")
    private String publicPath;


    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param startTime
     * @param endTime
     * @param userId
     * @param contact
     * @return
     */
    @Override
    public DTPageInfo<FeedbackDTO> query(int draw, int start, int length, String startTime, String endTime, String userId, String contact) {

        // 分页
        Page<FeedbackDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", endDatePlusOne(endTime));
        map.put("userId", userId);
        map.put("contact", contact);

        // 按照条件查询数据
        List<FeedbackPO> lists_PO = this.feedbackMapper.query(map);

        // 将查询到的 AdconfigPO 数据转换为 AdconfigDTO
        List<FeedbackDTO> lists_DTO = new ArrayList<>();
        for(FeedbackPO po: lists_PO) {
            FeedbackDTO dto = new FeedbackDTO();
            dto.setFeedbackId(po.getFeedbackId());
            dto.setPhone(queryPhoneByUserId(po.getUserId()));
            dto.setDeviceId(po.getDeviceId());
            dto.setManufacturer(po.getManufacturer());
            dto.setAndroidmodel(po.getAndroidmodel());
            dto.setBuildversion(po.getBuildversion());
            dto.setVersioncode(po.getVersioncode());
            dto.setCreateTime(po.getCreateTime());
            dto.setContact(po.getContact());
            dto.setContext(po.getContext());
            if (null == po.getUrl1()) {
                dto.setUrl1(po.getUrl1());
            } else {
                dto.setUrl1(publicPath + po.getUrl1());
            }

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }


    /**
     * 根据ID，从t_user表中查询出手机号码（账号）
     * @param userId
     * @return
     */
    public String queryPhoneByUserId(Long userId) {
        return this.userMapper.queryPhoneByUserId(userId);
    }

    /**
     * 类型转换：将字符串类型的时间，转换为日期类型，加一天后再转回字符串
     * @param strDate
     * @return
     */
    private String endDatePlusOne(String strDate) {
        if (null == strDate || "".equals(strDate)) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();

            return format.format(date);
        }


    }
}
