package com.rpa.common.mapper;

import com.rpa.common.bo.AdminUserBO;
import com.rpa.common.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AdminUserMapper继承基类
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserPO, Integer> {

    int updatePassword(@Param("aId") int aId,@Param("newPassword") String newPassword);

    String queryPassword(int aId);

    String queryUsernameByAid(Integer aId);

    AdminUserPO queryUserByUsername(String username);

    AdminUserBO queryById(Integer aId);

    List<AdminUserBO> queryBy(Map<String, Object> map);

    String queryPhoneByAid(Long aId);
}