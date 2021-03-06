package com.zfs.common.mapper;

import com.zfs.common.pojo.UserNoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserNoticeMapper继承基类
 */
@Mapper
public interface UserNoticeMapper extends BaseMapper<UserNoticePO, Long> {
}