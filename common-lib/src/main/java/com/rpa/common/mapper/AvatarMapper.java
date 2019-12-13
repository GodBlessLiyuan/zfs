package com.rpa.common.mapper;

import com.rpa.common.bo.AvatarBO;
import com.rpa.common.pojo.AvatarPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * AvatarMapper继承基类
 */
@Mapper
public interface AvatarMapper extends BaseMapper<AvatarPO, Long> {
    AvatarPO queryByVersionCode(Object versionCode);

    List<AvatarBO> queryByAvatarIds(List<Integer> appIds);

    List<Integer> queryAvatarIds(Map<String, Object> reqData);
}