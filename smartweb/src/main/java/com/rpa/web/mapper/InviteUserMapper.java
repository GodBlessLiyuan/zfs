package com.rpa.web.mapper;

import com.rpa.web.domain.InviteUserDO;
import com.rpa.web.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseDAO<InviteUserPO, Integer> {
    List<InviteUserDO> queryInviteduser(Map<String, Object> map);
}