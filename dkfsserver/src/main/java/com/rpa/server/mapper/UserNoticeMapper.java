package com.rpa.server.mapper;

import com.rpa.server.pojo.UserNoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserNoticeMapper继承基类
 */
@Mapper
public interface UserNoticeMapper extends BaseMapper<UserNoticePO, Long> {
}