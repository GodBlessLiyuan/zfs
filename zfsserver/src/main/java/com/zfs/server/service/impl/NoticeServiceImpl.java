package com.zfs.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.NoticeMapper;
import com.zfs.common.mapper.UserMapper;
import com.zfs.common.mapper.UserVipMapper;
import com.zfs.common.mapper.ViptypeMapper;
import com.zfs.common.pojo.NoticePO;
import com.zfs.common.pojo.UserPO;
import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.pojo.ViptypePO;
import com.zfs.common.utils.DateUtil;
import com.zfs.common.utils.DateUtilCard;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.constant.NoticeTypeConstant;
import com.zfs.server.constant.UserVipConstant;
import com.zfs.server.dto.NoticeDTO;
import com.zfs.server.service.INoticeService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    /***
     * 我先查询五种情况的数据，之后根据用户类型去直接给。
     * 可以将数据缓存起来，则应该查询通知结束时间之前的通知数据。
     * 缓存时间久了则App端存在大量的过期数据，App端加判断，可以保证通知的正确性，这里缓存前缀加上时间
     * */
    //缓存前缀加的时间格式
    private static final String current_time = DateUtilCard.date2Str(new Date(), DateUtilCard.YMD_HM);
    //所有用户的键值
    private static final String All = RedisKeyUtil.genNoticeRedisKey(current_time, "ALL_USER");
    private static final String NOT_MEMBER = RedisKeyUtil.genNoticeRedisKey(current_time, "NOT_MEMBER");
    private static final String MEMBER = RedisKeyUtil.genNoticeRedisKey(current_time, "MEMBER");
    private static final String HALF_YEAR_REGISTER = RedisKeyUtil.genNoticeRedisKey(current_time, "HALF_YEAR_REGISTER");
    private static final String ONE_MONTH_REGISTER = RedisKeyUtil.genNoticeRedisKey(current_time, "ONE_MONTH_REGISTER");
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserVipMapper userVipMapper;
    @Autowired
    private ViptypeMapper viptypeMapper;
    @Value("${notice.cacheTime}")
    private Integer cacheTime;

    @Override
    public ResultVO queryNotice(NoticeDTO dto) {
        //先查数据、之后存起来
        List<NoticeVO> allVOS = new ArrayList<>();
        List<NoticeVO> notMembers = new ArrayList<>();
        List<NoticeVO> members = new ArrayList<>();
        List<NoticeVO> halfYearRegS = new ArrayList<>();
        List<NoticeVO> oneMonthRegS = new ArrayList<>();
        if (template.hasKey(All)) {
            allVOS=JSON.parseArray(cache.getCacheByKey(All), NoticeVO.class);
        }
        else{
            List<NoticePO> noticePOS = noticeMapper.queryAll();
            if (noticePOS == null || noticePOS.size() == 0) {
                //没有全部数据
                return new ResultVO(1000);
            }
            //2 非会员用户,3 会员用户 ,4 近半年注册用户,5 近一月注册用户
            //默认，五个生存周期一样。则不再sql中做了，mysql字符串包含函数
            for (NoticePO noticePO : noticePOS) {
                if (noticePO.getMenbers().contains("1")) {
                    allVOS.add(po2vo(noticePO));
                }
                //不是全部下的其他数据
                else{
                    if (noticePO.getMenbers().contains("2")) {
                        notMembers.add(po2vo(noticePO));
                    }
                    if (noticePO.getMenbers().contains("3")) {
                        members.add(po2vo(noticePO));
                    }
                    if (noticePO.getMenbers().contains("4")) {
                        halfYearRegS.add(po2vo(noticePO));
                    }
                    if (noticePO.getMenbers().contains("5")) {
                        oneMonthRegS.add(po2vo(noticePO));
                    }
                }

            }
            if (allVOS.size() > 0) {
                cache.setCacheWithDate(All, allVOS, cacheTime, TimeUnit.HOURS);
            }
            if (notMembers.size() > 0) {
                cache.setCacheWithDate(NOT_MEMBER, notMembers, cacheTime, TimeUnit.HOURS);
            }
            if (members.size() > 0) {
                cache.setCacheWithDate(MEMBER, members, cacheTime, TimeUnit.HOURS);
            }
            if (halfYearRegS.size() > 0) {
                cache.setCacheWithDate(HALF_YEAR_REGISTER, halfYearRegS, cacheTime, TimeUnit.HOURS);
            }
            if (oneMonthRegS.size() > 0) {
                cache.setCacheWithDate(ONE_MONTH_REGISTER, oneMonthRegS, cacheTime, TimeUnit.HOURS);
            }
        }
        if (template.hasKey(NOT_MEMBER)) {
            notMembers = JSON.parseArray(cache.getCacheByKey(NOT_MEMBER), NoticeVO.class);
        }
        if (template.hasKey(MEMBER)) {
            members = JSON.parseArray(cache.getCacheByKey(MEMBER), NoticeVO.class);
        }
        if (template.hasKey(HALF_YEAR_REGISTER)) {
            halfYearRegS = JSON.parseArray(cache.getCacheByKey(HALF_YEAR_REGISTER), NoticeVO.class);
        }
        if (template.hasKey(ONE_MONTH_REGISTER)) {
            oneMonthRegS = JSON.parseArray(cache.getCacheByKey(ONE_MONTH_REGISTER), NoticeVO.class);
        }
        /**
         判断用户类型，返回vo数据，还有去重操作才对；用户肯定是全体用户，但是通知记录可能不是全体的，所以思路对；
         找集合之后缓存起来，也可以每次去数据库判断一遍；因为集合数据大，所以去数据库验证。
         * */
        //判断是会员和非会员
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        if(userVipPO!=null){
            //判断类型，集合去重
            if(userVipPO.getViptypeId()== UserVipConstant.NOT_VIP){
                allVOS.addAll(notMembers);
            }else{
                allVOS.addAll(members);
            }
        }
        //判断是否是半年内注册用户
        UserPO userPO = userMapper.selectByPrimaryKey(dto.getUd());
        if(userPO.getCreateTime()!=null){
            //半年内注册用户
            if(timeAfter(userPO,182)){
                allVOS.addAll(halfYearRegS);
                allVOS.stream().distinct().collect(Collectors.toList());
            }
            if(timeAfter(userPO,30)){
                allVOS.addAll(oneMonthRegS);
                allVOS.stream().distinct().collect(Collectors.toList());
            }
        }
        return new ResultVO(1000,allVOS);
    }
    //旧的原来的业务代码
    public ResultVO queryNoticeOld(NoticeDTO dto) {
        //准备要返回数据的对象
        List<NoticeVO> vos;
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //Redis中的key
        String key = RedisKeyUtil.genNoticeRedisKey(current_date);
        //先从Redis中查询，若为null，再去查询数据库
        if (template.hasKey(key)) {
            vos = JSON.parseObject(cache.getCacheByKey(key), List.class);
        } else {
            List<NoticePO> noticePOs = noticeMapper.queryAll();
            if (noticePOs == null || noticePOs.size() == 0) {
                return new ResultVO(1000);
            }
            vos = new ArrayList<>();
            pos2vos(noticePOs, vos);
            //将对象用JSON序列化，存入Redis
            cache.setCacheWithDate(key, vos, 24, TimeUnit.HOURS);
        }
        return new ResultVO<>(1000, vos);
    }
    private boolean timeAfter(UserPO userPO,int days){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(userPO.getCreateTime());
        calendar.add(Calendar.DATE,days);
        return calendar.getTime().after(new Date());
    }

    private void pos2vos(List<NoticePO> noticePOs, List<NoticeVO> vos) {
        for (NoticePO po : noticePOs) {
            vos.add(this.po2vo(po));
        }
    }

    private NoticeVO po2vo(NoticePO po) {
        NoticeVO vo = new NoticeVO();
        vo.setNoticeid(po.getNoticeId());
        vo.setType(po.getType());
        vo.setTitle(po.getTitle());
        vo.setText(po.getText());
        //前端是必填项
        vo.setShowtime(DateUtilCard.date2Str(po.getShowTime(), DateUtilCard.HM));
        vo.setEndShowTime(DateUtilCard.date2Str(po.getEndShowTime(), DateUtilCard.HM));
        vo.setStartTime(DateUtilCard.date2Str(po.getEndTime(), DateUtilCard.YMD));
        vo.setEndTime(DateUtilCard.date2Str(po.getEndTime(), DateUtilCard.YMD));
        vo.setUrl(po.getUrl());
        if (null != po.getPicurl()) {
            vo.setPicurl(filePublicPath + po.getPicurl());
        }
        return vo;
    }
}
