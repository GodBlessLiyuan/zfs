package com.rpa.common.mapper;

import com.rpa.common.pojo.UserNoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserNoticeMapper继承基类
 */
@Mapper
public interface UserNoticeMapper extends BaseMapper<UserNoticePO, Long> {
}