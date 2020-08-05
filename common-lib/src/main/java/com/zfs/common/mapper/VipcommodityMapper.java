package com.zfs.common.mapper;

import com.zfs.common.pojo.VipcommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipcommodityMapper extends BaseMapper<VipcommodityPO, Integer> {
    /**
     * 根据渠道Id和产品Id查询数据
     *
     * @param softChannelId 渠道Id
     * @param comTypeId     产品Id
     * @return
     */
    VipcommodityPO queryByChanIdAndComTypeId(@Param("softChannelId") int softChannelId, @Param("comTypeId") int comTypeId,
                                             @Param("commAttr") int commAttr);

    /**
     * 根据渠道Id查询数据
     *
     * @param chanDef
     * @return
     */
    List<VipcommodityPO> queryByChanId(int chanDef);
}