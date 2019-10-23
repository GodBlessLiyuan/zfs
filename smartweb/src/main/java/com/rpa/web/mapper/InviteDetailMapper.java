package com.rpa.web.mapper;

import com.rpa.web.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseDAO<InviteDetailPO, Long> {
}