package com.zfs.common.mapper;

import com.zfs.common.pojo.AppAvaChPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AppAvaChMapper继承基类
 */
@Mapper
public interface AppAvaChMapper extends BaseMapper<AppAvaChPO, Long> {
    int batchInsert(List<AppAvaChPO> aacPOs);

    List<AppAvaChPO> queryByAvatarIdAndAppId(@Param("avatarId") Long avatarId, @Param("appId") int appId);

    int updateStatus(@Param("avatarId") long avatarId, @Param("status") byte status);

    int batchDelete(List<Long> aacIds);

    int deleteByAvatarId(long avatarId);
}