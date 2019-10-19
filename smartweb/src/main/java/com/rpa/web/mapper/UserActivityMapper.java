package com.rpa.web.mapper;

import com.rpa.web.domain.UserActivityDO;
import com.rpa.web.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseDAO<UserActivityDO, Integer> {
    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    List<UserActivityDO> queryByUserId(int userId);

    List<UserActivityDO> goodCommentQuery(Map<String, Object> map);

    int updateStatus(UserActivityPO po);
}