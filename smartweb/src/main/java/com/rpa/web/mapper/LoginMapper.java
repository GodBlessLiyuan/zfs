package com.rpa.web.mapper;

import com.rpa.web.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    AdminUserPO getUserByUserName(String username);
}
