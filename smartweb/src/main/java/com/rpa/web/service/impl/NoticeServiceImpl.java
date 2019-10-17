package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.NoticeDTO;
import com.rpa.web.mapper.NoticeMapper;
import com.rpa.web.pojo.NoticePO;
import com.rpa.web.service.NoticeService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 18:48 2019/9/26
 * @version: 1.0.0
 * @description: 通知管理
 */

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param status
     * @param type
     * @param title
     * @return
     */
    @Override
    public DTPageInfo<NoticeDTO> query(int draw, int pageNum, int pageSize, Date startTime, Date endTime, Integer status, Byte type, String title) {

        // 分页
        Page<NoticeDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(5);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("status", status);
        map.put("type", type);
        map.put("title", title);

        // 按照条件查询数据
        List<NoticePO> lists_PO = noticeMapper.query(map);

        // 将查询到的 AdconfigPO 数据转换为 AdconfigDTO
        List<NoticeDTO> lists_DTO = new ArrayList<>();
        for(NoticePO po: lists_PO) {
            NoticeDTO dto = new NoticeDTO();
            dto.setNoticeId(po.getNoticeId());
            dto.setTitle(po.getTitle());
            dto.setText(po.getText());
            dto.setType(po.getType());
            dto.setShowTime(po.getShowTime());
            dto.setStartTime(po.getStartTime());
            dto.setEndTime(po.getEndTime());
            dto.setUrl(po.getUrl());
            dto.setStatus(po.getStatus());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param noticeDTO
     * @return
     */
    @Override
    public int insert(NoticeDTO noticeDTO) {

        // 把 noticeDTO 转换为 noticePO
        NoticePO noticePO = new NoticePO();
        noticePO.setTitle(noticeDTO.getTitle());
        noticePO.setText(noticeDTO.getText());
        noticePO.setType(noticeDTO.getType());
        noticePO.setShowTime(noticeDTO.getShowTime());
        noticePO.setStartTime(noticeDTO.getStartTime());
        noticePO.setEndTime(noticeDTO.getEndTime());
        noticePO.setStatus(noticeDTO.getStatus());
        noticePO.setUrl(noticeDTO.getUrl());

        int count = this.noticeMapper.insert(noticePO);
        return count;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        int count = noticeMapper.deleteByPrimaryKey(id);
        return count;
    }

}
