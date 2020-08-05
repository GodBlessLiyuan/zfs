package com.zfs.rabbit.mapper;

import com.zfs.rabbit.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseMapper<InviteDetailPO, Long> {
    /**
     * 查询注册人数
     *
     * @param userId
     * @return
     */
    Integer queryCountByUserId(Long userId);

    /**
     * 通过orderId查询
     *
     * @param orderId
     * @return
     */
    InviteDetailPO queryByOrderId(Integer orderId);
}