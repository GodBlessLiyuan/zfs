package com.rpa.common.mapper;

import com.rpa.common.pojo.AppChPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppChMapper继承基类
 */
@Mapper
public interface AppChMapper extends BaseMapper<AppChPO, Integer> {

    /**
     * 批量插入
     *
     * @param records 记录集
     * @return
     */
    int batchInsert(List<AppChPO> records);

    /**
     * 根据appId 更新 status
     *
     * @param appId
     * @param status
     * @return
     */
    int updateStatus(int appId, int status);

    /**
     * 根据appId 删除数据
     *
     * @param appId
     * @return
     */
    int deleteByAppId(int appId);

    /**
     * 根据appId 查询数据
     * @param appId
     * @return
     */
    List<AppChPO> queryByAppId(int appId);

    /**
     * 批量删除
     * @param delAcIds 应用渠道主键
     * @return
     */
    int batchDelete(List<Integer> delAcIds);
}