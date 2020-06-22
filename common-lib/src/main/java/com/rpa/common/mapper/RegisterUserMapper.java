package com.rpa.common.mapper;

import com.rpa.common.bo.UserBO;
import com.rpa.common.pojo.RegisterUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * RegisterUserMapper继承基类
 */
@Mapper
public interface RegisterUserMapper extends BaseMapper<RegisterUserPO, Long> {
    List<String> queryAllPhone();
    void batchInsert(Collection<UserBO> collection);
}