package com.rpa.server.mapper;

import com.rpa.server.pojo.NoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NoticeMapper继承基类
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticePO, Integer> {
}