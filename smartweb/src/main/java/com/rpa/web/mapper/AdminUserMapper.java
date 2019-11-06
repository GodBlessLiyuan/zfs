package com.rpa.web.mapper;

import com.rpa.web.domain.AdminUserDO;
import com.rpa.web.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * AdminUserMapper继承基类
 */
@Mapper
public interface AdminUserMapper extends BaseDAO<AdminUserPO, Integer> {

    int updatePassword(int aId, String newPassword);

    String queryPassword(int aId);

    String queryUsernameByAid(Integer aId);

    AdminUserPO queryUserByUsername(String username);

    AdminUserDO queryById(Integer aId);

    List<AdminUserDO> queryBy(Map<String, Object> map);

    String queryPhoneByAid(Long aId);
}