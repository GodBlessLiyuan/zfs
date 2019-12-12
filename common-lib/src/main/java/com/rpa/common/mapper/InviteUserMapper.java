package com.rpa.common.mapper;

import com.rpa.common.bo.InviteUserBO;
import com.rpa.common.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
    List<InviteUserBO> queryInviteduser(Map<String, Object> map);
}