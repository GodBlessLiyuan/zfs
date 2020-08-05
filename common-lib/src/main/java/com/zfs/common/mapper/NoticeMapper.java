package com.zfs.common.mapper;

import com.zfs.common.pojo.NoticePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NoticeMapper继承基类
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticePO, Integer> {
}