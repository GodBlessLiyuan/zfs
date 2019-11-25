package com.rpa.server.mapper;

import com.rpa.server.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
    /**
     * 查询大于指定版本中VersionId最大的一条记录
     *
     * @param versionCode
     * @param chanId
     * @param status
     * @return
     */
    AppPO queryMaxByVerId(Integer versionCode, Integer chanId, Integer status);

    int queryIdByVersioncode(Integer softv);
}