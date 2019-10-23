package com.rpa.server.mapper;

import com.rpa.server.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseMapper<InviteDetailPO, Long> {
}