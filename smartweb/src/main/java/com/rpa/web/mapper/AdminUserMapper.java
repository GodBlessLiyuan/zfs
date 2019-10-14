package com.rpa.web.mapper;

import com.rpa.web.domain.AdminUserDO;
import com.rpa.web.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminUserMapper继承基类
 */
@Mapper
public interface AdminUserMapper extends BaseDAO<AdminUserDO, Integer> {

    int updatePassword(int aId, String newPassword);

    String queryPassword(int aId);

    String queryUsernameByAid(Integer aId);

    AdminUserPO queryUserByUsername(String username);
}