package com.rpa.server.mapper;

import com.rpa.common.pojo.NoticePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * NoticeMapper继承基类
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticePO, Integer> {
    /**
     * 查询全部
     *
     * @return
     */
    List<NoticePO> queryAll();
}