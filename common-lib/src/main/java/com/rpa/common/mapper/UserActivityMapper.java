package com.rpa.common.mapper;

import com.rpa.common.bo.UserActivityBO;
import com.rpa.common.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
    /**
     * 根据userId查询数据
     *
     * @param userId
     * @return
     */
    List<UserActivityBO> queryByUserId(int userId);

    List<UserActivityBO> goodCommentQuery(Map<String, Object> map);

    int updateStatus(UserActivityPO po);
}