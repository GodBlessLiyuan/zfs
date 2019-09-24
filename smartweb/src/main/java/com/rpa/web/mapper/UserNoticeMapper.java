package com.rpa.web.mapper;

import com.rpa.web.pojo.UserNoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserNoticeMapper继承基类
 */
@Mapper
public interface UserNoticeMapper extends BaseDAO<UserNoticePO, UserNoticePO> {
}