package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.NoticeMapper;
import com.zfs.common.pojo.NoticePO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.NoticeDTO;
import com.zfs.server.service.INoticeService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate template;

    @Resource
    private RedisCacheUtil cache;

    @Value("${file.publicPath}")
    private String filePublicPath;

    @Override
    public ResultVO queryNotice(NoticeDTO dto) {

        //准备要返回数据的对象
        List<NoticeVO> vos;

        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //Redis中的key
        String key = RedisKeyUtil.genNoticeRedisKey(current_date);

        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vos = JSON.parseObject(cache.getCacheByKey(key), List.class);
        } else{
            List<NoticePO> noticePOs = noticeMapper.queryAll();
            if (noticePOs == null || noticePOs.size() == 0) {
                return new ResultVO(1000);
            }

            vos = new ArrayList<>();
            for (NoticePO po : noticePOs) {
                NoticeVO vo = new NoticeVO();
                vo.setNoticeid(po.getNoticeId());
                vo.setType(po.getType());
                vo.setTitle(po.getTitle());
                vo.setText(po.getText());
                vo.setShowtime(po.getShowTime());
                vo.setUrl(po.getUrl());
                if (null != po.getPicurl()) {
                    vo.setPicurl(filePublicPath + po.getPicurl());
                }
                vos.add(vo);
            }

            //将对象用JSON序列化，存入Redis
            cache.setCacheWithDate(key, vos ,24, TimeUnit.HOURS);
        }
        return new ResultVO<>(1000, vos);
    }
}
