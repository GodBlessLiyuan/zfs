package com.rpa.web.mapper;

import com.rpa.web.pojo.NoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NoticeMapper继承基类
 */
@Mapper
public interface NoticeMapper extends BaseDAO<NoticePO, Integer> {
}