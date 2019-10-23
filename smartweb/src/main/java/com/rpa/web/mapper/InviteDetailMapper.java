package com.rpa.web.mapper;

import com.rpa.web.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseDAO<InviteDetailPO, Long> {
    List<InviteDetailPO> queryInviteduserDetail(Map<String, Object> map);
}