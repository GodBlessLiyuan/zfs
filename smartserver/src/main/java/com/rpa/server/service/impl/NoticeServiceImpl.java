package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.NoticeDTO;
import com.rpa.server.mapper.NoticeMapper;
import com.rpa.server.pojo.NoticePO;
import com.rpa.server.service.INoticeService;
import com.rpa.server.vo.NoticeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:48
 * @description: 通知消息
 * @version: 1.0
 */
@Service
public class NoticeServiceImpl implements INoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public ResultVO queryNotice(NoticeDTO dto) {
        List<NoticePO> noticePOs = noticeMapper.queryAll();
        if (noticePOs == null || noticePOs.size() == 0) {
            return new ResultVO(1000);
        }

        List<NoticeVO> vos = new ArrayList<>();
        for (NoticePO po : noticePOs) {
            NoticeVO vo = new NoticeVO();
            vo.setNoticeid(po.getNoticeId());
            vo.setType(po.getType());
            vo.setTitle(po.getTitle());
            vo.setShowtime(po.getShowTime());
            vo.setUrl(po.getUrl());
            vos.add(vo);
        }

        return new ResultVO<>(1000, vos);
    }
}
