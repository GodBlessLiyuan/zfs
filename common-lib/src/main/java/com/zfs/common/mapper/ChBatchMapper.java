package com.zfs.common.mapper;

import com.zfs.common.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseMapper<ChBatchPO, Integer> {

    Integer queryTypeIdByBatchId(Integer batchId);

    Integer queryDaysByTypeId(Integer typeId);

    Byte queryActiveByPri(Integer batchId);
    //联合表查询，创建时间+天数小于现在时刻，从数据库构造时间
    List<Integer> getListStatusAndGroup(Byte originStatu);
}