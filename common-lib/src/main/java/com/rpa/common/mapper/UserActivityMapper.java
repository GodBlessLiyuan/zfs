package com.rpa.common.mapper;

import com.rpa.common.bo.UserActivityBO;
import com.rpa.common.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    List<UserActivityBO> queryByUserId(Long userId);

    List<UserActivityBO> goodCommentQuery(Map<String, Object> map);

    int updateStatus(UserActivityPO po);

    /**
     * 根据 userId 查询所有已激活的活动记录
     *
     * @param userId
     * @return
     */
    List<UserActivityPO> queryActivatedByUserId(Long userId);

    /**
     * 通过 userId 和 status 查询 (status == 0 时，查询所有状态)
     *
     * @param userId
     * @param status
     * @return
     */
    List<UserActivityPO> queryByUserIdAndStatus(@Param("userId") Long userId,@Param("status") Byte status);

    /**
     * 激活
     *
     * @param userId
     */
    void activate(Long userId);

    /**
     * dkfsfront 查询
     * @param ud
     * @return
     */
    Integer queryByUd(Long ud);
}