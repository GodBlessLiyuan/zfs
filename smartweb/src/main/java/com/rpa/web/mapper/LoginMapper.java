package com.rpa.web.mapper;

import com.rpa.web.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    UserPO getUserByUserName(String username);
}
