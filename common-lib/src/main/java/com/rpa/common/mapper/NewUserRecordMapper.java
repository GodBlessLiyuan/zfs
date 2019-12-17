package com.rpa.common.mapper;

import com.rpa.common.bo.NewUserRecordBO;
import com.rpa.common.pojo.NewUserRecordPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * NewUserRecordMapper继承基类
 */
@Mapper
public interface NewUserRecordMapper extends BaseMapper<NewUserRecordPO, Integer> {
    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    List<NewUserRecordBO> queryByUserId(Long userId);

    /**
     * 根据 userId 查询新用户赠送
     *
     * @param userId
     * @return
     */
    List<NewUserRecordPO> queryByUserId2(Long userId);
}