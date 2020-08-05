package com.zfs.common.mapper;

import com.zfs.common.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseMapper<InviteDetailPO, Long> {
    List<InviteDetailPO> queryInviteduserDetail(Map<String, Object> map);
}