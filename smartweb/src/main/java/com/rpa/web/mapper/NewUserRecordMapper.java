package com.rpa.web.mapper;

import com.rpa.web.domain.NewUserRecordDO;
import com.rpa.web.pojo.NewUserRecordPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * NewUserRecordMapper继承基类
 */
@Mapper
public interface NewUserRecordMapper extends BaseDAO<NewUserRecordPO, Integer> {
    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    List<NewUserRecordDO> queryByUserId(int userId);
}