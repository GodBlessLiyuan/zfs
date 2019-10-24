package com.rpa.server.mapper;

import com.rpa.server.bo.NewUserRecordBO;
import com.rpa.server.pojo.NewUserRecordPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * NewUserRecordMapper继承基类
 */
@Mapper
public interface NewUserRecordMapper extends BaseMapper<NewUserRecordPO, Integer> {
    /**
     * 根据 userId 查询新用户赠送
     * @param userId
     * @return
     */
    List<NewUserRecordBO> queryByUserId(Long userId);
}