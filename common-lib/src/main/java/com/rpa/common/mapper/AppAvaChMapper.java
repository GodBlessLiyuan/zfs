package com.rpa.common.mapper;

import com.rpa.common.pojo.AppAvaChPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AppAvaChMapper继承基类
 */
@Mapper
public interface AppAvaChMapper extends BaseMapper<AppAvaChPO, Long> {
    void batchInsert(List<AppAvaChPO> aacPOs);

    List<AppAvaChPO> queryByAvatarIdAndAppId(@Param("avatarId") Long avatarId, @Param("appId") int appId);

    void updateStatus(@Param("avatarId") long avatarId, @Param("status") byte status);
}