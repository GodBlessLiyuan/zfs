package com.zfs.server.constant;

import com.zfs.common.utils.DateUtilCard;
import com.zfs.common.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-13 10:46
 */
public class NoticeTypeConstant {
    /***
     * redis缓存变量
     */
    public static final String All="ALL_USER";
    public static final String NOT_MEMBER="NOT_MEMBER";
    public static final String MEMBER="MEMBER";
    public static final String HALF_YEAR_REGISTER="HALF_YEAR_REGISTER";
    public static final String ONE_MONTH_REGISTER="ONE_MONTH_REGISTER";
    //通知为了跟后台的key一致
    public static final String NOTICE_HASH_KEY= RedisKeyUtil.genNoticeRedisKey(DateUtilCard.date2Str(new Date(), DateUtilCard.YMD));
}
