package com.rpa.common.mapper;

import com.rpa.common.bo.AvatarBO;
import com.rpa.common.pojo.AvatarPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AvatarMapper继承基类
 */
@Mapper
public interface AvatarMapper extends BaseMapper<AvatarPO, Long> {
    AvatarPO queryByVersionCode(@Param("versionCode") Object versionCode,@Param("osVersion") int osVersion);

    List<AvatarBO> queryByAvatarIds(List<Integer> avatarIds);

    List<Integer> queryAvatarIds(Map<String, Object> reqData);

    List<AvatarBO> queryByAvatarId(long avatarId);

    AvatarPO queryMaxByVerId(@Param("softv") Integer softv, @Param("chanId") Integer chanId, @Param("avatarv") Integer avatarv,
                             @Param("osVersion") Integer osVersion,@Param("status") Integer status);
}