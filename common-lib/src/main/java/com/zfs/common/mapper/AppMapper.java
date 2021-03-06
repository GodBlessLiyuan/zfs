package com.zfs.common.mapper;

import com.zfs.common.bo.AppBO;
import com.zfs.common.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
    /**
     * 根据Id查询数据
     *
     * @param appId
     * @return
     */
    List<AppBO> queryById(int appId);

    List<AppPO> queryVersionname();

    /**
     * 根据 versionCode 查询数据
     *
     * @param versionCode
     * @return
     */
    AppPO queryByVersionCode(Object versionCode);

    /**
     * 查询应用Id
     *
     * @param reqData
     * @return
     */
    List<Integer> queryAppId(Map<String, Object> reqData);

    /**
     * 根据ids查询
     *
     * @param appIds
     * @return
     */
    List<AppBO> queryByIds(List<Integer> appIds);

    /**
     * 查询大于指定版本中VersionId最大的一条记录
     *
     * @param versionCode
     * @param chanId
     * @param status
     * @return
     */
    AppPO queryMaxByVerId(@Param("versionCode") Integer versionCode, @Param("chanId") Integer chanId, @Param("status") Integer status);

    Integer queryIdByVersioncode(Integer softv);

    String queryVersionameByVersioncode(int versioncode);

    List<AppPO> queryCodesAndNames();
    //插件-插件渠道表-t_app表
    List<HashMap<String, Object>> queryPOSByIds(Integer pluginId);

    List<Integer> queryIDSByIDS(Integer pluginId);

    List<String> queryNamesByIDS(Integer pluginId);
}