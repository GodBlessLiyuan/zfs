package com.rpa.web.mapper;

import com.rpa.web.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseDAO<UserPO, Long> {
    /**
     * 分页查询
     *
     * @return
     */
    List<UserPO> list();

    int queryNewRegister();

    String queryPhoneByUserId(Long userId);

    String queryPhoneByUserid(Long aId);
}