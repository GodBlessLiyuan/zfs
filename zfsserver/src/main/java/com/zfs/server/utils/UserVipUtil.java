package com.zfs.server.utils;

import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.constant.UserVipConstant;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/30 10:28
 * @description: 用户会员数据工具
 * @version: 1.0
 */
public class UserVipUtil {
    /**
     * 跟新用户会员PO
     *
     * @param userVipPO 用户会员PO
     * @param userId    用户Id
     * @param days      天数
     * @param isBuy     是否购买
     */
    public static UserVipPO buildUserVipVO(UserVipPO userVipPO, long userId, Integer days, boolean isBuy) {
        int vipType = days >= UserVipConstant.YEAR_VIP_DAY ? UserVipConstant.YEAR_VIP : UserVipConstant.COMM_VIP;
        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DATE, days);
        Date endDate = calendar.getTime();

        if (userVipPO == null) {
            // 新增
            UserVipPO po = new UserVipPO();
            po.setUserId(userId);
            po.setViptypeId(vipType);
            po.setStartTime(curDate);//当前时间
            po.setEndTime(endDate);//结束时间
            po.setStatus((byte) 1);
            po.setCreateTime(curDate);
            if (UserVipConstant.YEAR_VIP == vipType) {
                po.setVcreateTime(curDate);
                po.setVendTime(endDate);
            }
            if (isBuy) {
                po.setFirstTime(curDate);
                po.setLastTime(curDate);
            }

            return po;
        }

        // 更新
        userVipPO.setStatus((byte) 1);
        userVipPO.setUpdateTime(curDate);

        if (curDate.compareTo(userVipPO.getEndTime()) > 0) {
            // 会员过期
            userVipPO.setViptypeId(vipType);
            userVipPO.setStartTime(curDate);
            userVipPO.setEndTime(endDate);
        } else {
            calendar.setTime(userVipPO.getEndTime());//开始时间是结束时间
            calendar.add(Calendar.DATE, days);
            userVipPO.setEndTime(calendar.getTime());//结束时间加上天数
        }

        if (UserVipConstant.YEAR_VIP == vipType) {
            userVipPO.setViptypeId(UserVipConstant.YEAR_VIP);
            if (null == userVipPO.getVendTime() || curDate.compareTo(userVipPO.getVendTime()) > 0) {
                // 年费会员过期
                userVipPO.setVcreateTime(curDate);//年费时间的开始时间是当前时间
                userVipPO.setVendTime(endDate);
            } else {
                calendar.setTime(userVipPO.getVendTime());
                calendar.add(Calendar.DATE, days);
                userVipPO.setVendTime(calendar.getTime());
            }
        }
        //不是年会会员下，当前时间大于年会结束时间则职位一般会员？
        else {
            if (null != userVipPO.getVendTime() && curDate.compareTo(userVipPO.getVendTime()) > 0) {
                userVipPO.setViptypeId(UserVipConstant.COMM_VIP);
            }
        }

        if (isBuy) {
            if (null == userVipPO.getFirstTime()) {
                userVipPO.setFirstTime(curDate);
            }
            userVipPO.setLastTime(curDate);
        }

        return userVipPO;
    }
}
