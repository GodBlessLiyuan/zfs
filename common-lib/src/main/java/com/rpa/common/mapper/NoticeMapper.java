package com.rpa.common.mapper;

import com.rpa.common.pojo.NoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NoticeMapper继承基类
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticePO, Integer> {
}